package com.hongguaninfo.hgdf.eai.core;

/**
 * 
 * @ClassName: Constants
 * @Description: 全局常量定义类
 * @author henry
 * @date 2014-2-19 上午11:24:11
 * 
 */
public class Constants {
	/**
	 * web容器上下文路径名称
	 */
	private static String contextPath = "";

	public static String getContextPath() {
		return contextPath;
	}

	public static void setContextPath(String contextPath) {
		Constants.contextPath = contextPath;
	}
	
}
