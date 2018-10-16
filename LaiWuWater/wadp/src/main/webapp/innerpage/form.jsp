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
  	 <style type="text/css">
	 	form td {width:150px;text-align:right;}
	 
	 </style>
    
  
    <div class="easyui-panel" title="用户信息填写">
        <div>
        <form id="ff" method="post">
            <table>
                <tr>
                    <td>姓名:</td>
                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>
					<td>性别:</td>
					<td><select class="easyui-combobox" style="width:140px;"><option>男</option><option>女</option></select></td>
                </tr>
				 <tr>
                    <td>手机号码:</td>
                    <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true"></input></td>
					<td>生日:</td>
					<td><input class="easyui-datebox" required></td>
                </tr>
				<tr>
                    <td>qq:</td>
                    <td><input type="text" class="easyui-numberbox"></input></td>
					<td>出生地:</td>
					<td><select class="easyui-combobox" style="width:140px;">
						<option>上海</option>
						<option>北京</option>
						<option>广州</option>
						<option>杭州</option>
						<option>无锡</option>
						<option>苏州</option>
						<option>烟台</option>
						<option>石家庄</option>
						<option>郑州</option>
						<option>重庆</option>
						<option>西安</option>
						<option>大连</option>
						<option>沈阳</option>
						<option>贵州</option>
						</select>
					</td>
                </tr>
				<tr>
                    <td>爱好:</td>
                    <td><input/></td>
					<td>起床时间:</td>
					<td><input class="easyui-timespinner"></td>
                </tr>
				<tr>
					<tr>
                    <td>email:</td>
                    <td colspan="3"><input  data-options="validType:'email'" style="width:500px;"></td>

                </tr>
                    <td>微博地址:</td>
                    <td colspan="3"><input class="easyui-validatebox" data-options="required:true,validType:'url'" style="width:500px;"></td>

                </tr>
				<tr>
                    <td>签名档:</td>
                    <td colspan="3"><textarea name="message" style="height:60px;width:500px;" ></textarea></td>

                </tr>
               
            </table>
        </form>
        </div>
		<p></p>
		<p></p>
		<p></p>
		<p></p>
		<p></p>
		<p></p>
		<p></p>
        <div style="text-align:center;padding:5px;">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
        </div>
    </div>
    <script>
        function submitForm(){
            $('#ff').form('submit');
        }
        function clearForm(){
            $('#ff').form('clear');
        }
    </script>
  </body>

</html>
