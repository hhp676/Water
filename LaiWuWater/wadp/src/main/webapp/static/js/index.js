$(function() {
	// 个人中心----------------------------------------------------------------------------------
	$("#editpass").click(function() {
		var userCenterWin = new HgWin({
			id : "userCenterWin",
			title : "个人中心",
			width : 700,
			height : 400,
			iconCls : "m-icon-personal",
			url : "/personalCenter"
		});
	});

	$("#loginOut").click(function() {
		$.messager.confirm('退出登录', '您确认要退出登录么?', function(r) {
			if (r) {
				window.location = G_CTX_PATH + "/logout";
			}
		});
	});

    $("#backToMap").click(function() {
        window.location = G_CTX_PATH + "/";
    });

	// 左侧导航菜单-------------------------------------------------------------------------
	$("div .acc-panel-inner").hover(function() {
		$(this).addClass("accordion-header-selected");
	}, function() {
		$(this).removeClass("accordion-header-selected");
	}).click(function() {
		$("#tab").tabs("add", {
			title : "sss",
			closable : true
		});
	});

	$("#topMenu a").click(function() {
		var src = $(this).attr("tag");
		$('#center').panel("refresh", src);
		$("#topMenu li").removeClass("selected");
		$(this).parent().addClass("selected");
	});

	// 全局的ajax访问，处理ajax清求时sesion超时
	$.ajaxSetup({
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		complete : function(XMLHttpRequest, textStatus) {
			// 通过XMLHttpRequest取得响应头，sessionstatus，hhp隐藏
			/*var sessionstatus = XMLHttpRequest
					.getResponseHeader("sessionstatus");
			if (sessionstatus == "timeout") {
				// 如果超时就处理 ，指定要跳转的页面
				window.location = "<c:url value=" / " />";
			}*/
		}
	});
	
	
	//--------jGrowl demo--------
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
	
	//noty demo
   /* noty({text:"error test !", layout:"center", type: 'error', timeout:5000});
    noty({text:"success test !", layout:"center", type: 'success', timeout:5000});
    noty({text:"alert test !", layout:"center", type: 'alert', timeout:5000});*/
	//$.noty.consumeAlert("弹出框测试！");
    
    //gridly
    /*$(document).on("click", ".gridly .brick", function(event) {
      $('.gridly').gridly('layout');
    });*/
});
