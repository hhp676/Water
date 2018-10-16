package com.hongguaninfo.hgdf.adp.core.aop.log;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hongguaninfo.hgdf.adp.core.Constants;
import com.hongguaninfo.hgdf.adp.core.exception.BizException;
import com.hongguaninfo.hgdf.adp.entity.sys.SysConfig;
import com.hongguaninfo.hgdf.adp.entity.sys.SysMetaMethod;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUser;
import com.hongguaninfo.hgdf.adp.entity.sys.SysUserLog;
import com.hongguaninfo.hgdf.adp.service.sys.SysConfigService;
import com.hongguaninfo.hgdf.adp.service.sys.SysMetaMethodService;
import com.hongguaninfo.hgdf.adp.service.sys.SysUserLogService;
import com.hongguaninfo.hgdf.core.utils.StringUtil;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

@Aspect
@Component
public class DefaultUserLogAspect {
    
    private static final Log     LOG     = LogFactory.getLog(DefaultUserLogAspect.class);
    
    @Autowired
    private SysUserLogService    sysUserLogService;
    
    @Autowired
    private SysMetaMethodService sysMetaMethodService;
    
    @Autowired
    private SysConfigService     sysConfigService;
    
    //默认混淆密码
    private String               MIX_PWD = "********";
    
    @Pointcut("execution(* *..*Controller.*(..))")
    public void declareJoinPointExpression() {
    }
    
    @After("declareJoinPointExpression()")
    public void saveUserLog(JoinPoint joinPoit) throws Exception {
        Signature signature = joinPoit.getSignature();
        //方法名
        String methodName = signature.getName();
        //类名
        Class methodClass = signature.getDeclaringType();
        String methodClassName = signature.getDeclaringTypeName();
        //方法参数类型拼接处理
        MethodSignature methodSignature = (MethodSignature) signature;
        Class[] argTypes = methodSignature.getParameterTypes();
        String argTypesName = sysMetaMethodService.getArgTypesName(argTypes);
        
        //系统原方法表中该方法是否已配置日志信息
        SysMetaMethod filter = new SysMetaMethod();
        filter.setClassName(methodClassName);
        filter.setMethodName(methodName);
        filter.setArgsName(argTypesName);
        filter.setIsValid(Constants.ONE);
        SysMetaMethod metaMethod = sysMetaMethodService.getSysMetaMethod(filter);
        
        //如果该方法记录不存在或编码未配置，不记录日志
        if (metaMethod == null || StringUtil.isEmpty(metaMethod.getMethodCode())) {
            return;
        }
        
        //如果日志级别不低于日志配置级别，记录至用户日志
        int logLevelConfig = getSysConfigVal(Constants.SYSMETAMETHOD_LOG_LEVEL, Constants.ZERO);
        if (metaMethod.getLogLevel() >= logLevelConfig) {
            Object[] argObjects = joinPoit.getArgs();
            SysUserLog sysUserLog = new SysUserLog();
            
            //获取指定的需要存储日志的参数，若为空，参数均存储
            String logRemarkClass = metaMethod.getLogRemarkClass();
            if (!StringUtil.isEmpty(logRemarkClass)) {
                for (Object object : argObjects) {
                    String name = object.getClass().getName();
                    if (object.getClass().getName().equals(logRemarkClass)) {
                        object = filterUserInfo(object, methodName);
                        sysUserLog.setRemark(object2JsonStr(object) + "|" + sysUserLog.getRemark());
                    }
                    if (object instanceof HttpServletRequest) {
                        sysUserLog.setOperIP(getAddr((HttpServletRequest) object));
                    }
                }
            } else {
                for (Object object : argObjects) {
                    object = filterUserInfo(object, methodName);
                    String objectJsonStr = object2JsonStr(object);
                    objectJsonStr = StringUtil.isEmpty(objectJsonStr) ? "" : objectJsonStr + "|";
                    sysUserLog.setRemark(objectJsonStr + sysUserLog.getRemark());
                    
                    if (object instanceof HttpServletRequest) {
                        sysUserLog.setOperIP(getAddr((HttpServletRequest) object));
                    }
                }
            }
            
            sysUserLog.setCrtTime(new Date());
            sysUserLog.setOperCode(metaMethod.getMethodCode());
            sysUserLog.setOperName(metaMethod.getMethodZhName());
            sysUserLog.setLogType(metaMethod.getLogType());
            sysUserLogService.saveSysUserLog(sysUserLog);
        }
    }
    
    private String getAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
        
    }
    
    /**
     * 获取数值型的系统设定属性值
     * @param key
     * @param defaultValue 默认值，配置无或空值时使用
     * @return
     */
    private int getSysConfigVal(String key, int defaultVal) throws BizException{
        SysConfig config = sysConfigService.getSysConfigByKey(key);
        if(config == null || StringUtil.isBlank(config.getConfigValue())){
            return defaultVal;
        }
        int value = defaultVal;
        try{
            value = Integer.parseInt(config.getConfigValue());
        }catch(NumberFormatException e){
            throw new BizException("系统设定["+key+"]属性值应设为数值");
        }
        return value;
    }
    
    /**
     * 过滤用户信息。
     * 1.判断是否为用户信息操作，若为用户信息，则将密码信息处理为********;
     * 2.若为管理员或个人中心改密，日志中只记为********
     * @param object 参数
     * @param methodName 方法名
     * @return
     */
    private Object filterUserInfo(Object object, String methodName){
        if (object == null) {
            return null;
        }
        /**
         * 判断是否为用户信息操作，若为用户信息，则将密码信息处理为********
        */
        if (object.getClass() == SysUser.class) {
            final SysUser sysuer = (SysUser) object;
            sysuer.setLoginPwd(MIX_PWD);
            sysuer.setPlainPassword(MIX_PWD);
            sysuer.setUserName(MIX_PWD);
            sysuer.setLoginName(MIX_PWD);
            sysuer.setEngName(MIX_PWD);
            sysuer.setMobile(MIX_PWD);
            sysuer.setEmail(MIX_PWD);
            sysuer.setTelephone(MIX_PWD);
            object = sysuer;
        } /**若为管理员或个人中心改密，日志中只记为 ********/
        else if (("adminChangePwd").equals(methodName)
            || "changePwd".equals(methodName)) {
            object = MIX_PWD;
        }
        
        return object;
    }
    
    private String object2JsonStr(Object object){
        String jsonStr = "";
        try{
            jsonStr = JSONObject.toJSONString(object);
        }catch(Exception e){
            LOG.error("参数转Json失败", e);
        }
        return jsonStr;
    }
}
