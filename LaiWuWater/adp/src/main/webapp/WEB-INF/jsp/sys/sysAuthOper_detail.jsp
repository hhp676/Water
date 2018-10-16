<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>操作类型详细页面</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="sysOperDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;">
			<form id="sysOper_form" class="hgform">
				<input type="hidden" name="operId" value="${operId}"/>
				<table class="hgtable">
					<tr>
						<td width="130px;">操作类型code<font>*</font>:</td>
						<td><input type="text" name="operCode" value="${authOper.operCode }" style="width: 160px"></input></td>
					</tr>
					<tr>
						<td>操作类型名称<font>*</font>:</td>
						<td><input type="text" name="operName" value="${authOper.operName}" style="width: 160px"></input></td>
					</tr>
					<tr>
						<td>操作类型英文名称:</td><td>
						<input  type="text" name="operEnname" value="${authOper.operEnname}" style="width: 160px"></input></td>
					</tr>					
					
					<tr>
						<td>操作类型图标:</td><td>
						<input  type="text" name="operIcon" value="${authOper.operIcon }" style="width: 160px"></input></td>
					</tr>
					<tr>
						<td>排序id<font>*</font>:</td><td>
						<input  type="text" name="orderId"  value="${authOper.orderId }" style="width: 160px"></input></td>
					</tr>
					<tr>
						<td>描述:</td>
						<td><textarea name="descr" style="height:50px;width:300px;font-family:Arial">${authOper.descr}</textarea></td>
					</tr>					
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align: right; padding: 5px 5px 5px; background-color: #e0e0e0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" tag="cancel">取消</a>
		</div>
		
	</div>
	<script type="text/javascript">
		var operId = "${operId}";
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysOper_detail.js"></script>
</body>

</html>
