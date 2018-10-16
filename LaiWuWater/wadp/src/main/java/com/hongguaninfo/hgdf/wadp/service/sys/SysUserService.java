package com.hongguaninfo.hgdf.wadp.service.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.wadp.core.Constants;
import com.hongguaninfo.hgdf.wadp.core.base.BasePage;
import com.hongguaninfo.hgdf.wadp.core.base.BaseService;
import com.hongguaninfo.hgdf.wadp.core.beans.CheckFieldResult;
import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.wadp.core.utils.generator.DbIdGenerator;
import com.hongguaninfo.hgdf.wadp.dao.sys.SysUserDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysAuth;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.wadp.shiro.AccountService;
import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.page.Page;

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
        for (SysUser bo : list) {
            bo.setIsFinalStr(sysDatadicItemBiz.getItemNameByValue("LOGIC_TAG",
                    bo.getIsFinal() + ""));
            bo.setSexStr(sysDatadicItemBiz.getItemNameByValue("SEX",
                    bo.getSex() + ""));
        }
        map.put("rows", list);
        map.put("totalinfo", page.getTotalCount());
        return list;
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

    // 修改密码
    public void changePwd(String oldPwd, String newPwd) throws BizException {
        SysUser sysUser = new SysUser();
        sysUser.setLoginName(SessionUtils.getUser().getLoginName());
        sysUser.setPlainPassword(oldPwd);
        accountService.entryptPassword(sysUser);
        LOG.debug("------------------------>pwd:" + sysUser.getLoginPwd());
        SysUser bo = sysUserDao.getById(SessionUtils.getUserId());
        if (bo.getLoginPwd().equals(sysUser.getLoginPwd()) == false) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("field", "oldPwd");
            errorMap.put("message", "旧密码不正确");
            throw new BizException(JSON.toJSONString(errorMap));
        }
        sysUser.setPlainPassword(newPwd);
        accountService.entryptPassword(sysUser);
        LOG.debug("------------------------>newpwd:" + sysUser.getLoginPwd());
        SysUser updateVo = new SysUser();
        updateVo.setUserId(SessionUtils.getUserId());
        updateVo.setLoginPwd(sysUser.getLoginPwd());
        updateVo.setUpdTime(new Date());
        updateVo.setUpdUserid(SessionUtils.getUserId());
        sysUserDao.update(updateVo);
    }

    // 系统管理员改密
    public void adminChangePwd(String newPwd, int userId) throws BizException {
        String userLoginName = sysUserDao.getById(userId).getLoginName();
        SysUser sysUser = new SysUser();
        sysUser.setLoginName(userLoginName);
        sysUser.setPlainPassword(newPwd);
        accountService.entryptPassword(sysUser);
        LOG.debug("------------------------>pwd:" + sysUser.getLoginPwd());
        sysUser.setUserId(userId);
        sysUser.setLoginName(null);
        sysUser.setUpdTime(new Date());
        sysUser.setUpdUserid(SessionUtils.getUserId());
        sysUserDao.update(sysUser);
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
                }
            }

        }
        return retAuths;
    }

}