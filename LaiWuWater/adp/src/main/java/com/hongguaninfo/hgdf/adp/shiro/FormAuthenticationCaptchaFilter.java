package com.hongguaninfo.hgdf.adp.shiro;

import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.core.interceptor.RepeatSubmitUtil;
import com.hongguaninfo.hgdf.adp.core.utils.ResponseUtils;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLog;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLoginLog;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserLogService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserLoginLogService;
import com.hongguaninfo.hgdf.core.utils.encrypt.RSAUtil;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

@Service("formAuthenticationFilter")
public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {

	public Log log = LogFactory.getLog(getClass());

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

	@Autowired
	private SysUserLogService sysUserLogService;
	
	@Autowired
	private SysUserLoginLogService sysUserLoginLogService;
	
	@Autowired
	private AccountService accountService;

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, DEFAULT_CAPTCHA_PARAM);
	}

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = String.valueOf(RSAUtil.decryptStringByJs(getUsername(request)));
		String password = String.valueOf(RSAUtil.decryptStringByJs(getPassword(request)));
		String captcha = String.valueOf(getCaptcha(request));
		// boolean rememberMe = isRememberMe(request);
		//不记住用户
		boolean rememberMe = false;
		String host = String.valueOf(getHost(request));

		return new UsernamePasswordCaptchaToken(username, password.toCharArray(), rememberMe, host, captcha);
	}

	/*
	 * 主要是针对登入成功的处理方法。对于请求头是AJAX的之间返回JSON字符串。
	 */
	@Override
	public boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		SysUser user = (SysUser) subject.getPrincipal();
		saveUserLog(request,"onLoginSuccess", "登录系统成功", "");
		saveUserLoginLog(request,Constants.SUCCESS,user.getUserId(),Constants.IS_ACCUMULATE_LOGIN_TIMES_);
		if (!isAjax(request)) {// 不是ajax请求
			issueSuccessRedirect(request, response);
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("{\"success\":true,\"message\":\"登入成功\"}");
			out.flush();
			out.close();
		}
		return false;
	}

	/**
	 * 主要是处理登入失败的方法
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		String messageText = null;
		Integer isCount = Constants.NOT_ACCUMULATE_LOGIN_TIMES_;
		Integer userId = null;
		SysUser user = this.getSysUserByToken(token);
		if(user != null){
			userId =  user.getUserId() == null ? null : user.getUserId();
		}
		
		String ip = request.getRemoteAddr();
		boolean ifLock = false;
		
		try {
			String message = e.getClass().getSimpleName();
			if ("IncorrectCredentialsException".equals(message)) {
				messageText = "{\"success\":false,\"message\":\"密码错误\"}";
				isCount = Constants.IS_ACCUMULATE_LOGIN_TIMES_;
				ifLock = true;
			} else if ("UnknownAccountException".equals(message)) {
				messageText = "{\"success\":false,\"message\":\"账号不存在\"}";
			} else if ("LockedAccountException".equals(message)) {
				messageText = "{\"success\":false,\"message\":\"账号被锁定\"}";
			} else {
				messageText = "{\"success\":false,message:\"未知错误\"}";
			}
			saveUserLoginLog(request,Constants.FALIUER,userId,isCount);
			saveUserLog(request, "onLoginFailure", "登录系统失败", token.getPrincipal().toString());
			if (ifLock) {
				if(user.getIsLock() != Constants.LOCK){
					accountService.lock(userId,ip);
				}
			}
		} catch (BizException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return onAccountLoginFailure(token,e,messageText,request,response);
	}
	
	private boolean onAccountLoginFailure(AuthenticationToken token, AuthenticationException e,
	        String messageText, ServletRequest request, ServletResponse response) {
		if (!isAjax(request)) {// 不是ajax请求
			setFailureAttribute(request, e);
			return true;
		}
		
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(messageText);
			out.flush();
			out.close();
		}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		} 
		return false;
	}

	/**
	 * 所有请求都会经过的方法。
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		log.info("权限验证");
		if (log.isTraceEnabled()) {
			log.trace("Attempting to access a path which requires authentication.  Forwarding to the "
					+ "Authentication url [" + getLoginUrl() + "]");
		}
		if (!isAjax(request)) {// 不是ajax请求
			if (isLoginRequest(request, response)) {
				if (isLoginSubmission(request, response)) {
					if (log.isTraceEnabled()) {
						log.trace("Login submission detected.  Attempting to execute login.");
					}
					return executeLogin(request, response);
				} else {
					if (log.isTraceEnabled()) {
						log.trace("Login page view.");
					}
					// allow them to see the login page ;)
					return true;
				}
			} else {
				saveRequestAndRedirectToLogin(request, response);
				// redirectToLogin(request, response);
			}
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain;charset=utf-8");
			HttpServletResponse rep = (HttpServletResponse) response;
			rep.setHeader("sessionstatus", "timeout");// 在响应头设置session状态
			rep.getWriter().write("{\"success\":false,\"message\":\"timeout\"}");
		}
		return false;
	}
	
	private boolean isAjax(ServletRequest request) {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		return "XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With"));
	}

	/**
	 * 覆盖父类方法、解决所有失败的认证都跳转到登录页面
	 */
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
	}

	/**
	 * 重写执行登录方法,解决jsessionid重置的问题，排除掉Session_Fixation漏洞
	 */
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {

	    AuthenticationToken token = createToken(request, response);
		if (token == null) {
			String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken "
					+ "must be created in order to execute a login attempt.";
			throw new IllegalStateException(msg);
		}
		try {
			final Subject subject = getSubject(request, response);
			// 获取session数据
			Session session = subject.getSession();
			final LinkedHashMap<Object, Object> attributes = new LinkedHashMap<Object, Object>();
			final Collection<Object> keys = session.getAttributeKeys();
			for (Object key : keys) {
				final Object value = session.getAttribute(key);
				if (value != null) {
					attributes.put(key, value);
				}
			}
			session.stop();
			// 登录成功后复制session数据
			session = subject.getSession(true);
			for (final Object key : attributes.keySet()) {
				session.setAttribute(key, attributes.get(key));
			}
			//此处token效验必须放到新session之后
			if (request instanceof HttpServletRequest){
	            if (RepeatSubmitUtil.isRepeatSubmit((HttpServletRequest)request)){
	                ResponseUtils.renderText((HttpServletResponse)response, "请不要重复刷新!");
	                return false;
	            }
	            RepeatSubmitUtil.removeRepeatToken((HttpServletRequest)request);
	        }
			subject.login(token);
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}
	
	private void saveUserLog(ServletRequest request,String code, String name, String remark) {
		SysUserLog sysUserLog = new SysUserLog();
		sysUserLog.setRemark(remark);
		sysUserLog.setOperIP(((HttpServletRequest) request).getRemoteAddr());
		sysUserLog.setCrtTime(new Date());
		sysUserLog.setOperCode(code);
		sysUserLog.setOperName(name);
		sysUserLogService.saveSysUserLog(sysUserLog);
	}
	
	private void saveUserLoginLog(ServletRequest request,Integer isSuccess,
			Integer userId,Integer isAccumulateLoginTimes) throws BizException{
		SysUserLoginLog bo = new SysUserLoginLog();
		String operName = "用户登录" + (isSuccess == 1 ? "成功"
	            : "失败");
	    String operCode = "onLogin" + (isSuccess == 0 ? "Failure"
	            : "Success");
		bo.setAccountId(userId);
		bo.setIsAccumulateLoginTimes(isAccumulateLoginTimes);
		bo.setOperCode(operCode);
        bo.setOperName(operName);
        bo.setOperIp(((HttpServletRequest) request).getRemoteAddr());
        bo.setReqUri(((HttpServletRequest) request).getRequestURI());
        bo.setOperRes(isSuccess);
		bo.setCrtTime(new Date());
		bo.setCrtUserId(userId);
		sysUserLoginLogService.addSysUserLoginLog(bo);
	}
	
	private SysUser getSysUserByToken(AuthenticationToken token){
		String userName = null;
		if(token instanceof UsernamePasswordCaptchaToken){
			userName = ((UsernamePasswordCaptchaToken)token).getUsername();
		}else if(token instanceof UsernamePasswordTokenSignToken){
			userName = ((UsernamePasswordTokenSignToken)token).getUsername();
		}
		return accountService.findUserByLoginName(userName);
	}
}