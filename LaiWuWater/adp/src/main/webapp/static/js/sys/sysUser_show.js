var sysUser = {};
sysUser.tipCells = ["userName","engName", "loginName", "descr"];
sysUser.initSysUserShow = function() {
	Hg.fixTableHeight("sysUser_grid",true);
	
	//----------------------------------查询-----------------------------------------------------
	$("#user_toolbar [tag='search']").click(function(){
		 $('#sysUser_grid').datagrid('load',{
			 userName: $("[name='userName']").val(),
			 loginName: $("[name='loginName']").val()
		 });
		 
	});
	
	//----------------------------------锁定-----------------------------------------------------
	$("#user_toolbar [tag='lock']").click(function(){
		var row = $("#sysUser_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		
		if(row.isLock == 1){
			Hg.warning("当前用户已为锁定状态！",function(){});
			return;
		}
		
		$.messager.confirm("锁定", "确认将选中的用户锁定?", function(r) {
			if (r){
				Hg.post("/sys/sysUser/lock", {
					userId: row.userId,
				}, function(data) {
					if (data.success) {
						$.messager.ok("操作成功!",function(){
							$('#sysUser_grid').datagrid("reload");
						});							
					} else {
						$.messager.alert("操作失败!",data.data);
					}
				});
			}
		});
	});
	
	//---------------------解锁--------------------------------------------
	$("#user_toolbar [tag='unlock']").click(function(){
		var row = $("#sysUser_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		
		if(row.isLock == 0){
			Hg.warning("当前用户已为正常状态！",function(){});
			return;
		}
		
		$.messager.confirm("解锁", "确认将选中的用户解锁?", function(r) {
			if (r){
				Hg.post("/sys/sysUser/unlock", {
					userId: row.userId
				}, function(data) {
					if (data.success) {
						$.messager.ok("操作成功!",function(){
							$('#sysUser_grid').datagrid("reload");
						});							
					} else {
						$.messager.alert("操作失败!",data.data);
					}
				});
			}
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
		var sysUserDetailWin = new HgWin({id:"sysUserDetailWin",title:"添加用户",width:880,height:500,iconCls:iconCls,url:"/sys/sysUser/sysUserDetail/0/add"});
	});
    
	
	
	
	
	//-------------------------------------------修改---------------------------------------------------
	$("#user_toolbar [tag='edit'],#sysUser_contextMenu [tag='edit']").click(function(){
		sysUser.mode = "edit";
		var row = $("#sysUser_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		if (row.isFinal == 1) {
			Hg.warning("此条数据不可被修改！",function(){});
			return;
		}
		var id = row.userId;
		var editUrl = "/sys/sysUser/sysUserDetail/"+id+"/edit";
		var iconCls = $(this).attr("iconCls");
		var sysUserDetailWin = new HgWin({id:"sysUserDetailWin",title:"修改用户",width:880,height:500,iconCls:iconCls,url:editUrl});
	});
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#user_toolbar [tag='view']").click(function(){
		sysUser.mode = "view";
		var row = $("#sysUser_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		

		var id = row.userId;
		var url = "/sys/sysUser/sysUserDetail/"+id+"/view";
		var iconCls = $(this).attr("iconCls");
		var sysUserDetailWin = new HgWin({id:"sysUserDetailWin",title:"查看详情",width:880,height:500,iconCls:iconCls,url:url});
	});
	
	
	
	//--------------------------------------------改密-----------------------------------------------------
	$("#user_toolbar [tag='changePwd'],#sysUser_contextMenu [tag='changePwd']").click(function(){
		sysUser.mode = "changePwd";
		var row = $("#sysUser_grid").datagrid("getSelected");
		if (!row) {
			Hg.warning("请选择一条数据！",function(){});
			return;
		}
		var id = row.userId;
		var iconCls = $(this).attr("iconCls");
		var url = "/sys/sysUser/showChangePwd/"+id;
		var sysUserDetailWin = new HgWin({id:"sysUserDetailWin",title:"改密",width:430,height:250,iconCls:iconCls,url:url});
		
	});
	
	//--------------------------------------------导入-----------------------------------------------------
	$("#user_toolbar [tag='import']").click(function(){
		sysUser.mode = "import";
		var iconCls = $(this).attr("iconCls");
		var url = "/sys/sysUser/showImportExcel/";
		var sysUserImportWin = new HgWin({id:"sysUserImportWin",title:"批量导入",width:500,height:180,iconCls:iconCls,url:url});
		
	});
	
	
	//-------------------------------------------删除---------------------------------------------------
	$("#user_toolbar [tag='del'],#sysUser_contextMenu [tag='del']").click(function(){
//		Hg.removewindowshadow();
		var row = $("#sysUser_grid").datagrid("getSelected");
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

sysUser.onLoadSuccess = function(data) {
	Hg.showGridCellTip("sysUser_grid",sysUser.tipCells);
}
sysUser.onRowContextMenu = function(e,row){
	Hg.removeMenu("sysUser_contextMenu");
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