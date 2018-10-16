<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统公告详细页面</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="sysNoticeDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;">
			<form id="sysNotice_form" class="hgform">
				<input type="hidden" name="notifyId" value="${noticeId}"/>
				<table   style="width:550px;margin-left:10px;margin-top:5px;">
					<tr>
						<td width="100px;">标题<font style="color:red;font-weight:bold;">*</font>:</td>
						<td colspan="3" width="175px;"><input type="text" name="title"   value="${sysNotice.title}" style="width:458px;box-sizing:border-box;"></input></td>
					</tr>
					<tr>
						<td width="100px;">排序Id:</td>
						<td width="175px;"><input type="text" name="sortId" value="${sysNotice.sortId}" style="width:175px;box-sizing:border-box;"></input></td>
					    <td width="100px;">是否自动发布:</td>
						<td width="175px;">
							<select name="autoPub" style="width:175px;height:21px;box-sizing:border-box;">
								<c:forEach var="logicItem" items="${logicMap}">
									<option value="${logicItem.value}" <c:if test="${authObj.isVisible==logicItem.value}">selected</c:if>>${logicItem.key}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td width="100px;">开始日期<font style="color:red;font-weight:bold;">*</font>:</td>
						<td width="175px;"><input  type="text" name="startTimeStr" value="${sysNotice.startTimeStr}" id="sysNotice_startTimeStr" class="Wdate" style="width: 175px;box-sizing:border-box;"  onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'sysNotice_endTimeStr\',{d:0});}'});"/></input></td>
					    <td width="100px;">结束日期<font style="color:red;font-weight:bold;">*</font>:</td>
						<td width="175px;"><input  type="text" name="endTimeStr" value="${sysNotice.endTimeStr}" id="sysNotice_endTimeStr" class="Wdate" style="width: 175px;box-sizing:border-box;"  onClick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'sysNotice_startTimeStr\',{d:0});}'});"/></input></td>
					</tr>	
					<tr>
					    <td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td width="100px;">内容:</td>
						<td colspan="3">
						<span style="width:461px;height:150px; overflow:hidden;">
						    <textarea name="content" id="noticeContentEd" class="easyui-validatebox" style="overflow-y:auto;padding:0;height:150px;width:461px;box-sizing:border-box;font-family:Arial">${sysNotice.content}</textarea></td>
					    </span>
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
		UM.delEditor('noticeContentEd');
		var noticeContentEdEditor = UM.getEditor("noticeContentEd");
		var noticeId = "${noticeId}";
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysNotice_detail.js"></script>
</body>
</html>
