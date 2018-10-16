<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>运行的流程列表页面</title>
</head>
<body>
	<div title="运行的流程列表">
		<table striped=true id="processRunning_grid"
			toolbar='#processRunning_toolbar' singleSelect=true fitColumns=true
			class="easyui-datagrid"
			data-options="
            		rownumbers:true,url:'${ctx}/workflow/process-instance/running/list',
            		onRowContextMenu:processRunning.onRowContextMenu,
            		onDblClickRow:processRunning.onDblClickRow,
            		view: detailview,
            		detailFormatter:processRunning.detailFormatter,
            		onExpandRow:processRunning.expandRow"
			pagination="true" pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
				<tr>
					<th data-options="field:'name',width:130">流程名称</th>
					<th data-options="field:'id',width:130">执行ID</th>
					<th data-options="field:'processInstanceId',width:100">流程实例ID</th>
					<th data-options="field:'processDefinitionId',width:150,formatter:processRunning.drnLinkInit">流程定义ID</th>
					<th data-options="field:'businessKey',width:130">业务KEY</th>
					<th data-options="field:'activitiName',width:150">当前节点</th>
					<th data-options="field:'suspended',width:120">是否挂起</th>
					<th
						data-options="field:'operator',width:120,formatter:processRunning.operateLinkInit">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="processRunning_toolbar">
		<div class="gridSearchBar" style="height: 40px;">
			<form id="processRunningSearchForm">
					<table width="100%" height="100%">
						<tr>
							<td width="70px" align="right"><span>流程定义ID:</span></td>
							<td><input name="processDefinitionId"></td>
							<td width="120px" align="right"><span>流程实例ID:</span></td>
							<td><input name="processInstanceId"></td>
							<td width="120px" align="right"><span>业务KEY:</span></td>
							<td><input name="businessKey"></td>
							<td style="text-align: right;padding-top:4px;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align:80%;">查询</a>
							&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align:80%;">清空</a>
							</td>
						</tr>
					</table>
			</form>
			
		</div>
	</div>
	<script type="text/javascript"
		src="${ctx}/static/js/workflow/process_running_show.js"></script>
</body>

</html>
