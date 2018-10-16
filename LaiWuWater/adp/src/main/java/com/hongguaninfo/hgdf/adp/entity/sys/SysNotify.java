package com.hongguaninfo.hgdf.adp.entity.sys;
import java.util.Date;
import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

 /**
 * : sys_notify
 * entity 层
 * @author henry
 */

public class SysNotify extends BaseEntity{
	//NOTIFY_ID_ : 通知ID
	private Integer notifyId;

	//USER_ID_ : 用户ID
	private Integer userId;

	//TEMP_ID_ : 模板ID
	private Integer tempId;

	//TITLE_ : 通知标题
	private String title;

	//CONTENT_ : 通知内容
	private String content;

	//CRT_TIME_ : 创建时间
	private Date crtTime;

	//IS_READ_ : 是否读取
	private Integer isRead;
	
	private String isReadStr;

	//READ_TIME_ : 阅读时间
	private Date readTime;
	
	private String readTimeStr;


	private String contentSub;
	private String titleSub;
	/**
	 * NOTIFY_ID_
	 */
	public Integer getNotifyId () {
		return notifyId;
	}

	/**
	 * NOTIFY_ID_
	 */
	public void setNotifyId (Integer notifyId) {
		this.notifyId = notifyId;
	}
	/**
	 * USER_ID_
	 */
	public Integer getUserId () {
		return userId;
	}

	
	public String getReadTimeStr() {
		return readTimeStr;
	}

	public void setReadTimeStr(String readTimeStr) {
		this.readTimeStr = readTimeStr;
	}

	/**
	 * USER_ID_
	 */
	public void setUserId (Integer userId) {
		this.userId = userId;
	}
	/**
	 * TEMP_ID_
	 */
	public Integer getTempId () {
		return tempId;
	}

	
	public String getIsReadStr() {
		return isReadStr;
	}

	public void setIsReadStr(String isReadStr) {
		this.isReadStr = isReadStr;
	}

	/**
	 * TEMP_ID_
	 */
	public void setTempId (Integer tempId) {
		this.tempId = tempId;
	}
	/**
	 * TITLE_
	 */
	public String getTitle () {
		return title;
	}

	/**
	 * TITLE_
	 */
	public void setTitle (String title) {
		this.title = title;
	}
	/**
	 * CONTENT_
	 */
	public String getContent () {
		return content;
	}

	/**
	 * CONTENT_
	 */
	public void setContent (String content) {
		this.content = content;
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
	 * IS_READ_
	 */
	public Integer getIsRead () {
		return isRead;
	}

	/**
	 * IS_READ_
	 */
	public void setIsRead (Integer isRead) {
		this.isRead = isRead;
	}
	/**
	 * READ_TIME_
	 */
	public Date getReadTime () {
		return readTime;
	}

	/**
	 * READ_TIME_
	 */
	public void setReadTime (Date readTime) {
		this.readTime = readTime;
	}

	public String getContentSub() {
		return contentSub;
	}

	public void setContentSub(String contentSub) {
		this.contentSub = contentSub;
	}

	public String getTitleSub() {
		return titleSub;
	}

	public void setTitleSub(String titleSub) {
		this.titleSub = titleSub;
	}

}
