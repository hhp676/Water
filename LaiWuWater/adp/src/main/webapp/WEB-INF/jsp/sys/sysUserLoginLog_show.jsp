<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录日志表</title>
</head>
<body>
 	<div title="用户日志列表">
		<table  id="sysUserLoginLog_grid" singleSelect=true rownumbers="true"
			fitColumns=false toolbar='#sysUserLoginLog_toolbar'
			class="easyui-datagrid" data-options="url:'${ctx}/sys/SysUserLoginLog/list'"
			pagination="true" pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
				<tr>
				<th data-options="field:'accountId',width:150">账号ID</th>
				<th data-options="field:'userName',width:150">登录名</th>
                <th data-options="field:'operCode',width:150">操作编码</th>
                <th data-options="field:'operName',width:150">中文操作名称</th>
                <th data-options="field:'operIp',width:150">IP</th>
                <th data-options="field:'operResStr',width:150">操作结果</th>
                <th data-options="field:'content',width:150">日志内容(可放置json格式请求参数)</th>
                <th data-options="field:'crtTimeStr',width:150">操作时间</th>
				</tr>
			</thead>
		</table>

		<div id="sysUserLoginLog_toolbar" >
			<div class="gridSearchBar" style="height: 40px;">
				<form id="sysUserLoginLogSearchForm">
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
	<script type="text/javascript" src="${ctx}/static/js/sys/sysUserLoginLog_show.js"></script>
</body>

</html>