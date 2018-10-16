package com.hongguaninfo.hgdf.eai.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.hongguaninfo.hgdf.eai.webservice.soap.WsConstants;
import com.hongguaninfo.hgdf.eai.webservice.soap.result.WSResult;

/**
 * Web Service传输User信息的DTO.
 * 
 * 只传输外部接口需要的属性.使用JAXB 2.0的annotation标注JAVA-XML映射,尽量使用默认约定.
 * 
 * @author henry
 */
@XmlRootElement
@XmlType(name = "User", namespace = WsConstants.NS)
public class User extends WSResult {

    //@XmlAttribute
	private Long id;
	private String loginName;
	private String name;
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long value) {
		id = value;
	}

	@NotBlank
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String value) {
		loginName = value;
	}

	@NotBlank 
	public String getName() {
		return name;
	}

	public void setName(String value) {
		name = value;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		email = value;
	}

	/**
	 * 重新实现toString()函数方便在日志中打印DTO信息.
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
