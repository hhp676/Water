<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>首页home</title>
</head>
<body>
	<div class="easyui-panel" data-options="fit:true" style="padding: 0px;">
		<div class="easyui-layout" data-options="fit:true" id="index_home">
			<div data-options="region:'center'">
				<div id="pp" style="position: relative;overflow:hidden;">
					<%--<div style="width: 28%;">
						<div title="快捷方式" iconCls="m-icon-shortcut" collapsible="true" id="indexKeyBoardDiv"
							closable="false" style="height: 180px; padding: 1px;overflow:hidden;">
							<iframe frameborder="0" scrolling="no"   style="border: 0px" id="indexKeyBoard"></iframe>
						</div>
						<div title="常用连接" iconCls="m-icon-link" collapsible="true"
							closable="false" style="height: 130px; padding: 10px;">
							<div class="help">
								<a href="http://www.baidu.com/" target="_blank" style="font-size:12px;">百度(http://www.baidu.com/)</a>
							</div>
							<div class="help">
								<a href="http://fanyi.baidu.com/#auto/zh/" target="_blank" style="font-size:12px;">翻译(http://fanyi.baidu.com/)</a>
							</div>
						</div>
						<div title="操作手册" iconCls="m-icon-handbook" collapsible="true" id="helpDiv"
							closable="false" style="height: 130px; padding: 10px;">
							<div class="help">
				
							</div>
						</div>
					</div>--%>
					<div style="width: 43%;">
						<div title="公告" iconCls="acc_icon_photos" collapsible="true" id="noticeDiv"
							closable="false" style="padding: 5px;height:80px;" >
							<div style="text-align:center; line-height: 80px; font-size: 30px"> 欢迎 <shiro:principal />，进入莱芜市节水信息化管理平台  </div>

						</div>
						<div title="天气预报" iconCls="m-icon-weather" collapsible="true"
							closable="false" style="height: 150px; padding: 5px;overflow:hidden;">
							<iframe allowtransparency="true" frameborder="0" width="590" id="tianqi"
								height="96" scrolling="no"
								 ></iframe>
						</div>
						<div id="ownerLogs" iconCls="acc_icon_page_white_text" title="个人操作日志"  
							closable="false" collapsible="true" style="height: 150px;">
							<table class="easyui-datagrid" style="width: 650px; height: auto"
								fit="true" border="false" singleSelect="true" idField="logId"
								fitColumns="true" id="index_userLogGrid">
								<thead>
									<tr>
										<th data-options="field:'crtTimeStr',width:130">时间</th>
										<th data-options="field:'operName',width:120">操作名称</th>
										<th data-options="field:'operIP',width:100">操作IP</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<div style="width: 22%;">
						<div title="时钟" iconCls="m-icon-clock" collapsible="true"
							closable="false" style="height: 90px; padding: 10px;">
							<div id="clock"></div>
						</div>
						<div title="日历" iconCls="m-icon-calendar" collapsible="true"
							closable="false" style="height: 230px; padding: 10px;">
							<div class="easyui-calendar" style="width: 200px; height: 180px;"></div>
						</div>
						<div title="文件列表" iconCls="acc_icon_page_white_text" collapsible="true"
							 closable="false" style="height: 230px; ">
								<table class="easyui-datagrid" style="width: 650px; height: auto"
									   fit="true" border="false" singleSelect="true" idField="logId"
									   fitColumns="true" id="uploadFileList">
									<thead>
									<tr>
										<th data-options="field:'fileName', formatter:rowformatter, width:130">文件名称</th>

									</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">

        function fileDown(){
            var url = 'ftp://' + '' + ':' + '' + '@' + '192.168.1.127' + ':' + '21' + 'error.log';
            window.location.href = url;
        };


        function rowformatter(value, row, index){
			return "<a href='#' onclick=show('"+row.fileName+"'," +index+");>"+row.fileName + "</a>";
           /* return "<a href='/wa/WaCompanyInfo/downLoadFile/error.log' target='_self' >"+row.fileName + "</a>";*/
		}

		function show(val, index) {
            var downDirs = "D:\\";
            $.ajaxFileUpload({
                //处理文件上传操作的服务器端地址
                url:G_CTX_PATH +"/wa/WaCompanyInfo/downLoadFile/"+val, //+"/"+downDirs,
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: 'uploadFile', //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                method: "get",
                // data: param,
                // fileElementId:'companyInfoExcelFile',           //文件选择框的id属性
                // dataType:'json',                       //服务器返回的格式,可以是json或xml等
                success:function(data, status){
                    $.messager.ok("下载成功，文件路径： " + downDirs+"/"+val );

                },
                error:function(data, status, e){ //服务器响应失败时的处理函数
                    $.messager.ok(val + " 下载失败!");
                }
            });
        }

	   if(navigator.userAgent.indexOf('Firefox') >= 0){  
//		   $("#indexKeyBoardDiv").css("height","200px");
			$("#noticeDiv").css("height","150px");
			$("#ownerLogs").css("height","160px");
			$("#helpDiv").css("height","160px");
	   } else{
		   var browser=navigator.appName 
			var b_version=navigator.appVersion 
			var version=b_version.split(";"); 
			var trim_Version=version[1].replace(/[ ]/g,""); 
			 
			$(document).ready(function(){
				//非IE8 调试div 高度
				if(!(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE8.0")) 
				{ 
		 			$("#indexKeyBoardDiv").css("height","200px");
		 			$("#noticeDiv").css("height","150px");
		 			$("#ownerLogs").css("height","160px");
		 			$("#helpDiv").css("height","160px");
				} 
				if((browser=="Microsoft Internet Explorer" && trim_Version=="MSIE9.0")) 
				{ 
					$("#indexKeyBoardDiv").css("height","190px");
		 			$("#noticeDiv").css("height","150px");
		 			$("#ownerLogs").css("height","140px");
		 			$("#helpDiv").css("height","140px");
				}
				
			}) 
	   }
	   
	 
		 //延迟加载 代办任务-IE9中 jquery 会有冲突
		  setTimeout(function(){
			  $("#indexKeyBoard").attr("src","${ctx}/indexKeyboard"); 
			  $("#tianqi").attr("src","http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=2&t=0&v=0&d=5&bd=0&k=000000&f=004080&q=1&e=0&a=1&c=58362&w=290&h=96&align=center");
		}, 2000);  
		 
		$("#clock").MyDigitClock({
			fontSize : 25,
			fontFamily : "Century gothic",
			fontColor : "red",
			fontWeight : "bold",
			bAmPm : true,
			background : '#fff',
			bShowHeartBeat : false
		});

		$("#index_userLogGrid").datagrid({
			url : "${ctx}/sys/userLog/userLogList",
			view : bufferview,
			queryParams : {
				rows : 10
			}
		});

       $("#uploadFileList").datagrid({
           url : "${ctx}/wa/WaCompanyInfo/uploadFileList",
           view : bufferview,
           queryParams : {
               rows : 10
           }
       });

		$("#index_processGrid").datagrid();

		
		$(function() {
			$('#pp').portal({
				border : false,
				fit : true
			});
			  
		});

		Hg.getJson("/sys/notice/indexNoticeList", {}, function(data) {
			if (data.success) {
				var list = data.list;
				if (list.length > 0) {
					for (var i = 0; i < list.length; i++) {
						var id = list[i].notifyId;
						$("#pp .notice").append(
								"<li><a href='javascript:lookupNoticeDetail("
										+ id + ");'>" + list[i].title
										+ "</a></li>");
					}
				} else {
					$("#pp .notice").append("暂无公告");
				}
			}
		});
		function lookupNoticeDetail(id) {
			var notifyDetailWin = new HgWin({
				id : "notifyDetailWin",
				title : "公告",
				width : 700,
				height : 500,
				iconCls : "acc_icon_photos",
				url : "/sys/notice/indexView/" + id
			});
		};

		function showUserNotify() {
			Hg.getJson("/sys/notify/indexUserNotify",{},
							function(data) {
								if (data.success) {
									if (data.userNotify) {
										var notify = data.userNotify;
										var msg = "<a href='javascript:lookupNotifyDetail("
												+ notify.notifyId
												+ ");'>"
												+ notify.contentSub + "</a>";
										if (notify) {
											$.messager
													.show({
														title : notify.titleSub,
														msg : msg,
														showType : 'slide',
														style : {
															right : '',
															top : document.body.scrollTop
																	+ document.documentElement.scrollTop,
															bottom : ''
														}
													});
										}
									}

								}
							});
			if (defaultSetting.G_NOTIFY_FLUSH_SWITCH) {
				setTimeout("showUserNotify()",
						defaultSetting.G_NOTIFY_FLUSH_RATE);
			}
		};
		showUserNotify();

		function lookupNotifyDetail(id) {
			var UserNotifyViewWin = new HgWin({
				id : "UserNotifyViewWin",
				title : "通知",
				width : 700,
				height : 500,
				iconCls : "acc_icon_bell",
				url : "/sys/notify/indexView/" + id
			});
			Hg.getJson("/sys/notify/readUserNotify/" + id, {}, function(data) {
			});
			$("#pc_UserNotify_grid").datagrid({
				url : G_CTX_PATH+"/sys/notify/list?userId=" + loginUserId
			});
		}

		//发起流程
		$("#startDiaryProcess_index").click(function() {
			showStartProcess("diaryProcess", "填写日报信息", 600, 370);
		});

		$("#startRecruitProcess_index").click(function() {
			showStartProcess("recruitProcess", "填写招聘需求", 800, 500);
		});
		showStartProcess = function(key, title, width, height) {
			var processStartWin = new HgWin({
				id : "processStartWin",
				title : title,
				width : width,
				height : height,
				iconCls : 'm-icon-control-play-blue',
				url : "/workflow/form/showStart/" + key
			});
		};
	</script>
 
	<script type="text/javascript">
	function changeFrameHeight(){
	    var ifm= document.getElementById("indexKeyBoard"); 
	    ifm.height=document.documentElement.clientHeight;
	}
	window.onresize=function(){  
	     changeFrameHeight();  
	} 
	</script>
</body>
</html>
