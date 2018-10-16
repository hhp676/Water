<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色列表页面</title>	
  </head>
  <body>
      <div title="角色列表">
      	 <table  id="sysRole_grid" singleSelect=true  fitColumns=true  toolbar='#role_toolbar' class="easyui-datagrid" 
       	 	data-options="url:'${ctx}/sys/role/list',
       	 				onRowContextMenu:sysRole.onRowContextMenu,
       	 				onDblClickRow:sysRole.onDblClickRow" 
       	 				pagination="true" 
       	 				pageSize="${defaultPageSize}" 
       	 				pageList="${defaultPageList}">
	      <thead>
		      <tr>
		      	  <th data-options="field:'roleId',width:20">角色Id</th>
			      <th data-options="field:'roleName',width:80">角色名称</th>
			      <th data-options="field:'roleCode'">角色code</th>
			      <th data-options="field:'isFinalStr'">不可修改</th>
				  <th data-options="field:'descr',width:150">角色描述</th>

			  </tr>

		  </thead>
		</table>
		<div id="role_toolbar" style="display: none;">
    	<div class="gridSearchBar">
			<form id="sysRoleSearchForm">
				<fieldset>
					<legend>高级查询</legend>
					<table>
						<tr>
							<td width="120px" align="right"><span>角色名称:</span></td>
							<td><input name="roleName"></td>
							<td width="120px" align="right"><span>角色code:</span></td>
							<td><input name="roleCode"></td>
						</tr>
					</table>
				</fieldset>
			</form>
			<div style="position: absolute; right: 10px; top: 20px; *top: 30px;">
				<a class="easyui-linkbutton" iconCls="m-icon-search" tag="search">查询</a>
				<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear">清空</a>
			</div>
		</div>
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-star-add" plain="true" tag="add">添加</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-star-edit" plain="true" tag="edit">修改</a>
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-star-del" plain="true" tag="del">删除</a>
       		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
    	</div>   	
      </div>
      <div id="sysRole_contextMenu" class="easyui-menu" style="width: 120px;">
  		<shiro:hasPermission name="<%= Auths.SYS_ROLE_ADD %>"><div iconCls="m-icon-star-add" tag="add">添加</div></shiro:hasPermission>
  		<shiro:hasPermission name="<%= Auths.SYS_ROLE_EDIT %>"><div iconCls="m-icon-star-edit" tag="edit">修改</div></shiro:hasPermission>
	    <shiro:hasPermission name="<%= Auths.SYS_ROLE_DELETE %>"><div iconCls="m-icon-star-del" tag="del">删除</div></shiro:hasPermission>		
	</div>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysRole_show.js"></script>
  </body>

</html>
