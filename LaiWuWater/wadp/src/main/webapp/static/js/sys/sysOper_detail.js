function sysOperDetail(operId) {

	Hg.refRepeatSubmit("sysOper_form");//防止表单重复提交
	
	$('#sysOper_form').validate({
		rules:{			
			operCode: { required: true,rangelength:[0,100]},
			operName: { required: true,rangelength:[0,100]},
			orderId: {required:true,rangelength:[0,4],number:true},
			descr: { rangelength:[0,100]}
			
		}
	});			

	$("#sysOperDetail_layout [tag='ok']").click(function(){
		if (operId == 0) {
			_saveSysOper(true);
		} else {
			_saveSysOper(false);
		}
		
	});
	

	
	$("#sysOperDetail_layout [tag='cancel']").click(function(){		
		$("#sysOperDetail_layout").parent().window("close");
	});
	
	

	
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysOper(isAdd) {
		//验证表单--------------------------------------------------
		if(!$('#sysOper_form').validate().form()) return false;
		$("#sysOperDetail_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/authoper/add";
		if (!isAdd) submitUrl = "/sys/authoper/edit";
		Hg.getJson(submitUrl,$("#sysOper_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysOperDetail_layout").unblock();
				Hg.refreshSubmitToken("sysOper_form");
				Hg.showVaildMsg(data.data,$("#sysOperDetail_layout").parent().window());
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysOperDetail_layout").parent().window("close");
					$('#sysOper_grid').datagrid("reload");
				});
			}
		});
	}
	
}

sysOperDetail(operId);