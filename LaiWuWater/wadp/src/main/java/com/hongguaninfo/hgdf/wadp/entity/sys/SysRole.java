package com.hongguaninfo.hgdf.wadp.entity.sys;

import org.hibernate.validator.constraints.NotEmpty;

import com.hongguaninfo.hgdf.wadp.core.base.BaseEntity;

/**
 * 系统角色表:SYS_ROLE entity 层
 * 
 * @author:
 */

public class SysRole extends BaseEntity {
    // ROLE_ID_ : 角色编号
    private java.math.BigDecimal roleId;

    // ROLE_NAME_ : 角色名称
    @NotEmpty
    private String roleName;

    // ROLE_CODE_ : 角色编码(默认值为编号)
    @NotEmpty
    private String roleCode;

    private String roleCodeForQuery;

    // IS_DELETE_ : 删除标识(1:已删除;0:正常)
    private Integer isDelete;

    // IS_FINAL_ : 是否不可修改(1:不可修改;0:可修改)
    private Integer isFinal;

    private String isFinalStr;

    // CRT_TIME_ : 数据创建时间
    private java.util.Date crtTime;

    // UPD_TIME_ : 数据最后修改时间
    private java.util.Date updTime;

    // CRT_USERID_ : 数据创建用户编号
    private java.math.BigDecimal crtUserid;

    // UPD_USERID_ : 数据修改用户编号
    private java.math.BigDecimal updUserid;

    // DESCR_ : 描述
    private String descr;

    // 选中的权限id
    private String checkedAuthIds;

    private String authIds;

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
     * ROLE_NAME_
     */
    public String getRoleName() {
        return roleName;
    }

    public String getRoleCodeForQuery() {
        return roleCodeForQuery;
    }

    public void setRoleCodeForQuery(String roleCodeForQuery) {
        this.roleCodeForQuery = roleCodeForQuery;
    }

    /**
     * ROLE_NAME_
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * ROLE_CODE_
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * ROLE_CODE_
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * IS_DELETE_
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * IS_DELETE_
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
     * UPD_TIME_
     */
    public java.util.Date getUpdTime() {
        return updTime;
    }

    /**
     * UPD_TIME_
     */
    public void setUpdTime(java.util.Date updTime) {
        this.updTime = updTime;
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

    /**
     * UPD_USERID_
     */
    public java.math.BigDecimal getUpdUserid() {
        return updUserid;
    }

    /**
     * UPD_USERID_
     */
    public void setUpdUserid(java.math.BigDecimal updUserid) {
        this.updUserid = updUserid;
    }

    /**
     * DESCR_
     */
    public String getDescr() {
        return descr;
    }

    /**
     * DESCR_
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getIsFinalStr() {
        return isFinalStr;
    }

    public void setIsFinalStr(String isFinalStr) {
        this.isFinalStr = isFinalStr;
    }

    public String getCheckedAuthIds() {
        return checkedAuthIds;
    }

    public void setCheckedAuthIds(String checkedAuthIds) {
        this.checkedAuthIds = checkedAuthIds;
    }

    public String getAuthIds() {
        return authIds;
    }

    public void setAuthIds(String authIds) {
        this.authIds = authIds;
    }

}
