var workflow = {};

workflow.showProcessTraceByBK = function(name, pdId, businessKey) {
	var processTraceWin = new HgWin({
		id : "processTrace",
		title : "流程实例追踪-" + name,
		width : 1180,
		height : 550,
		iconCls : 'm-icon-control-play-blue',
		url : "/workflow/form/process-instance/showTrace/" + pdId + "/"
				+ businessKey
	});
};

workflow.showProcessTraceByPid = function(name, pdId, processInstanceId) {
	var processTraceWin = new HgWin({
		id : "processTrace",
		title : "流程实例追踪-" + name,
		width : 1180,
		height : 550,
		iconCls : 'm-icon-control-play-blue',
		url : "/workflow/process-instance/showTrace/" + pdId + "/"
				+ processInstanceId
	});
};


workflow.showProcessTrace = function(name, processInstanceId) {
	var processTraceWin = new HgWin({
		id : "processTrace",
		title : "流程实例追踪-" + name,
		width : 1180,
		height : 550,
		iconCls : 'm-icon-control-play-blue',
		url : "/workflow/process-instance/showTracePid/"
				+ processInstanceId
	});
};