/*
 * File Name:WaSavewaterType.java
 * Package Name:com.hongguaninfo.hgdf.bud.entity.wa
 * Date:2018年09月11日 下午8:49:44
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.wa.entity;

import java.util.Date;
import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

/**
 * : wa_savewater_type. <br />
 * entity 层 <br />
 * Date: 2018年09月11日 下午8:49:44 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
 
public class WaSavewaterType extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID_ : 。
     */
    private Integer id;
    /**
     * CODE_ : 。
     */
    private String code;
    /**
     * CONTENT_ : 。
     */
    private String content;
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
 


        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

        
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

        
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
