/*
 * File Name:WaDormitoryWaterData.java
 * Package Name:com.hongguaninfo.hgdf.bud.entity.wa
 * Date:2018年09月14日 上午11:31:47
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.wa.entity.totalinfo;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

import java.util.Date;

/**
 * : wa_dormitory_water_data. <br />
 * entity 层 <br />
 * Date: 2018年09月14日 上午11:31:47 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
 
public class WaDormitoryWaterData extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * DORMITORY_WATER_ID_ : 。
     */
    private Integer dormitoryWaterId;
    /**
     * COMPANY_ID_ : 单位代码。
     */
    private Integer companyId;
    /**
     * MONTH_DATE_ : 月份时间。
     */
    private String monthDate;
    /**
     * PEOPLE_AMOUNT_ : 用水人口。
     */
    private String peopleAmount;
    /**
     * NEW_WATER_AMOUNT_ : 新水量。
     */
    private String newWaterAmount;
    /**
     * TEMP_NEW_WATER_AMOUNT_ : 临时性新水量 。
     */
    private String tempNewWaterAmount;
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
 


        
    public Integer getDormitoryWaterId() {
        return dormitoryWaterId;
    }

    public void setDormitoryWaterId(Integer dormitoryWaterId) {
        this.dormitoryWaterId = dormitoryWaterId;
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

        
    public String getPeopleAmount() {
        return peopleAmount;
    }

    public void setPeopleAmount(String peopleAmount) {
        this.peopleAmount = peopleAmount;
    }

        
    public String getNewWaterAmount() {
        return newWaterAmount;
    }

    public void setNewWaterAmount(String newWaterAmount) {
        this.newWaterAmount = newWaterAmount;
    }

        
    public String getTempNewWaterAmount() {
        return tempNewWaterAmount;
    }

    public void setTempNewWaterAmount(String tempNewWaterAmount) {
        this.tempNewWaterAmount = tempNewWaterAmount;
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
