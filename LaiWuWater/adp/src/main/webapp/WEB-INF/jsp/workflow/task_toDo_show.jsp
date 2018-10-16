<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>待办任务页面</title>
</head>
<body>
	<div title="待办任务列表">
		<table striped=true id="taskToDo_grid" toolbar='#taskToDo_toolbar' singleSelect=true rownumbers="true"
			fitColumns=true class="easyui-datagrid"
			data-options="
            		rownumbers:true,url:'${ctx}/workflow/task/toDo/list',
            		onRowContextMenu:taskToDo.onRowContextMenu,
            		onDblClickRow:taskToDo.onDblClickRow,
            		view: detailview,
            		detailFormatter:taskToDo.detailFormatter,
            		onExpandRow:taskToDo.expandRow"
			pagination="true" pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
				<tr>
					<th data-options="field:'id',width:60,hidden:true,hidden:true">任务ID</th>
					<th data-options="field:'name',width:150">任务名称</th>
					<th data-options="field:'initiator',width:80">发起人</th>
					<th
						data-options="field:'processDefinitionId',width:150,formatter:taskToDo.drnLinkInit,hidden:true">流程定义ID</th>
					<th data-options="field:'processInstanceId',width:80,hidden:true">流程实例ID</th>
					<th data-options="field:'createTimeStr',width:130">任务创建日期</th>
					<th data-options="field:'dueDateStr',width:130">任务逾期日期</th>
					<th data-options="field:'assignee',width:80,hidden:true">任务所属人</th>
					<th
						data-options="field:'operator',width:180,formatter:taskToDo.operateLinkInit">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="taskToDo_toolbar">
		<div class="gridSearchBar" style="height: 40px;">
			<form id="taskToDoSearchForm">
					<table width="100%" height="100%">
						<tr>
							<td width="120px" align="right" ><span>任务名称:</span></td>
							<td><input name="name" style="height:20px;"></td>						
							<td width="120px" align="right"><span>创建时间段:</span></td>
							<td><input name="startStartTime"
								id="taskToDo_startStartTime" class="Wdate" style="width: 140px;height:20px;"
								onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'taskToDo_startEndTime\',{d:0});}'})">
								--- <input  name="startEndTime" id="taskToDo_startEndTime"
								class="Wdate" style="width: 140px;height:20px;"
								onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'taskToDo_startStartTime\',{d:0});}'})"></td>
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
		src="${ctx}/static/js/workflow/task_toDo_show.js"></script>
</body>

</html>
