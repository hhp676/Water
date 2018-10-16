package com.hongguaninfo.hgdf.wadp.core.aop.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hongguaninfo.hgdf.wadp.entity.sys.SysUserLog;
import com.hongguaninfo.hgdf.wadp.service.sys.SysUserLogService;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

@Aspect
@Component
public class UserLogAspect {

    private static final Log LOG = LogFactory.getLog(UserLogAspect.class);

    @Autowired
    private SysUserLogService sysUserLogService;

    @After(value = "@annotation(userLog)")
    public void saveUserLog(JoinPoint joinPoit, UserLog userLog)
            throws Exception {
        SysUserLog sysUserLog = new SysUserLog();
        for (Object object : joinPoit.getArgs()) {
            if (object.getClass().equals(userLog.remarkClass())) {
                sysUserLog.setRemark(JSONObject.toJSONString(object) + "|"
                        + sysUserLog.getRemark());
            }
            if (object instanceof HttpServletRequest) {
                sysUserLog.setOperIP(((HttpServletRequest) object)
                        .getRemoteAddr());
            }
        }
        sysUserLog.setCrtTime(new Date());
        sysUserLog.setOperCode(userLog.code());
        sysUserLog.setOperName(userLog.name());
        sysUserLog.setLogType(userLog.type());
        sysUserLogService.saveSysUserLog(sysUserLog);
    }
}
