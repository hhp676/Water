<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>通知模板列表页面</title>	
  </head>
  <body>
          <table striped=true id="sysNotifyTemplate_grid" singleSelect=true fit=true fitColumns=true  toolbar='#sysNotifyTemplate_toolbar' class="easyui-datagrid"  rownumbers="true" 
            data-options="
            		url:'${ctx}/sys/notifyTemplate/list',
            		onLoadSuccess:sysNotifyTemplate.onLoadSuccess"
            		pagination="true" pageSize="${defaultPageSize}"
            		pageList="${defaultPageList}">
	          <thead>
		           <tr>
                       <th data-options="field:'tempId',width:50,hidden:true">模板ID</th>
                       <th data-options="field:'name',width:100">模板名称</th>
                       <th data-options="field:'module',width:100">所属模块</th>
                       <th data-options="field:'titleTemplate',width:200">标题模板</th>
                       <th data-options="field:'isFinalStr',width:50">是否不可修改</th>

                  </tr>
		      </thead>
	       </table>
     
  	
    <div id="sysNotifyTemplate_toolbar">
    	<shiro:hasPermission name="<%= Auths.SYS_NOTIFYTEMPLATE_ADD %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add1" plain="true" tag="add">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_NOTIFYTEMPLATE_EDIT %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit1" plain="true" tag="edit">修改</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_NOTIFYTEMPLATE_DELETE %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-del1" plain="true" tag="del">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_NOTIFYTEMPLATE_VIEW %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
    	</shiro:hasPermission>
    </div>
    <div id="sysNotifyTemplate_contextMenu" class="easyui-menu" style="width: 120px;">
  		<div  iconCls="icon-add1" tag="add">添加</div>
  		<div  iconCls="icon-edit1" tag="edit">修改</div>
		<div  iconCls="icon-del1" tag="del">删除</div>		
	</div>
	<script type="text/javascript">
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysNotifyTemplate_show.js"></script>
  </body>

</html>
