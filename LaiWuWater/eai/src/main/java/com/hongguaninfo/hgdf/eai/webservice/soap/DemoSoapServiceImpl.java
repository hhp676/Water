package com.hongguaninfo.hgdf.eai.webservice.soap;

import javax.jws.WebService;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.cxf.feature.Features;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.hongguaninfo.hgdf.core.utils.Exceptions;
import com.hongguaninfo.hgdf.core.utils.beanvalidator.BeanValidators;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.eai.entity.User;
import com.hongguaninfo.hgdf.eai.service.DemoService;
import com.hongguaninfo.hgdf.eai.webservice.soap.result.IdResult;
import com.hongguaninfo.hgdf.eai.webservice.soap.result.ListResult;

/**
 * WebService服务端实现类.
 * 
 * @author henry
 */
// serviceName指明WSDL中<wsdl:service>与<wsdl:binding>元素的名称,
// endpointInterface属性指向Interface类全称.
@WebService(serviceName = "DemoService", endpointInterface = "com.hongguaninfo.hgdf.eai.webservice.soap.DemoSoapService", targetNamespace = WsConstants.NS)
@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class DemoSoapServiceImpl extends BaseSoapService implements
		DemoSoapService {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private DemoService demoService;

	@Autowired
	private Validator validator;

	/**
	 * @see DemoSoapService#getUsers()
	 */
	public ListResult getUsers() {
		ListResult result = new ListResult();
		try {
			// List<User> users = demoService.getUserList();
			// result.setList(users);
			return result;
		} catch (RuntimeException e) {
			return handleGeneralError(result, e);
		}
	}

	/**
	 * @see DemoSoapService#getUser(Long)
	 */
	public User getUser(Long id) {
		User user = new User();
		try {

			Validate.notNull(id, "id参数为空");

			// user = demoService.getUser(id);

			Validate.notNull(user, "用户不存在(id:" + id + ")");

			return user;

		} catch (IllegalArgumentException e) {
			return handleParameterError(user, e);
		} catch (RuntimeException e) {
			return handleGeneralError(user, e);
		}
	}

	/**
	 * @see DemoSoapService#createUser(User)
	 */
	public IdResult createUser(User user) {
		IdResult result = new IdResult();
		try {
			Validate.notNull(user, "用户参数为空");

			BeanValidators.validateWithException(validator, user);

			// demoService.saveUser(user);

			return new IdResult(user.getId());
		} catch (ConstraintViolationException e) {
			String message = StringUtils.join(
					BeanValidators.extractPropertyAndMessageAsList(e, " "),
					"\n");
			return handleParameterError(result, e, message);
		} catch (RuntimeException e) {
			if (Exceptions.isCausedBy(e, DuplicateKeyException.class)) {
				String message = "新建用户参数存在唯一性冲突(用户:" + user + ")";
				return handleParameterError(result, e, message);
			} else {
				return handleGeneralError(result, e);
			}
		}
	}
}
