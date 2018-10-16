<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>部门详细页面</title>
</head>
<body>

	<div class="easyui-layout" data-options="fit:true" id="sysDepartmentDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 50px;">
			<form id="sysDepartment_form" class="hgform">
				<table class="hgtable">
					<input type="hidden" name="departId" value="${departId}"/>
					<input class="easyui-validatebox" type="hidden" name="fid" value="" ></input>
					<tr id="fNode"><td>父节点:</td><td><input class="easyui-validatebox" type="text" name="fNode" value="" style="width:120px;" readonly></input></td></tr>
					<tr><td width="160px;">部门名称<font>*</font>:</td><td><input class="easyui-validatebox" type="text" name="departName" value="${departObj.departName }" style="width:120px;"></input></td></tr>
					<tr><td>部门全名:</td><td><input class="easyui-validatebox" type="text" name="departFullname" value="${departObj.departFullname }" style="width:180px;"></input></td></tr>
					<tr><td>部门code:</td><td><input class="easyui-validatebox" type="text" name="departCode" value="${departObj.departCode }" style="width:180px;"></input></td></tr>
					<tr><td>英文名:</td><td><input class="easyui-validatebox" type="text" name="engname" value="${departObj.engname }" style="width:180px;"></input></td></tr>
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align: right; padding: 5px 5px 5px; background-color: #e0e0e0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" tag="cancel">取消</a>
		</div>
	</div>
	<script type="text/javascript">
	
	var deptId = "${departId}";
	var sign = "${sign}";
	var deptName = "${deptName}";
	var fId = "${departObj.fid}";
	
	</script>	
	<script type="text/javascript" src="${ctx}/static/js/sys/sysDepartment_detail.js"></script>

</body>

</html>
