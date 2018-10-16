var sysUserStateChange = {};

sysUserStateChange.init = function(){
	Hg.fixTableHeight("sysUserStateChange_grid",true);
	Hg.initFreshPanel("sysUserStateChange_grid");
	
//----------------------------------查询-----------------------------------------------------
	$("#sysUserStateChange_toolbar [tag='search']").click(function(){
		 $('#sysUserStateChange_grid').datagrid('load',{
			 userName: $.trim($("[name='userName']").val()),
			 ip : $.trim($("[name='ip']").val()),
			 crtType : $("[name='crtType']").val(),
			 originState : $("[name='originState']").val(),
			 currentState : $("[name='currentState']").val(),
			 isCurrent : $("[name='isCurrent']").val()
		 });
		 
	});
	
//----------------------------------清空-----------------------------------------------------
	$("#sysUserStateChange_toolbar [tag='clear']").click(function(){
		$('#sysUserStateChangeSearchForm').form('clear');
		$('#sysUserStateChange_grid').datagrid("load",{});
	});
	
};
sysUserStateChange.init();