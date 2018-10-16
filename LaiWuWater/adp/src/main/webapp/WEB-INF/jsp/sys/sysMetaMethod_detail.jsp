<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>元方法详细页面</title>	
  </head>
  <body>
    <div class="easyui-layout" data-options="fit:true" id="sysMetaMethodDetail_layout">
		<div data-options="region:'center'" style="padding: 10 10 10 20px;">
			<form id="sysMetaMethod_form" class="hgform">
				<input type="hidden" name="metaMethodId" value="${id}"/>
				
				<table class="hgtable">
					<tr>
						<td width="150px;">类名<font>*</font>:</td>
						<td>
							<input type="text"    
								style="width:400px;box-sizing:border-box;" 
								name="className"  value="${sysMetaMethod.className}" />
							例:com.hginfo.xx.xxController
						</td>
					</tr>
					<tr>
						<td>方法名<font>*</font>:</td>
						<td>
							<input type="text"    
								style="width:200px;box-sizing:border-box;" 
								name="methodName"  value="${sysMetaMethod.methodName}" />
							例:xxMethod
						</td>
					</tr>
					<tr>
						<td>参数名<font>*</font>:</td>
						<td>
							<input type="text"    
								style="width:500px;box-sizing:border-box;" 
								name="argsName"  value="${sysMetaMethod.argsName}" />
							例:int,java.lang.String
						</td>
					</tr>
					<tr>
						<td>方法编码<font>*</font>:</td>
						<td>
							<input type="text"    
								style="width:200px;box-sizing:border-box;" 
								name="methodCode"  value="${sysMetaMethod.methodCode}" />
						</td>
					</tr>
					<tr>
						<td>方法中文名称<font>*</font>:</td>
						<td>
							<input type="text"    
								style="width:200px;box-sizing:border-box;" 
								name="methodZhName"  value="${sysMetaMethod.methodZhName}" />
						</td>
					</tr>
					<tr>
						<td>方法英文名称<font>*</font>:</td>
						<td>
							<input type="text"    
								style="width:200px;box-sizing:border-box;" 
								name="methodEngName"  value="${sysMetaMethod.methodEngName}" />
						</td>
					</tr>
					<tr>
						<td>指定参数中需存储日志的类:</td>
						<td>
							<input type="text"    
								style="width:400px;box-sizing:border-box;" 
								name="logRemarkClass"  value="${sysMetaMethod.logRemarkClass}" />
							例:com.hginfo.xx.xxEntity
						</td>
					</tr>
					<tr>
						<td>日志级别<font>*</font>:</td>
						<td>
							<select class="easyui-combobox" name="logLevel" style="width:160px;height:21px;box-sizing: border-box;" editable="false" data-options="panelHeight:'auto'">
								<c:forEach var="logLevelItem" items="${logLevelMap}">
									<option value="${logLevelItem.value}" <c:if test="${sysMetaMethod.logLevel==logLevelItem.value}">selected</c:if>>${logLevelItem.key}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>日志类型<font>*</font>:</td>
						<td>
							<select class="easyui-combobox" name="logType" style="width:160px;height:21px;box-sizing: border-box;" editable="false" data-options="panelHeight:'auto'">
								<c:forEach var="logTypeItem" items="${logTypeMap}">
									<option value="${logTypeItem.value}" <c:if test="${sysMetaMethod.logType==logTypeItem.value}">selected</c:if>>${logTypeItem.key}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>描述:</td><td>
							<textarea name="descr" 
								style="height:60px;width:400px;box-sizing:border-box;font-family:Arial" >${sysMetaMethod.descr}</textarea>
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
		var metaMethodId = "${id}";
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysMetaMethod_detail.js"></script>
  </body>

</html>
