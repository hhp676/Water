<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>个人中心</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="personalCenter_layout">
		 <div  class="easyui-tabs" fit=true tabPosition="top" data-options="tabWidth:150,tabHeight:65" >
			<div title="<span class='tt-inner'><img src='${ctx}/static/images/look.png'/><br><br>查看个人资料</span>" style="padding:10px" >
				<h3 style="border-bottom:2px dashed #aaa;padding-bottom: 12px;">个人资料</h3>
            	<form class="changeform" id="userInfoForm">
            		<table>
            			<tr>
            				<td width="120px" align="center">用户名</td>
            				<td width="200px;"><input type="text" name=""  style="width: 180px;" disabled="disabled" value="${user.userName}"/></td>
            				<td width="100px" align="center">英文名</td>
            				<td><input type="text" name="" style="width: 180px;" disabled="disabled" value="${user.engName}"/></td>
            			</tr>
            			<tr>
            				<td align="center">邮箱</td>
            				<td><input type="text" name="" style="width: 180px;" disabled="disabled" value="${user.email}"/></td>
            				<td align="center">手机号</td>
            				<td><input type="text" name="" style="width: 180px;" disabled="disabled" value="${user.mobile}"/></td>
            			</tr>
            			<tr>
            				<td align="center">性别</td>
            				<td><input type="radio"  value="1" name="sex">男</input><input type="radio"  value="0" name="sex">女</input></td>
            				<td align="center">生日</td>
            				<td><input type="text" name="" style="width: 180px;" disabled="disabled" value="${user.birthday}"/></td>
            			</tr>
            			<tr style="display: none;">
            				<td align="center">是否被锁定</td>
            				<td><input type="radio" checked="checked">否</input></td>
            				<td align="center">创建日期</td>
            				<td><input type="text" name="" style="width: 180px;" disabled="disabled" value="${user.crtTimeStr}"/></td>
            			</tr>
            		</table>
            	</form>
            	<h3 style="padding-bottom: 5px;padding-top: 10px;">权限信息</h3>
       			<div style="padding-left: 30px;">
       				<table id="centerpage_authTable">
       					<tr>
       						<td width="100px;" class="td1">应用权限:</td>
       						<td>${appAuth}</td>
       					</tr>
       					<tr>
       						<td class="td1">模块权限:</td>
       						<td>${moduleAuth}</td>
       					</tr>
       					<tr>
       						<td class="td1">操作权限:</td>
       						<td>${operAuth}</td>
       					</tr>
       					<tr>
       						<td class="td1">角色信息:</td>
       						<td>${userRoles}</td>
       					</tr>
       					<tr>
       						<td class="td1">用户组信息:</td>
       						<td>${userUgroups}</td>
       					</tr>
       				</table>
       			</div>
            	
            </div>
        	<div title="<span class='tt-inner'><img src='${ctx}/static/images/editbig.png'/><br><br>修改个人资料</span>" style="padding:20px" >
            	<form id="changeUserInfoForm" class="hgform changeform">
            		<table class="hgtable">
            			<tr>
            				<td width="200px" align="center">用户名<font>*</font></td>
            				<td width="200px;"><input type="text" name="userName"  style="width: 180px;" value="${user.userName}"/></td>
            				
            			</tr>
            			<tr>
            				<td  align="center">英文名</td>
            				<td><input type="text" name="engName" style="width: 180px;" value="${user.engName}"/></td>
            			</tr>
            			<tr>
            				<td align="center">邮箱</td>
            				<td><input type="text" name="email" style="width: 180px;"  value="${user.email}"/></td>
            				
            			</tr>
            			<tr>
            			
            				<td align="center">手机号</td>
            				<td><input type="text" name="mobile" style="width: 180px;" value="${user.mobile}"/></td>
            			</tr>
            			<tr>
            				<td>&nbsp;</td>
            				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a></td>
            				
            			</tr>
            			
            		</table>
            	</form>
        	</div>
        	<div title="<span class='tt-inner'><img src='${ctx}/static/images/key.png'/><br><br>修改密码</span>" style="padding:20px">
            	<form id="changePassForm" class="hgform changeform">
            		<table class="hgtable">
            			<tr>
            				<td width="200px" align="center">旧密码<font>*</font>:</td>
            				<td width="200px;"><input type="password" name="oldPwd"  style="width: 180px;"/></td>
            				
            			</tr>
            			<tr>
            				<td  align="center">新密码<font>*</font>:</td>
            				<td><input type="password" name="newPwd" id="newPwd" style="width: 180px;"/></td>
            			</tr>
            			<tr>
            				<td align="center">确认新密码<font>*</font>:</td>
            				<td><input type="password" name="confirmNewPwd" style="width: 180px;"/></td>
            				
            			</tr>
            			<tr>
            				<td>&nbsp;</td>
            				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a></td>
            				
            			</tr>
            			
            		</table>
            	</form>
        	</div>
        	
		</div>
		
	</div>
	<script type="text/javascript">
		var sex = "${user.sex}";
	</script>
	<script type="text/javascript" src="${ctx}/static/js/page/personalCenter.js"></script>
</body>

</html>
