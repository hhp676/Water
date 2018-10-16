package com.hongguaninfo.hgdf.adp.core.aop.log;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLog;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserLogService;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

@Aspect
@Component
public class UserLogAspect {

    private static final Log LOG = LogFactory.getLog(UserLogAspect.class);

    @Autowired
    private SysUserLogService sysUserLogService;

    //默认混淆密码
    private String MIX_PWD = "********";
    
    @After(value = "@annotation(userLog)")
    public void saveUserLog(JoinPoint joinPoit, UserLog userLog)
            throws Exception {
        SysUserLog sysUserLog = new SysUserLog();
        for (Object object : joinPoit.getArgs()) {
            if (object.getClass().equals(userLog.remarkClass())) {
                /**
                 * 判断是否为用户信息操作，若为用户信息，则将密码信息处理为********
                 */
                if(object.getClass() == SysUser.class){
                    final SysUser sysuer= (SysUser) object;
                    sysuer.setLoginPwd(MIX_PWD);
                    sysuer.setPlainPassword(MIX_PWD);
                    sysuer.setUserName(MIX_PWD);
                	sysuer.setLoginName(MIX_PWD);
            		sysuer.setEngName(MIX_PWD);
            		sysuer.setMobile(MIX_PWD);
            		sysuer.setEmail(MIX_PWD);
            		sysuer.setTelephone(MIX_PWD);
                    object = sysuer;
                }/**若为管理员或个人中心改密，日志中只记为 ********/
                else if(("adminChangePwd").equals(userLog.code())||"changePwd".equals(userLog.code())){
                    object =MIX_PWD;
                }
                sysUserLog.setRemark(JSONObject.toJSONString(object) + "|"
                        + sysUserLog.getRemark());
            }
            if (object instanceof HttpServletRequest) {
                sysUserLog.setOperIP(getAddr((HttpServletRequest)object));
            }
        }
        sysUserLog.setCrtTime(new Date());
        sysUserLog.setOperCode(userLog.code());
        sysUserLog.setOperName(userLog.name());
        sysUserLog.setLogType(userLog.type());
        sysUserLogService.saveSysUserLog(sysUserLog);
    }
    
    
    private String getAddr(HttpServletRequest request){
    	String ip=request.getHeader("x-forwarded-for");
    	if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)) {
    		ip=request.getHeader("Proxy-Client-IP");
    	}
    	if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)) {
    		ip=request.getHeader("WL-Proxy-Client-IP");
    	}
    	if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)) {
    		ip=request.getRemoteAddr();
    	}
    	return ip;

    }

}
