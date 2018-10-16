sysDepartment.initsysDepartmentDetail = function() {

	if (sysDepartment.mode == "view") {
		//查看详情时移除按钮
		$("#sysDepartmentDetail_layout [tag='ok']").parent().remove();
		
		$('#sysDepartment_form').validate({
			rules:{}
		});
		//查看详情时不可编辑
		$("#sysDepartmentDetail_layout [name='departName']").attr("readonly","readonly");
		$("#sysDepartmentDetail_layout [name='departName']").mouseover(function(){
			$("#sysDepartmentDetail_layout [name='departName']").attr("title",$("#sysDepartmentDetail_layout [name='departName']").val());
		});
		$("#sysDepartmentDetail_layout [name='departFullname']").attr("readonly","readonly");
		$("#sysDepartmentDetail_layout [name='departFullname']").mouseover(function(){
			$("#sysDepartmentDetail_layout [name='departFullname']").attr("title",$("#sysDepartmentDetail_layout [name='departFullname']").val());
		});
		$("#sysDepartmentDetail_layout [name='departCode']").attr("readonly","readonly");
		$("#sysDepartmentDetail_layout [name='departCode']").mouseover(function(){
			$("#sysDepartmentDetail_layout [name='departCode']").attr("title",$("#sysDepartmentDetail_layout [name='departCode']").val());
		});
		$("#sysDepartmentDetail_layout [name='engname']").attr("readonly","readonly");
		$("#sysDepartmentDetail_layout [name='engname']").mouseover(function(){
			$("#sysDepartmentDetail_layout [name='engname']").attr("title",$("#sysDepartmentDetail_layout [name='engname']").val());
		});
	}else if(sysDepartment.mode == "edit"){
		$("#sysDepartmentDetail_layout [name='departCode']").attr("disabled","disabled");
	}
	
	Hg.refRepeatSubmit("sysDepartment_form");//防止表单重复提交
	
	$('#sysDepartment_form').validate({
		rules:{
			departName: { required: true ,rangelength:[1,20]},
			departFullname: { rangelength:[0,50]},
			departCode: {required: true , rangelength:[1,20]},
			engname: { stringCheck:true,rangelength:[0,50]},
			fid: { required: true ,rangelength:[1,10]}
		},
		messages:{
			departName:{
				rangelength:"请输入长度在 1-20 之间的字符串或汉字"
			},
			departFullname:{
				rangelength:"请输入长度在 1-50 之间的字符串或汉字"
			}
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
					$('#sysDepartment_grid').treegrid('load',{
						 departNameForQuery: $("#sysDepartmentSearchForm [name='departName']").val(),
						 departCodeForQuery: $("#sysDepartmentSearchForm [name='departCode']").val()
					 });
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


