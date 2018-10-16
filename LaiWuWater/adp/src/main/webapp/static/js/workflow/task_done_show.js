var taskDone = {};
taskDone.initProcessShow = function() {
	Hg.fixTableHeight("taskDone_grid", true);
	Hg.initFreshPanel("taskDone_grid");
	
	// ----------------------------------查询-----------------------------------------------------
	$("#taskDone_toolbar [tag='search']")
			.click(
					function() {
						$('#taskDone_grid')
								.datagrid(
										'load',
										{
											name : $(
													"#taskDone_toolbar [name='name']")
													.val(),
											startStartTime : $(
													"#taskDone_toolbar [name='startStartTime']")
													.val(),
											startEndTime : $(
													"#taskDone_toolbar [name='startEndTime']")
													.val()
										});
					});

	// ----------------------------------清空-----------------------------------------------------
	$("#taskDone_toolbar [tag='clear']").click(function() {
		$('#taskDoneSearchForm').form('clear');
		$('#taskDone_grid').datagrid("load", {});
	});
};

taskDone.detailFormatter = function(index, row) {
	return '<div class="ddv" style="padding:5px 0"></div>';
};

taskDone.expandRow = function(index, row) {
	var ddv = $('#taskDone_grid').datagrid('getRowDetail', index).find('div.ddv');
	ddv.panel({
		height : 150,
		border : false,
		cache : false,
		href : G_CTX_PATH + '/workflow/form/process-instance/gridDetail/'
				+ row.processDefinitionId + '_' + row.processInstanceId,
		onLoad : function() {
			$('#taskDone_grid').datagrid('fixDetailRowHeight', index);
		}
	});
	$('#taskDone_grid').datagrid('fixDetailRowHeight', index);
};

taskDone.onRowContextMenu = function(e, row) {
	e.preventDefault();
	$('#taskDone_grid').datagrid("selectRow", row);
	$('#taskDone_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};

taskDone.onDblClickRow = function() {
	$("#taskDone_toolbar [tag='view']").click();
};

taskDone.drnLinkInit = function(value, row, index) {
	var url = '/activiti/resource/read?processDefinitionId='
			+ row.processDefinitionId + '&resourceType=image';
	return '<a href="javascript:HgTab(\'流程定义图片' + row.processDefinitionId
			+ '\', \'' + url + '\');">' + row.processDefinitionId + '</a>';
};

taskDone.operateLinkInit = function(value, row, index) {
	return '<a class="l-btn l-btn-plain" href="javascript:workflow.showProcessTraceByPid(\''
			+ row.name
			+ '\', \''
			+ row.processDefinitionId
			+ '\', \''
			+ row.processInstanceId
			+ '\');" tag="add" plain="true"><span class="l-btn-left"><span class="l-btn-text m-icon-control-play-blue l-btn-icon-right">追踪</span></span></a>';
};


taskDone.initProcessShow();
