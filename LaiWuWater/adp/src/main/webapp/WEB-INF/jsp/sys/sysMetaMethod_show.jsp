<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统元方法表</title>
</head>
<body>
 <div title="系统元方法表列表">
 		<table  id="sysMetaMethod_datagrid" singleSelect=true  fitColumns=true  toolbar='#sysMetaMethod_toolbar' 
 		    class="easyui-datagrid" data-options="url:'${ctx}/sys/sysMetaMethod/list',
			onRowContextMenu:sysMetaMethodDatagrid.onRowContextMenu,
			onDblClickRow:sysMetaMethodDatagrid.onDblClickRow,
			onLoadSuccess:sysMetaMethodDatagrid.onLoadSuccess" 
			pagination="true" 
			pageSize="${defaultPageSize}" 
			pageList="${defaultPageList}">
       	 	<thead>
            <tr>
                <th data-options="field:'className',width:700" editor="{type:'textbox'}">类名</th>
                <th data-options="field:'methodName',width:300" editor="{type:'textbox'}">方法名</th>
                <th data-options="field:'argsName',width:100" editor="{type:'textbox'}">参数</th>
                <th data-options="field:'methodCode',width:350" editor="{type:'textbox'}">方法编码</th>
                <th data-options="field:'methodZhName',width:120" editor="{type:'textbox'}">方法中文名称</th>
                <th data-options="field:'methodEngName',width:120" editor="{type:'textbox'}">方法英文名称</th>
                <th data-options="field:'logLevelStr',width:150" editor="{type:'textbox'}">日志级别</th>
                <th data-options="field:'logTypeStr',width:150" editor="{type:'textbox'}">日志类型</th>
                <th data-options="field:'logRemarkClass',width:150" editor="{type:'textbox'}">记录参数</th>
                <th data-options="field:'descr',width:100" editor="{type:'textbox'}">描述</th>
                <th data-options="field:'isValidStr',width:150" editor="{type:'textbox'}">是否在用</th>
            </tr>
            </thead>
        </table>
       	<div id="sysMetaMethod_toolbar" style="display: none;"> 
       		<div class="gridSearchBar" style="height: 50px;">
			<form id="sysMetaMethodSearchForm">
       			<table width="100%">
						<tr>
							<td width="60px" align="right"><span>类名:</span></td>
							<td><input name="className"></td>
							<td width="80px" align="right"><span>方法名:</span></td>
							<td><input name="methodName"></td>
							<td width="80px" align="right"><span>参数类型:</span></td>
							<td><input name="argsName"></td>
							
							<td style="text-align: right;" rowspan=2><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search">查询</a>
							&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear">清空</a>
							</td>
						</tr>
						<tr>
							<td width="60px" align="right"><span>方法编码:</span></td>
							<td><input name="methodCode"></td>
							<td width="60px" align="right"><span>方法中文名称:</span></td>
							<td><input name="methodZhName"></td>
							<td width="60px" align="right"><span>方法英文名称:</span></td>
							<td><input name="methodEngName"></td>
						</tr>
					</table>
       	</form>
		</div>
			<shiro:hasPermission name="<%= Auths.SYS_META_METHOD_ADD %>">
    			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add1" plain="true" tag="add">添加</a>
    		</shiro:hasPermission>
    		<shiro:hasPermission name="<%= Auths.SYS_META_METHOD_EDIT %>">
    			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit1" plain="true" tag="edit">修改</a>
    		</shiro:hasPermission>
    		<shiro:hasPermission name="<%= Auths.SYS_META_METHOD_DELETE %>">
    			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-del1" plain="true" tag="del">删除</a>
    		</shiro:hasPermission>
    		<shiro:hasPermission name="<%= Auths.SYS_META_METHOD_VIEW %>">
       			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
       		</shiro:hasPermission>
			<shiro:hasPermission name="<%=Auths.SYS_META_METHOD_INIT%>">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="m-icon-list" plain="true" tag="init">一键初始化Controller方法</a>
			</shiro:hasPermission>
		</div>   	
      </div>
      <div id="sysRole_contextMenu" class="easyui-menu" style="width: 120px;">
  		<shiro:hasPermission name="<%= Auths.SYS_META_METHOD_ADD %>">
  			<div iconCls="icon-add1" tag="add">添加</div>
  		</shiro:hasPermission>
  		<shiro:hasPermission name="<%= Auths.SYS_META_METHOD_EDIT %>">
  			<div iconCls="icon-edit1" tag="edit">修改</div>
  		</shiro:hasPermission>
	    <shiro:hasPermission name="<%= Auths.SYS_META_METHOD_DELETE %>">
	    	<div iconCls="icon-del1" tag="del">删除</div>
	    </shiro:hasPermission>
	    <shiro:hasPermission name="<%= Auths.SYS_META_METHOD_INIT %>">
	    	<div iconCls="m-icon-list" tag="init">一键初始化Controller方法</div>
	    </shiro:hasPermission>		
	</div>
       					 
<script type="text/javascript" src="${ctx}/static/js/sys/sysMetaMethod_show.js"></script>
</body>

</html>