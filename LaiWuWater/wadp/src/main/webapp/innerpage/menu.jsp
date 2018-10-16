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
  	 <div style="padding:5px;border:1px solid #ddd">
        <a href="#" class="easyui-linkbutton" data-options="plain:true">菜单1</a>
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm1'">菜单2</a>
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm2'">菜单3</a>
    </div>
    <div id="mm1" style="width:150px;">
        <div data-options="iconCls:'icon-undo'">Undo</div>
        <div data-options="iconCls:'icon-redo'">Redo</div>
        <div class="menu-sep"></div>
        <div>Cut</div>
        <div>Copy</div>
        <div>Paste</div>
        <div class="menu-sep"></div>
        <div>
            <span>Toolbar</span>
            <div style="width:150px;">
                <div>Address</div>
                <div>Link</div>
                <div>Navigation Toolbar</div>
                <div>Bookmark Toolbar</div>
                <div class="menu-sep"></div>
                <div>New Toolbar...</div>
            </div>
        </div>
        <div data-options="iconCls:'icon-remove'">Delete</div>
        <div>Select All</div>
    </div>
    <div id="mm2" style="width:100px;">
        <div>Help</div>
        <div>Update</div>
        <div>About</div>
    </div>

 <script type="text/plain" id="myEditor" style="width:800px;height:240px;">
        <p>这里我可以写一些输入提示</p>
    </script>
	<form>
		<div id="queue"></div>
		<input id="file_upload" name="file_upload" type="file" multiple="true">
	</form>
    <script type="text/javascript">
       
		
		$(function() {
			 var ue = UE.getEditor('myEditor',{
		            //这里可以选择自己需要的工具按钮名称,此处仅选择如下七个
		            toolbar:['fullscreen source undo redo bold italic underline'],
		            //focus时自动清空初始化时的内容
		            autoClearinitialContent:true,
		            //关闭字数统计
		            wordCount:false,
		            //关闭elementPath
		            elementPathEnabled:false,
		            //默认的编辑区域高度
		            initialFrameHeight:300
		            //更多其他参数，请参考umeditor.config.js中的配置项
		        });
			
			
	$("#file_upload").uploadify({
		height        : 30,
		swf           : '/lib/js/uploadify/uploadify.swf',
		uploader      : '/uploadify/uploadify.php',
		width         : 120
	});
});
    </script>
  	
  </body>

</html>
