<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>公告</title>
</head>
<body>
	<h3 style="font-size: 24px;text-align: center;">${sysNotice.title}</h1>
	<h1 style="font-size: 14px;color:#CDC8B1;text-align: center;">发布时间：${sysNotice.startTimeStr}~${sysNotice.endTimeStr}  by 管理员</h1>
	
	<div style="padding: 30px;">
		<div id="indexNoticeContent" style="margin-left: auto;word-wrap:break-word;margin-right: auto;padding: 10px;font-size: 14px;height: auto;background-color: #E6E6FA;-webkit-border-radius: 12px;-moz-border-radius: 12px;border-radius: 12px;">
			${sysNotice.content}
		</div>
	</div>
 	<script type="text/javascript" charset="utf-8" src="${ctx}/plugins/umeditor/umeditor.js"></script>
	<script type="text/javascript">
		$("#indexNoticeContent").html(UM.utils.html($("#indexNoticeContent").html()));
	</script>
</body>

</html>
