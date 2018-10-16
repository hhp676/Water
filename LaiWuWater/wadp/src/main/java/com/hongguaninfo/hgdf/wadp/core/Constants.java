package com.hongguaninfo.hgdf.wadp.core;

import java.math.BigDecimal;

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

    /**
     * 每页显示数
     */
    public static final int PAGE_SIZE = 30;

    /**
     * 是否删除 （0.有效）
     */
    public static int isDeleteVaild = 0;
    
    /**
     * 是否删除 （1.已删除）
     */
    public static int isDeleteDisVaild = 1;

    /**
     * config中的数据库ID存储key
     */
    public static final String NEXT_DB_ID = "next_db_id";
    
    /**
     * config中的验证码开关存储key
     */
    public static final String LOGIN_VERIFCODE = "login_verifcode";
    
    /**
     * config中的用户活跃超时时间
     */
    public static final String ACTIVE_TIMEOUT = "active_timeout";

    /**
     * 管理员编号
     */
    public static final Integer ADMIN_ID = 1;

    /**
     * 匿名用户编号
     */
    public static final Integer ANONYMOUS_ID = 0;

    /**
     * 匿名用户编号
     */
    public static final BigDecimal ROOT_MENU_ID = new BigDecimal(1);

    /**
     * 数值常量
     * */
    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;
    public static final Integer THREE = 3;
    public static final Integer FOUR = 4;
    public static final Integer FIVE = 5;
    public static final Integer SIX = 6;
    public static final Integer SEVEN = 7;
    public static final Integer EIGHT = 8;

    /**
     * 用户日志类型
     * */
    public static final String BUSINESS = "操作日志";
    public static final String OPERATION = "业务日志";

    
    
    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String contextPath) {
        Constants.contextPath = contextPath;
    }
}
