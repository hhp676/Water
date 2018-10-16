$(function() {
	// 个人中心----------------------------------------------------------------------------------
	$("#editpass").click(function() {
		showUserCenterWin();
	});

	$("#loginOut").click(function() {
		Hg.confirm('退出登录', '您确认要退出登录么?', function(r) {
			if (r) {
				window.location = G_CTX_PATH + "/logout";
			}
		});
	});

    $("#backToMap").click(function() {
        window.location = G_CTX_PATH + "/";
    });

    G_chkPassword(shouldChangePassword);
	// 全局的ajax访问，处理ajax清求时sesion超时
	$.ajaxSetup({
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		complete : function(XMLHttpRequest, textStatus) {
			// 通过XMLHttpRequest取得响应头，sessionstatus，hhp隐藏
			/*var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
			if (sessionstatus == "timeout") {
				// 如果超时就处理 ，指定要跳转的页面
				window.location = "<c:url value=" / " />";
			}*/
		}
	});
	
	//--------jGrowl demo--------
	/*
	$('#jgrowl').jGrowl("测试通知一", {
		glue: 'iphone',
		speed: 2000,
		animateOpen: { 
			height: "show",
			width: "show"
		},
		animateClose: { 
			height: "hide",
			width: "show"
		},
		life: 1000,
		header: '测试通知一',
		theme: 'iphone'
	});
	$('#jgrowl').jGrowl("测试消息二", {
		glue: 'before',
		speed: 2000,
		animateOpen: { 
			height: "show",
			width: "show"
		},
		animateClose: { 
			height: "hide",
			width: "show"
		},
		sticky: true,
		life: 1000,
		header: '测试消息二',
		theme: 'iphone'
	});	
	*/
	//noty demo
   /* noty({text:"error test !", layout:"center", type: 'error', timeout:5000});
    noty({text:"success test !", layout:"center", type: 'success', timeout:5000});
    noty({text:"alert test !", layout:"center", type: 'alert', timeout:5000});*/
	//$.noty.consumeAlert("弹出框测试！");
    
    //gridly
    /*$(document).on("click", ".gridly .brick", function(event) {
      $('.gridly').gridly('layout');
    });*/
	
	/**
	 * 用户活跃检测，通过配置值检测是否需要退出登录。
	 */
	var activeTimeoutNumber = parseInt(activeTimeoutObject.configValue);
	if (!isNaN(activeTimeoutNumber) && activeTimeoutNumber > 0){
		activeTimeoutObject.init(activeTimeoutNumber);
		activeTimeoutObject.intervalID = window.setInterval(activeTimeoutObject.intervalFunction, 30*1000);
	}
	
});

function showUserCenterWin() {
	var userCenterWin = new HgWin({
		id : "userCenterWin",
		title : "个人中心",
		width : 850,
		height : 550,
		iconCls : "m-icon-personal",
		url : "/personalCenter"
	});
}

/**
 * 初始化用户活跃检测对象
 */
activeTimeoutObject.init = function(activeTimeoutNumber){
	activeTimeoutObject.container = "body";
	activeTimeoutObject.cookieName = "lastActiveTime";
	activeTimeoutObject.configValue = activeTimeoutNumber;
	activeTimeoutObject.updateLastActiveTime();
}

/**
 * 获取用户最后活跃时间
 */
activeTimeoutObject.getLastActiveTime = function(){
	var lastActiveTime = parseInt(getCookie(activeTimeoutObject.cookieName));
	return lastActiveTime;
}

/**
 * 更新用户最后活跃时间
 */
activeTimeoutObject.updateLastActiveTime = function(){
	var now = (new Date()).getTime();
	setCookie(activeTimeoutObject.cookieName,now);
	//console.debug("setLastActiveTime--" + now);
}

/**
 * 用户活跃检测
 */
activeTimeoutObject.intervalFunction = function(){
	var now = (new Date()).getTime();
	if (now - activeTimeoutObject.getLastActiveTime() > activeTimeoutObject.configValue*1000){
		//执行退出操作
		$.get(G_CTX_PATH + "/logout?etc=" + new Date().getTime(), {},function(){
			 $.messager.alert('警告','长时间未操作，您的会话已结束！','warning',function ()  {
				            window.location.reload();
			  });
		});
		clearInterval(activeTimeoutObject.intervalID);
		return;
	}
	$(activeTimeoutObject.container).one("mousemove",function(){
		activeTimeoutObject.updateLastActiveTime();
	})
}

//是否需要修改密码
function G_chkPassword(shouldChangePassword){
	if (shouldChangePassword=="true"){
		var changePasswordWin = new HgWin({
			id : "showChangePersonalPwdWin",
			title : "修改密码",
			width : 550,
			height : 300,
			iconCls : "m-icon-personal",
			url : "/showChangePersonalPwd"
		});
		$("#showChangePersonalPwdWin").prev(".panel-header").find(".panel-tool-close").hide();
		
	}
}
