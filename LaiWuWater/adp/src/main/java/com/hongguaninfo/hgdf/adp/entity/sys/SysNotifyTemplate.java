package com.hongguaninfo.hgdf.adp.entity.sys;
import java.util.Date;
import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

 /**
 * : sys_notify_template
 * entity 层
 * @author henry
 */

public class SysNotifyTemplate extends BaseEntity{
	//TEMP_ID_ : 模板ID
	private Integer tempId;

	//NAME_ : 模板名
	private String name;

	//MODULE_ : 模板所属模块
	private String module;

	//TITLE_TEMPLATE_ : 通知标题
	private String titleTemplate;

	//CONTENT_TEMPLATE_ : 通知内容模板
	private String contentTemplate;

	//IS_DELETE_ : 删除标识(1:已删除;0:正常)
	private Integer isDelete;

	//IS_FINAL_ : 是否不可修改(1:不可修改;0:可修改)
	private Integer isFinal;
	
	private String isFinalStr;

	//CRT_TIME_ : 创建时间
	private Date crtTime;

	//UPD_TIME_ : 数据最后修改时间
	private Date updTime;

	//CRT_USERID_ : 数据创建用户编号
	private Integer crtUserid;

	//UPD_USERID_ : 数据修改用户编号
	private Integer updUserid;


	/**
	 * TEMP_ID_
	 */
	public Integer getTempId () {
		return tempId;
	}

	/**
	 * TEMP_ID_
	 */
	public void setTempId (Integer tempId) {
		this.tempId = tempId;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * MODULE_
	 */
	public String getModule () {
		return module;
	}

	public String getIsFinalStr() {
		return isFinalStr;
	}

	public void setIsFinalStr(String isFinalStr) {
		this.isFinalStr = isFinalStr;
	}

	/**
	 * MODULE_
	 */
	public void setModule (String module) {
		this.module = module;
	}
	/**
	 * TITLE_TEMPLATE_
	 */
	public String getTitleTemplate () {
		return titleTemplate;
	}

	/**
	 * TITLE_TEMPLATE_
	 */
	public void setTitleTemplate (String titleTemplate) {
		this.titleTemplate = titleTemplate;
	}
	/**
	 * CONTENT_TEMPLATE_
	 */
	public String getContentTemplate () {
		return contentTemplate;
	}

	/**
	 * CONTENT_TEMPLATE_
	 */
	public void setContentTemplate (String contentTemplate) {
		this.contentTemplate = contentTemplate;
	}
	/**
	 * IS_DELETE_
	 */
	public Integer getIsDelete () {
		return isDelete;
	}

	/**
	 * IS_DELETE_
	 */
	public void setIsDelete (Integer isDelete) {
		this.isDelete = isDelete;
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
	public Date getCrtTime () {
		return crtTime;
	}

	/**
	 * CRT_TIME_
	 */
	public void setCrtTime (Date crtTime) {
		this.crtTime = crtTime;
	}
	/**
	 * UPD_TIME_
	 */
	public Date getUpdTime () {
		return updTime;
	}

	/**
	 * UPD_TIME_
	 */
	public void setUpdTime (Date updTime) {
		this.updTime = updTime;
	}
	/**
	 * CRT_USERID_
	 */
	public Integer getCrtUserid () {
		return crtUserid;
	}

	/**
	 * CRT_USERID_
	 */
	public void setCrtUserid (Integer crtUserid) {
		this.crtUserid = crtUserid;
	}
	/**
	 * UPD_USERID_
	 */
	public Integer getUpdUserid () {
		return updUserid;
	}

	/**
	 * UPD_USERID_
	 */
	public void setUpdUserid (Integer updUserid) {
		this.updUserid = updUserid;
	}

}
