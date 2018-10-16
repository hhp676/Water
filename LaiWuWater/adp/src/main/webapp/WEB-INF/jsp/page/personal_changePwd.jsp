<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="changePersonalPass_layout">
		 <%--<div id="changePersonalPwdTab" class="easyui-tabs" fit=true tabPosition="top" data-options="tabWidth:150,tabHeight:65" >
			
        	
        	 <div title="<span class='tt-inner'><img src='${ctx}/static/images/icons/key.png'/><br><br>修改密码</span>" style="padding:20px"> --%>
        	 
        	<!--  <div style="    border: 1px solid #D4E66C;    margin: 20px 50px;    padding: 15px;    background-color: #FAFBD7;">您已经3个月未修改过登录密码，请更改您的登录密码。
        	 <a href="###" onclick="openFindPasswordDialog()" style="padding-left:15px">忘记密码</a>
        	 </div> -->
            	<form id="changePersonalPassForm" class="hgform changeform" style="padding:00px 0 0 0">
            		<table class="hgtable">
            			<tr>
            				<td width="200px" align="center">旧密码<font>*</font>:</td>
            				<td width="200px;"><input type="password" name="oldPwd" id="oldPwdPersonal" class="easyui-validatebox" style="width: 180px;padding:3px;"/></td>
            			</tr>
            			<tr>
            				<td  align="center">新密码<font>*</font>:</td>
            				<td><input type="password" name="newPwd" id="newPwdPersonal" class="easyui-validatebox" style="width: 180px;padding:3px;" /></td>
            			</tr>
            			<tr>
            				<td align="center">确认新密码<font>*</font>:</td>
            				<td><input type="password" name="confirmNewPwd" class="easyui-validatebox" style="width: 180px;padding:3px;"/></td>
            				
            			</tr>
            			<tr>
            				<td>&nbsp;</td>
            				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a></td>
            				
            			</tr>
            			
            		</table>
            	</form>
        	<!-- </div> 
        	
        	
		</div>-->
		
	</div>
<script>
function openFindPasswordDialog(){
	$("#changePersonalPass_layout").parent().window("close");
	Oa.openFindPasswordDialog();
	$("#openFindPasswordDialog").prev(".panel-header").find(".panel-tool-close").hide();
}

$(document).ready(function(){
	$('#changePersonalPassForm').validate({
		rules:{
			oldPwd: { required: true},
			newPwd: {required:true,rangelength:[8,20],isGoodPassword:'',notSamePassword:'#oldPwdPersonal'},
			confirmNewPwd: {equalTo: "#newPwdPersonal",}
		}
	});
	$("#changePersonalPassForm [tag='ok']").click( function(){
		if(!$('#changePersonalPassForm').validate().form()) return false;
		Hg.refRepeatSubmit("changePersonalPassForm");
		$("#changePersonalPass_layout").block();
		Hg.getJson("/sys/sysUser/changePsatPwd",{
			oldPwd: RSAUtils.encryptedString(rsa_key, $("#changePersonalPassForm input[name='oldPwd']").val()),
			newPwd: RSAUtils.encryptedString(rsa_key, $("#changePersonalPassForm input[name='newPwd']").val()),
		},function(data){
			if (!data.success) {
				$("#changePersonalPass_layout").unblock();
				//Hg.showErrorMsg("changePersonalPassForm",data.data);
				var errorResult = $.parseJSON(data.data);
				$.messager.alert("操作失败",errorResult.message);
			} else {
				$.messager.ok("修改密码成功!",function(){
					$("#changePersonalPass_layout").parent().window("close");
				});
			}
		});
		
	})

})

	
</script>
</body>

</html>
