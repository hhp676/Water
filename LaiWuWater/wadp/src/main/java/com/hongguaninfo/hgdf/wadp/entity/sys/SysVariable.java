package com.hongguaninfo.hgdf.wadp.entity.sys;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.hongguaninfo.hgdf.wadp.core.base.BaseEntity;

/**
 * 系统变量表:SYS_VARIABLE entity 层
 * 
 * @author:
 */

public class SysVariable extends BaseEntity {
    // VAR_ID_ : 参数ID
    private java.math.BigDecimal varId;

    // VAR_TYPE_ : 类型
    private String varType;

    // VAR_NAME_ : 名称
    @NotEmpty
    @Size(min = 5, max = 10)
    private String varName;

    // VAR_VALUE_ : 值
    @NotEmpty
    private String varValue;
    
    private String varCode;

    // DESCR_ : 描述
    private String descr;

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

    /**
     * VAR_ID_
     */
    public java.math.BigDecimal getVarId() {
        return varId;
    }

    /**
     * VAR_ID_
     */
    public void setVarId(java.math.BigDecimal varId) {
        this.varId = varId;
    }

    /**
     * VAR_TYPE_
     */
    public String getVarType() {
        return varType;
    }

    public String getVarCode() {
		return varCode;
	}

	public void setVarCode(String varCode) {
		this.varCode = varCode;
	}

	/**
     * VAR_TYPE_
     */
    public void setVarType(String varType) {
        this.varType = varType;
    }

    /**
     * VAR_NAME_
     */
    public String getVarName() {
        return varName;
    }

    /**
     * VAR_NAME_
     */
    public void setVarName(String varName) {
        this.varName = varName;
    }

    /**
     * VAR_VALUE_
     */
    public String getVarValue() {
        return varValue;
    }

    /**
     * VAR_VALUE_
     */
    public void setVarValue(String varValue) {
        this.varValue = varValue;
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

    public String getIsFinalStr() {
        return isFinalStr;
    }

    public void setIsFinalStr(String isFinalStr) {
        this.isFinalStr = isFinalStr;
    }

}
