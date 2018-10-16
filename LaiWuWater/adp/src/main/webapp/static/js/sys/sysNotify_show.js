var sysNotify = {};
sysNotify.tipCells = ["title" ];
sysNotify.initSysNotifyShow = function() {
	Hg.fixTableHeight("sysNotify_grid",true);
	Hg.initFreshPanel("sysNotify_grid");
	
   
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#sysNotify_toolbar [tag='view']").click(function(){
		sysNotify.mode = "view";
		var row = $("#sysNotify_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}

		var id = row.notifyId;
		var url = "/sys/notify/sysNotifyDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysNotifyDetailWin = new HgWin({id:"sysNotifyDetailWin",title:"查看详情",width:700,height:370,iconCls:iconCls,url:url});
	});
	
	$("#sysNotify_toolbar [tag='template']").click(function(){
		sysNotify.mode = "template";
		var url = "/sys/notifyTemplate/showSysNotifyTemplate"
		var iconCls = $(this).attr("iconCls");
		var sysNotifyTemplateWin = new HgWin({id:"sysNotifyTemplateWin",title:"通知模板",width:850,height:400,iconCls:iconCls,url:url});
	});
	
	
//----------------------------------查询-----------------------------------------------------
	
	$("#sysNotify_toolbar [tag='search']").click(function(){
		 $('#sysNotify_grid').datagrid('load',{
			 userId: $("[name='userId']").val(),
			 tempId: $("[name='tempId']").val()
		 });
		 
	});
	
	
	//----------------------------------清空-----------------------------------------------------
	$("#sysNotify_toolbar [tag='clear']").click(function(){
		$('#sysNotifySearchForm').form('clear');
		$('#sysNotify_grid').datagrid("load",{});
	});
	
	
	
	
};

sysNotify.initSysNotifyShow();

sysNotify.onLoadSuccess = function(data) {
	Hg.showGridCellTip("sysNotify_grid",sysNotify.tipCells);
}

 

sysNotify.onDblClickRow = function(){
	$("#sysNotify_toolbar [tag='view']").click();
};
		