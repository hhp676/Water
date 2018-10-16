<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户其他信息</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="sysUserExtension_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;">
			<form id="sysUserExtension_form" class="hgform">
				<input type="hidden" name="userId" value="${userId}"/>
				<table class="hgtable">
					<tr>
						<td width="150px;">员工编号：</td>
						<td>
							<input  type="text" name="staffNo" value="${extension.staffNo}"  style="width: 180px;" /></input>
						</td>
						
					</tr>
					<tr>
						<td>需要填写日报：</td>
						<td>
							<select name="needDiary" style="width:180px;">
								<c:forEach var="logicItem" items="${logicMap}">
									<option value="${logicItem.value}" <c:if test="${extension.needDiary==logicItem.value}">selected</c:if>>${logicItem.key}</option>
								</c:forEach>
							</select>
							
						</td>
						
					</tr>
					<tr>
						<td>入职日期：</td>
						<td>
							<input  type="text" name="entryDate" value="${extension.entryDate}" class="Wdate" style="width: 180px;"  onClick="WdatePicker({readOnly:true});"/></input>
						</td>
						
					</tr>
					<tr>
						<td>离职日期：</td>
						<td>
							<input  type="text" name="leaveDate" value="${extension.leaveDate}" class="Wdate" style="width: 180px;"  onClick="WdatePicker({readOnly:true});"/></input>
						</td>
						
					</tr>
					<tr>
						<td>员工状态：</td>
						<td>
							<select name="status" style="width:180px;">
								<c:forEach var="userStatus" items="${userStatusMap}">
									<option value="${userStatus.value}" <c:if test="${extension.status==userStatus.value}">selected</c:if>>${userStatus.key}</option>
								</c:forEach>
							</select>
						</td>
						
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
		var userId = "${userId}";			
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysUser_extension.js"></script>
</body>

</html>
