package com.hongguaninfo.hgdf.adp.service.sys;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.base.BasePage;
import com.hongguaninfo.hgdf.adp.core.base.BaseService;
import com.hongguaninfo.hgdf.adp.core.beans.CheckFieldResult;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.adp.dao.sys.SysUserDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysAuth;
import com.hongguaninfo.hgdf.adp.entity.sys.SysConfig;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserPasswordChange;
import com.hongguaninfo.hgdf.adp.shiro.AccountService;
import com.hongguaninfo.hgdf.adp.shiro.ShiroUser;
import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.PasswordUtil;
import com.hongguaninfo.hgdf.core.utils.page.Page;
import com.hongguaninfo.hgdf.core.utils.password.CheckResult;

@Service("sysUserService")
public class SysUserService extends BaseService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private DbIdGenerator dbIdGenerator;

    @Autowired
    private SysDatadicItemService sysDatadicItemBiz;

    @Autowired
    private SysUserAuthJoinService sysUserAuthJoinService;

    @Autowired
    private SysUserRoleJoinService sysUserRoleJoinService;
    
    @Autowired
    private SysUserUgroupJoinService sysUserUgroupJoinService;
    
    @Autowired
    private SysConfigService sysConfigService;
    
    @Autowired
    private SysUserPasswordChangeService sysUserPasswordChangeService;
    
    @Autowired
    private SysUserStateChangeService sysUserStateChangeService;
    /**
     * @Title: getSysUserList
     * @Description: 查询列表
     * @param @param
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     * @since 1.0.0
     */
    public List<SysUser> getSysUserList(BasePage<SysUser> basePage, SysUser vo,
            Map<String, Object> map) throws BizException {
        vo.setIsDelete(0);
        basePage.setFilters(vo);
        Page<SysUser> page = sysUserDao.pageQuery(basePage);
        List<SysUser> list = page.getResult();
        SysConfig sysConfig = sysConfigService.getSysConfigByKey("userinfo_status");
        for (SysUser bo : list) {
            bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
                    bo.getIsFinal() + ""));
            bo.setSexStr(sysDatadicItemBiz.getItemNameByValue("SEX",
                    bo.getSex() + ""));
            bo.setStatusStr(sysDatadicItemBiz.getItemNameByValue("USER_STATUS",
            		bo.getStatus() + ""));
            getUserInfo(bo,sysConfig);
        }
        map.put("rows", list);
        map.put("total", page.getTotalCount());
        return list;
    }

    public void getUserInfo(SysUser bo,SysConfig sysConfig){
    	// 0-不脱敏 1-普通界别 2-全部脱敏
        if(!Constants.USER_INFO_STAUTS_ZERO_LEVEL.equals(sysConfig.getConfigValue())){
        	bo.setUserName(bo.getUserName().substring(0, 1)+"***"+bo.getUserName().substring(bo.getUserName().length()-1));
        	bo.setLoginName(bo.getLoginName().substring(0,1)+"***"+bo.getLoginName().substring(bo.getLoginName().length()-1));
        	if(bo.getEngName() != null && bo.getEngName().length()>0){
        		bo.setEngName(bo.getEngName().substring(0,1)+"***"+bo.getEngName().substring(bo.getEngName().length()-1));
        	}
        	if(bo.getMobile()!=null && bo.getMobile().length()>0 && bo.getMobile().length()>10){
        		bo.setMobile(bo.getMobile().substring(0,3)+"****"+bo.getMobile().substring(7,11));
        	}
        	if(bo.getEmail()!=null && bo.getEmail().length() >0){
        		bo.setEmail(bo.getEmail().substring(0, 1)+"***"+bo.getEmail().substring(bo.getEmail().indexOf("@")));
        	}
        	if(bo.getTelephone() != null &&bo.getTelephone().length()>0 && bo.getTelephone().length()>4){
        		bo.setTelephone(bo.getTelephone().substring(0,4)+"****");
        	}
        }
    }
    /**
     * 验证密码
     * @param loginName
     * @param password
     * @return
     */
    public CheckResult checkPassword(String loginName, String password){

        int validLevel = Constants.ONE;
        
        SysConfig sysConfig = sysConfigService.getSysConfigByKey("sysSecurityLevel");
        
        if(sysConfig != null){
        	validLevel = Integer.parseInt(sysConfig.getConfigValue()) ;
        }
        return PasswordUtil.check(password,loginName, validLevel);
    }
    
    // 获取单条用户数据
    public SysUser getUser(int userId) throws BizException {
        if (userId == 0) {
            return null;
        }
        SysUser bo = sysUserDao.getById(userId);
        bo.setAuthIds(sysUserAuthJoinService.getAuthIdsByUserId(userId));
        return bo;
    }
 // 获取单条用户数据
    public SysUser getUserById(int userId ,String mode) throws BizException {
        if (userId == 0) {
            return null;
        }
        SysUser bo = sysUserDao.getById(userId);
        SysConfig sysConfig = sysConfigService.getSysConfigByKey("userinfo_status");
        if(!"edit".equals(mode) && Constants.USER_INFO_STAUTS_TWO_LEVEL.equals(sysConfig.getConfigValue())){
        	getUserInfo(bo,sysConfig);
        }
        bo.setAuthIds(sysUserAuthJoinService.getAuthIdsByUserId(userId));
        return bo;
    }
    
    /**
     * 获取条件下所有用户列表
     * @param vo
     * @return
     */
    public List<SysUser> getList(SysUser vo){
    	vo.setIsDelete(0);
    	return sysUserDao.getList(vo);
    }

    /**
     * @Title: getLoginUser
     * @Description: 从数据库获取登录用户
     * @param @return
     * @return SysUser 返回类型
     * @throws
     * @since 1.0.0
     */
    public SysUser getLoginUser() throws BizException {
        SysUser sysUser = sysUserDao.getById(SessionUtils.getUserId());
        sysUser.setCrtTimeStr(DateUtil.convertDateTimeToString(sysUser
                .getCrtTime()));
        return sysUser;
    }

    // 新增用户
    public void insertUser(SysUser sysUser) throws BizException {
        // 校验-------------------------------------------------------------------
        if (sysUserDao.getUserByLoginName(sysUser.getLoginName()) != null) {
            throw new BizException(JSON.toJSONString(new CheckFieldResult(
                    "loginName", "登录名已经存在")));
        }

        CheckResult checkResult =  checkPassword(sysUser.getLoginName(),sysUser.getPlainPassword());
        if (!checkResult.isValid()){
            throw new BizException(JSON.toJSONString(new CheckFieldResult(
                    "plainPassword", checkResult.getMsg())) );
        }
        
        // 校验通过-----------------------------------------------------------------
        sysUser.setUserId(dbIdGenerator.getNextId().intValue());
        accountService.entryptPassword(sysUser);
        LOG.debug("------------------------>pwd:" + sysUser.getLoginPwd());
        sysUser.setIsFinal(0);
        sysUser.setIsDelete(0);
        sysUser.setIsValid(1);
        sysUser.setIsLock(0);
        sysUser.setCrtTime(new Date());
        sysUser.setCrtUserid(SessionUtils.getUserId());
        sysUser.setUpdTime(new Date());
        sysUser.setUpdUserid(SessionUtils.getUserId());
        sysUserDao.save(sysUser);

        // 保存权限关联
        sysUserAuthJoinService.insertBatchSysUserAuthJoin(
                sysUser.getCheckedAuthIds(), sysUser.getUserId().intValue());
        sysUserRoleJoinService.insertBatchSysUserRoleJoin(
                sysUser.getCheckedRoleIds(), sysUser.getUserId().intValue());
        sysUserUgroupJoinService.insertBatchSysUserUgroupJoin(
        		sysUser.getCheckedUgroupIds(), sysUser.getUserId().intValue());

    }

    public void updateUser(SysUser sysUser) throws BizException {
    	ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal(); 
    	user.setName(sysUser.getUserName());
        sysUser.setUpdTime(new Date());
        sysUser.setUpdUserid(SessionUtils.getUserId());
        sysUserDao.update(sysUser);
    }
    
    public void updateSysUser(SysUser sysUser){
    	sysUser.setUpdTime(new Date());
        sysUser.setUpdUserid(SessionUtils.getUserId());
        sysUserDao.update(sysUser);
    }

    // 更新用户和用户关联表
    public void updateUserWithJoin(SysUser sysUser) throws BizException {
        sysUser.setUpdTime(new Date());
        sysUser.setUpdUserid(SessionUtils.getUserId());
        sysUserDao.update(sysUser);

        //用户权限关联
        sysUserAuthJoinService.deleteByUserId(sysUser.getUserId().intValue());
        sysUserAuthJoinService.insertBatchSysUserAuthJoin(
                sysUser.getCheckedAuthIds(), sysUser.getUserId().intValue());

        
        //用户角色关联
        sysUserRoleJoinService.deleteByUserId(sysUser.getUserId().intValue());
        sysUserRoleJoinService.insertBatchSysUserRoleJoin(
                sysUser.getCheckedRoleIds(), sysUser.getUserId().intValue());
        
        //用户用户组关联
        sysUserUgroupJoinService.deleteByUserId(sysUser.getUserId().intValue());
        sysUserUgroupJoinService.insertBatchSysUserUgroupJoin(
                sysUser.getCheckedUgroupIds(), sysUser.getUserId().intValue());

    }
    
    //修改密码：判断密码不能与最近几次相同
    public void changePwd(String oldPwd, String newPwd,Integer changeReason,Integer count) throws BizException{
    	SysUser sysUser = new SysUser();
        sysUser.setLoginName(SessionUtils.getUser().getLoginName());
        sysUser.setPlainPassword(oldPwd);
        accountService.entryptPassword(sysUser);
        String oldPassword = sysUser.getLoginPwd();
        LOG.debug("------------------------>pwd:" + sysUser.getLoginPwd());
        SysUser bo = sysUserDao.getById(SessionUtils.getUserId());
        if (bo.getLoginPwd().equals(sysUser.getLoginPwd()) == false) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("field", "oldPwd");
            errorMap.put("message", "旧密码不正确");
            throw new BizException(JSON.toJSONString(errorMap));
        }
        sysUser.setPlainPassword(newPwd);
        String newPassword = sysUser.getLoginPwd();
        CheckResult checkResult =  checkPassword(sysUser.getLoginName(),sysUser.getPlainPassword());
        if (!checkResult.isValid()){
        	 Map<String, String> errorMap = new HashMap<String, String>();
             errorMap.put("field", "newPwd");
             errorMap.put("message", checkResult.getMsg());
             throw new BizException(JSON.toJSONString(errorMap));
        }
        
        boolean checkRecent = sysUserPasswordChangeService.checkRecentUsed(SessionUtils.getUserId(), count, newPassword);
        if(checkRecent){
        	Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("field", "sysPwdChangeCanNotUsed");
            errorMap.put("message", "新密码不能与最近"+count+"次使用的密码相同！");
            throw new BizException(JSON.toJSONString(errorMap));
        }
        
        accountService.entryptPassword(sysUser);
        LOG.debug("------------------------>newpwd:" + sysUser.getLoginPwd());
        SysUser updateVo = new SysUser();
        updateVo.setUserId(SessionUtils.getUserId());
        updateVo.setLoginPwd(sysUser.getLoginPwd());
        updateVo.setUpdTime(new Date());
        updateVo.setUpdUserid(SessionUtils.getUserId());
        updateVo.setLastChangedPwd(new Date());
        sysUserDao.update(updateVo);
        
        sysUserPasswordChangeService.add(SessionUtils.getUserId(),changeReason,oldPassword,newPassword);
    }

    // 修改密码
    public void changePwd(String oldPwd, String newPwd,Integer changeReason) throws BizException {
        SysUser sysUser = new SysUser();
        sysUser.setLoginName(SessionUtils.getUser().getLoginName());
        sysUser.setPlainPassword(oldPwd);
        accountService.entryptPassword(sysUser);
        String oldPassword = sysUser.getLoginPwd();
        LOG.debug("------------------------>pwd:" + sysUser.getLoginPwd());
        SysUser bo = sysUserDao.getById(SessionUtils.getUserId());
        if (bo.getLoginPwd().equals(sysUser.getLoginPwd()) == false) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("field", "oldPwd");
            errorMap.put("message", "旧密码不正确");
            throw new BizException(JSON.toJSONString(errorMap));
        }
        sysUser.setPlainPassword(newPwd);
        String newPassword = sysUser.getLoginPwd();
        CheckResult checkResult =  checkPassword(sysUser.getLoginName(),sysUser.getPlainPassword());
        if (!checkResult.isValid()){
        	 Map<String, String> errorMap = new HashMap<String, String>();
             errorMap.put("field", "newPwd");
             errorMap.put("message", checkResult.getMsg());
             throw new BizException(JSON.toJSONString(errorMap));
        }
        accountService.entryptPassword(sysUser);
        LOG.debug("------------------------>newpwd:" + sysUser.getLoginPwd());
        SysUser updateVo = new SysUser();
        updateVo.setUserId(SessionUtils.getUserId());
        updateVo.setLoginPwd(sysUser.getLoginPwd());
        updateVo.setUpdTime(new Date());
        updateVo.setUpdUserid(SessionUtils.getUserId());
        updateVo.setLastChangedPwd(new Date());
        sysUserDao.update(updateVo);
        
        sysUserPasswordChangeService.add(SessionUtils.getUserId(),changeReason,oldPassword,newPassword);
    }

    // 系统管理员改密
    public void adminChangePwd(String newPwd, int userId) throws BizException {
    	SysUser vo = sysUserDao.getById(userId);
        String userLoginName = vo.getLoginName();
        SysUser sysUser = new SysUser();
        sysUser.setLoginName(userLoginName);
        sysUser.setPlainPassword(newPwd);
        CheckResult checkResult =  checkPassword(sysUser.getLoginName(),sysUser.getPlainPassword());
        if (!checkResult.isValid()){
        	 Map<String, String> errorMap = new HashMap<String, String>();
             errorMap.put("field", "adminChangePwd");
             errorMap.put("message", checkResult.getMsg());
             throw new BizException(JSON.toJSONString(errorMap));
        }
        accountService.entryptPassword(sysUser);
        LOG.debug("------------------------>pwd:" + sysUser.getLoginPwd());
        sysUser.setUserId(userId);
        sysUser.setLoginName(null);
        sysUser.setUpdTime(new Date());
        sysUser.setUpdUserid(SessionUtils.getUserId());
        sysUser.setLastChangedPwd(new Date());
        sysUserDao.update(sysUser);
        
        sysUserPasswordChangeService.add(userId, Constants.PWD_CHANGE_REASON_ADMIN, vo.getLoginPwd(), sysUser.getLoginPwd());
    }

    // 通过id逻辑删除
    public void deleteById(int id) throws BizException {
        SysUser delVo = sysUserDao.getById(id);
        delVo.setUpdTime(new Date());
        delVo.setUpdUserid(SessionUtils.getUserId());
        delVo.setIsDelete(1);
        delVo.setLoginName(delVo.getLoginName() + "_" + id);
        sysUserDao.update(delVo);

    }

    /**
     * @Title: getUserIndexAuthModule
     * @Description: 获取用户首页模块权限（两页）
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     * @since 1.0.0
     */
    public List getUserIndexLeftMenu() {
        // 获得用户的所有权限（包括角色关联、自己关联）
    	Map queryMap = new HashMap();
        queryMap.put("userId",SessionUtils.getUserId());
        queryMap.put("autyType", null);
        List<SysAuth> auths = sysUserDao.getUserAuths(queryMap);

        // 获取用户所有自己关联的权限
        return castMenusListToTree(auths);

    }
    
    
    
    /**
     * @Description: 通过用户id获取所有权限
     * @param @return 设定文件
     * @return List 返回类型
     * @author yuyanlin
     * @throws
     * @since 1.0.0
     */
    public List getUserAuthAll(int userId) {
    	Map queryMap = new HashMap();
        queryMap.put("userId",userId);
        List<SysAuth> auths = sysUserDao.getUserAuths(queryMap);
        return auths;
    }
    
    
    /**
     * @Description: 检查用户是否拥有某权限
     * @author yuyanlin
     * @throws
     * @since 1.0.0
     */
    public boolean checkUserAuth(int userId, int authId) {
    	List<SysAuth> auths = getUserAuthAll(userId);
    	for (SysAuth auth : auths) {
    		if (auth.getAuthId().intValue() == authId) {
    			return true;
    		}
    	}
    	return false;
    }
    
    
    /**
     * @Title: getUserAppAuth
     * @Description: 获取登录用户应用权限
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     * @since 1.0.0
     */
    public List getUserAppAuth() {
    	Map queryMap = new HashMap();
        queryMap.put("userId",SessionUtils.getUserId());
        queryMap.put("authType", new Integer(0));
        List<SysAuth> auths = sysUserDao.getUserAuths(queryMap);
        return auths;
    }
    
    
    /**
     * @Title: getUserAppAuth
     * @Description: 获取登录用户模块权限
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     * @since 1.0.0
     */
    public List getUserModuleAuth() {
    	Map queryMap = new HashMap();
        queryMap.put("userId",SessionUtils.getUserId());
        queryMap.put("authType", new Integer(1));
        List<SysAuth> auths = sysUserDao.getUserAuths(queryMap);
        return auths;
    }
    
    
    /**
     * @Title: getUserAppAuth
     * @Description: 获取登录用户操作权限
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     * @since 1.0.0
     */
    public List getUserOperAuth() {
    	Map queryMap = new HashMap();
        queryMap.put("userId",SessionUtils.getUserId());
        queryMap.put("authType", new Integer(2));
        List<SysAuth> auths = sysUserDao.getUserAuths(queryMap);
        return auths;
    }
       
    public boolean doReadXls(InputStream is) throws Exception{
    	HSSFWorkbook workBook = new HSSFWorkbook(is);
    	HSSFSheet sheet = workBook.getSheetAt(0);
    	boolean result=true;//返回结果标识
    	boolean cell_0=true,cell_1=true,cell_2=true,cell_3=true,cell_4=true;//单元格校验通过与否的标识
    	boolean cell_5=true,cell_6=true,cell_7=true,cell_8=true,cell_9=true;
    	List<SysUser> userArray = new ArrayList<SysUser>();//创建list，用做中间变量，暂时存放user
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	//登录名和英文名，验证规则：只能输入英文字母、数字、下划线和空格
    	String regName = "[a-zA-Z0-9_ ]+";
    	// 邮箱验证规则
        String regEmail = "[a-zA-Z0-9_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
        //只填数字（任意个）
        String regNumber = "^[0-9]*$";        
    	for (int rowNum = 1;rowNum <= sheet.getLastRowNum();rowNum++) {
    		HSSFRow row = sheet.getRow(rowNum);
    		SysUser user = new SysUser(); 		
    		if (row != null) {
    			//用户名校验：必填，长度[1,32]
    			HSSFCell name = row.getCell(0);
    			String userNameStr = getCellValue(name);
    			if(userNameStr.length()>0&&userNameStr.length()<=32){
    				user.setUserName(userNameStr);
    				cell_0 = true;
    			}else{
    				cell_0 = false;
    			}
    			//登录名校验：必填，长度[1,32]，只能输入英文字母、数字、下划线和空格
    			HSSFCell loginName = row.getCell(1);
    			String loginNameStr = getCellValue(loginName);
    			if(loginNameStr.length()>0&&loginNameStr.length()<=32){
    				if(loginNameStr.matches(regName)){
    					user.setLoginName(loginNameStr);
        				cell_1 = true;
    				}else{
    					cell_1 = false;
    				}
    				
    			}else{
    				cell_1 = false;
    			}
   			    //初始密码校验:必填，长度[8,20]
    			HSSFCell pwd = row.getCell(2);
    			String pwdStr = getCellValue(pwd);
    			if(pwdStr.length()>=8&&pwdStr.length()<=20){
    				user.setPlainPassword(pwdStr);
    				cell_2 = true;
    			}else{
    				cell_2 = false;
    			}
    			//英文名校验：长度[0,100]，只能输入英文字母、数字、下划线和空格
    			HSSFCell engName = row.getCell(3);
    			String engNameStr = getCellValue(engName);
    			if(engNameStr.length()!=0){
    				if(engNameStr.length()>=0&&engNameStr.length()<=100){
        				if(engNameStr.matches(regName)){
        					user.setEngName(engNameStr);
            				cell_3 = true;
        				}else{
        					cell_3 = false;
        				}   				
        			}else{
        				cell_3 = false;
        			}
    			}else{
    				cell_3 = true;
    			}
    			
    			//性别校验：只填男或女		
    			String sex = getCellValue(row.getCell(4));
    			if(sex.length()!=0){
    				if ("男".equals(sex)) {
        				user.setSex(1);
        				cell_4 = true;
        			} else if ("女".equals(sex)) {
        				user.setSex(0);
        				cell_4 = true;
        			} else{
        				cell_4 = false;
        			} 
    			}else{
    				cell_4 = true;
    			}
    			
    			//日期格式校验:yyyy-MM-dd
    			HSSFCell birth = row.getCell(5);
    			HSSFCell birth_1 = row.getCell(5);//临时变量，转换为字符串，判断内容是否为空
    			Date date = null;
    			String birthStr = null;
    			if(birth_1.toString().length()!=0){
    				if (HSSFDateUtil.isCellDateFormatted(birth)) {    				
        				date = birth.getDateCellValue();			
        				if(date != null){
        					birthStr =sdf.format(date);
            				user.setBirthday(birthStr);
            				cell_5 = true;
        				}    				
        			}else{
        				cell_5 = false;
        			}
    			}else{
    				cell_5 = true;
    			}
    			//手机号校验：11位,只填数字
    			HSSFCell telphone = row.getCell(7);
    			String telStr = getCellValue(telphone);
    			if(telStr.length()!=0){
    				if(telStr.length()==11){
        				if(telStr.matches(regNumber)){
        					user.setTelephone(telStr);
            				cell_6 = true;
        				}else{
        					cell_6 = false;
        				}
        				
        			}else{
        				cell_6 = false;
        			}
    			}else{
    				cell_6 = true;
    			}
    			
        		//电话号码校验：只填数字
    			HSSFCell mobile = row.getCell(6);
    			String mobileStr = getCellValue(mobile);
    			if(mobileStr.length()!=0){
    				if(mobileStr.matches(regNumber)){
        				user.setMobile(mobileStr);
        				cell_7 = true;
        			}else{
        				cell_7 = false;
        			}   	
    			}else{
    				cell_7 = true;
    			}
    					
    			//邮箱校验
    			HSSFCell email = row.getCell(8);
    			String emailStr = getCellValue(email);
    			if(emailStr.length()!=0){
    				if(emailStr.matches(regEmail)){
        				user.setEmail(emailStr);
        				cell_8 = true;
        			}else{
        				cell_8 = false;
        			}  			
    			}else{
    				cell_8 = true;
    			}
    			
    			//地址校验：长度[0,100]
    			HSSFCell address = row.getCell(9);
    			String addressStr = getCellValue(address);
    			if(addressStr.length()>=0&&addressStr.length()<=100){
    				user.setAddress(addressStr);
    				cell_9 = true;
    			}else{
    				cell_9 = false;
    			}
    			//所有单元格校验都通过时，result=true；否则，result=false
    			if(cell_0==true&&cell_1==true&&cell_2==true&&cell_3==true&&cell_4==true&&cell_5==true
    					&&cell_6==true&&cell_7==true&&cell_8==true&&cell_9==true){
    				userArray.add(user);//将user暂时存放到userArray中去
    				result = true;
    			}else{
    				result = false;
    				break;//一旦出现result为false，立即中止循环
    			}  
    		}else{
    			result = false;
    			break;//一旦出现result为false，立即中止循环
    		}
    		  		
    	}
    	//循环插入userArray
		if(result){
			for(int i=0;i<userArray.size();i++){
				insertUser(userArray.get(i));
			}   			
		}
		return result;
    }
    

    /**
     * @Title: castMenusListToTree
     * @Description: 把普通的菜单list转成treelist
     * @param @param authModules
     * @param @return 设定文件
     * @return List 返回类型
     * @throws
     * @since 1.0.0
     */
    private List castMenusListToTree(List<SysAuth> auths) {
        List<SysAuth> retAuths = new ArrayList<SysAuth>();
        for (SysAuth auth : auths) { // level 1
            if (Constants.ROOT_MENU_ID.equals(auth.getFid())) {
                retAuths.add(auth);
            }
        }
        for (SysAuth authRet : retAuths) { // level 2
            for (SysAuth module : auths) {
                if (authRet.getAuthId().equals(module.getFid())) {
                    authRet.addChild(module);
                    for (SysAuth auth : auths) {
                    	if (module.getAuthId().equals(auth.getFid()) && auth.getAuthType().intValue() == Constants.AUTH_TYPE_MODULE && auth.getIsVisible().intValue() == Constants.ONE.intValue()) {
                    		module.addChild(auth);
                    	}                   
                    }
                }
            }
        }
        
        return retAuths;
    }
    
    @SuppressWarnings("unused")
	private String getCellValue(HSSFCell cell) throws Exception {
		String strCell = "";
		if (cell == null) {
			return "";
		}
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
		    strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf((long)cell.getNumericCellValue());
			break;
//		case HSSFCell.CELL_TYPE_BOOLEAN:
//			strCell = String.valueOf(cell.getBooleanCellValue());
//			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
//			int column = cell.getColumnIndex()+1;
//			int row = cell.getRowIndex();
//			throw new Exception("单元格文本必须是文本格式：第"+ row +"行，第"+ column +"列。");
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		
		return strCell;
	}
    
    public SysUser getPureUser(int userId) throws BizException {
		if (userId == 0) {
			return null;
		}
		SysUser bo = sysUserDao.getById(userId);
		return bo;
	}
    
    /**
	 * 用户是否需要修改密码
	 * @param sysUser
	 * @return
	 */
	public synchronized boolean shouldChangePassword(Integer changeCycle,SysUser sysUser){
		if (changeCycle == null || changeCycle <= 0) {
            return false;
        }
		Date now = new Date();
		long threeMonthAgo = now.getTime() / 1000 - changeCycle * 24 * 60 * 60;
		long lastChangedTime = 0;
		try{
			lastChangedTime = (sysUser.getLastChangedPwd().getTime() / 1000);
		}catch(Exception e){}
		return (lastChangedTime < threeMonthAgo) ? true : false;

	}
	
	public void setLock(Integer userId) throws BizException{
		SysUser user = this.getPureUser(userId);
		if(user == null){
			return;
		}
		
		int oldIsLock = user.getIsLock();
		user.setIsLock(Constants.LOCK);
		user.setLastLockTime(new Date());
		this.updateSysUser(user);
		
		//用户状态变更记录
		sysUserStateChangeService.updateAccountManualToLock(userId, oldIsLock);
	}
	
	public void setUnlock(Integer userId) throws BizException {
		SysUser user = this.getPureUser(userId);
		if(user == null){
			return;
		}
        int oldIsLock = user.getIsLock();
        user.setIsLock(Constants.UNLOCK);
        this.updateSysUser(user);
        
        //用户状态变更记录
        sysUserStateChangeService.updateAccountManualToUnlock(userId, oldIsLock);
    }

}