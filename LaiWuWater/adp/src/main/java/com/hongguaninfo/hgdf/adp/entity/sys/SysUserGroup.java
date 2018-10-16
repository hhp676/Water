package com.hongguaninfo.hgdf.adp.entity.sys;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

/**
 * 系统用户组表:SYS_USER_GROUP entity 层
 * 
 * @author:yuyanlin
 */

public class SysUserGroup extends BaseEntity {
    // GROUP_ID_ : 用户组编号
    private java.math.BigDecimal groupId;

    // DEPART_ID_ : 部门编号
    private java.math.BigDecimal departId;

    private String departName;

    // GROUP_NAME_ : 用户组名称
    private String groupName;

    // GROUP_CODE_ : 用户组编码(默认值为编号)
    private String groupCode;

    // DESCR_ : 描述
    private String descr;

    // FID_ : 父组编号
    private java.math.BigDecimal fid;

    // 父组名称
    private String fName;

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

    private java.math.BigDecimal _parentId;// 父节点id

    private String iconCls;// 图标样式

    private String checkedAuthIds;
    private String checkedRoleIds;
    private String authIds;
    private String roleIds;
    //统计该组下、该域下的人员
    private java.math.BigDecimal userCount;
    /**
     * GROUP_ID_
     */
    public java.math.BigDecimal getGroupId() {
        return groupId;
    }

    public String getCheckedAuthIds() {
        return checkedAuthIds;
    }

    public void setCheckedAuthIds(String checkedAuthIds) {
        this.checkedAuthIds = checkedAuthIds;
    }

    public String getCheckedRoleIds() {
        return checkedRoleIds;
    }

    public void setCheckedRoleIds(String checkedRoleIds) {
        this.checkedRoleIds = checkedRoleIds;
    }

    public String getAuthIds() {
        return authIds;
    }

    public void setAuthIds(String authIds) {
        this.authIds = authIds;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    /**
     * GROUP_ID_
     */
    public void setGroupId(java.math.BigDecimal groupId) {
        this.groupId = groupId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * DEPART_ID_
     */
    public java.math.BigDecimal getDepartId() {
        return departId;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    /**
     * DEPART_ID_
     */
    public void setDepartId(java.math.BigDecimal departId) {
        this.departId = departId;
    }

    /**
     * GROUP_NAME_
     */
    public String getGroupName() {
        return groupName;
    }

    public java.math.BigDecimal get_parentId() {
        return _parentId;
    }

    public void set_parentId(java.math.BigDecimal _parentId) {
        this._parentId = _parentId;
    }

    public String getIsFinalStr() {
        return isFinalStr;
    }

    public void setIsFinalStr(String isFinalStr) {
        this.isFinalStr = isFinalStr;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    /**
     * GROUP_NAME_
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * GROUP_CODE_
     */
    public String getGroupCode() {
        return groupCode;
    }

    /**
     * GROUP_CODE_
     */
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
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
     * FID_
     */
    public java.math.BigDecimal getFid() {
        return fid;
    }

    /**
     * FID_
     */
    public void setFid(java.math.BigDecimal fid) {
        this.fid = fid;
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

	public java.math.BigDecimal getUserCount() {
		return userCount;
	}

	public void setUserCount(java.math.BigDecimal userCount) {
		this.userCount = userCount;
	}

}
