package com.hongguaninfo.hgdf.wadp.shiro;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongguaninfo.hgdf.wadp.dao.sys.SysUserDao;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.test.SpringContextTestCase;

public class AccountServiceTest extends SpringContextTestCase {

	private final Log log = LogFactory.getLog(getClass());
	
	@InjectMocks
	@Autowired
	AccountService accountService;

	@Mock
	private SysUserDao sysUserDao;

	@Before
	public void myBefore() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindUserByLoginName() {
		fail("尚未实现");
	}

	@Test
	public void TestEntryptPassword() {
		SysUser user = new SysUser();
		user.setLoginName("admin");
		user.setUserId(1);
		user.setPlainPassword("111111");

		accountService.entryptPassword(user);

		log.debug(user.getLoginPwd());
		// 55516a952f026f086ffc38f73348b34e58e97756

		user.setLoginName("super");
		user.setUserId(1);
		user.setPlainPassword("111111");

		accountService.entryptPassword(user);
		log.debug(user.getLoginPwd());
		// 8462d0480e2232443268decc78f920a0880579d1
	}
}
