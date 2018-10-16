sysUser.initChangePwd = function(userId) {
	$('#sysUserChangePwd_form').validate({
		rules:{
			newPwd: { required: true ,rangelength:[5,20]}
		}
	});
	$("#sysUserChangePwd_layout [name='getRandomNum']").click(function(){
		Hg.getJson("/getRandomNum",{},function(data){
			var pwd = data.data;
			$("#sysUserChangePwd_form [name='newPwd']").val(pwd);
			$("#sysUserChangePwd_layout [name='randomNumSpan']").html("随机密码："+pwd);
		});
	});
	
	$("#sysUserChangePwd_layout [tag='ok']").click(function(){
		//验证表单--------------------------------------------------
		if(!$('#sysUserChangePwd_form').validate().form()) return false;
		$("#sysUserChangePwd_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/sysUser/adminChangePwd";
		Hg.getJson(submitUrl,$("#sysUserChangePwd_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysUserChangePwd_layout").unblock();
				$.messager.alert("提示","改密失败!");
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
