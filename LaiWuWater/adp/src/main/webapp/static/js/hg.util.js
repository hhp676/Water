 

// Hg 构造器
var Hg = {

}
 

//扩展提示窗：让图标与文字居中
Hg.warning = function(msg,callback){
	$.messager.alert("提示", "&nbsp;&nbsp;<div style='float:left;'><img src='" + G_CTX_PATH
			+ "/static/images/icons/warning.png' /></div>&nbsp;&nbsp;" + "<div style='float:left;height:40px;line-height:40px;'>&nbsp;&nbsp;&nbsp;&nbsp;"+msg+"</div>", null,
			callback);
}
//扩展提示窗：让图标与文字居中
Hg.confirm = function(title,msg,callback){
	$.messager.confirm(title, "<div style='padding-top:6px'>"+msg+"</div>",callback);
}

Hg.fixTableHeight = function(tableId, hasParentPanel) {
	var height = $("body").height() - $("#" + tableId).offset().top - 2 - 30;
	if (hasParentPanel)
		height = height - 30;
	$("#" + tableId).height(height);
}

Hg.initFreshPanel = function(tableId) {
	$("#" + tableId).parent().panel({
		fit : true,
		tools : [ {
			iconCls : 'icon-reload',
			handler : function() {
				$("#tab").tabs('getSelected').panel("refresh");
			}
		} ]
	});
}

// ajax获取json数据
Hg.getJson = function(path, data, callback) {
	$.getJSON(G_CTX_PATH + path + "?etc=" + new Date().getTime(), data,
			callback);
}

// post请求（大数据请求json无法处理时,用此方法）,POST 请求功能以取代复杂 $.ajax
// 。请求成功时可调用回调函数。如果需要在出错时执行函数，请使用 $.ajax。
Hg.post = function(path, data, callback) {
	$.post(G_CTX_PATH + path + "?etc=" + new Date().getTime(), data, callback);
}

// 防止表单重复提交
Hg.refRepeatSubmit = function(formId) {
	$("#" + formId).append("<input type='hidden' name='submitToken'/>");
	Hg.getJson("/refSubmitToken", {}, function(data) {
		$("#" + formId).find("[name='submitToken']").val(data.data);
	});
}

// 刷新提交token
Hg.refreshSubmitToken = function(formId) {
	Hg.getJson("/refSubmitToken", {}, function(data) {
		$("#" + formId).find("[name='submitToken']").val(data.data);
	});
}
//移除多余右键菜单----改为处理页面有右键功能，但是无菜单情况下显示问题
Hg.removeMenu = function (divId){
	 /*if($("body #"+divId).length>1){
		var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
		var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器  
		var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器  
		var isEdge = userAgent.indexOf("Trident/7.0;") > -1 && !isIE; //判断是否IE的Edge浏览器 
		if (!isIE && !isEdge){ 
			$("body #"+divId)[0].remove();
	    }
	} */
	if($("#"+divId +"  div[class='menu-item']").length<1){
		$("#"+divId).remove();
	}
}
/**
 * 为了解决右键菜单多次绑定事件---此方法可废弃
 */
Hg.removewindowshadow = function (){
	var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
	var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器  
	var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera  ; //判断是否IE浏览器
	var isEdge = userAgent.indexOf("Windows NT 6.1; Trident/7.0;") > -1 && !isIE; //判断是否IE的Edge浏览器 --不正确
	if($("body [class='panel window messager-window']").length>0){
		if (!isIE && !isEdge){  
				$("body [class='window-shadow']")[0].remove();
				$("body [class='window-mask']")[0].remove();
				$("body [class='panel window messager-window']")[0].remove(); 
		}
	}
	if($("body [class='panel window']").length>0){
		if (!isIE && !isEdge){  
				$("body [class='panel window']")[0].remove(); 
				$("body [class='window-shadow']")[0].remove();
				$("body [class='window-mask']")[0].remove();
		}
	}
}

/**
 * 单元格悬浮
 */

Hg.showGridCellTip = function(gridId, fieldNames) {
	if (!(fieldNames instanceof Array)){
		return;
	}
	//用于判断 系统信息一块
	var type =0;
	if("jvmInfo_pgrid" == gridId || "sysInfo_pgrid" == gridId || "propsInfo_pgrid" == gridId  ){
		type =1;
	}
	for (var index in fieldNames){
		var fieldName = fieldNames[index];
		var cellObj = $("#" + gridId).datagrid("getPanel").find(".datagrid-body td[field='" + fieldName + "']");
		if(type =1 && index == 0){
			type =0;
		}
		Hg.showCellObjTip(cellObj,type);
	}
};
/**
 * cell对象上显示tip
 */
Hg.showCellObjTip = function(cellObj,type) {
	var width = cellObj.width();
	if(width==0){
		width=200;
	}
	cellObj.each(function(i) {
		var text = $(this).text();
		//将字符转译,防脚本攻击yyzh
		text = text.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
		var cellObj = $(this).children()[0];
		
		width= cellObj.offsetWidth;
		if(width==0  ){
			width=200;
		}
		if(type == 1){
			width=900;
			if(i == 0){
				width=200;
			}
		}
//		if (text && cellObj.offsetWidth < cellObj.scrollWidth) {
		if (text  && (text.length>12 || cellObj.offsetWidth < cellObj.scrollWidth)) {
			var content = "<div style='width:"+width+"px;word-wrap: break-word; word-break: normal; '>"+text+"</div>";
			$(this).tooltip({
				content : content,
				position :"top",
				onShow:function(){
					var tip_ = $(this);
					tip_.tooltip('tip').unbind().bind('mouseenter', function(){
						tip_.tooltip('show');
					}).bind('mouseleave', function(){
						tip_.tooltip('hide');
					});
				}
			});
		}
	});
}
 
Hg.showErrorMsg = function(formId, errorJson) {
	// 系统错误
	if (!errorJson.match("^\{(.+:.+,*){1,}\}$")) {
		$.messager.alert("操作失败", errorJson);
	} else {// 校验错误
		var errorResult = $.parseJSON(errorJson);
		if (errorResult.field) {
			var field = errorResult.field;
			var message = errorResult.message;
			var fidldElm = $("#" + formId + " [name='" + field + "']");
			fidldElm.after("<div><label class='error' for='" + field
					+ "' generated='true'>" + message + "</label></div>");
			fidldElm.keydown(function() {
				$(this).parent().find("label[for='" + field + "']").remove();
			});
		}
	}
}

Hg.showVaildMsg = function(formId, errorJson, win) {

	// 销毁tip对象
	var bindDestoryTip = function(vaild) {
		if (win) {
			win.window({
				onClose : function() {
					vaild.destory(vaild.tip);
				}
			});
		}
	};

	var errorResult = $.parseJSON(errorJson);
	// 数组---------------------
	if ($.isArray(errorResult)) {
		$.each(errorResult, function() {
			if (this.field) {
				bindDestoryTip($(
						"#" + formId + " input[name='" + this.field + "']")
						.showVaildTip(this.message));
			}

		});
	} else {
		if (errorResult.field) {
			bindDestoryTip($(
					"#" + formId + " input[name='" + errorResult.field + "']")
					.showVaildTip(errorResult.message));
		}
	}

}
 
 
 

Hg.defaultAjaxError = function(XMLHttpRequest, textStatus, thrownError, callback){
	if (XMLHttpRequest.status==500){
        var responseJson = JSON.parse(XMLHttpRequest.responseText);
        //console.log(responseJson.data);
        //TODO 防止重复弹窗
        Hg.error(responseJson.data,callback);
    } else if (XMLHttpRequest.status==404){
    	//TODO 防止重复弹窗
        Hg.error('请求资源不存在！' , callback);
    } else if (XMLHttpRequest.status==0){
        //TODO 防止重复弹窗
        Hg.error('后台无响应,请求失败!' , callback);
    }
};
Hg.error = function(msg, callback) {
	$.messager.alert("提示", "&nbsp;&nbsp;<div style='float:left;'><img src='" + G_CTX_PATH
			+ "/static/images/error.png' /></div>&nbsp;&nbsp;" + "<div style='float:left;height:40px;line-height:40px;'>&nbsp;&nbsp;&nbsp;&nbsp;"+msg+"</div>", null,
			callback);
};
 		
