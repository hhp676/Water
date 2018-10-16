<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>通知详细页面</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="sysNotifyDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;">
			<form id="sysNotify_form" class="hgform">
				<input type="hidden" name="notifyId" value="${notifyId}"/>
				<table class="hgtable">
					<tr>
						<td width="130px;">用户id:</td>
						<td><input type="text" name="userId"   value="${sysNotify.userId}" style="width:180px;"></input></td>
					</tr>
					<tr>
						<td width="130px;">模板id:</td>
						<td><input type="text" name="tempId"   value="${sysNotify.tempId}" style="width:180px;"></input></td>
					</tr>
					<tr>
						<td width="130px;">标题</td>
						<td><input type="text" name="title"   value="${sysNotify.title}" style="width:450px;"></input></td>
					</tr>	
					<tr>
						<td>内容:</td>
						<td><textarea name="content"    class="easyui-validatebox" style="height:190px;width:450px;font-family:Arial">${sysNotify.content}</textarea></td>
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
		var notifyId = "${notifyId}";
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysNotify_detail.js"></script>
</body>

</html>
