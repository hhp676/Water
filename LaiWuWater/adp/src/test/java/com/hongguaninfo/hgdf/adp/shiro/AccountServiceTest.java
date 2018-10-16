package com.hongguaninfo.hgdf.adp.shiro;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongguaninfo.hgdf.adp.dao.sys.SysUserDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.core.utils.EncodeUtils;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.core.utils.security.DigestUtils;
import com.hongguaninfo.hgdf.test.SpringContextTestCase;

public class AccountServiceTest extends SpringContextTestCase {

	private final Log log = LogFactory.getLog(getClass());
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    private static final int SALT_SIZE = 8;	
	
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
		user.setLoginName("yuyanlin");
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

    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    public static void entryptPassword(SysUser user) {
        /*
         * byte[] salt = DigestUtils.generateSalt(SALT_SIZE);
         * user.setSalt(EncodeUtils.encodeHex(salt));
         */

        byte[] salt = new String(user.getLoginName()).getBytes();
        byte[] hashPassword = DigestUtils.sha1(user.getPlainPassword()
                .getBytes(), salt, HASH_INTERATIONS);
        user.setLoginPwd(EncodeUtils.encodeHex(hashPassword));
    }
	
	public static void main(String[] args){
        SysUser user = new SysUser();
        user.setLoginName("super");
        user.setUserId(1);
        user.setPlainPassword("111111");

        AccountServiceTest.entryptPassword(user);

        System.out.print(user.getLoginPwd());
    }
}
