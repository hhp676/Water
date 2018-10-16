var sysMetaMethodDatagrid =  {};
//----需要悬浮提示的单元格
sysMetaMethodDatagrid.tipCells = [ "className", "methodName", "argsName",
		"methodCode", "methodZhName", "methodEngName", "logLevel", "logType", "logRemarkClass", "descr" ];
sysMetaMethodDatagrid.initsysMetaMethod = function(){
   	Hg.fixTableHeight("sysMetaMethod_datagrid",true);
    //----------------------------------查询-----------------------------------------------------
	$("#sysMetaMethod_toolbar [tag='search']").click(function(){
		 $('#sysMetaMethod_datagrid').datagrid('load',{
			 className: $("[name='className']").val(),
			 methodName: $("[name='methodName']").val(),
			 argsName: $("[name='argsName']").val(),
			 methodCode: $("[name='methodCode']").val(),
			 methodZhName: $("[name='methodZhName']").val(),
			 methodEngName: $("[name='methodEngName']").val(),
			 descr: $("[name='descr']").val()
		 });
		 
	});
 
    //----------------------------------清空-----------------------------------------------------
	$("#sysMetaMethod_toolbar [tag='clear']").click(function(){
		$('#sysMetaMethodSearchForm').form('clear');
		$('#sysMetaMethod_datagrid').datagrid("load",{});
	});
	//-------------------------------------------增加---------------------------------------------------
	$("#sysMetaMethod_toolbar [tag='add'],#sysMetaMethod_contextMenu [tag='add']").click(function(){
		sysMetaMethodDatagrid.mode = "add";
		var iconCls = $(this).attr("iconCls");
		var sysMetaMethodDetailWin = new HgWin({
			id : "sysMetaMethodDetailWin",
			title : "添加系统元方法表",
			width : 850,
			height : 400,
			iconCls : iconCls,
			url : "/sys/sysMetaMethod/sysMetaMethodDetail/0"
		});
	});
	
	//-------------------------------------------修改---------------------------------------------------
	$("#sysMetaMethod_toolbar [tag='edit'],#sysMetaMethod_contextMenu [tag='edit']").click(function(){
		sysMetaMethodDatagrid.mode = "edit";
		var row = $("#sysMetaMethod_datagrid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		if (row.isValid == 0) {
			$.messager.alert("提示","不在用的数据无法修改","warning");
			return;
		}
		if (row.isFinal == 1) {
			$.messager.alert("提示","此条数据不可被修改","warning");
			return;
		}
		var id = row.metaMethodId;
		var editUrl = "/sys/sysMetaMethod/sysMetaMethodDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysMetaMethodDetailWin = new HgWin({id:"sysMetaMethodDetailWin",title:"修改系统元方法表",width:850,height:380,iconCls:iconCls,url:editUrl});
	});
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#sysMetaMethod_toolbar [tag='view']").click(function(){
		sysMetaMethodDatagrid.mode = "view";
		var row = $("#sysMetaMethod_datagrid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}

		var id = row.metaMethodId;
		var url = "/sys/sysMetaMethod/sysMetaMethodDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysMetaMethodDetailWin = new HgWin({id:"sysMetaMethodDetailWin",title:"查看详情",width:850,height:380,iconCls:iconCls,url:url});
	});
	
	//-------------------------------------------删除---------------------------------------------------
	$("#sysMetaMethod_toolbar [tag='del'],#sysMetaMethod_contextMenu [tag='del']").click(function(){
		var row = $("#sysMetaMethod_datagrid").datagrid("getSelected");
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
				var id = row.metaMethodId;
				Hg.getJson("/sys/sysMetaMethod/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#sysMetaMethod_datagrid').datagrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
		
		
	});
	
	//-------------------------------------------一键初始化Controller方法---------------------------------------------------
	$("#sysMetaMethod_toolbar [tag='init'],#sysMetaMethod_contextMenu [tag='init']").click(function(){
		$.messager.confirm("数据初始化确认", "确认初始化所有Controller方法?", function(r){
			if (r){
				Hg.getJson("/sys/sysMetaMethod/init",null,function(data){
					if (data.success) {
						$.messager.ok("初始化成功!",function(){
							$('#sysMetaMethod_datagrid').datagrid("reload");
						});
					} else {
						$.messager.alert("初始化失败!",data.data);
					}
				});
			}
		});
	});
} 

sysMetaMethodDatagrid.initsysMetaMethod();

sysMetaMethodDatagrid.onLoadSuccess = function(data) {  
	Hg.showGridCellTip("sysMetaMethod_datagrid",sysMetaMethodDatagrid.tipCells);
}
Hg.removeMenu("sysMetaMethod_contextMenu");

sysMetaMethodDatagrid.onRowContextMenu = function(e,row){
	Hg.removeMenu("sysMetaMethod_contextMenu");
	$('#sysMetaMethod_datagrid').datagrid("selectRow",row);
	e.preventDefault();
	$('#sysMetaMethod_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};

sysMetaMethodDatagrid.onDblClickRow = function(){
	$("#sysMetaMethod_toolbar [tag='view']").click();
};

