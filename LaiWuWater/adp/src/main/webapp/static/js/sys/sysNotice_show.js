var sysNotice = {};
sysNotice.tipCells = ["title","statusStr", "autoPubStr"];
sysNotice.initSysNoticeShow = function() {
	Hg.fixTableHeight("sysNotice_grid",true);
	Hg.initFreshPanel("sysNotice_grid");
	
    //-------------------------------------------增加---------------------------------------------------
	$("#sysNotice_toolbar [tag='add'],#sysNotice_contextMenu [tag='add']").click(function(){
		sysNotice.mode = "add";
		var iconCls = $(this).attr("iconCls");
		var sysNoticeDetailWin = new HgWin({id:"sysNoticeDetailWin",title:"添加系统公告",width:650,height:500,iconCls:iconCls,url:"/sys/notice/sysNoticeDetail/0"});
	});
    
	
	
	
	
	//-------------------------------------------修改---------------------------------------------------
	$("#sysNotice_toolbar [tag='edit'],#sysNotice_contextMenu [tag='edit']").click(function(){
		sysNotice.mode = "edit";
		var row = $("#sysNotice_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		if (row.isFinal == 1) {
			Hg.warning("此条数据不可被修改！",function(){});
			return;
		}
		var id = row.notifyId;
		var editUrl = "/sys/notice/sysNoticeDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysNoticeDetailWin = new HgWin({id:"sysNoticeDetailWin",title:"修改系统公告",width:700,height:500,iconCls:iconCls,url:editUrl});
	});
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#sysNotice_toolbar [tag='view']").click(function(){
		sysNotice.mode = "view";
		var row = $("#sysNotice_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}

		var id = row.notifyId;
		var url = "/sys/notice/sysNoticeDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysNoticeDetailWin = new HgWin({id:"sysNoticeDetailWin",title:"查看详情",width:700,height:500,iconCls:iconCls,url:url});
	});
	
	
	//-------------------------------------------删除---------------------------------------------------
	$("#sysNotice_toolbar [tag='del'],#sysNotice_contextMenu [tag='del']").click(function(){
//		Hg.removewindowshadow();
		var row = $("#sysNotice_grid").datagrid("getSelected");
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
				var id = row.notifyId;
				Hg.getJson("/sys/notice/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#sysNotice_grid').datagrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
		
		
	});
	
	
}

sysNotice.initSysNoticeShow();

sysNotice.onLoadSuccess = function(data) {
	Hg.showGridCellTip("sysNotice_grid",sysNotice.tipCells);
}
//Hg.removeMenu("sysNotice_contextMenu");
sysNotice.onRowContextMenu = function(e,row){
	Hg.removeMenu("sysNotice_contextMenu");
	e.preventDefault();
	$('#sysNotice_grid').datagrid("selectRow",row);
	$('#sysNotice_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};

sysNotice.onDblClickRow = function(){
	$("#sysVar_toolbar [tag='view']").click();
};
		