var sysAuth = {};
sysAuth.initSysAuthShow = function() {
	Hg.initFreshPanel("sysAuth_grid");
	$('#sysAuth_grid').treegrid({loadFilter:function(data){
		data.footer = [{authId:"总数:",authName:data.rows.length,iconCls:"icon-sum"}];
		return data;
	}});
	//$('#sysAuth_grid').treegrid('reloadFooter',[{authId:"总数:",authName:"17",iconCls:"icon-sum"} ]);
	
	//----------------------------------查询-----------------------------------------------------
	$("#auth_toolbar [tag='search']").click(function(){
		 $('#sysAuth_grid').treegrid('load',{
			 authName: $("[name='authName']").val(),
			 authCode: $("[name='authCode']").val()
		 });
		 
	});
	
	
	//----------------------------------清空-----------------------------------------------------
	$("#auth_toolbar [tag='clear']").click(function(){
		$('#sysAuthSearchForm').form('clear');
		$('#sysAuth_grid').treegrid("load",{});
	});
	
	//---------------------新增--------------------------------------------------------
	$("#auth_toolbar_menu [tag='moduleAuth']").click(function(){
		sysAuth.mode = "add";
		var row = $("#sysAuth_grid").treegrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择父模块","warning");
			return;
		}
		if (row.authType != 1) {
			$.messager.alert("提示","非模块权限不可添加子权限","warning");
			return;
		}
		var iconCls = $(this).attr("iconCls");
		var addUrl = "/sys/auth/detail/1/0/"+row.authId;
		var authDetailWin = new HgWin({id:"authDetailWin",width:800,height:280,title:"添加模块权限",iconCls:iconCls,url:addUrl});
	});
	
	$("#auth_toolbar_menu [tag='operAuth']").click(function(){
		sysAuth.mode = "add";
		var row = $("#sysAuth_grid").treegrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择所属模块","warning");
			return;
		}
		if (row.authType != 1) {
			$.messager.alert("提示","非模块权限不可添加子权限","warning");
			return;
		}
		var iconCls = $(this).attr("iconCls");
		var addUrl = "/sys/auth/detail/2/0/"+row.authId;
		var authDetailWin = new HgWin({id:"authDetailWin",width:800,height:280,title:"添加操作权限",iconCls:iconCls,url:addUrl});
	});
	
	$("#auth_toolbar_menu [tag='appAuth']").click(function(){
		sysAuth.mode = "add";
		var iconCls = $(this).attr("iconCls");
		var addUrl = "/sys/auth/detail/0/0/0";
		var authDetailWin = new HgWin({id:"authDetailWin",width:800,height:200,title:"添加应用权限",iconCls:iconCls,url:addUrl});
	});
    
	
	
	
	
	//-------------------------------------------修改---------------------------------------------------
	$("#auth_toolbar [tag='edit'],#sysAuth_contextMenu [tag='edit']").click(function(){
		sysAuth.mode = "edit";
		var row = $("#sysAuth_grid").treegrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		if (row.isFinal == 1) {
			$.messager.alert("提示","此条数据不可被修改","warning");
			return;
		}
		var id = row.authId;
		var authType = row.authType;
		var editUrl = "/sys/auth/detail/"+authType+"/"+id+"/0";
		var winHeight = 200,winTitle = "修改应用权限";
		if (authType == 1) {//模块权限
			winHeight = 280,winTitle = "修改模块权限";
		} else if (authType == 2) {//操作权限
			winHeight = 280,winTitle = "修改操作权限";
		}
		
		var iconCls = $(this).attr("iconCls");
		var authDetailWin = new HgWin({id:"authDetailWin",width:800,height:winHeight,title:winTitle,iconCls:iconCls,url:editUrl+id});
	});
	//-------------------------------------------查看详情---------------------------------------------------
	$("#auth_toolbar [tag='view']").click(function(){
		sysAuth.mode = "view";
		var row = $("#sysAuth_grid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}

		var id = row.authId;
		var authType = row.authType;
		var url = "/sys/auth/detail/"+authType+"/"+id+"/0";
		var winHeight = 200;
		if (authType == 1) {//模块权限
			winHeight = 280;
		} else if (authType == 2) {//操作权限
			winHeight = 280;
		}
		
		var iconCls = $(this).attr("iconCls");
		var authDetailWin = new HgWin({id:"authDetailWin",width:800,height:winHeight,title:"查看详情",iconCls:iconCls,url:url+id});
	});
	
	
	//-------------------------------------------删除---------------------------------------------------
	$("#auth_toolbar [tag='del'],#sysAuth_contextMenu [tag='del']").click(function(){
		var row = $("#sysAuth_grid").treegrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		if (row.isFinal == 1) {
			$.messager.alert("提示","此条数据不可被删除","warning");
			return;
		}
		$.messager.confirm("删除确认", "确认删除此条数据?", function(r){
			if (r){
				var id = row.authId;
				var authType = row.authType;
				Hg.getJson("/sys/auth/delete",{authId:id,authType:authType},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#sysAuth_grid').treegrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
		
		
	});
	
	
	//操作类型
	$("#auth_toolbar [tag='oper']").click(function(){
		var iconCls = $(this).attr("iconCls");
		var url = "/sys/authoper/show";
		var authOperWin = new HgWin({id:"authOperWin",width:880,height:350,title:"操作类型管理",iconCls:iconCls,url:url});
	});
	

};

sysAuth.initSysAuthShow();
function sysAuthOnContextMenu(e,row){
	e.preventDefault();
	$('#sysAuth_grid').treegrid("select",row.authId);	
	$('#sysAuth_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};

function sysAuthOnDblClickRow(){
	$("#auth_toolbar [tag='view']").click();
};

