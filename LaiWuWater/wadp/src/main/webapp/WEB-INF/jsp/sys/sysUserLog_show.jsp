<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户日志页面</title>
  </head>
  <body>
  <div title="用户日志列表">
      <table striped=true id="sysUserGroup_grid" singleSelect=true fitColumns=true  toolbar='#sysUserGroup_toolbar' class="easyui-datagrid" 
        data-options="url:'${ctx}/sys/userLog/list'" pagination="true" pageSize="${defaultPageSize}" pageList="${defaultPageList}">
	      <thead>
		       <tr>
                <th data-options="field:'logId',width:40">日志Id</th>
                <th data-options="field:'logTypeStr',width:40">日志类型</th>
                <th data-options="field:'userId',width:30">用户Id</th>
                <th data-options="field:'userName',width:50">登陆名</th>
                <th data-options="field:'operCode',width:120">操作编码</th>
                <th data-options="field:'operName',width:120">操作名称</th>
                <th data-options="field:'operIP',width:80">操作IP</th>
                <th data-options="field:'remark',width:100">备注</th>
                <th data-options="field:'crtTimeStr',width:80">操作时间</th>
            </tr>
		  </thead>
	</table>
	
    <div id="sysUserGroup_toolbar" class="sysVarbar">
        <div class="gridSearchBar"><form id="wifiVarSearchForm"><fieldset><legend>高级查询</legend>   	
    		<table><tr>
    			<td width="80px" align="right"><span>用户Id:</span></td>
    			<td><input name="userId"></td>
    			<td width="80px" align="right"><span>登陆名:</span></td>
    			<td><input name="userName"></td>
    			<td width="80px" align="right"><span>操作名称:</span></td>
    			<td><input name="operName"></td>
    			<td width="80px" align="right"><span>操作Ip:</span></td>
    			<td><input name="operIP"></td>
    		</tr>
    		</table></fieldset></form>
    		<div style="position: absolute;right: 10px;top:20px;*top:30px;">
    			<a class="easyui-linkbutton" iconCls="m-icon-search" tag="search">查询</a>
    			<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear">清空</a>
    		</div>
    	</div>
    </div>
    </div>
    <script type="text/javascript" src="${ctx}/static/js/sys/sysUserLog_show.js"></script>
  </body>
</html>
