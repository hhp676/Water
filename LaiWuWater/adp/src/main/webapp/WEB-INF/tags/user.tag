<%@tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@tag import="org.springframework.context.ApplicationContext"%>
<%@tag
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@tag import="com.hongguaninfo.hgdf.adp.dao.sys.SysUserDao"%>
<%@tag import="com.hongguaninfo.hgdf.core.utils.logging.Log,com.hongguaninfo.hgdf.core.utils.logging.LogFactory"%>
<%@attribute name="userId" type="java.lang.Object"%>
<%@attribute name="loginName" type="java.lang.Object"%>
<%
	//记录日志
	Log log = LogFactory.getLog("user.tag");

    Object userId = jspContext.getAttribute("userId");
    Object loginName = jspContext.getAttribute("loginName");
    if (userId != null) {
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
        SysUserDao sysUserDao = ctx.getBean(SysUserDao.class);
        try {
            out.print(sysUserDao.getById(Integer.parseInt(userId.toString())).getUserName());
        } catch (Exception ex) {
            log.error(userId.toString(), ex);
        }
    } else if(loginName != null) {
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
        SysUserDao sysUserDao = ctx.getBean(SysUserDao.class);
        try {
            out.print(sysUserDao.getUserByLoginName(loginName.toString()).getUserName());
        } catch (Exception ex) {
            log.error(loginName.toString(), ex);
        }
    } else{
        out.print("");
    }
%>
