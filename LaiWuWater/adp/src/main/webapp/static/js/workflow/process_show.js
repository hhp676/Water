var process = {};
process.initProcessShow = function() {
	Hg.fixTableHeight("process_grid", true);
	Hg.initFreshPanel("process_grid");

	// ----------------------------------查询-----------------------------------------------------
	$("#process_toolbar [tag='search']").click(function() {
		$('#process_grid').datagrid('load', {
			key : $("#process_toolbar [name='key']").val(),
			name : $("#process_toolbar [name='name']").val(),
			version : $("#process_toolbar [name='version']").val()
		});
	});

	// ----------------------------------清空-----------------------------------------------------
	$("#process_toolbar [tag='clear']").click(function() {
		$('#processSearchForm').form('clear');
		$('#process_grid').datagrid("load", {});
	});
};

process.onRowContextMenu = function(e, row) {
	e.preventDefault();
	$('#process_grid').datagrid("selectRow", row);
	$('#process_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};

process.onDblClickRow = function() {
	$("#process_toolbar [tag='view']").click();
};

process.rnLinkInit = function(value, row, index) {
	var url = '/activiti/resource/read?processDefinitionId=' + row.id
			+ '&resourceType=xml';
	return '<a href="' + G_CTX_PATH + url + '" target="_blank">'
			+ row.resourceName + '</a>';
};

process.drnLinkInit = function(value, row, index) {
	var url = '/activiti/resource/read?processDefinitionId=' + row.id
			+ '&resourceType=image';
	return '<a href="javascript:HgTab(\'流程定义图片' + row.id + '\', \'' + url
			+ '\');">' + row.diagramResourceName + '</a>';
};

process.operateLinkInit = function(value, row, index) {
	if (!row.lastVersion) { //非最后一个版本
		return "";
	}
	if(!row.hasPermit){//无权限
		return "";
	}
	return '<a class="l-btn l-btn-plain" href="javascript:process.showStartProcess(\''
			+ row.key
			+ '\');" tag="add" plain="true"><span class="l-btn-left"><span class="l-btn-text m-icon-control-play-blue l-btn-icon-right">发起流程</span></span></a>';
};

process.showStartProcess = function(key) {
	var processStartWin = new HgWin({
		id : "processStartWin",
		title : "发起流程",
		width : 800,
		height : 500,
		iconCls : 'm-icon-control-play-blue',
		url : "/workflow/form/showStart/" + key
	});
};

process.initProcessShow();
