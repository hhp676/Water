package com.hongguaninfo.hgdf.wadp.entity.sys;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import com.hongguaninfo.hgdf.wadp.core.base.BaseEntity;

/**
 * @author 查健
 * @Description 用户日志表 entity层
 * @Date 2014-5-20
 * */
public class SysUserLog extends BaseEntity {

    // 日志ID
    @NotEmpty
    private java.math.BigDecimal logId;
    // 类型(1:业务日志;0:操作日志)
    private Integer logType;
    // 用户ID
    private Integer userId;
    // 操作编码
    private String operCode;
    // 操作名称
    private String operName;
    // 操作时的IP
    private String operIP;
    // 备注
    private String remark = "";
    // 创建时间
    private Date crtTime;
    // 页面显示时间
    private String crtTimeStr;
    // 页面显示类型
    private String logTypeStr;
    // 用户登陆名称
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogTypeStr() {
        return logTypeStr;
    }

    public void setLogTypeStr(String logTypeStr) {
        this.logTypeStr = logTypeStr;
    }

    public java.math.BigDecimal getLogId() {
        return logId;
    }

    public void setLogId(java.math.BigDecimal logId) {
        this.logId = logId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getOperCode() {
        return operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getOperIP() {
        return operIP;
    }

    public void setOperIP(String operIP) {
        this.operIP = operIP;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getCrtTimeStr() {
        return crtTimeStr;
    }

    public void setCrtTimeStr(String crtTimeStr) {
        this.crtTimeStr = crtTimeStr;
    }

}
