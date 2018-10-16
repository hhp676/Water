sysUser.initSysUserDetail = function(userId) {
	if (sysUser.mode == "view") {
		//查看详情时移除按钮
		$("#sysUserDetail_layout [tag='ok']").parent().remove();
	} else if (sysUser.mode == "add") {
		$("#sysUserDetail_layout [name='pwd']").show();
	} else if (sysUser.mode == "edit") {
		$("#sysUser_form [name='loginName']").attr("disabled","disabled");
		$("#sysUser_form [name='plainPassword']").val("111111");
	}
	Hg.refRepeatSubmit("sysUser_form");//防止表单重复提交

	$('#sysUser_form').validate({
		rules:{
			userName: { required: true ,rangelength:[1,32]},
			loginName: { required: true ,rangelength:[1,32]},
			plainPassword: { required: true ,rangelength:[5,20]},
			email:{email:true},
			mobile:{mobile:true},
			telephone:{number:true},
			descr: { rangelength:[0,100]}
		}
	});
	
	$("#sysUserDetail_layout [tag='ok']").click(function(){
		if (userId == 0) {
			_saveSysUser(true);
		} else {
			_saveSysUser(false);
		}
		
	});
	

	
	$("#sysUserDetail_layout [tag='cancel']").click(function(){
		$("#sysUserDetail_layout").parent().window("close");
	});


	
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysUser(isAdd) {
		$("#sysUser_form [tag='mixField']").attr("name","mixField");
		//验证表单--------------------------------------------------
		if(!$('#sysUser_form').validate().form()) return false;
		$("#sysUserDetail_layout").block();
		//处理右侧选中的权限
		handlerCheckedAuth();
		handlerCheckedUgroup();
		handlerCheckedRole();
	
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/sysUser/add";
		if (!isAdd) submitUrl = "/sys/sysUser/edit";
		Hg.getJson(submitUrl,$("#sysUser_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysUserDetail_layout").unblock();
				Hg.refreshSubmitToken("sysUser_form");
				Hg.showErrorMsg("sysUser_form",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysUserDetail_layout").parent().window("close");
					$('#sysUser_grid').datagrid("reload");
				});
			}
		});
		//处理右侧选中的权限
		function handlerCheckedAuth(){
			$("#sysUser_form [name='checkedAuthIds']").val($("#sysUserDetail_layout [name='authTree']").treegrid("getCheckedIds"));
		};
		function handlerCheckedUgroup(){
			$("#sysUser_form [name='checkedUgroupIds']").val($("#sysUserDetail_layout [name='ugroupTree']").treegrid("getCheckedIds"));
		}
		function handlerCheckedRole(){
			$("#sysUser_form [name='checkedRoleIds']").val($("#sysUserDetail_layout [name='roleGrid']").datagrid("getCheckedIds","roleId"));
		};
	}
	

};


sysUser.initUserAuthTree = function(userId){
	var treeObj = $("#sysUserDetail_layout [name='authTree']").treegrid({
		url:G_CTX_PATH+"/sys/auth/list",
		idField:"authId",
		treeField:"authName",
		onLoadSuccess:function(row,data){
			if (userId != 0) {
				Hg.getJson("/sys/sysUser/authList",{userId:userId},function(authData){
					var list = authData.list;
					for (var i=0;i<list.length;i++) {
						var checkbox = treeObj.treegrid("getCheckBox",list[i].authId).attr("checked",true);
						if (list[i].isFinal == 1) {
							checkbox.attr("disabled",true);
						}

					}
					
			});
		}
	}});
	

};

sysUser.initUserUgroupTree = function(userId){
	var treeObj = $("#sysUserDetail_layout [name='ugroupTree']").treegrid({
		url:G_CTX_PATH+"/sys/userGroup/list",
		idField:"groupId",
		treeField:"groupName",
		onLoadSuccess:function(row,data){
			if (userId != 0) {
				Hg.getJson("/sys/sysUser/ugroupList",{userId:userId},function(authData){
					var list = authData.list;
					for (var i=0;i<list.length;i++) {
						var checkbox = treeObj.treegrid("getCheckBox",list[i].groupId).attr("checked",true);
						if (list[i].isFinal == 1) {
							checkbox.attr("disabled",true);
						}

					}
					
			});
		}
	}});
	

};

sysUser.initUserRoleGrid = function(userId){
	var gridObj = $("#sysUserDetail_layout [name='roleGrid']").datagrid({
		url:G_CTX_PATH+"/sys/role/list",
		onLoadSuccess:function(data){
			var rows = gridObj.datagrid("getRows");
			if (userId != 0) {
				Hg.getJson("/sys/sysUser/roleList",{userId:userId},function(roleData){
					roleData = roleData.list;
					for (var i=0;i<rows.length;i++) {
						for (var j=0;j<roleData.length;j++) {
							if (rows[i].roleId == roleData[j].roleId) {
								var checkbox = gridObj.datagrid("getCheckBox",i).attr("checked",true);
								if (roleData[j].isFinal == 1) {
									checkbox.attr("disabled",true);
								}
							}
						}
					}
				});
				
				
			}
		}
	});
	

};
sysUser.initSysUserDetail(userId);
sysUser.initUserAuthTree(userId);
sysUser.initUserUgroupTree(userId);
sysUser.initUserRoleGrid(userId);

		