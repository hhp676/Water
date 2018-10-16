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
  	 <table title="部门列表" class="easyui-treegrid"
            data-options="
                url: '<%=path%>/innerpage/department.json',
                method: 'get',
                rownumbers: true,
                idField: 'id',
                treeField: 'name',
                showFooter:true,
                toolbar:'#department_toolbar',
                fitColumns:true
             
            ">
        <thead>
            <tr>
                <th data-options="field:'name'" width="300px">部门名称</th>
                <th data-options="field:'fullname'" width="100px">部门全名</th>
                <th data-options="field:'code'" width="200px">code</th>
                <th data-options="field:'isFinal'" width="200px">不可修改</th>
 
            </tr>
        </thead>
    </table>
    <div id="department_toolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add1" plain="true" tag="add">添加</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit1" plain="true" tag="edit">修改</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-del1" plain="true" tag="del">删除</a>
       
    </div>
    

	<script type="text/javascript">
		
		$("#department_toolbar [tag='add']").click(function(){
			var iconCls = $(this).attr("iconCls");
			var departmentDetailWin = new HgWin({id:"departmentDetailWin",width:480,height:280,title:"添加部门",iconCls:iconCls,url:"/innerpage/departmentDetail.jsp"});
		});
		
		
	</script>
  </body>

</html>
