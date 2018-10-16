<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>详细页面</title>
    <style type="text/css">
        select.selectType{width: 170px;}
        input{width: 170px;}
    </style>
</head>
<body>
<div class="easyui-layout" data-options="fit:true" id="waCompanyInfo_layout">
    <div data-options="region:'center'" style="padding: 10 10 10 20px;">
        <form id="waCompanyInfo_form"  class="hgform">
            <input type="hidden" name="companyId" value="${waCompanyInfo.companyId}" id="companyId"></input>
            <table class="hgtable" id="companyDetailTab">
                <tr>
                    <td width="130px;">节水代码<font>*</font>:</td>
                    <td><input type="text" style="width: 170px;"  name="companyCode" value="${waCompanyInfo.companyCode}" id="companyCode"></input></td>
                </tr>

                <tr>
                    <td>单位名称<font>*</font>:</td>
                    <td><input type="text" style="width: 170px;" name="companyName" id="companyName" value="${waCompanyInfo.companyName}"></input></td>
                </tr>
                <tr>
                    <td>联系电话:</td>
                    <td><input type="text" style="width: 170px;" name="contactNum" id="contactNum" value="${waCompanyInfo.contactNum}"></input></td>
                </tr>
                <tr>
                    <td>联系人:</td>
                    <td><input type="text" style="width: 170px;" name="contactMan" id="contactMan" value="${waCompanyInfo.contactMan}" ></input></td>
                </tr>
                <tr>
                    <td>所属部门:</td>
                    <td><input type="text" style="width: 170px;" name="department" id="department" value="${waCompanyInfo.department}"></input></td>
                </tr>
                <tr>
                    <td>用户类别:</td>
                    <td>
                        <select name="userType" class="selectType" style="width: 170px;">
                            <c:forEach var="userTypeItem" items="${userTypeMap}">
                                <option value="${userTypeItem.value}" <c:if test="${waCompanyInfo.userType==userTypeItem.value}">selected</c:if>>${userTypeItem.key}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>邮箱:</td>
                    <td><input type="text" style="width: 170px;"  name="email" id="email" value="${waCompanyInfo.email}"></input></td>
                </tr>
                <tr>
                    <td>手机号码:</td>
                    <td><input type="text" style="width: 170px;" name="telphone" id="telphone" value="${waCompanyInfo.telphone}"></input></td>
                </tr>
                <tr>
                    <td>行政区域:</td>
                    <td><input type="text" style="width: 170px;" name="cityArea" id="cityArea" value="${waCompanyInfo.cityArea}"></input></td>
                </tr>
                <tr>
                    <td>邮编:</td>
                    <td><input type="text" style="width: 170px;" name="postcode" id="postcode" value="${waCompanyInfo.postcode}"></input></td>
                </tr>
                <tr>
                    <td>单位人数:</td>
                    <td><input type="text" style="width: 170px;" name="peopleCount" id="peopleCount" value="${waCompanyInfo.peopleCount}" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();" ></input></td>
                </tr>
                <tr>
                    <td>营业面积:</td>
                    <td><input type="text" style="width: 170px;" name="acreage" id="acreage" value="${waCompanyInfo.acreage}"></input></td>
                </tr>
                <tr>
                    <td>用水性质:</td>
                    <%--<td><input type="text"   name="waterType" id="waterType" value="${waCompanyInfo.waterType}"></input></td>--%>
                    <td>
                        <select name="waterType" style="width: 170px;">
                            <c:forEach var="waterTypeItem" items="${waterTypeMap}">
                                <option value="${waterTypeItem.value}" <c:if test="${waCompanyInfo.waterType==waterTypeItem.value}">selected</c:if>>${waterTypeItem.key}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>是否是重点用户:</td>
                    <td>
                        <select name="isImport" style="width: 170px;">
                            <c:forEach var="isImportItem" items="${isImportMap}">
                                <option value="${isImportItem.value}" <c:if test="${waCompanyInfo.isImport==isImportItem.value}">selected</c:if>>${isImportItem.key}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>描述:</td>
                    <td><input type="text" name="descr" id="descr" value = "${waCompanyInfo.descr}"></input></td>
                </tr>

                <c:forEach var="uploadFileListItem" items="${uploadFileList}">
                    <%--<option value="${isImportItem.value}" <c:if test="${waCompanyInfo.isImport==isImportItem.value}">selected</c:if>>${isImportItem.key}</option>--%>
                    <tr id="file${uploadFileListItem.fileid}">
                        <td> 附件：</td>
                        <td> <a href='#' onclick=downFile("${uploadFileListItem.fileName}")> ${uploadFileListItem.fileName} </a></td>
                        <td><a href="#" onclick=delFile("${uploadFileListItem.fileid}")>删除</a></td>
                    </tr>
                </c:forEach>
            </table>
        </form>

        <div >
            <table id="uploadFileTab">
                <tr>
                    <td>添加附件: <input type="file" name="fileImg" id="uploadFile"/></td>
                    <td><a class="easyui-linkbutton" tag="uploadFile">保存</a></td>
                </tr>
            </table>



        </div>

    </div>
    <div data-options="region:'south',border:false" style="text-align: right; padding: 5px 5px 5px; background-color: #e0e0e0;">
    <shiro:hasPermission name="<%= Auths.WA_COMPANY_INFO_IMPORT %>">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a>
    </shiro:hasPermission>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" tag="cancel">取消</a>
    </div>

</div>
<script type="text/javascript">
    var companyId = "${companyId}";
</script>
<script type="text/javascript" src="${ctx}/static/js/wa/waCompanyInfo_detail.js"></script>
</body>

</html>
