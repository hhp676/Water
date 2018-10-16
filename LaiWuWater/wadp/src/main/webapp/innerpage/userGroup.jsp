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
  	 <table title="用户组列表" class="easyui-treegrid"
            data-options="
                url: '<%=path%>/innerpage/usergroup.json',
                method: 'get',
                rownumbers: true,
                idField: 'id',
                treeField: 'name',
                showFooter:true,
                toolbar:'#userGroup_toolbar',
                fitColumns:true
             
            ">
        <thead>
            <tr>
                <th data-options="field:'name'" width="300px">名称</th>
                <th data-options="field:'persons'" width="100px">所属部门</th>
                <th data-options="field:'position'" width="200px">描述</th>
 
            </tr>
        </thead>
    </table>
    <div id="userGroup_toolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-group-add" plain="true" tag="add">添加</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-group-edit" plain="true" tag="edit">修改</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-group-del" plain="true" tag="del">删除</a>
       
    </div>
    

	<script type="text/javascript">
		
		$("#userGroup_toolbar [tag='add']").click(function(){
			var iconCls = $(this).attr("iconCls");
			var userGroupWin = new HgWin({id:"userGroupDetailWin",width:800,height:400,title:"添加用户组",iconCls:iconCls,url:"/innerpage/userGroupDetail.jsp"});
		});
		
		
	</script>
  </body>

</html>
