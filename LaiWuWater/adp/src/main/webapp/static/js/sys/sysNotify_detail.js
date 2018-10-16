
sysNotify.initSysNotifyDetail = function(notifyId) {
	if (sysNotify.mode == "view") {
		//查看详情时移除按钮
		$("#sysNotifyDetail_layout [tag='ok']").parent().remove();
		//查看详情时不可编辑
		$("#sysNotifyDetail_layout [name='userId']").attr("readonly","readonly");
		$("#sysNotifyDetail_layout [name='userId']").mouseover(function(){
			$("#sysNotifyDetail_layout [name='userId']").attr("title",$("#sysNotifyDetail_layout [name='userId']").val());
		});
		$("#sysNotifyDetail_layout [name='tempId']").attr("readonly","readonly");
		$("#sysNotifyDetail_layout [name='tempId']").mouseover(function(){
			$("#sysNotifyDetail_layout [name='tempId']").attr("title",$("#sysNotifyDetail_layout [name='tempId']").val());
		});
		$("#sysNotifyDetail_layout [name='title']").attr("readonly","disabled");
		$("#sysNotifyDetail_layout [name='title']").mouseover(function(){
			$("#sysNotifyDetail_layout [name='title']").attr("title",$("#sysNotifyDetail_layout [name='title']").val());
		});
		$("#sysNotifyDetail_layout [name='content']").attr("readonly","readonly");
		$("#sysNotifyDetail_layout [name='content']").mouseover(function(){
			$("#sysNotifyDetail_layout [name='content']").attr("title",$("#sysNotifyDetail_layout [name='content']").val());
		});
	}
	

	
};


sysNotify.initSysNotifyDetail(notifyId);
