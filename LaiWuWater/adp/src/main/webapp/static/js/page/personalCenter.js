userCenter.tabNativeObj = $("#personalCenterTab");
userCenter.tabObj = $("#personalCenterTab").tabs();
userCenter.notifyGridNativeObj = $("#pc_UserNotify_grid");


function initPersonalCenter(sex) {
	
	$("#userInfoForm [value='"+sex+"']").attr("checked","checked");
	
	$('#changeUserInfoForm').validate({
		rules:{
			userName: { required: true ,maxlength:32},
			engName: {stringCheck:true,maxlength:32},
			email:{email:true,rangelength:[0,100]},
			mobile:{mobile:true},
			telephone:{number:true},
			address:{rangelength:[0,100]}
			
		}
	});

	Hg.refRepeatSubmit("changeUserInfoForm");//防止表单重复提交
	Hg.refRepeatSubmit("changePassForm");//防止表单重复提交
	
	$("#changeUserInfoForm [tag='ok']").click(function(){
		//验证表单--------------------------------------------------
		if(!$('#changeUserInfoForm').validate().form()) return false;
		$("#personalCenter_layout").block();
		Hg.getJson("/sys/sysUser/editLoginUser",$("#changeUserInfoForm").serializeArray(),function(data){
			if (!data.success) {
				$.messager.alert("保存数据失败",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#personalCenter_layout").parent().window("close");
					window.location.href = G_CTX_PATH;
				});
			}
		});

	});
	
	$('#changePassForm').validate({
		rules:{
			oldPwd: { required: true},
			newPwd: {required:true,rangelength:[8,20],isGoodPassword:'',notSamePassword:'#changePassForm_oldPwd'},
			confirmNewPwd: {equalTo: "#changePassForm_newPwd"}
		},
		messages:{
			confirmNewPwd: {
				equalTo : "'确认新密码'和'新密码'请保持一致"
			}
		}
	});
	$("#changePassForm [tag='ok']").click(function(){
		//验证表单--------------------------------------------------
		if(!$('#changePassForm').validate().form()) return false;
		$("#personalCenter_layout").block();
		Hg.getJson("/sys/sysUser/changePwd",{
			oldPwd: RSAUtils.encryptedString(rsa_key, $("#changePassForm input[name='oldPwd']").val()),
			newPwd: RSAUtils.encryptedString(rsa_key, $("#changePassForm input[name='newPwd']").val()),
			},function(data){
			if (!data.success) {
				$("#personalCenter_layout").unblock();
				Hg.refreshSubmitToken("changePassForm");
				//Hg.showErrorMsg("changePassForm",data.data);
				var errorResult = $.parseJSON(data.data);
				$.messager.alert("操作失败",errorResult.message);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#personalCenter_layout").parent().window("close");
				});
			}
		});

	});
	$("#pc_UserNotify_grid").datagrid({
		url : G_CTX_PATH+"/sys/notify/list?userId=" + loginUserId
	});
};


initPersonalCenter(sex);

if (userCenter.callback) {
	userCenter.callback();
	userCenter.callback = null;
}

function fmtPcNotifyTitle(val,row) {
	var html = "<a href='javascript:lookupNotifyDetail("+row.notifyId+")'>"+val+"</a>";
	return html;
};

function fmtPcNotifyFlag(val,row) {
	var html = "<span class='m-icon-flag-white'>&nbsp;&nbsp;&nbsp;&nbsp;</span>";
	if (row.isRead == 0) {
		html = "<span class='m-icon-flag-red'>&nbsp;&nbsp;&nbsp;&nbsp;</span>";
	}
	return html;
}
