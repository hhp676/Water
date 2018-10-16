    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript" src="${ctx}/static/js/wa/jquery.PrintArea.js"></script>
<html>
<head>
    <title>打印预览</title>
    <style type="text/css">

    </style>

</head>
<body>
<div class="easyui-layout" data-options="fit:true" id="print_layout">
    <div id="area1" style="width: 100%; float: left; margin: inherit;">
        <br>
        <br>
        <table border="1" align="center" style="font-size: large; color:#333333; border-collapse: collapse; border: inherit 10px;">
            <tr style="border-collapse: collapse">
                <th >单位名称</th>
                <th >计划用水量</th>
                <th >实际用水量</th>
            </tr>
            <tr>
                <td>${waMonthWaterData.companyName}</td>
                <td>${waMonthWaterData.planMonthWater}</td>
                <td>${waMonthWaterData.actMonthWater}</td>
            </tr>
        </table>

        <br>

                        日期：2018.XX.XX
        <br>
                        签字：___________
    </div>
    <div data-options="region:'south',border:false" style="text-align: right; padding: 5px 5px 5px; background-color: #e0e0e0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="print"  onclick="print();">执行打印</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" tag="cancel" onclick="cancel()">取消</a>
    </div>

</div>
<script type="text/javascript">
    print = function(){
        //mode:popup弹窗执行打印/popClose打印执行完毕自动关闭/extraHead头信息
        var options = { mode : 'popup', popClose : 'popup',
            extraHead : '<meta charset="utf-8" />,<meta http-equiv="X-UA-Compatible" content="IE=edge"/>' };
        //选择器可以书写多个,以英文逗号间隔,如:#area1,#area2也可以书写样式选择器
        $( "#area1" ).printArea( options );
    }

    cancel = function(){
        $("#print_layout").parent().window("close");
    }

</script>

</body>
</html>
