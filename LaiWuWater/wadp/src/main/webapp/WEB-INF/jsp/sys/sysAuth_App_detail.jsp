<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>应用权限详细页面</title>
</head>
<body>

	<div class="easyui-layout" data-options="fit:true" id="sysAuthAppDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;">
			<form id="sysAuthApp_form" class="hgform">
				<input type="hidden" name="authId" value="${authObj.authId}"/>
				<input type="hidden" name="authType" value="${authType}"/>
				<table class="hgtable">
					<tr>
						<td width="150px;">应用名称<font>*</font>:</td>
						<td width="200px;"><input type="text" name="authName" value="${authObj.authName}" style="width: 160px"></input></td>
						<td width="150px;">应用code<font>*</font>:</td><td>
						<input  type="text" name="authCode" style="width: 160px" value="${authObj.authCode}"></input></td>
					</tr>
					<tr>
						<td>应用英文名称:</td>
						<td colspan="3"><input type="text" name="authEnname" style="width: 520px" value="${authObj.authEnname}"></input></td>
					</tr>	
					<tr><td>描述:</td><td colspan="3"><textarea name="descr" style="height:30px;width:520px;">${authObj.descr}</textarea></td></tr>
					
					
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align: right; padding: 5px 5px 5px; background-color: #e0e0e0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" tag="cancel">取消</a>
		</div>
	</div>

	<script type="text/javascript">
		var authId = "${authObj.authId}";	
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysAuth_App_detail.js"></script>
</body>

</html>
