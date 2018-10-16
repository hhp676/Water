
sysVar.initSysVarDetail = function(varId) {
	if (sysVar.mode == "view") {
		//查看详情时移除按钮
		$("#sysVarDetail_layout [tag='ok']").parent().remove();
		$('#sysVar_form').validate({
			rules:{}
		});
		//查看详情时不可编辑
		$("#sysVarDetail_layout [name='varName']").attr("readonly","readonly");
		$("#sysVarDetail_layout [name='varName']").mouseover(function(){
			$("#sysVarDetail_layout [name='varName']").attr("title",$("#sysVarDetail_layout [name='varName']").val());
		});
		$("#sysVarDetail_layout [name='varValue']").attr("readonly","readonly");
		$("#sysVarDetail_layout [name='varValue']").mouseover(function(){
			$("#sysVarDetail_layout [name='varValue']").attr("title",$("#sysVarDetail_layout [name='varValue']").val());
		});
		$("#sysVarDetail_layout [name='varType']").attr("readonly","readonly");
		$("#sysVarDetail_layout [name='varType']").mouseover(function(){
			$("#sysVarDetail_layout [name='varType']").attr("title",$("#sysVarDetail_layout [name='varType']").val());
		});
		$("#sysVarDetail_layout [name='descr']").attr("readonly","readonly");
		$("#sysVarDetail_layout [name='descr']").mouseover(function(){
			$("#sysVarDetail_layout [name='descr']").attr("title",$("#sysVarDetail_layout [name='descr']").val());
		});
	}
	Hg.refRepeatSubmit("sysVar_form");//防止表单重复提交

	$('#sysVar_form').validate({
		rules:{
			varName: { required: true ,rangelength:[5,10]},
			varValue: { required: true ,rangelength:[1,32]},
			varType: { rangelength:[0,10]},
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
				Hg.showErrorMsg("sysVar_form",data.data);
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
