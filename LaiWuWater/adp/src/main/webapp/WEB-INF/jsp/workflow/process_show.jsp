<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>工作流程列表页面</title>
</head>
<body>
	<div title="流程列表">
		<table striped=true id="process_grid" toolbar='#process_toolbar' singleSelect=true rownumbers="true"
			fitColumns=true class="easyui-datagrid"
			data-options="
            		rownumbers:true,url:'${ctx}/workflow/process/list',
            		onRowContextMenu:process.onRowContextMenu,
            		onDblClickRow:process.onDblClickRow"
			pagination="true" pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
				<tr>
					<th data-options="field:'id',width:130,hidden:true">流程ID</th>
					<th data-options="field:'key',width:100">流程KEY</th>
					<th data-options="field:'name',width:130">流程名称</th>
					<th data-options="field:'version',width:80">版本号</th>
					<th
						data-options="field:'resourceName',width:120,formatter:process.rnLinkInit">流程XML</th>
					<th
						data-options="field:'diagramResourceName',width:120,formatter:process.drnLinkInit">流程图片</th>
					<th
						data-options="field:'operator',width:120,formatter:process.operateLinkInit">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="process_toolbar">
		<div class="gridSearchBar" style="height: 40px;">
			<form id="processSearchForm">
					<table width="100%" height="100%">
						<tr>
							<td width="60px" align="right"><span>流程KEY:</span></td>
							<td width="120px"><input name="key"></td>
							<td width="120px" align="right"><span>流程名称:</span></td>
							<td><input name="name"></td>
							<td width="120px" align="right"><span>版本号:</span></td>
							<td><input name="version"></td>
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
		src="${ctx}/static/js/workflow/process_show.js"></script>
</body>

</html>
