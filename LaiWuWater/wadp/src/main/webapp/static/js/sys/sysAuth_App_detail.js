
function initSysAuthAppDetail(authId) {
	if (sysAuth.mode == "view") {
		//查看详情时移除按钮
		$("#sysAuthAppDetail_layout [tag='ok']").parent().remove();
	} else if (sysAuth.mode == "edit") {
		$("#sysAuthAppDetail_layout [name='authCode']").attr("disabled","disabled");
	}
	
	Hg.refRepeatSubmit("sysAuthApp_form");//防止表单重复提交

	$('#sysAuthApp_form').validate({
		rules:{
			authName: { required: true ,rangelength:[0,100]},
			authCode: {  required: true,rangelength:[0,32]},
			authEnname: {rangelength:[0,100]},
			descr: { rangelength:[0,100]}
		}
	});
	
	$("#sysAuthAppDetail_layout [tag='ok']").click(function(){
		if (authId == 0) {
			_saveSysAuthApp(true);
		} else {
			_saveSysAuthApp();
		}
		
	});
	

	
	$("#sysAuthAppDetail_layout [tag='cancel']").click(function(){
		$("#sysAuthAppDetail_layout").parent().window("close");
	});
	
	

	
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysAuthApp(isAdd) {
		//验证表单--------------------------------------------------
		if(!$('#sysAuthApp_form').validate().form()) return false;
		$("#sysAuthAppDetail_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/auth/add";
		if (!isAdd) submitUrl = "/sys/auth/edit";
		Hg.getJson(submitUrl,$("#sysAuthApp_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysAuthAppDetail_layout").unblock();
				Hg.refreshSubmitToken("sysAuthApp_form");
				Hg.showErrorMsg("sysAuthApp_form",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysAuthAppDetail_layout").parent().window("close");
					$('#sysAuth_grid').treegrid("reload");
				});
			}
		});
	}
	
}


initSysAuthAppDetail(authId);
		