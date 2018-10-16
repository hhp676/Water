package com.hongguaninfo.hgdf.adp.core.utils.email;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.hongguaninfo.hgdf.adp.dao.sys.SysUserDao;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserService;
import com.hongguaninfo.hgdf.core.utils.DateUtil;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

@ContextConfiguration(locations = { "classpath*:applicationContext-test.xml" })
public class TestMimeMailService extends AbstractJUnit4SpringContextTests {

    private static final Log lOG = LogFactory.getLog(TestMimeMailService.class);

    @Autowired
    MimeMailService mimeMailService;

    @Autowired
    SysUserDao sysUserDao;

    @Test
    @Ignore
    public void testSendNotificationMail() {
        mimeMailService.sendNotificationMail("ABC");
    }

    @Test
    @Ignore
    public void testTestNotifyMail0801() {
        String content = "鸿冠OA系统已经开始测试，欢迎大家登录测试环境提出您的建议. <br> 目前的功能点包括：系统管理、系统运维、我的流程、我的任务、项目管理（未完成）、日报管理、统计报表（未完成）等几大主要模块。"
                + "<br> 您的测试登录名/密码为：姓名拼音/111111"
                + "<br> <br> 为方便测试目前角色及权限未分配完毕，所有用户都拥有当前全部权限."
                + "<br> 为达到最佳使用效果，建议使用chrome浏览器  <a href='http://www.google.cn/chrome/' targe='_blank'>下载</a>";
        String subject = "鸿冠OA系统测试通知-" + DateUtil.getDateStr(new Date());

        List<SysUser> users = sysUserDao.getList(new SysUser());
        for (SysUser user : users) {
            lOG.debug("NAME:" + user.getUserName());
            lOG.debug("EMAIL:" + user.getEmail());
            if (null != user.getEmail()) {
                //testMailService.sendMainMail(user.getUserName(), user.getEmail(), content, subject);
                lOG.debug("testTestNotifyMail to " + user.getEmail() + " ok!");
            }
        }

    }
}
