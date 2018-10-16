/*
 * File Name:WaMonthWaterData.java
 * Package Name:com.hongguaninfo.hgdf.bud.entity.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.wa.entity;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

import java.util.Date;

/**
 * : wa_month_water_data. <br />
 * entity 层 <br />
 * Date: 2018年09月11日 下午8:49:44 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
 
public class WaMonthWaterData extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * MONTH_WATER_ID_ : 。
     */
    private Integer monthWaterId;
    /**
     * COMPANY_CODE_ : 单位代码。
     */
    private String companyId;

    private String companyName;
    /**
     * MONTH_DATE_ : 月份时间。
     */
    private String monthDate;
    /**
     * PLAN_MONTH_WATER_ : 计划月份用水量。
     */
    private String planMonthWater;
    /**
     * ACT_MONTH_WATER_ : 实际月份用水量。
     */
    private String actMonthWater;
    /**
     * IS_OVERROOF_ : 是否超标当月计划。
     */
    private String isOverroof;
    /**
     * IS_DELTE_ : 。
     */
    private Integer isDelte;
    /**
     * CRT_TIME_ : 。
     */
    private Date crtTime;
    /**
     * UPD_TIME_ : 。
     */
    private Date updTime;

    private String isImport;

    private String beyondAmount;

    /**
     * 收费标准
     */
    private String feeStandard;

    private String companyCode;
    /**
     * 查询年份
     */
    private String waYear;

    /**
     * 查询月份
     */
    private String waMonth;

    public String getBeyondAmount() {
        return beyondAmount;
    }

    public void setBeyondAmount(String beyondAmount) {
        this.beyondAmount = beyondAmount;
    }

    public String getFeeStandard() {
        return feeStandard;
    }

    public void setFeeStandard(String feeStandard) {
        this.feeStandard = feeStandard;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getWaYear() {
        return waYear;
    }

    public void setWaYear(String waYear) {
        this.waYear = waYear;
    }

    public String getWaMonth() {
        return waMonth;
    }

    public void setWaMonth(String waMonth) {
        this.waMonth = waMonth;
    }

    public String getIsImport() {
        return isImport;
    }

    public void setIsImport(String isImport) {
        this.isImport = isImport;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private WaCompanyInfo waCompanyInfo;

    public WaCompanyInfo getWaCompanyInfo() {
        return waCompanyInfo;
    }

    public void setWaCompanyInfo(WaCompanyInfo waCompanyInfo) {
        this.waCompanyInfo = waCompanyInfo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getMonthWaterId() {
        return monthWaterId;
    }

    public void setMonthWaterId(Integer monthWaterId) {
        this.monthWaterId = monthWaterId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }

    public String getPlanMonthWater() {
        return planMonthWater;
    }

    public void setPlanMonthWater(String planMonthWater) {
        this.planMonthWater = planMonthWater;
    }

        
    public String getActMonthWater() {
        return actMonthWater;
    }

    public void setActMonthWater(String actMonthWater) {
        this.actMonthWater = actMonthWater;
    }

    public String getIsOverroof() {
        return isOverroof;
    }

    public void setIsOverroof(String isOverroof) {
        this.isOverroof = isOverroof;
    }

    public Integer getIsDelte() {
        return isDelte;
    }

    public void setIsDelte(Integer isDelte) {
        this.isDelte = isDelte;
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
