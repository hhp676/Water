package com.hongguaninfo.hgdf.wadp.entity.sys;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.hongguaninfo.hgdf.wadp.core.base.BaseEntity;

/**
 * : SYS_DATADIC_ITEM entity 层
 * 
 * @author zhengyangjun
 */

public class SysDatadicItem extends BaseEntity {
    // ITEM_ID_ : 用户ID
    @NotNull
    private Integer itemId;

    // GROUP_ID_ : 组ID
    private Integer groupId;

    // ITEM_CODE_ : 项编码
    @NotEmpty
    private String itemCode;

    // ITEM_NAME_ : 项名
    private String itemName;

    // ITEM_VALUE_ : 项值
    private String itemValue;

    // ITEM_DESC_ : 描述
    private String itemDesc;

    // ORDER_ID_ : 排序ID
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

    private String groupCode;

    private String groupName;

    /**
     * ITEM_ID_
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * ITEM_ID_
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

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
     * ITEM_CODE_
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * ITEM_CODE_
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * ITEM_NAME_
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * ITEM_NAME_
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * ITEM_VALUE_
     */
    public String getItemValue() {
        return itemValue;
    }

    /**
     * ITEM_VALUE_
     */
    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    /**
     * ITEM_DESC_
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * ITEM_DESC_
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
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

    public String getIsFinalStr() {
        return isFinalStr;
    }

    public void setIsFinalStr(String isFinalStr) {
        this.isFinalStr = isFinalStr;
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

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
