<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统变量详细页面</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="sysVarDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;">
			<form id="sysVar_form" class="hgform">
				<input type="hidden" name="varId" value="${varId}"/>
				<table class="hgtable">
					<tr>
						<td width="130px;">变量名称<font>*</font>:</td>
						<td><input type="text"   name="varName" value="${sysVariable.varName }" id="varName"></input></td>
					</tr>
					<tr>
						<td>变量值<font>*</font>:</td>
						<td><input type="text" name="varValue" id="varValue" value="${sysVariable.varValue }"></input></td>
					</tr>
					<tr>
						<td>变量类型:</td>
						<td><input   type="text" name="varType" id="varType" value="${sysVariable.varType }"></input></td>
					</tr>					
					<tr>
						<td>描述:</td>
						<td><textarea    name="descr" id="descr" class="easyui-validatebox" style="height:60px;width:300px;font-family:Arial">${sysVariable.descr }</textarea></td>
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
		var varId = "${varId}";			
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysVar_detail.js"></script>
</body>

</html>
