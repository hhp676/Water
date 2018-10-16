
function initSysAuthModuleDetail(mode) {
	if (sysAuth.mode == "view") {
		//查看详情时移除按钮
		$("#sysAuthModuleDetail_layout [tag='ok']").parent().remove();
		//查看详情时不可编辑
		$('#sysAuthModule_form').validate({
			rules:{}
		});
		$("#sysAuthModuleDetail_layout [name='authName']").attr("readonly","readonly");
		$("#sysAuthModuleDetail_layout [name='authName']").mouseover(function(){
			$("#sysAuthModuleDetail_layout [name='authName']").attr("title",$("#sysAuthModuleDetail_layout [name='authName']").val());
		});
		$("#sysAuthModuleDetail_layout [name='authCode']").attr("readonly","readonly");
		$("#sysAuthModuleDetail_layout [name='authCode']").mouseover(function(){
			$("#sysAuthModuleDetail_layout [name='authCode']").attr("title",$("#sysAuthModuleDetail_layout [name='authCode']").val());
		});
		$("#sysAuthModuleDetail_layout [name='authIcon']").attr("readonly","readonly");
		$("#sysAuthModuleDetail_layout [name='authIcon']").mouseover(function(){
			$("#sysAuthModuleDetail_layout [name='authIcon']").attr("title",$("#sysAuthModuleDetail_layout [name='authIcon']").val());
		});
		$("#sysAuthModuleDetail_layout [name='authEnname']").attr("readonly","readonly");
		$("#sysAuthModuleDetail_layout [name='authEnname']").mouseover(function(){
			$("#sysAuthModuleDetail_layout [name='authEnname']").attr("title",$("#sysAuthModuleDetail_layout [name='authEnname']").val());
		});
		$("#sysAuthModuleDetail_layout [name='isVisible']").attr("disabled","disabled");
		$("#sysAuthModuleDetail_layout [name='isVisible']").css("background-color","#fff");
		
		$("#sysAuthModuleDetail_layout [name='orderId']").attr("readonly","readonly");
		$("#sysAuthModuleDetail_layout [name='orderId']").mouseover(function(){
			$("#sysAuthModuleDetail_layout [name='orderId']").attr("title",$("#sysAuthModuleDetail_layout [name='orderId']").val());
		});
		$("#sysAuthModuleDetail_layout [name='authUri']").attr("readonly","readonly");
		$("#sysAuthModuleDetail_layout [name='authUri']").mouseover(function(){
			$("#sysAuthModuleDetail_layout [name='authUri']").attr("title",$("#sysAuthModuleDetail_layout [name='authUri']").val());
		});
		$("#sysAuthModuleDetail_layout [name='descr']").attr("readonly","readonly");
		$("#sysAuthModuleDetail_layout [name='descr']").mouseover(function(){
			$("#sysAuthModuleDetail_layout [name='descr']").attr("title",$("#sysAuthModuleDetail_layout [name='descr']").val());
		});
	} else if (sysAuth.mode == "edit") {
		$("#sysAuthModuleDetail_layout [name='authCode']").attr("disabled","disabled");
	}
	
	
	Hg.refRepeatSubmit("sysAuthModule_form");//防止表单重复提交

	$('#sysAuthModule_form').validate({
		rules:{
			authName: { required: true ,rangelength:[1,11]},
			orderId: {required:true,rangelength:[1,4],digits:true},
			authCode: {required:true,rangelength:[1,32]},
			authEnname: {stringCheck:true,rangelength:[0,32]},
			authIcon: { rangelength:[0,100]},
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
					$('#sysAuth_grid').treegrid('load',{
						 authNameForQuery: $("#sysAuthSearchForm [name='authName']").val(),
						 authCodeForQuery: $("#sysAuthSearchForm [name='authCode']").val()
					 });
				});
			}
		});
	}
	
}


initSysAuthModuleDetail(mode);
		