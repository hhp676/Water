/*
 * File Name:WaCommFacilitiesWaterData.java
 * Package Name:com.hongguaninfo.hgdf.bud.entity.wa
 * Date:2018年09月14日 上午11:31:48
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.wa.entity.totalinfo;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

import java.util.Date;

/**
 * : wa_comm_facilities_water_data. <br />
 * entity 层 <br />
 * Date: 2018年09月14日 上午11:31:48 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
 
public class WaCommFacilitiesWaterData extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * COMM_FACTILITIES_ID_ : 。
     */
    private Integer commFactilitiesId;
    /**
     * COMPANY_ID_ : 单位代码。
     */
    private Integer companyId;
    /**
     * MONTH_DATE_ : 月份时间。
     */
    private String monthDate;
    /**
     * NEW_WATER_AMOUNT_ : 新水量 。
     */
    private String newWaterAmount;
    /**
     * COLD_NEW_WATER_AMOUNT_ : 冷却水新水量。
     */
    private String coldNewWaterAmount;
    /**
     * MANAGER_NEW_WATER_AMOUNT_ : 经营性新水量 。
     */
    private String managerNewWaterAmount;
    /**
     * REUSE_WATER_AMOUNT_ : 重复利用水量。
     */
    private String reuseWaterAmount;
    /**
     * COLD_LOOP_WATER_AMOUNT_ : 冷却水循环量。
     */
    private String coldLoopWaterAmount;
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
 


        
    public Integer getCommFactilitiesId() {
        return commFactilitiesId;
    }

    public void setCommFactilitiesId(Integer commFactilitiesId) {
        this.commFactilitiesId = commFactilitiesId;
    }

        
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

        
    public String getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }

        
    public String getNewWaterAmount() {
        return newWaterAmount;
    }

    public void setNewWaterAmount(String newWaterAmount) {
        this.newWaterAmount = newWaterAmount;
    }

        
    public String getColdNewWaterAmount() {
        return coldNewWaterAmount;
    }

    public void setColdNewWaterAmount(String coldNewWaterAmount) {
        this.coldNewWaterAmount = coldNewWaterAmount;
    }

        
    public String getManagerNewWaterAmount() {
        return managerNewWaterAmount;
    }

    public void setManagerNewWaterAmount(String managerNewWaterAmount) {
        this.managerNewWaterAmount = managerNewWaterAmount;
    }

        
    public String getReuseWaterAmount() {
        return reuseWaterAmount;
    }

    public void setReuseWaterAmount(String reuseWaterAmount) {
        this.reuseWaterAmount = reuseWaterAmount;
    }

        
    public String getColdLoopWaterAmount() {
        return coldLoopWaterAmount;
    }

    public void setColdLoopWaterAmount(String coldLoopWaterAmount) {
        this.coldLoopWaterAmount = coldLoopWaterAmount;
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
