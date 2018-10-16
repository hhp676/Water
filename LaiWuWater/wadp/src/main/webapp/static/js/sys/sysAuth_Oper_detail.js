
function initSysAuthOperDetail(mode) {
	if (sysAuth.mode == "view") {
		//查看详情时移除按钮
		$("#sysAuthOperDetail_layout [tag='ok']").parent().remove();
	}  else if (sysAuth.mode == "edit") {
		$("#sysAuthOperDetail_layout [name='authCode']").attr("disabled","disabled");
	}
	
	
	Hg.refRepeatSubmit("sysAuthOper_form");//防止表单重复提交

	$('#sysAuthOper_form').validate({
		rules:{
			authName: { required: true ,rangelength:[0,100]},
			operId: {required: true},
			authCode: {required:true,rangelength:[0,32]},
			orderId: {required:true,rangelength:[0,4],number:true},
			descr: { rangelength:[0,100]}
		}
	});
	
	$("#sysAuthOperDetail_layout [tag='ok']").click(function(){
		if (mode == 'add') {
			_saveSysAuthOper(true);
		} else {
			_saveSysAuthOper();
		}
		
	});
	
	//初始化---------------------------------------------------------
	if (mode == "edit") {
		$("#cg_operId").combogrid({onLoadSuccess:function(){
			$("#cg_operId").combogrid("setValue", operId);
		}});
		
	}

	
	$("#sysAuthOperDetail_layout [tag='cancel']").click(function(){
		$("#sysAuthOperDetail_layout").parent().window("close");
	});
	
	

	
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysAuthOper(isAdd) {
		//验证表单--------------------------------------------------
		if(!$('#sysAuthOper_form').validate().form()) return false;
		$("#sysAuthOperDetail_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/auth/add";
		if (!isAdd) submitUrl = "/sys/auth/edit";
		Hg.getJson(submitUrl,$("#sysAuthOper_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysAuthOperDetail_layout").unblock();
				Hg.refreshSubmitToken("sysAuthOper_form");
				Hg.showErrorMsg("sysAuthOper_form",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysAuthOperDetail_layout").parent().window("close");
					$('#sysAuth_grid').treegrid("reload");
				});
			}
		});
	}
	
}


initSysAuthOperDetail(mode);
		