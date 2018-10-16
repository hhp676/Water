<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ajax</title>
</head>
<body>

	<div class="easyui-layout" data-options="fit:true" id="userGroupDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 50px;">
			<form action="">
				<table class="hgtable">
					<tr><td width="160px;">部门名称:</td><td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td></tr>
					<tr><td>部门全名:</td><td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td></tr>
					<tr><td>部门code:</td><td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td></tr>
					
					<tr><td>英文名:</td><td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td></tr>
				
					<tr><td>父id:</td><td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" disabled="disabled" value="1"></input></td></tr>
					<tr><td>不可修改:</td><td><select name="" style="width:140px;"><option>是</option><option>否</option></td></tr>
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align: right; padding: 5px 5px 5px; background-color: #e0e0e0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" tag="cancel">取消</a>
		</div>
	</div>

	<script type="text/javascript">
		$("#userGroupDetail_layout [tag='ok']").click(function(){
			alert("ok1");
		});
		$("#userGroupDetail_layout [tag='cancel']").click(function(){
			$("#userGroupDetail_layout").parent().window("close");
		});
	</script>
</body>

</html>
