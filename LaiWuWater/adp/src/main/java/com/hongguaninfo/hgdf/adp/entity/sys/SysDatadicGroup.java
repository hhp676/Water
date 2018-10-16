package com.hongguaninfo.hgdf.adp.entity.sys;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

/**
 * : SYS_DATADIC_GROUP entity 层
 * 
 * @author zhengyangjun
 */

public class SysDatadicGroup extends BaseEntity {
    // GROUP_ID_ : 组ID
    @NotNull
    private Integer groupId;

    // GROUP_CODE_ : 组编码
    @NotEmpty
    private String groupCode;

    // GROUP_NAME_ : 组名
    private String groupName;

    // GROUP_DESC_ : 组描述
    private String groupDesc;

    // ORDER_ID_ : 排序值
    private Integer orderId;

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
    private Integer crtUserid;

    // UPD_USERID_ : 数据修改用户编号
    private Integer updUserid;

    /**
     * GROUP_ID_
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * GROUP_ID_
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * GROUP_CODE_
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * GROUP_CODE_
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    /**
     * GROUP_NAME_
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * GROUP_NAME_
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * GROUP_DESC_
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * GROUP_DESC_
     */
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    /**
     * ORDER_ID_
     */
    public Integer getOrderId() {
        return orderId;
    }

    public String getIsFinalStr() {
        return isFinalStr;
    }

    public void setIsFinalStr(String isFinalStr) {
        this.isFinalStr = isFinalStr;
    }

    /**
     * ORDER_ID_
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
    public Integer getCrtUserid() {
        return crtUserid;
    }

    /**
     * CRT_USERID_
     */
    public void setCrtUserid(Integer crtUserid) {
        this.crtUserid = crtUserid;
    }

    /**
     * UPD_USERID_
     */
    public Integer getUpdUserid() {
        return updUserid;
    }

    /**
     * UPD_USERID_
     */
    public void setUpdUserid(Integer updUserid) {
        this.updUserid = updUserid;
    }

}
