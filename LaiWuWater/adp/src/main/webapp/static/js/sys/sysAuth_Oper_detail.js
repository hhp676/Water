

function initSysAuthOperDetail(mode) {
	if (sysAuth.mode == "view") {
		//查看详情时移除按钮
		$("#sysAuthOperDetail_layout [tag='ok']").parent().remove();
		//查看详情时不可编辑
		$('#sysAuthOper_form').validate({
			rules:{}
		});
		
		$("#sysAuthOperDetail_layout [name='authName']").attr("readonly","readonly");
		$("#sysAuthOperDetail_layout [name='authName']").mouseover(function(){
			$("#sysAuthOperDetail_layout [name='authName']").attr("title",$("#sysAuthOperDetail_layout [name='authName']").val());
		});
		$("#sysAuthOperDetail_layout [name='authCode']").attr("readonly","readonly");
		$("#sysAuthOperDetail_layout [name='authCode']").mouseover(function(){
			$("#sysAuthOperDetail_layout [name='authCode']").attr("title",$("#sysAuthOperDetail_layout [name='authCode']").val());
		});
		$("#sysAuthOperDetail_layout [name='operId']").attr("readonly","readonly");
		$("#sysAuthOperDetail_layout [name='operId']").mouseover(function(){
			$("#sysAuthOperDetail_layout [name='operId']").attr("title",$("#sysAuthOperDetail_layout [name='operId']").val());
		});
		$("#sysAuthOperDetail_layout [name='authEnname']").attr("readonly","readonly");
		$("#sysAuthOperDetail_layout [name='authEnname']").mouseover(function(){
			$("#sysAuthOperDetail_layout [name='authEnname']").attr("title",$("#sysAuthOperDetail_layout [name='authEnname']").val());
		});
		$("#sysAuthOperDetail_layout [name='authIcon']").attr("readonly","readonly");
		$("#sysAuthOperDetail_layout [name='authIcon']").mouseover(function(){
			$("#sysAuthOperDetail_layout [name='authIcon']").attr("title",$("#sysAuthOperDetail_layout [name='authIcon']").val());
		});
		$("#sysAuthOperDetail_layout [name='orderId']").attr("readonly","readonly");
		$("#sysAuthOperDetail_layout [name='orderId']").mouseover(function(){
			$("#sysAuthOperDetail_layout [name='orderId']").attr("title",$("#sysAuthOperDetail_layout [name='orderId']").val());
		});
		$("#sysAuthOperDetail_layout [name='isVisible']").attr("disabled","disabled");
		$("#sysAuthOperDetail_layout [name='isVisible']").css("background-color","#fff");
		
		$("#sysAuthOperDetail_layout [name='authUri']").attr("readonly","readonly");
		$("#sysAuthOperDetail_layout [name='authUri']").mouseover(function(){
			$("#sysAuthOperDetail_layout [name='authUri']").attr("title",$("#sysAuthOperDetail_layout [name='authUri']").val());
		});
		$("#sysAuthOperDetail_layout [name='descr']").attr("readonly","readonly");
		$("#sysAuthOperDetail_layout [name='descr']").mouseover(function(){
			$("#sysAuthOperDetail_layout [name='descr']").attr("title",$("#sysAuthOperDetail_layout [name='descr']").val());
		});
	}  else if (sysAuth.mode == "edit") {
		$("#sysAuthOperDetail_layout [name='authCode']").attr("disabled","disabled");
	}
	
	
	Hg.refRepeatSubmit("sysAuthOper_form");//防止表单重复提交

	$('#sysAuthOper_form').validate({
		rules:{
			authName: { required: true ,rangelength:[1,32]},
			operId: {required: true,number:true},
			authCode: {required:true,rangelength:[1,32]},
			orderId: {required:true,rangelength:[1,4],digits:true},
			authEnname: {stringCheck:true,rangelength:[1,32]},
			authIcon: { rangelength:[0,100]},
			authUri: { rangelength:[0,100]},
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
					$('#sysAuth_grid').treegrid('load',{
						 authNameForQuery: $("#sysAuthSearchForm [name='authName']").val(),
						 authCodeForQuery: $("#sysAuthSearchForm [name='authCode']").val()
					 });
				});
			}
		});
	}
	
}


initSysAuthOperDetail(mode);
		