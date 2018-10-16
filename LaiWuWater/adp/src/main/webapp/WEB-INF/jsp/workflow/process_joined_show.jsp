<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>参与的流程列表页面</title>
</head>
<body>
	<div title="参与的流程列表">
		<table striped=true id="processJoined_grid"
			toolbar='#processJoined_toolbar' singleSelect=true fitColumns=true
			class="easyui-datagrid"
			data-options="
            		rownumbers:true,url:'${ctx}/workflow/process-instance/joined/list',
            		onRowContextMenu:processJoined.onRowContextMenu,
            		onDblClickRow:processJoined.onDblClickRow,
            		view: detailview,
            		detailFormatter:processJoined.detailFormatter,
            		onExpandRow:processJoined.expandRow"
			pagination="true" pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
				<tr>
					<th data-options="field:'name',width:130">流程名称</th>
					<th data-options="field:'startUserId',width:130">发起人</th>
					<th data-options="field:'startTimeStr',width:130">开始时间</th>
					<th data-options="field:'endTimeStr',width:130">结束时间</th>
					<th data-options="field:'processDefinitionId',width:150,formatter:processJoined.drnLinkInit">流程定义ID</th>
					<th data-options="field:'businessKey',width:130">业务KEY</th>
					<th data-options="field:'suspended',width:120">是否挂起</th>
					<th
						data-options="field:'operator',width:120,formatter:processJoined.operateLinkInit">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="processJoined_toolbar">
		<div class="gridSearchBar" style="height: 65px;">
			<form id="processJoinedSearchForm">
					<table width="100%" height="100%">
						<tr>
							<td width="120px" align="right"><span>发起人:</span></td>
							<td width="120px;"><input name="startUserId"></td>
							<td width="120px" align="right"><span>开始时间段:</span></td>
							<td width="330px;"><input name="startStartTime" id="processJoined_startStartTime" class="Wdate"
								style="width: 140px;" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'processJoined_startEndTime\',{d:0});}'})">
								--- <input name="startEndTime"  id="processJoined_startEndTime" class="Wdate" style="width: 140px;"
								onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'processJoined_startStartTime\',{d:0});}'})"></td>
							<td width="60px" align="right"><span>业务KEY:</span></td>
							<td><input name="businessKey"></td>	
							<td></td>							
						</tr>
						<tr>
							<td width="120px" align="right"><span>流程定义ID:</span></td>
							<td><input name="processDefinitionId"></td>
							<td width="120px" align="right"><span>结束时间段:</span></td>
							<td><input name="endStartTime" id="processJoined_endStartTime" class="Wdate"
								style="width: 140px;" onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'processJoined_endEndTime\',{d:0});}'});">
								--- <input name="endEndTime" id="processJoined_endEndTime" class="Wdate" style="width: 140px;"
								onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'processJoined_endStartTime\',{d:0});}'});"></td>
							<td style="text-align: right;" colspan="3"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search">查询</a>
							&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear">清空</a>
							</td>							
						</tr>
					</table>
			</form>
		</div>
	</div>
	<script type="text/javascript"
		src="${ctx}/static/js/workflow/process_joined_show.js"></script>
</body>

</html>
