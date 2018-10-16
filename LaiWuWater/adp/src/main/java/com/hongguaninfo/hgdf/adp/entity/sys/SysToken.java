package com.hongguaninfo.hgdf.adp.entity.sys;
import java.util.Date;
import com.hongguaninfo.hgdf.adp.core.base.BaseEntity;

 /**
 * : sys_token
 * entity 层
 * @author henry
 */

public class SysToken extends BaseEntity{
	//TOKEN_ID_ : 密钥ID
	private Integer tokenId;

	//USER_ID_ : 用户ID
	private Integer userId;

	//STATUS_ : 状态（1:使用|0:未使用）
	private Integer status;

	//USE_TYPE_ : 用途类型
	private String useType;

	//AVOID_LOGIN_ : 是否免登录（1:是|0:否）
	private Integer avoidLogin;

	//TOKEN_SIGN_ : 密钥签名
	private String tokenSign;

	//CRT_TIME_ : 创建时间
	private Date crtTime;

	//DUE_TIME_ : 过期时间
	private Date dueTime;


	/**
	 * TOKEN_ID_
	 */
	public Integer getTokenId () {
		return tokenId;
	}

	/**
	 * TOKEN_ID_
	 */
	public void setTokenId (Integer tokenId) {
		this.tokenId = tokenId;
	}
	/**
	 * USER_ID_
	 */
	public Integer getUserId () {
		return userId;
	}

	/**
	 * USER_ID_
	 */
	public void setUserId (Integer userId) {
		this.userId = userId;
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
	 * USE_TYPE_
	 */
	public String getUseType () {
		return useType;
	}

	/**
	 * USE_TYPE_
	 */
	public void setUseType (String useType) {
		this.useType = useType;
	}
	/**
	 * AVOID_LOGIN_
	 */
	public Integer getAvoidLogin () {
		return avoidLogin;
	}

	/**
	 * AVOID_LOGIN_
	 */
	public void setAvoidLogin (Integer avoidLogin) {
		this.avoidLogin = avoidLogin;
	}
	/**
	 * TOKEN_SIGN_
	 */
	public String getTokenSign () {
		return tokenSign;
	}

	/**
	 * TOKEN_SIGN_
	 */
	public void setTokenSign (String tokenSign) {
		this.tokenSign = tokenSign;
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
	 * DUE_TIME_
	 */
	public Date getDueTime () {
		return dueTime;
	}

	/**
	 * DUE_TIME_
	 */
	public void setDueTime (Date dueTime) {
		this.dueTime = dueTime;
	}

}
