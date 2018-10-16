package com.hongguaninfo.hgdf.adp.entity.sys;
import java.util.Date;
import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

 /**
 * : sys_notice
 * entity 层
 * @author henry
 */

public class SysNotice extends BaseEntity{
	//NOTIFY_ID_ : 通知ID
	private Integer notifyId;

	//STATUS_ : 状态（1:发布|0:未发布）
	private Integer status;
	
	private String statusStr;

	//AUTO_PUB_ : 自动发布（1:是|0:否）
	private Integer autoPub;
	
	private String autoPubStr;

	//START_TIME_ : 起始时间
	private Date startTime;

	//END_TIME_ : 截止时间
	private Date endTime;

	//TITLE_ : 标题
	private String title;

	//CONTENT_ : 内容
	private String content;

	//SORT_ID_ : 排序号
	private Integer sortId;

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
	
	private String queryDate;
	
	


	

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}

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
	 * STATUS_
	 */
	public Integer getStatus () {
		return status;
	}

	/**
	 * STATUS_
	 */
	public void setStatus (Integer status) {
		this.status = status;
	}
	/**
	 * AUTO_PUB_
	 */
	public Integer getAutoPub () {
		return autoPub;
	}

	/**
	 * AUTO_PUB_
	 */
	public void setAutoPub (Integer autoPub) {
		this.autoPub = autoPub;
	}
	/**
	 * START_TIME_
	 */
	public Date getStartTime () {
		return startTime;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public String getAutoPubStr() {
		return autoPubStr;
	}

	public void setAutoPubStr(String autoPubStr) {
		this.autoPubStr = autoPubStr;
	}

	public String getIsFinalStr() {
		return isFinalStr;
	}

	public void setIsFinalStr(String isFinalStr) {
		this.isFinalStr = isFinalStr;
	}

	/**
	 * START_TIME_
	 */
	public void setStartTime (Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * END_TIME_
	 */
	public Date getEndTime () {
		return endTime;
	}

	/**
	 * END_TIME_
	 */
	public void setEndTime (Date endTime) {
		this.endTime = endTime;
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
	 * SORT_ID_
	 */
	public Integer getSortId () {
		return sortId;
	}

	/**
	 * SORT_ID_
	 */
	public void setSortId (Integer sortId) {
		this.sortId = sortId;
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
