<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>已办任务页面</title>
</head>
<body>
	<div title="已办任务列表">
		<table striped=true id="taskDone_grid" toolbar='#taskDone_toolbar' singleSelect=true rownumbers="true"
			fitColumns=true class="easyui-datagrid"
			data-options="
            		rownumbers:true,url:'${ctx}/workflow/task/done/list',
            		onRowContextMenu:taskDone.onRowContextMenu,
            		onDblClickRow:taskDone.onDblClickRow,
            		view: detailview,
            		detailFormatter:taskDone.detailFormatter,
            		onExpandRow:taskDone.expandRow"
			pagination="true" pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
				<tr>
					<th data-options="field:'id',width:60,hidden:true">任务ID</th>
					<th data-options="field:'name',width:150">任务名称</th>
					<th data-options="field:'processDefinitionId',width:150,formatter:taskDone.drnLinkInit,hidden:true">流程定义ID</th>
					<th data-options="field:'processInstanceId',width:80,hidden:true">流程实例ID</th>
					<th data-options="field:'startTimeStr',width:130">开始日期</th>
					<th data-options="field:'endTimeStr',width:130">结束日期</th>
					<th data-options="field:'assignee',width:80">任务办理人</th>
					<th data-options="field:'deleteReason',width:80">处理结果</th>
					<th
						data-options="field:'operator',width:120,formatter:taskDone.operateLinkInit">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="taskDone_toolbar">
		<div class="gridSearchBar" style="height: 40px;">
			<form id="taskDoneSearchForm">
					<table width="100%" height="100%">
						<tr>
							<td width="120px" align="right"><span>任务名称:</span></td>
							<td><input name="name" style="height:20px;"></td>
							<td width="120px" align="right"><span>开始时间段:</span></td>
							<td><input name="startStartTime"   
								id="taskDone_startStartTime" class="Wdate"
								style="width: 140px;"
								onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'taskDone_startEndTime\',{d:0});}'})">
								--- <input name="startEndTime" id="taskDone_startEndTime"  
								class="Wdate" style="width: 140px;"
								onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'taskDone_startStartTime\',{d:0});}'})"></td>
								<td style="text-align: right;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align:60%;">查询</a>
								&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align:60%;">清空</a>
							</td>
						</tr>
					</table>
			</form>
		</div>
	</div>	
	<script type="text/javascript"
		src="${ctx}/static/js/workflow/task_done_show.js"></script>
</body>

</html>
