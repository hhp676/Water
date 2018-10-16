<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
</head>
<body>
  	<div class="easyui-layout" fit=true  id="sysUserRoleJoin_tab">
  	
	  	<div class="easyui-panel" data-options="fit:true">
	   		<table  id="sysUserRoleJoin_grid" class="easyui-datagrid" rownumbers="true"  fit=true 
						 fit=true  fitColumns="true" toolbar='#sysUserRoleJoin_toolbar' singleSelect="true" showFooter="true"
						 pagination="true" pageSize="${defaultPageSize}" pageList="${defaultPageList}"
	       				data-options="url:'${ctx}/sys/role/userByRoleList/${roleId}'">
		      <thead>
			       <tr>
			      	  <th data-options="field:'userName',width:30">姓名</th>
			      	  <th data-options="field:'departName',width:30">部门</th>
			      	  <th data-options="field:'areaName',width:30">域</th>
<!-- 				      <th data-options="field:'entryDateStr',width:80">入职日期</th> -->
<!-- 				      <th data-options="field:'statusStr',width:30">状态</th> -->
				  </tr>
		  		</thead>
			</table>
		</div>
		
	</div>
	<div id="sysUserRoleJoin_toolbar">
		<div class="gridSearchBar">
				<form id="sysUserRoleJoinSearchForm">
					<fieldset>
						<legend>高级查询</legend>
						<table>
							<tr>
								<td width="60px" align="right"><span>姓名:</span></td>
								<td><input name="userNames"   ></td>
								<!--  <td  width="60px" align="right"><span>入职日期:</span></td>
								<td>
									<input name="entryDateBegin" class="Wdate" style="width: 100px;" id="contractSearchForm_start" value=""
									onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'contractSearchForm_end\',{d:0});}'})">
								</td>
								<td align="center">
									---
								</td>
								<td>	
									<input name="entryDateEnd" class="Wdate" style="width: 100px;" id="contractSearchForm_end" value=""
									onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'contractSearchForm_start\',{d:0});}'})">
								</td>-->
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
		  <script>
		  
				  $("#sysUserRoleJoin_toolbar [tag='search']").click(function(){
						 $('#sysUserRoleJoin_grid').datagrid('load',{
							 userName:  $.trim($("#sysUserRoleJoinSearchForm [name='userNames']").val())
						 });
						 
					});
					$("#sysUserRoleJoin_toolbar [tag='clear']").click(function(){
						$('#sysUserRoleJoinSearchForm').form('clear');
						$('#sysUserRoleJoin_grid').datagrid("load",{});
					});
		  
		  </script>
  </body>
 
</html>
