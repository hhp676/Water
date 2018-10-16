<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>操作权限详细页面</title>
</head>
<body>

	<div class="easyui-layout" data-options="fit:true" id="sysAuthOperDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;">
			<form id="sysAuthOper_form" class="hgform">
				<input type="hidden" name="authId" value="${authObj.authId}"/>
				<input type="hidden" name="authType" value="${authType}"/>
				<input type="hidden" name="fid" value="${authObj.fid}"/>
				<table class="hgtable">
					<tr>
						<td width="150px;">操作名称<font>*</font>:</td>
						<td width="200px;"><input type="text" name="authName" style="width: 160px" value="${authObj.authName}"></input></td>
						<td width="150px;">操作code<font>*</font>:</td><td>
						<input  type="text" name="authCode" style="width: 160px" value="${authObj.authCode}"></input></td>
					</tr>
					<tr>
						<td>所属模块:</td>
						<td><input type="text"  value="${authObj.fName}" disabled="disabled" style="width: 160px"></input></td>
						<td>操作类型<font>*</font>:</td>
						<td>
							<select id="cg_operId" class="easyui-combogrid" style="width:160px" name="operId"  data-options="
            					panelWidth: 320,idField: 'operId',textField: 'operName',url: '${ctx}/sys/authoper/list',fitColumns: true,
	                            method: 'get',columns: [[
                                    {field:'operCode',title:'操作code',width:80},
                                    {field:'operName',title:'操作名称',width:80},
                                    {field:'descr',title:'描述',width:100}]]">
    						</select>
						</td>
					</tr>
					<tr>
						<td>操作英文名称:</td>
						<td><input type="text" name="authEnname" style="width: 160px" value="${authObj.authEnname}"></input></td>
						<td>操作图标:</td>
						<td><input type="text" name="authIcon" style="width: 160px" value="${authObj.authIcon}"></input></td>
					</tr>	
					<tr>
						<td>排序id<font>*</font>:</td>
						<td><input  type="text" name="orderId" style="width: 160px" value="${authObj.orderId}"></input></td>
						<td>可见:</td>
						<td>
							<select name="isVisible" style="width:160px;">
								<c:forEach var="logicItem" items="${logicMap}">
									<option value="${logicItem.value}" <c:if test="${authObj.isVisible==logicItem.value}">selected</c:if>>${logicItem.key}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>uri:</td>
						<td colspan="3"><input  type="text" name="authUri" style="width: 520px" value="${authObj.authUri}"></input></td>
					</tr>
					<tr>
						<td>描述:</td>
						<td colspan="3"><textarea name="descr" style="height:30px;width:520px;">${authObj.descr}</textarea></td>
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
		var operId = "${authObj.operId}";
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysAuth_Oper_detail.js"></script>
</body>

</html>
