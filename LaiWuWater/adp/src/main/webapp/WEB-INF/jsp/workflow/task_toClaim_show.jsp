<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>待认领任务页面</title>
</head>
<body>
	<div title="待认领任务列表">
		<table striped=true id="taskToClaim_grid" toolbar='#taskToClaim_toolbar' singleSelect=true rownumbers="true"
			fitColumns=true class="easyui-datagrid"
			data-options="
            		rownumbers:true,url:'${ctx}/workflow/task/toClaim/list',
            		onRowContextMenu:taskToClaim.onRowContextMenu,
            		onDblClickRow:taskToClaim.onDblClickRow,
            		view: detailview,
            		detailFormatter:taskToClaim.detailFormatter,
            		onExpandRow:taskToClaim.expandRow"
			pagination="true" pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
				<tr>
					<th data-options="field:'id',width:60,hidden:true">任务ID</th>
					<th data-options="field:'name',width:150">任务名称</th>
					<th data-options="field:'initiator',width:80">发起人</th>
					<th
						data-options="field:'processDefinitionId',width:100,formatter:taskToClaim.drnLinkInit,hidden:true">流程定义ID</th>
					<th data-options="field:'processInstanceId',width:100,hidden:true">流程实例ID</th>
					<th data-options="field:'createTimeStr',width:130">任务创建日期</th>
					<th data-options="field:'dueDateStr',width:130">任务逾期日期</th>
					<th data-options="field:'candidateUsers',width:120">任务认领人</th>
					<th
						data-options="field:'operator',width:180,formatter:taskToClaim.operateLinkClaimInit">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="taskToClaim_toolbar">
		<div class="gridSearchBar" style="height: 40px;">
			<form id="taskToClaimSearchForm"> 
					<table width="100%" height="100%">
						<tr>
							<td width="120px" align="right" style="padding-top:3px;"><span>任务名称:</span></td>
							<td><input name="name" style="height:20px;"></td>
							<td width="120px" align="right"><span>创建时间段:</span></td>
							<td><input name="startStartTime"  
								id="taskToClaim_startStartTime" class="Wdate" style="width: 140px;height:20px;"
								onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'taskToClaim_startEndTime\',{d:0});}'})">
								--- <input   name="startEndTime" id="taskToClaim_startEndTime"
								class="Wdate" style="width: 140px;height:20px;"
								onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'taskToClaim_startStartTime\',{d:0});}'})"></td>
								<td style="text-align: right;padding-top:3px;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align:70%;">查询</a>
								&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align:70%;">清空</a>
							</td>
						</tr>
					</table>
			</form>
			
		</div>
	</div>
	<script type="text/javascript"
		src="${ctx}/static/js/workflow/task_toClaim_show.js"></script>
</body>

</html>
