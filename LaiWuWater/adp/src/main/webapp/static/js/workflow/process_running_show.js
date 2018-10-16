var processRunning = {};
processRunning.initProcessShow = function() {
	Hg.fixTableHeight("processRunning_grid", true);
	Hg.initFreshPanel("processRunning_grid");
	
	// ----------------------------------查询-----------------------------------------------------
	$("#processRunning_toolbar [tag='search']").click(function() {
		$('#processRunning_grid').datagrid('load', {
			processDefinitionId : $("#processRunning_toolbar [name='processDefinitionId']").val(),
			processInstanceId : $("#processRunning_toolbar [name='processInstanceId']").val(),
			businessKey : $("#processRunning_toolbar [name='businessKey']").val()
		});
	});

	// ----------------------------------清空-----------------------------------------------------
	$("#processRunning_toolbar [tag='clear']").click(function() {
		$('#processRunningSearchForm').form('clear');
		$('#processRunning_grid').datagrid("load", {});
	});	
};

processRunning.detailFormatter = function(index, row) {
	return '<div class="ddv" style="padding:5px 0"></div>';
};

processRunning.expandRow = function(index, row) {
	var ddv = $('#processRunning_grid').datagrid('getRowDetail', index).find('div.ddv');
	ddv.panel({
		height : 150,
		border : false,
		cache : false,
		href : G_CTX_PATH + '/workflow/form/process-instance/gridDetail/'
				+ row.processDefinitionId + '/' + row.businessKey,
		onLoad : function() {
			$('#processRunning_grid').datagrid('fixDetailRowHeight', index);
		}
	});
	$('#processRunning_grid').datagrid('fixDetailRowHeight', index);
};

processRunning.onRowContextMenu = function(e, row) {
//	e.preventDefault();
//	$('#processRunning_grid').datagrid("selectRow", row);
//	$('#processRunning_contextMenu').menu('show', {
//		left : e.pageX,
//		top : e.pageY
//	});
};

processRunning.onDblClickRow = function() {
	var index = $("#processRunning_grid").datagrid("getRowIndex");
	var row = $("#processRunning_grid").datagrid("getSelected");
	processRunning.expandRow(index, row);
};

processRunning.drnLinkInit = function(value, row, index) {
	var url = '/activiti/resource/read?processDefinitionId='
			+ row.processDefinitionId + '&resourceType=image';
	return '<a href="javascript:HgTab(\'流程定义图片' + row.processDefinitionId
			+ '\', \'' + url + '\');">' + row.processDefinitionId + '</a>';
};

processRunning.operateLinkInit = function(value, row, index) {
	return '<a class="l-btn l-btn-plain" href="javascript:workflow.showProcessTraceByBK(\'' + row.name
	+ '\', \'' + row.processDefinitionId + '\', \'' + row.businessKey + '\');" tag="add" plain="true"><span class="l-btn-left"><span class="l-btn-text m-icon-control-play-blue l-btn-icon-right">追踪</span></span></a>';
};

processRunning.initProcessShow();