<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户组人员统计详情</title>
</head>
<body>
	<div title="用户组人员统计列表" style="width:100%;height:100%">
		<table id="sysUserGroupUserCountDetail_grid" singleSelect=true fit=true fitColumns=true  toolbar='#userCountDetailGroup_toolbar' class="easyui-datagrid"  
            data-options="
            		url:'${ctx}/sys/userGroup/getUserDetailByGroupId?groupId=${groupId}'" 
            		pagination="true" 
            		pageSize="10"
            		pageList="[10,20,30,40,50]">
	      <thead>
		      <tr>
		      	  <th data-options="field:'userName',width:20">姓名</th>
			      <th data-options="field:'mainDepartNamesStr',width:20">部门</th>
			      <th data-options="field:'secondDepartNamesStr',width:20">域</th>
<!-- 			      <th data-options="field:'entryDateStr',width:20">入职日期</th> -->
<!-- 			      <th data-options="field:'statusStr',width:20">状态</th> -->
			  </tr>
		  </thead>
		</table>
		<div id="userCountDetailGroup_toolbar">
		<div class="gridSearchBar">
			<form id="sysUserGroupUserCountDetailSearchForm">
				<fieldset>
					<legend>高级查询</legend>
					<table>
						<tr>
							<td width="120px" align="right"><span>姓名:</span></td>
							<td><input name="username"></td>
						</tr>
					</table>
				</fieldset>
			</form>
			<div style="position: absolute; right: 20px; top: 20px; *top: 30px;">
				<a class="easyui-linkbutton" iconCls="m-icon-search" tag="search">查询</a>&nbsp;
				<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear">清空</a>
			</div>
		</div>      		
    	</div>   	
	</div>
      <script>
      var groupId = "${groupId}";
        //----------------------------------查询-----------------------------------------------------
		$("#userCountDetailGroup_toolbar [tag='search']").click(function(){
				$('#sysUserGroupUserCountDetail_grid').datagrid('load',{				 
					userName: $.trim($("#sysUserGroupUserCountDetailSearchForm [name='username']").val()) 
			 });
		});
		
		//----------------------------------清空-----------------------------------------------------
		$("#userCountDetailGroup_toolbar [tag='clear']").click(function(){
			$('#sysUserGroupUserCountDetailSearchForm').form('clear');
			$('#sysUserGroupUserCountDetail_grid').datagrid("load",{});
		});
      </script>
</body>
</html>