package com.hongguaninfo.hgdf.adp.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongguaninfo.hgdf.adp.service.sys.SysUserService;
import com.hongguaninfo.hgdf.adp.shiro.AccountService;
import com.hongguaninfo.hgdf.test.SpringContextTestCase;

public class SysUserServiceTest extends SpringContextTestCase{

	@Autowired
	SysUserService sysUserService;
	
	@Test
	public void testGetUserIndexLeftMenu() {
		
	}

}
