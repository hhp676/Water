<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>首页keyboard</title>
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${ctx}/plugins/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/plugins/jquery-easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/static/css/page/index.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css" />

<!-- jquery -->
<!--[if IE 8]>
    <! -- ie8时,使用jqueryA版本  -->
	<script type="text/javascript" src="${ctx}/static/js/jquery-A.js"></script>
<![endif]-->
<!--[if gte IE 9]>
    <! --ie版本大于等于9时,使用jquery1.12.4版本  -->
	<script type="text/javascript" src="${ctx}/static/js/jquery-1.12.4.js"></script>
<![endif]-->
<!-- jquery-ui -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/plugins/jquery-ui/jquery-ui.css" />
<script type="text/javascript"
	src="${ctx}/plugins/jquery-ui/jquery-ui.js"></script>

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

<script type="text/javascript"
	src="${ctx}/plugins/jquery-easyui/extension/jquery-easyui-portal/jquery.portal.js"></script>
<link
	href="${ctx}/plugins/jquery-easyui/extension/jquery-easyui-portal/portal.css"
	type="text/css" rel="stylesheet">
	
<!-- validate -->
<script type="text/javascript"
	src="${ctx}/plugins/jquery-validator/jquery.validate.js"></script>
<script type="text/javascript"
	src="${ctx}/plugins/jquery-validator/jquery.validate.message_cn.js"></script>

<!-- blockUI -->
<script type="text/javascript"
	src="${ctx}/plugins/jquery-blockUI/jquery.blockUI.js"></script>
	
<script type="text/javascript" src="${ctx}/static/js/main.js"></script>
<script type="text/javascript" src="${ctx}/static/js/hg.util.js"></script>
<!-- grumble -->
<link rel="stylesheet" type="text/css"
	href="${ctx}/plugins/grumble/css/grumble.css">
<script src="${ctx}/plugins/grumble/js/Bubble.js"></script>
<script src="${ctx}/plugins/grumble/js/jquery.grumble.js"></script>

</head>
<body>
	<div>
		<span id="grumble_todo"><a href="javascript:void(0);" id="grumble_todo_tab">待办任务</a></span> &nbsp;&nbsp;&nbsp;
		<span id="grumble_toclaim"><a href="javascript:void(0);" id="grumble_toclaim_tab">待领任务</a></span> &nbsp;&nbsp;&nbsp;
		<span id="grumble_notify"><a href="javascript:void(0);" id="grumble_notify_tab">未读通知</a></span>
	</div>
	<script>
		$(".grumble").remove();
		Hg.getJson("/workflow/task/count", {}, function(data) {
			if (data.success) {
				//待办、待认领任务
				if(data.toDoCount > 0){
					animeGrumble({
						divId : "grumble_todo",
						text : data.toDoCount
					});
				}else{
					basicGrumble({
						divId : "grumble_todo",
						text : data.toDoCount
					});					
				}

				if(data.toClaimCount > 0){
					animeGrumble({
						divId : "grumble_toclaim",
						text : data.toClaimCount
					});
				}else{
					basicGrumble({
						divId : "grumble_toclaim",
						text : data.toClaimCount
					});					
				}
			}
		});

		Hg.getJson("/sys/notify/myNotify/count", {}, function(data) {
			if (data.success) {
				//待办、待认领任务
				if(data.count > 0){
					animeGrumble({
						divId : "grumble_notify",
						text : data.count
					});
				}else{
					basicGrumble({
						divId : "grumble_notify",
						text : data.count
					});					
				}
			}
		});
		
		function basicGrumble(option){
			$('#'+ option.divId).grumble({
				text: option.text, 
				angle: 180, 
				distance: 3, 
				showAfter: 1000,
				hideAfter: 2000,
				type : 'alt-',
				hideAfter : false
			});	
		}

		function animeGrumble(option){
			$('#'+ option.divId).grumble({
				text: option.text, 
				angle: 180, 
				distance: 3, 
				showAfter: 1000,
				hideAfter: 2000,
				hideAfter : false,
				onShow: function(){
					var angle = 150, dir = 1;
					interval = setInterval(function(){
						(angle > 200 ? (dir=-1, angle--) : ( angle < 150 ? (dir=1, angle++) : angle+=dir));
						$('#'+ option.divId).grumble('adjust',{angle: angle});
					},30);
				},
				onHide: function(){
					clearInterval(interval);
				}			
			});
		}
		
		//发起流程
		$("#grumble_todo_tab").click(function() {
			var url = "/workflow/task/toDo/show";
			parent.HgTab("待办任务", url, null, null);
		});
		$("#grumble_toclaim_tab").click(function() {
			var url = "/workflow/task/toClaim/show";
			parent.HgTab("待领任务", url, null, null);
		});
		
		$("#grumble_notify_tab").click(function() {
			parent.showUserCenterWin();
			 parent.userCenter.callback = function(){
				parent.userCenter.tabObj.tabs("select",3);
			} 
		});	
	</script>
</body>
</html>
