package com.hongguaninfo.hgdf.wadp.core.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.hongguaninfo.hgdf.wadp.core.Constants;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUser;

public class SessionUtils {
    private static final String USER_KEY = "SESSION_USER";

    /**
     * 获取session用户信息
     * 
     * @param request
     * @return
     */
    public final static SysUser getUser(HttpServletRequest request) {
/*        Object user = request.getSession().getAttribute(USER_KEY);
        if (user != null) {
            return (SysUser) user;
        } else {
            return null;
        }*/
        return getUser();
    }

    /**
     * 设置session用户信息
     * 
     * @param request
     * @param user
     */
    public final static void setUser(HttpServletRequest request, SysUser user) {
        request.getSession().setAttribute(USER_KEY, user);
    }

    /**
     * 获取shiro用户信息
     * 
     * @param request
     * @param user
     */
    public final static SysUser getUser() {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            return (SysUser) subject.getPrincipal();
        }
        return null;
    }

    /**
     * 获取用户编号
     * 
     * @param request
     * @param user
     */
    public final static Integer getUserId() {
        SysUser sysUser = getUser();
        return (sysUser == null) ? Constants.ANONYMOUS_ID : sysUser.getUserId();
    }
    

    /**
     * 获取退出登录
     * 
     * @param request
     * @param user
     */
    public final static void kickUser(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            subject.logout();
        }
    }
}