package com.hongguaninfo.hgdf.eai.webservice.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.eai.entity.User;
import com.hongguaninfo.hgdf.eai.service.DemoService;

/**
 * cxf在web.xml侦听/cxf, 在applicationContext.xml里侦听/jaxrx，完整访问路径为 /cxf/jaxrs/user/1.xml
 * 
 * @author henry
 */
@Path("/user")
public class DemoJaxRsService {

	private static Log LOG = LogFactory.getLog(DemoJaxRsService.class);

	@Autowired
	private DemoService demoService;

	@GET
	@Path("/{id}.xml")
	@Produces(MediaType.APPLICATION_XML)
	public User getAsXml(@PathParam("id") Long id) {
		User user = demoService.getUser(id);
		if (user == null) {
			String message = "用户不存在(id:" + id + ")";
			LOG.warn(message);
			throw buildException(Status.NOT_FOUND, message);
		}
		return user;
	}

	@GET
	@Path("/{id}.json")
	@Produces(MediaType.APPLICATION_JSON)
	public User getAsJson(@PathParam("id") Long id) {
		User user = demoService.getUser(id);
		if (user == null) {
			String message = "用户不存在(id:" + id + ")";
			LOG.warn(message);
			throw buildException(Status.NOT_FOUND, message);
		}
		return user;
	}

	private WebApplicationException buildException(Status status, String message) {
		return new WebApplicationException(Response.status(status).entity(message).type(MediaType.TEXT_PLAIN)
				.build());
	}
}
