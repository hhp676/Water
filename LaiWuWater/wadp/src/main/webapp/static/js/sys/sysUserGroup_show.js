var sysUserGroup = {};
sysUserGroup.initSysUserGroupShow = function() {
	Hg.fixTableHeight("sysUserGroup_grid",true);
	Hg.initFreshPanel("sysUserGroup_grid");
	
	//----------------------------------查询-----------------------------------------------------
	$("#userGroup_toolbar [tag='search']").click(function(){
		 $('#sysUserGroup_grid').treegrid('load',{
			 groupName: $("[name='groupName']").val(),
			 groupCode: $("[name='groupCode']").val()
		 });
		 
	});
	
	
	//----------------------------------清空-----------------------------------------------------
	$("#userGroup_toolbar [tag='clear']").click(function(){
		$('#sysUserGroupSearchForm').form('clear');
		$('#sysUserGroup_grid').treegrid("load",{});
	});
	
	$("#sysUserGroup_grid").treegrid({onContextMenu:function(e,row){
		e.preventDefault();
		$("#sysUserGroup_grid").treegrid("select",row.groupId);
		$('#userGroup_contextMenu').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
	},onDblClickRow:function(){
		$("#userGroup_toolbar [tag='view']").click();
	}});
	
	
    //-------------------------------------------增加---------------------------------------------------
	$("#userGroup_toolbar,#userGroup_contextMenu").find("[tag='add']").click(function(){
		sysUserGroup.mode = "add";
		var iconCls = $(this).attr("iconCls");
		var row = $("#sysUserGroup_grid").treegrid("getSelected");
		var fid = 0;
		if (row) {
			fid = row.groupId;
		}
		var url = "/sys/userGroup/sysUserGroupDetail/0/"+fid;
		var sysUserGroupDetailWin = new HgWin({id:"sysUserGroupDetailWin",title:"添加用户组",width:830,height:350,iconCls:iconCls,url:url});
	});
    
	
	//-------------------------------------------修改---------------------------------------------------
	$("#userGroup_toolbar,#userGroup_contextMenu").find("[tag='edit']").click(function(){
		sysUserGroup.mode = "edit";
		var row = $("#sysUserGroup_grid").treegrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		if (row.isFinal == 1) {
			$.messager.alert("提示","此条数据不可被修改","warning");
			return;
		}
		var id = row.groupId;
		var editUrl = "/sys/userGroup/sysUserGroupDetail/"+id+"/0";
		var iconCls = $(this).attr("iconCls");
		var sysUserGroupDetailWin = new HgWin({id:"sysUserGroupDetailWin",title:"修改用户组",width:830,height:350,iconCls:iconCls,url:editUrl});
	});
	
	
	//-------------------------------------------详情---------------------------------------------------
	$("#userGroup_toolbar [tag='view']").click(function(){
		sysUserGroup.mode = "view";
		var row = $("#sysUserGroup_grid").treegrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		var id = row.groupId;
		var url = "/sys/userGroup/sysUserGroupDetail/"+id+"/0";
		var iconCls = $(this).attr("iconCls");
		var sysUserGroupDetailWin = new HgWin({id:"sysUserGroupDetailWin",title:"查看详情",width:830,height:350,iconCls:iconCls,url:url});
	});
	
	
	//-------------------------------------------删除---------------------------------------------------
	$("#userGroup_toolbar [tag='del'],#userGroup_contextMenu [tag='del']").click(function(){
		var row = $("#sysUserGroup_grid").treegrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		if (row.isFinal == 1) {
			$.messager.alert("提示","此条数据不可被删除","warning");
			return;
		}
		$.messager.confirm("删除确认", "确认删除此条数据?", function(r){
			if (r){
				var id = row.groupId;
				Hg.getJson("/sys/userGroup/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#sysUserGroup_grid').treegrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
		
		
	});
	
	
}

sysUserGroup.initSysUserGroupShow();
