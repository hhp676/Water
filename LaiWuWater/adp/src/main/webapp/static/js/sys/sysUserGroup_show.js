var sysUserGroup = {};
sysUserGroup.tipCells = ["groupName","groupCode", "departName", "descr"];
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
		Hg.removeMenu("userGroup_contextMenu");
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
		var sysUserGroupDetailWin = new HgWin({id:"sysUserGroupDetailWin",title:"添加用户组",width:800,height:350,iconCls:iconCls,url:url});
	});
    
	
	//-------------------------------------------修改---------------------------------------------------
	$("#userGroup_toolbar,#userGroup_contextMenu").find("[tag='edit']").click(function(){
		sysUserGroup.mode = "edit";
		var row = $("#sysUserGroup_grid").treegrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		if (row.isFinal == 1) {
			Hg.warning("此条数据不可被修改！",function(){});
			return;
		}
		var id = row.groupId;
		var editUrl = "/sys/userGroup/sysUserGroupDetail/"+id+"/0";
		var iconCls = $(this).attr("iconCls");
		var sysUserGroupDetailWin = new HgWin({id:"sysUserGroupDetailWin",title:"修改用户组",width:800,height:350,iconCls:iconCls,url:editUrl});
	});
	
	
	//-------------------------------------------详情---------------------------------------------------
	$("#userGroup_toolbar [tag='view']").click(function(){
		sysUserGroup.mode = "view";
		var row = $("#sysUserGroup_grid").treegrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		var id = row.groupId;
		var url = "/sys/userGroup/sysUserGroupDetail/"+id+"/0";
		var iconCls = $(this).attr("iconCls");
		var sysUserGroupDetailWin = new HgWin({id:"sysUserGroupDetailWin",title:"查看详情",width:800,height:350,iconCls:iconCls,url:url});
	});
	
	var i=0
	//-------------------------------------------删除---------------------------------------------------
	$("#userGroup_toolbar [tag='del'],#userGroup_contextMenu [tag='del']").click(function(e){
//		Hg.removewindowshadow();
		var row = $("#sysUserGroup_grid").treegrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		if (row.isFinal == 1) {
			Hg.warning("此条数据不可被删除！",function(){});
			return;
		}
		Hg.confirm("删除确认", "确认删除此条数据?(该动作将同时删除用户组子数据及设置的关联信息)", function(r){
			if (r){
				var id = row.groupId;
				Hg.getJson("/sys/userGroup/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
//							$('#sysUserGroup_grid').treegrid("reload");
							$("#userGroup_toolbar [tag='search']").click();
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
			i=0;
		});
		
	});
	
	
}

sysUserGroup.showSysUserGroupUserCountFormatter = function(val,row){
	var html = val;
	 if(val){
		if(row.groupId){
			html = "<a href='javascript:sysUserGroup.showSysUserGroupCountDetail(" + row.groupId + ");'>" + val
			+ "</a>";
		}else{
			html = val;
		}
	 }
	return html;
};

sysUserGroup.showSysUserGroupCountDetail = function(groupId) {
	new HgWin({
		id : "showSysUserGroupCountDetail",
		title : "用户组人员统计详情",
		width : 900,
		height : 450,
		iconCls : "",
		url : "/sys/userGroup/showSysUserGroupUserCountDetail/"+groupId
	});
	
};

sysUserGroup.initSysUserGroupShow();

//分组形式，需要直接写 function 加载成功方法
Hg.removeMenu("userGroup_contextMenu");
 function sysUserGroupOnLoadSuccess(data) {
	Hg.showGridCellTip("sysUserGroup_grid",sysUserGroup.tipCells);
}
 
 
