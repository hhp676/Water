<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户组页面</title>	
  </head>
  <body>
      <div title="用户组列表">
      	 <table  id="sysUserGroup_grid" singleSelect=true  fit=true  fitColumns=false  toolbar='#userGroup_toolbar' rownumbers="true"
      	  data-options="url:'${ctx}/sys/userGroup/list?number='+ new Date().getTime(),
      	  onLoadSuccess:sysUserGroupOnLoadSuccess,
      	  idField: 'groupId',treeField:'groupName'">
	      <thead>
		      <tr>
			      <th data-options="field:'groupName',width:270">用户组名称</th>
			      <th data-options="field:'groupCode',width:190">用户组code</th>
			      <th data-options="field:'departName',width:150">所属部门</th>
			      <th data-options="field:'userCount',width:110,formatter:sysUserGroup.showSysUserGroupUserCountFormatter">人员统计</th>
			      <th data-options="field:'isFinalStr',width:120">不可修改</th>
				  <th data-options="field:'descr',width:260">描述</th>
		      	  <th data-options="field:'groupId',width:40,hidden:true">用户组Id</th>

			  </tr>

		  </thead>
		</table>
		<div id="userGroup_toolbar">
		<div class="gridSearchBar" style="height: 40px;">
			<form id="sysUserGroupSearchForm">
					<table width="100%" height="100%">
						<tr>
							<td width="70px" align="right"><span>用户组名称:</span></td>
							<td width="120px;"><input name="groupName"></td>
							<td width="120px" align="right"><span>用户组code:</span></td>
							<td><input name="groupCode"></td>
							<td style="text-align: right;padding-top:4px;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align:80%;">查询</a>
							&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align:80%;">清空</a>
							</td>
						</tr>						
					</table>
			</form>
			<div style="position: absolute; right: 10px; top: 20px; *top: 30px;">
				
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
