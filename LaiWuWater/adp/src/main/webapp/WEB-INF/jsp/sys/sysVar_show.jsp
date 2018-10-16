<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML >
<html>
<head>
<title>系统变量列表页面</title>
</head>
<body>
	<div title="系统变量列表">
		<table striped=true id="sysVar_grid" singleSelect=true fitColumns=true rownumbers="true"
			toolbar='#sysVar_toolbar' class="easyui-datagrid"
			data-options="
            		url:'${ctx}/sys/var/list',
            		onRowContextMenu:sysVar.onRowContextMenu,
            		onLoadSuccess:sysVar.onLoadSuccess,
            		onDblClickRow:sysVar.onDblClickRow"
			pagination="true" pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
				<tr>
					<th data-options="field:'varName',width:100">变量名称</th>
					<th data-options="field:'varValue',width:100">变量值</th>
					<th data-options="field:'varType',width:100">变量类型</th>
					<th data-options="field:'descr',width:180">描述</th>
					<th data-options="field:'isFinalStr',width:60">不可修改</th>
					<th data-options="field:'varId',width:30,hidden:true">变量ID</th>
				</tr>
			</thead>
		</table>
	</div>


	<div id="sysVar_toolbar" class="sysVarbar">
		<div class="gridSearchBar" style="height: 40px;">
			<form id="wifiVarSearchForm">
					<table width="100%" height="100%">
						<tr>
							<td width="60px" align="right"><span>变量ID:</span></td>
							<td width="120px;"><input name="varId"></td>
							<td width="120px" align="right"><span>变量名称:</span></td>
							<td><input name="varName"></td>
							<td style="text-align: right;padding-top:4px;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align:80%;">查询</a>
							&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align:80%;">清空</a>
							</td>
						</tr>
					</table>
			</form>
		</div>
		<shiro:hasPermission name="<%=Auths.SYS_VARIABLE_ADD%>">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add1" plain="true" tag="add">添加</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="<%=Auths.SYS_VARIABLE_EDIT%>">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-edit1" plain="true" tag="edit">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="<%=Auths.SYS_VARIABLE_DELETE%>">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-del1" plain="true" tag="del">删除</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="<%=Auths.SYS_VARIABLE_VIEW%>">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
		</shiro:hasPermission>
	</div>
	<div id="sysVar_contextMenu" class="easyui-menu" style="width: 120px;">
		<shiro:hasPermission name="<%= Auths.SYS_VARIABLE_ADD %>">
		    <div iconCls="icon-add1" tag="add">添加</div>
		</shiro:hasPermission>	
		<shiro:hasPermission name="<%= Auths.SYS_VARIABLE_EDIT %>">
		    <div iconCls="icon-edit1" tag="edit">修改</div>
		</shiro:hasPermission>
		<shiro:hasPermission name="<%= Auths.SYS_VARIABLE_DELETE %>">
		    <div iconCls="icon-del1" tag="del">删除</div>
		</shiro:hasPermission>
	</div>
	<script type="text/javascript"
		src="${ctx}/static/js/sys/sysVar_show.js"></script>
</body>

</html>
