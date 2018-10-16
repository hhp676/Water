<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>通知列表页面</title>
</head>
<body>
	<div title="用户通知列表">
		<table striped=true id="sysNotify_grid" singleSelect=true rownumbers="true"
			fitColumns=true toolbar='#sysNotify_toolbar' class="easyui-datagrid"
			data-options="
            		url:'${ctx}/sys/notify/list',
            		onDblClickRow:sysNotify.onDblClickRow,
            		onLoadSuccess:sysNotify.onLoadSuccess
            		"
			pagination="true" pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
				<tr>
					<th data-options="field:'userId',width:120">用户ID</th>
					<th data-options="field:'tempId',width:100">模板ID</th>
					<th data-options="field:'title',width:200">标题模板</th>
					<th data-options="field:'isReadStr',width:50">已读</th>
					<th data-options="field:'readTimeStr',width:100">已读时间</th>
					<th data-options="field:'notifyId',width:40,hidden:true">通知ID</th>
				</tr>
			</thead>
		</table>
	</div>


	<div id="sysNotify_toolbar">
		<div class="gridSearchBar" style="height: 40px;">
			<form id="sysNotifySearchForm">
					<table width="100%" height="100%">
						<tr>
							<td width="60px" align="right"><span>用户ID:</span></td>
							<td width="120px"><input name="userId"></td>
							<td width="120px" align="right"><span>模板ID:</span></td>
							<td><input name="tempId"></td>
							<td style="text-align: right;padding-top:4px;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align:80%;">查询</a>
							&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align:80%;">清空</a>
							</td>
						</tr>
					</table>
			</form>

		</div>
		<shiro:hasPermission name="<%=Auths.SYS_NOTIFY_VIEW%>">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="<%=Auths.SYS_NOTIFYTEMPLATE%>">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="acc_icon_template" plain="true" tag="template">通知模板</a>
		</shiro:hasPermission>
	</div>
	<script type="text/javascript">
		
	</script>
	<script type="text/javascript"
		src="${ctx}/static/js/sys/sysNotify_show.js"></script>
</body>

</html>
