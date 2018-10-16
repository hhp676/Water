var waMonthWaterDatagrid =  {
    /**
	 * 格式化时间
     * @param value
     * @returns {string}
     */
    datetoLocal: function (value){
		var unixTimestamp = new Date(value);
		 var year = unixTimestamp.getFullYear();
		 var month = unixTimestamp.getMonth() + 1;
		 var day = unixTimestamp.getDate() + 1;
		 var hours = unixTimestamp.getHours() + 1;
		 var minutes = unixTimestamp.getMinutes() + 1;
		 var seconde = unixTimestamp.getSeconds() + 1;
		 var result = year + "-" + month;
			 //+ day + "  " + hours + ":" + minutes + ":" + seconde;
		 return result;
    }
};
//----需要悬浮提示的单元格
waMonthWaterDatagrid.tipCells = ["descr"];
var searchType = "actSearch";
waMonthWaterDatagrid.initwaMonthWaterData = function(){
   	Hg.fixTableHeight("waMonthWaterData_datagrid_actSearch",true);
    //----------------------------------查询-----------------------------------------------------
	$("#waMonthWaterData_toolbar_actSearch [tag='search']").click(function(){
		 $('#waMonthWaterData_datagrid_actSearch').datagrid('load',{
             companyCode: $("[name='month_companyCodeMonth_actSearch']").val(),
			 companyName: $("[name='month_companyNameMonth_actSearch']").val(),
             isOverroof: $("[name='month_isOverroof_actSearch']").val(),
             waYear: $("[name='month_waYear_actSearch']").val(),
             waMonth: $("[name='month_waMonth_actSearch']").val(),
             isImport: $("[name='month_isImportMonth_actSearch']").val()
		 });
	});

    //----------------------------------清空-----------------------------------------------------
	$("#waMonthWaterData_toolbar_actSearch [tag='clear']").click(function(){
		$('#waMonthWaterDataSearchForm_actSearch').form('clear');
		$('#waMonthWaterData_datagrid_actSearch').datagrid("load",{});
	});
	/* //-------------------------------------------增加---------------------------------------------------
	$("#waMonthWaterData_toolbar_actInsert [tag='add']").click(function(){
		waMonthWaterDatagrid.mode = "add";
        // waMonthWaterDatagrid.selCompany = "0";
        var monthWaterId = 0;
        var companyId=0;
        var row = $("#waMonthWaterData_datagrid_actInsert").datagrid("getSelected");
		if (row){
            monthWaterId = row.monthWaterId;
		}
		var url = "/wa/WaMonthWaterData/waMonthWaterDataDetail/"+monthWaterId;
		var iconCls = $(this).attr("iconCls");
		var waMonthWaterDataDetailWin = new HgWin({id:"waMonthWaterDataDetailWin",title:"添加",width:850,height:400,iconCls:iconCls,url:url});
	});*/

    //-------------------------------------------查看详情---------------------------------------------------
    $("#waMonthWaterData_toolbar_actSearch [tag='view']").click(function(){
        waMonthWaterDatagrid.mode = "view";
        var row = $("#waMonthWaterData_datagrid_actSearch").datagrid("getSelected");
        if (!row) {
            $.messager.alert("提示","请选择一条数据","warning");
            return;
        }

        var monthWaterId = row.monthWaterId;
        var url = "/wa/WaMonthWaterData/waMonthWaterDataDetail/"+monthWaterId + "/" + searchType;
        var iconCls = $(this).attr("iconCls");
        var waMonthWaterDataDetailWin = new HgWin({id:"waMonthWaterDataDetailWin",title:"查看详情",width:850,height:380,iconCls:iconCls,url:url});
    });

    //-------------------------------------------打印功能---------------------------------------------------
    $("#waMonthWaterData_toolbar_actSearch [tag='print']").click(function(){
        waMonthWaterDatagrid.mode = "print";
        var row = $("#waMonthWaterData_datagrid_actSearch").datagrid("getSelected");
        if (!row) {
            $.messager.alert("提示","请选择一条数据","warning");
            return;
        }

        var monthWaterId = row.monthWaterId;
        var url = "/wa/WaMonthWaterData/waterDataPrint/"+monthWaterId;
        var iconCls = $(this).attr("iconCls");
        var waterDataPrintWin = new HgWin({id:"waterDataPrintWin",title:"打印信息预览",width:850,height:380,iconCls:iconCls,url:url});
    });

	/*//-------------------------------------------修改---------------------------------------------------
	$("#waMonthWaterData_toolbar_actInsert [tag='edit']").click(function(){
		waMonthWaterDatagrid.mode = "edit";
		var row = $("#waMonthWaterData_datagrid_actInsert").datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示","请选择一条数据","warning");
			return;
		}
		if (row.isFinal == 1) {
			$.messager.alert("提示","此条数据不可被修改","warning");
			return;
		}
        var monthWaterId = row.monthWaterId;
        var url = "/wa/WaMonthWaterData/waMonthWaterDataDetail/"+monthWaterId;
        var iconCls = $(this).attr("iconCls");
        var waMonthWaterDataDetailWin = new HgWin({id:"waMonthWaterDataDetailWin",title:"查看详情",width:850,height:380,iconCls:iconCls,url:url});
	});
	


	//-------------------------------------------删除---------------------------------------------------
	$("#waMonthWaterData_toolbar_actInsert [tag='del']").click(function(){
		var row = $("#waMonthWaterData_datagrid_actInsert").datagrid("getSelected");
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
				var monthWaterId = row.monthWaterId;
				Hg.getJson("/wa/WaMonthWaterData/delete",{monthWaterId: monthWaterId},function(data){
					if (data.success) {
						$.messager.ok("删除成功!",function(){
							$('#waMonthWaterData_datagrid_actInsert').datagrid("reload");
						});							
					} else {
						$.messager.alert("删除失败!",data.data);
					}
				});
			}
		});

	});


*/

	/*//---------------------导入----------------------------------------------------------
	$("#waMonthWaterData_toolbar_actInsert [tag='import']").click(function(){
            waMonthWaterDatagrid.mode = "import";
        var iconCls = $(this).attr("iconCls");
        var url = "/wa/WaMonthWaterData/showImportExcel/";
        var waMonthWaterDataWin = new HgWin({id:"waMonthWaterDataWin",title:"批量导入",width:500,height:180,iconCls:iconCls,url:url});

    });

    //---------------------汇总excel----------------------------------------------------------
    $("#waMonthWaterData_toolbar__actSearch [tag='sumExcel']").click(function(){
        waMonthWaterDatagrid.mode = "sumExcel";
        var iconCls = $(this).attr("iconCls");
        var url = "/wa/WaMonthWaterData/showsumExcel/";
        var wasumExcelWin = new HgWin({id:"wasumExcelWin",title:"批量导入",width:500,height:180,iconCls:iconCls,url:url});

    });*/
} 

waMonthWaterDatagrid.initwaMonthWaterData();

//去除input标签的记录功能
$('input:not([autocomplete]),textarea:not([autocomplete]),select:not([autocomplete])').attr('autocomplete', 'off');

