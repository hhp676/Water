<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统信息页面</title>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
</head>
<body>
	<div  class="easyui-tabs" fit=true tabPosition="top" id="sysInfo_tab">		
		<div title="JVM信息" style="padding:2px">
			<table id="jvmInfo_pgrid" class="easyui-propertygrid" showGroup=true striped=true scrollbarSize=0 nowrap=false autoRowHeight=false data-options="columns: mycolumns"></table>
		</div>
		<div title="系统信息" style="padding:2px">
			<table id="sysInfo_pgrid" class="easyui-propertygrid" striped=true scrollbarSize=0 nowrap=false autoRowHeight=false data-options="columns: mycolumns"></table>
		</div>
		<div title="服务器信息" style="padding:2px;" id="serverInfo_tab">
			<table id="serverInfo_grid"  class="easyui-datagrid" showHeader=false fitColumns=true striped=true scrollbarSize=0 nowrap=false  data-options="view:detailview,detailFormatter:_serverInfoGridDefaultFmt">
				 <thead>
        		 	<tr>
            			<th data-options="field:'group',width:400"></th>
        			</tr>
    			</thead>
			</table>
		</div>
		<div title="数据库监控信息" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="${ctx}/druid/index.html" style="width:100%;height:100%;"></iframe>
        </div>
		<div title="系统设置信息" style="padding:2px">
			<table id="propsInfo_pgrid" class="easyui-propertygrid" striped=true scrollbarSize=0 nowrap=false data-options="columns: mycolumns"></table>
		</div>
	</div>

	<script type="text/javascript">
		
		var mycolumns = [[
	    	{field:'name',title:'<b>键</b>',width:80,resizable:true},
	        {field:'value',title:'<b>值</b>',width:400,resizable:true}
	    ]];
		
		
		showLoadMsg("serverInfo_tab");
		
		
		
		
		
		
		
		//-------------------------------JVM信息-----------------------------------------------------------------
		Hg.getJson("/sys/info/jvmInfo",{},function(data){
			var rows = [];
			var data = data.data;
			for (var prop in data) {				
				if (typeof(data[prop]) == "object") {
					var obj = data[prop];
					if (obj instanceof Array) {
						
					} else {
						for (var subProp in obj) {
							_makePgRowAndPush(rows,subProp,obj[subProp],prop);							
						}
					}
				} else {
					_makePgRowAndPush(rows,prop,data[prop],"main");	
				}				
			}
			$('#jvmInfo_pgrid').propertygrid('loadData', rows);
			
		});

		//-------------------------------系统信息---------------------------------------------------------------------
		Hg.getJson("/sys/info/systemInfo",{},function(data){
			var rows = [];
			$('#sysInfo_pgrid').propertygrid('loadData', rows);
			var data = data.data;
			var row = {};
			for (var prop in data) {
				row.name = prop;
				row.value = data[prop];
				$('#sysInfo_pgrid').propertygrid('appendRow', row);
			}
			
		});
		
		
		
		
		
		//---------------------------------服务器信息---------------------------------------------------------------------
		function _creatServerinfo(){
			Hg.getJson("/sys/info/serverInfo",{},function(data){
				rmLoadMsg("serverInfo_tab");
				var groupRows = [];
				var subGridColumns = [[]];
				var subGridColumnsMultiObj = {};
				var rowData = {};
				var data = data.data;
				for (var prop in data) {
					if (typeof(data[prop]) == "object") {
						var groupRow = {group:"<b>"+prop+"</b>",groupTag:prop};					
						var obj = data[prop];
						if (obj instanceof Array) {
							groupRow.isMulti = true;
							var subGridColumnsMulti = [[]];
							var subData = obj[0];
							for (var subProp in subData) {
								subGridColumnsMulti[0].push({field:subProp,title:subProp,width:200});
							}
							subGridColumnsMultiObj[prop] = subGridColumnsMulti;
							//------------------------行数据组织-----------------------------------
							var ddAry = [];
							for (var i=0;i<obj.length;i++) {
								ddAry.push(obj[i]);
							}
							rowData[prop] = ddAry;
							//alert(ddAry.join(","));
						} else {
							groupRow.isMulti = false;
							subGridColumns = [[{field:"name",title:'键',width:200},{field:'value',title:'值',width:200}]];
							//------------------------行数据组织-----------------------------------
							var ddAry = [];
							for (var subProp in obj) {
								ddAry.push({name:subProp,value:obj[subProp]});
							}
							rowData[prop] = ddAry;
							
						}
						groupRows.push(groupRow);
					}
					
				}
				$('#serverInfo_grid').datagrid('loadData', groupRows);
				
				$("#serverInfo_grid").datagrid({
					onExpandRow:function(index,row){
						var subGrid = $(this).datagrid('getRowDetail',index).find('table.subGrid');
						var isMulti = $(this).datagrid('getRows')[index].isMulti;
						var group = $(this).datagrid('getRows')[index].groupTag;
						var columns = [[]];
						if (isMulti) columns = subGridColumnsMultiObj[group];
						else columns = subGridColumns;
						subGrid.datagrid({
							height:"auto",
							fitColumns:true,
							columns:columns,
							onResize:function(){
	                            $('#serverInfo_grid').datagrid('fixDetailRowHeight',index);
	                        },
	                        onLoadSuccess:function(){
	                            setTimeout(function(){
	                                $('#serverInfo_grid').datagrid('fixDetailRowHeight',index);
	                            },0);
	                        }
						});
						subGrid.datagrid('loadData', rowData[group]);
						$('#serverInfo_grid').datagrid('fixDetailRowHeight',index);
					}
				 	
				});
				
				for (var i=0;i<groupRows.length;i++) {
					$('#serverInfo_grid').datagrid('expandRow',i);
				}
				//$('table.subGrid').datagrid('resize');

				
				
				
			});
		}
		
		$('#sysInfo_tab').tabs({
		    border:false,
		    onSelect:function(title){
		        if (title == "服务器信息") {
		        	_creatServerinfo();
		        	
		        }
		    }
		});
		
		
		//------------------------------------系统设置-----------------------------------------------------------------
		Hg.getJson("/sys/info/propsInfo",{},function(data){
			var rows = [];
			$('#propsInfo_pgrid').propertygrid('loadData', rows);
			var data = data.data;
			var row = {};
			for (var prop in data) {
				row.name = "<b>"+prop+"</b>";
				row.value = data[prop];
				$('#propsInfo_pgrid').propertygrid('appendRow', row);
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		
		
		/*-----------------------------------------------------------------------------
		* 私有工具方法
		------------------------------------------------------------------------------*/
		function _makePgRowAndPush(rows,name,value,group) {
			var row = {};
			row.name = name;
			row.value = value;
			if (group) row.group = group;			
			rows.push(row);
		}
		
		
		function _serverInfoGridDefaultFmt(rowIndex, rowData){
			return '<div style="padding:2px"><table class="subGrid"></table></div>';
		}

	</script>
</body>

</html>
