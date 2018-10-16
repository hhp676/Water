package com.hongguaninfo.hgdf.eai.webservice.soap.result;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import com.hongguaninfo.hgdf.eai.webservice.soap.WsConstants;

@XmlType(name = "ListResult", namespace = WsConstants.NS)
public class ListResult<T> extends WSResult {
	private List<T> list;

	public ListResult() {
	}

	public ListResult(List<T> list) {
		this.list = list;
	}

	@XmlElementWrapper(name = "list")
	@XmlElement(name = "entity")
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
