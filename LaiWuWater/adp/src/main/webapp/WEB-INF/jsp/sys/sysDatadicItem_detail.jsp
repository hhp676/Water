<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>字典项详细页面</title>	
  </head>
  <body>
    <div class="easyui-layout" data-options="fit:true" id="sysDatadicItemDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;">
			<form id="sysDatadicItem_form" class="hgform">
				<input type="hidden" name="itemId" value="${itemObj.itemId}"/>
				
				<table class="hgtable">
					<tr>
						<td width="150px;">所属字典组<font>*</font>:</td>
						<td>
							<select id="cg_groupId" class="easyui-combogrid" style="height:21px;width:150px;box-sizing:border-box;" name="groupId"  editable="false" data-options="
            					panelWidth: 320,idField: 'groupId',textField: 'groupCode',url: '${ctx}/sys/datadicgroup/list?number='+ new Date().getTime(),fitColumns: true,
	                            method: 'get',columns: [[
                                    {field:'groupCode',title:'字典组code',width:80},
                                    {field:'groupName',title:'字典组名称',width:80},
                                    {field:'groupDesc',title:'描述',width:100}]]">
    						</select>
						</td>
					</tr>
					<tr>
						<td>字典项code<font>*</font>:</td>
						<td>
							<input type="text"    style="width:150px;box-sizing:border-box;" name="itemCode"  value="${itemObj.itemCode}" ></input>
						</td>
					</tr>
					<tr>
						<td>字典项名称<font>*</font>:</td>
						<td>
							<input    type="text" style="width:150px;box-sizing:border-box;" name="itemName"  value="${itemObj.itemName}" ></input>
						</td>
					</tr>
					<tr>
						<td>字典项值:</td>
						<td>
							<input    type="text" style="width:150px;box-sizing:border-box;" name="itemValue"  value="${itemObj.itemValue}" ></input>
						</td>
					</tr>
					<tr>
						<td>排序id:</td>
						<td>
							<input  type="text" style="width:150px;box-sizing:border-box;" name="orderId" value="${itemObj.orderId}"  data-options=""></input>
						</td>
					</tr>				
					<tr>
						<td>描述:</td><td>
							<textarea name="itemDesc"    style="height:60px;width:300px;box-sizing:border-box;font-family:Arial" >${itemObj.itemDesc}</textarea>
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
		var groupId = "${itemObj.groupId}";
		var itemId = "${itemId}";
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysDatadicItem_detail.js"></script>
  </body>

</html>
