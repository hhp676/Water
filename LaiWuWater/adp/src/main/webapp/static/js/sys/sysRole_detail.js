
sysRole.initSysRoleDetail = function(roleId) {
	if (sysRole.mode == "view") {
		//查看详情时移除按钮
		$("#sysRoleDetail_layout [tag='ok']").parent().remove();
		$('#sysRole_form').validate({
			rules:{}
		});
		//查看详情时不可编辑
		$("#sysRoleDetail_layout [name='roleName']").attr("readonly","readonly");
		$("#sysRoleDetail_layout [name='roleName']").mouseover(function(){
			$("#sysRoleDetail_layout [name='roleName']").attr("title",$("#sysRoleDetail_layout [name='roleName']").val());
		});
		$("#sysRoleDetail_layout [name='roleCode']").attr("readonly","readonly");
		$("#sysRoleDetail_layout [name='roleCode']").mouseover(function(){
			$("#sysRoleDetail_layout [name='roleCode']").attr("title",$("#sysRoleDetail_layout [name='roleCode']").val());
		});
		$("#sysRoleDetail_layout [name='descr']").attr("readonly","readonly");
		$("#sysRoleDetail_layout [name='descr']").mouseover(function(){
			$("#sysRoleDetail_layout [name='descr']").attr("title",$("#sysRoleDetail_layout [name='descr']").val());
		});
	} else if (sysRole.mode == "edit") {
		$("#sysRoleDetail_layout [name='roleCode']").attr("disabled","disabled");
	}
	Hg.refRepeatSubmit("sysRole_form");//防止表单重复提交

	$('#sysRole_form').validate({
		rules:{
			roleName: { required: true ,rangelength:[1,100]},
			roleCode: { required: true ,rangelength:[1,100]},
			descr: { rangelength:[0,100]}
		}
	});
	
	$("#sysRoleDetail_layout [tag='ok']").click(function(){
		if (roleId == 0) {
			_saveSysRole(true);
		} else {
			_saveSysRole(false);
		}
		
	});
	

	
	$("#sysRoleDetail_layout [tag='cancel']").click(function(){
		$("#sysRoleDetail_layout").parent().window("close");
	});


	
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysRole(isAdd) {
		//验证表单--------------------------------------------------
		if(!$('#sysRole_form').validate().form()) return false;
		$("#sysRoleDetail_layout").block();
		//处理右侧选中的权限
		handlerCheckedAuth();

		//提交数据--------------------------------------------------
		var submitUrl = "/sys/role/add";
		if (!isAdd) submitUrl = "/sys/role/edit";
		Hg.getJson(submitUrl,$("#sysRole_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysRoleDetail_layout").unblock();
				Hg.refreshSubmitToken("sysRole_form");
				Hg.showErrorMsg("sysRole_form",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysRoleDetail_layout").parent().window("close");
					$('#sysRole_grid').datagrid("reload");
				});
			}
		});
	}
	
	
	//处理右侧选中的权限
	function handlerCheckedAuth(){
		$("#sysRole_form [name='checkedAuthIds']").val($("#sysRoleDetail_layout [name='authTree']").treegrid("getCheckedIds"));
	}
};

sysRole.initRoleAuthTree = function(roleId){
	var treeObj = $("#sysRoleDetail_layout [name='authTree']").treegrid({
		url:G_CTX_PATH+"/sys/auth/addInfoList?number="+ new Date().getTime(),
		idField:"authId",
		treeField:"authName",
		onLoadSuccess:function(row,data){
			if (roleId == 0) {
				treeObj.treegrid("cascadeCheckBox","authId");
			}	
			if (roleId != 0) {
				Hg.getJson("/sys/role/authList?number="+ new Date().getTime(),{roleId:roleId},function(authData){
					var list = authData.list;
					for (var i=0;i<list.length;i++) {
						var checkbox = treeObj.treegrid("getCheckBox",list[i].authId).attr("checked",true);
						if (list[i].isFinal == 1) {
							checkbox.attr("disabled",true);
						}

					}
					treeObj.treegrid("cascadeCheckBox","authId");
					
			});
		}
	   Hg.showGridCellTip("sysRole_authTree", ["authId","authName"]);
	   $("#sysRoleDetail_layout [class='panel-tool']").attr("class","panel-tool1");
	}
	});
	

};


sysRole.initSysRoleDetail(roleId);
sysRole.initRoleAuthTree(roleId);


		