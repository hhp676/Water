var nextYearWater;
waPlanYearWaterDataGrid.initComputeDetail = function(companyId, planWaterId, newYearData){
    $("#waPlanYearWaterCompute_layout [tag='ok']").click(function(){
        nextYearWater =  $("#computeResult").val();
        if(nextYearWater.length != 0){
            var WaPlanYearWaterData = {};
            WaPlanYearWaterData.companyId = companyId;
            WaPlanYearWaterData.nextYearWater = nextYearWater;
            WaPlanYearWaterData.newYearData = newYearData;

            Hg.getJson("/wa/WaPlanYearWaterData/savePlanWater",{companyId: companyId,planYear: newYearData,planYearAvgWater: nextYearWater},function(data){
                if (data.success) {
                    $.messager.ok("保存成功!",data.data);
                    $("#waPlanYearWaterCompute_layout").parent().window("close");
                    $('#waPlanYearWaterData_datagrid').datagrid("reload");

                } else {
                    $.messager.alert("保存失败!",data.data);
                }
            });
        }

    });

    $("#waPlanYearWaterCompute_layout [tag='cancel']").click(function(){
        $("#waPlanYearWaterCompute_layout").parent().window("close");
    });

    //-------------计算---------------------------------------------
    $("#waPlanYearWaterCompute_layout [tag='computeYear']").click(function(){
        Hg.getJson("/wa/WaPlanYearWaterData/compute",{companyId: companyId},function(data){
            if (data.success) {
                $("#computeResult").val(data.result);
            } else {
                $.messager.alert("删除失败!",data.data);
            }
        });
    });
}

waPlanYearWaterDataGrid.initComputeDetail(companyId, planWaterId, newYearData);

//去除input标签的记录功能
$('input:not([autocomplete]),textarea:not([autocomplete]),select:not([autocomplete])').attr('autocomplete', 'off');