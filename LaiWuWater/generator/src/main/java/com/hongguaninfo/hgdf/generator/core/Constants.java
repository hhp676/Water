package com.hongguaninfo.hgdf.generator.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
	 * 私有构造方法，防止实例被创建。
	 */
	private Constants() { }
    /**
     * web容器上下文路径名称
     */
    private static String contextPath = "";
    

    /**
     *
     */
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy年MM月dd日 ah:mm:ss");
    /**
     *
     */
    public static final String ENTITY_PKG = "entity";
    /**
     *
     */
    public static final String MAPPER_PKG = "mapper";
    /**
     *
     */
    public static final String DAO_PKG = "dao";
    /**
     *
     */
    public static final String SERVICE1_PKG = "service";
    /**
     *
     */
    public static final String SERVICE2_PKG = "service.impl";
    /**
     *
     */
    public static final String CONTROLLER_PKG = "web";
    /**
     *
     */
    public static final String MAPPER_SUFFIX = "Mapper";
    /**
     *
     */
    public static final String DAO_SUFFIX = "Dao";
    /**
     *
     */
    public static final String SERVICE1_SUFFIX = "Service";
    /**
     *
     */
    public static final String SERVICE2_SUFFIX = "ServiceImpl";
    /**
     *
     */
    public static final String CONTROLLER_SUFFIX = "Controller";
    /**
     *
     */
    public static final String TYPE_STRING = "String";
    /**
     *
     */
    public static final String TYPE_FLOAT = "java.math.BigDecimal";
    /**
     *
     */
    public static final String TYPE_INTEGER = "Integer";
    /**
     *
     */
    public static final String TYPE_LONG = "Long";
    /**
     *
     */
    public static final String TYPE_TIMESTAMP = "TIMESTAMP";

    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String contextPath) {
        Constants.contextPath = contextPath;
    }
}
