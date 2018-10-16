package com.hongguaninfo.hgdf.wadp.entity.sys;

import javax.validation.constraints.NotNull;

import com.hongguaninfo.hgdf.wadp.core.base.BaseEntity;

/**
 * 组织架构(部门)表:SYS_DEPARTMENT entity 层
 * 
 * @author:
 */

public class SysDepartment extends BaseEntity {
    // DEPART_ID_ : 部门编号
    @NotNull
    private java.math.BigDecimal departId;

    // DEPART_NAME_ : 部门名称
    @NotNull
    private String departName;

    // DEPART_FULLNAME_ : 部门全名
    @NotNull
    private String departFullname;

    // DEPART_CODE_ : 部门编码(默认为编号)
    private String departCode;

    // ENGNAME_ : 部门英文名称
    private String engname;

    // FID_ : 父部门
    private java.math.BigDecimal fid;

    // IS_DELETE_ : 删除标识(1:已删除;0:正常)
    private Integer isDelete;

    // IS_FINAL_ : 是否不可修改(1:不可修改;0:可修改)
    private Integer isFinal;

    // CRT_TIME_ : 数据创建时间
    private java.util.Date crtTime;

    // UPD_TIME_ : 数据最后修改时间
    private java.util.Date updTime;

    // CRT_USERID_ : 数据创建用户编号
    private java.math.BigDecimal crtUserid;

    // UPD_USERID_ : 数据修改用户编号
    private java.math.BigDecimal updUserid;

    private java.math.BigDecimal _parentId;// 父节点id

    private String iconCls;// 图标样式

    private String isFinalStr;

    public String getIsFinalStr() {
        return isFinalStr;
    }

    public void setIsFinalStr(String isFinalStr) {
        this.isFinalStr = isFinalStr;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public java.math.BigDecimal get_parentId() {
        return _parentId;
    }

    public void set_parentId(java.math.BigDecimal _parentId) {
        this._parentId = _parentId;
    }

    /**
     * DEPART_ID_
     */
    public java.math.BigDecimal getDepartId() {
        return departId;
    }

    /**
     * DEPART_ID_
     */
    public void setDepartId(java.math.BigDecimal departId) {
        this.departId = departId;
    }

    /**
     * DEPART_NAME_
     */
    public String getDepartName() {
        return departName;
    }

    /**
     * DEPART_NAME_
     */
    public void setDepartName(String departName) {
        this.departName = departName;
    }

    /**
     * DEPART_FULLNAME_
     */
    public String getDepartFullname() {
        return departFullname;
    }

    /**
     * DEPART_FULLNAME_
     */
    public void setDepartFullname(String departFullname) {
        this.departFullname = departFullname;
    }

    /**
     * DEPART_CODE_
     */
    public String getDepartCode() {
        return departCode;
    }

    /**
     * DEPART_CODE_
     */
    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    /**
     * ENGNAME_
     */
    public String getEngname() {
        return engname;
    }

    /**
     * ENGNAME_
     */
    public void setEngname(String engname) {
        this.engname = engname;
    }

    /**
     * FID_
     */
    public java.math.BigDecimal getFid() {
        return fid;
    }

    /**
     * FID_
     */
    public void setFid(java.math.BigDecimal fid) {
        this.fid = fid;
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

    public Integer getIsFinal() {
        return isFinal;
    }

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

}
