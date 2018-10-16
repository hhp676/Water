/**
 * easyui 自定义扩展。
 */
(function(){
	
	var defaultAjaxError = function (XMLHttpRequest, textStatus, thrownError){
		HgUtil.defaultAjaxError.apply(this,arguments);
	};
	/**
	 * easyui 异常默认方法重写。
	 */
	$.fn.tree.defaults.onLoadError = defaultAjaxError;
	$.fn.panel.defaults.onLoadError = defaultAjaxError;
	$.fn.form.defaults.onLoadError = defaultAjaxError;
	$.fn.datagrid.defaults.onLoadError = defaultAjaxError;
	$.fn.treegrid.defaults.onLoadError = defaultAjaxError;
	$.fn.combobox.defaults.onLoadError = defaultAjaxError;

  
})()