<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>

<!-- PAGE CONTENT BEGINS -->
<div class="page-header">
	<h1>设置系统配置</h1>
</div>
<form method='post' id="sysCfg_form" class="well form-horizontal">
	
	
	<div class="action" tag="btn">
		<input class="btn btn-sm btn-primary" tag="save" type="button" value="保存">
		<input class="btn btn-sm btn-default" tag="reset" type="button" value="重置">
	</div>

	
</form>
<script type="text/javascript">
	$("#sysCfg_form [tag='save']").click(function(){
		if (check_form("sysCfg_form")) {
			var newAry = [];
			$("#sysCfg_form input:not(:disabled).form-control").each(function(index,ele){
				var configKey = $(ele).attr("name");
				var configValue = $(ele).val();
				newAry.push({configKey:configKey,configValue:configValue});
			});
			
			Hg.getJson("/sys/cfg/batchUpdate",{jsonListStr:$.toJSON(newAry)},function(data){
				ui_alert("保存成功！",function(){
					
				});
			});
		}
	});
	$("#sysCfg_form [tag='reset']").click(function(){
		Hg.getJson("/sys/cfg/reset",{},function(data){
			if (data.success) {
				ui_alert("重置系统配置信息成功!",function(){
					location.reload();
				});
			} else {
				ui_error(data.data);
			}
		});
	});
	Hg.getJson("/sys/cfg/list",{},function(data){
		var data = data.data;
		for (var i=0;i<data.length;i++){
			var str = new StringBuffer();
			var key = data[i].configKey;
			var desc = data[i].configDesc;
			var value = data[i].configValue;
			var disabled = "";
			if (data[i].isFinal == 1) {
				disabled = "disabled";
			}
			str.append("<div  class='form-group col-xs-12'>");
			str.append("    <label class='col-xs-6 col-sm-4 control-label' for='"+key+"'>"+key+"：</label>");
			str.append("    <div class='col-xs-6 col-sm-4'>");
			str.append("        <input class='form-control' type='text'  value='"+value+"' name='"+key+"'check='require' msg='请输入"+desc+"' "+disabled+">");
			str.append("    </div>");
			str.append("    <label class='col-xs-12 col-sm-4' for='"+key+"'>"+desc+"</label>");
			str.append("</div>");
			$("#sysCfg_form").prepend(str.toString());
		}
	
	});
</script>
<!-- PAGE CONTENT ENDS -->
