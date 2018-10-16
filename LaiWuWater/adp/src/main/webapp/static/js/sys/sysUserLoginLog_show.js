var sysUserLoginLog = {};

sysUserLoginLog.initSysUserGroupShow = function() {
	Hg.fixTableHeight("sysUserLoginLog_grid",true);
	Hg.initFreshPanel("sysUserLoginLog_grid");
	
	$('#sysUserLoginLogSearchForm').validate({
		rules:{
			userId: { digits:true}
		}
	});
	
//----------------------------------查询-----------------------------------------------------
	$("#sysUserLoginLog_toolbar [tag='search']").click(function(){
		if(!$('#sysUserLoginLogSearchForm').validate().form()) return false;
		 $('#sysUserLoginLog_grid').datagrid('load',{
			 accountId: $("[name='userId']").val(),
			 userName: $("[name='userName']").val(),
			 operName: $("[name='operName']").val(),
			 operIP: $("[name='operIP']").val()
		 });
		 
	});
//----------------------------------清空-----------------------------------------------------
	$("#sysUserLoginLog_toolbar [tag='clear']").click(function(){
		$('#sysUserLoginLogSearchForm').form('clear');
		$('#sysUserLoginLog_grid').datagrid("load",{});
	});
	
//-------------------------------------------查看详情---------------------------------------------------
	$("#sysUserLoginLog_toolbar [tag='view']").click(function(){
		sysUserLoginLog.mode = "view";
		var row = $("#sysUserLoginLog_grid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}

		var id = row.loginLogId;
		var url = "/sys/SysUserLoginLog/sysUserLoginLogDetail/"+id;
		var iconCls = "m-icon-list";
		var sysUserLoginLogDetailWin = new HgWin({id:"userLoginLogDetailWin",title:"查看详情",width:700,height:280,iconCls:iconCls,url:url});
	});
	

}
sysUserLoginLog.initSysUserGroupShow();