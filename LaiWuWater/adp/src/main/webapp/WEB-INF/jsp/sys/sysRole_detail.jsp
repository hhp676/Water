<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>角色详细页面</title>
<style type="text/css">
#sysRoleDetail_layout .panel-tool {
	  display: none;
	}
</style>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="sysRoleDetail_layout">
		<div data-options="region:'east',split:true" title="设置权限" style="width:350px">
		    <table fit=true name="authTree" singleSelect=false fitColumns=true id="sysRole_authTree">
        		<thead>
            			<tr>
            				<th data-options="field:'isChecked',checkbox:true"></th>  
            				<th data-options="field:'authId'" >权限Id</th>       				
                			<th data-options="field:'authName'" width=230>权限名称</th>              			
            			</tr>
        			</thead>
    		</table>
		</div>
		<div data-options="region:'center'" style="padding: 10 10 10 20px;" title="基本信息">
			<form id="sysRole_form" class="hgform">
				<input type="hidden" name="roleId" value="${roleId}"/>
				<input type="hidden" name="checkedAuthIds" />
				<input type="hidden" name="authIds" value="${sysRole.authIds}"/>
				<table class="hgtable">
					<tr>
						<td width="100px;">角色名称<font>*</font>:</td>
						<td><input  type="text" name="roleName"  value="${sysRole.roleName}"></input></td>
					</tr>
					<tr>
						<td>角色code<font>*</font>:</td>
						<td><input  type="text" name="roleCode" id ="roleCode"   value="${sysRole.roleCode}"></input></td>
					</tr>
					<tr>
						<td>描述:</td>
						<td><textarea name="descr" style="height:60px;width:300px;font-family:Arial;font-size:13.3333px;"  >${sysRole.descr}</textarea></td>
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
		var roleId = "${roleId}";			
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysRole_detail.js"></script>
</body>

</html>
