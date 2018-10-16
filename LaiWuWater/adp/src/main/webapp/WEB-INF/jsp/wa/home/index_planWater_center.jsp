<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<head>
<title>莱芜市节水信息化管理平台</title>
<meta charset="utf-8" /> 
<meta http-equiv="X-UA-Compatible" content="IE=edge,IE=11,IE=10,IE=9,IE=8" />
<style type="text/css">
</style>
<link rel="icon" href="action.ico" type="image/x-icon" />
<link rel="shortcut icon" href="action.ico" type="image/x-icon" />
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${ctx}/plugins/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/plugins/jquery-easyui/themes/icon.css">
	<link href="${ctx}/plugins/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet"> 
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/css/page/index.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css" />


<link rel="stylesheet" type="text/css" href="${ctx}/static/css/error/dandelion.css"  media="screen" />

<!--[if IE 8]>
    <! -- ie8时,使用jqueryA版本  -->
	<script type="text/javascript" src="${ctx}/static/js/jquery-A.js"></script>
<![endif]-->
<!--[if gte IE 9]>
    <! --ie版本大于等于9时,使用jquery1.12.4版本  -->
	<script type="text/javascript" src="${ctx}/static/js/jquery-1.12.4.js"></script>
<![endif]-->
    
<%--     <script type="text/javascript" src="${ctx}/static/js/jquery-1.12.4.js"></script> --%>
<!-- My97DatePicker -->
<script type="text/javascript"
	src="${ctx}/plugins/My97DatePicker/WdatePicker.js"></script>

<!-- jquery-ui -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/plugins/jquery-ui/jquery-ui.css" />
<script type="text/javascript"
	src="${ctx}/plugins/jquery-ui/jquery-ui.js"></script>

<!-- easyui -->
<script type="text/javascript" src="${ctx}/static/js/jquery.json-2.4.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/jquery.easyui.js"></script>
<!-- um编辑器 -->

<script type="text/javascript" charset="utf-8" src="${ctx}/plugins/umeditor/umeditor.config.js"></script>
 <script type="text/javascript" charset="utf-8" src="${ctx}/plugins/umeditor/umeditor.js"></script>
 <script type="text/javascript" src="${ctx}/plugins/umeditor/lang/zh-cn/zh-cn.js"></script>

<!-- jquery extension -->
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/extension/datagrid-groupview.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/extension/datagrid-detailview.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/extension/datagrid-bufferview.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/extension/datagrid-filter.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/extension/jquery-easyui-portal/jquery.portal.js"></script>
<link
	href="${ctx}/plugins/jquery-easyui/extension/jquery-easyui-portal/portal.css"
	type="text/css" rel="stylesheet">
	
<!-- validate -->
<script type="text/javascript"
	src="${ctx}/plugins/jquery-validator/jquery.validate.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-validator/jquery.validate.message_cn.js"></script>

<!-- blockUI -->
<script type="text/javascript"
	src="${ctx}/plugins/jquery-blockUI/jquery.blockUI.js"></script>




<!-- jqPlot -->
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="${ctx}/plugins/jqPlot/excanvas.js"></script><![endif]-->
<script language="javascript" type="text/javascript"
	src="${ctx}/plugins/jqPlot/jquery.jqplot.js"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx}/plugins/jqPlot/jquery.jqplot.css" />

<!-- flot -->
<script language="javascript" type="text/javascript"
	src="${ctx}/plugins/flot/jquery.flot.js"></script>

<!-- uploadify -->
<link href="${ctx}/plugins/uploadify/uploadify.css" type="text/css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctx}/plugins/uploadify/jquery.uploadify.js"></script>

<script type="text/javascript" src="${ctx}/plugins/jquery-easyui/jquery.easyui.custom.js"></script>
<script type="text/javascript" src="${ctx}/static/js/main.js"></script>
<script type="text/javascript" src="${ctx}/static/js/hg.util.js"></script>
<script type="text/javascript" src="${ctx}/static/js/hg.config.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/static/js/theme.js"></script>

<!-- jGrowl
<link rel="stylesheet" href="${ctx}/plugins/jquery-jGrowl/jquery.jgrowl.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/jgrowl/custom.css" type="text/css" />
<script type="text/javascript" src="${ctx}/plugins/jquery-jGrowl/jquery.jgrowl.js"></script> -->

<!-- noty -->
<script type="text/javascript"
	src="${ctx}/plugins/jquery-noty/jquery.noty.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-noty/layouts/center.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-noty/themes/default.js"></script>
<script type="text/javascript" src="${ctx}/static/js/noty/custom.js"></script>

<!-- gridly -->
<link href="${ctx}/plugins/jquery-gridly/jquery.gridly.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/gridly/custom.css"
	type="text/css" />
<script src="${ctx}/plugins/jquery-gridly/jquery.gridly.js"
	type="text/javascript"></script>

<!-- tooltipster -->
<link href="${ctx}/plugins/tooltipster/tooltipster.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx}/plugins/tooltipster/jquery.tooltipster.js"
	type="text/javascript"></script>
<link href="${ctx}/plugins/tooltipster/themes/tooltipster-light.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/plugins/tooltipster/themes/tooltipster-noir.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/plugins/tooltipster/themes/tooltipster-punk.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/plugins/tooltipster/themes/tooltipster-shadow.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/tooltipster/custom.css"
	type="text/css" />

<!-- upload -->
<script type="text/javascript" src="${ctx}/static/js/ajaxfileupload.js"></script>
	
<!-- MyDigitclock -->
<script src="${ctx}/plugins/MyDigitclock/jquery-MyDigitclock.js"></script>

<!-- grumble -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/plugins/grumble/css/grumble.css">
<script src="${ctx}/plugins/grumble/js/Bubble.js"></script>
<script src="${ctx}/plugins/grumble/js/jquery.grumble.js"></script>

<!-- workflow -->
<script src="${ctx}/static/js/workflow/workflow.js"></script>

<script type="text/javascript" src="${ctx}/static/js/security/RSAUtil.js"></script>

	<!-- map-->
<%--	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/map/style.css" />--%>
</head>
<body class="easyui-layout" fit=true >
	<div region="north" split="true" border="false" id="indexTop_new">
		<span style="float: right; padding-right: 20px;font-size:13px;text-align: left;  text-shadow: 0px 0px 6px rgba(0, 0, 0, 1); font-weight:bold;line-height: 55px;" class="head">
			欢迎  <shiro:principal />,<a href="#" id="backToMap">返回地图</a>
			| <a href="#" id="loginOut">安全退出</a> | 换肤：<select id="cb-theme"
			style="width: 120px"></select>
		</span>
		 <span style="padding-left: 10px; font-size: 16px;font-family: 黑体;font-size: 28px; padding-left: 30px;text-align: left; text-shadow: 0px 0px 6px rgba(0, 0, 0, 1); font-weight:bold;line-height: 55px;">
			 莱芜市节水信息化管理平台
		 </span>
	</div>
	<div id="center"   style="overflow:hidden;margin:0px;padding:0px;"
		data-options="region:'center',href:'${ctx}/indexPlanWaterHome',border:false">
	</div>
	<div region="south" split="false" id="indexSouth"> 
		<div class="footer">  </div>
	</div>
	<div id="jgrowl" class="bottom-right"></div>
</body>
<script type="text/javascript" src="${ctx}/static/js/commonIndexCenter.js"></script>
<script type="text/javascript" src="${ctx}/static/js/index.js"></script>
<!-- echarts -->
 <script type="text/javascript" src="${ctx}/plugins/echarts/echarts-plain.js"></script> 
</html>
