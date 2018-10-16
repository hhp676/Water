<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户组详细页面</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="sysUserGroupDetail_layout">
		<div data-options="region:'east',split:true" style="width:360px">
			 <div  class="easyui-tabs" fit=true tabPosition="top">
			 	<div title="设置部门" style="padding:2px">
            		<table  singleSelect=true fitColumns=true name="departTree">
        				<thead>
            				 <tr>
            				 	<th data-options="field:'departId'" width="10px" formatter=departTreeFmt>部门Id</th>
                				<th data-options="field:'departName'" width="80px">部门名称</th>             
            				</tr>
        				</thead>
    				</table>
        		</div>
			 	<div title="设置角色" style="padding:2px">
            		<table title=""  singleSelect=false fitColumns=true name="roleGrid">
        				<thead>
            				<tr>
            					<th data-options="field:'isChecked',checkbox:true"></th>
            					<th data-options="field:'roleId'">角色id</th> 
            					<th data-options="field:'roleName'" width=230>角色名称</th>          			
            				</tr>
        				</thead>
    				</table>
        		</div>
        		<div title="设置权限" style="padding:2px">
            		<table fit=true name="authTree" singleSelect=false fitColumns=true>
        				<thead>
            				<tr>
            					<th data-options="field:'isChecked',checkbox:true"></th>  
            					<th data-options="field:'authId'" >权限id</th>       				
                				<th data-options="field:'authName'" width=230>权限名称</th>              			
            				</tr>
        				</thead>
    				</table>
        		</div>
			 </div>
		   
    		
		</div>
		<div data-options="region:'center'" style="padding: 10 10 10 20px;" title="基本信息">
			<form id="sysUserGroup_form" class="hgform">
				<input type="hidden" name="groupId" value="${userGroup.groupId}"/>
				<input type="hidden" name="checkedAuthIds" />
				<input type="hidden" name="authIds" value="${userGroup.authIds}"/>
				<input type="hidden" name="checkedRoleIds" />
				<input type="hidden" name="roleIds" value="${userGroup.roleIds}"/>
				<input type="hidden" name="departId" value="${userGroup.departId}"/>
				<table class="hgtable">
					<tr>
						<td width="180px;">用户组名称<font>*</font>:</td>
						<td><input  type="text" name="groupName" value="${userGroup.groupName}" style="width: 180px;"></input></td>
					</tr>
					<tr>
						<td>用户组code<font>*</font>:</td>
						<td>
							<input  type="text" name="groupCode" value="${userGroup.groupCode}" style="width: 180px;"></input>
						</td>
					</tr>
					<tr name="fIdRow">
						<td>父用户组:</td>
						<td>
							<input  type="text" name="fName" value="${userGroup.fName}" style="width: 180px;" disabled="disabled"></input>
							<input  type="hidden" name="fid" value="${fid}"></input>
						</td>
					</tr>
										
					<tr>
						<td>描述:</td>
						<td><textarea name="descr" style="height:30px;width:300px;">${userGroup.descr}</textarea></td>
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
		var groupId = "${userGroup.groupId}";
		var fid = "${fid}";
		var boFid = "${userGroup.fid}";
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysUserGroup_detail.js"></script>
	
	
</body>

</html>
