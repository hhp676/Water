
sysNotifyTemplate.initSysNotifyTemplateDetail = function(tempId) {
	if (sysNotifyTemplate.mode == "view") {
		//查看详情时移除按钮
		$("#sysNotifyTemplateDetail_layout [tag='ok']").parent().remove();
		$('#sysNotifyTemplate_form').validate({
			rules:{}
		});
		//查看详情时不可编辑
		$("#sysNotifyTemplateDetail_layout [name='name']").attr("readonly","readonly");
		$("#sysNotifyTemplateDetail_layout [name='name']").mouseover(function(){
			$("#sysNotifyTemplateDetail_layout [name='name']").attr("title",$("#sysNotifyTemplateDetail_layout [name='name']").val());
		});
		$("#sysNotifyTemplateDetail_layout [name='module']").attr("readonly","readonly");
		$("#sysNotifyTemplateDetail_layout [name='module']").mouseover(function(){
			$("#sysNotifyTemplateDetail_layout [name='module']").attr("title",$("#sysNotifyTemplateDetail_layout [name='module']").val());
		});
		$("#sysNotifyTemplateDetail_layout [name='titleTemplate']").attr("readonly","readonly");
		$("#sysNotifyTemplateDetail_layout [name='titleTemplate']").mouseover(function(){
			$("#sysNotifyTemplateDetail_layout [name='titleTemplate']").attr("title",$("#sysNotifyTemplateDetail_layout [name='titleTemplate']").val());
		});
		$("#sysNotifyTemplateDetail_layout [name='contentTemplate']").attr("readonly","readonly");
		$("#sysNotifyTemplateDetail_layout [name='contentTemplate']").mouseover(function(){
			$("#sysNotifyTemplateDetail_layout [name='contentTemplate']").attr("title",$("#sysNotifyTemplateDetail_layout [name='contentTemplate']").val());
		});
	}
	Hg.refRepeatSubmit("sysNotifyTemplate_form");//防止表单重复提交

	$('#sysNotifyTemplate_form').validate({
		rules:{
			name: { required: true ,rangelength:[1,25]},
			module: {rangelength:[0,25]},
			titleTemplate: { rangelength:[0,100]},
			contentTemplate: { rangelength:[0,1000]}
		}
	});
	
	$("#sysNotifyTemplateDetail_layout [tag='ok']").click(function(){
		if (tempId == 0) {
			_saveSysNotifyTemplate(true);
		} else {
			_saveSysNotifyTemplate(false);
		}
		
	});
	

	
	$("#sysNotifyTemplateDetail_layout [tag='cancel']").click(function(){
		$("#sysNotifyTemplateDetail_layout").parent().window("close");
	});
	
	

	
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysNotifyTemplate(isAdd) {
		//验证表单--------------------------------------------------
		if(!$('#sysNotifyTemplate_form').validate().form()) return false;
		$("#sysNotifyTemplateDetail_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/notifyTemplate/add";
		if (!isAdd) submitUrl = "/sys/notifyTemplate/edit";
		Hg.post(submitUrl,$("#sysNotifyTemplate_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysNotifyTemplateDetail_layout").unblock();
				Hg.refreshSubmitToken("sysNotifyTemplate_form");
				Hg.showErrorMsg("sysNotifyTemplate_form",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysNotifyTemplateDetail_layout").parent().window("close");
					$('#sysNotifyTemplate_grid').datagrid("reload");
				});
			}
		});
	}
};


sysNotifyTemplate.initSysNotifyTemplateDetail(tempId);
