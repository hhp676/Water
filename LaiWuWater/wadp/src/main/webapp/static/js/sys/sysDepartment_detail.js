sysDepartment.initsysDepartmentDetail = function() {

	if (sysDepartment.mode == "view") {
		//查看详情时移除按钮
		$("#sysDepartmentDetail_layout [tag='ok']").parent().remove();
	}
	
	Hg.refRepeatSubmit("sysDepartment_form");//防止表单重复提交
	
	$('#sysDepartment_form').validate({
		rules:{
			departName: { required: true ,rangelength:[1,20]},
			departFullname: { rangelength:[0,50]},
			departCode: {required: true , rangelength:[0,20]},
			engname: { rangelength:[0,50]},
			fid: { required: true ,rangelength:[1,10]}
		}
	});
	
	$("#sysDepartmentDetail_layout [tag='ok']").click(function(){
			if (sign == 0) {
				_saveSysDept(true);
			} else {
				_saveSysDept(false);
			}
	});
	
	$("#sysDepartmentDetail_layout [tag='cancel']").click(function(){
		$("#sysDepartmentDetail_layout").parent().window("close");
	});
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysDept(isAdd) {
		//验证表单--------------------------------------------------
		if(!$('#sysDepartment_form').validate().form()) return false;
		$("#sysDepartmentDetail_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/department/add";
		if (!isAdd) submitUrl = "/sys/department/edit";
		Hg.getJson(submitUrl,$("#sysDepartment_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysDepartmentDetail_layout").unblock();
				Hg.refreshSubmitToken("sysDepartment_form");
				Hg.showErrorMsg("sysDepartment_form",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysDepartmentDetail_layout").parent().window("close");
					$('#sysDepartment_grid').treegrid("reload");
				});
			}
		});
	}
	
	if(sign == 0) {
		$("[name='fid']").val(deptId);
		if(deptName == 0){
			 $("#fNode").remove();
		} else {
			$("[name='fNode']").val(deptName);					
		}
	} else if(sign == 1) {
		$("[name='fid']").val(fId);
		if(deptName == 0){
			 $("#fNode").remove();
		} else {
			$("[name='fNode']").val(deptName);					
		}
	}
	
}

sysDepartment.initsysDepartmentDetail();


