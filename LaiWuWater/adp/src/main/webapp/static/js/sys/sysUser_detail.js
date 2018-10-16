sysUser.initSysUserDetail = function(userId) {
	if (sysUser.mode == "view") {
		//查看详情时移除按钮
		$("#sysUserDetail_layout [tag='ok']").parent().remove();
		$('#sysUser_form').validate({
			rules:{}
		});
		//查看详情时不可编辑
		$("#sysUser_form [name='userName']").attr("readonly","readonly");
		$("#sysUser_form [name='userName']").mouseover(function(){
			$("#sysUser_form [name='userName']").attr("title",$("#sysUser_form [name='userName']").val());
		});
		$("#sysUser_form [name='loginName']").attr("readonly","readonly");
		$("#sysUser_form [name='loginName']").mouseover(function(){
			$("#sysUser_form [name='loginName']").attr("title",$("#sysUser_form [name='loginName']").val());
		});
		$("#sysUser_form [name='engName']").attr("readonly","readonly");
		$("#sysUser_form [name='engName']").mouseover(function(){
			$("#sysUser_form [name='engName']").attr("title",$("#sysUser_form [name='engName']").val());
		});
		$("#sysUser_form [name='sex']").attr("disabled","disabled");
		$("#sysUser_form [name='sex']").css("background-color","#fff");
		 
		$("#sysUser_form [name='birthday']").attr("disabled","disabled");
		$("#sysUser_form [name='birthday']").css("background-color","#fff");
		 
		$("#sysUser_form [name='mobile']").attr("readonly","readonly");
	 
		$("#sysUser_form [name='telephone']").attr("readonly","readonly");
		 
		$("#sysUser_form [name='email']").attr("readonly","readonly");
	 
		$("#sysUser_form [name='address']").attr("readonly","readonly");
		$("#sysUser_form [name='address']").mouseover(function(){
			$("#sysUser_form [name='address']").attr("title",$("#sysUser_form [name='address']").val());
		});
		$("#sysUser_form [name='descr']").attr("readonly","readonly");
		$("#sysUser_form [name='descr']").mouseover(function(){
			$("#sysUser_form [name='descr']").attr("title",$("#sysUser_form [name='descr']").val());
		});
		
	} else if (sysUser.mode == "add") {
		$("#sysUserDetail_layout [name='pwd']").show();
	} else if (sysUser.mode == "edit") {
		$("#sysUser_form [name='loginName']").attr("disabled","disabled");
		$("#sysUser_form [name='plainPassword']").attr("disabled","disabled");
		//$("#sysUser_form [name='plainPassword']").val("111111");
	}
	Hg.refRepeatSubmit("sysUser_form");//防止表单重复提交

	$('#sysUser_form').validate({
		rules:{
			userName: { required: true ,rangelength:[1,32]},
			loginName: { required: true ,rangelength:[1,32],stringCheck:true},
			plainPassword: { required: true ,rangelength:[8,20] },
			engName: {stringCheck:true,rangelength:[0,100]},
			email:{email:true,rangelength:[0,100]},
			mobile:{mobile:true},
			telephone:{number:true},
			descr: { rangelength:[0,100]},
			address:{rangelength:[0,100]}
			
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
		url:G_CTX_PATH+"/sys/auth/addInfoList?number="+ new Date().getTime(),
		idField:"authId",
		treeField:"authName",
		onLoadSuccess:function(row,data){
			if (userId == 0) {
				treeObj.treegrid("cascadeCheckBox","authId");
			}	
			if (userId != 0) {
				Hg.getJson("/sys/sysUser/authList?number="+ new Date().getTime(),{userId:userId},function(authData){
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
		Hg.showGridCellTip("sysUser_AuthTreeGrid", ["authId","authName"]);
	}});
	

};

sysUser.initUserUgroupTree = function(userId){
	var treeObj = $("#sysUserDetail_layout [name='ugroupTree']").treegrid({
		url:G_CTX_PATH+"/sys/userGroup/addInfoList?number="+ new Date().getTime(),
		idField:"groupId",
		treeField:"groupName",
		onLoadSuccess:function(row,data){
			if (userId != 0) {
				Hg.getJson("/sys/sysUser/ugroupList?number="+ new Date().getTime(),{userId:userId},function(authData){
					var list = authData.list;
					for (var i=0;i<list.length;i++) {
						var checkbox = treeObj.treegrid("getCheckBox",list[i].groupId).attr("checked",true);
						if (list[i].isFinal == 1) {
							checkbox.attr("disabled",true);
						}

					}
			});
		}
			Hg.showGridCellTip("sysUser_UserGroupTree", ["groupId","groupName"]);
	}});
	

};

sysUser.initUserRoleGrid = function(userId){
	var gridObj = $("#sysUserDetail_layout [name='roleGrid']").datagrid({
		url:G_CTX_PATH+"/sys/role/addInfoList?number="+ new Date().getTime(),
		onLoadSuccess:function(data){
			var rows = gridObj.datagrid("getRows");
			if (userId != 0) {
				Hg.getJson("/sys/sysUser/roleList?number="+ new Date().getTime(),{userId:userId},function(roleData){
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
			Hg.showGridCellTip("sysUser_RoleTree", ["roleId","roleName"]);
		}
	});
	

};
sysUser.initSysUserDetail(userId);
sysUser.initUserAuthTree(userId);
sysUser.initUserUgroupTree(userId);
sysUser.initUserRoleGrid(userId);
