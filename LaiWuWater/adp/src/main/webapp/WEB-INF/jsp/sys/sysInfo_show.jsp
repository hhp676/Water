<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>系统信息页面</title>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
</head>
<body>
	<div  class="easyui-tabs" fit=true tabPosition="top" id="sysInfo_tab">	
		<div title="在线用户信息" style="padding:2px">
			<table id="sessionInfo_pgrid" class="easyui-datagrid"  rownumbers=true fitColumns=false singleSelect=true striped=true scrollbarSize=0 nowrap=false autoRowHeight=false data-options="columns: mycolumns2"></table>
		</div>	
		<div title="JVM信息" style="padding:2px">
			<table id="jvmInfo_pgrid" class="easyui-propertygrid" striped=true scrollbarSize=0 nowrap=false autoRowHeight=false data-options="columns: mycolumns"></table>
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
		
		var mycolumns2 = [[
		      	    	{field:'name',title:'<b>姓名</b>',width:120,resizable:true},
		      	    	{field:'sessionid',title:'<b>SESSIONID</b>',width:270,resizable:true},
		      	        {field:'host',title:'<b>Host</b>',width:150,resizable:true},
		      	      	{field:'startTimestamp',title:'<b>登录时间</b>',width:130,resizable:true},
		      	    	{field:'lastAccessTime',title:'<b>最后访问</b>',width:130,resizable:true},
		      	    	{field:'onlines',title:'<b>在线时长</b>',width:100,resizable:true},
		      	      	{field:'oper',title:'<b>操作</b>',width:200,resizable:true}
		      	    ]];
		
		showLoadMsg("serverInfo_tab");
		
		
		//-------------------------------在线用户信息-----------------------------------------------------------------
		function _creatSessioninfo(){
			Hg.getJson("/sys/info/sessionInfo",{},function(data){
				var rows = [];
				$('#sessionInfo_pgrid').datagrid('loadData', rows);
				var data = data.data;
				var row = {};
				var num = 1;
				var lists = data["list"];
				for (i=0; i<lists.length; i++) {
					row.sessionid = lists[i].id;
					row.host = lists[i].host;
					row.startTimestamp = new Date(lists[i].startTimestamp).Format("yyyy-MM-dd hh:mm:ss");
					row.lastAccessTime = new Date(lists[i].lastAccessTime).Format("yyyy-MM-dd hh:mm:ss");
					row.onlines = _formatSeconds((lists[i].lastAccessTime-lists[i].startTimestamp)/1000);
					
					row.num = num++;
					var attributes = lists[i].attributes;
					if(attributes && attributes["org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY"]){				
						row.name = attributes["org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY"]["primaryPrincipal"]["name"];
						//row.oper	= "<a href=\"javascript:_forceLogoutUser('" + row.sessionid + "','" + row.num + "');\">踢出 </a>";
						$('#sessionInfo_pgrid').datagrid('appendRow', row);
					}
				}
				Hg.showGridCellTip("sessionInfo_pgrid", ["name","sessionid","host"]);
			});
		}
		
		/**
		* 将秒数换成时分秒格式
		* 整理：www.jbxue.com
		*/
		  
		function _formatSeconds(value) {
		    var theTime = parseInt(value);// 秒
		    var theTime1 = 0;// 分
		    var theTime2 = 0;// 小时
		    if(theTime > 60) {
		        theTime1 = parseInt(theTime/60);
		        theTime = parseInt(theTime%60);
		            if(theTime1 > 60) {
		            theTime2 = parseInt(theTime1/60);
		            theTime1 = parseInt(theTime1%60);
		            }
		    }
		        var result = ""+parseInt(theTime)+"秒";
		        if(theTime1 > 0) {
		        result = ""+parseInt(theTime1)+"分"+result;
		        }
		        if(theTime2 > 0) {
		        result = ""+parseInt(theTime2)+"小时"+result;
		        }
		    return result;
		}
		
		Date.prototype.Format = function (fmt) {
		    var o = {
		        "M+": this.getMonth() + 1, //月份 
		        "d+": this.getDate(), //日 
		        "h+": this.getHours(), //小时 
		        "m+": this.getMinutes(), //分 
		        "s+": this.getSeconds(), //秒 
		        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
		        "S": this.getMilliseconds() //毫秒 
		    };
		    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		    for (var k in o)
		    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		    return fmt;
		}
		
		function _forceLogoutUser(sessionid, num){
			Hg.getJson("/sys/info/forceLogout/"+sessionid, {}, function(data) {
				if (data.success) {
					$('#sessionInfo_pgrid').datagrid("deleteRow", num);
					_creatSessioninfo();
					Oa.showMsgNotAuth('操作成功！');
				}else{
					Oa.showMsgNotAuth('操作失败或已不存在该用户！');
				}
				
			});
		}
		_creatSessioninfo();
		
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
			Hg.showGridCellTip("jvmInfo_pgrid", ["name","value"]);
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
			Hg.showGridCellTip("sysInfo_pgrid", ["name","value"]);
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
								subGridColumnsMulti[0].push({field:subProp,title:subProp,width:220});
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
							subGridColumns = [[{field:"name",title:'键',width:220},{field:'value',title:'值',width:220}]];
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
				///$('#serverInfo_grid').datagrid('loadData', groupRows);
				
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
							fitColumns:false,
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
				
				/* for (var i=0;i<groupRows.length;i++) {
					$('#serverInfo_grid').datagrid('expandRow',i);
				} */
				//$('table.subGrid').datagrid('resize');	
				for (var i=0;i<groupRows.length;i++) {
					$('#serverInfo_grid').datagrid('expandRow',i);
				}
				$('#serverInfo_grid').datagrid('loadData', groupRows);
				Hg.showGridCellTip("serverInfo_grid", ["name","value"]);
			});
		}
		
		$('#sysInfo_tab').tabs({
		    border:false,
		    onSelect:function(title){
		        if (title == "服务器信息") {
		        	//_creatServerinfo();
		        	
		        }
		    }
		});
		
		_creatServerinfo();
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
			Hg.showGridCellTip("propsInfo_pgrid", ["name","value"]);
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
