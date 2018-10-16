<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${meta.tableDesc}</title>
</head>
<body>
 <div title="${meta.tableDesc}列表">
 		<table  id="${meta.firstLowerClassName}_datagrid" singleSelect=true  fitColumns=true  toolbar='#${meta.firstLowerClassName}_toolbar' 
 		    class="easyui-datagrid" data-options="url:'${r'${ctx}'}/${meta.module}/${meta.className}/list',
			onRowContextMenu:${meta.firstLowerClassName}Datagrid.onRowContextMenu,
			onDblClickRow:${meta.firstLowerClassName}Datagrid.onDblClickRow,
			onLoadSuccess:${meta.firstLowerClassName}Datagrid.onLoadSuccess" 
			pagination="true" 
			pageSize="${r'${defaultPageSize}'}" 
			pageList="${r'${defaultPageList}'}">
       	 	<thead>
            <tr>
            <#list meta.entityCols as col>
                <#if col.pkFlag == false && col.fieldName != "tenantId">
                <th data-options="field:'${col.fieldName}',width:150" editor="{type:'textbox'<#if col.nullable != "1">, options:{required:true,missingMessage:'此输入项为必填项'}</#if>}"><b>${col.colDesc}</b></th>
                </#if>
            </#list>
            </tr>
            </thead>
        </table>
       	<div id="${meta.firstLowerClassName}_toolbar" style="display: none;"> 
       		<div class="gridSearchBar" style="height: 40px;">
			<form id="${meta.firstLowerClassName}SearchForm">
       			<table width="100%">
						<tr>
							<td width="60px" align="right"><span>条件1:</span></td>
							<td width="120px;"><input name=" "></td>
							<td width="120px" align="right"><span>条件2:</span></td>
							<td><input name=" "></td>
							<td style="text-align: right;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search">查询</a>
							&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear">清空</a>
							</td>
						</tr>
					</table>
       	</form>
		</div>
			<shiro:hasPermission name="<%= Auths.${meta.tableNameUC}_ADD %>">
    			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-star-add" plain="true" tag="add">添加</a>
    		</shiro:hasPermission>
    		<shiro:hasPermission name="<%= Auths.${meta.tableNameUC}_EDIT %>">
    			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-star-edit" plain="true" tag="edit">修改</a>
    		</shiro:hasPermission>
    		<shiro:hasPermission name="<%= Auths.${meta.tableNameUC}_DELETE %>">
    			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-star-del" plain="true" tag="del">删除</a>
    		</shiro:hasPermission>
    		<shiro:hasPermission name="<%= Auths.${meta.tableNameUC}_VIEW %>">
       			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
       		</shiro:hasPermission>
    	</div>   	
      </div>
      <div id="sysRole_contextMenu" class="easyui-menu" style="width: 120px;">
  		<shiro:hasPermission name="<%= Auths.${meta.tableNameUC}_ADD %>">
  			<div iconCls="m-icon-star-add" tag="add">添加</div>
  		</shiro:hasPermission>
  		<shiro:hasPermission name="<%= Auths.${meta.tableNameUC}_EDIT %>">
  			<div iconCls="m-icon-star-edit" tag="edit">修改</div>
  		</shiro:hasPermission>
	    <shiro:hasPermission name="<%= Auths.${meta.tableNameUC}_DELETE %>">
	    	<div iconCls="m-icon-star-del" tag="del">删除</div>
	    </shiro:hasPermission>		
	</div>
       					 
<script type="text/javascript" src="${r'${ctx}'}/static/js/${meta.module}/${meta.firstLowerClassName}_show.js"></script>
</body>

</html>