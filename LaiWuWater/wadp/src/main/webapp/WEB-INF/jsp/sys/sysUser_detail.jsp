<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户详细页面</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="sysUserDetail_layout">
		<div data-options="region:'east',split:true" style="width:360px">
			 <div  class="easyui-tabs" fit=true tabPosition="top">
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
        		<div title="设置用户组" style="padding:2px">
            		<table fit=true name="ugroupTree" singleSelect=false fitColumns=true>
        				<thead>
            				<tr>
            					<th data-options="field:'isChecked',checkbox:true"></th>  
            					<th data-options="field:'groupId'" >用户组id</th>       				
                				<th data-options="field:'groupName'" width=230>用户组名称</th>              			
            				</tr>
        				</thead>
    				</table>
        		</div>
			 </div>
		   
    		
		</div>
		<div data-options="region:'center'" style="padding: 10 10 10 20px;" title="基本信息">
			<form id="sysUser_form" class="hgform">
				<input type="hidden" name="userId" value="${sysUser.userId}"/>
				<input type="hidden" name="checkedAuthIds" />
				<input type="hidden" name="authIds" value="${sysUser.authIds}"/>
				<input type="hidden" name="checkedRoleIds" />
				<input type="hidden" name="checkedUgroupIds" />
				<input type="hidden" name="roleIds" value="${sysUser.roleIds}"/>
				<table class="hgtable">
					<tr>
						<td width="200px;">用户名<font>*</font>:</td>
						<td><input  type="text" name="userName" value="${sysUser.userName}" style="width: 180px;"></input></td>
					</tr>
					<tr>
						<td>登录名<font>*</font>:</td>
						<td>
							<input  type="text" name="loginName" value="${sysUser.loginName}" style="width: 180px;"></input>
							<!-- 可解决chrome下避免自动填充登录名和密码！ --><input type="hidden" name="loginName" tag="mixField"></input>
						</td>
					</tr>
					<tr name="pwd" style="display: none;">
						<td>初始密码<font>*</font>:</td>
						<td><input  type="password" name="plainPassword"  style="width: 180px;"></input></td>
					</tr>
					<tr>
						<td>英文名:</td>
						<td><input  type="text" name="engName" value="${sysUser.engName}" style="width: 180px;"></input></td>
					</tr>
					<tr>
						<td>性别:</td>
						<td>
							<select name="sex">
								<c:forEach var="sexItem" items="${sexMap}">
									<option value="${sexItem.value}" <c:if test="${sysUser.sex==sexItem.value}">selected</c:if>>${sexItem.key}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>生日:</td>
						<td><input  type="text" name="birthday" value="${sysUser.birthday}" class="Wdate" style="width: 180px;"  onClick="WdatePicker({readOnly:true});"/></input></td>
					</tr>
					<tr>
						<td>手机:</td>
						<td><input  type="text" name="mobile" value="${sysUser.mobile}" style="width: 180px;"></input></td>
					</tr>
					<tr>
						<td>联系电话:</td>
						<td><input  type="text" name="telephone" value="${sysUser.telephone}" style="width: 180px;"></input></td>
					</tr>	
					<tr>
						<td>email:</td>
						<td><input  type="text" name="email" value="${sysUser.email}" style="width: 300px;"></input></td>
					</tr>	
					<tr>
						<td>地址:</td>
						<td><input  type="text" name="address" value="${sysUser.address}" style="width: 300px;"></input></td>
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
		var userId = "${sysUser.userId}";			
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysUser_detail.js"></script>
</body>

</html>
