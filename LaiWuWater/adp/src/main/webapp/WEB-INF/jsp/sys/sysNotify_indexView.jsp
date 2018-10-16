<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>通知</title>
</head>
<body>
	<h3 style="font-size: 24px;text-align: center;">${sysNotify.title}</h1>
	
	<div style="padding: 30px;">
		<div id="indexNotifyContent" style="margin-left: auto;word-wrap:break-word;margin-right: auto;padding: 10px;font-size: 14px;height: auto;background-color: #E6E6FA;-webkit-border-radius: 12px;-moz-border-radius: 12px;border-radius: 12px;">
			${sysNotify.content}
		</div>
	</div>

</body>

</html>
