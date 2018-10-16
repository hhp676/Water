var processFinished = {};
processFinished.initProcessShow = function() {
	Hg.fixTableHeight("processFinished_grid", true);
	Hg.initFreshPanel("processFinished_grid");
	
	// ----------------------------------查询-----------------------------------------------------
	$("#processFinished_toolbar [tag='search']").click(function() {
		$('#processFinished_grid').datagrid('load', {
			startUserId : $("#processFinished_toolbar [name='startUserId']").val(),
			processDefinitionId : $("#processFinished_toolbar [name='processDefinitionId']").val(),
			startStartTime : $("#processFinished_toolbar [name='startStartTime']").val(),
			startEndTime : $("#processFinished_toolbar [name='startEndTime']").val(),
			endStartTime : $("#processFinished_toolbar [name='endStartTime']").val(),
			endEndTime : $("#processFinished_toolbar [name='endEndTime']").val(),
			businessKey : $("#processFinished_toolbar [name='businessKey']").val()
		});
	});

	$('#processFinishedSearchForm').validate({
		rules:{
			id: { digits:true}
		}
	});
	
	// ----------------------------------清空-----------------------------------------------------
	$("#processFinished_toolbar [tag='clear']").click(function() {
		$('#processFinishedSearchForm').form('clear');
		$('#processFinished_grid').datagrid("load", {});
	});	
};

processFinished.detailFormatter = function(index, row) {
	return '<div class="ddv" style="padding:5px 0"></div>';
};

processFinished.expandRow = function(index, row) {
	var ddv = $('#processFinished_grid').datagrid('getRowDetail', index).find('div.ddv');
	ddv.panel({
		height : 150,
		border : false,
		cache : false,
		href : G_CTX_PATH + '/workflow/form/process-instance/gridDetail/'
				+ row.processDefinitionId + '/' + row.businessKey,
		onLoad : function() {
			$('#processFinished_grid').datagrid('fixDetailRowHeight', index);
		}
	});
	$('#processFinished_grid').datagrid('fixDetailRowHeight', index);
};

processFinished.onRowContextMenu = function(e, row) {
//	e.preventDefault();
//	$('#processFinished_grid').datagrid("selectRow", row);
//	$('#processFinished_contextMenu').menu('show', {
//		left : e.pageX,
//		top : e.pageY
//	});
};

processFinished.onDblClickRow = function() {
	//$("#processFinished_toolbar [tag='view']").click();
};

processFinished.drnLinkInit = function(value, row, index) {
	var url = '/activiti/resource/read?processDefinitionId='
			+ row.processDefinitionId + '&resourceType=image';
	return '<a href="javascript:HgTab(\'流程定义图片' + row.processDefinitionId
			+ '\', \'' + url + '\');">' + row.processDefinitionId + '</a>';
};

processFinished.operateLinkInit = function(value, row, index) {
	return '<a class="l-btn l-btn-plain" href="javascript:workflow.showProcessTraceByBK(\'' + row.name
	+ '\', \'' + row.processDefinitionId + '\', \'' + row.businessKey + '\');" tag="add" plain="true"><span class="l-btn-left"><span class="l-btn-text m-icon-control-play-blue l-btn-icon-right">追踪</span></span></a>';
};

processFinished.initProcessShow();