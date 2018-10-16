package com.hongguaninfo.hgdf.wadp.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.wadp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.wadp.dao.sys.SysUserDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.wadp.shiro.ShiroDbRealm.ShiroUser;
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

    /**
     * 按登录名查询用户.
     */
    public SysUser findUserByLoginName(String loginName) {
        SysUser user = sysUserDao.getUserByLoginName(loginName);
        Map queryMap = new HashMap();
        queryMap.put("userId",SessionUtils.getUserId());
        queryMap.put("autyType", null);
        user.setAuths(sysUserDao.getUserAuths(queryMap));
        user.setRoles(sysUserDao.getUserRoles(user.getUserId()));
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
}