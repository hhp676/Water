/*
 * File Name:WaPlanYearWaterData.java
 * Package Name:com.hongguaninfo.hgdf.bud.entity.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.wa.entity;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

import java.util.Date;

/**
 * : wa_plan_year_water_data. <br />
 * entity 层 <br />
 * Date: 2018年09月11日 下午8:49:44 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
 
public class WaPlanYearWaterData extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * PLAN_WATER_ID_ : 。
     */
    private Integer planWaterId;
    /**
     * COMPANY_CODE_ : 单位代码。
     */
    private String companyId;
    /**
     * PLAN_YEAR_ : 计划的年份时间。
     */
    private String planYear;
    /**
     * PLAN_YEAR_AVG_WATER_ : 计划的年份用水量。
     */
    private String planYearAvgWater;
    /**
     * PLAN_YEAR_EDIT_WATER_ : 单位上报用水量。
     */
    private String planYearEditWater;
    /**
     * ACT_YEAR_WATER_ : 当前年份用水总量。
     */
    private String actYearWater;
    /**
     * IS_OVERPROOF_ : 是否已超标。
     */
    private Integer isOverproof;
    /**
     * IS_DELTE_ : 是否有效。
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

    private String companyName;

    private String companyCode;

    private String isImport;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getIsImport() {
        return isImport;
    }

    public void setIsImport(String isImport) {
        this.isImport = isImport;
    }

    private WaCompanyInfo waCompanyInfo;

    public WaCompanyInfo getWaCompanyInfo() {
        return waCompanyInfo;
    }

    public void setWaCompanyInfo(WaCompanyInfo waCompanyInfo) {
        this.waCompanyInfo = waCompanyInfo;
    }

    public Integer getPlanWaterId() {
        return planWaterId;
    }

    public void setPlanWaterId(Integer planWaterId) {
        this.planWaterId = planWaterId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getPlanYear() {
        return planYear;
    }

    public void setPlanYear(String planYear) {
        this.planYear = planYear;
    }

        
    public String getPlanYearAvgWater() {
        return planYearAvgWater;
    }

    public void setPlanYearAvgWater(String planYearAvgWater) {
        this.planYearAvgWater = planYearAvgWater;
    }

        
    public String getPlanYearEditWater() {
        return planYearEditWater;
    }

    public void setPlanYearEditWater(String planYearEditWater) {
        this.planYearEditWater = planYearEditWater;
    }

        
    public String getActYearWater() {
        return actYearWater;
    }

    public void setActYearWater(String actYearWater) {
        this.actYearWater = actYearWater;
    }

        
    public Integer getIsOverproof() {
        return isOverproof;
    }

    public void setIsOverproof(Integer isOverproof) {
        this.isOverproof = isOverproof;
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
