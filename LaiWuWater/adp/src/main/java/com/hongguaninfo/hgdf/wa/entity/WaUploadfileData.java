/*
 * File Name:WaUploadfileData.java
 * Package Name:com.hongguaninfo.hgdf.bud.entity.wa
 * Date:2018年10月12日 下午4:17:20
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.wa.entity;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

import java.util.Date;

/**
 * : wa_uploadfile_data. <br />
 * entity 层 <br />
 * Date: 2018年10月12日 下午4:17:20 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
 
public class WaUploadfileData extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * FILEID : 。
     */
    private Integer fileid;
    /**
     * COMPANY_ID_ : 。
     */
    private String companyId;
    /**
     * FILE_NAME_ : 。
     */
    private String fileName;
    /**
     * DIRS_ : 。
     */
    private String dirs;
    /**
     * FLAG_ : 。
     */
    private String flag;
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


    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

        
    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

        
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

        
    public String getDirs() {
        return dirs;
    }

    public void setDirs(String dirs) {
        this.dirs = dirs;
    }

        
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
