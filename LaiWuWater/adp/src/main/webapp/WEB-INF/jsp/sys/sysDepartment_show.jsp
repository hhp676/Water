<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>部门列表页面</title>	
  </head>
  <body>
   <div class="easyui-tabs" fit=true tabPosition="top" id="sysUser_tab" data-options="tabWidth:200">
  	<div title="部门列表" data-options="closable:false" style="overflow: hidden">
  	 <table fit=true  class="easyui-treegrid" id="sysDepartment_grid" toolbar="#department_toolbar"  showFooter=true
  	  	fitColumns=false
            data-options="
                method: 'get',
                idField: 'departId',
                treeField: 'departName',
                onContextMenu: sysDepartment.onContextMenu,
                onLoadSuccess: sysDepartment.onLoadSuccess, 
                onDblClickRow: sysDepartment.onDblClickRow,
                url: '${ctx}/sys/department/list?number='+ new Date().getTime()
                 ">
        <thead>
            <tr>
          		<th data-options="field:'departId'" width="80px">部门编号</th>
                <th data-options="field:'departName'" width="240px">部门名称</th>
                <th data-options="field:'departCode'" width="200px">部门code</th>
                <th data-options="field:'departFullname'" width="240px">部门全名</th>
                <th data-options="field:'engname'" width="200px">部门英文名称</th>
                <th data-options="field:'userCount',width:80,formatter:sysDepartment.showSysDepartmentUserCountFormatter">人员统计</th>
                <th data-options="field:'isFinalStr'" width="80px">不可修改</th>
            </tr>
        </thead>
    </table>
    </div>
    <div title="图表统计" data-options="closable:false" href="${ctx}/sys/chart/sysDepart"
		style="overflow: hidden">
		
  	</div>

    </div>
    	
	<div id="department_toolbar">
	     <div class="gridSearchBar" style="height: 40px;">
			<form id="sysDepartmentSearchForm">
					<table width="100%" height="100%">
						<tr>
							<td width="60px" align="right"><span>部门名称:</span></td>
							<td width="120px;"><input name="departName"></td>
							<td width="120px" align="right"><span>部门code:</span></td>
							<td><input name="departCode"></td>
							<td style="text-align: right;padding-top:4px;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search" style="vertical-align:80%;">查询</a>
							&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear" style="vertical-align:80%;">清空</a>
							</td>
						</tr>
					</table>
			</form>
		
		</div>
    	<shiro:hasPermission name="<%= Auths.SYS_DEPARTMENT_ADD %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add1" plain="true" tag="add">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_DEPARTMENT_EDIT %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit1" plain="true" tag="edit">修改</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_DEPARTMENT_DELETE %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-del1" plain="true" tag="del">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_DEPARTMENT_VIEW %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
    	</shiro:hasPermission>
       
	    <div id="sysDepartment_contextMenu" class="easyui-menu" style="width: 120px;">
			<shiro:hasPermission name="<%= Auths.SYS_DEPARTMENT_ADD %>">
				<div data-options="iconCls:'icon-add1'" tag="add">添加</div>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.SYS_DEPARTMENT_EDIT %>">
				<div data-options="iconCls:'icon-edit1'" tag="edit">修改</div>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.SYS_DEPARTMENT_DELETE %>">
				<div data-options="iconCls:'icon-del1'" tag="del">删除</div>
			</shiro:hasPermission>
		</div>
    </div>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysDepartment_show.js"></script>
  </body>

</html>
