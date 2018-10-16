<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>个人中心</title>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true" id="personalCenter_layout">
		 <div id="personalCenterTab" class="easyui-tabs" fit=true tabPosition="top" data-options="tabWidth:150,tabHeight:65" >
			<%--<div title="<span class='tt-inner'><img src='${ctx}/static/images/icons/look.png'/><br><br>查看个人资料</span>" style="padding:10px" >
				<h3 style="border-bottom:2px dashed #aaa;padding-bottom: 12px;">个人资料</h3>
            	<form class="changeform" id="userInfoForm">
            		<table>
            			<tr>
            				<td width="120px" align="center">用户名</td>
            				<td width="250px;"><input type="text" name=""  style="width: 180px;" disabled="disabled" value="${user.userName}" /></td>
            				<td width="100px" align="center">英文名</td>
            				<td><input type="text" name="" style="width: 180px;" disabled="disabled" value="${user.engName}"/></td>
            			</tr>
            			<tr>
            				<td align="center">email</td>
            				<td><input type="text" name="" style="width: 180px;" disabled="disabled" value="${user.email}"/></td>
            				<td align="center">手机号</td>
            				<td><input type="text" name="" style="width: 180px;" disabled="disabled" value="${user.mobile}"/></td>
            			</tr>
            			<tr>
            				<td align="center">性别</td>
            				<td><input type="radio"  value="1" name="sex" disabled="disabled">男</input><input type="radio"  value="0" name="sex" disabled="disabled">女</input></td>
            				<td align="center">生日</td>
            				<td><input type="text" name="" style="width: 180px;" disabled="disabled" value="${user.birthday}"/></td>
            			</tr>
            			<tr style="display: none;">
            				<td align="center">是否被锁定</td>
            				<td><input type="radio" checked="checked">否</input></td>
            				<td align="center">创建日期</td>
            				<td><input type="text" name="" style="width: 180px;" disabled="disabled" value="${user.crtTimeStr}"/></td>
            			</tr>
            		</table>
            	</form>
            	<h3 style="padding-bottom: 5px;padding-top: 10px;">权限信息</h3>
       			<div style="padding-left: 30px;">
       				<table id="centerpage_authTable">
       					<tr>
       						<td width="100px;" class="td1">应用权限:</td>
       						<td>${appAuth}&nbsp;</td>
       					</tr>
       					<tr>
       						<td class="td1">模块权限:</td>
       						<td>${moduleAuth}</td>
       					</tr>
       					<tr>
       						<td class="td1">操作权限:</td>
       						<td>${operAuth}</td>
       					</tr>
       					<tr>
       						<td class="td1">角色信息:</td>
       						<td>${userRoles}</td>
       					</tr>
       					<tr>
       						<td class="td1">用户组信息:</td>
       						<td>${userUgroups}&nbsp;</td>
       					</tr>
       				</table>
       			</div>
            	
            </div>--%>
        	<div title="<span class='tt-inner'><img src='${ctx}/static/images/icons/editbig.png'/><br><br>修改个人资料</span>" style="padding:20px" >
            	<form id="changeUserInfoForm" class="hgform changeform">
            		<table class="hgtable">
            			<tr>
            				<td width="200px" align="center">用户名<font>*</font></td>
            				<td width="400px;"><input type="text" name="userName"  style="width: 180px;" value="${user.userName}"/></td>
            				
            			</tr>
            			<tr>
            				<td  align="center">英文名</td>
            				<td><input type="text" name="engName" style="width: 180px;" value="${user.engName}"/></td>
            			</tr>
            			<tr>
						<td align="center">性别:</td>
						<td>
							<select name="sex">
								<c:forEach var="sexItem" items="${sexMap}">
									<option value="${sexItem.value}" <c:if test="${user.sex==sexItem.value}">selected</c:if>>${sexItem.key}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
            			<tr>
						<td align="center">生日:</td>
						<td><input  type="text" name="birthday" value="${user.birthday}" class="Wdate" style="width: 180px;"  onClick="WdatePicker({readOnly:true,maxDate:new Date()});"/></input></td>
					</tr>
            			<tr>
            				<td align="center">email</td>
            				<td><input type="text" name="email" style="width: 180px;"  value="${user.email}"/></td>
            				
            			</tr>
            			<tr>
            				<td align="center">手机号</td>
            				<td><input type="text" name="mobile" style="width: 180px;" value="${user.mobile}"/></td>
            			</tr>
            			<tr>
							<td align="center">联系电话:</td>
							<td><input  type="text" name="telephone" value="${user.telephone}" style="width: 180px;"></input></td>
						</tr>
						<tr>
						<td align="center">地址:</td>
						<td><textarea   name="address" style="height:50px;width:300px;font-family:Arial">${user.address}</textarea>
						</td>
					</tr>	
            			<tr>
            				<td>&nbsp;</td>
            				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a></td>
            				
            			</tr>
            			
            		</table>
            	</form>
        	</div>
        	<div title="<span class='tt-inner'><img src='${ctx}/static/images/icons/key.png'/><br><br>修改密码</span>" style="padding:20px">
            	<form id="changePassForm" class="hgform changeform">
            		<table class="hgtable">
            			<tr>
            				<td width="200px" align="center">旧密码<font>*</font>:</td>
            				<td width="400px;">
            				    <span id="oldPwdSpan">
            				        <input type="password" name="oldPwd" id="changePassForm_oldPwd"  style="width: 180px;"/>  
            				    </span>            				    
            				    &nbsp;
            				    <input type="checkbox" id="showOldPwd" onclick="changeOldPwd()" style="width:15px;height:15px;margin-bottom:5px;vertical-align:text-top;margin-top:0;"/>显示旧密码
            				</td>
            			</tr>
            			<tr>
            				<td  align="center">新密码<font>*</font>:</td>
            				<td>
            				    <span id="changePassForm_newPwdSpan">
            				        <input type="password" name="newPwd" id="changePassForm_newPwd" style="width: 180px;"/>
            				    </span>
            				    &nbsp;
            				    <input type="checkbox" id="changePassForm_showNewPwd" onclick="changeNewPwd()" style="width:15px;height:15px;margin-bottom:5px;vertical-align:text-top;margin-top:0;"/>显示新密码
            				</td>
            			</tr>
            			<tr>
            				<td align="center">确认新密码<font>*</font>:</td>
            				<td>
            				    <span id="changePassForm_confirmPwdSpan">
            				        <input type="password" name="confirmNewPwd" id="changePassForm_rePwd" style="width: 180px;"/>
            				    &nbsp;
            				    </span>
            				    <input type="checkbox" id="changePassForm_showConfirm" onclick="changeConfirm()" style="width:15px;height:15px;margin-bottom:5px;vertical-align:text-top;margin-top:0;"/>显示确认新密码
            				</td>        				
            			</tr>
            			<tr>
            				<td>&nbsp;</td>
            				<td><a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" tag="ok">保存</a></td>
            				
            			</tr>
            			
            		</table>
            	</form>
        	</div>
        	<%--<div title="<span class='tt-inner'><img src='${ctx}/static/images/icons/bell_32.png'/><br><br>我的通知</span>">
            	<table striped=true id="pc_UserNotify_grid" singleSelect=true fitColumns=true fit=true   
					 pagination="true" pageSize="${defaultPageSize}" pageList="${defaultPageList}">
	          		<thead>
		           	<tr>
                        <!-- <th data-options="width:200,formatter:fmtPcNotifyFlag"></th> -->
                      	<th data-options="field:'title',width:400,formatter:fmtPcNotifyTitle">标题</th>
                       	<th data-options="field:'isReadStr',width:50">是否已读</th>
                  	</tr>
		      		</thead>
	       		</table>
        	</div>--%>
        	
		</div>
		
	</div>
	<script type="text/javascript">
		var sex = "${user.sex}";

		//旧密码：明文、密文切换
		function changeOldPwd(){
			var styleStr = "width: 180px;";
			exchangePwd_js("showOldPwd","changePassForm_oldPwd","oldPwdSpan","oldPwd",styleStr);
		}
		//新密码：明文、密文切换
		function changeNewPwd(){	
			var styleStr = "width: 180px;";
			exchangePwd_js("changePassForm_showNewPwd","changePassForm_newPwd","changePassForm_newPwdSpan","changePassForm_newPwd",styleStr);
		}
		//确认新密码：明文、密文切换
		function changeConfirm(){
			var styleStr = "width: 180px;";
			exchangePwd_js("changePassForm_showConfirm","changePassForm_rePwd","changePassForm_confirmPwdSpan","changePassForm_confirmNewPwd",styleStr);		
		}
		function exchangePwd_js(checkboxId,pwdId,spanId,name,styleStr){			
			var showPwd = document.getElementById(checkboxId);//定位复选框	
			var pwd = document.getElementById(pwdId);//定位密码框
			var pwdSpan = document.getElementById(spanId); //定位span
			var pwdValue = pwd.value;//密码框的值
			//console.log(showPwd.checked);
			if(showPwd.checked) {
				pwdSpan.innerHTML = "";
				pwdSpan.innerHTML = '<input type="text" name="'+name+'" id="'+pwdId+'"  style="'+styleStr+'" value="'+pwdValue+'"/>';									    
			}else{
				pwdSpan.innerHTML = "";
				pwdSpan.innerHTML = '<input type="password" name="'+name+'" id="'+pwdId+'"  style="'+styleStr+'" value="'+pwdValue+'"/>';
			}
		}
	</script>
	<script type="text/javascript" src="${ctx}/static/js/page/personalCenter.js"></script>
</body>

</html>
