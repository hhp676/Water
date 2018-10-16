//@ sourceURL=waCompanyInfo_show.js
var waPlanYearWaterDataGrid =  {
};
//----需要悬浮提示的单元格
waPlanYearWaterDataGrid.tipCells = ["descr"];

waPlanYearWaterDataGrid.initPlanYearWaterData = function(){
    Hg.fixTableHeight("waPlanYearWaterData_datagrid",true);
    //----------------------------------查询-----------------------------------------------------
    $("#waPlanYearWaterData_toolbar [tag='search']").click(function(){
        $('#waPlanYearWaterData_datagrid').datagrid('load',{
            companyCode: $("[name='year_companyCode']").val(),
            companyName: $("[name='year_companyName']").val()/*,
            planYear: $("[name='planYear']").val(),
            isImport: $("[name='isImportMonth']").val()*/
        });
    });

    //----------------------------------清空-----------------------------------------------------
    $("#waPlanYearWaterData_toolbar [tag='clear']").click(function(){
        $('#waPlanYearWaterDataSearchForm').form('clear');
        $('#waPlanYearWaterData_datagrid').datagrid("load",{});
    });
    //-------------------------------------------增加---------------------------------------------------
    $("#waPlanYearWaterData_toolbar [tag='add']").click(function(){
        waPlanYearWaterDataGrid.mode = "add";
        var iconCls = $(this).attr("iconCls");
        var row = $("#waPlanYearWaterData_datagrid").datagrid("getSelected");
        var planWaterId=0;
        if(row){
            planWaterId = row.planWaterId;
        }
        var url="/wa/WaPlanYearWaterData/waPlanYearWaterDataDetail/"+planWaterId;
        var waPlanYearWaterDataDetailWin = new HgWin({id:"waPlanYearWaterDataDetailWin",title:"添加",width:850,height:400,iconCls:iconCls,url:url});
    });

    //-------------------------------------------修改---------------------------------------------------
    $("#waPlanYearWaterData_toolbar [tag='edit']").click(function(){
        waPlanYearWaterDataGrid.mode = "edit";
        var row = $("#waPlanYearWaterData_datagrid").datagrid("getSelected");
        if (!row) {
            $.messager.alert("提示","请选择一条数据1","warning");
            return;
        }
        if (row.isFinal == 1) {
            $.messager.alert("提示","此条数据不可被修改","warning");
            return;
        }
        var planWaterId = row.planWaterId;
        var url = "/wa/WaPlanYearWaterData/waPlanYearWaterDataDetail/"+planWaterId;
        var iconCls = $(this).attr("iconCls");
        var waPlanWaterDetailWin = new HgWin({id:"waPlanWaterDetailWin",title:"查看详情",width:850,height:380,iconCls:iconCls,url:url});
    });

    //-------------------------------------------查看详情---------------------------------------------------
    $("#waPlanYearWaterData_toolbar [tag='view']").click(function(){
        waPlanYearWaterDataGrid.mode = "view";
        var row = $("#waPlanYearWaterData_datagrid").datagrid("getSelected");
        if (!row) {
            $.messager.alert("提示","请选择一条数据","warning");
            return;
        }

        var planWaterId = row.planWaterId;
        var url = "/wa/WaPlanYearWaterData/waPlanYearWaterDataDetail/"+planWaterId;
        var iconCls = $(this).attr("iconCls");
        var waPlanWaterDetailWin = new HgWin({id:"waPlanWaterDetailWin",title:"查看详情",width:850,height:380,iconCls:iconCls,url:url});
    });

    //-------------------------------------------删除---------------------------------------------------
    $("#waPlanYearWaterData_toolbar [tag='del']").click(function(){
        var row = $("#waPlanYearWaterData_datagrid").datagrid("getSelected");
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
                var planWaterId = row.planWaterId;
                Hg.getJson("/wa/WaPlanYearWaterData/delete",{planWaterId: planWaterId},function(data){
                    if (data.success) {
                        $.messager.ok("删除成功!",function(){
                            $('#waPlanYearWaterData_datagrid').datagrid("reload");
                        });
                    } else {
                        $.messager.alert("删除失败!",data.data);
                    }
                });
            }
        });

    });

    //---------------计算下一年平均用水量----------------------------------
    $("#waPlanYearWaterData_toolbar [tag='compute']").click(function(){
        var row = $("#waPlanYearWaterData_datagrid").datagrid("getSelected");
        if (!row) {
            $.messager.alert("提示","请选择一条数据","warning");
            return;
        }

        var planWaterId = row.planWaterId;
        var url = "/wa/WaPlanYearWaterData/showCompute/"+planWaterId;
        var iconCls = $(this).attr("iconCls");
        var waPlanWaterComputeWin = new HgWin({id:"waPlanWaterComputeWin",title:"查看详情",width:850,height:380,iconCls:iconCls,url:url});
    });


    //---------------打印----------------------------------
    $("#waPlanYearWaterData_toolbar [tag='print']").click(function(){
        var row = $("#waPlanYearWaterData_datagrid").datagrid("getSelected");
        if (!row) {
            $.messager.alert("提示","请选择一条数据","warning");
            return;
        }
        var companyId = row.companyId;
        var companyName = row.companyName;
        var companyCode = row.companyCode;
        var planYear = row.planYear;
        var planYearAvgWater = row.planYearAvgWater;
        var url = "/wa/WaPlanYearWaterData/print/"+companyId+"/"+companyName+"/"+companyCode+"/"+planYear+"/"+planYearAvgWater;
        var iconCls = $(this).attr("iconCls");
        var waPlanWaterPrintWin = new HgWin({id:"waPlanWaterPrintWin",title:"打印详情",width:850,height:380,iconCls:iconCls,url:url});
    });

    //---------------------导入----------------------------------------------------------
    $("#waPlanYearWaterData_toolbar [tag='import']").click(function(){
        waPlanYearWaterDataGrid.mode = "import";
        var iconCls = $(this).attr("iconCls");
        var url = "/wa/WaPlanYearWaterData/showImportExcel/";
        var waYearWaterDataWin = new HgWin({id:"waYearWaterDataWin",title:"批量导入",width:500,height:180,iconCls:iconCls,url:url});

    });
}

waPlanYearWaterDataGrid.initPlanYearWaterData();
//去除input标签的记录功能
$('input:not([autocomplete]),textarea:not([autocomplete]),select:not([autocomplete])').attr('autocomplete', 'off');

//去除input标签的记录功能
$('input:not([autocomplete]),textarea:not([autocomplete]),select:not([autocomplete])').attr('autocomplete', 'off');
