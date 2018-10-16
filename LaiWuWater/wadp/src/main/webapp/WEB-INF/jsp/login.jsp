<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<meta charset="utf-8" />
<title>应用管理平台</title>
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- basic styles -->
<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css" />

<!--[if IE 7]>
<link rel="stylesheet" href="${ctx}/static/css/font-awesome-ie7.min.css" />
<![endif]-->

<!-- page specific plugin styles -->
<link rel="stylesheet"
	href="${ctx}/static/css/jquery-ui-1.10.3.full.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/jquery.gritter.css" />
<!-- fonts -->
<!-- ace styles -->
<link rel="stylesheet" href="${ctx}/static/css/ace.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/ace-skins.min.css" />

<!--[if lte IE 8]>
<link rel="stylesheet" href="${ctx}/static/css/ace-ie.min.css" />
<![endif]-->

<!-- inline styles related to this page -->
<link rel="stylesheet" href="${ctx}/static/css/style.css" />
<!-- ace settings handler -->

<script src="${ctx}/static/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<script src="${ctx}/static/js/html5shiv.js"></script>
<script src="${ctx}/static/js/respond.min.js"></script>
<![endif]-->
</head>

<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>
								<i class="icon-leaf green"></i> <span class="red">鸿冠</span> <span
									class="white">应用管理平台</span>
							</h1>
							<!-- <h4 class="blue">&copy; 上海鸿冠信息科技有限公司</h4> -->
						</div>

						<div class="space-6"></div>

						<div class="position-relative">
							<div id="login-box"
								class="login-box visible widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header blue lighter bigger">
											<i class="icon-coffee green"></i> 登录
										</h4>

										<div class="space-6"></div>

										<form id="loginForm" action="${ctx}/login" method="post">
										  <input type='hidden' name='submitToken'/>
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" placeholder="登录名"
														id="username_text" name="username_text" value="${username}" maxlength=30 /> <i
														class="icon-user"></i>
														<input type="hidden"  id="username" name="username"/>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" class="form-control" placeholder="密码"
														id="password_text" name="password_text" maxlength=30 /> <i class="icon-lock"></i>
														<input type="hidden"  id="password" name="password"/>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" id="captcha" name="captcha" placeholder="验证码"
														style="width: 80px;" /> &nbsp;<img
														src="${ctx}/captchaImage" id="captchaImage" width="70"
														height="22" align="absmiddle" /> &nbsp;<a href="#"
														onclick="changeCaptcha()">看不清?换一张</a><br /> </i>
												</span>
												</label>

												<div class="space"></div>

												<div class="clearfix">
													<label class="inline"> <input type="checkbox"
														class="ace" name="rememberMe" id="rememberMe" /> <span
														class="lbl"> 记住我</span>
													</label>

													<button type="button" id="loginSubmitB" onclick="login();"
														class="width-35 pull-right btn btn-sm btn-primary">
														<i class="icon-key"></i> 登录
													</button>
												</div>

												<div class="space-4"></div>
												<div class="clearfix">
													<c:choose>
														<c:when
															test="${shiroLoginFailure eq 'com.hongguaninfo.hgdf.wadp.shiro.CaptchaException'}">
															<div style='color: red'>验证码错误，请重试.</div>
														</c:when>
														<c:when
															test="${shiroLoginFailure eq 'org.apache.shiro.authc.UnknownAccountException'}">
															<div style='color: red'>该用户不存在.</div>
														</c:when>
														<c:when
															test="${shiroLoginFailure eq 'org.apache.shiro.authc.IncorrectCredentialsException'}">
															<div style='color: red'>用户或密码错误.</div>
														</c:when>
														<c:when test="${shiroLoginFailure ne null}">
															<div style='color: red'>登录认证错误，请重试.</div>
														</c:when>
													</c:choose>
												</div>
											</fieldset>
										</form>
										<!-- 
										<div class="social-or-login center">
											<span class="bigger-110">Or Login Using</span>
										</div>

										<div class="social-login center">
											<a class="btn btn-primary"> <i class="icon-facebook"></i>
											</a> <a class="btn btn-info"> <i class="icon-twitter"></i>
											</a> <a class="btn btn-danger"> <i class="icon-google-plus"></i>
											</a>
										</div>
										 -->
									</div>
									<!-- /widget-main -->

									<div class="toolbar clearfix">
										<div>
											<a href="#" onclick="show_box('forgot-box'); return false;"
												class="forgot-password-link"> <i class="icon-arrow-left"></i>
												忘记密码
											</a>
										</div>
										<!-- 
										<div>
											<a href="#" onclick="show_box('signup-box'); return false;"
												class="user-signup-link"> I want to register <i
												class="icon-arrow-right"></i>
											</a>
										</div>
										 -->
									</div>
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /login-box -->

							<div id="forgot-box" class="forgot-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header red lighter bigger">
											<i class="icon-key"></i> 找回密码
										</h4>

										<div class="space-6"></div>
										<p>请输入您的电子邮箱地址</p>

										<form>
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="email" class="form-control" placeholder="Email" />
														<i class="icon-envelope"></i>
												</span>
												</label>

												<div class="clearfix">
													<button type="button"
														class="width-35 pull-right btn btn-sm btn-danger">
														<i class="icon-lightbulb"></i> 发送!
													</button>
												</div>
											</fieldset>
										</form>
									</div>
									<!-- /widget-main -->

									<div class="toolbar center">
										<a href="#" onclick="show_box('login-box'); return false;"
											class="back-to-login-link"> 返回登录 <i
											class="icon-arrow-right"></i>
										</a>
									</div>
								</div>
								<!-- /widget-body -->
							</div>
							<!-- /forgot-box -->
							<!-- 
							<div id="signup-box" class="signup-box widget-box no-border">
								<div class="widget-body">
									<div class="widget-main">
										<h4 class="header green lighter bigger">
											<i class="icon-group blue"></i> New User Registration
										</h4>

										<div class="space-6"></div>
										<p>Enter your details to begin:</p>

										<form>
											<fieldset>
												<label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="email" class="form-control" placeholder="Email" />
														<i class="icon-envelope"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="text" class="form-control" placeholder="Username" />
														<i class="icon-user"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" class="form-control"
														placeholder="Password" /> <i class="icon-lock"></i>
												</span>
												</label> <label class="block clearfix"> <span
													class="block input-icon input-icon-right"> <input
														type="password" class="form-control"
														placeholder="Repeat password" /> <i class="icon-retweet"></i>
												</span>
												</label> <label class="block"> <input type="checkbox"
													class="ace" /> <span class="lbl"> I accept the <a
														href="#">User Agreement</a>
												</span>
												</label>

												<div class="space-24"></div>

												<div class="clearfix">
													<button type="reset" class="width-30 pull-left btn btn-sm">
														<i class="icon-refresh"></i> Reset
													</button>

													<button type="button"
														class="width-65 pull-right btn btn-sm btn-success">
														Register <i class="icon-arrow-right icon-on-right"></i>
													</button>
												</div>
											</fieldset>
										</form>
									</div>

									<div class="toolbar center">
										<a href="#" onclick="show_box('login-box'); return false;"
											class="back-to-login-link"> <i class="icon-arrow-left"></i>
											Back to login
										</a>
									</div>
								</div>
								 -->
							<!-- /widget-body -->
						</div>
						<!-- /signup-box -->
					</div>
					<!-- /position-relative -->
				</div>
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
	</div>
	</div>
	<!-- /.main-container -->

	<!-- basic scripts -->
	<script src='${ctx}/static/js/jquery-1.12.4.js'></script>
	<script type="text/javascript">
		if ("ontouchend" in document)
			document
					.write("<script src='${ctx}/static/js/jquery.mobile.custom.min.js'>"
							+ "<" + "/script>");
	</script>
	<script src="${ctx}/static/js/bootstrap.min.js"></script>
	<script src="${ctx}/static/js/typeahead-bs2.min.js"></script>

	<!-- page specific plugin scripts -->
	<script src="${ctx}/static/js/jquery.gritter.min.js"></script>
	<script src="${ctx}/static/js/bootbox.min.js"></script>

	<script src="${ctx}/static/js/ace-elements.min.js"></script>
	<script src="${ctx}/static/js/ace.min.js"></script>
	<script src="${ctx}/static/js/common.js"></script>
	<script src="${ctx}/static/js/security/RSAUtil.js"></script>

	<script type="text/javascript">
	var rsa_modulus = "${rsa_modulus}";
	var rsa_exponent = "${rsa_exponent}";
	var rsa_key = RSAUtils.getKeyPair(rsa_exponent, '', rsa_modulus);
	
		function show_box(id) {
			jQuery('.widget-box.visible').removeClass('visible');
			jQuery('#' + id).addClass('visible');
		}

		function changeCaptcha() {
			$('#captchaImage').hide().attr('src',
					'${ctx}/captchaImage?' + Math.floor(Math.random() * 100))
					.fadeIn();
			event.cancelBubble = true;
		}
		function login() {
			if (check_form("loginForm")) {
				//此处目的并非防重复提交，而是针对安全扫描中重复提交登录请求的数据情况进行token验证处理。
		        $.getJSON("${ctx}/refSubmitToken", {}, function(data) {
		            $("#loginForm").find("[name='submitToken']").val(data.data);
		            $("#username").val(RSAUtils.encryptedString(rsa_key, $("#username_text").val()));
		            $("#password").val(RSAUtils.encryptedString(rsa_key, $("#password_text").val()));
		            $("#username_text").attr("disabled","disabled");
		            $("#password_text").attr("disabled","disabled");
		            sendForm("loginForm",  "${ctx}/login");
		        });
			}
		}
		$(document).ready(function() {
			$('#captchaImage').click(changeCaptcha);
		});
	</script>
</body>
</html>
