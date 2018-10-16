<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>详细页面</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true" id="waPlanYearWaterData_layout">
    <div data-options="region:'center'" style="padding: 10 10 10 20px;">
        <form id="waPlanYearWaterData_form"  class="hgform">
           <input type="hidden" name="planWaterId" value="${waPlanYearWaterData.planWaterId}" id="planWaterId"></input>
            <table class="hgtable">
                <tr>
                    <td width="130px;">单位名称<font>*</font>:</td>
                    <td>
                        <select name="companyId" style="width: 170px">
                            <c:forEach var="companyDataItem" items="${companyData}">
                                <option value="${companyDataItem.key}" <c:if test="${waPlanYearWaterData.companyId==companyDataItem.key}">selected</c:if>>${companyDataItem.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>时间年份<font>*</font>:</td>
                    <td><input type="text" style="width: 170px" name="planYear" id="planYear" value="${waPlanYearWaterData.planYear}"></input></td>
                </tr>
               <tr>
                    <td>计划的年份用水量:</td>
                    <td><input type="text" style="width: 170px"  name="planYearAvgWater" id="planYearAvgWater" value="${waPlanYearWaterData.planYearAvgWater}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" ></input></td>
                </tr>
                <tr>
                     <td>单位上报用水量:</td>
                     <td><input type="text" style="width: 170px" name="planYearEditWater" id="planYearEditWater" value="${waPlanYearWaterData.planYearEditWater}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" ></input></td>
                 </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south',border:false" style="text-align: right; padding: 5px 5px 5px; background-color: #e0e0e0;">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" tag="cancel">取消</a>
    </div>

</div>
<script type="text/javascript">
    var planWaterId = "${planWaterId}";
</script>
<script type="text/javascript" src="${ctx}/static/js/wa/waterPlanManagement/year/waPlanYearWaterData_detail.js"></script>
</body>

</html>
