<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ajax</title>	
  </head>
  <body>
      <table title="数据字典项列表"  striped=true rownumbers=true fitColumns=true  toolbar='#datadic_toolbar' class="easyui-datagrid"  
        data-options="
        		url: '<%=path%>/innerpage/datadic.json',
                singleSelect:true,
                collapsible:true,
                rownumbers:true,
                fitColumns:true,
                view:groupview,
                groupField:'groupName',
                groupFormatter:function(value,rows){
                    return '<b>'+value + ' - ' + rows.length + ' 条</b>';
                }
            "
        >
	      <thead>
		       <tr>
                <th data-options="field:'itemName',width:80">字典项名称</th>
                <th data-options="field:'itemCode',width:100">字典项code</th>
                <th data-options="field:'itemValue',width:80">字典项值</th>
                <th data-options="field:'itemDesc',width:100">描述</th>
                <th data-options="field:'orderId',width:60">排序id</th>
                <th data-options="field:'groupCode',width:80">所属字典组code</th>
                <th data-options="field:'isFinal',width:60">不可修改</th>
            </tr>
		  </thead>
	</table>
  	
    <div id="datadic_toolbar">
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add1" plain="true" tag="add">添加</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit1" plain="true" tag="edit">修改</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-del1" plain="true" tag="del">删除</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-app" plain="true" tag="datadicGroup">字典组配置</a>
       
    </div>
    

	<script type="text/javascript">		
	
		$("#datadic_toolbar [tag='add']").click(function(){
			var iconCls = $(this).attr("iconCls");
			var datadicDetailWin = new HgWin({id:"datadicDetailWin",title:"添加字典项",width:700,height:380,iconCls:iconCls,url:"/innerpage/datadicDetail.jsp"});
		});
		
		$("#datadic_toolbar [tag='datadicGroup']").click(function(){
			var iconCls = $(this).attr("iconCls");
			var datadicGroupWin = new HgWin({id:"datadicGroupWin",title:"字典组配置",width:700,height:380,iconCls:iconCls,url:"/innerpage/datadicGroup.jsp"});
		});
		
		
	</script>
  </body>

</html>
