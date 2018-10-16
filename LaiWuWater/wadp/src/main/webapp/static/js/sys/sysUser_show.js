var sysUser = {};
sysUser.initSysUserShow = function() {
	Hg.fixTableHeight("sysUser_grid",true);
	Hg.initFreshPanel("sysUser_grid");
	
	//----------------------------------查询-----------------------------------------------------
	$("#user_toolbar [tag='search']").click(function(){
		 $('#sysUser_grid').datagrid('load',{
			 userName: $("[name='userName']").val(),
			 loginName: $("[name='loginName']").val()
		 });
		 
	});
	
	
	//----------------------------------清空-----------------------------------------------------
	$("#user_toolbar [tag='clear']").click(function(){
		$('#sysUserSearchForm').form('clear');
		$('#sysUser_grid').datagrid("load",{});
	});
	
	
	 //-------------------------------------------增加---------------------------------------------------
	$("#user_toolbar [tag='add'],#sysUser_contextMenu [tag='add']").click(function(){
		sysUser.mode = "add";
		var iconCls = $(this).attr("iconCls");
		var sysUserDetailWin = new HgWin({id:"sysUserDetailWin",title:"添加用户",width:850,height:400,iconCls:iconCls,url:"/sys/sysUser/sysUserDetail/0"});
	});
    
	
	
	
	
	//-------------------------------------------修改---------------------------------------------------
	$("#user_toolbar [tag='edit'],#sysUser_contextMenu [tag='edit']").click(function(){
		sysUser.mode = "edit";
		var row = $("#sysUser_grid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		if (row.isFinal == 1) {
			$.messager.alert("提示","此条数据不可被修改","warning");
			return;
		}
		var id = row.userId;
		var editUrl = "/sys/sysUser/sysUserDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysUserDetailWin = new HgWin({id:"sysUserDetailWin",title:"修改用户",width:850,height:380,iconCls:iconCls,url:editUrl});
	});
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#user_toolbar [tag='view']").click(function(){
		sysUser.mode = "view";
		var row = $("#sysUser_grid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		

		var id = row.userId;
		var url = "/sys/sysUser/sysUserDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysUserDetailWin = new HgWin({id:"sysUserDetailWin",title:"查看详情",width:850,height:380,iconCls:iconCls,url:url});
	});
	
	//--------------------------------------------改密-----------------------------------------------------
	$("#user_toolbar [tag='changePwd'],#sysUser_contextMenu [tag='changePwd']").click(function(){
		sysUser.mode = "changePwd";
		var row = $("#sysUser_grid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		var id = row.userId;
		var iconCls = $(this).attr("iconCls");
		var url = "/sys/sysUser/showChangePwd/"+id;
		var sysUserDetailWin = new HgWin({id:"sysUserDetailWin",title:"改密",width:430,height:150,iconCls:iconCls,url:url});
		
	});
	
	
	//-------------------------------------------删除---------------------------------------------------
	$("#user_toolbar [tag='del'],#sysUser_contextMenu [tag='del']").click(function(){
		var row = $("#sysUser_grid").datagrid("getSelected");
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
				var id = row.userId;
				Hg.getJson("/sys/sysUser/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#sysUser_grid').datagrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
		
		
	});
	
};
sysUser.initSysUserShow();

sysUser.onRowContextMenu = function(e,row){
	$('#sysUser_grid').datagrid("selectRow",row);
	e.preventDefault();
	$('#sysUser_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};

sysUser.onDblClickRow = function(){
	$("#user_toolbar [tag='view']").click();
};