/*-----------------------------------------------------------------------------
| 默认设置
------------------------------------------------------------------------------*/
var defaultSetting = {
		G_NOTIFY_FLUSH_SWITCH : true, // 定时刷新通知开关
		G_NOTIFY_FLUSH_RATE : 300000, // 定时刷新通知频率
		G_HOME_TABS_LIMIT : 10  //首页TAB上限
};

/*-----------------------------------------------------------------------------
 | 全局变量
 ------------------------------------------------------------------------------*/
var G_V = {};

function HgWin(option) {
	var defaults = {
		width : 600,
		height : 400,
		modal : true,
		minimizable : false,
		maximizable : false,
		collapsible : false,
		resizable : false,
		iconCls : "m-icon-win"
	};
	var _$winObj = null;
	if ($("#" + option.id).length > 0) {
		_$winObj = $("#" + option.id);
	} else {
		_$winObj = $("<div id='" + option.id + "'></div>");
	}
	var _$winOpts = $.extend({}, defaults, option || {});
	/*var win = _$winObj.window($.extend({}, defaults, option || {})).window(
	"open");*/
	
	//弹窗关闭时同时销毁该窗体 
	_$winOpts.onClose = function() {
		if(option.onClose){
			option.onClose();
		}
		$("#" + option.id).window('destroy');
	}
	var win = _$winObj.window(_$winOpts).window("open");	
	if (option.url)
		win.window("refresh", G_CTX_PATH + option.url);
	return win;
}

function HgDialog(option) {
	var defaults = {
		width : 600,
		height : 400,
		modal : true,
		minimizable : false,
		maximizable : false,
		collapsible : false,
		iconCls : "m-icon-win"
	};
	var _$digObj = $("<div></div>");
	if (option.id)
		_$winObj = $("#" + option.id);
	var win = _$winObj.window($.extend({}, defaults, option || {})).window(
			"open");
	if (option.url)
		win.window("refresh", G_CTX_PATH + option.url);
	return win;
}

function HgTab(title, url, type, icon) {
	if (typeof icon == 'undefined') {
		icon = "m-icon-door";
	}
	var tab = $("#tab");
	if (tab.tabs("exists", title)) {
		tab.tabs("select", title);
	} else {
		tab.tabs("add", {
			title : title,
			closable : true,
			icon : icon
		});
		url = G_CTX_PATH + url;
		if (typeof type == 'undefined' || type == 'iframe') {
			var subtab = tab.tabs('getSelected');
			var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ url + '" style="width:100%;height:100%;"></iframe>';
			tab.tabs('update', {
				tab : subtab,
				options : {
					content : content
				}
			});
		} else {
			tab.tabs('getSelected').panel("refresh", url);
		}
	}
}

/*----------------------------------------------------------------------------
 |  页面函数
 ------------------------------------------------------------------------------*/
function calPageTotalNum(totalCount, pageSize) {
	var totalNum = 0;
	if (totalCount % pageSize == 0) {
		totalNum = totalCount / pageSize;
	} else {
		totalNum = parseInt(totalCount / pageSize) + 1;
	}
	return totalNum;
}

function showLoadMsg(targetId) {
	$("#" + targetId).append(
			"<div id='" + targetId + "_loadMsg'><img  src='" + G_CTX_PATH
					+ "/static/images/icons/loading.gif' />数据加载中...</div>");
}

function rmLoadMsg(targetId) {
	$("#" + targetId + "_loadMsg").remove();
}

var G_VAL = {

}
 

$.messager.ok = function(msg, callback) {
	$.messager.alert("提示", "&nbsp;&nbsp;<div style='float:left'><img src='" + G_CTX_PATH
			+ "/static/images/icons/ok.png' /></div>&nbsp;&nbsp;" + "<div style='float:left;padding-top:5px'>&nbsp;&nbsp;&nbsp;&nbsp;"+msg+"</div>", null,
			callback);
}
 

// 提交遮罩
$.blockUI.defaults = $.extend({}, $.blockUI.defaults, {
	message : "<img src='" + G_CTX_PATH
			+ "/static/images/icons/loading1.gif'/>",
	css : {
		border : 'none',
		padding : '15px',
		width : "20px",
		top : "0px",
		left : "0px",
		opacity : 1
	},
	overlayCSS : {
		backgroundColor : '#ddd',
		opacity : 0.6,
		cursor : 'wait'
	}
});

// 拓展treegrid
$.extend($.fn.treegrid.methods, {
	cascadeCheckBox : function(target, idField) {
		var checkBoxObj = $(target).treegrid("getPanel").find("[node-id]")
				.find("input[type='checkbox']");
		checkBoxObj.change(function() {
			var id = $(this).parents("[node-id]").attr("node-id");
			var checkParentNode = function(currentObj, id) {
				var parentNode = $(target).treegrid("getParent", id);
				if (parentNode) {
					var parentId = parentNode[idField];
					if (currentObj.is(":checked")) {
						var parentObj = $(target).treegrid("getCheckBox",
								parentId).attr("checked", "checked");
						checkParentNode(parentObj, parentId);
					}

				}
			};
			checkParentNode($(this), id);

		});
	},
	getCheckBox : function(target, id) {
		var checkBoxObj = $(target).treegrid("getPanel").find(
				"[node-id='" + id + "']").find("input[type='checkbox']");
		return checkBoxObj;
	},
	getCheckedIds : function(target) {
		var authIdsAry = [];
		var panelObj = $(target).treegrid("getPanel");
		panelObj.find("[node-id]").each(function(i) {
			if ($(this).find("input[type='checkbox']:checked").length > 0) {
				authIdsAry.push($(this).attr("node-id"));
			}
		});
		return authIdsAry.join(",");
	}

});

// 拓展datagrid
$
		.extend(
				$.fn.datagrid.methods,
				{
					getCheckBox : function(target, indexId) {
						var checkBoxObj = $(target).datagrid("getPanel").find(
								"[datagrid-row-index='" + indexId + "']").find(
								"input[type='checkbox']");
						return checkBoxObj;
					},
					getCheckedIds : function(target, idFieldName) {
						var idsAry = [];
						var panelObj = $(target).datagrid("getPanel");
						panelObj
								.find("[datagrid-row-index]")
								.each(
										function(i) {
											if ($(this)
													.find(
															"input[type='checkbox']:checked").length > 0) {
												idsAry.push($(this).find(
														"[field='"
																+ idFieldName
																+ "']").text());
											}
										});
						return idsAry.join(",");
					},
					getNativeCheckedIds : function(target, idFieldName) {
						var checkedRows = $(target).datagrid("getChecked");
						var idsAry = [];
						for (var i = 0; i < checkedRows.length; i++) {
							idsAry.push(checkedRows[i][idFieldName]);
						}
						return idsAry.join(",");
					},
					showCellTip : function(target, fieldName) {
						$(target).datagrid(
								{
									onLoadSuccess : function() {
										var cellObj = $(target).datagrid(
												"getPanel")
												.find(
														"td[field='"
																+ fieldName
																+ "']");
										cellObj.each(function(i) {
											var content = $(this).text();
											$(this).tooltip({
												content : content
											});
										});

									}
								});

					}
				});

// 拓展validate
$.validator
		.addMethod(
				"mobile",
				function(value, element) {
					var length = value.length;
					var mobile = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|18[0-9]{1})+\d{8})$/
					return this.optional(element)
							|| (length == 11 && mobile.test(value));
				}, "手机号码格式错误");

// 设置validator的默认属性
$.validator.setDefaults({
	wrapper : "div",
	errorPlacement : function(error, element) {
		error.appendTo(element.parents("td:eq(0)"));
	}
});
//jquery.validator  验证扩展 
$.validator.addMethod("stringCheck",
		function(value, element) {
		return this.optional(element) ||  /^[a-zA-Z0-9_\s]*$/.test(value);
	}, "只能输入英文字母、数字、下划线和空格");
$.validator.addMethod("codeCheck",
		function(value, element) {
		return this.optional(element) ||  /^[a-zA-Z0-9_:\s]*$/.test(value);
	}, "只能输入英文字母、数字、下划线、空格和冒号");

 


$.fn.extend({
	showVaildTip : function(message) {
		var aryTipObj = [];
		this.each(function() {
			var tip = $(this).tooltip({
				position : 'right',
				content : "<span>" + message + "</span>",
				onShow : function() {
					$(this).tooltip('tip').css({
						color : "#000",
						borderColor : "#CC9933",
						backgroundColor : "#FFFFCC"
					});
				}
			}).tooltip("show");
			aryTipObj.push(tip);

		});
		var destoryTipObj = function(aryTipObj) {
			for (var i = 0; i < aryTipObj.length; aryTipObj++) {
				aryTipObj[i].tooltip("destroy");
			}
		};
		return {
			tip : aryTipObj,
			destory : destoryTipObj
		};

	}
});



var userCenter = {

};

/*-----------------------------------------------------------------------------
 |  公共函数
 ------------------------------------------------------------------------------*/
String.format = function() {
	if (arguments.length == 0)
		return null;

	var str = arguments[0];
	for (var i = 1; i < arguments.length; i++) {
		var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
		str = str.replace(re, arguments[i]);
	}
	return str;
}

// 字符串连接
function StringBuffer(str) {
	this.tmp = new Array();
}
StringBuffer.prototype.append = function(value) {
	this.tmp.push(value);
	return this;
}
StringBuffer.prototype.clear = function() {
	tmp.length = 1;
}
StringBuffer.prototype.toString = function() {
	return this.tmp.join('');
}

Array.prototype.find = function(val) {
	for (var i = 0, len = this.length; i < len; i++) {
		if (this[i] == val) {
			return this[i];
		}
	}
	return null;
}


function setCookie(name,value)
{
  var Days = 30; //此 cookie 将被保存 30 天
  var exp  = new Date();    //new Date("December 31, 9998");
  exp.setTime(exp.getTime() + Days*24*60*60*1000);
  document.cookie = name + "="+ escape(value) +";expires="+ exp.toGMTString();
}
function getCookie(name)
{
  var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
  if(arr != null) return unescape(arr[2]); return null;
}
function delCookie(name)
{
  var exp = new Date();
  exp.setTime(exp.getTime() - 1);
  var cval=getCookie(name);
  if(cval!=null) document.cookie=name +"="+cval+";expires="+exp.toGMTString();
}

 
 		
 /*原生js方式 ------------为解决IE浏览器某些方法不支持
密码：明文、密文切换
checkboxId：复选框ID
pwdId：密码框ID
spanId：包含密码框的span的Id
name：密码框的name
styleStr：密码框的样式	
*/		
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
/*jQuery方式 
密码：明文、密文切换
checkboxId：复选框ID
pwdId：密码框ID
spanId：包含密码框的span的Id
name：密码框的name
styleStr：密码框的样式	
*/		
function exchangePwd_jQuery(checkboxId,pwdId,spanId,name,styleStr){			
	var showPwd = $("#"+checkboxId);//定位复选框	
	var pwd = $("#"+pwdId);//定位密码框
	var pwdSpan =$("#"+spanId); //定位span
	var pwdValue = pwd.val();//密码框的值
	//console.log(showPwd.is(':checked'));
	if(showPwd.is(':checked')) {
		pwdSpan.html();
		pwdSpan.html('<input type="text" name="'+name+'" id="'+pwdId+'"  style="'+styleStr+'" value="'+pwdValue+'"/>');						
	}else{
		pwdSpan.html();
		pwdSpan.html('<input type="password" name="'+name+'" id="'+pwdId+'"  style="'+styleStr+'" value="'+pwdValue+'"/>');						
	}
}