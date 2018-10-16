var sysNotifyTemplate = {};
sysNotifyTemplate.tipCells = ["name","module", "titleTemplate"];
sysNotifyTemplate.initSysNotifyTemplateShow = function() {
	
    //-------------------------------------------增加---------------------------------------------------
	$("#sysNotifyTemplate_toolbar [tag='add'],#sysNotifyTemplate_contextMenu [tag='add']").click(function(){
		sysNotifyTemplate.mode = "add";
		var iconCls = $(this).attr("iconCls");
		var sysNotifyTemplateDetailWin = new HgWin({id:"sysNotifyTemplateDetailWin",title:"添加通知模板",width:700,height:400,iconCls:iconCls,url:"/sys/notifyTemplate/sysNotifyTemplateDetail/0"});
	});
    
	
	
	
	
	//-------------------------------------------修改---------------------------------------------------
	$("#sysNotifyTemplate_toolbar [tag='edit'],#sysNotifyTemplate_contextMenu [tag='edit']").click(function(){
		sysNotifyTemplate.mode = "edit";
		var row = $("#sysNotifyTemplate_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		if (row.isFinal == 1) {
			Hg.warning("此条数据不可被修改！",function(){});
			return;
		}
		var id = row.tempId;
		var editUrl = "/sys/notifyTemplate/sysNotifyTemplateDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysNotifyTemplateDetailWin = new HgWin({id:"sysNotifyTemplateDetailWin",title:"修改通知模板",width:700,height:400,iconCls:iconCls,url:editUrl});
	});
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#sysNotifyTemplate_toolbar [tag='view']").click(function(){
		sysNotifyTemplate.mode = "view";
		var row = $("#sysNotifyTemplate_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}

		var id = row.tempId;
		var url = "/sys/notifyTemplate/sysNotifyTemplateDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysNotifyTemplateDetailWin = new HgWin({id:"sysNotifyTemplateDetailWin",title:"查看详情",width:700,height:370,iconCls:iconCls,url:url});
	});
	
	
	//-------------------------------------------删除---------------------------------------------------
	$("#sysNotifyTemplate_toolbar [tag='del'],#sysNotifyTemplate_contextMenu [tag='del']").click(function(){
		//Hg.removewindowshadow();
		var row = $("#sysNotifyTemplate_grid").datagrid("getSelected");
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
				var id = row.tempId;
				Hg.getJson("/sys/notifyTemplate/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#sysNotifyTemplate_grid').datagrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
		
		
	});
	
	
}

sysNotifyTemplate.initSysNotifyTemplateShow();

sysNotifyTemplate.onLoadSuccess = function(data) {
	Hg.showGridCellTip("sysNotifyTemplate_grid",sysNotifyTemplate.tipCells);
}
//Hg.removeMenu("sysNotifyTemplate_contextMenu");
sysNotifyTemplate.onRowContextMenu = function(e,row){
	Hg.removeMenu("sysNotifyTemplate_contextMenu");
	e.preventDefault();
	$('#sysNotifyTemplate_grid').datagrid("selectRow",row);
	$('#sysNotifyTemplate_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};

sysNotifyTemplate.onDblClickRow = function(){
	$("#sysNotifyTemplate_toolbar [tag='view']").click();
};
		