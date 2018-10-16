
function initSysAuthModuleDetail(mode) {
	if (sysAuth.mode == "view") {
		//查看详情时移除按钮
		$("#sysAuthModuleDetail_layout [tag='ok']").parent().remove();
	} else if (sysAuth.mode == "edit") {
		$("#sysAuthModuleDetail_layout [name='authCode']").attr("disabled","disabled");
	}
	
	
	Hg.refRepeatSubmit("sysAuthModule_form");//防止表单重复提交

	$('#sysAuthModule_form').validate({
		rules:{
			authName: { required: true ,rangelength:[0,100]},
			orderId: {required:true,rangelength:[0,4],number:true},
			authCode: {required:true,rangelength:[0,32]},
			authEnname: {rangelength:[0,100]},
			descr: { rangelength:[0,100]}
		}
	});
	
	$("#sysAuthModuleDetail_layout [tag='ok']").click(function(){
		if (mode == 'add') {
			_saveSysAuthModule(true);
		} else {
			_saveSysAuthModule();
		}
		
	});
	

	
	$("#sysAuthModuleDetail_layout [tag='cancel']").click(function(){
		$("#sysAuthModuleDetail_layout").parent().window("close");
	});
	
	

	
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysAuthModule(isAdd) {
		//验证表单--------------------------------------------------
		if(!$('#sysAuthModule_form').validate().form()) return false;
		$("#sysAuthModuleDetail_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/auth/add";
		if (!isAdd) submitUrl = "/sys/auth/edit";
		Hg.getJson(submitUrl,$("#sysAuthModule_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysAuthModuleDetail_layout").unblock();
				Hg.refreshSubmitToken("sysAuthModule_form");
				Hg.showErrorMsg("sysAuthModule_form",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysAuthModuleDetail_layout").parent().window("close");
					$('#sysAuth_grid').treegrid("reload");
				});
			}
		});
	}
	
}


initSysAuthModuleDetail(mode);
		