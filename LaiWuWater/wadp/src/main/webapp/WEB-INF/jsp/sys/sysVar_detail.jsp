<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>

<!-- PAGE CONTENT BEGINS -->
<div class="page-header">
	<h1>系统变量</h1>
</div>
<form method='post' id="sysVar_form" class="well form-horizontal">
	<div class="form-group">
		<input type="hidden" name="varId" value="${varId}"/>
		<label class="col-sm-3 control-label no-padding-right" for="varName"> 变量名称*:</label>
		<div class="col-sm-9">
			<input type="text" name="varName" placeholder="" value="${sysVariable.varName}" class="col-xs-10 col-sm-5" check="require" msg="请输入变量名称"/>
		</div>
		
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="varCode"> 变量Code*： </label>
		<div class="col-sm-9">
			<input type="text" name="varCode" placeholder="" value="${sysVariable.varCode}" class="col-xs-10 col-sm-5" check="require" msg="请输入变量code"/>
		</div>		
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="varValue"> 变量值*： </label>
		<div class="col-sm-9">
			<input type="text" name="varValue" placeholder="" value="${sysVariable.varValue}" class="col-xs-10 col-sm-5" check="require" msg="请输入变量值"/>
		</div>		
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="varType"> 变量类型： </label>
		<div class="col-sm-9">
			<input type="text" name="varType" placeholder="" value="${sysVariable.varValue}" class="col-xs-10 col-sm-5"/>
		</div>		
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label no-padding-right" for="descr"> 描述： </label>
		<div class="col-sm-9">
			<textarea type="text" name="descr" placeholder=""  class="col-xs-10 col-sm-5" rows="4">${sysVariable.descr}</textarea>
		</div>		
	</div>
	
	<div class="action" tag="btn">
		<input class="btn btn-sm btn-primary" tag="save" type="button" value="保存"/>
		<input class="btn btn-sm btn-default" tag="cancel" type="button" value="取消"/>
	</div>
	
	<div class="action" tag="btn2" style="display: none;">
		<input class="btn btn-sm btn-default" tag="cancel" type="button" value="<-返回"/>
	</div>

	
</form>
<script type="text/javascript">
	var varId = "${varId}";
	var mode = "${mode}";
	if (mode == "view") {
		$("#sysVar_form [tag='btn']").hide();
		$("#sysVar_form [tag='btn2']").show();
	}
	Hg.refRepeatSubmit("sysVar_form");
	$("#sysVar_form [tag='cancel']").click(function(){		
		window.location.href = "${ctx}/sys/var/showSysVar";
	});
	
	$("#sysVar_form [tag='save']").click(function(){
		var url = "/sys/var/add";
		if (varId != 0) url = "/sys/var/edit";
		if (check_form("sysVar_form")) {
			Hg.getJson(url,$("#sysVar_form").serializeArray(),function(data){
				if (data.success) {
					ui_alert("保存成功！",function(){
						window.location.href = "${ctx}/sys/var/showSysVar";
					});
				} else {
					Hg.refreshSubmitToken("sysVar_form");
					ui_error(data.data);
				}				
			});
		}
	});
			
</script>
<!-- PAGE CONTENT ENDS -->
