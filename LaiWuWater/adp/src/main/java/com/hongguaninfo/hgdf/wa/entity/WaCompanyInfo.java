/*
 * File Name:WaCompanyInfo.java
 * Package Name:com.hongguaninfo.hgdf.bud.entity.wa
 * Date:2018年09月10日 下午5:14:26
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.wa.entity;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

import java.util.Date;

/**
 * : wa_company_info. <br />
 * entity 层 <br />
 * Date: 2018年09月10日 下午5:14:26 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
 
public class WaCompanyInfo extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID_ : 。
     */
    private Integer companyId;
    /**
     * COMPANY_CODE_ : 节水代码。
     */
    private String companyCode;
    /**
     * COMPANY_NAME_ : 单位名称。
     */
    private String companyName;
    /**
     * CONTACT_NUM_ : 联系电话。
     */
    private String contactNum;
    /**
     * CONTACT_MAN_ : 联系人。
     */
    private String contactMan;
    /**
     * DEPARTMENT_ : 所属部门。
     */
    private String department;
    /**
     * USER_TYPE__ : 用户类别。
     */
    private String userType;
    /**
     * EMAIL_ : 邮箱。
     */
    private String email;
    /**
     * TELPHONE_ : 手机号码。
     */
    private String telphone;
    /**
     * CITY_AREA_ : 行政区域。
     */
    private String cityArea;
    /**
     * POSTCODE_ : 邮编。
     */
    private String postcode;
    /**
     * PEOPLE_COUNT_ : 单位人数。
     */
    private String peopleCount;
    /**
     * ACREAGE_ : 营业面积。
     */
    private String acreage;
    /**
     * WATER_TYPE_ : 用水性质。
     */
    private String waterType;
    /**
     * IS_IMPORT_ : 是否是重点用户。
     */
    private String isImport;
    /**
     * IS_DELETE_ : 是否有效。
     */
    private Integer isDelete;
    /**
     * DESCR_ : 描述。
     */
    private String descr;
    /**
     * CRT_TIME_ : 创建时间。
     */
    private Date crtTime;
    /**
     * UPD_TIME_ : 更新时间。
     */
    private Date updTime;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getCityArea() {
        return cityArea;
    }

    public void setCityArea(String cityArea) {
        this.cityArea = cityArea;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

        
    public String getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(String peopleCount) {
        this.peopleCount = peopleCount;
    }

        
    public String getAcreage() {
        return acreage;
    }

    public void setAcreage(String acreage) {
        this.acreage = acreage;
    }

        
    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public String getIsImport() {
        return isImport;
    }

    public void setIsImport(String isImport) {
        this.isImport = isImport;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

        
    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

        
    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

        
    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    

}
