<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ page
    import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<title>莱芜市节水信息化管理平台</title>
<style>
 ::-ms-clear, ::-ms-reveal{display: none;}
 input:-webkit-autofill {-webkit-box-shadow: 0 0 0px 1000px white inset;}
</style>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<link rel="icon" href="action.ico" type="image/x-icon" />
<link rel="shortcut icon" href="action.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/login.css" />

<link rel="stylesheet" type="text/css" href="${ctx}/static/css/error/dandelion.css"  media="screen" />

<!--[if IE 8]>
    <! -- ie8时,使用jqueryA版本  -->
	<script type="text/javascript" src="${ctx}/static/js/jquery-A.js"></script>
<![endif]-->
<!--[if gte IE 9]>
    <! --ie版本大于等于9时,使用jquery1.12.4版本  -->
	<script type="text/javascript" src="${ctx}/static/js/jquery-1.12.4.js"></script>
<![endif]-->
<!-- easyui -->
<script type="text/javascript" src="${ctx}/static/js/jquery.json-2.4.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/jquery.easyui.js"></script>
	
<!-- jquery extension -->
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/extension/datagrid-groupview.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/extension/datagrid-detailview.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/extension/datagrid-bufferview.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/extension/datagrid-filter.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	
	<!-- validate -->
<script type="text/javascript"
	src="${ctx}/plugins/jquery-validator/jquery.validate.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-validator/jquery.validate.message_cn.js"></script>

<!-- blockUI -->
<script type="text/javascript"
	src="${ctx}/plugins/jquery-blockUI/jquery.blockUI.js"></script>

<script type="text/javascript" src="${ctx}/plugins/jquery-easyui/jquery.easyui.custom.js"></script>
<script type="text/javascript" src="${ctx}/static/js/main.js"></script>
<script type="text/javascript" src="${ctx}/static/js/hg.util.js"></script>
<script type="text/javascript" src="${ctx}/static/js/hg.config.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.cookie.js"></script>	

 
<script type="text/javascript" src="${ctx}/static/js/encode64.js"></script>
<script type="text/javascript" src="${ctx}/static/js/security/RSAUtil.js"></script>

<script type="text/javascript">
$(function(){         
    $('#captchaImage').click(changeCaptcha);
    
    $("#sumbitBtn").click(function() {
    	//此处目的并非防重复提交，而是针对安全扫描中重复提交登录请求的数据情况进行token验证处理。
    	  $("body").block();
        $.ajax({
            type:"GET",
            url:G_CTX_PATH+"/refSubmitToken",
            cache:false,
            dataType:"json",
            success:function(data){
                $("#loginForm").find("[name='submitToken']").val(data.data);
                //$("#username").val(encode64($("#username_text").val()));
                $("#username").val(RSAUtils.encryptedString(rsa_key, $("#username_text").val()));
                $("#password").val(RSAUtils.encryptedString(rsa_key, $("#password_text").val()));                         
                //$("#username_text").attr("disabled","disabled");
                //$("#password_text").attr("disabled","disabled");
                $("#loginForm").submit();
            } ,
            error:function(data){
            	$("body").unblock();
            	var password = document.getElementById("password_text").value;
            	var errorMsg = document.getElementById("password_error");
            	errorMsg.innerText = "后台无响应,请求失败!!";
                return false;
            }  
        });
    	return false;
	});
});
var rsa_modulus = "${rsa_modulus}";
var rsa_exponent = "${rsa_exponent}";
var rsa_key = RSAUtils.getKeyPair(rsa_exponent, '', rsa_modulus);
function changeCaptcha() {  
    $('#captchaImage').hide().attr('src', '${ctx}/captchaImage?' + Math.floor(Math.random()*100) ).fadeIn();  
    event.cancelBubble=true;  
}

</script>
</head>
<body
	style="background: url(${ctx}/static/images/page/login_bg.jpg) #efefef no-repeat center fixed;">
	<div id="logo">
		<!-- <img src="${ctx}/static/images/page/login_logo.png" width="514"
			height="95" /> -->
	</div>
	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td align="center">
				<div id="login_lable">
					<div class="title" style="text-align: center">莱芜市节水信息化管理平台</div>
					<div class="content">
						<form id="loginForm" action="${ctx}/login" method="post" onsubmit="return checkAll();">
						    <input type='hidden' name='submitToken'/>
							<table width="100%" border="0" cellpadding="0" cellspacing="05">
								<tr>
									<th width="25%" valign="top">登录名：</th>
									<td width="200px" valign="top" style="float:left;">
									    <input type="text" style="margin:0; padding:0; width:200px;height:23px;"
										id="username_text" name="username_text" autocomplete="off" maxlength=30/>
										<input type="hidden" id="username" name="username"/> &nbsp;</td>																	   
								</tr>							
								<tr>
									<th valign="top">密码：</th>
									<td width="200px" valign="top">
									    <span style="float:left;">		    
	   							            <span id="pwdSpan">
	   							                <input type="password" style="margin:0; padding:0; width:200px;height:23px;" id="password_text"
										        name="password_text" autocomplete="off" maxlength=30 />	
										    </span>										    
										    <input type="hidden" id="password" name="password"/>										
										    <!-- 方式一 -->
										    &nbsp;<input type="checkbox" style="margin:0;padding:0;height:15px;width:15px;margin-bottom:5px;vertical-align:text-top;margin-top:1px;" id="changeBtn" onclick="changeByCheckbox()" >
										    <span style="font-size:12px;margin-top:5px;">&nbsp;显示密码</span>	
										</span>											
									</td>
								</tr>
<!-- 								<tr> -->
<!-- 									<th valign="top"></th> -->
<!-- 									<td valign="top">默认用户名/密码：super/111111</td> -->
<!-- 								</tr> -->
								<c:if test="${sysConfig.configValue eq '1'}">
								<tr>
									<th valign="top">验证码：</th>
									<td valign="top" width="285px" style="float:left;">
									     <input type="text" id="captcha" name="captcha" autocomplete="off" style="margin:0; padding:0; width:80px;height:23px;"  />
										 &nbsp;<img src="${ctx}/captchaImage" id="captchaImage" width="100" height="27" align="absmiddle" />
										 &nbsp;<a href="#" onclick="changeCaptcha()">看不清?换一张</a><br />
										<!-- <div class="checkbox">
											<input type="checkbox" name="rememberMe" id="rememberMe" value="true"/>&nbsp;下次自动登录
										</div>
										 -->
								    </td>								  
								</tr>
								
								</c:if>
								<c:if test="${sysConfig.configValue ne '1'}">
								<!-- 
								<tr>
									<th valign="top"></th>
									<td valign="top">
										<div class="checkbox">
											<input type="checkbox" name="rememberMe" id="rememberMe" value="true"/>&nbsp;下次自动登录
										</div></td>
								</tr>
								 -->								
								</c:if>
								<tr>
									<td colspan="2" align="center">
									    <input type="image"
										src="${ctx}/static/images/page/but01.png" width="90"
										height="32" id="sumbitBtn"/>
										<!-- 阻止IE下表单自定提交 -->
										<img
										src="${ctx}/static/images/page/but02.png" width="90"
										height="32"  style="display:none"/></td>
								</tr>
								 
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>
                                    <label id="loginName_error" style="color:red;"></label>&nbsp;&nbsp;
                                    <label id="password_error" style="color:red;"></label>&nbsp;&nbsp;
                                    <label id="captcha_error" style="color:red;"></label>&nbsp;&nbsp;
        <c:choose>
            <c:when
                test="${shiroLoginFailure eq 'org.apache.shiro.authc.UnknownAccountException'}">
                <div style='color:red'>该用户不存在.</div>
            </c:when>
            <c:when
                test="${shiroLoginFailure eq 'org.apache.shiro.authc.IncorrectCredentialsException'}">
                <div style='color:red'>密码错误.</div>
            </c:when>
            <c:when
                test="${shiroLoginFailure eq 'com.hongguaninfo.hgdf.adp.shiro.CaptchaException'}">
                <div style='color:red'>验证码错误，请重试.</div>
            </c:when>
            <c:when
                test="${shiroLoginFailure eq 'org.apache.shiro.authc.LockedAccountException'}">
                <div style='color:red'>账号被锁定.</div>
            </c:when>
            <c:when test="${shiroLoginFailure ne null}">
                <div style='color:red'>登录认证错误，请重试.</div>
            </c:when>
            
            
        </c:choose>                                    
                                    </td>
                                   
                                </tr>								
							</table>
						</form>
					</div>
				</div>
				<div id="foot">
					<em></em>
				</div>
			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
//验证登录名：是否为空
function loginName_required(){
	var msg = "";
	var loginName = document.getElementById("username_text").value;
	var errorMsg = document.getElementById("loginName_error");
	errorMsg.innerText = "";//清空错误提示
	if(loginName.length==0){
		msg = "登录名不能为空";
		errorMsg.innerText = msg;
		$("body").unblock();
		return false;
	}else{
		return true;
	}
}
 //验证密码：是否为空
function password_required(){
	var msg = "";
	var password = document.getElementById("password_text").value;
	var errorMsg = document.getElementById("password_error");
	errorMsg.innerText = "";
	if(password.length==0){
		msg = "密码不能为空";
		errorMsg.innerText = msg;
		$("body").unblock();
		return false;
	}else{
		return true;
	}
}
//验证码：是否为空
function captcha_required(){
	var msg = "";
	var captcha = document.getElementById("captcha");
	var errorMsg = document.getElementById("captcha_error");
	errorMsg.innerText = "";
	//先判断这个验证码的元素是否存在
	if(captcha){
		if(captcha.value.length==0){
			msg = "验证码不能为空";
			errorMsg.innerText = msg;
			$("body").unblock();
			return false;
		}else{
			return true;
		}
	}else{	
		return true;
	}
	
} 
function checkAll(){
	var loginName_required_result = loginName_required();
	var password_required_result = password_required();
	var captcha_required_result = captcha_required();
	if(loginName_required_result&&password_required_result&&captcha_required_result){
		return true;
	}else{
		return false;
	}
}

//密码明文、密文切换——方式一:复选框样式
function changeByCheckbox(){
	
	var changeBtn = document.getElementById("changeBtn");//复选框
	var pwd = document.getElementById("password_text");//密码框
	var pwdSpan = document.getElementById("pwdSpan");//span
	var pwdValue = pwd.value;//密码框的值
    
    if(changeBtn.checked) {	     	
		pwdSpan.innerHTML = "";
		pwdSpan.innerHTML = '<input type="text" style="margin:0; padding:0; width:200px;height:23px;" id="password_text" name="password_text" autocomplete="off" maxlength=30 value="'+pwdValue+'" onblur="password_required()" onfocus="clearError("password_error")"/>';		
    }else{  
		pwdSpan.innerHTML = "";
	    pwdSpan.innerHTML = '<input type="password" style="margin:0; padding:0; width:200px;height:23px;" id="password_text" name="password_text" autocomplete="off" maxlength=30 value="'+pwdValue+'" onblur="password_required()" onfocus="clearError("password_error")"/>';     
    }
}

</script>
</html>