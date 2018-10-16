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
      <table title="角色列表" rownumbers=true fitColumns=true  toolbar='#role_toolbar' class="easyui-datagrid"  data-options="url:'<%=path%>/innerpage/role.json',method:'get'">
	      <thead>
		      <tr>
			      <th data-options="field:'name',width:80">角色名称</th>
			      <th data-options="field:'code'">角色code</th>
			      <th data-options="field:'isFinal'">不可修改</th>
				  <th data-options="field:'desc',width:150">角色描述</th>

			  </tr>

		  </thead>
	</table>
  	
    <div id="role_toolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add1" plain="true" tag="add">添加</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit1" plain="true" tag="edit">修改</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-del1" plain="true" tag="del">删除</a>
       
    </div>
    

	<script type="text/javascript">
		
		$("#role_toolbar [tag='add']").click(function(){
			var iconCls = $(this).attr("iconCls");
			var userGroupWin = new HgWin({id:"roleDetailWin",title:"添加角色",width:700,height:380,iconCls:iconCls,url:"/innerpage/roleDetail.jsp"});
		});
		
		
	</script>
  </body>

</html>
