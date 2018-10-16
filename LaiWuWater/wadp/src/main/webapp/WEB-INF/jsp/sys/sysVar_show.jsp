<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>

<!-- PAGE CONTENT BEGINS -->


<div class="row">
	<div class="col-xs-12">
		<div class="table-header">
			系统参数列表 
		</div>
		<div class="table-responsive">
			<div class="dataTables_wrapper" id="sample-table-2_wrapper"
				role="grid">
				<div class="row">
					<div class="col-sm-6">
						<div class="pull-left">
							<a class="btn btn-sm btn-info" id="addBtn">新建</a>
						</div>
					</div>
					
				</div>
				<table
					class="table table-striped table-bordered table-hover dataTable"
					id="sysVar_grid" aria-describedby="sample-table-2_info">
					<thead>
						<tr role="row">
		
							<th tabindex="0"  role="columnheader"
								aria-controls="sample-table-2" style="width: 30px;"
								rowspan="1" colspan="1">ID</th>
							<th tabindex="0" role="columnheader"
								aria-controls="sample-table-2" style="width: 125px;"
								rowspan="1" colspan="1">变量名称</th>
							<th tabindex="0" role="columnheader"
								aria-controls="sample-table-2" style="width: 138px;"
								rowspan="1" colspan="1">变量code</th>
							<th tabindex="0"  role="columnheader"
								aria-controls="sample-table-2" style="width: 195px;"> 变量类型</th>
							<th tabindex="0" s role="columnheader"
								aria-controls="sample-table-2" style="width: 147px;"
								rowspan="1" colspan="1">变量值</th>
							<th tabindex="0"  role="columnheader"
								aria-controls="sample-table-2" style="width: 147px;"
								rowspan="1" colspan="1">操作</th>
						</tr>
					</thead>


					<tbody role="alert" aria-live="polite" aria-relevant="all">
				
					</tbody>
				</table>
				<div class="row">
					<div class="col-xs-12">
						<div class="dataTables_paginate paging_bootstrap" id="sysVar_page">
							<ul class="pagination">
								
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#addBtn").click(function(){
		window.location.href = "${ctx}/sys/var/sysVarDetail/0/add";
	});

	function mrkSysVarList(page){
		$("#sysVar_grid tbody").empty();
		Hg.getJson("/sys/var/list",{page:page,rows:defaultSetting.pageSize},function(data){
			var total = data.total;
			var data = data.rows;
			if (page == 1) {
				Hg.mrkPageList("sysVar_page",total,function(currentPage){
					mrkSysVarList(currentPage);
				});
			}			
			for (var i=0;i<data.length;i++){
				var str = new StringBuffer();
				str.append("<tr class='odd'>");
				str.append("    <td tag='id'>"+data[i].varId+"</td>");
				str.append("    <td>"+data[i].varName+"</td>");
				str.append("    <td>"+data[i].varCode+"</td>");
				str.append("    <td>"+data[i].varType+"</td>");
				str.append("    <td>"+data[i].varValue+"</td>");
				str.append("    <td>");
				str.append("        <div class='visible-md visible-lg hidden-sm hidden-xs action-buttons'>");
				str.append("            <a class='blue' href='javascript:;' tag='view' onclick='sysVarView(this);'> <i class='icon-zoom-in bigger-130'></i></a> ");
				str.append("            <a class='green' href='javascript:;' onclick='sysVarDoEdit(this);' tag='edit'> <i class='icon-pencil bigger-130'></i></a>");
				str.append("            <a class='red' href='javascript:;' tag='del' onclick='sysVarDoDel(this);'> <i class='icon-trash bigger-130'></i></a>");
				str.append("        </div>");
				str.append("    </td>");
				str.append("</tr>");			
				$("#sysVar_grid tbody").append(str.toString());
				
			}
		});
	};
	mrkSysVarList(1);
	
	
	function sysVarDoEdit(source){
		var id = $(source).parents("tr").find("[tag=id]").text();
		window.location.href = "${ctx}/sys/var/sysVarDetail/"+id+"/edit";
		
	};
	
	function sysVarView(source){
		var id = $(source).parents("tr").find("[tag=id]").text();
		window.location.href = "${ctx}/sys/var/sysVarDetail/"+id+"/view";
		
	};
	
	
	function sysVarDoDel(source){
		var id = $(source).parents("tr").find("[tag=id]").text();
		ui_confirm("确定删除？",function(){
			Hg.getJson("/sys/var/delete",{id:id},function(data){
				if (data.success) {
					ui_alert("删除成功！",function(){
						location.reload();
					});					
				} else {
					ui_error("删除失败："+data.data);
				}
			});
		});
			
	};
	
</script>

<!-- PAGE CONTENT ENDS -->
<!-- PAGE CONTENT ENDS -->
