<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表页面</title>	
  </head>
  <body>
  	<div title="用户列表">
      <table id="sysUser_grid" singleSelect=true  toolbar='#user_toolbar' class="easyui-datagrid" 
        data-options="
        	url:'${ctx}/sys/sysUser/list',
       		onDblClickRow:sysUser.onDblClickRow,
       		onRowContextMenu:sysUser.onRowContextMenu,
       		fitColumns:true"       		
       		pagination="true" pageSize="${defaultPageSize}" pageList="${defaultPageList}">
	      <thead>
		      <tr>
		      	  <th data-options="field:'userId',width:30">用户Id</th>
			      <th data-options="field:'userName',width:80">用户名</th>
			      <th data-options="field:'engName',width:80">英文名</th>
			      <th data-options="field:'loginName',width:80">登录名</th>
			      <th data-options="field:'sexStr',width:50">性别</th>
			      <th data-options="field:'mobile',width:80">手机号</th>
			      <th data-options="field:'telephone',width:80">联系电话</th>
			      <th data-options="field:'email',width:120">email</th>
			      <th data-options="field:'isFinalStr'">不可修改</th>
				  <th data-options="field:'descr',width:150">描述</th>

			  </tr>

		  </thead>
	</table>
  </div>
    <div id="user_toolbar">
    	<div class="gridSearchBar">
			<form id="sysUserSearchForm">
				<fieldset>
					<legend>高级查询</legend>
					<table>
						<tr>
							<td width="120px" align="right"><span>用户名:</span></td>
							<td><input name="userName"></td>
							<td width="120px" align="right"><span>登陆名:</span></td>
							<td><input name="loginName"></td>
						</tr>
					</table>
				</fieldset>
			</form>
			<div style="position: absolute; right: 10px; top: 20px; *top: 30px;">
				<a class="easyui-linkbutton" iconCls="m-icon-search" tag="search">查询</a>
				<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear">清空</a>
			</div>
		</div>
    	<shiro:hasPermission name="<%= Auths.SYS_USRR_ADD %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-person-add" plain="true" tag="add">添加</a>
    	</shiro:hasPermission>
        <shiro:hasPermission name="<%= Auths.SYS_USRR_EDIT %>">
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-person-edit" plain="true" tag="edit">修改</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="<%= Auths.SYS_USRR_DELETE %>">
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-person-del" plain="true" tag="del">删除</a>
        </shiro:hasPermission>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-key-y" plain="true" tag="changePwd">改密</a>
        <shiro:hasPermission name="<%= Auths.SYS_USRR_VIEW %>">
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
        </shiro:hasPermission>
    </div>
    <div id="sysUser_contextMenu" class="easyui-menu" style="width: 120px;">
  		<div  iconCls="m-icon-person-add" tag="add">添加</div>
  		<div  iconCls="m-icon-person-edit" tag="edit">修改</div>
		<div  iconCls="m-icon-person-del" tag="del">删除</div>
		<div  iconCls="m-icon-key-y" tag="changePwd">改密</div>		
	</div>    
    <script type="text/javascript" src="${ctx}/static/js/sys/sysUser_show.js"></script>
	
  </body>

</html>
