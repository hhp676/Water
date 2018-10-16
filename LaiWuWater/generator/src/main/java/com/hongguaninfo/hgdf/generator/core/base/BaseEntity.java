package com.hongguaninfo.hgdf.generator.core.base;

import java.io.Serializable;
import java.util.Date;

import com.hongguaninfo.hgdf.core.utils.DateUtil;

/**
 * 
 * @ClassName: BaseEntity
 * @Description: 基础实体
 * @author henry
 * @date 2014-2-19 上午11:25:57
 * 
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -7200095849148417467L;

    protected static final String DATE_FORMAT = "yyyy-MM-dd";

    protected static final String TIME_FORMAT = "HH:mm:ss";

    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

    // 排序
    private String orderStr;

    // 开始结束时间
    private Date startTime;
    private Date endTime;

    private String startTimeStr;
    private String endTimeStr;

    // 列头下拉框
    private String distinctName;
    private String distinctValue;

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setStartTime(String startTimeStr) {
        if (startTimeStr != null && startTimeStr.length() > 0) {
            this.startTime = DateUtil.convertTimeStringToDate(startTimeStr);
        }
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setEndTime(String endTimeStr) {
        if (endTimeStr != null && endTimeStr.length() > 0) {
            this.endTime = DateUtil.convertTimeStringToDate(endTimeStr);
        }
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getDistinctName() {
        return distinctName;
    }

    public void setDistinctName(String distinctName) {
        this.distinctName = distinctName;
    }

    public String getDistinctValue() {
        return distinctValue;
    }

    public void setDistinctValue(String distinctValue) {
        this.distinctValue = distinctValue;
    }

}
