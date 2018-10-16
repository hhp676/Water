<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Viewport metatags -->
<meta name="HandheldFriendly" content="true" />
<meta name="MobileOptimized" content="320" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/error/dandelion.css"  media="screen" />
<title>403 - 无权限</title>
<style  >
body{background-image:url("${ctx}/static/images/error/blueprint.png")}
</style>
</head>
<body>
	<!-- Main Wrapper. Set this to 'fixed' for fixed layout and 'fluid' for fluid layout' -->
	<div id="da-wrapper" class="fluid">
        <!-- Content -->
        <div id="da-content">
            <!-- Container -->
            <div class="da-container clearfix">
            	<div id="da-error-wrapper">
                   	<div id="da-error-pin"></div>
                    <div id="da-error-code">
                    	无权限访问 <span>403</span>
                    </div>
                	<h1 class="da-error-heading">报告问题，请联系管理员！</h1>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
