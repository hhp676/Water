
sysVar.initSysVarDetail = function(varId) {
	if (sysVar.mode == "view") {
		//查看详情时移除按钮
		$("#sysVarDetail_layout [tag='ok']").parent().remove();
	}
	Hg.refRepeatSubmit("sysVar_form");//防止表单重复提交

	$('#sysVar_form').validate({
		rules:{
			varName: { required: true ,rangelength:[5,10]},
			varValue: { required: true ,rangelength:[0,50]},
			varType: { rangelength:[0,20]},
			descr: { rangelength:[0,100]}
		}
	});
	
	$("#sysVarDetail_layout [tag='ok']").click(function(){
		if (varId == 0) {
			_saveSysVar(true);
		} else {
			_saveSysVar(false);
		}
		
	});
	

	
	$("#sysVarDetail_layout [tag='cancel']").click(function(){
		$("#sysVarDetail_layout").parent().window("close");
	});
	
	

	
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysVar(isAdd) {
		//验证表单--------------------------------------------------
		if(!$('#sysVar_form').validate().form()) return false;
		$("#sysVarDetail_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/var/add";
		if (!isAdd) submitUrl = "/sys/var/edit";
		Hg.getJson(submitUrl,$("#sysVar_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysVarDetail_layout").unblock();
				Hg.refreshSubmitToken("sysVar_form");
				Hg.showVaildMsg("sysVar_form",data.data,$("#sysVarDetail_layout").parent().window());
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysVarDetail_layout").parent().window("close");
					$('#sysVar_grid').datagrid("reload");
				});
			}
		});
	}
};


sysVar.initSysVarDetail(varId);
