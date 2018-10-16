<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
	<%@ page
			import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
	<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
	<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
	<title>莱芜市节水信息化管理平台</title>
	<style>
		::-ms-clear, ::-ms-reveal{display: none;}
		input:-webkit-autofill {-webkit-box-shadow: 0 0 0px 1000px white inset;}
	</style>
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Expires" CONTENT="-1">
	<link rel="icon" href="action.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="action.ico" type="image/x-icon" />
	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/login.css" />

	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/error/dandelion.css"  media="screen" />

	<!--[if IE 8]>
        <! -- ie8时,使用jqueryA版本  -->
	<script type="text/javascript" src="${ctx}/static/js/jquery-A.js"></script>
	<![endif]-->
	<!--[if gte IE 9]>
        <! --ie版本大于等于9时,使用jquery1.12.4版本  -->
	<script type="text/javascript" src="${ctx}/static/js/jquery-1.12.4.js"></script>
	<![endif]-->
	<!-- easyui -->
	<script type="text/javascript" src="${ctx}/static/js/jquery.json-2.4.js"></script>
	<script type="text/javascript"
			src="${ctx}/plugins/jquery-easyui/jquery.easyui.js"></script>

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

	<!-- validate -->
	<script type="text/javascript"
			src="${ctx}/plugins/jquery-validator/jquery.validate.js"></script>
	<script type="text/javascript"
			src="${ctx}/plugins/jquery-validator/jquery.validate.message_cn.js"></script>

	<!-- blockUI -->
	<script type="text/javascript"
			src="${ctx}/plugins/jquery-blockUI/jquery.blockUI.js"></script>

	<script type="text/javascript" src="${ctx}/plugins/jquery-easyui/jquery.easyui.custom.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/main.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/hg.util.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/hg.config.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/jquery.cookie.js"></script>


	<script type="text/javascript" src="${ctx}/static/js/encode64.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/security/RSAUtil.js"></script>

	<link rel="stylesheet" type="text/css"
			  href="${ctx}/static/css/map/style.css" />
<script type="text/javascript" src="${ctx}/static/js/map/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${ctx}/static/js/map/Openlayers/OpenLayers.js"></script>
<script type="text/javascript" src="${ctx}/static/js/map/mapInitial.js"></script>
<script type="text/javascript" src="${ctx}/static/js/map/common.js"></script>
<script type="text/javascript" src="${ctx}/static/js/map/mapOperation.js"></script>
<script type="text/javascript" src="${ctx}/static/js/map/global.js"></script>

</head>

<body>
<!--头部-->
<div class="header">
	<p>莱芜市节水信息化管理平台</p>
</div>
<!--中间-->
<div class="contant">
	<!--左边-->
	<div class="left_box"></div>
	<!--地图-->
	<div id="mapWrapper">
	  <div id="draggable" class="ui-widget-content">          
	      <span id="zomIn" onclick="toggleControl(this)" class="icon" title="放大"></span>
	      <span id="zomOut" onclick="toggleControl(this)" class="icon" title="缩小"></span>               
	      <span id="pan" onclick="toggleControl(this)" class="icon" title="漫游"></span>
	      <span id="mearuePolygon" class='icon' title="面积测量" onclick="drawControl(this);"></span>
	      <span id="mearueLine" class='icon' title="距离测量" onclick="drawControl(this);"></span>
	  </div>
	  <!--左上角按钮-->
		<div class="uiWidget thematic-zs" style="top: 15px; left: 15px;">			
			<div class="item">
				<div class="block" id="home_bt">
					<img src="${ctx}/static/images/map/waterresupply.png">
					<span>首页</span>				 
				</div>		
			</div>
			<div class="item">
				<div class="block" id="map_bt">
					<img src="${ctx}/static/images/map/map.png">
					<span>地图浏览</span>				 
				</div>		
			</div>
			<div class="item">
				<div class="block" id="info_bt">
					<img src="${ctx}/static/images/map/event.png">
					<span>单位信息管理</span>
					 
				</div>		
			</div>
			<div class="item">
				<div class="block" id="ysjh_bt">
					<img src="${ctx}/static/images/map/product.png">
					<span>用水计划管理</span>
					 
				</div>
		
			</div>			
			<div class="item">
				<div class="block" id="ysgl_bt">
					<img src="${ctx}/static/images/map/monitor.png">
					<span>用水数据管理</span>					 
				</div>			
			</div>
		</div>
	  <div id="measurePol"></div>
	</div>

</div>

</body>


</html>