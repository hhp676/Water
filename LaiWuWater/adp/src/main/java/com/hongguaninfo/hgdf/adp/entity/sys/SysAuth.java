package com.hongguaninfo.hgdf.adp.entity.sys;

import java.util.ArrayList;
import java.util.List;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

/**
 * 系统权限表:SYS_AUTH entity 层
 * 
 * @author:
 */

public class SysAuth extends BaseEntity implements Comparable {
    // AUTH_ID_ : 权限编号
    private java.math.BigDecimal authId;

    // AUTH_TYPE_ : 权限类别(0:应用;1:模块;2:操作)
    private Integer authType;

    private String authTypeStr;

    // AUTH_CODE_ : 编码
    private String authCode;
    
    private String authCodeForQuery;

    // AUTH_NAME_ : 权限名称
    private String authName;
    private String authNameForQuery;
    

    // AUTH_ENNAME : 英文名
    private String authEnname;

    // AUTH_URI_ : URI
    private String authUri;

    // AUTH_IMG_ : 图标
    private String authIcon;

    // ORDER_ID_ : 排序ID
    private Integer orderId;

    // DESCR_ : 描述
    private String descr;

    // IS_DELETE_ : 删除标识(1:已删除;0:正常)
    private Integer isDelete;

    // IS_FINAL_ : 是否不可修改(1:不可修改;0:可修改)
    private Integer isFinal;

    private String isFinalStr;

    // CRT_TIME_ : 数据创建时间
    private java.util.Date crtTime;

    // UPD_TIME_ : 数据最后修改时间
    private java.util.Date updTime;

    // CRT_USERID_ : 数据创建用户编号
    private java.math.BigDecimal crtUserid;

    // UPD_USERID_ : 数据修改用户编号
    private java.math.BigDecimal updUserid;

    // OPER_ID_ : 操作编号
    private java.math.BigDecimal operId;

    // FID_ : 父IDs
    private java.math.BigDecimal fid;

    private String fName;

    private String fids;

    // LEVEL_ID_ : 等级ID
    private Integer levelId;

    // IS_VISIBLE_ : 是否可见
    private Integer isVisible;

    private List<SysAuth> childs = new ArrayList<SysAuth>();

    private String iconCls;// 图标样式

    private java.math.BigDecimal _parentId;// 父节点id

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public java.math.BigDecimal get_parentId() {
        return _parentId;
    }

    public void set_parentId(java.math.BigDecimal _parentId) {
        this._parentId = _parentId;
    }

    /**
     * AUTH_ID_
     */
    public java.math.BigDecimal getAuthId() {
        return authId;
    }

    /**
     * AUTH_ID_
     */
    public void setAuthId(java.math.BigDecimal authId) {
        this.authId = authId;
    }

    /**
     * AUTH_TYPE_
     */
    public Integer getAuthType() {
        return authType;
    }

    /**
     * AUTH_TYPE_
     */
    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    /**
     * AUTH_CODE_
     */
    public String getAuthCode() {
        return authCode;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * AUTH_CODE_
     */
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * AUTH_NAME_
     */
    public String getAuthName() {
        return authName;
    }

    /**
     * AUTH_NAME_
     */
    public void setAuthName(String authName) {
        this.authName = authName;
    }

    /**
     * AUTH_ENNAME
     */
    public String getAuthEnname() {
        return authEnname;
    }

    /**
     * AUTH_ENNAME
     */
    public void setAuthEnname(String authEnname) {
        this.authEnname = authEnname;
    }

    /**
     * AUTH_URI_
     */
    public String getAuthUri() {
        return authUri;
    }

    /**
     * AUTH_URI_
     */
    public void setAuthUri(String authUri) {
        this.authUri = authUri;
    }

    /**
     * AUTH_ICON_
     */
    public String getAuthIcon() {
        return authIcon;
    }

    /**
     * AUTH_ICON_
     */
    public void setAuthIcon(String authIcon) {
        this.authIcon = authIcon;
    }

    /**
     * ORDER_ID_
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * ORDER_ID_
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * DESCR_
     */
    public String getDescr() {
        return descr;
    }

    /**
     * DESCR_
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * IS_DELETE_
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * IS_DELETE_
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * IS_FINAL_
     */
    public Integer getIsFinal() {
        return isFinal;
    }

    /**
     * IS_FINAL_
     */
    public void setIsFinal(Integer isFinal) {
        this.isFinal = isFinal;
    }

    /**
     * CRT_TIME_
     */
    public java.util.Date getCrtTime() {
        return crtTime;
    }

    /**
     * CRT_TIME_
     */
    public void setCrtTime(java.util.Date crtTime) {
        this.crtTime = crtTime;
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
     * CRT_USERID_
     */
    public java.math.BigDecimal getCrtUserid() {
        return crtUserid;
    }

    /**
     * CRT_USERID_
     */
    public void setCrtUserid(java.math.BigDecimal crtUserid) {
        this.crtUserid = crtUserid;
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

    public String getAuthTypeStr() {
        return authTypeStr;
    }

    public void setAuthTypeStr(String authTypeStr) {
        this.authTypeStr = authTypeStr;
    }

    public String getIsFinalStr() {
        return isFinalStr;
    }

    public void setIsFinalStr(String isFinalStr) {
        this.isFinalStr = isFinalStr;
    }

    public java.math.BigDecimal getOperId() {
        return operId;
    }

    public void setOperId(java.math.BigDecimal operId) {
        this.operId = operId;
    }

    public java.math.BigDecimal getFid() {
        return fid;
    }

    public void setFid(java.math.BigDecimal fid) {
        this.fid = fid;
    }

    public String getFids() {
        return fids;
    }

    public void setFids(String fids) {
        this.fids = fids;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public List<SysAuth> getChilds() {
        return childs;
    }

    public void setChilds(List<SysAuth> childs) {
        this.childs = childs;
    }

    public void addChild(SysAuth child) {
        this.childs.add(child);
    }

	public String getAuthCodeForQuery() {
		return authCodeForQuery;
	}

	public void setAuthCodeForQuery(String authCodeForQuery) {
		this.authCodeForQuery = authCodeForQuery;
	}

	public String getAuthNameForQuery() {
		return authNameForQuery;
	}

	public void setAuthNameForQuery(String authNameForQuery) {
		this.authNameForQuery = authNameForQuery;
	}
	@Override
    public int compareTo(Object o) {
		SysAuth sdto = (SysAuth)o;
         return  this.orderId.compareTo(sdto.getOrderId());
    }
}
