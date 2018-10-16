sysDatadicItem.sysDatadicItemDetail = function(itemId) {
	
	if (sysDatadicItem.mode == "view") {
		//查看详情时移除按钮
		$("#sysDatadicItemDetail_layout [tag='ok']").parent().remove();
	}

	Hg.refRepeatSubmit("sysDatadicItem_form");//防止表单重复提交
	$('#sysDatadicItem_form').validate({
		rules:{
			groupId: {required:true},
			itemCode: { required: true,rangelength:[0,32]},
			itemName: { rangelength:[0,32]},
			itemValue: { rangelength:[0,32]},
			itemDesc: { rangelength:[0,100]}
			
		}
	});

	//初始化---------------------------------------------------------
	if (itemId != 0) {
		$("#cg_groupId").combogrid({onLoadSuccess:function(){
			$("#cg_groupId").combogrid("setValue", groupId);
		}});
		
	}
	
	
	
	
	//绑定事件--------------------------------------------------------
	$("#sysDatadicItemDetail_layout [tag='ok']").click(function(){
		if (itemId == 0) {
			_saveSysDatadicItem(true);
		} else {
			_saveSysDatadicItem(false);
		}
		
	});
	

	
	$("#sysDatadicItemDetail_layout [tag='cancel']").click(function(){
		$("#sysDatadicItemDetail_layout").parent().window("close");
	});
	
	

	
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysDatadicItem(isAdd) {
		//验证表单--------------------------------------------------
		if(!$('#sysDatadicItem_form').validate().form()) return false;
		$("#sysDatadicItemDetail_layout").block();
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/datadic/item/add";
		if (!isAdd) submitUrl = "/sys/datadic/item/edit";
		Hg.getJson(submitUrl,$("#sysDatadicItem_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysDatadicItemDetail_layout").unblock();
				Hg.refreshSubmitToken("sysDatadicItem_form");
				Hg.showVaildMsg(data.data,$("#sysDatadicItemDetail_layout").parent().window());
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysDatadicItemDetail_layout").parent().window("close");
					$('#sysDatadicItem_grid').datagrid("reload");
				});
			}
		});
	}
}

sysDatadicItem.sysDatadicItemDetail(itemId);