/*
 * File Name:SysMetaMethod.java
 * Package Name:com.hongguaninfo.hgdf.bud.entity.sys
 * Date:2018年01月03日 下午2:16:08
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.adp.entity.sys;

import java.util.Date;
import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

/**
 * 系统元方法表: sys_meta_method. <br />
 * 如果方法编码已配置，且日志级别不低于系统元方法日志级别开关，该方法执行时则记录用户日志<br />
 * entity 层 <br />
 * Date: 2018年01月03日 下午2:16:08 <br />
 *
 * @author  zhxl
 * @since V1.0.0
 */
 
public class SysMetaMethod extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * META_METHOD_ID_ : 主键ID。
     */
    private Long metaMethodId;
    /**
     * TENANT_ID_ : 租户ID。
     */
    private Integer tenantId;
    /**
     * CLASS_NAME_ : 类名(格式为：com.hginfo.xx.class)。
     */
    private String className;
    /**
     * METHOD_NAME_ : 方法名(格式为：method)。
     */
    private String methodName;
    /**
     * ARGS_NAME_ : 参数类型(格式为：java.lang.String,java.lang.Integer,...)。
     */
    private String argsName;
    /**
     * METHOD_CODE_ : 方法编码。
     * （如果方法编码已配置，且日志级别不低于系统元方法日志级别开关，该方法执行时则记录用户日志）
     */
    private String methodCode;
    /**
     * METHOD_ZH_NAME_ : 方法中文名称。
     */
    private String methodZhName;
    /**
     * METHOD_ENG_NAME_ : 方法英文名称。
     */
    private String methodEngName;
    /**
     * LOG_LEVEL_ : 日志级别。
     * （如果方法编码已配置，且日志级别不低于系统元方法日志级别开关，该方法执行时则记录用户日志）
     */
    private Integer logLevel;
    /**
     * logLevel 日志级别名称
     */
    private String logLevelStr;
    /**
     * LOG_TYPE_ 日志类型(1:业务日志;0:操作日志)
     */
    private Integer logType;
    /**
     * logType 日志类型名称
     */
    private String logTypeStr;
    /**
     * LOG_REMARK_CLASS_ 记录日志的参数类型
     * (即方法参数中该类型的参数需记录到日志内容，格式为：com.hginfo.xx.class)
     */
    private String logRemarkClass;
    /**
     * DESCR_ : 描述。
     */
    private String descr;
    /**
     * IS_VALID_ : 方法是否在用(1:在用;0:不再用 与类中方法不再匹配时不再用)。
     */
    private Integer isValid;
    /**
     * isValid 名称
     */
    private String isValidStr;
    /**
     * IS_DELETE_ : 删除标识(1:已删除;0:正常)。
     */
    private Integer isDelete;
    /**
     * IS_FINAL_ : 是否不可修改(1:不可修改;0:可修改)。
     */
    private Integer isFinal;
    /**
     * isFinal 名称
     */
    private String isFinalStr;
    /**
     * CRT_TIME_ : 数据创建时间。
     */
    private Date crtTime;
    /**
     * UPD_TIME_ : 数据最后修改时间。
     */
    private Date updTime;
    /**
     * CRT_USER_ID_ : 数据创建用户编号。
     */
    private Long crtUserId;
    /**
     * UPD_USER_ID_ : 数据修改用户编号。
     */
    private Long updUserId;
 


        
    public Long getMetaMethodId() {
        return metaMethodId;
    }

    public void setMetaMethodId(Long metaMethodId) {
        this.metaMethodId = metaMethodId;
    }

        
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

        
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

        
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

        
    public String getArgsName() {
        return argsName;
    }

    public void setArgsName(String argsName) {
        this.argsName = argsName;
    }

        
    public String getMethodCode() {
        return methodCode;
    }

    public void setMethodCode(String methodCode) {
        this.methodCode = methodCode;
    }

        
    public String getMethodZhName() {
        return methodZhName;
    }

    public void setMethodZhName(String methodZhName) {
        this.methodZhName = methodZhName;
    }

        
    public String getMethodEngName() {
        return methodEngName;
    }

    public void setMethodEngName(String methodEngName) {
        this.methodEngName = methodEngName;
    }

        
    public Integer getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(Integer logLevel) {
        this.logLevel = logLevel;
    }

        
    public String getLogLevelStr() {
        return logLevelStr;
    }

    public void setLogLevelStr(String logLevelStr) {
        this.logLevelStr = logLevelStr;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getLogTypeStr() {
        return logTypeStr;
    }

    public void setLogTypeStr(String logTypeStr) {
        this.logTypeStr = logTypeStr;
    }

    public String getLogRemarkClass() {
        return logRemarkClass;
    }

    public void setLogRemarkClass(String logRemarkClass) {
        this.logRemarkClass = logRemarkClass;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

        
    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

        
    public String getIsValidStr() {
        return isValidStr;
    }

    public void setIsValidStr(String isValidStr) {
        this.isValidStr = isValidStr;
    }

    public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Integer isFinal) {
        this.isFinal = isFinal;
    }

        
    public String getIsFinalStr() {
        return isFinalStr;
    }

    public void setIsFinalStr(String isFinalStr) {
        this.isFinalStr = isFinalStr;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

        
    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

        
    public Long getCrtUserId() {
        return crtUserId;
    }

    public void setCrtUserId(Long crtUserId) {
        this.crtUserId = crtUserId;
    }

        
    public Long getUpdUserId() {
        return updUserId;
    }

    public void setUpdUserId(Long updUserId) {
        this.updUserId = updUserId;
    }

    

}
