<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户状态变更表</title>
</head>
<body>
	<div title="用户日志列表">
		<table  id="sysUserStateChange_grid" singleSelect=true rownumbers="true"
			fitColumns=true toolbar='#sysUserStateChange_toolbar'
			class="easyui-datagrid" data-options="url:'${ctx}/sys/SysUserStateChange/list'"
			pagination="true" pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
				<tr>
				<th data-options="field:'accountId',width:80">账号ID</th>
				<th data-options="field:'userName',width:80">登录名</th>
                <th data-options="field:'typeStr',width:80">状态类型</th>
                <th data-options="field:'ip',width:80">客户端IP</th>
                <th data-options="field:'originStateStr',width:80">原状态</th>
                <th data-options="field:'currentStateStr',width:80">现状态</th>
                <th data-options="field:'isCurrentStr',width:80">是否当前状态</th>
                <th data-options="field:'crtTypeStr',width:80">数据创建类型</th>
                <th data-options="field:'crtTimeStr',width:80">数据创建时间</th>
				</tr>
			</thead>
		</table>

		<div id="sysUserStateChange_toolbar" >
			<div class="gridSearchBar" style="height: 80px;">
				<form id="sysUserStateChangeSearchForm">
						<table width="100%" height="100%">
							<tr>
								<td width="60px" align="right"><span>用户名:</span></td>
								<td><input name="userName" style="width:120px"></td>
								<td width="60px" align="right"><span>操作IP:</span></td>
								<td><input name="ip" style="width:120px"></td>
								<td width="60px"></td>
								<td></td>
								<td width="80px"></td>
								<td></td>
								<td rwospan=2 style="text-align: right;padding-top:4px;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align:60%;">查询</a>
							    &nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align:60%;">清空</a>
							   </td>
							</tr>
							<tr>
								<td width="60px" align="right"><span>状态类型:</span></td>
								<td><select name="crtType" style="width: 120px;">
											<option value="">-------请选择-------</option>
											<c:forEach var="item" items="${crtTypeList}">
												<option value="${item.itemValue}">${item.itemName}</option>
											</c:forEach>
									</select>
								</td>
								<td width="60px" align="right"><span>原状态:</span></td>
								<td>
									<select name="originState" style="width: 120px;">
											<option value="">-------请选择-------</option>
											<c:forEach var="item" items="${userLockStateList}">
												<option value="${item.itemValue}">${item.itemName}</option>
											</c:forEach>
									</select></td>
								<td width="60px" align="right"><span>现状态:</span></td>
								<td>
									<select name="currentState" style="width: 120px;">
											<option value="">-------请选择-------</option>
											<c:forEach var="item" items="${userLockStateList}">
												<option value="${item.itemValue}">${item.itemName}</option>
											</c:forEach>
									</select></td>
								<td width="60px" align="right"><span>是否当前状态:</span></td>
								<td>
									<select name="isCurrent" style="width: 120px;">
											<option value="">-------请选择-------</option>
											<c:forEach var="item" items="${currentList}">
												<option value="${item.itemValue}">${item.itemName}</option>
											</c:forEach>
									</select>
								</td>
							</tr>
						</table>
				</form>

			</div>
			<shiro:hasPermission name="<%= Auths.SYS_USER_STATE_CHANGE_VIEW %>">
       			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
       		</shiro:hasPermission>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysUserStateChange_show.js"></script>
</body>
</html>