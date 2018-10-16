<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录日志详情</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="sysLoginLogDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 30px;">
			<form id="sysLoginLogDetail_form" class="hgform">
				<table class="hgtable">
					<tr>
						<td width="130px;">日志Id:</td><td width="190px;"><input readonly type="text"  onmouseover="this.title=this.value" style="width: 160px" name="varName" value="${userLoginLog.loginLogId }"></input></td>
						<td width="130px;">用户Id:</td><td><input  type="text" readonly onmouseover="this.title=this.value" style="width: 160px" name="varType" id="varType" value="${userLoginLog.accountId }"></input></td>
					</tr>
					
					<tr>
						<td>操作结果:</td><td><input readonly  type="text" style="width: 160px" name="varValue" value="${userLoginLog.operResStr }"></input></td>
						<td>操作编码:</td><td><input readonly type="text" style="width: 160px" name="descr" value="${userLoginLog.operCode }"></input></td>
					</tr>				
					
					<tr>
						<td>操作名称:</td><td><input readonly onmouseover="this.title=this.value" type="text" style="width: 160px" name="varType" value="${userLoginLog.operName }"></input></td>
						<td>操作IP:</td><td><input readonly onmouseover="this.title=this.value"  type="text" style="width: 160px" name="varType" value="${userLoginLog.operIp }"></input></td>
					</tr>
						
					<tr><td>操作时间:</td><td><input readonly type="text" style="width: 160px" name="varType" id="varType" value="${userLoginLog.crtTimeStr }"></input></td></tr>
					
					<tr><td>备注:</td><td colspan="3"><textarea readonly  onmouseover="this.title=this.value" name="varType" style="height:50px;width:490px;" id="varType" >${userLoginLog.content }</textarea></td></tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>