<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>详细页面</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true" id="waCompanyInfo_totalInfo_layout">
    <div data-options="region:'west'" style="">
        <div  class="easyui-tabs" fit=true tabPosition="top">
            <div title="公共设施用水量" id="commFacWater_layout" style="padding:2px">
                <form id="commFacWater_form"  class="hgform">
                    <table fit=true name="commFacWaterTab" id="commFacWaterTab" singleSelect=false fitColumns=true>
                        <thead>
                        <input type="hidden"   name="companyId" value="${companyId}" id="companyId1"></input>
                        <input type="hidden"   name="commFactilitiesId" value="${commFacData.commFactilitiesId}" id="commFactilitiesId"></input>

                        <tr>
                            <td width="130px;">新水量<font>*</font>:</td>
                            <td><input type="text"   name="newWaterAmount" value="${commFacData.newWaterAmount}" id="newWaterAmount"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">冷却水新水量<font>*</font>:</td>
                            <td><input type="text"   name="coldNewWaterAmount" value="${commFacData.coldNewWaterAmount}" id="coldNewWaterAmount"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">经营性新水量<font>*</font>:</td>
                            <td><input type="text"   name="managerNewWaterAmount" value="${commFacData.managerNewWaterAmount}" id="managerNewWaterAmount"></input></td>
                        </tr> <tr>
                            <td width="130px;">重复利用水量<font>*</font>:</td>
                            <td><input type="text"   name="reuseWaterAmount" value="${commFacData.reuseWaterAmount}" id="reuseWaterAmount"></input></td>
                        </tr> <tr>
                            <td width="130px;">冷却水循环量<font>*</font>:</td>
                            <td><input type="text"   name="coldLoopWaterAmount" value="${commFacData.coldLoopWaterAmount}" id="coldLoopWaterAmount"></input></td>
                        </tr>
                        </thead>
                    </table>
                </form>
            </div>

            <div title="工业用水统计信息" id="industryWater_layout" style="padding:2px">
                <form id="industryWater_form"  class="hgform">
                    <table fit=true name="industryWaterTab" id="industryWaterTab" singleSelect=false fitColumns=true>
                        <thead>
                        <input type="hidden"   name="companyId" value="${companyId}" id="companyId2"></input>
                        <input type="hidden"   name="industryWaterId" value="${industryWaterData.industryWaterId}" id="industryWaterId"></input>
                        <tr>
                            <td width="130px;">工业新水量合计<font>*</font>:</td>
                            <td><input type="text"   name="newWaterTotalCount" value="${industryWaterData.newWaterTotalCount}" id="newWaterTotalCount"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">间冷水<font>*</font>:</td>
                            <td><input type="text"   name="interColdWater" value="${industryWaterData.interColdWater}" id="interColdWater"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">工艺水<font>*</font>:</td>
                            <td><input type="text"   name="freshWater" value="${industryWaterData.freshWater}" id="freshWater"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">锅炉水<font>*</font>:</td>
                            <td><input type="text"   name="boilerWater" value="${industryWaterData.boilerWater}" id="boilerWater"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">锅炉发气量<font>*</font>:</td>
                            <td><input type="text"   name="boilerGasAmount" value="${industryWaterData.boilerGasAmount}" id="boilerGasAmount"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">厂内生活水<font>*</font>:</td>
                            <td><input type="text"   name="factoryLiveWater" value="${industryWaterData.factoryLiveWater}" id="factoryLiveWater"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">重复利用合计<font>*</font>:</td>
                            <td><input type="text"   name="reuseTotalAmount" value="${industryWaterData.reuseTotalAmount}" id="reuseTotalAmount"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">间冷水循环量<font>*</font>:</td>
                            <td><input type="text"   name="interColdWaterLoopAmount" value="${industryWaterData.interColdWaterLoopAmount}" id="interColdWaterLoopAmount"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">工艺水回用量<font>*</font>:</td>
                            <td><input type="text"   name="freshWaterBackAmount" value="${industryWaterData.freshWaterBackAmount}" id="freshWaterBackAmount"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">锅炉回用水量<font>*</font>:</td>
                            <td><input type="text"   name="boilerWaterBackAmount" value="${industryWaterData.boilerWaterBackAmount}" id="boilerWaterBackAmount"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">蒸汽冷凝回用量<font>*</font>:</td>
                            <td><input type="text"   name="steamCondenBackAmount" value="${industryWaterData.steamCondenBackAmount}" id="steamCondenBackAmount"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">生活水重复量<font>*</font>:</td>
                            <td><input type="text"   name="liveWaterRepetAmount" value="${industryWaterData.liveWaterRepetAmount}" id="liveWaterRepetAmount"></input></td>
                        </tr>
                        </thead>
                    </table>
                </form>
            </div>

            <div title="宿舍用水量" id="dormitoryWater_layout" style="padding:2px">
                <form id="dormitoryWater_form" class="hgform">
                    <table fit=true name="dormitoryWaterTab" id="dormitoryWaterTab" singleSelect=false fitColumns=true>
                        <thead>
                        <input type="hidden"   name="companyId" value="${companyId}" id="companyId3"></input>
                        <input type="hidden"   name="dormitoryWaterId" value="${dormitoryWaterData.dormitoryWaterId}" id="dormitoryWaterId"></input>
                        <tr>
                            <td width="130px;">用水人口<font>*</font>:</td>
                            <td><input type="text"   name="peopleAmount" value="${dormitoryWaterData.peopleAmount}" id="peopleAmount"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">新水量<font>*</font>:</td>
                            <td><input type="text"   name="newWaterAmount" value="${dormitoryWaterData.newWaterAmount}" id="newWaterAmount2"></input></td>
                        </tr>
                        <tr>
                            <td width="130px;">临时性新水量<font>*</font>:</td>
                            <td><input type="text"   name="tempNewWaterAmount" value="${dormitoryWaterData.tempNewWaterAmount}" id="tempNewWaterAmount"></input></td>
                        </tr>
                        </thead>
                    </table>
                </form>
            </div>

        </div>
    </div>
    <div data-options="region:'south',border:false" style="text-align: right; padding: 5px 5px 5px; background-color: #e0e0e0;">
        <shiro:hasPermission name="<%= Auths.WA_COMPANY_INFO_EDIT %>">
            <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a>
        </shiro:hasPermission>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" tag="cancel">取消</a>
    </div>

</div>
<script type="text/javascript">
    var companyId = "${companyId}";
    var dormitoryWaterId ="${dormitoryWaterData.dormitoryWaterId}";
    var commFactilitiesId = "${commFacData.commFactilitiesId}";
    var industryWaterId = "${industryWaterData.industryWaterId}";
</script>
<script type="text/javascript" src="${ctx}/static/js/wa/waCompanyInfo_total.js"></script>
</body>

</html>
