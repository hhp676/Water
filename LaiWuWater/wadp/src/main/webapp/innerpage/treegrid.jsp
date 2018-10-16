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
  	 <table title="Folder Browser" class="easyui-treegrid" 
            data-options="
                url: '<%=path%>/innerpage/treegrid_data1.json',
                method: 'get',
                rownumbers: true,
                idField: 'id',
                treeField: 'name'
            ">
        <thead>
            <tr>
                <th data-options="field:'name'" width="220">Name</th>
                <th data-options="field:'size'" width="100" align="right">Size</th>
                <th data-options="field:'date'" width="500">Modified Date</th>
            </tr>
        </thead>
    </table>
  </body>

</html>
