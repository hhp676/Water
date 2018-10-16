sysDatadicItem.sysDatadicItemDetail = function(itemId) {
	
	if (sysDatadicItem.mode == "view") {
		//查看详情时移除按钮
		$("#sysDatadicItemDetail_layout [tag='ok']").parent().remove();
		//查看详情时不可编辑
		$('#sysDatadicItem_form').validate({
			rules:{}
		});
		
		$("#sysDatadicItemDetail_layout [name='groupId']").attr("readonly","readonly");
		$("#sysDatadicItemDetail_layout [name='groupId']").mouseover(function(){
			$("#sysDatadicItemDetail_layout [name='groupId']").attr("title",$("#sysDatadicItemDetail_layout [name='groupId']").val());
		});
		$("#sysDatadicItemDetail_layout [name='itemCode']").attr("readonly","readonly");
		$("#sysDatadicItemDetail_layout [name='itemCode']").mouseover(function(){
			$("#sysDatadicItemDetail_layout [name='itemCode']").attr("title",$("#sysDatadicItemDetail_layout [name='itemCode']").val());
		});
		$("#sysDatadicItemDetail_layout [name='itemName']").attr("readonly","readonly");
		$("#sysDatadicItemDetail_layout [name='itemName']").mouseover(function(){
			$("#sysDatadicItemDetail_layout [name='itemName']").attr("title",$("#sysDatadicItemDetail_layout [name='itemName']").val());
		});
		$("#sysDatadicItemDetail_layout [name='itemValue']").attr("readonly","readonly");
		$("#sysDatadicItemDetail_layout [name='itemValue']").mouseover(function(){
			$("#sysDatadicItemDetail_layout [name='itemValue']").attr("title",$("#sysDatadicItemDetail_layout [name='itemValue']").val());
		});
		$("#sysDatadicItemDetail_layout [name='itemDesc']").attr("readonly","readonly");
		$("#sysDatadicItemDetail_layout [name='itemDesc']").mouseover(function(){
			$("#sysDatadicItemDetail_layout [name='itemDesc']").attr("title",$("#sysDatadicItemDetail_layout [name='itemDesc']").val());
		});
		$("#sysDatadicItemDetail_layout [name='orderId']").attr("readonly","readonly");
		$("#sysDatadicItemDetail_layout [name='orderId']").mouseover(function(){
			$("#sysDatadicItemDetail_layout [name='orderId']").attr("title",$("#sysDatadicItemDetail_layout [name='orderId']").val());
		});
	}

	Hg.refRepeatSubmit("sysDatadicItem_form");//防止表单重复提交
	$('#sysDatadicItem_form').validate({
		rules:{
			groupId: {required:true},
			itemCode: { required: true,rangelength:[1,32],codeCheck:true},
			itemName: { required: true,rangelength:[1,32]},
			itemValue: { rangelength:[0,10],digits:true},
			orderId: { rangelength:[0,4],digits:true},
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
				Hg.showErrorMsg("sysDatadicItem_form",data.data);
				//Hg.showVaildMsg(data.data,$("#sysDatadicItemDetail_layout").parent().window());
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