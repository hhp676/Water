package com.hongguaninfo.hgdf.wadp.entity.sys;

import com.hongguaninfo.hgdf.wadp.core.base.BaseEntity;

/**
 * 系统设置表:SYS_CONFIG entity 层
 * 
 * @author:
 */
public class SysConfig extends BaseEntity {
    // CONFIG_KEY_ : 设置KEY
    private String configKey;

    // CONFIG_VALUE_ : 设置值
    private String configValue;

    // 设置默认值
    private String defaultValue;

    // CONFIG_DESC_ : 设置描述
    private String configDesc;

    // CONFIG_TYPE_ : 设置类型
    private String configType;

    // UPD_TIME_ : 数据最后修改时间
    private java.util.Date updTime;

    // UPD_USERID_ : 数据修改用户编号
    private java.math.BigDecimal updUserid;

    private Integer isFinal;

    private Integer isVisible;

    /**
     * CONFIG_KEY_
     */
    public String getConfigKey() {
        return configKey;
    }

    /**
     * CONFIG_KEY_
     */
    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    /**
     * CONFIG_VALUE_
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * CONFIG_VALUE_
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    /**
     * CONFIG_DESC_
     */
    public String getConfigDesc() {
        return configDesc;
    }

    /**
     * CONFIG_DESC_
     */
    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc;
    }

    /**
     * CONFIG_TYPE_
     */
    public String getConfigType() {
        return configType;
    }

    /**
     * CONFIG_TYPE_
     */
    public void setConfigType(String configType) {
        this.configType = configType;
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
     * UPD_USERID_
     */
    public java.math.BigDecimal getUpdUserid() {
        return updUserid;
    }

    /**
     * UPD_USERID_
     */
    public void setUpdUserid(java.math.BigDecimal updUserid) {
        this.updUserid = updUserid;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Integer isFinal) {
        this.isFinal = isFinal;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

}
