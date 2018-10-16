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
  	
	<div style="margin:10px 0;">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#w').window('open')">对话框</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#w').window('close')">xx</a>
    </div>
    <div id="w" class="easyui-window" title="选择" data-options="modal:true,closed:true" style="width:500px;height:200px;padding:10px;">
       <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'center'" style="padding:10px;">
               hello
            </div>
            <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
                <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="javascript:alert('ok')">保存</a>
                <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="$('#w').window('close')">取消</a>
            </div>
        </div>
    </div>
  </body>

</html>
