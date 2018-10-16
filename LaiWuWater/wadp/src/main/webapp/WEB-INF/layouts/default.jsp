<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<script type="text/javascript">
	G_COUNT = 0;
</script>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>

<meta charset="utf-8" />
<title>应用管理平台</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link type="image/x-icon" href="${ctx}/favicon.png" rel="shortcut icon">

<!-- basic styles -->
<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css" />

<!--[if IE 7]>
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome-ie7.min.css" />
    <![endif]-->

<!-- page specific plugin styles -->
<link rel="stylesheet"
	href="${ctx}/static/css/jquery-ui-1.10.3.full.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/jquery.gritter.css" />
<link rel="stylesheet"
	href="${ctx}/static/css/jquery-ui-1.10.3.full.min.css" />

<!-- fonts -->

<!-- ace styles -->
<link rel="stylesheet" href="${ctx}/static/css/ace.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/ace-skins.min.css" />

<!--[if lte IE 8]>
    <link rel="stylesheet" href="${ctx}/static/css/ace-ie.min.css" />
    <![endif]-->

<!-- inline styles related to this page -->
<link rel="stylesheet" href="${ctx}/static/css/style.css" />

<!-- ace settings handler -->
<script src="${ctx}/static/js/ace-extra.min.js"></script>

<script src="${ctx}/static/js/jquery-2.2.4.js"></script>
<script src="${ctx}/static/js/jquery.json-2.4.js"></script>
<script src="${ctx}/static/js/main.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
    <script src="${ctx}/static/js/html5shiv.js"></script>
    <script src="${ctx}/static/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="shade"></div>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>
		<div class="navbar-container" id="navbar-container">
			<%@ include file="/WEB-INF/layouts/header.jsp"%>
		</div>
		<!-- /.container -->
	</div>
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>
		<div class="main-container-inner">
			<a class="menu-toggler hidden-print" id="menu-toggler" href="#">
				<span class="menu-text"></span>
			</a>
			<div class="sidebar hidden-print" id="sidebar">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<a class="btn btn-success" href="#"><i class="icon-file-alt"></i></a> 
						<a class="btn btn-info" href="#"> <i class="icon-pencil"></i></a> 
						<a class="btn btn-warning" href="#"> <i class="icon-group"></i></a>
						<a class="btn btn-danger" href="#"> <i class="icon-calendar"></i></a>
					</div>

					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span>

						<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
					</div>
				</div>
				<!-- #sidebar-shortcuts -->
				<ul class='nav nav-list'>
					<%@ include file="/WEB-INF/layouts/left.jsp"%>
				</ul>
				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
					try {
						if (ace.settings.is("sidebar", "collapsed")) {
							ace.settings.sidebar_collapsed(true);
						} else {
							ace.settings.sidebar_collapsed(false);
						}
					} catch (e) {
					}
				</script>
			</div>

			<div class="main-content">
				<div class="breadcrumbs hidden-print" id="breadcrumbs">
					<script type="text/javascript">
						try {
							ace.settings.check('breadcrumbs', 'fixed')
						} catch (e) {
						}
					</script>

					<ul class="breadcrumb">
						<%@ include file="/WEB-INF/layouts/navigation.jsp"%>
					</ul>
					<!-- .breadcrumb -->
				</div>
				<div class="page-content Home">
					<div class="row">
						<div class="col-xs-12">
							<sitemesh:body />
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->
			<div class="ace-settings-container hidden-print"
				id="ace-settings-container">
				<div class="btn btn-app btn-xs btn-warning ace-settings-btn"
					id="ace-settings-btn">
					<i class="icon-cog bigger-150"></i>
				</div>

				<div class="ace-settings-box" id="ace-settings-box">
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hide">
								<option data-skin="default" value="#438EB9">#438EB9</option>
								<option data-skin="skin-1" value="#222A2D">#222A2D</option>
								<option data-skin="skin-2" value="#C6487E">#C6487E</option>
								<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; Choose Skin</span>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-navbar" /> <label class="lbl"
							for="ace-settings-navbar">Fixed Navbar</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-sidebar" /> <label class="lbl"
							for="ace-settings-sidebar">Fixed Sidebar</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-breadcrumbs" /> <label class="lbl"
							for="ace-settings-breadcrumbs">Fixed Breadcrumbs</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-rtl" /> <label class="lbl"
							for="ace-settings-rtl">Right To Left (rtl)</label>
					</div>

					<div>
						<input type="checkbox" class="ace ace-checkbox-2"
							id="ace-settings-add-container" /> <label class="lbl"
							for="ace-settings-add-container"> Inside <b>.container</b>
						</label>
					</div>
				</div>
			</div>
			<!-- /#ace-settings-container -->
		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->
	<div id="push_msg"></div>
	<!-- <iframe src="/smeoa/index.php?m=push&a=client" class="push" id="push"></iframe> -->
	<!-- basic scripts -->


	<!--[if IE]>
<script type="text/javascript">
        window.jQuery || document.write("<script src='${ctx}/static/js/jquery-1.12.4.js'>"+"<"+"/script>");</script>
<![endif]-->
	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='${ctx}/static/js/jquery.mobile.custom.min.js'>"
							+ "<" + "/script>");
	</script>
	<script src="${ctx}/static/js/bootstrap.min.js"></script>
	<script src="${ctx}/static/js/typeahead-bs2.min.js"></script>

	<!-- page specific plugin scripts -->
	<script src="${ctx}/static/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script src="${ctx}/static/js/jquery.ui.touch-punch.min.js"></script>

	<script src="${ctx}/static/js/jquery.gritter.min.js"></script>
	<script src="${ctx}/static/js/bootbox.min.js"></script>

	<!-- ace scripts -->
	<script src="${ctx}/static/js/ace-elements.min.js"></script>
	<script src="${ctx}/static/js/ace.min.js"></script>
	<script src="${ctx}/static/js/common.js"></script>
	<script src="${ctx}/static/js/activeTimeout.js"></script>
	<script type="text/javascript">
	$(function() {
       activeTimeoutObject.configValue = "${sysConfActiveTimeout.configValue}";
       var activeTimeoutNumber = parseInt(activeTimeoutObject.configValue);
       if (!isNaN(activeTimeoutNumber) && activeTimeoutNumber > 0){
           activeTimeoutObject.init(activeTimeoutNumber);
           activeTimeoutObject.intervalID = window.setInterval(activeTimeoutObject.intervalFunction, 30*1000);
       }
	});
    </script>
	
	
	<script type="text/javascript">
	</script>
	<!-- inline scripts related to this page -->
</body>
</html>