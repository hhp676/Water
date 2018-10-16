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
  	 <table title="权限列表" fit=true class="easyui-treegrid" toolbar="#auth_toolbar"  rownumbers=true fitColumns=true data-options="url: '<%=path%>/innerpage/auth.json',method: 'get',idField: 'id',treeField: 'name'">
        <thead>
            <tr>
               
                <th data-options="field:'name'" width="150px">权限名称</th>
                <th data-options="field:'type'">权限类型</th>
                <th data-options="field:'code'">code</th>
                <th data-options="field:'uri'" width="100px">uri</th>
                <th data-options="field:'orderId'">排序id</th>
                <th data-options="field:'isFinal'">不可修改</th>
                <th data-options="field:'descr'" width="150px">描述</th>                  			
            </tr>
        </thead>
    </table>
   
    <div style="padding:5px;border:1px solid #ddd" id="auth_toolbar">
      	<a href="#" class="easyui-menubutton" data-options="menu:'#auth_toolbar_menu',iconCls:'m-icon-tag-add'">添加</a>
       	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-tag-edit" plain="true" tag="edit">修改</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-tag-del" plain="true" tag="del">删除</a>
    </div>
    <div id="auth_toolbar_menu" style="width:100px;">
        <div iconCls="m-icon-tag-green" tag="moduleAuth">模块权限</div>
        <div iconCls="m-icon-tag-green" tag="moduleAuth">操作权限</div>
        <div iconCls="m-icon-tag-green" tag="moduleAuth">应用权限</div>
    </div>
    

	<script type="text/javascript">
		
		$("#auth_toolbar_menu [tag='moduleAuth']").click(function(){
			var iconCls = $(this).attr("iconCls");
			var moduleAuthWin = new HgWin({id:"moduleAuthWin",title:"添加模块权限",iconCls:iconCls,url:"/innerpage/moduleAuthDetail.jsp"});
		});
		
		
	</script>
  </body>

</html>
