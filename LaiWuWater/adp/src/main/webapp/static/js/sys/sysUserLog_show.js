var sysUserLog = {};

sysUserLog.tipCells = ["logTypeStr","userName", "operCode","operName","operIP","remark"];
sysUserLog.initSysUserGroupShow = function() {
	Hg.fixTableHeight("sysUserLog_grid",true);
	Hg.initFreshPanel("sysUserLog_grid");
	
	//--------------------------------------双击详情----------------------------------------------
	//----------------------------------查询-----------------------------------------------------
	
	$("#sysUserLog_toolbar [tag='search']").click(function(){
		if(!$('#sysUserLogSearchForm').validate().form()) return false;
		 $('#sysUserLog_grid').datagrid('load',{
			 userId: $("[name='userId']").val(),
			 userName: $("[name='userName']").val(),
			 operName: $("[name='operName']").val(),
			 operIP: $("[name='operIP']").val()
		 });
		 
	});
	
	$('#sysUserLogSearchForm').validate({
		rules:{
			userId: { digits:true}
		}
	});
	
	//----------------------------------清空-----------------------------------------------------
	$("#sysUserLog_toolbar [tag='clear']").click(function(){
		$('#sysUserLogSearchForm').form('clear');
		$('#sysUserLog_grid').datagrid("load",{});
	});
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#sysUserLog_toolbar [tag='view']").click(function(){
		sysUserLog.mode = "view";
		var row = $("#sysUserLog_grid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}

		var id = row.logId;
		var url = "/sys/userLog/sysUserLogDetail/"+id;
		var iconCls = "m-icon-list";
		var sysUserLogDetailWin = new HgWin({id:"userLogDetailWin",title:"查看详情",width:700,height:280,iconCls:iconCls,url:url});
	});

}

sysUserLog.initSysUserGroupShow();


sysUserLog.onLoadSuccess = function(data) {
	Hg.showGridCellTip("sysUserLog_grid",sysUserLog.tipCells);
}

sysUserLog.onDblClickRow = function(index,row) {
	$("#sysUserLog_toolbar [tag='view']").click();
};
