<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
	<div title="列表">
		<table  id="waPlanYearWaterData_datagrid" singleSelect=true  fitColumns=true  toolbar='#waPlanYearWaterData_toolbar'
			class="easyui-datagrid" data-options="
				url: '${ctx}/wa/WaPlanYearWaterData/list',
				singleSelect:true,
				collapsible:true,
				view:groupview,
				groupField:'companyName',
				groupFormatter:function(value,rows){
					return '<b>'+value + '</b>';  <%--' - ' + rows.length + ' 条--%>
				}
			"
			pagination="true"
			pageSize="${defaultPageSize}"
			pageList="${defaultPageList}">
			<thead>
			<tr>
			   <%-- <th style="display: none" data-options="field:'companyCode',width:0" editor="{type:'textbox'}"></th>--%>
				<%--<th style="display: none" data-options="field:'companyName',width:0" editor="{type:'textbox'}"></th>--%>
				<th data-options="field:'planYear',width:150" editor="{type:'textbox'}"><b>计划的年份时间</b></th>
				<th data-options="field:'planYearAvgWater',width:150" editor="{type:'textbox'}"><b>计划的年份用水量</b></th>
				<%--<th data-options="field:'planYearEditWater',width:150" editor="{type:'textbox'}"><b>单位上报用水量</b></th>
				<th data-options="field:'actYearWater',width:150" editor="{type:'textbox'}"><b>当前年份用水总量</b></th>--%>
			</tr>
			</thead>
		</table>
		<div id="waPlanYearWaterData_toolbar" style="display: none;">
			<div class="gridSearchBar" style="height: 40px;">
				<form id="waPlanYearWaterDataSearchForm">
					<table width="100%">
						<tr>
							<td width="60px" align="right"><span>节水代码:</span></td>
							<td width="120px;"><input name="year_companyCode"></td>
							<td width="60px" align="right"><span>单位名称:</span></td>
							<td width="120px;"><input name="year_companyName"></td>

							<td style="text-align: right;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search">查询</a>
							&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>

			<shiro:hasPermission name="<%= Auths.WA_PLAN_YEAR_WATER_ADD%>">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-star-add" plain="true" tag="add">添加</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.WA_PLAN_YEAR_WATER_EDIT%>">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-star-edit" plain="true" tag="edit">修改</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.WA_PLAN_YEAR_WATER_DELETE%>">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-star-del" plain="true" tag="del">删除</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.WA_PLAN_YEAR_WATER_VIEW%>">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.WA_PLAN_YEAR_WATER_IMPORT%>">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="import">年计划用水导入</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.WA_PLAN_YEAR_WATER_PRINT%>">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="print">打印</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.WA_PLAN_YEAR_WATER_COMPUTE%>">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="compute">计算下年用水量</a>
			</shiro:hasPermission>
		</div>
	</div>

<script type="text/javascript" src="${ctx}/static/js/wa/waterPlanManagement/year/waPlanYearWaterData_show.js"></script>
 <script type="text/javascript" src="${ctx}/static/js/wa/jquery.PrintArea.js"></script>
</body>

</html>