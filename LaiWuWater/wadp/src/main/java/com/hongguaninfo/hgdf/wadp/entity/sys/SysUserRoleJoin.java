package com.hongguaninfo.hgdf.wadp.entity.sys;

import com.hongguaninfo.hgdf.wadp.core.base.BaseEntity;

/**
 * 系统用户角色关系表:SYS_USER_ROLE_JOIN entity 层
 * 
 * @author:
 */

public class SysUserRoleJoin extends BaseEntity {
    // USER_ID_ : 用户ID
    private java.math.BigDecimal userId;

    // ROLE_ID_ : 角色ID
    private java.math.BigDecimal roleId;
    
    private String roleName;

    // IS_FINAL_ : 是否不可修改(1:不可修改;0:可修改)
    private Integer isFinal;

    // CRT_TIME_ : 数据创建时间
    private java.util.Date crtTime;

    // CRT_USERID_ : 数据创建用户编号
    private java.math.BigDecimal crtUserid;

    /**
     * USER_ID_
     */
    public java.math.BigDecimal getUserId() {
        return userId;
    }

    /**
     * USER_ID_
     */
    public void setUserId(java.math.BigDecimal userId) {
        this.userId = userId;
    }

    /**
     * ROLE_ID_
     */
    public java.math.BigDecimal getRoleId() {
        return roleId;
    }

    /**
     * ROLE_ID_
     */
    public void setRoleId(java.math.BigDecimal roleId) {
        this.roleId = roleId;
    }

    
    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
     * IS_FINAL_
     */
    public Integer getIsFinal() {
        return isFinal;
    }

    /**
     * IS_FINAL_
     */
    public void setIsFinal(Integer isFinal) {
        this.isFinal = isFinal;
    }

    /**
     * CRT_TIME_
     */
    public java.util.Date getCrtTime() {
        return crtTime;
    }

    /**
     * CRT_TIME_
     */
    public void setCrtTime(java.util.Date crtTime) {
        this.crtTime = crtTime;
    }

    /**
     * CRT_USERID_
     */
    public java.math.BigDecimal getCrtUserid() {
        return crtUserid;
    }

    /**
     * CRT_USERID_
     */
    public void setCrtUserid(java.math.BigDecimal crtUserid) {
        this.crtUserid = crtUserid;
    }

}
