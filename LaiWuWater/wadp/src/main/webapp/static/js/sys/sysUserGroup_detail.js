sysUserGroup.initSysUserGroupDetail = function(groupId) {	
	if (sysUserGroup.mode == "view") {
		//查看详情时移除按钮
		$("#sysUserGroupDetail_layout [tag='ok']").parent().remove();
		if (!boFid) {
			$("#sysUserGroupDetail_layout [name='fIdRow']").hide();
		}
	} else if (sysUserGroup.mode == "add") {
		if (fid == 0) {
			$("#sysUserGroupDetail_layout [name='fIdRow']").hide();
		}
	} else if (sysUserGroup.mode == "edit") {
		if (!boFid) {
			$("#sysUserGroupDetail_layout [name='fIdRow']").hide();
		}
		$("#sysUserGroup_form [name='fid']").val(boFid);
		$("#sysUserGroup_form [name='groupCode']").attr("disabled","disabled");
	}
	
	Hg.refRepeatSubmit("sysUserGroup_form");//防止表单重复提交
	$('#sysUserGroup_form').validate({
		rules:{
			groupName: { required: true ,rangelength:[1,32]},
			groupCode: { required: true ,rangelength:[1,20]},
			descr: { rangelength:[0,100]}
		}
	});
	
	
	
	$("#sysUserGroupDetail_layout [tag='ok']").click(function(){
		if (groupId == 0) {
			_saveSysUserGroup(true);
		} else {
			_saveSysUserGroup(false);
		}
		
	});
	

	
	$("#sysUserGroupDetail_layout [tag='cancel']").click(function(){
		$("#sysUserGroupDetail_layout").parent().window("close");
	});
	
	
	//处理右侧选中的权限
	function handlerCheckedAuth(){
		$("#sysUserGroup_form [name='checkedAuthIds']").val($("#sysUserGroupDetail_layout [name='authTree']").treegrid("getCheckedIds"));
	};
	function handlerCheckedRole(){
		$("#sysUserGroup_form [name='checkedRoleIds']").val($("#sysUserGroupDetail_layout [name='roleGrid']").datagrid("getCheckedIds","roleId"));
	};
	function handlerRadioVal(){
		var radioVal = $("#sysUserGroupDetail_layout [name='departTree']").treegrid("getPanel").find("input[name='departIdRadio']:checked").val();
		$("#sysUserGroup_form [name='departId']").val(radioVal);
	}
	
	//私有页面方法------------------------------------------------------------------------------------------------------
	function _saveSysUserGroup(isAdd) {
		//验证表单--------------------------------------------------
		if(!$('#sysUserGroup_form').validate().form()) return false;
		$("#sysUserGroupDetail_layout").block();

		//处理右侧选中的权限
		handlerCheckedAuth();
		handlerCheckedRole();
		handlerRadioVal();
		
		//提交数据--------------------------------------------------
		var submitUrl = "/sys/userGroup/add";
		if (!isAdd) submitUrl = "/sys/userGroup/edit";
		Hg.getJson(submitUrl,$("#sysUserGroup_form").serializeArray(),function(data){
			if (!data.success) {
				$("#sysUserGroupDetail_layout").unblock();
				Hg.refreshSubmitToken("sysUserGroup_form");
				Hg.showErrorMsg("sysUserGroup_form",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#sysUserGroupDetail_layout").parent().window("close");
					$('#sysUserGroup_grid').treegrid("reload");
				});
			}
		});
	}
};



sysUserGroup.initUserGroupAuthTree = function(groupId){
	var treeObj = $("#sysUserGroupDetail_layout [name='authTree']").treegrid({
		url:G_CTX_PATH+"/sys/auth/list",
		idField:"authId",
		treeField:"authName",
		onLoadSuccess:function(row,data){
			if (groupId != 0) {
				Hg.getJson("/sys/userGroup/authList",{groupId:groupId},function(authData){
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

sysUserGroup.initUserGroupRoleGrid = function(groupId){
	var gridObj = $("#sysUserGroupDetail_layout [name='roleGrid']").datagrid({
		url:G_CTX_PATH+"/sys/role/list",
		onLoadSuccess:function(data){
			var rows = gridObj.datagrid("getRows");
			if (groupId != 0) {
				Hg.getJson("/sys/userGroup/roleList",{groupId:groupId},function(roleData){
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
sysUserGroup.initUserGroupDepartTree = function(groupId){
	var treeObj = $("#sysUserGroupDetail_layout [name='departTree']").treegrid({
		url:G_CTX_PATH+"/sys/department/list",
		idField:"departId",
		treeField:"departName",
		onLoadSuccess:function(row,data){
			if (groupId != 0) {
				var departId = $("#sysUserGroup_form [name='departId']").val();
				 $("#sysUserGroupDetail_layout [name='departTree']").treegrid("getPanel").find("input:radio[value='"+departId+"']").attr("checked","checked");
			}
		}
	});
};
sysUserGroup.initUserGroupDepartTree(groupId);
sysUserGroup.initSysUserGroupDetail(groupId);
sysUserGroup.initUserGroupAuthTree(groupId);
sysUserGroup.initUserGroupRoleGrid(groupId);

function departTreeFmt(value,row,index) {
	return "<input type='radio' name='departIdRadio' value='"+value+"'/>";
};