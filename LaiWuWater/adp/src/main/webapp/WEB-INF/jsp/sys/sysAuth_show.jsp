<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>权限列表页面</title>	
  </head>
  <body>
  	<div title="权限列表">
  		 <table fit=true id="sysAuth_grid" class="easyui-treegrid" toolbar="#auth_toolbar"  showFooter=true rownumbers="true"  
  	  	fitColumns=false data-options="
  	  		url: '${ctx}/sys/auth/list?number='+ new Date().getTime(),
  	  		method: 'get',
  	  		idField: 'authId', 	  		
  	  		onDblClickRow:sysAuthOnDblClickRow,
  	  		onLoadSuccess:sysAuthOnLoadSuccess,
  	  		treeField:'authName'
  	  		">
        <thead>
            <tr>
                <th data-options="field:'authName'" width="260px">权限名称</th>
                <th data-options="field:'authTypeStr'"width="80px">权限类型</th>
                <th data-options="field:'authCode'" width="120px">code</th>
                <th data-options="field:'authUri'" width="180px">uri</th>
                <th data-options="field:'levelId'" width="60px">层级</th>
                <th data-options="field:'orderId'" width="60px">排序id</th>
                <th data-options="field:'isFinalStr'" width="95px">不可修改</th>
                <th data-options="field:'descr'" width="245px">描述</th>                  			
               	<th data-options="field:'authId',hidden:true" width="40px">权限Id</th>
            </tr>
        </thead>
    </table>
  	</div>
   
    <div style="padding:5px;border:1px solid #ddd" id="auth_toolbar">
        	<div class="gridSearchBar" style="height: 40px;">
			<form id="sysAuthSearchForm">
					<table width="100%" >
						<tr>
							<td width="60px" align="right"><span>权限名称:</span></td>
							<td width="120px"><input name="authName"></td>
							<td width="120px" align="right"><span>code:</span></td>
							<td><input name="authCode"></td>
							<td style="text-align: right;padding-top:5px;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align:80%;">查询</a>
							&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align:80%;">清空</a>
							</td>
						</tr>
					</table>
			</form>
		</div>
      	<shiro:hasPermission name="<%= Auths.SYS_AUTH_ADD %>">
      		<a href="#" class="easyui-menubutton" data-options="menu:'#auth_toolbar_menu',iconCls:'m-icon-tag-add'">添加</a>
      	</shiro:hasPermission>
       	<shiro:hasPermission name="<%= Auths.SYS_AUTH_EDIT %>">
       		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-tag-edit" plain="true" tag="edit">修改</a>
       	</shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_AUTH_DELETE %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-tag-del" plain="true" tag="del">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_AUTH_VIEW %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
    	</shiro:hasPermission>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-oper16" plain="true" tag="oper">操作类型管理</a>
    </div>
    <div id="auth_toolbar_menu" style="width:100px;">
        <div iconCls="m-icon-tag-green" tag="moduleAuth">模块权限</div>
        <div iconCls="m-icon-tag-purple" tag="operAuth">操作权限</div>
        <div iconCls="m-icon-tag-red" tag="appAuth">应用权限</div>
    </div>
    <div id="sysAuth_contextMenu" class="easyui-menu" style="width: 120px;">
    	<shiro:hasPermission name="<%= Auths.SYS_AUTH_EDIT %>">
  			<div  data-options="iconCls:'m-icon-tag-edit'" tag="edit">修改</div>
  		</shiro:hasPermission>
  		<shiro:hasPermission name="<%= Auths.SYS_AUTH_DELETE %>">
			<div  data-options="iconCls:'m-icon-tag-del'" tag="del">删除</div>
		</shiro:hasPermission>		
	</div>
   
	<script type="text/javascript" src="${ctx}/static/js/sys/sysAuth_show.js"></script>
  </body>
 
	
</html>
