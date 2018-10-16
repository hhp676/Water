<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>工作流实例追踪页面</title>
<link href="${ctx}/plugins/activiti/trace-viewer/qtip/jquery.qtip.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript" src="${ctx}/static/js/jquery-1.12.4.js"></script>
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${ctx}/plugins/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/plugins/jquery-easyui/themes/icon.css">
<!-- easyui -->
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/jquery.easyui.js"></script>
<!-- jquery extension -->
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${ctx}/plugins/activiti/trace-viewer/qtip/jquery.qtip.pack.js"
	type="text/javascript"></script>
<script src="${ctx }/plugins/html/jquery.outerhtml.js"
	type="text/javascript"></script>
<script type="text/javascript">
    var pid = "${processInstanceId}";
    var processDefinitionId = "${processDefinitionId}";
</script>
</head>
<body>
<div class="process_image" style="position:relative;font-size:14px;"></div>
<script src="${ctx}/static/js/workflow/process_showTrace.js" type="text/javascript"></script>
</body>
</html>
