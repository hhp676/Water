<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表页面</title>	
  </head>
  <body>
  <div class="easyui-tabs" fit=true tabPosition="top" id="sysUser_tab"
	data-options="tabWidth:200">
	<div title="用户列表" data-options="closable:false"
		style="overflow: hidden">
      <table id="sysUser_grid" singleSelect=true  toolbar='#user_toolbar' class="easyui-datagrid"  rownumbers="true"
        data-options="
        	url:'${ctx}/sys/sysUser/list',
       		onDblClickRow:sysUser.onDblClickRow,
       		onRowContextMenu:sysUser.onRowContextMenu,
       		onLoadSuccess:sysUser.onLoadSuccess,
       		fitColumns:true"       		
       		pagination="true" pageSize="${defaultPageSize}" pageList="${defaultPageList}">
	      <thead>
		      <tr>
		      	  <th data-options="field:'userId',width:50,hidden:true">用户Id</th>
			      <th data-options="field:'userName',width:50">用户名</th>
			      <th data-options="field:'engName',width:80">英文名</th>
			      <th data-options="field:'loginName',width:80">登录名</th>
			      <th data-options="field:'sexStr',width:30">性别</th>
			      <th data-options="field:'mobile',width:70">手机号</th>
			      <th data-options="field:'telephone',width:80">联系电话</th>
			      <th data-options="field:'email',width:180">email</th>
			      <th data-options="field:'isFinalStr',width:80">是否不可修改</th>
				  <th data-options="field:'descr',width:100">描述</th>

			  </tr>

		  </thead>
	</table>
  </div>
  <div title="图表统计" data-options="closable:false" href="${ctx}/sys/chart/sysUser"
		style="overflow: hidden" width="600px;">
		
  </div>
  </div>
  	
    <div id="user_toolbar">
    	<div class="gridSearchBar" style="height: 40px;">
			<form id="sysUserSearchForm">
					<table width="100%" height="100%">
						<tr >
							<td width="60px" align="right"><span>用户名:</span></td>
							<td width="120px" ><input name="userName"></td>
							<td width="120px" align="right" ><span>登录名:</span></td>
							<td ><input name="loginName" ></td>
							<td style="text-align: right;padding-top:4px;" >
								<a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align: 80%;">查询</a>
								&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align: 80%;">清空</a>
							</td>
						</tr>
					</table>
			</form>
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
        
        <shiro:hasPermission name="<%= Auths.SYS_USRR_CHANGEPWD %>">
      		 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-key-y" plain="true" tag="changePwd">改密</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="<%= Auths.SYS_USRR_VIEW %>">
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="<%= Auths.SYS_USRR_IMPORT %>">
      	  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-import" plain="true" tag="import">批量导入</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="<%= Auths.SYS_USRR_LOCK %>">
          <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-import" plain="true" tag="lock">锁定</a>
      	</shiro:hasPermission>
      	<shiro:hasPermission name="<%= Auths.SYS_USRR_UNLOCK %>">
      	  <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-import" plain="true" tag="unlock">解锁</a>
    	</shiro:hasPermission>
    </div>
    <div id="sysUser_contextMenu" class="easyui-menu" style="width: 120px;">
  		<shiro:hasPermission name="<%= Auths.SYS_USRR_ADD %>">
  			<div  iconCls="m-icon-person-add" tag="add">添加</div>
  		</shiro:hasPermission>
  		<shiro:hasPermission name="<%= Auths.SYS_USRR_EDIT %>">
  			<div  iconCls="m-icon-person-edit" tag="edit">修改</div>
  		</shiro:hasPermission>
  		<shiro:hasPermission name="<%= Auths.SYS_USRR_DELETE %>">
			<div  iconCls="m-icon-person-del" tag="del">删除</div>
		</shiro:hasPermission>
		 <shiro:hasPermission name="<%= Auths.SYS_USRR_CHANGEPWD %>">
			<div  iconCls="m-icon-key-y" tag="changePwd">改密</div>	
		</shiro:hasPermission>	
	</div>    
    <script type="text/javascript" src="${ctx}/static/js/sys/sysUser_show.js"></script>
	
  </body>

</html>
