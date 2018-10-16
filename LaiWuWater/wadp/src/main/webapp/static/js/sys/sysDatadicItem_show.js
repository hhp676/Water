var sysDatadicItem = {};
sysDatadicItem.initSysDatadicItemShow = function() {
	Hg.fixTableHeight("sysDatadicItem_grid",true);
	Hg.initFreshPanel("sysDatadicItem_grid");
	
	//----------------------------------查询-----------------------------------------------------
	
	$("#sysDatadicItem_toolbar [tag='search']").click(function(){
		 $('#sysDatadicItem_grid').datagrid('load',{
			 itemName: $("[name='itemName']").val(),
			 groupName: $("[name='groupName']").val(),
			 groupCode: $("[name='groupCode']").val()
		 });
		 
	});
	
	
	//----------------------------------清空-----------------------------------------------------
	$("#sysDatadicItem_toolbar [tag='clear']").click(function(){
		$('#sysDatadicItemSearchForm').form('clear');
		$('#sysDatadicItem_grid').datagrid("load",{});
	});
	
	//-------------------------------------------新增---------------------------------------------------
	
	$("#sysDatadicItem_toolbar [tag='add'],#sysDatadicItem_contextMenu [tag='add']").click(function(){
		sysDatadicItem.mode = "add";
		var iconCls = $(this).attr("iconCls");
		var sysDatadicItemDetailWin = new HgWin({id:"sysDatadicItemDetailWin",title:"添加系统字典项",width:530,height:300,iconCls:iconCls,url:"/sys/datadic/item/sysDatadicItemDetail/0"});
	});
	
	
	
	
	
	//-------------------------------------------修改---------------------------------------------------
	$("#sysDatadicItem_toolbar [tag='edit'],#sysDatadicItem_contextMenu [tag='edit']").click(function(){
		sysDatadicItem.mode = "edit";
		var row = $("#sysDatadicItem_grid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		if (row.isFinal == 1) {
			$.messager.alert("提示","此条数据不可被修改","warning");
			return;
		}
		var id = row.itemId;
		var editUrl = "/sys/datadic/item/sysDatadicItemDetail/"+id;	
		var iconCls = $(this).attr("iconCls");
		var sysDatadicItemDetailWin = new HgWin({id:"sysDatadicItemDetailWin",title:"修改系统字典项",width:530,height:300,iconCls:iconCls,url:editUrl});
	});
	
	
	
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#sysDatadicItem_toolbar [tag='view']").click(function(){
		sysDatadicItem.mode = "view";
		var row = $("#sysDatadicItem_grid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}

		var id = row.itemId;
		var url = "/sys/datadic/item/sysDatadicItemDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysVarDetailWin = new HgWin({id:"sysDatadicItemDetailWin",title:"查看详情",width:530,height:300,iconCls:iconCls,url:url});
	});
	
	
	
	
	
	
	//-------------------------------------------删除---------------------------------------------------
	$("#sysDatadicItem_toolbar [tag='del'],#sysDatadicItem_contextMenu [tag='del']").click(function(){
		var row = $("#sysDatadicItem_grid").datagrid("getSelected");
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
				var id = row.itemId;
				Hg.getJson("/sys/datadic/item/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#sysDatadicItem_grid').datagrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
		
		
	});
	
	//----------------------------------------------字典组------------------------------------------------------
	$("#sysDatadicItem_toolbar [tag='datadicGroup']").click(function(){
		var url = "/sys/datadicgroup/showSysDatadicGroup";
		var iconCls = $(this).attr("iconCls");
		var sysDatadicGroupWin = new HgWin({id:"sysDatadicGroupWin",title:"数据字典组配置",width:900,height:450,iconCls:iconCls,url:url});
	});
}

sysDatadicItem.initSysDatadicItemShow();
sysDatadicItem.onRowContextMenu = function(e,row){
	e.preventDefault();
	$('#sysDatadicItem_grid').datagrid("selectRow",row);
	$('#sysDatadicItem_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};
sysDatadicItem.onDblClickRow = function() {
	$("#sysDatadicItem_toolbar [tag='view']").click();
}

