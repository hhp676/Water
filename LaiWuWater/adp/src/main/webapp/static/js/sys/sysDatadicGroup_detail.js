function sysDatadicGroupDetail(groupId) {
	if (sysDatadicGroup.mode == "view") {
		//查看详情时移除按钮
		$("#sysDatadicGroupDetail_layout [tag='ok']").parent().remove();
		//查看详情时不可编辑
		$('#sysDatadicGroup_form').validate({
			rules:{}
		});
		
		$("#sysDatadicGroup_form [name='groupCode']").attr("readonly","readonly");
		$("#sysDatadicGroup_form [name='groupCode']").mouseover(function(){
			$("#sysDatadicGroup_form [name='groupCode']").attr("title",$("#sysDatadicGroup_form [name='groupCode']").val());
		});
		$("#sysDatadicGroup_form [name='groupName']").attr("readonly","readonly");
		$("#sysDatadicGroup_form [name='groupName']").mouseover(function(){
			$("#sysDatadicGroup_form [name='groupName']").attr("title",$("#sysDatadicGroup_form [name='groupName']").val());
		});
		$("#sysDatadicGroup_form [name='groupDesc']").attr("readonly","readonly");
		$("#sysDatadicGroup_form [name='groupDesc']").mouseover(function(){
			$("#sysDatadicGroup_form [name='groupDesc']").attr("title",$("#sysDatadicGroup_form [name='groupDesc']").val());
		});
		$("#sysDatadicGroup_form [name='orderId']").attr("readonly","readonly");
		$("#sysDatadicGroup_form [name='orderId']").mouseover(function(){
			$("#sysDatadicGroup_form [name='orderId']").attr("title",$("#sysDatadicGroup_form [name='orderId']").val());
		});
	} else if (sysDatadicGroup.mode == "edit") {
		$("#sysDatadicGroup_form [name='groupCode']").attr("disabled","disabled");
	}
	
	Hg.refRepeatSubmit("sysDatadicGroup_form");//防止表单重复提交
	
	$('#sysDatadicGroup_form').validate({
		rules:{
			
			groupCode: { required: true,rangelength:[1,30],codeCheck:true},
			groupName: { rangelength:[0,32]},
			orderId: { rangelength:[0,4],digits:true},
			groupDesc: { rangelength:[0,100]}
			
		}
	});			

	$("#sysDatadicGroupDetail_layout [tag='ok']").click(function(){
		if (groupId == 0) {
			_saveSysDatadicGroup(true);
		} else {
			_saveSysDatadicGroup(false);
		}
		
	});
	

	
	$("#sysDatadicGroupDetail_layout [tag='cancel']").click(function(){
		$("#sysDatadicGroupDetail_layout").parent().window("close");
	});
	
	

	
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysDatadicGroup(isAdd) {
		//验证表单--------------------------------------------------
		if(!$('#sysDatadicGroup_form').validate().form()) return false;
		$("#sysDatadicGroupDetail_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/datadicgroup/add";
		if (!isAdd) submitUrl = "/sys/datadicgroup/edit";
		Hg.getJson(submitUrl,$("#sysDatadicGroup_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysDatadicGroupDetail_layout").unblock();
				Hg.refreshSubmitToken("sysDatadicGroup_form");
				Hg.showErrorMsg("sysDatadicGroup_form",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysDatadicGroupDetail_layout").parent().window("close");
					$('#sysDatadicGroup_grid').datagrid("reload");
				});
			}
		});
	}
	
}

sysDatadicGroupDetail(groupId);