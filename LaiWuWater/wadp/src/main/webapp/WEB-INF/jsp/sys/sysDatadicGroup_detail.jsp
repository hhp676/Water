<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>字典组详细页面</title>	
  </head>
  <body>
    <div class="easyui-layout" data-options="fit:true" id="sysDatadicGroupDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;">
			<form id="sysDatadicGroup_form" class="hgform">
				<input type="hidden" name="groupId" value="${groupObj.groupId}"/>
				<table class="hgtable">
					<tr>
						<td width="150px;">字典组code<font>*</font>:</td>
						<td>
							<input class="easyui-validatebox" type="text" name="groupCode" value="${groupObj.groupCode}"  ></input>
						</td>
					</tr>
					<tr>
						<td>字典组名称:</td>
						<td>
							<input class="easyui-validatebox" type="text" name="groupName"  value="${groupObj.groupName}" ></input>
						</td>
					</tr>
					<tr>
						<td>描述:</td><td>
							<textarea name="groupDesc" class="easyui-validatebox" style="height:30px;width:300px;" >${groupObj.groupDesc}</textarea>
						</td>
					</tr>
					<tr>
						<td>排序id:</td>
						<td>
							<input class="easyui-validatebox" type="text" name="orderId" value="${groupObj.orderId}"  data-options=""></input>
						</td>
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
		var groupId = "${groupId}";	
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysDatadicGroup_detail.js"></script>
  </body>

</html>
