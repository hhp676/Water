package com.hongguaninfo.hgdf.eai.webservice.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.eai.entity.User;
import com.hongguaninfo.hgdf.eai.service.DemoService;

//@Component
//@Path("/users")
public class RestService {
    
    private static Log log = LogFactory.getLog(RestService.class);
    
    @Context
    private User user;
    
    @Autowired
    DemoService demoService;
    
    /**
     * 获取所有用户.
     */
    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<User> getAllUser() {
        try {
            List<User> entityList = demoService.getUsers();
            return entityList;
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException();
        }
    }

    /**
     * 获取用户.
     * 
     * @throws IOException
     */
    @GET
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public User getUser(@PathParam("id") Long id) {
        try {
            User entity = demoService.getUser(id);
            return entity;
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw new WebApplicationException();
        }
    }
}
