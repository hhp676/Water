package com.hongguaninfo.hgdf.adp.service;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongguaninfo.hgdf.adp.core.base.BaseService;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.adp.dao.sys.SysTokenDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysToken;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.service.sys.SysTokenService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserService;
import com.hongguaninfo.hgdf.adp.shiro.UsernamePasswordCaptchaToken;
import com.hongguaninfo.hgdf.adp.shiro.UsernamePasswordTokenSignToken;

/**
 * : indexservice
 * 
 * @author henry
 */

@Service("indexService")
public class IndexService extends BaseService {

    @Autowired
    private SysTokenDao sysTokenDao;
    
    @Autowired
    private SysUserService sysUserService;

    public boolean doLoginByToken(SysToken token){
        try {
            //根据userid获取用户信息
            SysUser su = sysUserService.getUser(token.getUserId());
            if(su == null){
                throw new BizException("无登录用户信息！");
            }
            //判断token有效性
            int tCount = sysTokenDao.countValidToken(token);
            if(tCount == 0){
                throw new BizException("token信息不正确 或 已经过期！！");
            }
            //使用token登录
            loginByToken(token, su);
            return true;
        } catch (Exception e) {
            LOG.error("根据token登录失败", e);
            return false;
        }
    }
    
    
    /**
     * 免密码登录
     * @param request
     * @param user
     */
    private final static void loginByToken(SysToken sysToken, SysUser sysUser){
        UsernamePasswordTokenSignToken token = new UsernamePasswordTokenSignToken();  
        token.setUsername(sysUser.getLoginName());  
        token.setPassword(sysUser.getLoginPwd().toCharArray());
        token.setTokenId(sysToken.getTokenId());
        token.setUserId(sysToken.getUserId());
        token.setTokenSign(sysToken.getTokenSign());
        SecurityUtils.getSubject().login(token);
    }
}
