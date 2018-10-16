/**
 * Project Name:oa-core
 * File Name:MyShiroHttpServletResponse.java
 * Package Name:com.hongguaninfo.hgdf.adp.shiro.filter
 * Date:2016年10月13日上午10:53:14
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 *
*/

package com.hongguaninfo.hgdf.wadp.shiro;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.ShiroHttpServletResponse;

/**
 * ClassName:MyShiroHttpServletResponse <br/>
 * Date:     2016年10月13日 上午10:53:14 <br/>
 * @author   henry
 * @version  
 * @since    V2.6.0
 * @see 	 
 */
public class MyShiroHttpServletResponse extends ShiroHttpServletResponse {
    public MyShiroHttpServletResponse(HttpServletResponse wrapped, ServletContext context,
        ShiroHttpServletRequest request) {
        super(wrapped, context, request);
    }
    
    @Override
    protected String toEncoded(String url, String sessionId) {
        if ((url == null) || (sessionId == null))
            return (url);
        
        String path = url;
        String query = "";
        String anchor = "";
        int question = url.indexOf('?');
        if (question >= 0) {
            path = url.substring(0, question);
            query = url.substring(question);
        }
        int pound = path.indexOf('#');
        if (pound >= 0) {
            anchor = path.substring(pound);
            path = path.substring(0, pound);
        }
        StringBuilder sb = new StringBuilder(path);
        sb.append(anchor);
        sb.append(query);
        return (sb.toString());
    }
}
