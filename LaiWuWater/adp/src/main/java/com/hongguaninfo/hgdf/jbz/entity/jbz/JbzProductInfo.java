/*
 * File Name:JbzProductInfo.java
 * Package Name:com.hongguaninfo.hgdf.adp.entity.jbz
 * Date:2018年01月11日 下午9:09:20
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.jbz.entity.jbz;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

/**
 * : jbz_product_info. <br />
 * entity 层 <br />
 * Date: 2018年01月11日 下午9:09:20 <br />
 *
 * @author  hhp
 * @since V1.0.0
 */
 
public class JbzProductInfo extends BaseEntity {

    /**
     * serialVersionUID.
     * @since V1.0.0
     */
    private static final long serialVersionUID = 1L;

    /**
     * id : 。
     */
    private Integer id;
    /**
     * name : 。
     */
    private String name;
    /**
     * bigcategory : 。
     */
    private String bigcategory;
    /**
     * smallcategory : 。
     */
    private String smallcategory;
    /**
     * num : 。
     */
    private Integer num;
    /**
     * price : 。
     */
    private java.math.BigDecimal price;
    /**
     * introduce : 。
     */
    private String introduce;
    /**
     * province : 。
     */
    private String province;
    /**
     * city : 。
     */
    private String city;
    /**
     * address : 。
     */
    private String address;
    /**
     * endtime : 。
     */
    private String endtime;
    /**
     * tel : 。
     */
    private String tel;
    /**
     * person : 。
     */
    private String person;
 


        
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

        
    public String getBigcategory() {
        return bigcategory;
    }

    public void setBigcategory(String bigcategory) {
        this.bigcategory = bigcategory;
    }

        
    public String getSmallcategory() {
        return smallcategory;
    }

    public void setSmallcategory(String smallcategory) {
        this.smallcategory = smallcategory;
    }

        
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

        
    public java.math.BigDecimal getPrice() {
        return price;
    }

    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

        
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

        
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

        
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

        
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

        
    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

        
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

        
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    

}
