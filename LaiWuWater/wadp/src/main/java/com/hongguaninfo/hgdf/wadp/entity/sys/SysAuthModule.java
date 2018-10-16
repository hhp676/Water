package com.hongguaninfo.hgdf.wadp.entity.sys;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.hongguaninfo.hgdf.wadp.core.base.BaseEntity;

/**
 * 系统模块权限表:SYS_AUTH_MODULE entity 层
 * 
 * @author:
 */

public class SysAuthModule extends BaseEntity {
    // MODULE_ID_ : 模块编号
    private java.math.BigDecimal moduleId;

    // AUTH_ID_ : 权限编号
    private java.math.BigDecimal authId;

    // MODULE_TYPE_ : 模块类型(数据字典)
    private String moduleType;

    // MODULE_CODE_ : 编码
    private String moduleCode;

    // MODULE_NAME_ : 权限名称
    @NotEmpty
    private String moduleName;

    // MODULE_ENNAME : 英文名
    private String moduleEnname;

    // MODULE_URI_ : URI
    private String moduleUri;

    // MODULE_ICON_ : 图标
    private String moduleIcon;

    // FID_ : 父ID
    @NotNull
    private java.math.BigDecimal fid;

    // LEVEL_ID_ : 等级ID
    @NotNull
    private Integer levelId;

    // ORDER_ID_ : 排序ID
    @NotNull
    private Integer orderId;

    // IS_VISIBLE_ : 是否可见
    private Integer isVisible;

    // DESCR_ : 描述
    private String descr;

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

    private List<SysAuthModule> childs = new ArrayList<SysAuthModule>();

    /**
     * MODULE_ID_
     */
    public java.math.BigDecimal getModuleId() {
        return moduleId;
    }

    /**
     * MODULE_ID_
     */
    public void setModuleId(java.math.BigDecimal moduleId) {
        this.moduleId = moduleId;
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
     * MODULE_TYPE_
     */
    public String getModuleType() {
        return moduleType;
    }

    /**
     * MODULE_TYPE_
     */
    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    /**
     * MODULE_CODE_
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * MODULE_CODE_
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * MODULE_NAME_
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * MODULE_NAME_
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * MODULE_ENNAME
     */
    public String getModuleEnname() {
        return moduleEnname;
    }

    /**
     * MODULE_ENNAME
     */
    public void setModuleEnname(String moduleEnname) {
        this.moduleEnname = moduleEnname;
    }

    /**
     * MODULE_URI_
     */
    public String getModuleUri() {
        return moduleUri;
    }

    /**
     * MODULE_URI_
     */
    public void setModuleUri(String moduleUri) {
        this.moduleUri = moduleUri;
    }

    /**
     * MODULE_ICON_
     */
    public String getModuleIcon() {
        return moduleIcon;
    }

    /**
     * MODULE_ICON_
     */
    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon;
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
     * LEVEL_ID_
     */
    public Integer getLevelId() {
        return levelId;
    }

    /**
     * LEVEL_ID_
     */
    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    /**
     * ORDER_ID_
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * ORDER_ID_
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * IS_VISIBLE_
     */
    public Integer getIsVisible() {
        return isVisible;
    }

    /**
     * IS_VISIBLE_
     */
    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
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

    public List<SysAuthModule> getChilds() {
        return childs;
    }

    public void setChilds(List<SysAuthModule> childs) {
        this.childs = childs;
    }

    public void addChild(SysAuthModule child) {
        this.childs.add(child);
    }
}
