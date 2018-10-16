<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>首页center</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
	<div id="leftMenu" data-options="region:'west',collapsed:true,split:true,title:'导航菜单'" style="width:200px;">
		<div id="leftAccordion" class="easyui-accordion" data-options="fit:true,border:false" >

		</div>
	</div>
	<div data-options="region:'center'">
		<div id="tab" class="easyui-tabs" data-options="fit:true,border:false,plain:true,tools:'#tab-tools'">
			<div title="用水计划管理" id="homeTab" data-options="href:'${ctx}/wa/WaPlanYearWaterData/showWaPlanYearWaterData',tools:'#p-tools',iconCls:'acc_icon_other',tabWidth:120" style="padding:5px"></div>
		</div>

	</div>

</div>
<script type="text/javascript" src="${ctx}/static/js/commonIndexHome.js"></script>

</body>
</html>
