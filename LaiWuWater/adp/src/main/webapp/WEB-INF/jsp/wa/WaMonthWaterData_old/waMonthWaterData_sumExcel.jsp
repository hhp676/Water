<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>下月计划用水批量导入</title>
</head>
<body>
<div style="padding-top: 20px;">
    <form   method="post" enctype="multipart/form-data"  id="monthWaterDataExcel" class="hgform">
        <table width="98%" border="0" cellpadding="0" align="center" cellspacing="1" class="hgtable">
            <tr>
                <td align="center">选择导入文件<font>*</font>:
                </td>
                <td align="left" ><input name="excelFile" type="file"  class="form-input"  id="monthWaterDataFile" multiple="multiple" /></td>
                <td><input name="import" type="button" value="导&nbsp;&nbsp;入" onclick="sumExcelUpload()"/>&nbsp;
                    <a href="${ctx}/static/template/下月用水计划表.xls" target="_blank">下月用水模板</a>
                </td>
            </tr>
            <tr>
                <td colspan="3" align="right">
                    <font>(文件必须为xls文件)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
                </td>
            </tr>
            <tr align="center" style="display: none" id="loadingExcel">
                <td colspan="3">正在导入，请等待 ...<br>
                    <%--<img src="${ctx}/images/loadingExcel.gif">--%>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    $("#monthWaterDataExcel").validate({
        rules : {
            excelFile : {
                required : true
            }
        },
        messages : {
            excelFile : {
                required : "请选择上传文件！ "
            }
        }
    });

    function sumExcelUpload(){
        if(!$('#monthWaterDataExcel').validate().form()) return false;
        $.messager.progress({
            title:"稍等",
            msg:"正在上传..."
        });
        $.ajaxFileUpload({
            //处理文件上传操作的服务器端地址
            url:"${ctx}/wa/WaMonthWaterData/getSumExcel",
            fileElementId:'monthWaterDataFile',           //文件选择框的id属性
            dataType:'json',                       //服务器返回的格式,可以是json或xml等
            success:function(data, status, XMLHttpRequest){ //, status
                if (data.result == "success") {
                    $.messager.ok("上传数据成功!",function(){
                        $("#wasumExcelWin").window("close");
                        $('#waMonthWaterData_datagrid').datagrid("reload");
                    });
                    $.messager.progress("close");
                }else {
                    $.messager.alert("提示",data.result);
                }
            },
            error:function(data, status, e){ //服务器响应失败时的处理函数
                $.messager.progress("close");
            }
        });
    }
</script>
</body>

</html>
