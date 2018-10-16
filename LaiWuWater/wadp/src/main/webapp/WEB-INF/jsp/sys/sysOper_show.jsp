<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>操作类型列表页面</title>	
  </head>
  <body>

  	 <table striped=true id="sysOper_grid" singleSelect=true fitColumns=true fit=true toolbar='#sysOper_toolbar' class="easyui-datagrid"  
      data-options="url:'${ctx}/sys/authoper/list'" pagination="true" pageSize="${defaultPageSize}" pageList="${defaultPageList}">
	      <thead>
		      <tr>
                 <th data-options="field:'operId',width:30">操作Id</th>
                 <th data-options="field:'operCode',width:80">操作code</th>
                 <th data-options="field:'operName',width:100">操作名称</th>
 				 <th data-options="field:'operEnname',width:100">操作英文名称</th>
                 <th data-options="field:'descr',width:180">描述</th>
                 <th data-options="field:'isFinalStr',width:60">不可修改</th>
               </tr>
		  </thead>
	</table>
    <div id="sysOper_toolbar">
    	<shiro:hasPermission name="<%= Auths.SYS_OPER_ADD %>"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add1" plain="true" tag="add">添加</a></shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_OPER_EDIT %>"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit1" plain="true" tag="edit">修改</a></shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_OPER_DELETE %>"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-del1" plain="true" tag="del">删除</a></shiro:hasPermission>      
    </div>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysOper_show.js"></script>
  </body>

</html>
