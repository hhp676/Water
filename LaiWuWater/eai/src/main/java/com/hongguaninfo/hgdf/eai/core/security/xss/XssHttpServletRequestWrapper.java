package com.hongguaninfo.hgdf.eai.core.security.xss;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private final Log log = LogFactory.getLog(getClass());

	private static String key = "alert,expression,select,delete,update,insert";
	private static Set<String> notAllowedKeyWords = new HashSet<String>(0);
	static {
		String keyStr[] = key.split(",");
		for (String str : keyStr) {
			notAllowedKeyWords.add(str);
		}
	}

	private String currentUrl;

	public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
		currentUrl = servletRequest.getRequestURI();
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null)
			return null;
		return cleanXSS(value);
	}

	private String cleanXSS(String value) {
		// You'll need to remove the spaces from the html entities below
		value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		value = value.replaceAll("'", "");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']",
				"\"\"");
		// value = value.replaceAll("script", "");
		value = new HTMLInputFilter(false).filter(value);
		value = cleanSqlKeyWords(value);
		return value;
	}

	private String cleanSqlKeyWords(String value) {
		String paramValue = value;
		for (String keyword : notAllowedKeyWords) {
			if (paramValue.length() > keyword.length() + 4
					&& paramValue.contains(keyword)) {
				paramValue = StringUtils.replace(paramValue, keyword, "");
				log.warn(this.currentUrl + "已被过滤，因为参数中包含不允许的关键字(" + keyword
						+ ")");
			}
		}
		return paramValue;
	}

}
