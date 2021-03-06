<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
</head>
<body>
 <div title="列表">
 		<table  id="waCompanyInfo_datagrid" singleSelect=true  fitColumns=true  toolbar='#waCompanyInfo_toolbar' 
 		    class="easyui-datagrid" data-options="url:'${ctx}/wa/WaCompanyInfo/list'"
			pagination="true" 
			pageSize="${defaultPageSize}" 
			pageList="${defaultPageList}">
       	 	<thead>
            <tr>
				<th data-options="field:'companyCode',width:150" editor="{type:'textbox'}"><b>节水代码</b></th>
                <th data-options="field:'companyName',width:250" editor="{type:'textbox'}"><b>单位名称</b></th>
                <th data-options="field:'contactNum',width:180" editor="{type:'textbox'}"><b>联系电话</b></th>
                <th data-options="field:'contactMan',width:120" editor="{type:'textbox'}"><b>联系人</b></th>
                <th data-options="field:'department',width:150" editor="{type:'textbox'}"><b>所属部门</b></th>
                <th data-options="field:'userType',width:150" editor="{type:'textbox'}"><b>用户类别</b></th>
                <th data-options="field:'email',width:150" editor="{type:'textbox'}"><b>邮箱</b></th>
                <th data-options="field:'telphone',width:180" editor="{type:'textbox'}"><b>手机号码</b></th>
                <th data-options="field:'cityArea',width:150" editor="{type:'textbox'}"><b>行政区域</b></th>
                <th data-options="field:'postcode',width:120" editor="{type:'textbox'}"><b>邮编</b></th>
                <th data-options="field:'peopleCount',width:120" editor="{type:'textbox'}"><b>单位人数</b></th>
                <th data-options="field:'acreage',width:120" editor="{type:'textbox'}"><b>营业面积</b></th>
                <th data-options="field:'waterType',width:150" editor="{type:'textbox'}"><b>用水性质</b></th>
                <th data-options="field:'isImport',width:150" editor="{type:'textbox'}"><b>重点用户</b></th>
            </tr>
            </thead>
        </table>
       	<div id="waCompanyInfo_toolbar" style="display: none;"> 
       		<div class="gridSearchBar" style="height: 40px;">
			<form id="￥{3333}">
       			<table width="100%">
						<tr>
							<td width="60px" align="right"><span>节水代码:</span></td>
							<td width="120px;"><input name="com_companyCode"></td>
							<td width="120px" align="right"><span>单位名称:</span></td>
							<td><input name="com_companyName"></td>
							<td width="120px" align="right"><span>重点用户:</span></td>
							<td>
								<select style="width: 120px;" name="com_isImport">
									<option value="">全部</option>
									<option value="1">是</option>
									<option value="0">否</option>
								</select>
							<td>
						</td></td>
							<td style="text-align: right;"><a class="easyui-linkbutton" iconCls="m-icon-search" tag="search">查询</a>
							&nbsp;
								<a class="easyui-linkbutton" iconCls="m-icon-clear" tag="clear">清空</a>
							</td>
						</tr>
					</table>
       		</form>
			</div>
			<shiro:hasPermission name="<%= Auths.WA_COMPANY_INFO_ADD %>">
    			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-star-add" plain="true" tag="add">添加</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.WA_COMPANY_INFO_EDIT %>">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-star-edit" plain="true" tag="edit">修改</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.WA_COMPANY_INFO_DELETE %>">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-star-del" plain="true" tag="del">删除</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.WA_COMPANY_INFO_VIEW %>">
       				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.WA_COMPANY_INFO_TOTAL %>">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="statisticsView">用水情况统计</a>
			</shiro:hasPermission>
			<shiro:hasPermission name="<%= Auths.WA_COMPANY_INFO_IMPORT %>">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-import" plain="true" tag="import">批量导入</a>
			</shiro:hasPermission>
			</div>
      </div>
      <div id="sysRole_contextMenu" class="easyui-menu" style="width: 120px;">
  			<div iconCls="m-icon-star-add" tag="add">添加</div>
  			<div iconCls="m-icon-star-edit" tag="edit">修改</div>
	    	<div iconCls="m-icon-star-del" tag="del">删除</div>
	  </div>
 <script type="application/javascript">
	 alert(${pageContext.request.contextPath});
 </script>
<script type="text/javascript" src="${ctx}/static/js/wa/waCompanyInfo/waCompanyInfo_show.js"></script>
</body>

</html>