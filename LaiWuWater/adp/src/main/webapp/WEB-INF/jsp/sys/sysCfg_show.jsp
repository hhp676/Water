<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统配置页面</title>
</head>
<body style="background-color: #e0e0e0;"  >
	<table id="sysCfg_grid" title="系统配置信息" rownumbers=false singleSelect=true fitColumns=true showHeader=false class="easyui-datagrid">
	      <thead>
		      <tr>
<!-- 			      <th data-options="field:'configKey',width:390,align:'right',styler:_sysCfgCellStyler"></th> -->
<!-- 				  <th data-options="field:'configDesc',width:200,align:'right'"></th> -->
<!-- 			      <th data-options="field:'configValue',width:378,editor:'text',required:true"></th> -->
				  <th data-options="field:'configDesc',width:485,align:'right',styler:_sysCfgCellStyler"></th>
			      <th data-options="field:'configValue',width:200,align:'left',editor:'text',required:true"></th>
			      <th data-options="field:'configKey',width:474,align:'left',styler:_sysCfgCellStyler"></th>


			  </tr>
		  </thead>
	</table>
	<div id="sysCfg_btn"  style="padding-bottom:10px;text-align: center; padding-top:10px; background-color: #e0e0e0;">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="save">保存</a>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-reset'" tag="reset" style="margin-left:100px;">重置</a>
		</div>
<!-- 	<div id="sysCfg_btn" style="text-align: center; padding-bottom:10px;padding-top:10px; background-color: #e0e0e0;"> -->

<!-- 	</div> -->
	

	<script type="text/javascript">
		

		
		
		
		
		
		
		
		
		//-----------------------------------获取列表信息-------------------------------------------------------------
		function _loadSysCfgData() {
			Hg.getJson("/sys/cfg/list",{},function(data){
				rows = data.data;			
				$('#sysCfg_grid').datagrid('loadData', rows);
				for (var i=0;i<rows.length;i++) {
					if (rows[i].isFinal == 0) {
						$('#sysCfg_grid').datagrid("beginEdit",i);
					}
					
				}
				
			});
		}
		_loadSysCfgData();

		
		
		$("#sysCfg_btn [tag='reset']").click(function(){
			$.messager.confirm("确认", "确认重置系统配置信息?", function(r){
				if (r){
					Hg.getJson("/sys/cfg/reset",{},function(data){
						if (data.success) {
							$.messager.ok("重置系统配置信息成功！",function(){
								_loadSysCfgData();
							});
							
						}
					});
				}
			});
			
		});
		
		
		$("#sysCfg_btn [tag='save']").click(function(){
			if (rows) for (var i=0;i<rows.length;i++)  $('#sysCfg_grid').datagrid('endEdit',i);		
			var changedRows = $('#sysCfg_grid').datagrid('getChanges');
			if (rows) for (var i=0;i<rows.length;i++)  $('#sysCfg_grid').datagrid('beginEdit',i);
			var newAry = [];
			for (var i=0;i<changedRows.length;i++) {				
				newAry.push({configKey:changedRows[i].configKey,configValue:changedRows[i].configValue});
			}
			
			 Hg.getJson("/sys/cfg/batchUpdate",{jsonListStr:$.toJSON(newAry)},function(data){
				 $.messager.ok("设置成功！");
				 _loadSysCfgData();
			 });
		});
	
		
		
		
		
		
		
		
		
		
		
		
		/*-----------------------------------------------------------------------------
		* 私有工具方法
		------------------------------------------------------------------------------*/		
		
		function _sysCfgCellStyler(value,row,index) {
			 return "font-weight:bold;"
		}

	</script>
</body>

</html>
