var activeTimeoutObject = {};
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
	var lastActiveTime = parseInt(get_cookie(activeTimeoutObject.cookieName));
	return lastActiveTime;
}

/**
 * 更新用户最后活跃时间
 */
activeTimeoutObject.updateLastActiveTime = function(){
	var now = (new Date()).getTime();
	set_cookie(activeTimeoutObject.cookieName,now);
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
			/*$.messager.ok("长时间未操作，您的会话已结束。",function(){
				window.location = window.location ;
			})*/
			window.location = window.location ;
		});
		console.debug("active timeout!--" + now);
		clearInterval(activeTimeoutObject.intervalID);
		return;
	}
	$(activeTimeoutObject.container).one("mousemove",function(){
		activeTimeoutObject.updateLastActiveTime();
	})
}