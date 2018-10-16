package com.hongguaninfo.hgdf.test;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.DefaultAnnotationHandlerMapping;

@ContextConfiguration(locations = { "classpath*:applicationContext-test.xml",
		"file:src/main/webapp/WEB-INF/spring3-servlet.xml" })
public class SpringActionTestCase extends AbstractJUnit4SpringContextTests {

	public static Logger logger = LoggerFactory
			.getLogger(SpringActionTestCase.class);

	/**
	 * default http port.
	 */
	private static final int DEFAULT_PORT = 80;

	/**
	 * DefaultAnnotationHandlerMapping.
	 */
	@Resource(type = DefaultAnnotationHandlerMapping.class)
	protected HandlerMapping handlerMapping;
	/**
	 * AnnotationMethodHandlerAdapter.
	 */
	@Resource(type = AnnotationMethodHandlerAdapter.class)
	protected HandlerAdapter handlerAdapter;

	/**
	 * Simulate Request to URL appoint by MockHttpServletRequest.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return ModelAndView
	 * @throws Exception
	 *             runtimeException
	 */
	public final ModelAndView excuteAction(final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		HandlerExecutionChain chain = this.handlerMapping.getHandler(request);
		final ModelAndView model = this.handlerAdapter.handle(request,
				response, chain.getHandler());
		return model;
	}

	/**
	 * Simulate Request to URL appoint by MockHttpServletRequest, default POST,
	 * port 80.
	 * 
	 * @param url
	 *            requestURL
	 * @param objects
	 *            parameters
	 * @return ModelAndView
	 */
	public final ModelAndView excuteAction(final String url,
			final Object[]... objects) {
		return this.excuteAction("POST", url, DEFAULT_PORT, objects);
	}

	/**
	 * Simulate Request to URL appoint by MockHttpServletRequest, default POST.
	 * 
	 * @param url
	 *            requestURL
	 * @param port
	 *            int
	 * @param objects
	 *            parameters
	 * @return ModelAndView
	 */
	public final ModelAndView excuteAction(final String url, final int port,
			final Object[]... objects) {
		return this.excuteAction("POST", url, port, objects);
	}

	/**
	 * Simulate Request to URL appoint by MockHttpServletRequest.
	 * 
	 * @param method
	 *            POST/GET
	 * @param url
	 *            requestURL
	 * @param port
	 *            int
	 * @param objects
	 *            parameters
	 * @return ModelAndView
	 */
	public final ModelAndView excuteAction(final String method,
			final String url, final int port, final Object[]... objects) {
		MockHttpServletRequest request = new MockHttpServletRequest(method, url);
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setServerPort(port);
		request.setLocalPort(port);
		if (objects != null) {
			for (Object[] object : objects) {
				if (object != null && object.length == 2) {
					request.addParameter(object[0].toString(),
							object[1].toString());
				}
			}
		}
		MockHttpSession session = new MockHttpSession();
		request.setSession(session);
		try {
			return this.excuteAction(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return null;
	}
}