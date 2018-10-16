var sysRole = {};
sysRole.tipCells = ["roleName","roleCode", "descr"];
sysRole.initSysRoleShow = function() {
	Hg.fixTableHeight("sysRole_grid",true);
	Hg.initFreshPanel("sysRole_grid");
	
	//----------------------------------查询-----------------------------------------------------
	$("#role_toolbar [tag='search']").click(function(){
		 $('#sysRole_grid').datagrid('load',{
			 roleName: $("[name='roleName']").val(),
			 roleCodeForQuery: $("[name='roleCode']").val()
		 });
		 
	});
	
	
	//----------------------------------清空-----------------------------------------------------
	$("#role_toolbar [tag='clear']").click(function(){
		$('#sysRoleSearchForm').form('clear');
		$('#sysRole_grid').datagrid("load",{});
	});
	
	
    //-------------------------------------------增加---------------------------------------------------
	$("#role_toolbar [tag='add'],#sysRole_contextMenu [tag='add']").click(function(){
		sysRole.mode = "add";
		var iconCls = $(this).attr("iconCls");
		var sysRoleDetailWin = new HgWin({id:"sysRoleDetailWin",title:"添加角色",width:800,height:350,iconCls:iconCls,url:"/sys/role/sysRoleDetail/0"});
	});
    
	
	//-------------------------------------------修改---------------------------------------------------
	$("#role_toolbar [tag='edit'],#sysRole_contextMenu [tag='edit']").click(function(){
//		Hg.removewindowshadow();
		sysRole.mode = "edit";
		var row = $("#sysRole_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		if (row.isFinal == 1) {
			Hg.warning("此条数据不可被修改！",function(){});
			return;
		}
		var id = row.roleId;
		var editUrl = "/sys/role/sysRoleDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysRoleDetailWin = new HgWin({id:"sysRoleDetailWin",title:"修改角色",width:800,height:350,iconCls:iconCls,url:editUrl});
	});
	
	
	//-------------------------------------------详情---------------------------------------------------
	$("#role_toolbar [tag='view']").click(function(){
		sysRole.mode = "view";
		var row = $("#sysRole_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		var id = row.roleId;
		var url = "/sys/role/sysRoleDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysRoleDetailWin = new HgWin({id:"sysRoleDetailWin",title:"查看详情",width:800,height:350,iconCls:iconCls,url:url});
	});
	
	
	//-------------------------------------------删除---------------------------------------------------
	$("#role_toolbar [tag='del'],#sysRole_contextMenu [tag='del']").click(function(){
//		Hg.removewindowshadow();
		var row = $("#sysRole_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		if (row.isFinal == 1) {
			Hg.warning("此条数据不可被删除！",function(){});
			return;
		}
		Hg.confirm("删除确认", "确认删除此条数据?(该动作将同时删除该角色设置的关联信息)", function(r){
			if (r){
				var id = row.roleId;
				Hg.getJson("/sys/role/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#sysRole_grid').datagrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
		
		
	});
	
	
}

sysRole.onLoadSuccess = function(data) {
	Hg.showGridCellTip("sysRole_grid",sysRole.tipCells);
}
//Hg.removeMenu("sysRole_contextMenu");

sysRole.initSysRoleShow();
sysRole.onRowContextMenu = function(e,row){
	Hg.removeMenu("sysRole_contextMenu");
	$('#sysRole_grid').datagrid("selectRow",row);
	e.preventDefault();
	$('#sysRole_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};
sysRole.onDblClickRow = function(index,row) {
	$("#role_toolbar [tag='view']").click();
};

sysRole.formatSysHref = function(val, row) {
	var html = val;
	if(val){
		if(row.roleId){
			html = "<a href='javascript:sysRole.userList(" + row.roleId + ")'>" + val
			+ "</a>";
		}else{
			html = val;
		}
	}
	
	return html;
};

sysRole.userList = function(roleId) {
	
	var url = "/sys/role/userByRoleListShow/"+roleId;
	
	new HgWin({id:"userNumber",title:"人员列表",width:700,height:400,iconCls:"m-icon-zy",url:url});
};
		