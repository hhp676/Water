var taskToClaim = {};
taskToClaim.initProcessShow = function() {
	Hg.fixTableHeight("taskToClaim_grid", true);
	Hg.initFreshPanel("taskToClaim_grid");


	// ----------------------------------查询-----------------------------------------------------
	$("#taskToClaim_toolbar [tag='search']")
			.click(
					function() {
						$('#taskToClaim_grid')
								.datagrid(
										'load',
										{
											name : $(
													"#taskToClaim_toolbar [name='name']")
													.val(),
											startStartTime : $(
													"#taskToClaim_toolbar [name='startStartTime']")
													.val(),
											startEndTime : $(
													"#taskToClaim_toolbar [name='startEndTime']")
													.val()
										});
					});

	// ----------------------------------清空-----------------------------------------------------
	$("#taskToClaim_toolbar [tag='clear']").click(function() {
		$('#taskToClaimSearchForm').form('clear');
		$('#taskToClaim_grid').datagrid("load", {});
	});

	// ----------------------------------认领任务-----------------------------------------------------
};

taskToClaim.detailFormatter = function(index, row) {
	return '<div class="ddv" style="padding:5px 0"></div>';
};

taskToClaim.expandRow = function(index, row) {
	var ddv = $('#taskToClaim_grid').datagrid('getRowDetail', index).find(
			'div.ddv');
	ddv.panel({
		height : 150,
		border : false,
		cache : false,
		href : G_CTX_PATH + '/workflow/form/process-instance/gridDetail/'
				+ row.processDefinitionId + '_' + row.processInstanceId,
		onLoad : function() {
			$('#taskToClaim_grid').datagrid('fixDetailRowHeight', index);
		}
	});
	$('#taskToClaim_grid').datagrid('fixDetailRowHeight', index);
};

taskToClaim.onRowContextMenu = function(e, row) {
	e.preventDefault();
	$('#taskToClaim_grid').datagrid("selectRow", row);
	$('#taskToClaim_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};

taskToClaim.onDblClickRow = function() {
	$("#taskToClaim_toolbar [tag='view']").click();
};

taskToClaim.drnLinkInit = function(value, row, index) {
	var url = '/activiti/resource/read?processDefinitionId='
			+ row.processDefinitionId + '&resourceType=image';
	return '<a href="javascript:HgTab(\'流程定义图片' + row.processDefinitionId
			+ '\', \'' + url + '\');">' + row.processDefinitionId + '</a>';
};

taskToClaim.operateLinkClaimInit = function(value, row, index) {
	return '<a class="l-btn l-btn-plain" href="javascript:taskToClaim.doClaim(\''
			+ row.id
			+ '\');" tag="add" plain="true"><span class="l-btn-left"><span class="l-btn-text m-icon-control-play-blue l-btn-icon-right">认领</span></span></a>'
			+ '<a class="l-btn l-btn-plain" href="javascript:workflow.showProcessTraceByPid(\''
			+ row.name
			+ '\', \''
			+ row.processDefinitionId
			+ '\', \''
			+ row.processInstanceId
			+ '\');" tag="add" plain="true"><span class="l-btn-left"><span class="l-btn-text m-icon-control-play-blue l-btn-icon-right">追踪</span></span></a>';
};

taskToClaim.doClaim = function(id) {
	$.messager.confirm('认领任务', '您确认认领该任务?', function(r) {
		if (r) {
			$("#taskToClaim_grid").block();
			// 提交数据--------------------------------------------------
			var submitUrl = "/workflow/task/doClaim";
			Hg.getJson(submitUrl, {
				"taskId" : id
			}, function(data) {
				if (!data.success) {
					$("#taskToClaim_grid").unblock();
					$.messager.alert('error', data.data);
				} else {
					$.messager.ok("认领任务成功!", function() {
						$("#taskToClaim_grid").unblock();
						$('#taskToClaim_grid').datagrid("reload");
					});
				}
			});
		}
	});
};

taskToClaim.initProcessShow();
