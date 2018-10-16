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
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.dao.sys.SysTokenDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysAuth;
import com.hongguaninfo.hgdf.adp.entity.sys.SysConfig;
import com.hongguaninfo.hgdf.adp.entity.sys.SysRole;
import com.hongguaninfo.hgdf.adp.entity.sys.SysToken;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.service.sys.SysConfigService;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;


@Service("shiroDbTokenRealm")
public class ShiroDbTokenRealm extends AuthorizingRealm {

    private static final Log LOG = LogFactory.getLog(ShiroDbTokenRealm.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private SysConfigService sysConfigService;


    @Autowired
    private SysTokenDao sysTokenDao;

    /**
     * 认证回调函数, 登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
    	if (!(authcToken instanceof UsernamePasswordTokenSignToken)) {
            return null;
        }
        UsernamePasswordTokenSignToken token = (UsernamePasswordTokenSignToken) authcToken;

        String username = token.getUsername();
        Integer userId = token.getUserId();
        Integer tokenId = token.getTokenId();        
        String tokenSign = token.getTokenSign();
        
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.");
        }

        SysUser user = accountService.findUserByLoginName(username);
        if (null == user) {
            throw new UnknownAccountException("No account found for user [" + username + "]");
        }
        
        //判断token是否存在并且有效
        SysToken st = new SysToken();
        st.setUserId(user.getUserId());
        st.setTokenId(tokenId);
        st.setTokenSign(token.getTokenSign());
        int tCount = sysTokenDao.countValidToken(st);
        if(tCount == 0){
                throw new UnknownAccountException("token信息不正确 或 已经过期！");
        }

        
        return new SimpleAuthenticationInfo(new ShiroUser(user.getUserId(), user.getLoginName(), user.getUserName()),
                user.getLoginPwd(), getName());
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
        setCredentialsMatcher(new SimpleCredentialsMatcher());
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