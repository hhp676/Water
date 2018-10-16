var taskToDo = {};
taskToDo.initProcessShow = function() {
	Hg.fixTableHeight("taskToDo_grid", true);
	Hg.initFreshPanel("taskToDo_grid");
	
	// ----------------------------------查询-----------------------------------------------------
	$("#taskToDo_toolbar [tag='search']")
			.click(
					function() {
						$('#taskToDo_grid')
								.datagrid(
										'load',
										{
											name : $(
													"#taskToDo_toolbar [name='name']")
													.val(),
											startStartTime : $(
													"#taskToDo_toolbar [name='startStartTime']")
													.val(),
											startEndTime : $(
													"#taskToDo_toolbar [name='startEndTime']")
													.val()
										});
					});

	// ----------------------------------清空-----------------------------------------------------
	$("#taskToDo_toolbar [tag='clear']").click(function() {
		$('#taskToDoSearchForm').form('clear');
		$('#taskToDo_grid').datagrid("load", {});
	});

};

taskToDo.detailFormatter = function(index, row) {
	return '<div class="ddv" style="padding:5px 0"></div>';
};

taskToDo.expandRow = function(index, row) {
	var ddv = $('#taskToDo_grid').datagrid('getRowDetail', index).find(
			'div.ddv');
	ddv.panel({
		height : 180,
		border : false,
		cache : false,
		href : G_CTX_PATH + '/workflow/form/process-instance/gridDetail/'
				+ row.processDefinitionId + '_' + row.processInstanceId,
		onLoad : function() {
			$('#taskToDo_grid').datagrid('fixDetailRowHeight', index);
		}
	});
	$('#taskToDo_grid').datagrid('fixDetailRowHeight', index);
};

taskToDo.onRowContextMenu = function(e, row) {
	e.preventDefault();
	$('#taskToDo_grid').datagrid("selectRow", row);
	$('#taskToDo_contextMenu').menu('show', {
		left : e.pageX,
		top : e.pageY
	});
};

taskToDo.onDblClickRow = function() {
	$("#taskToDo_toolbar [tag='view']").click();
};

taskToDo.drnLinkInit = function(value, row, index) {
	var url = '/activiti/resource/read?processDefinitionId='
			+ row.processDefinitionId + '&resourceType=image';
	return '<a href="javascript:HgTab(\'流程定义图片' + row.processDefinitionId
			+ '\', \'' + url + '\');">' + row.processDefinitionId + '</a>';
};

taskToDo.operateLinkInit = function(value, row, index) {
	return '<a class="l-btn l-btn-plain" href="javascript:taskToDo.showDoProcess(\''
			+ row.id
			+ '\', \''
			+ row.taskDefinitionKey
			+ '\', \''
			+ row.name
			+ '\','
			+ row.processInstanceId
			+ ');" tag="add" plain="true"><span class="l-btn-left"><span class="l-btn-text m-icon-control-play-blue l-btn-icon-right">办理</span></span></a>'
			+ '<a class="l-btn l-btn-plain" href="javascript:workflow.showProcessTraceByPid(\''
			+ row.name
			+ '\', \''
			+ row.processDefinitionId
			+ '\', \''
			+ row.processInstanceId
			+ '\');" tag="add" plain="true"><span class="l-btn-left"><span class="l-btn-text m-icon-control-play-blue l-btn-icon-right">追踪</span></span></a>';
};

taskToDo.showDoProcess = function(taskId, taskkey, taskName, processInstanceId) {
	var width = 600, height = 370;
	if (taskkey == Oa.TASK_KEY_RECRUIT_DEPT_LEADER_AUDIT
			|| taskkey == Oa.TASK_KEY_RECRUIT_HR_LEADER_AUDIT
			|| taskkey == Oa.TASK_KEY_RECRUIT_HR_SUBMIT_INFO
			|| taskkey == Oa.TASK_KEY_RECRUIT_HR_LEADER_COMMAND
			|| taskkey == Oa.TASK_KEY_RECRUIT_ADJUST_RECRUIT) {
		width = 900;
		height = 500;
	}
	var taskToDoWin = new HgWin({
		id : "taskToDoWin",
		title : "办理流程-" + taskName,
		width : width,
		height : height,
		iconCls : 'm-icon-control-play-blue',
		url : "/workflow/form/showToDo/" + taskkey + "/" + taskId + "/"
				+ processInstanceId
	});
};

taskToDo.initProcessShow();