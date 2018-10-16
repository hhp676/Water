<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>模块权限详细页面</title>
</head>
<body>

	<div class="easyui-layout" data-options="fit:true" id="sysAuthModuleDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;">
			<form id="sysAuthModule_form" class="hgform">
				<input type="hidden" name="authId" value="${authObj.authId}"/>
				<input type="hidden" name="authType" value="${authType}"/>
				<input type="hidden" name="fid" value="${authObj.fid}"/>
				<table class="hgtable" style="margin-left:50px;margin-top:20px;">
					<tr>
						<td width="120px;">模块名称<font>*</font>:</td>
						<td width="200px;"><input  type="text" name="authName"  value="${authObj.authName}" style="width: 160px;box-sizing: border-box;"></input></td>
						<td width="120px;">模块code<font>*</font>:</td>
						<td width="200px;"><input type="text" name="authCode"  style="width: 160px;box-sizing: border-box;" value="${authObj.authCode}"></input></td>
					</tr>
					<tr>
						<td>父模块:</td>
						<td><input type="text"  value="${authObj.fName}"    disabled="disabled" style="width: 160px;box-sizing: border-box;"></input></td>
						<td>模块图标:</td>
						<td><input  type="text" name="authIcon" style="width: 160px;box-sizing: border-box;" value="${authObj.authIcon}"></input></td>
						
					</tr>
					<tr>
						<td>模块英文名称:</td>
						<td><input  type="text" name="authEnname"  style="width: 160px;box-sizing: border-box;" value="${authObj.authEnname}"></input></td>
						<td>可见:</td>
						<td>
							<select name="isVisible" style="height:21px;width:160px;box-sizing: border-box;">
								<c:forEach var="logicItem" items="${logicMap}">
									<option value="${logicItem.value}" <c:if test="${authObj.isVisible==logicItem.value}">selected</c:if>>${logicItem.key}</option>
								</c:forEach>
							</select>
						</td>
					</tr>	
					<tr>
						<td>排序id<font>*</font>:</td>
						<td><input type="text" name="orderId" style="width: 160px;box-sizing: border-box;" value="${authObj.orderId}"></input></td>
					</tr>
					<tr>
						<td>uri:</td>
						<td colspan="3"><input  type="text" name="authUri" style="width: 488px;box-sizing: border-box;" value="${authObj.authUri}"></input></td>
					</tr>
					<tr>
						<td>描述:</td>
						<td colspan="3"><textarea name="descr"   style="height:60px;width:488px;box-sizing: border-box;font-family:Arial;font-size:13.3333px;">${authObj.descr}</textarea></td>
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
		var authId = "${authObj.authId}";
		var mode = "add";
		if (authId) mode = "edit";
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysAuth_Module_detail.js"></script>
</body>

</html>
