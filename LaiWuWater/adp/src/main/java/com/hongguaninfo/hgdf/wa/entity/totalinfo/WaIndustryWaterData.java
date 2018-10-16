/*
 * File Name:WaIndustryWaterData.java
 * Package Name:com.hongguaninfo.hgdf.bud.entity.wa
 * Date:2018年09月14日 上午11:31:48
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.wa.entity.totalinfo;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

import java.util.Date;

/**
 * : wa_industry_water_data. <br />
 * entity 层 <br />
 * Date: 2018年09月14日 上午11:31:48 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
 
public class WaIndustryWaterData extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * INDUSTRY_WATER_ID_ : 。
     */
    private Integer industryWaterId;
    /**
     * COMPANY_ID_ : 。
     */
    private Integer companyId;
    /**
     * MONTH_DATE_ : 。
     */
    private String monthDate;
    /**
     * NEW_WATER_TOTAL_COUNT_ : 工业新水量合计。
     */
    private String newWaterTotalCount;
    /**
     * INTER_COLD_WATER_ : 间冷水。
     */
    private String interColdWater;
    /**
     * FRESH_WATER_ : 工艺水。
     */
    private String freshWater;
    /**
     * BOILER_WATER_ : 锅炉水。
     */
    private String boilerWater;
    /**
     * BOILER_GAS_AMOUNT_ : 锅炉发气量 。
     */
    private String boilerGasAmount;
    /**
     * FACTORY_LIVE_WATER_ : 厂内生活水 。
     */
    private String factoryLiveWater;
    /**
     * REUSE_TOTAL_AMOUNT_ : 重复利用合计。
     */
    private String reuseTotalAmount;
    /**
     * INTER_COLD_WATER_LOOP_AMOUNT_ : 间冷水循环量。
     */
    private String interColdWaterLoopAmount;
    /**
     * FRESH_WATER_BACK_AMOUNT_ : 工艺水回用量 。
     */
    private String freshWaterBackAmount;
    /**
     * BOILER_WATER_BACK_AMOUNT_ : 锅炉回用水量。
     */
    private String boilerWaterBackAmount;
    /**
     * STEAM_CONDEN_BACK_AMOUNT_ : 蒸汽冷凝回用量 。
     */
    private String steamCondenBackAmount;
    /**
     * LIVE_WATER_REPET_AMOUNT_ : 生活水重复量。
     */
    private String liveWaterRepetAmount;
    /**
     * IS_DELETE_ : 。
     */
    private Integer isDelete;
    /**
     * CRT_TIME_ : 。
     */
    private Date crtTime;
    /**
     * UPT_TIME_ : 。
     */
    private Date uptTime;
 


        
    public Integer getIndustryWaterId() {
        return industryWaterId;
    }

    public void setIndustryWaterId(Integer industryWaterId) {
        this.industryWaterId = industryWaterId;
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

        
    public String getNewWaterTotalCount() {
        return newWaterTotalCount;
    }

    public void setNewWaterTotalCount(String newWaterTotalCount) {
        this.newWaterTotalCount = newWaterTotalCount;
    }

        
    public String getInterColdWater() {
        return interColdWater;
    }

    public void setInterColdWater(String interColdWater) {
        this.interColdWater = interColdWater;
    }

        
    public String getFreshWater() {
        return freshWater;
    }

    public void setFreshWater(String freshWater) {
        this.freshWater = freshWater;
    }

        
    public String getBoilerWater() {
        return boilerWater;
    }

    public void setBoilerWater(String boilerWater) {
        this.boilerWater = boilerWater;
    }

        
    public String getBoilerGasAmount() {
        return boilerGasAmount;
    }

    public void setBoilerGasAmount(String boilerGasAmount) {
        this.boilerGasAmount = boilerGasAmount;
    }

        
    public String getFactoryLiveWater() {
        return factoryLiveWater;
    }

    public void setFactoryLiveWater(String factoryLiveWater) {
        this.factoryLiveWater = factoryLiveWater;
    }

        
    public String getReuseTotalAmount() {
        return reuseTotalAmount;
    }

    public void setReuseTotalAmount(String reuseTotalAmount) {
        this.reuseTotalAmount = reuseTotalAmount;
    }

        
    public String getInterColdWaterLoopAmount() {
        return interColdWaterLoopAmount;
    }

    public void setInterColdWaterLoopAmount(String interColdWaterLoopAmount) {
        this.interColdWaterLoopAmount = interColdWaterLoopAmount;
    }

        
    public String getFreshWaterBackAmount() {
        return freshWaterBackAmount;
    }

    public void setFreshWaterBackAmount(String freshWaterBackAmount) {
        this.freshWaterBackAmount = freshWaterBackAmount;
    }

        
    public String getBoilerWaterBackAmount() {
        return boilerWaterBackAmount;
    }

    public void setBoilerWaterBackAmount(String boilerWaterBackAmount) {
        this.boilerWaterBackAmount = boilerWaterBackAmount;
    }

        
    public String getSteamCondenBackAmount() {
        return steamCondenBackAmount;
    }

    public void setSteamCondenBackAmount(String steamCondenBackAmount) {
        this.steamCondenBackAmount = steamCondenBackAmount;
    }

        
    public String getLiveWaterRepetAmount() {
        return liveWaterRepetAmount;
    }

    public void setLiveWaterRepetAmount(String liveWaterRepetAmount) {
        this.liveWaterRepetAmount = liveWaterRepetAmount;
    }

        
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

        
    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

        
    public Date getUptTime() {
        return uptTime;
    }

    public void setUptTime(Date uptTime) {
        this.uptTime = uptTime;
    }

    

}
