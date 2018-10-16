var sysVar = {};
sysVar.tipCells = ["varName","varValue", "descr"];

sysVar.initSysVarShow = function() {
	Hg.fixTableHeight("sysVar_grid",true);
	Hg.initFreshPanel("sysVar_grid");
	
    //-------------------------------------------增加---------------------------------------------------
	$("#sysVar_toolbar [tag='add'],#sysVar_contextMenu [tag='add']").click(function(){
		sysVar.mode = "add";
		var iconCls = $(this).attr("iconCls");
		var sysVarDetailWin = new HgWin({id:"sysVarDetailWin",title:"添加系统变量",width:500,height:300,iconCls:iconCls,url:"/sys/var/sysVarDetail/0"});
	});
    
	
	
	
	
	//-------------------------------------------修改---------------------------------------------------
	$("#sysVar_toolbar [tag='edit'],#sysVar_contextMenu [tag='edit']").click(function(){
		sysVar.mode = "edit";
		var row = $("#sysVar_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		if (row.isFinal == 1) {
			Hg.warning("此条数据不可被修改！",function(){});
			return;
		}
		var id = row.varId;
		var editUrl = "/sys/var/sysVarDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysVarDetailWin = new HgWin({id:"sysVarDetailWin",title:"修改系统变量",width:500,height:300,iconCls:iconCls,url:editUrl});
	});
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#sysVar_toolbar [tag='view']").click(function(){
		sysVar.mode = "view";
		var row = $("#sysVar_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}

		var id = row.varId;
		var url = "/sys/var/sysVarDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysVarDetailWin = new HgWin({id:"sysVarDetailWin",title:"查看详情",width:500,height:250,iconCls:iconCls,url:url});
	});
	
	
	//-------------------------------------------删除---------------------------------------------------
	$("#sysVar_toolbar [tag='del'],#sysVar_contextMenu [tag='del']").click(function(){
//		Hg.removewindowshadow();
		var row = $("#sysVar_grid").datagrid("getSelected");
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
				var id = row.varId;
				Hg.getJson("/sys/var/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#sysVar_grid').datagrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
		
		
	});
	
	//----------------------------------查询-----------------------------------------------------
	
	$("#sysVar_toolbar [tag='search']").click(function(){
		 $('#sysVar_grid').datagrid('load',{
			 varId: $("[name='varId']").val(),
			 varName: $("[name='varName']").val()
		 });
		 
	});
	
	
	//----------------------------------清空-----------------------------------------------------
	$("#sysVar_toolbar [tag='clear']").click(function(){
		$('#wifiVarSearchForm').form('clear');
		$('#sysVar_grid').datagrid("load",{});
	});
}

sysVar.initSysVarShow();

sysVar.onLoadSuccess = function(data) {
	Hg.showGridCellTip("sysVar_grid",sysVar.tipCells);
}
//Hg.removeMenu("sysVar_contextMenu");
sysVar.onRowContextMenu = function(e,row){
	Hg.removeMenu("sysVar_contextMenu");
	e.preventDefault();
	$('#sysVar_grid').datagrid("selectRow",row);
	$('#sysVar_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};

sysVar.onDblClickRow = function(){
	$("#sysVar_toolbar [tag='view']").click();
};	