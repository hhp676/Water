package com.hongguaninfo.hgdf.wadp.core.templete;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import com.hongguaninfo.hgdf.wadp.core.exception.BizException;
import com.hongguaninfo.hgdf.wadp.core.utils.SessionUtils;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUser;

/**
 * web 层封装类
 * 
 * @author henry
 * 
 */
public abstract class HttpTemplete extends OperateTemplete {
    private HttpServletRequest request;

    /**
     * 构造函数
     * 
     * @param request
     */
    public HttpTemplete(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 构造函数
     * 
     * @param request
     */
    public HttpTemplete(HttpServletRequest request, BindingResult... results) {
        this.request = request;
        for (BindingResult result : results) {
            validResultList.add(result);
        }
    }

    /**
     * 获取session中用户信息
     * 
     * @return SysUser
     * @throws BizException
     */
    public final SysUser getUser() throws BizException {
        SysUser user = SessionUtils.getUser(request);
        if (user == null) {
            throw new BizException("Session 没有找到用户信息");
        }
        return user;
    }
}
