<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户日志详细页面</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="sysVarDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 30px;">
			<form id="sysVar_form" class="hgform">
				<table class="hgtable">
					<tr>
						<td width="130px;">日志Id:</td><td width="190px;"><input readonly type="text"  onmouseover="this.title=this.value" style="width: 160px" name="varName" value="${userLog.logId }" id="varName"></input></td>
						<td width="130px;">用户Id:</td><td><input  type="text" readonly onmouseover="this.title=this.value" style="width: 160px" name="varType" id="varType" value="${userLog.userId }"></input></td>
					</tr>
					
					<tr>
						<td>日志类型:</td><td><input readonly  type="text" style="width: 160px" name="varValue" id="varValue" value="${userLog.logTypeStr }"></input></td>
						<td>操作编码:</td><td><input readonly type="text" style="width: 160px" name="descr" value="${userLog.operCode }"></input></td>
					</tr>				
					
					<tr>
						<td>操作名称:</td><td><input readonly onmouseover="this.title=this.value" type="text" style="width: 160px" name="varType" id="varType" value="${userLog.operName }"></input></td>
						<td>操作IP:</td><td><input readonly onmouseover="this.title=this.value"  type="text" style="width: 160px" name="varType" id="varType" value="${userLog.operIP }"></input></td>
					</tr>
						
					<tr><td>操作时间:</td><td><input readonly type="text" style="width: 160px" name="varType" id="varType" value="${userLog.crtTimeStr }"></input></td></tr>
					
					<tr><td>备注:</td><td colspan="3"><textarea readonly  onmouseover="this.title=this.value" name="varType" style="height:50px;width:490px;" id="varType" >${userLog.remark }</textarea></td></tr>
				</table>
			</form>
		</div>
	</div>
</body>

</html>
