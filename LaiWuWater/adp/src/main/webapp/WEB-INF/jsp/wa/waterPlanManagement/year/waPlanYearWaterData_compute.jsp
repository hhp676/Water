<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>详细页面</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true" id="waPlanYearWaterCompute_layout">
    <div data-options="region:'center'" style="padding: 10 10 10 20px;">
        <form id="waPlanYearWaterCompute_form"  class="hgform">
           <input type="hidden" name="companyId" value="${companyId}" id="companyId"></input>
            <table class="hgtable">
                <tr>
                    <td width="130px;">单位名称:</td>
                    <td>${companyName}</td>
                </tr>
                <tr>
                    <td width="30px;">年份</td>
                    <td width="130px;">全年用水量</td>
                </tr>
                    <c:forEach items="${waterDataList}" var="keyword" varStatus="waterList" begin="0">
                        <tr>
                            <td width="30px;">${keyword.planYear}</td>
                            <td width="130px;">${keyword.planYearAvgWater}</td>
                        </tr>
                    </c:forEach>
            </table>
        </form>
        <hr>
        <br>
        <div>
            <table class="hgtable">
                <tr><td><a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="computeYear">计算下一年</a></td>
                    <td> <label id="newYearData">${newYearData} 年：</label></td>
                    <td> <input type="text" name="computeResult" id="computeResult" ></td>
                </tr>
                <tr>

                </tr>
            </table>
        </div>
    </div>

    <div data-options="region:'south',border:false" style="text-align: right; padding: 5px 5px 5px; background-color: #e0e0e0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" tag="cancel">取消</a>
    </div>

</div>
<script type="text/javascript">
    var planWaterId = "${planWaterId}";
    var companyId = "${companyId}";
    var newYearData = "${newYearData}";
</script>
<script type="text/javascript" src="${ctx}/static/js/wa/waterPlanManagement/year/waPlanYearWaterData_computer.js"></script>
</body>

</html>
