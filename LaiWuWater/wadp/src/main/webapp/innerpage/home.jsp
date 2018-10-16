<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ajax</title>	
  </head>
  <body>
  	<div style="width:900px;">
	<div style="float:left;">
		<div class="easyui-draggable" style="width:400px;height:200px;background:#fafafa;border:1px solid #ccc">
		   <div id="title" style="padding:5px;background:#ccc;color:#fff">最新动态</div>
		</div>
        <div  class="easyui-draggable" style="width:400px;height:200px;background:#fafafa;border:1px solid #ccc;margin-top:10px">
          <div id="title" style="padding:5px;background:#ccc;color:#fff">公告栏</div>
		</div>
    </div>
	<div style="float:left;margin-left:10px;">
		<div  class="easyui-draggable" style="width:400px;height:200px;background:#fafafa;border:1px solid #ccc">
		 <div id="title" style="padding:5px;background:#ccc;color:#fff">排行榜</div>
		</div>
        <div class="easyui-draggable"  style="width:400px;height:200px;background:#fafafa;border:1px solid #ccc;margin-top:10px">
        <div id="title" style="padding:5px;background:#ccc;color:#fff">报告</div>
    </div>
	</div>
	</div>
  </body>

</html>
