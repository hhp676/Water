<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>部门列表页面</title>	
  </head>
  <body>
  	<div title="部门列表">
  	 <table class="easyui-treegrid" id="sysDepartment_grid"
            data-options="
                url: '${ctx}/sys/department/list',
                method: 'get',
                rownumbers: false,
                idField: 'departId',
                treeField: 'departName',
                showFooter:true,
                toolbar:'#department_toolbar',
                fitColumns:true,
                onContextMenu: sysDepartment.onContextMenu,
                onDblClickRow: sysDepartment.onDblClickRow
                
            ">
        <thead>
            <tr>
          		<th data-options="field:'departId'" width="20px">部门编号</th>
                <th data-options="field:'departName'" width="80px">部门名称</th>
                <th data-options="field:'departCode'" width="80px">部门code</th>
                <th data-options="field:'departFullname'" width="100px">部门全名</th>
                <th data-options="field:'engname'" width="80px">部门英文名称</th>
                <th data-options="field:'isFinalStr'" width="50px">不可修改</th>
            </tr>
        </thead>
    </table>
	<div id="sysDepartment_contextMenu" class="easyui-menu" style="width: 120px;">
		<div data-options="iconCls:'icon-add1'" tag="add">添加</div>
		<div data-options="iconCls:'icon-edit1'" tag="edit">修改</div>
		<div data-options="iconCls:'icon-del1'" tag="del">删除</div>
	</div>
	<div id="department_toolbar">
	     <div class="gridSearchBar">
			<form id="sysDepartmentSearchForm">
				<fieldset>
					<legend>高级查询</legend>
					<table>
						<tr>
							<td width="120px" align="right"><span>部门名称:</span></td>
							<td><input name="departName"></td>
							<td width="120px" align="right"><span>部门code:</span></td>
							<td><input name="departCode"></td>
						</tr>
					</table>
				</fieldset>
			</form>
			<div style="position: absolute; right: 10px; top: 20px; *top: 30px;">
				<a class="easyui-linkbutton" iconCls="m-icon-search" tag="search">查询</a>
				<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear">清空</a>
			</div>
		</div>
    	<shiro:hasPermission name="<%= Auths.SYS_DEPARTMENT_ADD %>"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add1" plain="true" tag="add">添加</a></shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_DEPARTMENT_EDIT %>"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit1" plain="true" tag="edit">修改</a></shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_DEPARTMENT_DELETE %>"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-del1" plain="true" tag="del">删除</a></shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_DEPARTMENT_VIEW %>"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a></shiro:hasPermission>
       
    </div>
    </div>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysDepartment_show.js"></script>
  </body>

</html>
