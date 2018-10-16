
waPlanYearWaterDataGrid.initPlanYearWaterDataDetail = function(planWaterId) {
    if (waPlanYearWaterDataGrid.mode == "view") {
        //查看详情时移除按钮
        $("#waPlanYearWaterData_layout [tag='ok']").parent().remove();
        $('#waPlanYearWaterData_form').validate({
            // rules:{  companyCode: { required: true ,rangelength:[1,100]}}
        });
        //查看详情时不可编辑
        $("#waPlanYearWaterData_layout [name='planWaterCompanyName']").attr("disabled","disabled");
    } else if (waPlanYearWaterDataGrid.mode == "edit") {
        $("#waPlanYearWaterData_layout [name='planWaterCompanyName']").attr("disabled","disabled");
    }else if(waPlanYearWaterDataGrid.mode == "add"){
        planWaterId = 0;
        $("#waPlanYearWaterData_layout [name='planWaterId']").val("");
        $("#waPlanYearWaterData_layout [name='planYear']").val("");
        $("#waPlanYearWaterData_layout [name='planYearAvgWater']").val("");
        $("#waPlanYearWaterData_layout [name='planYearEditWater']").val("");

    }
    Hg.refRepeatSubmit("waPlanYearWaterData_form");//防止表单重复提交

    $('#waPlanYearWaterData_form').validate({
        rules:{
            companyName: { required: true ,rangelength:[1,100]}  //,
        }
    });

    $("#waPlanYearWaterData_layout [tag='ok']").click(function(){
        if (planWaterId == 0) {
            _savePlanWaterData(true);
        } else {
            _savePlanWaterData(false);
        }
    });


    $("#waPlanYearWaterData_layout [tag='cancel']").click(function(){
        $("#waPlanYearWaterData_layout").parent().window("close");
    });


    //私有页面方法------------------------------------------------------------------------------------------------------
    function _savePlanWaterData(isAdd) {
        //验证表单--------------------------------------------------
        if(!$('#waPlanYearWaterData_form').validate().form()) return false;
        $("#waPlanYearWaterData_layout").block();
        //提交数据--------------------------------------------------
        var submitUrl = "/wa/WaPlanYearWaterData/add";
        if (!isAdd) submitUrl = "/wa/WaPlanYearWaterData/update";
        Hg.getJson(submitUrl,$("#waPlanYearWaterData_form").serializeArray(),function(data){
            if (!data.success) {
                $("#waPlanYearWaterData_layout").unblock();
                Hg.refreshSubmitToken("waPlanYearWaterData_form");
                Hg.showErrorMsg("waPlanYearWaterData_form",data.data);
            } else {
                $.messager.ok("保存数据成功!",function(){
                    $("#waPlanYearWaterData_layout").parent().window("close");
                    $('#waPlanYearWaterData_datagrid').datagrid("reload");
                });
            }
        });
    }
};

waPlanYearWaterDataGrid.initPlanYearWaterDataDetail(planWaterId);

//去除input标签的记录功能
$('input:not([autocomplete]),textarea:not([autocomplete]),select:not([autocomplete])').attr('autocomplete', 'off');

