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
  	 <table title="用户列表" class="easyui-treegrid" fitColumns=true
            data-options="
                url: '<%=path%>/innerpage/userdata.json',
                method: 'get',
                rownumbers: true,
                idField: 'id',
                treeField: 'name',
                showFooter:true,
                toolbar:'#user_toolbar'
            ">
        <thead>
            <tr>
                <th data-options="field:'name'" width="300">名称</th>
                <th data-options="field:'loginName'" width="100">登录名</th>
                <th data-options="field:'sex'">性别</th>
                <th data-options="field:'mobile'">电话号码</th>
                <th data-options="field:'email'" width="150">email</th>
            </tr>
        </thead>
    </table>
    <div id="user_toolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-person-add" plain="true" tag="add">添加</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-person-edit" plain="true" tag="edit">修改</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-person-del" plain="true" tag="del">删除</a>
    </div>
    
    
    
    
    
    <div id="user_contextmenu" class="easyui-menu" style="width:120px;">
		<div  iconCls="m-icon-tag-green" tag="setAuth">设置权限</div>
		<div  iconCls="m-icon-star" tag="setRole">设置角色</div>
	</div>
	
	<div id="user_setAuth_win" class="easyui-window" title="设置权限" data-options="modal:true,closed:true,minimizable:false,maximizable:false,collapsible:false,iconCls:'m-icon-win'" style="width:500px;height:450px">
		<div  class="easyui-tabs" fit=true tabPosition="right">
			<div title="菜单权限" style="padding:2px">
            	<table title="" fit=true class="easyui-treegrid"  singleSelect=false fitColumns=true data-options="url: '<%=path%>/innerpage/moduleauth.json',method: 'get',idField: 'id',treeField: 'name'">
        			<thead>
            			<tr>
            				<th data-options="field:'ck',checkbox:true"></th>
            				<th data-options="field:'type'">权限类型</th>
                			<th data-options="field:'name'">权限名称</th>              			
            			</tr>
        			</thead>
    			</table>
            </div>
        	<div title="操作权限" style="padding:2px">
            
        	</div>
        	<div title="应用权限" style="padding:2px">
            
        	</div>
		</div>
	</div>
	
	
	
	
	<script type="text/javascript">
		function onContextMenu(e,row){
        	e.preventDefault();
        	$(this).treegrid('select', row.id);
        	$('#user_contextmenu').menu('show',{
            	left: e.pageX,
            	top: e.pageY
        	});
    	}
		$("#user_contextmenu [tag='setAuth']").click(function(){
			var iconCls = $(this).attr("iconCls");
			var win = new HgWin({id:"user_setAuth_win",iconCls:iconCls});
			
		});
		$("#user_contextmenu [tag='setRole']").click(function(){
			var iconCls = $(this).attr("iconCls");
			$("#user_setRole_win").window({iconCls:iconCls}).window("open");
			
		});
		$("#user_toolbar [tag='add']").click(function(){
			var iconCls = $(this).attr("iconCls");
			var iconCls = $(this).attr("iconCls");
			var userDetailWin = new HgWin({id:"userDetailWin",width:800,height:430,title:"添加用户",iconCls:iconCls,url:"/innerpage/userDetail.jsp"});
		});
		
	</script>
  </body>

</html>
