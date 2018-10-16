var sysUserLog = {};
sysUserLog.initSysUserGroupShow = function() {
	Hg.fixTableHeight("sysUserGroup_grid",true);
	Hg.initFreshPanel("sysUserGroup_grid");
	
	//--------------------------------------双击详情----------------------------------------------
	$('#sysUserGroup_grid').datagrid({onDblClickRow:function(){
		var row = $("#sysUserGroup_grid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}

		var id = row.logId;
		var url = "/sys/userLog/sysUserLogDetail/"+id;
		var iconCls = "m-icon-list";
		var sysVarDetailWin = new HgWin({id:"userLogDetailWin",title:"查看详情",width:700,height:280,iconCls:iconCls,url:url});
	}});
	
	
	$('#sysUserGroup_grid').datagrid("showCellTip","remark");
	//----------------------------------查询-----------------------------------------------------
	
	$("#sysUserGroup_toolbar [tag='search']").click(function(){
		if(!$('#wifiVarSearchForm').validate().form()) return false;
		 $('#sysUserGroup_grid').datagrid('load',{
			 userId: $("[name='userId']").val(),
			 userName: $("[name='userName']").val(),
			 operName: $("[name='operName']").val(),
			 operIP: $("[name='operIP']").val()
		 });
		 
	});
	
	$('#wifiVarSearchForm').validate({
		rules:{
			userId: { digits:true}
		}
	});
	
	//----------------------------------清空-----------------------------------------------------
	$("#sysUserGroup_toolbar [tag='clear']").click(function(){
		$('#wifiVarSearchForm').form('clear');
		$('#sysUserGroup_grid').datagrid("load",{});
	});

}

sysUserLog.initSysUserGroupShow();



