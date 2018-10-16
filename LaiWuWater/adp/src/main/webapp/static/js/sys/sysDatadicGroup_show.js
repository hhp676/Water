var sysDatadicGroup = {};
sysDatadicGroup.tipCells = ["groupCode","groupName", "groupDesc"];
sysDatadicGroup.initSysDatadicGroupShow = function() {
	
	//----------------------------------查询-----------------------------------------------------
	
	$("#sysDatadicGroup_toolbar [tag='search']").click(function(){
		 $('#sysDatadicGroup_grid').datagrid('load',{
			 groupName: $("#sysDatadicGroupSearchForm [name='groupName']").val(),
			 groupCode: $("#sysDatadicGroupSearchForm [name='groupCode']").val()
		 });
		 
	});
	
	
	//----------------------------------清空-----------------------------------------------------
	$("#sysDatadicGroup_toolbar [tag='clear']").click(function(){
		$('#sysDatadicGroupSearchForm').form('clear');
		$('#sysDatadicGroup_grid').datagrid("load",{});
	});
	
	
	//-------------------------------------------新增---------------------------------------------------
	
	$("#sysDatadicGroup_toolbar [tag='add']").click(function(){
		sysDatadicGroup.mode = "add";
		var iconCls = $(this).attr("iconCls");
		var sysDatadicGroupDetailWin = new HgWin({id:"sysDatadicGroupDetailWin",title:"添加系统字典组",width:500,height:350,iconCls:iconCls,url:"/sys/datadicgroup/sysDatadicGroupDetail/0"});
	});
	
	
	//-------------------------------------------修改---------------------------------------------------
	$("#sysDatadicGroup_toolbar [tag='edit']").click(function(){
		sysDatadicGroup.mode = "edit";
		var row = $("#sysDatadicGroup_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		if (row.isFinal == 1) {
			Hg.warning("此条数据不可被修改！",function(){});
			return;
		}
		var id = row.groupId;
		var editUrl = "/sys/datadicgroup/sysDatadicGroupDetail/"+id;	
		var iconCls = $(this).attr("iconCls");
		var sysDatadicGroupDetailWin = new HgWin({id:"sysDatadicGroupDetailWin",title:"修改系统字典组",width:500,height:350,iconCls:iconCls,url:editUrl});
	});
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#sysDatadicGroup_toolbar [tag='view']").click(function(){
		sysDatadicGroup.mode = "view";
		var row = $("#sysDatadicGroup_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}

		var id = row.groupId;
		var url = "/sys/datadicgroup/sysDatadicGroupDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysDatadicGroupDetailWin = new HgWin({id:"sysDatadicGroupDetailWin",title:"查看详情",width:500,height:250,iconCls:iconCls,url:url});
	});
	
	
	
	
	
	//-------------------------------------------删除---------------------------------------------------
	$("#sysDatadicGroup_toolbar [tag='del']").click(function(){
		var row = $("#sysDatadicGroup_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		if (row.isFinal == 1) {
			Hg.warning("此条数据不可被删除！",function(){});
			return;
		}
		Hg.confirm("删除确认", "确认删除此条数据?", function(r){
			if (r){
				var id = row.groupId;
				Hg.getJson("/sys/datadicgroup/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#sysDatadicGroup_grid').datagrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
		
		
	});
	
	
}

sysDatadicGroup.initSysDatadicGroupShow();

sysDatadicGroup.onLoadSuccess = function(data) {
	Hg.showGridCellTip("sysDatadicGroup_grid",sysDatadicGroup.tipCells);
}

sysDatadicGroup.onDblClickRow = function() {
	$("#sysDatadicGroup_toolbar [tag='view']").click();
}