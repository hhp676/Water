var sysDepartment = {};
sysDepartment.initsysDepartmentShow = function() {
	Hg.initFreshPanel("sysDepartment_grid");
	
	//----------------------------------查询-----------------------------------------------------
	$("#department_toolbar [tag='search']").click(function(){
		 $('#sysDepartment_grid').treegrid('load',{
			 departName: $("[name='departName']").val(),
			 departCode: $("[name='departCode']").val()
		 });
		 
	});
	
	
	//----------------------------------清空-----------------------------------------------------
	$("#department_toolbar [tag='clear']").click(function(){
		$('#sysDepartmentSearchForm').form('clear');
		$('#sysDepartment_grid').treegrid("load",{});
	});
	
	//-------------------------------------------增加---------------------------------------------------
	$("#department_toolbar [tag='add'],#sysDepartment_contextMenu [tag='add']").click(function(){
			sysDepartment.mode = "add";
			var row = $("#sysDepartment_grid").treegrid("getSelected");
			if(row == null) {
				var id = 0;
				var iconCls = $(this).attr("iconCls");
				var departmentDetailWin = new HgWin({id:"departmentDetailWin",width:530,height:270,title:"添加部门",iconCls:iconCls,url:"/sys/department/sysDepartmentAddDetail/" + id});
			} else 
			if (!row) {
				$.messager.alert("提示","请选择一条数据","warning");
				return;
			}
			var id = row.departId;
			var iconCls = $(this).attr("iconCls");
			var departmentDetailWin = new HgWin({id:"departmentDetailWin",width:570,height:270,title:"添加部门",iconCls:iconCls,url:"/sys/department/sysDepartmentAddDetail/" + id});


	});
	
	//-------------------------------------------修改---------------------------------------------------
	$("#department_toolbar [tag='edit'],#sysDepartment_contextMenu [tag='edit']").click(function(){
		sysDepartment.mode = "edit";
		var row = $("#sysDepartment_grid").treegrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		if (row.isFinal == 1) {
			$.messager.alert("提示","此条数据不可被修改","warning");
			return;
		}
		var id = row.departId;
		var editUrl = "/sys/department/sysDepartmentDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var departmentDetailWin = new HgWin({id:"departmentDetailWin",title:"修改部门信息",width:570,height:270,iconCls:iconCls,url:editUrl});
	});
	
	//-------------------------------------------查看详情---------------------------------------------------
	$("#department_toolbar [tag='view']").click(function(){
		sysDepartment.mode = "view";
		var row = $("#sysDepartment_grid").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}

		var id = row.departId;
		var url = "/sys/department/sysDepartmentDetail/"+id;
		var iconCls = $(this).attr("iconCls");
		var sysVarDetailWin = new HgWin({id:"departmentDetailWin",title:"查看详情",width:530,height:270,iconCls:iconCls,url:url});
	});
	
	//-------------------------------------------删除---------------------------------------------------
	$("#department_toolbar [tag='del'],#sysDepartment_contextMenu [tag='del']").click(function(){
		var row = $("#sysDepartment_grid").treegrid("getSelected");
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
				var id = row.departId;
				Hg.getJson("/sys/department/delete",{id:id},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#sysDepartment_grid').treegrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});
	});
	
}

sysDepartment.initsysDepartmentShow();

//------------------------------------------右键菜单--------------------------------------------------
sysDepartment.onContextMenu = function(e, row) {
	e.preventDefault();
	$(this).treegrid('select', row.departId);
	$('#sysDepartment_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
}
//------------------------------------------双击功能--------------------------------------------------
sysDepartment.onDblClickRow = function() {
	$("#department_toolbar [tag='view']").click();
}
