
sysNotice.initSysNoticeDetail = function(noticeId) {
	if (sysNotice.mode == "view") {
		//查看详情时移除按钮
		$("#sysNoticeDetail_layout [tag='ok']").parent().remove();
		$('#sysNotice_form').validate({
			rules:{}
		});
		//查看详情时不可编辑
		//UM.delEditor('noticeContentEd');
		 noticeContentEdEditor.setDisabled('fullscreen');
		//UM.getEditor('noticeContentEd').setDisabled('fullscreen');
		 
		 $("#sysNoticeDetail_layout [name='contentDiv']").val($("#sysNoticeDetail_layout [name='sortId']").val());
		 
		 $("#sysNoticeDetail_layout [name='contentDiv']").show();
		$("#sysNoticeDetail_layout [name='title']").attr("readonly","readonly");
		$("#sysNoticeDetail_layout [name='title']").mouseover(function(){
			$("#sysNoticeDetail_layout [name='title']").attr("title",$("#sysNoticeDetail_layout [name='title']").val());
		});
		$("#sysNoticeDetail_layout [name='sortId']").attr("readonly","readonly");
		$("#sysNoticeDetail_layout [name='sortId']").mouseover(function(){
			$("#sysNoticeDetail_layout [name='sortId']").attr("title",$("#sysNoticeDetail_layout [name='sortId']").val());
		});
		$("#sysNoticeDetail_layout [name='content']").attr("readonly","readonly");
		$("#sysNoticeDetail_layout [name='content']").mouseover(function(){
			$("#sysNoticeDetail_layout [name='content']").attr("title",$("#sysNoticeDetail_layout [name='content']").val());
		});
		$("#sysNoticeDetail_layout [name='autoPub']").attr("readonly","readonly");
		$("#sysNoticeDetail_layout [name='autoPub']").mouseover(function(){
			$("#sysNoticeDetail_layout [name='autoPub']").attr("title",$("#sysNoticeDetail_layout [name='autoPub']").val());
		});
		$("#sysNoticeDetail_layout [name='startTimeStr']").attr("disabled","disabled");
		$("#sysNoticeDetail_layout [name='startTimeStr']").css("background-color","#fff");
		 
		$("#sysNoticeDetail_layout [name='endTimeStr']").attr("disabled","disabled");
		$("#sysNoticeDetail_layout [name='endTimeStr']").css("background-color","#fff");
	}
	Hg.refRepeatSubmit("sysNotice_form");//防止表单重复提交
	
	
	
	
	$('#sysNotice_form').validate({
		rules:{
			title: { required: true ,rangelength:[1,25]},
			sortId: {rangelength:[0,8],digits:true},
//			content: {rangelength:[0,2000] },
			startTimeStr: {required:true},
			endTimeStr: {required:true}
		}
	});
	
	$("#sysNoticeDetail_layout [tag='ok']").click(function(){
		//UM.getEditor('noticeContentEd').setDisabled('fullscreen');
		if (noticeId == 0) {
			_saveSysNotice(true);
		} else {
			_saveSysNotice(false);
		}
		
	});
	

	
	$("#sysNoticeDetail_layout [tag='cancel']").click(function(){
		UM.delEditor('noticeContentEd');
		$("#sysNoticeDetail_layout").parent().window("close");
		//销毁编辑器
	});
	
	

	
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysNotice(isAdd) {
		//验证表单--------------------------------------------------
		 if(!$('#sysNotice_form').validate().form()) return false;
		 
		 var content = noticeContentEdEditor.getContent();
		 var getPlainTxt = noticeContentEdEditor.getPlainTxt();
		    if(getPlainTxt.length>10000){
		        Hg.error("操作失败,正文长度超出10000!");
		        return false;
		    }
//		    $("#ueditorContent").val(content);
//		    $("#sysNotice_form [name='content']").val(content)
		$("#sysNoticeDetail_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/notice/add";
		if (!isAdd) submitUrl = "/sys/notice/edit";
		Hg.post(submitUrl,$("#sysNotice_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysNoticeDetail_layout").unblock();
				Hg.refreshSubmitToken("sysNotice_form");
				Hg.showErrorMsg("sysNotice_form",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					//销毁编辑器
					UM.delEditor('noticeContentEd');
					$("#sysNoticeDetail_layout").parent().window("close");
					$('#sysNotice_grid').datagrid("reload");
				});
			}
		});
	}
};


sysNotice.initSysNoticeDetail(noticeId);
