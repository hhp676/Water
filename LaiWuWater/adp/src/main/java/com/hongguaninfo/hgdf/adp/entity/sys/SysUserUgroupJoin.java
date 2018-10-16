package com.hongguaninfo.hgdf.adp.entity.sys;

import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;


 /**
 *  系统用户与用户组关系表:SYS_USER_UGROUP_JOIN
 * entity 层
 * @author:
 */

public class SysUserUgroupJoin  extends BaseEntity{
	//USER_ID_ : 用户ID
	private java.math.BigDecimal userId;

	//GROUP_ID_ : 用户组编号
	private java.math.BigDecimal groupId;
	
	private String groupName;
	
	private Integer departId;
	
	private String departName;

	//IS_FINAL_ : 是否不可修改(1:不可修改;0:可修改)
	private Integer isFinal;

	//CRT_TIME_ : 数据创建时间
	private java.util.Date crtTime;

	//CRT_USERID_ : 数据创建用户编号
	private java.math.BigDecimal crtUserid;


	/**
	 * USER_ID_
	 */
	public java.math.BigDecimal getUserId () {
		return userId;
	}

	
	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getDepartId() {
		return departId;
	}


	public void setDepartId(Integer departId) {
		this.departId = departId;
	}


	public String getDepartName() {
		return departName;
	}


	public void setDepartName(String departName) {
		this.departName = departName;
	}


	/**
	 * USER_ID_
	 */
	public void setUserId (java.math.BigDecimal userId) {
		this.userId = userId;
	}
	/**
	 * GROUP_ID_
	 */
	public java.math.BigDecimal getGroupId () {
		return groupId;
	}

	/**
	 * GROUP_ID_
	 */
	public void setGroupId (java.math.BigDecimal groupId) {
		this.groupId = groupId;
	}
	/**
	 * IS_FINAL_
	 */
	public Integer getIsFinal () {
		return isFinal;
	}

	/**
	 * IS_FINAL_
	 */
	public void setIsFinal (Integer isFinal) {
		this.isFinal = isFinal;
	}
	/**
	 * CRT_TIME_
	 */
	public java.util.Date getCrtTime () {
		return crtTime;
	}

	/**
	 * CRT_TIME_
	 */
	public void setCrtTime (java.util.Date crtTime) {
		this.crtTime = crtTime;
	}
	/**
	 * CRT_USERID_
	 */
	public java.math.BigDecimal getCrtUserid () {
		return crtUserid;
	}

	/**
	 * CRT_USERID_
	 */
	public void setCrtUserid (java.math.BigDecimal crtUserid) {
		this.crtUserid = crtUserid;
	}

}
