sysMetaMethodDatagrid.sysMetaMethodDetail = function(metaMethodId) {
	
	if (sysMetaMethodDatagrid.mode == "view") {
		//查看详情时移除按钮
		$("#sysMetaMethodDetail_layout [tag='ok']").parent().remove();
		//查看详情时不可编辑
		$('#sysMetaMethod_form').validate({
			rules:{}
		});
		
		$("#sysMetaMethodDetail_layout [name='className']").attr("readonly","readonly");
		$("#sysMetaMethodDetail_layout [name='className']").mouseover(function(){
			$("#sysMetaMethodDetail_layout [name='className']").attr("title",$("#sysMetaMethodDetail_layout [name='className']").val());
		});
		$("#sysMetaMethodDetail_layout [name='methodName']").attr("readonly","readonly");
		$("#sysMetaMethodDetail_layout [name='methodName']").mouseover(function(){
			$("#sysMetaMethodDetail_layout [name='methodName']").attr("title",$("#sysMetaMethodDetail_layout [name='methodName']").val());
		});
		$("#sysMetaMethodDetail_layout [name='argsName']").attr("readonly","readonly");
		$("#sysMetaMethodDetail_layout [name='argsName']").mouseover(function(){
			$("#sysMetaMethodDetail_layout [name='argsName']").attr("title",$("#sysMetaMethodDetail_layout [name='argsName']").val());
		});
		$("#sysMetaMethodDetail_layout [name='methodCode']").attr("readonly","readonly");
		$("#sysMetaMethodDetail_layout [name='methodCode']").mouseover(function(){
			$("#sysMetaMethodDetail_layout [name='methodCode']").attr("title",$("#sysMetaMethodDetail_layout [name='methodCode']").val());
		});
		$("#sysMetaMethodDetail_layout [name='methodZhName']").attr("readonly","readonly");
		$("#sysMetaMethodDetail_layout [name='methodZhName']").mouseover(function(){
			$("#sysMetaMethodDetail_layout [name='methodZhName']").attr("title",$("#sysMetaMethodDetail_layout [name='methodZhName']").val());
		});
		$("#sysMetaMethodDetail_layout [name='methodEngName']").attr("readonly","readonly");
		$("#sysMetaMethodDetail_layout [name='methodEngName']").mouseover(function(){
			$("#sysMetaMethodDetail_layout [name='methodEngName']").attr("title",$("#sysMetaMethodDetail_layout [name='methodEngName']").val());
		});
		$("#sysMetaMethodDetail_layout [name='logRemarkClass']").attr("readonly","readonly");
		$("#sysMetaMethodDetail_layout [name='logRemarkClass']").mouseover(function(){
			$("#sysMetaMethodDetail_layout [name='logRemarkClass']").attr("title",$("#sysMetaMethodDetail_layout [name='logRemarkClass']").val());
		});
		$("#sysMetaMethodDetail_layout [name='logLevel']").attr("readonly","readonly");
		$("#sysMetaMethodDetail_layout [name='logLevel']").mouseover(function(){
			$("#sysMetaMethodDetail_layout [name='logLevel']").attr("title",$("#sysMetaMethodDetail_layout [name='logLevel']").val());
		});
		$("#sysMetaMethodDetail_layout [name='logType']").attr("readonly","readonly");
		$("#sysMetaMethodDetail_layout [name='logType']").mouseover(function(){
			$("#sysMetaMethodDetail_layout [name='logType']").attr("title",$("#sysMetaMethodDetail_layout [name='logType']").val());
		});
		$("#sysMetaMethodDetail_layout [name='descr']").attr("readonly","readonly");
		$("#sysMetaMethodDetail_layout [name='descr']").mouseover(function(){
			$("#sysMetaMethodDetail_layout [name='descr']").attr("title",$("#sysMetaMethodDetail_layout [name='descr']").val());
		});
	}else if (sysMetaMethodDatagrid.mode == "edit") {
		$("#sysMetaMethodDetail_layout [name='className']").attr("disabled","disabled");
		$("#sysMetaMethodDetail_layout [name='methodName']").attr("disabled","disabled");
		$("#sysMetaMethodDetail_layout [name='argsName']").attr("disabled","disabled");
	}

	Hg.refRepeatSubmit("sysMetaMethod_form");//防止表单重复提交
	$('#sysMetaMethod_form').validate({
		rules:{
			className: {required:true,codeNameCheck:true},
			methodName: {required: true,codeSimpleNameCheck:true},
			argsName: {required: true,codeNamesSplitCheck:true},
			methodCode: {rangelength:[0,50],required: true,codeSimpleNameCheck:true},
			methodEngName: {rangelength:[0,50],stringCheck:true},
			methodZhName: {rangelength:[0,100]},
			logRemarkClass: {codeNameCheck:true}
		}
	});

	
	//绑定事件--------------------------------------------------------
	$("#sysMetaMethodDetail_layout [tag='ok']").click(function(){
		if (metaMethodId == 0) {
			_saveSysMetaMethod(true);
		} else {
			_saveSysMetaMethod(false);
		}
		
	});
	

	
	$("#sysMetaMethodDetail_layout [tag='cancel']").click(function(){
		$("#sysMetaMethodDetail_layout").parent().window("close");
	});
	
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysMetaMethod(isAdd) {
		//验证表单--------------------------------------------------
		if(!$('#sysMetaMethod_form').validate().form()) return false;
		$("#sysMetaMethodDetail_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/sysMetaMethod/add";
		if (!isAdd) submitUrl = "/sys/sysMetaMethod/update";
		Hg.getJson(submitUrl,$("#sysMetaMethod_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysMetaMethodDetail_layout").unblock();
				Hg.refreshSubmitToken("sysMetaMethod_form");
				Hg.showErrorMsg("sysMetaMethod_form",data.data);
				//Hg.showVaildMsg(data.data,$("#sysMetaMethodDetail_layout").parent().window());
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysMetaMethodDetail_layout").parent().window("close");
					$('#sysMetaMethod_datagrid').datagrid("reload");
				});
			}
		});
	}
}

sysMetaMethodDatagrid.sysMetaMethodDetail(metaMethodId);

$.validator.addMethod("codeNameCheck",
		function(value, element) {
		return this.optional(element) ||  /^[a-zA-Z0-9_\.]*$/.test(value);
	}, "只能输入英文字母、数字、_和.");
$.validator.addMethod("codeSimpleNameCheck",
		function(value, element) {
		return this.optional(element) ||  /^[a-zA-Z0-9_]*$/.test(value);
	}, "只能输入英文字母、数字和_");
$.validator.addMethod("codeNamesSplitCheck",
		function(value, element) {
		return this.optional(element) ||  /^[a-zA-Z0-9,\.]*$/.test(value);
	}, "只能输入英文字母、数字、_、.和,");
