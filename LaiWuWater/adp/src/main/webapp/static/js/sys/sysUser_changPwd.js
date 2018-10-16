sysUser.initChangePwd = function(userId) {
	$('#sysUserChangePwd_form').validate({
		rules:{
			newPwd: { required: true ,rangelength:[8,20],isGoodPassword:''}
		}
	});
	$("#sysUserChangePwd_layout [name='getRandomPassword']").click(function(){
		Hg.getJson("/getRandomChars",{length:8},function(data){
			var pwd = data.data;
			$("#sysUserChangePwd_form [name='newPwd']").val(pwd);
			$("#sysUserChangePwd_layout [name='randomPasswordSpan']").html("随机密码："+pwd);
		});
	});
	
	$("#sysUserChangePwd_layout [tag='ok']").click(function(){
		//验证表单--------------------------------------------------
		if(!$('#sysUserChangePwd_form').validate().form()) return false;
		$("#sysUserChangePwd_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/sysUser/adminChangePwd";
		Hg.getJson(submitUrl,{
				"userId":$("#sysUserChangePwd_form input[name='userId']").val(),
				"newPwd":RSAUtils.encryptedString(rsa_key, $("#sysUserChangePwd_form input[name='newPwd']").val())
			},function(data){
			if (!data.success) {
				$("#sysUserChangePwd_layout").unblock();
				//Hg.showErrorMsg("sysUserChangePwd_form",data.data);
				// $.messager.alert("提示","改密失败!");
				var errorResult = $.parseJSON(data.data);
				$.messager.alert("操作失败",errorResult.message);
			} else {
				$.messager.ok("改密成功!",function(){
					$("#sysUserChangePwd_layout").parent().window("close");
				});
			}
		});
		
	});
	

	
	$("#sysUserChangePwd_layout [tag='cancel']").click(function(){
		$("#sysUserChangePwd_layout").parent().window("close");
	});

	
};


sysUser.initChangePwd(userId);
