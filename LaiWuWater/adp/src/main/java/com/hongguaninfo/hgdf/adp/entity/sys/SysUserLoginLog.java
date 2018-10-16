/*
 * File Name:SysUserLoginLog.java
 * Package Name:com.hongguaninfo.hgdf.bud.entity.sys
 * Date:2018年01月05日 下午3:56:34
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.adp.entity.sys;

import java.util.Date;
import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

/**
 * 用户登录日志表: sys_user_login_log. <br />
 * entity 层 <br />
 * Date: 2018年01月05日 下午3:56:34 <br />
 *
 * @author  menghaixiao
 * @since V1.0.0
 */
 
public class SysUserLoginLog extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * LOGIN_LOG_ID_ : 登录日志ID。
     */
    private Integer loginLogId;
    /**
     * ACCOUNT_ID_ : 账号ID。
     */
    private Integer accountId;
    /**
     * LOGIN_TYPE_ : 登录类型(general;token;rest;sso;cas)。
     */
    private String loginType;
    /**
     * OPER_CODE_ : 操作编码。
     */
    private String operCode;
    /**
     * OPER_NAME_ : 中文操作名称。
     */
    private String operName;
    /**
     * ENG_NAME_ : 英文操作名称。
     */
    private String engName;
    /**
     * REQ_URI_ : 请求URI。
     */
    private String reqUri;
    /**
     * OPER_IP_ : 调用方IP。
     */
    private String operIp;
    /**
     * OPER_RES_ : 操作结果(0:失败;1:成功)。
     */
    private Integer operRes;
    /**
     * EXCEPTION_NAME_ : 异常名。
     */
    private String exceptionName;
    /**
     * IS_ACCUMULATE_LOGIN_TIMES_ : 是否累计登陆次数(用于账号锁定和IP锁定查询最近n次累计登陆次数的登陆记录)。
     */
    private Integer isAccumulateLoginTimes;
    /**
     * CONTENT_ : 日志内容(可放置json格式请求参数)。
     */
    private String content;
    /**
     * REQ_TYPE_ : 请求类型(0:非ajax;1:ajax)。
     */
    private Integer reqType;
    /**
     * REQ_METHOD_ : 请求方式(如:GET)。
     */
    private String reqMethod;
    /**
     * BROWSER_TYPE_ : 浏览器类型。
     */
    private String browserType;
    /**
     * REQ_USER_AGENT_ : 请求Request Headers的User-Agent。
     */
    private String reqUserAgent;
    /**
     * LOGIN_LOCATION_ : 登录地点。
     */
    private String loginLocation;
    /**
     * IS_FINAL_ : 是否不可修改。
     */
    private Integer isFinal;
    /**
     * CRT_TIME_ : 创建时间。
     */
    private Date crtTime;
    /**
     * CRT_USER_ID_ : 数据创建用户编号。
     */
    private Integer crtUserId;
    
    private Integer limitCount;
    
    private String crtTimeStr;
    private String userName;
    private String operResStr;
 


        
    public Integer getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(Integer loginLogId) {
        this.loginLogId = loginLogId;
    }

        
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

        
    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

        
    public String getOperCode() {
        return operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }

        
    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

        
    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

        
    public String getReqUri() {
        return reqUri;
    }

    public void setReqUri(String reqUri) {
        this.reqUri = reqUri;
    }

        
    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

        
    public Integer getOperRes() {
        return operRes;
    }

    public void setOperRes(Integer operRes) {
        this.operRes = operRes;
    }

        
    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

        
    public Integer getIsAccumulateLoginTimes() {
        return isAccumulateLoginTimes;
    }

    public void setIsAccumulateLoginTimes(Integer isAccumulateLoginTimes) {
        this.isAccumulateLoginTimes = isAccumulateLoginTimes;
    }

        
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

        
    public Integer getReqType() {
        return reqType;
    }

    public void setReqType(Integer reqType) {
        this.reqType = reqType;
    }

        
    public String getReqMethod() {
        return reqMethod;
    }

    public void setReqMethod(String reqMethod) {
        this.reqMethod = reqMethod;
    }

        
    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }

        
    public String getReqUserAgent() {
        return reqUserAgent;
    }

    public void setReqUserAgent(String reqUserAgent) {
        this.reqUserAgent = reqUserAgent;
    }

        
    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

        
    public Integer getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Integer isFinal) {
        this.isFinal = isFinal;
    }

        
    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

        
    public Integer getCrtUserId() {
        return crtUserId;
    }

    public void setCrtUserId(Integer crtUserId) {
        this.crtUserId = crtUserId;
    }

	public Integer getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(Integer limitCount) {
		this.limitCount = limitCount;
	}

	public String getCrtTimeStr() {
		return crtTimeStr;
	}

	public void setCrtTimeStr(String crtTimeStr) {
		this.crtTimeStr = crtTimeStr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperResStr() {
		return operResStr;
	}

	public void setOperResStr(String operResStr) {
		this.operResStr = operResStr;
	}

    

}
