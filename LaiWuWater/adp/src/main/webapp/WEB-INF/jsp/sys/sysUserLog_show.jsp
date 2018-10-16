<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<title>用户日志页面</title>
</head>
<body>
	<div title="用户日志列表">
		<table  id="sysUserLog_grid" singleSelect=true rownumbers="true"
			fitColumns=false toolbar='#sysUserLog_toolbar'
			class="easyui-datagrid" data-options="url:'${ctx}/sys/userLog/list'	,
			onLoadSuccess:sysUserLog.onLoadSuccess,
			onDblClickRow:sysUserLog.onDblClickRow"
			pagination="true" pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
				<tr>
					<th data-options="field:'logTypeStr',width:80">日志类型</th>
					<th data-options="field:'userId',width:80">用户Id</th>
					<th data-options="field:'userName',width:120">登陆名</th>
					<th data-options="field:'operCode',width:160">操作编码</th>
					<th data-options="field:'operName',width:160">操作名称</th>
					<th data-options="field:'operIP',width:140">操作IP</th>
					<th data-options="field:'remark',width:220">备注</th>
					<th data-options="field:'crtTimeStr',width:140">操作时间</th>
					<th data-options="field:'logId',width:40,hidden:true">日志Id</th>
				</tr>
			</thead>
		</table>

		<div id="sysUserLog_toolbar" >
			<div class="gridSearchBar" style="height: 40px;">
				<form id="sysUserLogSearchForm">
						<table width="100%" height="100%">
							<tr>
								<td width="60px" align="right"><span>用户Id:</span></td>
								<td><input name="userId"></td>
								<td width="60px" align="right"><span>登陆名:</span></td>
								<td><input name="userName"></td>
								<td width="60px" align="right"><span>操作名称:</span></td>
								<td><input name="operName"></td>
								<td width="60px" align="right"><span>操作IP:</span></td>
								<td><input name="operIP"></td>
								<td style="text-align: right;padding-top:4px;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align:60%;">查询</a>
							    &nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align:60%;">清空</a>
							   </td>
							</tr>
						</table>
				</form>

			</div>
			<shiro:hasPermission name="<%= Auths.SYS_USER_LOG_VIEW %>">
       			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
       		</shiro:hasPermission>
		</div>
	</div>
	<script type="text/javascript"
		src="${ctx}/static/js/sys/sysUserLog_show.js"></script>
</body>
</html>
