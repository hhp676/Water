package com.hongguaninfo.hgdf.adp.shiro;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.dao.sys.SysUserDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysConfig;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLog;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLoginLog;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserStateChange;
import com.hongguaninfo.hgdf.adp.service.sys.SysConfigService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserLogService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserLoginLogService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserStateChangeService;
import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.EncodeUtils;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.core.utils.security.DigestUtils;

@Service("accountService")
public class AccountService {

    private static final Log LOG = LogFactory.getLog(AccountService.class);

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;

    @Autowired
    private SysUserDao sysUserDao;
    
    @Autowired
    private SysConfigService sysConfigService;
    
    @Autowired
    private SysUserStateChangeService sysUserStateChangeService;
    
    @Autowired
    private SysUserLogService sysUserLogService;
    
    @Autowired
    private SysUserLoginLogService sysUserLoginLogService;
    
    /**
     * ip解锁时间
     */
    private Integer ipLockingTime = null;
    
    /**
     * 账号解锁时间
     */
    private Integer accountLockingTime = null;

    /**
     * 按登录名查询用户.
     */
    public SysUser findUserByLoginName(String loginName) {
        SysUser user = sysUserDao.getUserByLoginName(loginName);
        Map queryMap = new HashMap();
        Integer userId =0;
        if(user != null){
        	userId = user.getUserId();
        	queryMap.put("userId",userId);
        	//  LOG.debug("------SESSIONUTIL USER " + SessionUtils.getUserId());
        	queryMap.put("autyType", null);
        	user.setAuths(sysUserDao.getUserAuths(queryMap));
        	user.setRoles(sysUserDao.getUserRoles(userId));
        }
        return user;
    }

    /**
     * 判断是否超级管理员.
     */
    private boolean isSupervisor(SysUser user) {
        return ((user.getUserId() != null) && (user.getUserId() == 1L));
    }

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    public void entryptPassword(SysUser user) {
        /*
         * byte[] salt = DigestUtils.generateSalt(SALT_SIZE);
         * user.setSalt(EncodeUtils.encodeHex(salt));
         */

        byte[] salt = new String(user.getLoginName()).getBytes();
        byte[] hashPassword = DigestUtils.sha1(user.getPlainPassword()
                .getBytes(), salt, HASH_INTERATIONS);
        user.setLoginPwd(EncodeUtils.encodeHex(hashPassword));
    }

    /**
     * 取出Shiro中的当前用户LoginName.
     */
    private String getCurrentUserName() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.getLoginName();
    }
    
    /**
     * 1：检验登录用户是否锁定。
     *:2：先检验账号状态，若为锁定，则所有ip下不能登录；
     *:3：若未锁定，再检验账号在当前IP的状态是否锁定(暂时不支持此条功能)
     */
    protected void checkAccountLockState(SysUser user){
    	//登录时检查用户锁定状态开关(默认开启)
    	SysConfig loginCheckUserLockCon = sysConfigService.getSysConfigByKey(Constants.LOGIN_CHECKUSERLOCK);
    	if(loginCheckUserLockCon == null && StringUtils.isNotBlank(loginCheckUserLockCon.getConfigValue()) && 
    			"0".endsWith(loginCheckUserLockCon.getConfigValue())){
    		LOG.debug("登录检查用户锁定状态开关未开，{密码验证}前不进行用户锁定状态校验:" + user.getLoginName());
            return;
    	}
    	
    	//校验账号状态
    	if(user.getIsLock() == 1){
    		LOG.debug(user.getLoginName() + "账号为锁定状态");
    		throw new LockedAccountException("Account are locked by this realm.");
    	}
    	
    	//账号在当前IP的状态
        /*Subject subject = SecurityUtils.getSubject();
        HttpServletRequest request = (HttpServletRequest) ((WebSubject) subject).getServletRequest();
        String clientIp = request.getRemoteAddr();
        SysUserStateChange latestState = sysUserStateChangeService.getCurrentOne(user.getUserId(), clientIp, 1);
        if (latestState != null
                && latestState.getCurrentState() == 1) {
                LOG.debug("账号IP为锁定状态");
                throw new LockedAccountException("Account with current ip are locked by this realm.");
        }*/
        
    }
    
    public void lock(Integer userId,String ip) throws Exception{
		boolean isBlockUser = lockSysUser(userId,ip);
		
		if (isBlockUser) {
            String msg = "密码错误频繁，账号锁定，预计" + accountLockingTime + "min后恢复";
            LOG.info(msg);
            throw new Exception(msg);
        }
	}
	
    boolean lockSysUser(Integer userId,String ip) throws Exception{
		//登录自动锁定用户功能开关
		SysConfig lockCon = sysConfigService.getSysConfigByKey(Constants.LOGIN_USERLOCK);
		if(lockCon == null || StringUtils.isBlank(lockCon.getConfigValue()) ||
				"0".equals(lockCon.getConfigValue())){
			return false;
		}
		
		LOG.debug("登录自动锁定账号功能已开，开始执行账号锁定功能校验:");
		SysConfig lockCon1 = sysConfigService.getSysConfigByKey(Constants.LOGIN_CHECKUSERLOCK);
		if(lockCon1 == null || StringUtils.isBlank(lockCon1.getConfigValue()) ||
				"0".equals(lockCon1.getConfigValue())){
			LOG.debug("登录检查用户锁定状态开关被禁用，{密码验证}前不进行用户锁定状态校验，登录自动锁定账号功能需打开该开关");
            throw new Exception("[登录检查用户锁定状态开关]被禁用,账号锁定功能不执行");
		}
		
		
		Integer sysLoginUserLockType = Constants.SYS_LOGIN_USER_LOCK_TYPE_DEFAULT;
	    Integer countsLimit = Constants.SYS_USER_PASS_ATTEMPTS_COUNTS_LIMIT_DEFAULT;
	    Integer timeLimit = Constants.ZERO;
	    Integer lockingTime = Constants.SYS_USER_LOCKING_TIME_DEFAULT;
		SysConfig sysLoginUserLockTypeCon = sysConfigService.getSysConfigByKey(Constants.LOGIN_USERLOCKTYPE);
		SysConfig countsLimitCon = sysConfigService.getSysConfigByKey(Constants.USERPASS_ATTEMPTSCOUNTS_LIMIT);
		SysConfig timeLimitCon = sysConfigService.getSysConfigByKey(Constants.USERPASS_ATTEMPTSTIME_LIMIT);
		SysConfig lockingTimeCon = sysConfigService.getSysConfigByKey(Constants.USERLOCKING_TIME);
		countsLimit = (countsLimitCon != null && StringUtils.isNotBlank(countsLimitCon.getConfigValue())) ? 
				Integer.parseInt(countsLimitCon.getConfigValue()) : countsLimit;
		timeLimit = (timeLimitCon != null && StringUtils.isNotBlank(timeLimitCon.getConfigValue())) ? 
				Integer.parseInt(timeLimitCon.getConfigValue()) : timeLimit; 
		lockingTime = (lockingTimeCon != null && StringUtils.isNotBlank(lockingTimeCon.getConfigValue())) ? 
				Integer.parseInt(lockingTimeCon.getConfigValue()) : lockingTime; 
				sysLoginUserLockType = (sysLoginUserLockTypeCon != null && StringUtils.isNotBlank(sysLoginUserLockTypeCon.getConfigValue())) ? 
				Integer.parseInt(sysLoginUserLockTypeCon.getConfigValue()) : sysLoginUserLockType; 
		
		//默认使用锁定账号
		
		return dolockSysUser(userId,ip,countsLimit,timeLimit,lockingTime,sysLoginUserLockType);
	}
    
    public boolean dolockSysUser(Integer userId,String ip,Integer countsLimit,Integer timeLimit,
    		Integer lockingTime,Integer scenerioType) throws BizException{
    	
         SysUser user = sysUserDao.getById(userId);
         if(user == null){
        	 LOG.debug("------用户不存在------");
             return false;
         }
         
         //查询最近countsLimit次用户登录日志(密码输入有countsLimit次机会，用完即锁定账户)
         SysUserLoginLog vo = new SysUserLoginLog();
         vo.setAccountId(userId);
         vo.setLimitCount(countsLimit);
         vo.setIsAccumulateLoginTimes(Constants.IS_ACCUMULATE_LOGIN_TIMES_);
         List<SysUserLoginLog> list = sysUserLoginLogService.getList(vo);
         if (list == null || list.size() < countsLimit) {
        	 LOG.debug("------登录日志记录少于" + countsLimit + "次， 不予锁定------");
             return false;
         }
         
         //查询最近countsLimit此登录日志是否有登陆成功的
         if(sysUserLoginLogService.contants(list, Constants.SUCCESS)){
        	 LOG.debug("------其中登录成功过， 不予锁定------");
             return false;
         }
         
         //若不包含登录成功记录，判断是否满足账户锁定条件
         boolean ifLock = checkLockSysUser(userId,ip,list,timeLimit);
         
         //锁定账号
         if(ifLock){
        	 //账号目前状态
        	 int originState = user.getIsLock(); 
        	 
        	 //将账号置为锁定
        	 user.setIsLock(1);
        	 user.setLastLockTime(new Date());
        	 user.setUpdTime(new Date());
        	 sysUserDao.update(user);
        	 
        	//账号锁定状态变更记录
             sysUserStateChangeService.updateAccountToLock(userId, originState);
             LOG.debug("添加状态变更记录成功-{登录失败 系统自动锁定账号}");
             
            //保存日志
             SysUserLog userLog = new SysUserLog();
             userLog.setUserId(Constants.ADMIN_ID);
             userLog.setOperCode("doLockAccount");
             userLog.setOperName("登录失败频繁系统自动锁定账号");
             userLog.setOperIP(ip);
             sysUserLogService.saveSysUserLog(userLog);
         }
         return false;
    }
    
    private boolean checkLockSysUser(Integer userId,String ip,List<SysUserLoginLog> list,Integer timeLimit){
    	return checkCanLock(userId,ip,list,timeLimit,Constants.TYPE_ACCOUNT);
    }
    
    private boolean checkCanLock(Integer userId,String ip,List<SysUserLoginLog> loginlogs,Integer timeLimit,Integer type){
    	SysUserLoginLog earliestLog = loginlogs.get(loginlogs.size() - 1);
        Date earlistLogTime = earliestLog.getCrtTime();
        LOG.debug("n次记录中最早记录：" + JSON.toJSONString(earliestLog));
        LOG.debug("n次记录中最早记录的时间：" + DateUtil.convertDateToString(earlistLogTime));
        
        boolean isAfterUnlock = isAfterUnlock(userId, ip, earlistLogTime, type);
        LOG.debug("是否在上次解锁之后：" + isAfterUnlock);
        
        boolean isInTimeLimit = isInTimeLimit(timeLimit, earlistLogTime);
        LOG.debug("是否在密码试错时间范围" + timeLimit + "内:" + isInTimeLimit);
        
        if (isAfterUnlock && isInTimeLimit) {
            return true;
        }
        
    	return false;
    }
    
    /**
     * 最近N此密码是否再上次解锁之后
     * @param userId
     * @param ip
     * @param earlistLogTime
     * @param type
     * @return
     */
    private boolean isAfterUnlock(Integer userId,String ip,Date earlistLogTime,Integer type){
    	//获取n次记录时间内的用户状态变更记录(只要期间有状态变更，便不满足锁定条件)
        SysUserStateChange filter = new SysUserStateChange();
        filter.setAccountId(userId);
        filter.setIp(ip);
        filter.setType(type);
        filter.setStartTime(earlistLogTime);
        List<SysUserStateChange> list = sysUserStateChangeService.getList(filter);
        if (list != null && list.size() > 0) {
            LOG.debug("获取到用户状态变更记录，不在上次解锁之后");
            LOG.debug(list.size() + "条状态变更记录:" + JSON.toJSONString(list));
            return false;
        }
        LOG.debug("没有获取到用户状态变更记录，在上次解锁之后");
        
        return true;
    }
    
    /**
     * 最近n次密码错误记录均在密码试错时间范围内
     * @param timeLimit 密码试错时间范围(min)
     * @param logTime n次记录中最早记录时间
     * @return
     */
    private boolean isInTimeLimit(int timeLimit, Date logTime) {
        if(timeLimit <= 0){
            //当未设时间范围或为0时，密码错误累计没有时间限制，只要达到错误次数上限即可
            return true;
        }
        return DateUtil.isMinsInterval(logTime, timeLimit);
    }
}