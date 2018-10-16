package com.hongguaninfo.hgdf.adp.job.service;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.base.BaseService;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.entity.sys.SysConfig;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLog;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserStateChange;
import com.hongguaninfo.hgdf.adp.service.sys.SysConfigService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserLogService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserStateChangeService;
import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("unlockSysUserService")
public class UnlockSysUserService extends BaseService{
	
	@Autowired
	private SysConfigService sysConfigService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysUserStateChangeService sysUserStateChangeService;
	
	@Autowired
	private SysUserLogService sysUserLogServicec;
	
	public void doUnlockSysUserList() throws BizException{
		
		//登录时检查用户锁定状态开关(默认开启)
    	SysConfig loginCheckUserLockCon = sysConfigService.getSysConfigByKey(Constants.LOGIN_USERLOCK);
    	if(loginCheckUserLockCon == null && StringUtils.isNotBlank(loginCheckUserLockCon.getConfigValue()) && 
    			"0".endsWith(loginCheckUserLockCon.getConfigValue())){
            return;
    	}
    	
    	Integer lockingTime = Constants.SYS_USER_LOCKING_TIME_DEFAULT;
    	SysConfig lockingTimeConf = sysConfigService.getSysConfigByKey(Constants.USERLOCKING_TIME);
    	if(lockingTimeConf != null && StringUtil.isNotBlank(lockingTimeConf.getConfigValue())){
    		lockingTime = Integer.parseInt(lockingTimeConf.getConfigValue());
    	}
    	
    	LOG.debug("----------------账号解锁开始----------------");
    	doUnlockSysUserList(lockingTime);
    	LOG.debug("----------------账号解锁结束----------------");
	}
	
	//账号解锁
	private void doUnlockSysUserList(Integer lockingTime) throws BizException{
		LOG.debug("----------------########账号解锁功能doUnlockAccount...----------------");
        LOG.debug("当前账号自动解锁时间(min)：" + lockingTime);
        
        //获取锁定账户列表
        SysUser vo = new SysUser();
        vo.setIsLock(1);
        List<SysUser> lockUsersList = sysUserService.getList(vo);
        if (lockUsersList == null || lockUsersList.size() < 1) {
            LOG.debug("------暂时没有状态为锁定的账号------");
            return;
        }
        
        LOG.debug("获取到的锁定账号数量：" + lockUsersList.size());
        for(SysUser lockUser : lockUsersList){
    		Integer usereId = lockUser.getUserId();
    		String loginName = lockUser.getLoginName();
    		Date lastLockTime = lockUser.getLastLockTime();
    		
    		//对于手动锁定的账号，不予自动解锁
    		SysUserStateChange lockUserState = sysUserStateChangeService.getCurrentOne(usereId);
    		LOG.debug("获取账号的最新状态变更记录：" + lockUserState == null ? "无"
                    : JSON.toJSONString(lockUser));
                if (lockUserState != null && lockUserState.getCurrentState() == Constants.LOCK
                    && lockUserState.getCrtType() == Constants.MANUAL) {
                    LOG.debug("------对于手动锁定的账号，不予自动解锁------");
                    return; //continue;
                }
            
            Date now = new Date();
            long time = now.getTime() - (lastLockTime == null ? 0
                : lastLockTime.getTime());
            
            //若已过解锁时间
            if (time >= lockingTime * DateUtil.MIN_MILLISECOND) {
                
                LOG.debug("------已过解锁时间，开始解锁账号------");
                
                //账号置为解锁
                lockUser.setIsLock(Constants.UNLOCK);
                sysUserService.updateSysUser(lockUser);
                LOG.debug("账号状态置为解锁成功!");
                
                //账号解锁状态变更记录
                sysUserStateChangeService.updateAccountToUnlock(usereId, Constants.LOCK);
                LOG.debug("添加状态变更记录成功-{系统自动解锁账号}");
                
                //保存日志
                String content = usereId + "," + loginName;
                saveUserLog(Constants.ADMIN_ID, "doUnlockAccount","系统自动解锁账号", content);
                LOG.debug("保存用户日志成功-{系统自动解锁账号} " + content);
                
                LOG.info("锁定时间结束，账号自动解锁：" + content);
            }
        }
	}
	
	//账号解锁某个账户
	public void doUnlockSysUser(SysUser lockUser,Integer lockingTime) throws BizException{
		Integer usereId = lockUser.getUserId();
		String loginName = lockUser.getLoginName();
		Date lastLockTime = lockUser.getLastLockTime();
		
		//对于手动锁定的账号，不予自动解锁
		SysUserStateChange lockUserState = sysUserStateChangeService.getCurrentOne(usereId);
		LOG.debug("获取账号的最新状态变更记录：" + lockUserState == null ? "无"
                : JSON.toJSONString(lockUser));
            if (lockUserState != null && lockUserState.getCurrentState() == Constants.LOCK
                && lockUserState.getCrtType() == Constants.MANUAL) {
                LOG.debug("------对于手动锁定的账号，不予自动解锁------");
                return;
            }
        
        Date now = new Date();
        long time = now.getTime() - (lastLockTime == null ? 0
            : lastLockTime.getTime());
        
        //若已过解锁时间
        if (time >= lockingTime * DateUtil.MIN_MILLISECOND) {
            
            LOG.debug("------已过解锁时间，开始解锁账号------");
            
            //账号置为解锁
            lockUser.setIsLock(Constants.UNLOCK);
            sysUserService.updateUser(lockUser);
            LOG.debug("账号状态置为解锁成功!");
            
            //账号解锁状态变更记录
            sysUserStateChangeService.updateAccountToUnlock(usereId, Constants.LOCK);
            LOG.debug("添加状态变更记录成功-{系统自动解锁账号}");
            
            //保存日志
            String content = usereId + "," + loginName;
            saveUserLog(Constants.ADMIN_ID, "doUnlockAccount","系统自动解锁账号", content);
            LOG.debug("保存用户日志成功-{系统自动解锁账号} " + content);
            
            LOG.info("锁定时间结束，账号自动解锁：" + content);
        }
		
	}
	
	private void saveUserLog(Integer userId, String operCode, String operName,String content) {
		SysUserLog userLog = new SysUserLog();
		userLog.setUserId(userId);
		userLog.setOperCode(operCode);
		userLog.setOperName(operName);
		userLog.setRemark(content);
		sysUserLogServicec.saveSysUserLog(userLog);
	}
}
