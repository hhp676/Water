var ${meta.firstLowerClassName}Datagrid =  {};
//----需要悬浮提示的单元格
${meta.firstLowerClassName}Datagrid.tipCells = ["descr"];
${meta.firstLowerClassName}Datagrid.init${meta.firstLowerClassName} = function(){
   	Hg.fixTableHeight("${meta.firstLowerClassName}_datagrid",true);
    //----------------------------------查询-----------------------------------------------------
	$("#${meta.firstLowerClassName}_toolbar [tag='search']").click(function(){
		 $('#${meta.firstLowerClassName}_datagrid').datagrid('load',{
			 
		 });
		 
	});
 
    //----------------------------------清空-----------------------------------------------------
	$("#${meta.firstLowerClassName}_toolbar [tag='clear']").click(function(){
		$('#${meta.firstLowerClassName}Form').form('clear');
		$('#${meta.firstLowerClassName}_datagrid').datagrid("load",{});
	});
	 //-------------------------------------------增加---------------------------------------------------
	$("#${meta.firstLowerClassName}_toolbar [tag='add'],#${meta.firstLowerClassName}_contextMenu [tag='add']").click(function(){
		${meta.firstLowerClassName}Datagrid.mode = "add";
		var iconCls = $(this).attr("iconCls");
		var ${meta.firstLowerClassName}DetailWin = new HgWin({id:"${meta.firstLowerClassName}DetailWin",title:"添加${meta.tableDesc}",width:850,height:400,iconCls:iconCls,url:"/${meta.module}/${meta.className}/${meta.firstLowerClassName}Detail/0"});
	});
	
	//-------------------------------------------修改---------------------------------------------------
	$("#${meta.firstLowerClassName}_toolbar [tag='edit'],#${meta.firstLowerClassName}_contextMenu [tag='edit']").click(function(){
		${meta.firstLowerClassName}Datagrid.mode = "edit";
		var row = $("#${meta.firstLowerClassName}_datagrid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		if (row.isFinal == 1) {
			$.messager.alert("提示","此条数据不可被修改","warning");
			return;
		}
		var id = row.id;
		var editUrl = "/${meta.module}/${meta.className}/${meta.firstLowerClassName}Detail/"+id;
		var iconCls = $(this).attr("iconCls");
		var ${meta.firstLowerClassName}DetailWin = new HgWin({id:"${meta.firstLowerClassName}DetailWin",title:"修改${meta.tableDesc}",width:850,height:380,iconCls:iconCls,url:editUrl});
	});
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#${meta.firstLowerClassName}_toolbar [tag='view']").click(function(){
		${meta.firstLowerClassName}Datagrid.mode = "view";
		var row = $("#${meta.firstLowerClassName}_datagrid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		

		var id = row.id;
		var url = "/${meta.module}/${meta.className}/${meta.firstLowerClassName}Detail/"+id;
		var iconCls = $(this).attr("iconCls");
		var ${meta.firstLowerClassName}DetailWin = new HgWin({id:"${meta.firstLowerClassName}DetailWin",title:"查看详情",width:850,height:380,iconCls:iconCls,url:url});
	});
	
	//-------------------------------------------删除---------------------------------------------------
	$("#${meta.firstLowerClassName}_toolbar [tag='del'],#${meta.firstLowerClassName}_contextMenu [tag='del']").click(function(){
		var row = $("#${meta.firstLowerClassName}_datagrid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		if (row.isFinal == 1) {
			$.messager.alert("提示","此条数据不可被修改","warning");
			return;
		}
		$.messager.confirm("删除确认", "确认删除此条数据?", function(r){
			if (r){
				var id = row.id;
				Hg.getJson("/${meta.module}/${meta.className}/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#${meta.firstLowerClassName}_datagrid').datagrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
		
		
	});
	
} 

${meta.firstLowerClassName}Datagrid.init${meta.firstLowerClassName}();

${meta.firstLowerClassName}Datagrid.onLoadSuccess = function(data) {  
	Hg.showGridCellTip("${meta.firstLowerClassName}_datagrid",${meta.firstLowerClassName}Datagrid.tipCells);
}
Hg.removeMenu("${meta.firstLowerClassName}_contextMenu");

${meta.firstLowerClassName}Datagrid.onRowContextMenu = function(e,row){
	Hg.removeMenu("${meta.firstLowerClassName}_contextMenu");
	$('#${meta.firstLowerClassName}_datagrid').datagrid("selectRow",row);
	e.preventDefault();
	$('#${meta.firstLowerClassName}_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};

${meta.firstLowerClassName}Datagrid.onDblClickRow = function(){
	$("#${meta.firstLowerClassName}_toolbar [tag='view']").click();
};

 