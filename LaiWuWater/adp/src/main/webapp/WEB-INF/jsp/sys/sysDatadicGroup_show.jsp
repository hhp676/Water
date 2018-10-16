<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>字典组列表页面</title>	
  </head>
  <body>
     <table striped=true singleSelect=true id="sysDatadicGroup_grid" fit=true fitColumns=true  toolbar='#sysDatadicGroup_toolbar' class="easyui-datagrid" rownumbers="true" 
        data-options="url:'${ctx}/sys/datadicgroup/list',
        onDblClickRow:sysDatadicGroup.onDblClickRow,
        onLoadSuccess:sysDatadicGroup.onLoadSuccess
        " pagination="true" pageSize="${defaultPageSize}" pageList="${defaultPageList}">
	      <thead>
		       <tr>
                <th data-options="field:'groupId',width:30,hidden:true">字典组Id</th>
                 <th data-options="field:'groupCode',width:50">字典组code</th>
                <th data-options="field:'groupName',width:50">字典组名称</th>
                <th data-options="field:'orderId',width:50">排序</th>
                <th data-options="field:'groupDesc',width:80">描述</th>
                <th data-options="field:'isFinalStr',width:30">不可修改</th>
            </tr>
		  </thead>
	</table>
  	
    <div id="sysDatadicGroup_toolbar">
    	<div class="gridSearchBar" tyle="height:65px">
			<form id="sysDatadicGroupSearchForm">
				<fieldset>
					<legend>高级查询</legend>
					<table>
						<tr>
							<td width="120px" align="right"><span>字典组名称:</span></td>
							<td><input name="groupName"></td>
							<td width="120px" align="right"><span>字典组code:</span></td>
							<td width="250px"><input name="groupCode"></td>
<!-- 							<td><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align:middle;">查询</a></td> -->
<!-- 							<td><a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align:middle;">清空</a></td> -->
						</tr>
					</table>
				</fieldset>
			</form>
		   <div style="position: absolute; right: 20px; top: 30px; *top: 30px;">
				<a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align:80%;">查询</a>
				<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align:80%;">清空</a>
			</div>  
		</div>
    
    	<shiro:hasPermission name="<%= Auths.SYS_DATADICGROUP_ADD %>"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add1" plain="true" tag="add">添加</a></shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_DATADICGROUP_EDIT %>"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit1" plain="true" tag="edit">修改</a></shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_DATADICGROUP_DELETE %>"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-del1" plain="true" tag="del">删除</a></shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_DATADICGROUP_VIEW %>"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a></shiro:hasPermission>      
    </div>
    

	<script type="text/javascript" src="${ctx}/static/js/sys/sysDatadicGroup_show.js"></script>
  </body>

</html>