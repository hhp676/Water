<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>工作流实例追踪页面</title>
</head>
<body>
	<div class="easyui-tabs" fit=true tabPosition="top"
		id="processTrace_tab">
		<div title="追踪视图" data-options="closable:false"
			style="overflow: hidden">
			<iframe scrolling="yes" frameborder="0"
				src="${ctx}/activiti/process/showTraceView/${processInstanceId}/${processDefinitionId}"
				style="width: 100%; height: 100%;"></iframe>
		</div>
		<div title="历史任务列表" data-options="closable:false"
			style="overflow: hidden">
			<table striped=true id="processHisTask_grid" singleSelect=true
				fitColumns=true class="easyui-datagrid" autoRowHeight=false nowrap=false
				data-options="
            		rownumbers:true">
				<thead>
					<tr>
						<th data-options="field:'id',width:130">编号</th>
						<th data-options="field:'name',width:130">名称</th>
						<th data-options="field:'startTime',width:130">开始时间</th>
						<th data-options="field:'endTime',width:130">结束时间</th>
						<th data-options="field:'assignee',width:130">负责人</th>
						<th data-options="field:'deleteReason',width:130">处理结果</th>
						<th data-options="field:'comment',width:130">处理意见</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${historicTasks}" var="item">
						<tr>
							<td>${item.id}</td>
							<td>${item.name}</td>
							<td><fmt:formatDate value="${item.startTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><fmt:formatDate value="${item.endTime}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${item.owner}</td>
							<td>${item.deleteReason}</td>
							<td>${item.comment}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div title="表单变量" data-options="closable:false"
			style="overflow: hidden">
			<table striped=true id="processHisVariable_grid" singleSelect=true
				fitColumns=true class="easyui-datagrid"
				data-options="
            		rownumbers:true">
				<thead>
					<tr>
						<th data-options="field:'variableName',width:200">名称</th>
						<th data-options="field:'value',width:200">值</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${historicVariableInstances}" var="item">
						<tr>
							<td>${item.variableName}</td>
							<td>${item.value}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div title="Diagram-Viewer" data-options="closable:false"
			style="overflow: hidden">
			<iframe scrolling="yes" frameborder="0"
				src="${ctx}/plugins/activiti/diagram-viewer/index.html?processDefinitionId=${processDefinitionId}&processInstanceId=${processInstanceId}"
				style="width: 100%; height: 100%;"></iframe>
		</div>
	</div>
</body>

</html>
