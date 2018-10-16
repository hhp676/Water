<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>改密页面</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="sysUserChangePwd_layout">
		<div data-options="region:'center'">
			<form id="sysUserChangePwd_form" class="hgform">
				<input type="hidden" name="userId" value="${userId}"/>				
				<table class="hgtable" style="margin-top:43px">
					<tr>
						<td width="50px;" style="vertical-align:middle;">新密码<font>*</font>:</td>
						<td width="200px;" style="vertical-align:middle;">
							<span id="pwdSpan">
							    <input type="password" name="newPwd" id="newPwd" style="width:180px;"></input>
							</span>						
						</td>
						<td style="vertical-align:middle;">
						    <input type="button" name="getRandomPassword" value="获取随机密码" style="height:25px;line-height:20px;box-sizing:border-box;"></input>&nbsp;					    
						</td>						
					</tr>
					<tr>
					    <td></td>
					    <td>
					        <input type="checkbox" id="checkboxId" onclick="changePwd()" style=""/>显示密码 
					    </td>
					    <td></td>
					</tr>
					<tr>
					    <td></td>
					    <td><span style="color: blue;" name="randomPasswordSpan"></span></td>
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
		
		//密码：明文、密文切换
		function changePwd(){
			var styleStr = "width:180px;";
			exchangePwd_js("checkboxId","newPwd","pwdSpan","newPwd",styleStr);	
		}
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysUser_changPwd.js"></script>
</body>

</html>
