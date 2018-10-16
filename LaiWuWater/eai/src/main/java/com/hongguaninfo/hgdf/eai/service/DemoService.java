package com.hongguaninfo.hgdf.eai.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.eai.entity.User;

/**
 * Demo业务类.
 * 
 * @author henry
 */
@Component
public class DemoService {
	private Log log = LogFactory.getLog(getClass());
	
	public User getUser(Long id){
		return null;
	}
	
    public List<User> getUsers(){
        return null;
    }	
}
