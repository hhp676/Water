/*
 * File Name:SysUserStateChange.java
 * Package Name:com.hongguaninfo.hgdf.bud.entity.sys
 * Date:2018年01月04日 下午3:55:45
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.adp.entity.sys;

import java.util.Date;
import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

/**
 * 用户状态变更表: sys_user_state_change. <br />
 * entity 层 <br />
 * Date: 2018年01月04日 下午3:55:45 <br />
 *
 * @author  menghaixiao
 * @since V1.0.0
 */
 
public class SysUserStateChange extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * STATE_CHANGE_ID_ : 状态变更记录ID。
     */
    private Integer stateChangeId;
    /**
     * ACCOUNT_ID_ : 账号ID。
     */
    private Integer accountId;
    /**
     * TYPE_ : 状态类型(0:账号状态;1:[账号IP]状态)。
     */
    private Integer type;
    /**
     * IP_ : 客户端IP(LOCK_TYPE_=1时使用)。
     */
    private String ip;
    /**
     * CRT_TIME_ : 创建时间。
     */
    private Date crtTime;
    /**
     * ORIGIN_STATE_ : 原状态(0:正常;1:锁定)。
     */
    private Integer originState;
    /**
     * CURRENT_STATE_ : 现状态(0:正常;1:锁定)。
     */
    private Integer currentState;
    /**
     * IS_CURRENT_ : 是否当前状态(1:是;0:否)。
     */
    private Integer isCurrent;
    /**
     * DESCR_ : 描述。
     */
    private String descr;
    /**
     * CRT_TYPE_ : 数据创建类型(0:系统自动;1:手动配置)。
     */
    private Integer crtType;
    /**
     * IS_DELETE_ : 删除标识(1:已删除;0:正常)。
     */
    private Integer isDelete;
    /**
     * IS_FINAL_ : 是否不可修改。
     */
    private Integer isFinal;
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
    
    private Date startTime;
    
    private String userName;
    private String crtTimeStr;
    private String typeStr;
    private String originStateStr;
    private String currentStateStr;
    private String isCurrentStr;
    private String crtTypeStr;
 


        
    public Integer getStateChangeId() {
        return stateChangeId;
    }

    public void setStateChangeId(Integer stateChangeId) {
        this.stateChangeId = stateChangeId;
    }

        
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

        
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

        
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

        
    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

        
    public Integer getOriginState() {
        return originState;
    }

    public void setOriginState(Integer originState) {
        this.originState = originState;
    }

        
    public Integer getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Integer currentState) {
        this.currentState = currentState;
    }

        
    public Integer getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Integer isCurrent) {
        this.isCurrent = isCurrent;
    }

        
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

        
    public Integer getCrtType() {
        return crtType;
    }

    public void setCrtType(Integer crtType) {
        this.crtType = crtType;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCrtTimeStr() {
		return crtTimeStr;
	}

	public void setCrtTimeStr(String crtTimeStr) {
		this.crtTimeStr = crtTimeStr;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getOriginStateStr() {
		return originStateStr;
	}

	public void setOriginStateStr(String originStateStr) {
		this.originStateStr = originStateStr;
	}

	public String getCurrentStateStr() {
		return currentStateStr;
	}

	public void setCurrentStateStr(String currentStateStr) {
		this.currentStateStr = currentStateStr;
	}

	public String getIsCurrentStr() {
		return isCurrentStr;
	}

	public void setIsCurrentStr(String isCurrentStr) {
		this.isCurrentStr = isCurrentStr;
	}

	public String getCrtTypeStr() {
		return crtTypeStr;
	}

	public void setCrtTypeStr(String crtTypeStr) {
		this.crtTypeStr = crtTypeStr;
	}

    

}
