
waCompanyInfoDatagrid.initTotal = function(companyId, dormitoryWaterId,commFactilitiesId,industryWaterId){
   var dorAdd = dormitoryWaterId == 0;
   var commAdd = commFactilitiesId == 0;
   var indAdd = industryWaterId == 0;
    $("#waCompanyInfo_totalInfo_layout [tag='ok']").click(function(){
        var resultBol1, resultBol12,resultBol13,resultBol14,resultBol15,resultBol16;
        if(dorAdd){
             _saveDormitoryWaterData(true);
        }else{
            _saveDormitoryWaterData(false);
        }
        if(commAdd){
            _saveCommFacilitiesWaterData(true);
        }else{
             _saveCommFacilitiesWaterData(false);
        }
        if(indAdd){
            _saveIndustryWater(true);
        }else{
            _saveIndustryWater(false);
        }
       /* if(resultBol1){
            $.messager.ok("保存数据成功!")
        }else {
            $.messager.ok("保存失败!")
        }*/
        $.messager.ok("保存数据成功!");
        $("#waCompanyInfo_totalInfo_layout").parent().window("close");
    });

    function _saveCommFacilitiesWaterData(isAdd){
        //验证公共设施用水表单--------------------------------------------------
        if(!$('#commFacWater_form').validate().form()) return false;
        $("#commFacWater_layout").block();
        //提交数据--------------------------------------------------
        var submitUrl = "/wa/WaCommFacilitiesWaterData/add";
        if (!isAdd) submitUrl = "/wa/WaCommFacilitiesWaterData/update";
        Hg.getJson(submitUrl,$("#commFacWater_form").serializeArray(),function(data){
            if (!data.success) {
                $("#commFacWater_layout").unblock();
                Hg.refreshSubmitToken("commFacWater_form");
                Hg.showErrorMsg("commFacWater_form",data.data);
            } else {
                /*$.messager.ok("保存数据成功!",function(){
                  /!*  $("#waCompanyInfo_layout").parent().window("close");
                    $('#waCompanyInfo_datagrid').datagrid("reload");*!/
                });*/
            }
        });
    }

    function _saveIndustryWater(isAdd){
        //验证工业用水表单--------------------------------------------------
        if(!$('#industryWater_form').validate().form()) return false;
        $("#industryWater_layout").block();
        //提交数据--------------------------------------------------
        var submitUrl = "/wa/WaIndustryWaterData/add";
        if (!isAdd) submitUrl = "/wa/WaIndustryWaterData/update";
        Hg.getJson(submitUrl,$("#industryWater_form").serializeArray(),function(data){
            if (!data.success) {
                // $("#industryWater_layout").unblock();
                // Hg.refreshSubmitToken("industryWater_form");
                // Hg.showErrorMsg("industryWater_form",data.data);
            } else {
                // _saveDormitoryWaterData(isAdd);
               /* $.messager.ok("保存数据成功!",function(){
                    /!*  $("#waCompanyInfo_layout").parent().window("close");
                     $('#waCompanyInfo_datagrid').datagrid("reload");*!/
                });*/
            }
        });
    }

    function _saveDormitoryWaterData(isAdd){
        //验证工业用水表单--------------------------------------------------
        if(!$('#dormitoryWater_form').validate().form()) return false;
        $("#dormitoryWater_layout").block();
        //提交数据--------------------------------------------------
        var submitUrl = "/wa/WaDormitoryWaterData/add";
        if (!isAdd) submitUrl = "/wa/WaDormitoryWaterData/update";
        Hg.getJson(submitUrl,$("#dormitoryWater_form").serializeArray(),function(data){
            if (!data.success) {
                $("#dormitoryWater_layout").unblock();
                Hg.refreshSubmitToken("dormitoryWater_form");
                Hg.showErrorMsg("dormitoryWater_form",data.data);
            } else {
                // $.messager.ok("保存数据成功!");
            }
        });
    }
//-------------取消操作------------------------------------------------
    $("#waCompanyInfo_totalInfo_layout [tag='cancel']").click(function(){
        $("#waCompanyInfo_totalInfo_layout").parent().window("close");
    });
}

waCompanyInfoDatagrid.initTotal(companyId, dormitoryWaterId,commFactilitiesId,industryWaterId);