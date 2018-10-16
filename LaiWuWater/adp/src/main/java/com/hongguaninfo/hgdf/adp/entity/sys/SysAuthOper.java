package com.hongguaninfo.hgdf.adp.entity.sys;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

/**
 * 系统操作类型表:SYS_AUTH_OPER entity 层
 * 
 * @author:
 */

public class SysAuthOper extends BaseEntity {
    // OPER_ID_ : 操作编号
    private java.math.BigDecimal operId;

    // OPER_CODE_ : 编码
    private String operCode;

    // OPER_NAME_ : 操作名称
    private String operName;

    // OPER_ENNAME : 英文名
    private String operEnname;

    // OPER_ICON_ : 图标
    private String operIcon;

    // ORDER_ID_ : 排序ID
    private Integer orderId;

    // DESCR_ :
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
     * OPER_ID_
     */
    public java.math.BigDecimal getOperId() {
        return operId;
    }

    /**
     * OPER_ID_
     */
    public void setOperId(java.math.BigDecimal operId) {
        this.operId = operId;
    }

    /**
     * OPER_CODE_
     */
    public String getOperCode() {
        return operCode;
    }

    /**
     * OPER_CODE_
     */
    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }

    /**
     * OPER_NAME_
     */
    public String getOperName() {
        return operName;
    }

    public String getIsFinalStr() {
        return isFinalStr;
    }

    public void setIsFinalStr(String isFinalStr) {
        this.isFinalStr = isFinalStr;
    }

    /**
     * OPER_NAME_
     */
    public void setOperName(String operName) {
        this.operName = operName;
    }

    /**
     * OPER_ENNAME
     */
    public String getOperEnname() {
        return operEnname;
    }

    /**
     * OPER_ENNAME
     */
    public void setOperEnname(String operEnname) {
        this.operEnname = operEnname;
    }

    /**
     * OPER_ICON_
     */
    public String getOperIcon() {
        return operIcon;
    }

    /**
     * OPER_ICON_
     */
    public void setOperIcon(String operIcon) {
        this.operIcon = operIcon;
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

}
