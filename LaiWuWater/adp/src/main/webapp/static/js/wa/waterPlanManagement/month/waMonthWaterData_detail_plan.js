
waMonthWaterDatagrid.initwaMonthWaterData = function(monthWaterId) {
    if (waMonthWaterDatagrid.mode == "view") {
        //查看详情时移除按钮
        $("#waMonthWaterData_layout [tag='ok']").parent().remove();
        $('#waMonthWaterData_form').validate({
            // rules:{  companyCode: { required: true ,rangelength:[1,100]}}
        });
        //查看详情时不可编辑
        $("#waMonthWaterData_layout [name='companyId']").attr("disabled","disabled");
    } else if (waMonthWaterDatagrid.mode == "edit") {
        $("#waMonthWaterData_layout [name='companyId']").attr("disabled","disabled");
       /* $("#waMonthWaterData_layout [name='feeStandard']").attr("disabled","disabled");*/
    }else if(waMonthWaterDatagrid.mode == "add"){
        monthWaterId = 0;
        $("#waMonthWaterData_layout [name='monthWaterId']").val("");
        $("#waMonthWaterData_layout [name='monthDate']").val("");
        $("#waMonthWaterData_layout [name='planMonthWater']").val("");
    }
    Hg.refRepeatSubmit("waMonthWaterData_form");//防止表单重复提交

    $('#waMonthWaterData_form').validate({
        rules:{
            planYear: { required: true ,rangelength:[1,100]},
            companyName: { required: true ,rangelength:[1,100]}
        }
    });

    $("#waMonthWaterData_layout [tag='ok']").click(function(){
        if (monthWaterId == 0) {
            _saveWaMonthWater(true);
        } else {
            _saveWaMonthWater(false);
        }
    });


    $("#waMonthWaterData_layout [tag='cancel']").click(function(){
        $("#waMonthWaterData_layout").parent().window("close");
    });


    //私有页面方法------------------------------------------------------------------------------------------------------
    function _saveWaMonthWater(isAdd) {
        //验证表单--------------------------------------------------
        if(!$('#waMonthWaterData_form').validate().form()) return false;
        $("#waMonthWaterData_layout").block();
        //提交数据--------------------------------------------------
        var submitUrl = "/wa/WaMonthWaterData/planAdd";
        if (!isAdd) submitUrl = "/wa/WaMonthWaterData/planUpdate";
        Hg.getJson(submitUrl,$("#waMonthWaterData_form").serializeArray(),function(data){
            if (!data.success) {
                $("#waMonthWaterData_layout").unblock();
                Hg.refreshSubmitToken("waMonthWaterData_form");
                Hg.showErrorMsg("waMonthWaterData_form",data.data);
            } else {
                $.messager.ok("保存数据成功!",function(){
                    $("#waMonthWaterData_layout").parent().window("close");
                    $('#waMonthWaterData_datagrid_plan').datagrid("reload");
                });
            }
        });
    }
};

waMonthWaterDatagrid.initwaMonthWaterData(monthWaterId);

//去除input标签的记录功能
$('input:not([autocomplete]),textarea:not([autocomplete]),select:not([autocomplete])').attr('autocomplete', 'off');

