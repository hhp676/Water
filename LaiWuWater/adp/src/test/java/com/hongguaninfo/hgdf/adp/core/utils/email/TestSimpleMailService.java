package com.hongguaninfo.hgdf.adp.core.utils.email;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath*:applicationContext-test.xml" })
public class TestSimpleMailService extends AbstractJUnit4SpringContextTests {

    @Autowired
    SimpleMailService simpleMailService;

    @Test
    public void testSendNotificationMail() {
        simpleMailService.sendNotificationMail("ABC", null);
    }

}
