package com.hongguaninfo.hgdf.eai.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.hongguaninfo.hgdf.eai.core.Constants;

/**
 * 
* @ClassName: DefaultFilter 
* @Description: 系统默认filter 
* @author henry 
* @date 2014-2-19 上午11:29:47 
*
 */
public class DefaultFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		 String contextPath = filterConfig.getServletContext().getContextPath();
		 Constants.setContextPath(contextPath);
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new com.hongguaninfo.hgdf.eai.core.security.xss.XssHttpServletRequestWrapper((HttpServletRequest) request), response);
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
