var processJoined = {};
processJoined.initProcessShow = function() {
	Hg.fixTableHeight("processJoined_grid", true);
	Hg.initFreshPanel("processJoined_grid");

	// ----------------------------------查询-----------------------------------------------------
	$("#processJoined_toolbar [tag='search']")
			.click(
					function() {
						$('#processJoined_grid')
								.datagrid(
										'load',
										{
											startUserId : $(
													"#processJoined_toolbar [name='startUserId']")
													.val(),
											processDefinitionId : $(
													"#processJoined_toolbar [name='processDefinitionId']")
													.val(),
											startStartTime : $(
													"#processJoined_toolbar [name='startStartTime']")
													.val(),
											startEndTime : $(
													"#processJoined_toolbar [name='startEndTime']")
													.val(),
											endStartTime : $(
													"#processJoined_toolbar [name='endStartTime']")
													.val(),
											endEndTime : $(
													"#processJoined_toolbar [name='endEndTime']")
													.val(),
											businessKey : $(
													"#processJoined_toolbar [name='businessKey']")
													.val()
										});
					});

	$('#processJoinedSearchForm').validate({
		rules : {
			id : {
				digits : true
			}
		}
	});

	// ----------------------------------清空-----------------------------------------------------
	$("#processJoined_toolbar [tag='clear']").click(function() {
		$('#processJoinedSearchForm').form('clear');
		$('#processJoined_grid').datagrid("load", {});
	});
};

processJoined.detailFormatter = function(index, row) {
	return '<div class="ddv" style="padding:5px 0"></div>';
};

processJoined.expandRow = function(index, row) {
	var ddv = $('#processJoined_grid').datagrid('getRowDetail', index).find(
			'div.ddv');
	ddv.panel({
		height : 150,
		border : false,
		cache : false,
		href : G_CTX_PATH + '/workflow/form/process-instance/gridDetail/'
				+ row.processDefinitionId + '/' + row.businessKey,
		onLoad : function() {
			$('#processJoined_grid').datagrid('fixDetailRowHeight', index);
		}
	});
	$('#processJoined_grid').datagrid('fixDetailRowHeight', index);
};

processJoined.onRowContextMenu = function(e, row) {
	// e.preventDefault();
	// $('#processJoined_grid').datagrid("selectRow", row);
	// $('#processJoined_contextMenu').menu('show', {
	// left : e.pageX,
	// top : e.pageY
	// });
};

processJoined.onDblClickRow = function() {
	// $("#processJoined_toolbar [tag='view']").click();
};

processJoined.drnLinkInit = function(value, row, index) {
	var url = '/activiti/resource/read?processDefinitionId='
			+ row.processDefinitionId + '&resourceType=image';
	return '<a href="javascript:HgTab(\'流程定义图片' + row.processDefinitionId
			+ '\', \'' + url + '\');">' + row.processDefinitionId + '</a>';
};

processJoined.operateLinkInit = function(value, row, index) {
	return '<a class="l-btn l-btn-plain" href="javascript:workflow.showProcessTraceByBK(\''
			+ row.name
			+ '\', \''
			+ row.processDefinitionId
			+ '\', \''
			+ row.businessKey
			+ '\');" tag="add" plain="true"><span class="l-btn-left"><span class="l-btn-text m-icon-control-play-blue l-btn-icon-right">追踪</span></span></a>';
};

processJoined.initProcessShow();