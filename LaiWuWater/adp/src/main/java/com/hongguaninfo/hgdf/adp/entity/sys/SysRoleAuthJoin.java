package com.hongguaninfo.hgdf.adp.entity.sys;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

/**
 * 系统角色权限关系表:SYS_ROLE_AUTH_JOIN entity 层
 * 
 * @author:
 */

public class SysRoleAuthJoin extends BaseEntity {
    // ROLE_ID_ : 角色编号
    private java.math.BigDecimal roleId;

    // AUTH_ID_ : 权限编号
    private java.math.BigDecimal authId;

    // IS_FINAL_ : 是否不可修改(1:不可修改;0:可修改)
    private Integer isFinal;

    // CRT_TIME_ : 数据创建时间
    private java.util.Date crtTime;

    // CRT_USERID_ : 数据创建用户编号
    private java.math.BigDecimal crtUserid;

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

    /**
     * AUTH_ID_
     */
    public java.math.BigDecimal getAuthId() {
        return authId;
    }

    /**
     * AUTH_ID_
     */
    public void setAuthId(java.math.BigDecimal authId) {
        this.authId = authId;
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
