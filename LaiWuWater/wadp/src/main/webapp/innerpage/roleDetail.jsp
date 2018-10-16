<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ajax</title>
</head>
<body>

	<div class="easyui-layout" data-options="fit:true" id="roleDetail_layout">
		<div data-options="region:'east',split:true" title="设置权限" style="width:270px">
		    <table title="" fit=true class="easyui-treegrid"  singleSelect=false fitColumns=true data-options="url: '${ctx}/innerpage/auth.json',method: 'get',idField: 'id',treeField: 'name'">
        		<thead>
            			<tr>
            				<th data-options="field:'ck',checkbox:true"></th>            				
                			<th data-options="field:'name'" width=230>权限名称</th>              			
            			</tr>
        			</thead>
    			</table>
		</div>
		<div data-options="region:'center'" title="基本信息" style="padding: 5px;">
			<form action="">
				<table class="hgtable">
							<tr><td width="200px;">角色名称:</td><td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td></tr>
							<tr><td>角色code:</td><td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td></tr>							
							<tr><td>不可修改:</td><td><select name="" style="width:140px;"><option>是</option><option>否</option></td></tr>
							<tr><td>描述:</td><td><textarea name="message" style="height:30px;width:300px;"></textarea></td></tr>
							<tr><td></td><td></td></tr>
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" style="text-align: right; padding: 5px 5px 5px; background-color: #e0e0e0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" tag="cancel">取消</a>
		</div>
	</div>

	<script type="text/javascript">
		$("#roleDetail_layout [tag='ok']").click(function(){
			alert("ok1");
		});
		$("#roleDetail_layout [tag='cancel']").click(function(){
			$("#roleDetail_layout").parent().window("close");
		});
	</script>
</body>

</html>
