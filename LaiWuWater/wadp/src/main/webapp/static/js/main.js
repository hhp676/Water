/*-----------------------------------------------------------------------------
| 默认设置
------------------------------------------------------------------------------*/
var defaultSetting = {
		pageSize : 10
};


/*-----------------------------------------------------------------------------
| 全局变量
------------------------------------------------------------------------------*/
var  G_V = {};












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
	$("#"+targetId).append("<div id='"+targetId+"_loadMsg'><img  src='"+G_CTX_PATH+"/static/images/loading.gif' />数据加载中...</div>");
}

function rmLoadMsg(targetId) {
	$("#"+targetId+"_loadMsg").remove();
}


var G_VAL = {

}



//Hg 构造器
var Hg = {

}



Hg.mrkPageList = function(pageContainerId, totalCount,calBack) {
	var totalPageNum =  calPageTotalNum(totalCount, defaultSetting.pageSize);
	var $page = $("#"+pageContainerId+" ul.pagination").empty();
	$page.append("<li class='prev disabled' tag='go'><a href='#'><i class='icon-double-angle-left'></i></a></li>");
	for (var i=0; i<totalPageNum; i++) {
		if (i==3) break;
		var classStr = "";
		if (i==0) classStr = "class='active'";
		$page.append("<li "+classStr+" tag='num'><a href='#'>"+(i+1)+"</a></li>");
	}
	$page.append("<li class='next' tag='go'><a href='#'><i class='icon-double-angle-right'></i></a></li>");
	$page.find("li").bind("click",false);
	$page.find("li[tag='num']").click(function(){
		var num = $(this).text();
		if (num != 1) {
			$page.find("li.prev").removeClass("disabled");
		}
		if (num == totalPageNum) {
			$page.find("li.next").addClass("disabled");
		}
		$page.find("li[tag='num']").removeClass("active");
		$(this).addClass("active");
		calBack($(this).text());
	});
	$page.find("li.prev").click(function(){
		$page.find("li[tag='num'].active").prev().click();
	});
	$page.find("li.next").click(function(){
		$page.find("li[tag='num'].active").next().click();
	});
	
}


Hg.makeComboAry = function(keyStr,valStr,hasNullRow,selectedValue){
	var ary = new Array();
	var aryKey = [];
	var aryVal = [];
	if (keyStr != "") aryKey = keyStr.split("$$$");
	if (valStr != "") aryVal = valStr.split("$$$");
	if (hasNullRow) ary.push({text:"---请选择---",value:-1});
	for (var i=0; i<aryKey.length; i++) {
		var obj = {};
		obj.value = aryKey[i];
		obj.text = aryVal[i];
		if (typeof selectedValue != 'undefined') {
			if (aryKey[i] == selectedValue) obj.selected = true;
		}
		ary.push(obj);

	}

	return ary;
}

Hg.checkSelectOptionExist = function(option,value) {
	for (var i=0;i<option.length;i++) {
		if (option[i].value == value) {
			return {item:new Option(option[i].text,option[i].value),index:i};
		}
	}
	return null;
}
Hg.getSelectOptionStr = function(options) {
	var aryValue = new Array();
	var aryText = new Array();
	for (var i=0;i<options.length;i++) {
		aryValue.push(options[i].value);
		aryText.push(options[i].text);
	}
	return {values:aryValue.join(","),texts:aryText.join(",")};
}






//ajax获取json数据
Hg.getJson = function(path,data,callback){
	$.getJSON(G_CTX_PATH+path+"?etc="+new Date().getTime(),data,callback);
}

//post请求（大数据请求json无法处理时,用此方法）,POST 请求功能以取代复杂 $.ajax 。请求成功时可调用回调函数。如果需要在出错时执行函数，请使用 $.ajax。
Hg.post = function(path,data,callback){
	$.post(G_CTX_PATH+path+"?etc="+new Date().getTime(),data,callback);
}



//防止表单重复提交
Hg.refRepeatSubmit = function(formId) {
	$("#"+formId).append("<input type='hidden' name='submitToken'/>");
	Hg.getJson("/refSubmitToken",{},function(data){
		$("#"+formId).find("[name='submitToken']").val(data.data);
	});
}

//刷新提交token
Hg.refreshSubmitToken = function(formId) {
	Hg.getJson("/refSubmitToken",{},function(data){
		$("#"+formId).find("[name='submitToken']").val(data.data);
	});
}






/*-----------------------------------------------------------------------------
|  初始化
------------------------------------------------------------------------------*/











/*-----------------------------------------------------------------------------
|  公共函数
------------------------------------------------------------------------------*/
String.format = function() {
    if( arguments.length == 0 )
        return null;

    var str = arguments[0];
    for(var i=1;i<arguments.length;i++) {
        var re = new RegExp('\\{' + (i-1) + '\\}','gm');
        str = str.replace(re, arguments[i]);
    }
    return str;
}


//字符串连接
function StringBuffer(str){
    this.tmp = new Array();
}
StringBuffer.prototype.append= function(value){
    this.tmp.push(value);
    return this;
}
StringBuffer.prototype.clear = function(){
    tmp.length=1;
}
StringBuffer.prototype.toString = function(){
    return this.tmp.join('');
}

Array.prototype.find = function(val){
    for(var i = 0, len = this.length; i < len; i++){
        if( this[i] == val ){
            return this[i];
        }
    }
    return null;
}

