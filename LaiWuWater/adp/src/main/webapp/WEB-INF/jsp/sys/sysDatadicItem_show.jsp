<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>字典项列表页面</title>
</head>
<body>
	<div title="数据字典项列表">
		<table id="sysDatadicItem_grid" striped=true fitColumns=true rownumbers="true"
			toolbar='#sysDatadicItem_toolbar' class="easyui-datagrid"
			data-options="
        		url: '${ctx}/sys/datadic/item/list',
                singleSelect:true,
                collapsible:true,
                view:groupview,
                groupField:'groupCode',
                onRowContextMenu:sysDatadicItem.onRowContextMenu,
                onLoadSuccess:sysDatadicItem.onLoadSuccess,
                onDblClickRow: sysDatadicItem.onDblClickRow,
                groupFormatter:function(value,rows){
                    return '<b>'+value + ' - ' + rows.length + ' 条</b>';
                }
            "
			pagination="true" pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
				<tr>
					<th data-options="field:'itemId',width:30,hidden:true">字典项Id</th>
					<th data-options="field:'itemCode',width:80">字典项code</th>
					<th data-options="field:'itemValue',width:60">字典项值</th>
					<th data-options="field:'itemName',width:80">字典项名称</th>
					<th data-options="field:'itemDesc',width:100">描述</th>
					<th data-options="field:'orderId',width:60">排序id</th>
					<th data-options="field:'groupCode',width:80">所属字典组code</th>
					<th data-options="field:'groupName',width:80">所属字典组名称</th>
					<th data-options="field:'isFinalStr',width:60">不可修改</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="sysDatadicItem_toolbar" class="sysVarbar">
		<shiro:hasPermission name="<%= Auths.SYS_DATADIC_ADD %>">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add1" plain="true" tag="add">添加</a>
		</shiro:hasPermission> 
		<shiro:hasPermission name="<%= Auths.SYS_DATADIC_EDIT %>">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit1" plain="true" tag="edit">修改</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="<%= Auths.SYS_DATADIC_DELETE %>">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-del1" plain="true" tag="del">删除</a>
		</shiro:hasPermission> 
		<shiro:hasPermission name="<%= Auths.SYS_DATADIC_VIEW %>">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
		</shiro:hasPermission> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-app" plain="true" tag="datadicGroup">字典组配置</a>


	</div>
	<div id="sysDatadicItem_contextMenu" class="easyui-menu" style="width: 120px;">
		<shiro:hasPermission name="<%= Auths.SYS_DATADIC_ADD %>">
			<div data-options="iconCls:'icon-add1'" tag="add">添加</div>
		</shiro:hasPermission>
		<shiro:hasPermission name="<%= Auths.SYS_DATADIC_EDIT %>">		
			<div data-options="iconCls:'icon-edit1'" tag="edit">修改</div>
		</shiro:hasPermission>
		<shiro:hasPermission name="<%= Auths.SYS_DATADIC_DELETE %>">
			<div data-options="iconCls:'icon-del1'" tag="del">删除</div>
		</shiro:hasPermission>
	</div>

	<script type="text/javascript"
		src="${ctx}/static/js/sys/sysDatadicItem_show.js"></script>
</body>

</html>
