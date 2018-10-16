<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>改密页面</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="sysUserChangePwd_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;">
			<form id="sysUserChangePwd_form" class="hgform">
				<input type="hidden" name="userId" value="${userId}"/>
				<table class="hgtable">
					<tr>
						<td width="80px;">新密码<font>*</font>:</td>
						<td>
							<input type="password" name="newPwd"></input>
						</td>
						<td><input type="button" name="getRandomNum" value="获取随机密码"></input></td>
					</tr>
					<tr>
						<td></td>
						<td colspan="2"><span style="color: blue;" name="randomNumSpan"></span></td>
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
	<script type="text/javascript" src="${ctx}/static/js/sys/sysUser_changPwd.js"></script>
</body>

</html>
