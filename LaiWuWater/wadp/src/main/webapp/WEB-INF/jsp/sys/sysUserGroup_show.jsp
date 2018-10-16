<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户组页面</title>	
  </head>
  <body>
      <div title="用户组列表">
      	 <table  id="sysUserGroup_grid" singleSelect=true  fitColumns=true  toolbar='#userGroup_toolbar' data-options="url:'${ctx}/sys/userGroup/list',idField: 'groupId',treeField:'groupName'">
	      <thead>
		      <tr>
		      	  <th data-options="field:'groupId',width:20">用户组Id</th>
			      <th data-options="field:'groupName',width:80">用户组名称</th>
			      <th data-options="field:'groupCode'">用户组code</th>
			      <th data-options="field:'departName'">所属部门</th>
			      <th data-options="field:'isFinalStr'">不可修改</th>
				  <th data-options="field:'descr',width:150">描述</th>

			  </tr>

		  </thead>
		</table>
		<div id="userGroup_toolbar">
		<div class="gridSearchBar">
			<form id="sysUserGroupSearchForm">
				<fieldset>
					<legend>高级查询</legend>
					<table>
						<tr>
							<td width="120px" align="right"><span>用户组名称:</span></td>
							<td><input name="groupName"></td>
							<td width="120px" align="right"><span>用户组code:</span></td>
							<td><input name="groupCode"></td>
						</tr>
					</table>
				</fieldset>
			</form>
			<div style="position: absolute; right: 10px; top: 20px; *top: 30px;">
				<a class="easyui-linkbutton" iconCls="m-icon-search" tag="search">查询</a>
				<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear">清空</a>
			</div>
		</div>
    		<shiro:hasPermission name="<%= Auths.SYS_USERGROUP_ADD%>">
    			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-group-add" plain="true" tag="add">添加</a>
    		</shiro:hasPermission>
    		<shiro:hasPermission name="<%= Auths.SYS_USERGROUP_EDIT %>">
    			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-group-edit" plain="true" tag="edit">修改</a>
    		</shiro:hasPermission>
    		<shiro:hasPermission name="<%= Auths.SYS_USERGROUP_DELETE %>">
    			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-group-del" plain="true" tag="del">删除</a>
    		</shiro:hasPermission>
    		<shiro:hasPermission name="<%= Auths.SYS_USERGROUP_VIEW %>">
    			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
    		</shiro:hasPermission>
       		
    	</div>   	
      </div>
      <div id="userGroup_contextMenu" class="easyui-menu" style="width: 120px;">
  		<shiro:hasPermission name="<%= Auths.SYS_USERGROUP_ADD %>"><div iconCls="icon-add1" tag="add">添加</div></shiro:hasPermission>
  		<shiro:hasPermission name="<%= Auths.SYS_USERGROUP_EDIT %>"><div iconCls="icon-edit1" tag="edit">修改</div></shiro:hasPermission>
	    <shiro:hasPermission name="<%= Auths.SYS_USERGROUP_DELETE %>"><div iconCls="icon-del1" tag="del">删除</div></shiro:hasPermission>		
	</div>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysUserGroup_show.js"></script>
  </body>

</html>
