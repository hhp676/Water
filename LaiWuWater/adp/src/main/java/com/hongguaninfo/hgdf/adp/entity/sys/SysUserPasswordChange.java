/*
 * File Name:SysUserPasswordChange.java
 * Package Name:com.hongguaninfo.hgdf.bud.entity.sys
 * Date:2018年01月03日 下午2:21:54
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.adp.entity.sys;

import java.util.Date;
import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

/**
 * 用户密码变更记录表: sys_user_password_change. <br />
 * entity 层 <br />
 * Date: 2018年01月03日 下午2:21:54 <br />
 *
 * @author  menghaixiao
 * @since V1.0.0
 */
 
public class SysUserPasswordChange extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * PWD_CHANGE_ID_ : 用户密码记录ID。
     */
    private Integer pwdChangeId;
    /**
     * ACCOUNT_ID_ : 登录账号ID。
     */
    private Integer accountId;
    /**
     * OLD_LOGIN_PWD_ : 旧登录密码。
     */
    private String oldLoginPwd;
    /**
     * NEW_LOGIN_PWD_ : 新登录密码。
     */
    private String newLoginPwd;
    /**
     * CHANGE_REASON_ : 变更原因(0:普通修改;1:过期修改;2:用户管理修改;3忘记密码找回修改;4:新用户修改初始密码)。
     */
    private Integer changeReason;
    /**
     * IS_DELETE_ : 删除标识(1:已删除;0:正常)。
     */
    private Integer isDelete;
    /**
     * IS_FINAL_ : 是否不可修改(1:不可修改;0:可修改)。
     */
    private Integer isFinal;
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
    private Integer crtUserId;
    /**
     * UPD_USER_ID_ : 数据修改用户编号。
     */
    private Integer updUserId;
    
    //密码不能与最近几次相同-次数
    private Integer count;
 


        
    public Integer getPwdChangeId() {
        return pwdChangeId;
    }

    public void setPwdChangeId(Integer pwdChangeId) {
        this.pwdChangeId = pwdChangeId;
    }

        
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

        
    public String getOldLoginPwd() {
        return oldLoginPwd;
    }

    public void setOldLoginPwd(String oldLoginPwd) {
        this.oldLoginPwd = oldLoginPwd;
    }

        
    public String getNewLoginPwd() {
        return newLoginPwd;
    }

    public void setNewLoginPwd(String newLoginPwd) {
        this.newLoginPwd = newLoginPwd;
    }

        
    public Integer getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(Integer changeReason) {
        this.changeReason = changeReason;
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

        
    public Integer getCrtUserId() {
        return crtUserId;
    }

    public void setCrtUserId(Integer crtUserId) {
        this.crtUserId = crtUserId;
    }

        
    public Integer getUpdUserId() {
        return updUserId;
    }

    public void setUpdUserId(Integer updUserId) {
        this.updUserId = updUserId;
    }

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

    

}
