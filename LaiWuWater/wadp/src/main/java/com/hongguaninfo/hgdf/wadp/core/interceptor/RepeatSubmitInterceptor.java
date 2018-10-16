package com.hongguaninfo.hgdf.wadp.core.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * <p>
 * 防止重复提交拦截器
 * </p>
 */
public class RepeatSubmitInterceptor extends HandlerInterceptorAdapter {
	private final Log log = LogFactory.getLog(getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			RepeatSubmitToken annotation = method.getAnnotation(RepeatSubmitToken.class);
			if (annotation != null) {
				boolean needSaveSession = annotation.saveToken();
				if (needSaveSession) {
					RepeatSubmitUtil.putRepeatToken(request, UUID.randomUUID().toString());
				}
				boolean needRemoveSession = annotation.removeToken();
				if (needRemoveSession) {
					if (isRepeatSubmit(request)) {
						return false;
					}
					RepeatSubmitUtil.removeRepeatToken(request);
				}
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		ArrayList<String> tokens = RepeatSubmitUtil.getTokens(request);
		if (tokens.size() == 0) {
			return true;
		}
		if (!tokens.contains(RepeatSubmitUtil.getRequestToken(request))) {
			return true;
		}
		return false;
	}
}