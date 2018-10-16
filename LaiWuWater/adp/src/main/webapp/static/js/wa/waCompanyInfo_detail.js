
waCompanyInfoDatagrid.initWaCompanyInfo = function(companyId) {
  //新增情况下隐藏上传附件功能
    if(companyId==0){
        $("#uploadFileTab").hide();
    }

    if (waCompanyInfoDatagrid.mode == "view") {
        //查看详情时移除按钮
        $("#waCompanyInfo_layout [tag='ok']").parent().remove();
        $('#waCompanyInfo_form').validate({
            rules:{  companyCode: { required: true ,rangelength:[1,100]}}
        });
        //查看详情时不可编辑
        $("#waCompanyInfo_layout [name='companyCode']").attr("readonly","readonly");
    } else if (waCompanyInfoDatagrid.mode == "edit") {
        // $("#waCompanyInfo_layout [name='companyId']").attr("disabled","disabled");
    }
    Hg.refRepeatSubmit("waCompanyInfo_form");//防止表单重复提交

    $('#waCompanyInfo_form').validate({
        rules:{
            companyCode: { required: true ,rangelength:[1,100]},
            companyName: { required: true ,rangelength:[1,100]}
        }
    });

    $("#waCompanyInfo_layout [tag='ok']").click(function(){
        if (companyId == 0) {
            _saveCompanyInfo(true);
        } else {
            _saveCompanyInfo(false);
        }
    });


    $("#waCompanyInfo_layout [tag='cancel']").click(function(){
        $("#waCompanyInfo_layout").parent().window("close");
    });


    //私有页面方法------------------------------------------------------------------------------------------------------
    function _saveCompanyInfo(isAdd) {
        //验证表单--------------------------------------------------
        if(!$('#waCompanyInfo_form').validate().form()) return false;
        $("#waCompanyInfo_layout").block();
        //提交数据--------------------------------------------------
        var submitUrl = "/wa/WaCompanyInfo/add";
        if (!isAdd) submitUrl = "/wa/WaCompanyInfo/update";
        Hg.getJson(submitUrl,$("#waCompanyInfo_form").serializeArray(),function(data){
            if (!data.success) {
                $("#waCompanyInfo_layout").unblock();
                Hg.refreshSubmitToken("waCompanyInfo_form");
                Hg.showErrorMsg("waCompanyInfo_form",data.data);
            } else {
                $.messager.ok("保存数据成功!",function(){
                    $("#waCompanyInfo_layout").parent().window("close");
                    $('#waCompanyInfo_datagrid_insert').datagrid("reload");
                    $('#waCompanyInfo_datagrid_edit').datagrid("reload");
                });
            }
        });
    }


    $("#waCompanyInfo_layout [tag='uploadFile']").click(function() {

        $.ajaxFileUpload({
            //处理文件上传操作的服务器端地址
            url: G_CTX_PATH +"/wa/WaCompanyInfo/uploadFtpFile/" + companyId,
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'uploadFile', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            method: "post",
            success: function (data, status) {
                $.messager.ok("保存数据成功!");
                var trHtml = "<tr id="+'file'+data.fileId +"><td>附件：</td><td> <a href='#' onclick=downFile('"+data.data+"')>"+ data.data +"</a> </td><td><a href='#' onclick=delFile("+data.fileId+")>删除</a></td></tr>";
                $("#companyDetailTab").append(trHtml);
            },
            error: function (data, status, e) { //服务器响应失败时的处理函数
                $.messager.ok("失败!");
            }
        });

    });

};

waCompanyInfoDatagrid.initWaCompanyInfo(companyId);
//去除input标签的记录功能
$('input:not([autocomplete]),textarea:not([autocomplete]),select:not([autocomplete])').attr('autocomplete', 'off');

/**
 * 下载文件，通过fileName
 * @param fileName
 */
function downFile(fileName) {
    $.ajaxFileUpload({
        //处理文件上传操作的服务器端地址
        url:G_CTX_PATH +"/wa/WaCompanyInfo/downLoadFile/"+fileName,
        secureuri: false, //是否需要安全协议，一般设置为false
        fileElementId: 'uploadFile', //文件上传域的ID
        dataType: 'json', //返回值类型 一般设置为json
        method: "get",
        success:function(data, status){
            $.messager.ok("下载文件成功!位置：");

        },
        error:function(data, status, e){ //服务器响应失败时的处理函数
            $.messager.ok("失败!");
        }
    });
}
/**
 * 删除文件by companyId
 * @param fileId
 */
function delFile(fileId){
    var delUrl = G_CTX_PATH +"/wa/WaCompanyInfo/delFile";
    Hg.getJson(delUrl,{"fileId" : fileId},function(data){
        if (!data.success) {
            $.messager.ok("删除文件失败!");
        } else {
            $.messager.ok("删除文件数据成功!",function(){
                //隐藏待删除的内容
                var delId= "file"+fileId;
                $("#"+delId).hide();
            });
        }
    });
}

		