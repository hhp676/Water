var jbzProductInfoDatagrid =  {};
//----需要悬浮提示的单元格
jbzProductInfoDatagrid.tipCells = ["descr"];
jbzProductInfoDatagrid.initjbzProductInfo = function(){
   	Hg.fixTableHeight("jbzProductInfo_datagrid",true);
    //----------------------------------查询-----------------------------------------------------
	$("#jbzProductInfo_toolbar [tag='search']").click(function(){
		 $('#jbzProductInfo_datagrid').datagrid('load',{
			 
		 });
		 
	});
 
    //----------------------------------清空-----------------------------------------------------
	$("#jbzProductInfo_toolbar [tag='clear']").click(function(){
		$('#jbzProductInfoForm').form('clear');
		$('#jbzProductInfo_datagrid').datagrid("load",{});
	});
	 //-------------------------------------------增加---------------------------------------------------
	$("#jbzProductInfo_toolbar [tag='add'],#jbzProductInfo_contextMenu [tag='add']").click(function(){
		jbzProductInfoDatagrid.mode = "add";
		var iconCls = $(this).attr("iconCls");
		var jbzProductInfoDetailWin = new HgWin({id:"jbzProductInfoDetailWin",title:"添加",width:850,height:400,iconCls:iconCls,url:"/wa/JbzProductInfo/jbzProductInfoDetail/0"});
	});
	
	//-------------------------------------------修改---------------------------------------------------
	$("#jbzProductInfo_toolbar [tag='edit'],#jbzProductInfo_contextMenu [tag='edit']").click(function(){
		jbzProductInfoDatagrid.mode = "edit";
		var row = $("#jbzProductInfo_datagrid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		if (row.isFinal == 1) {
			$.messager.alert("提示","此条数据不可被修改","warning");
			return;
		}
		var id = row.id;
		var editUrl = "/wa/JbzProductInfo/jbzProductInfoDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var jbzProductInfoDetailWin = new HgWin({id:"jbzProductInfoDetailWin",title:"修改",width:850,height:380,iconCls:iconCls,url:editUrl});
	});
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#jbzProductInfo_toolbar [tag='view']").click(function(){
		jbzProductInfoDatagrid.mode = "view";
		var row = $("#jbzProductInfo_datagrid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		

		var id = row.id;
		var url = "/wa/JbzProductInfo/jbzProductInfoDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var jbzProductInfoDetailWin = new HgWin({id:"jbzProductInfoDetailWin",title:"查看详情",width:850,height:380,iconCls:iconCls,url:url});
	});
	
	//-------------------------------------------删除---------------------------------------------------
	$("#jbzProductInfo_toolbar [tag='del'],#jbzProductInfo_contextMenu [tag='del']").click(function(){
		var row = $("#jbzProductInfo_datagrid").datagrid("getSelected");
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
				Hg.getJson("/wa/JbzProductInfo/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#jbzProductInfo_datagrid').datagrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
		
		
	});
	
} 

jbzProductInfoDatagrid.initjbzProductInfo();

jbzProductInfoDatagrid.onLoadSuccess = function(data) {  
	Hg.showGridCellTip("jbzProductInfo_datagrid",jbzProductInfoDatagrid.tipCells);
}
Hg.removeMenu("jbzProductInfo_contextMenu");

jbzProductInfoDatagrid.onRowContextMenu = function(e,row){
	Hg.removeMenu("jbzProductInfo_contextMenu");
	$('#jbzProductInfo_datagrid').datagrid("selectRow",row);
	e.preventDefault();
	$('#jbzProductInfo_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};

jbzProductInfoDatagrid.onDblClickRow = function(){
	$("#jbzProductInfo_toolbar [tag='view']").click();
};

