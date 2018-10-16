package com.hongguaninfo.hgdf.adp.shiro;

import javax.annotation.PostConstruct;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.entity.sys.SysAuth;
import com.hongguaninfo.hgdf.adp.entity.sys.SysConfig;
import com.hongguaninfo.hgdf.adp.entity.sys.SysRole;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.service.sys.SysConfigService;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

@Service("shiroDbRealm")
public class ShiroDbRealm extends AuthorizingRealm {

    private static final Log LOG = LogFactory.getLog(ShiroDbRealm.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private SysConfigService sysConfigService;
    


    /**
     * 认证回调函数, 登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;

        String username = token.getUsername();
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }
        
        //判断验证码开关是否打开了～
        SysConfig sysConf = sysConfigService.getSysConfigByKey(Constants.LOGIN_VERIFCODE);
        if ((null != sysConf) && sysConf.getConfigValue().equals("1")) {
            // 增加判断验证码逻辑
            String captcha = token.getCaptcha();
            String exitCode = (String) SecurityUtils.getSubject().getSession()
                    .getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
            if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
                throw new CaptchaException("验证码错误");
            }
        }

        SysUser user = accountService.findUserByLoginName(username);
        if (null == user) {
            throw new UnknownAccountException("No account found for user [" + username + "]");
        }
        accountService.checkAccountLockState(user);
        byte[] salt = new String(user.getLoginName()).getBytes();
        return new SimpleAuthenticationInfo(new ShiroUser(user.getUserId(), user.getLoginName(), user.getUserName()),
                user.getLoginPwd(), ByteSource.Util.bytes(salt), getName());

    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
         * ShiroUser shiroUser = (ShiroUser)
         * principals.fromRealm(getName()).iterator().next();
         */
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        SysUser user = accountService.findUserByLoginName(shiroUser.getLoginName());
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            LOG.debug("用户角色:" + JSONObject.toJSONString(user.getRoles()));
            for (SysRole role : user.getRoles()) {
                info.addRole(role.getRoleCode());
            }
            LOG.debug("用户权限:" + JSONObject.toJSONString(user.getAuths()));
            for (SysAuth auth : user.getAuths()) {
                info.addStringPermission(auth.getAuthCode());
            }
            return info;
        } else {
            return null;
        }
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(AccountService.HASH_ALGORITHM);
        matcher.setHashIterations(AccountService.HASH_INTERATIONS);

        setCredentialsMatcher(matcher);
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }

    

}