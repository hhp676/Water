package com.hongguaninfo.hgdf.eai.webservice.soap;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.hongguaninfo.hgdf.eai.entity.User;
import com.hongguaninfo.hgdf.eai.webservice.soap.result.IdResult;
import com.hongguaninfo.hgdf.eai.webservice.soap.result.ListResult;

/**
 * JAX-WS2.0的WebService接口定义类.
 * 
 * 使用JAX-WS2.0 annotation设置WSDL中的定义. 使用WSResult及其子类类包裹返回结果.
 * 使用DTO传输对象隔绝系统内部领域对象的修改对外系统的影响.
 * 
 * @author henry
 */
// name 指明wsdl中<wsdl:portType>元素的名称
@WebService(name = "DemoService", targetNamespace = WsConstants.NS)
public interface DemoSoapService {
	/**
	 * 获取用户列表信息.
	 */
	ListResult<User> getUsers();

	/**
	 * 获取用户信息.
	 */
	User getUser(@WebParam(name = "id") Long id);

	/**
	 * 新建用户.
	 */
	IdResult createUser(@WebParam(name = "user") User user);

}
