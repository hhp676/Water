package com.hongguaninfo.hgdf.eai.webservice.soap.result;

import javax.xml.bind.annotation.XmlType;

import com.hongguaninfo.hgdf.eai.webservice.soap.WsConstants;

/**
 * 创建某个对象返回的 通用IdResult.
 * 
 * @author henry
 */
@XmlType(name = "IdResult", namespace = WsConstants.NS)
public class IdResult extends WSResult {
	private Long id;

	public IdResult() {
	}

	public IdResult(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
