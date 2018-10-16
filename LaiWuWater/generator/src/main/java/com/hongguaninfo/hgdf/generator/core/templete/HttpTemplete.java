package com.hongguaninfo.hgdf.generator.core.templete;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

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

}
