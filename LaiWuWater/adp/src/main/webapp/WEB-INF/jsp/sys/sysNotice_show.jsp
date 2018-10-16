<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>系统公告列表页面</title>	
  </head>
  <body>
      <div title="系统公告列表">
          <table striped=true id="sysNotice_grid" singleSelect=true fitColumns=true  toolbar='#sysNotice_toolbar' class="easyui-datagrid"  
          rownumbers="true"
            data-options="
            		url:'${ctx}/sys/notice/list',
            		onLoadSuccess:sysNotice.onLoadSuccess"
            		pagination="true" pageSize="${defaultPageSize}"
            		pageList="${defaultPageList}">
	          <thead>
		           <tr>
                       <th data-options="field:'title',width:200">公告标题</th>
                       <th data-options="field:'startTimeStr',width:100">开始时间</th>
                       <th data-options="field:'endTimeStr',width:100">结束时间</th>
                       <th data-options="field:'statusStr',width:50">状态</th>
                       <th data-options="field:'sortId',width:50">排序Id</th>
                       <th data-options="field:'autoPubStr',width:50">自动发布</th>
                       <th data-options="field:'isFinalStr',width:50">不可修改</th>
                       <th data-options="field:'operate',width:100,formatter:formatNoticeOperate">操作</th>
                       <th data-options="field:'notifyId',width:40,hidden:true">公告Id</th>
                  </tr>
		      </thead>
	       </table>
      </div>
     
  	
    <div id="sysNotice_toolbar" class="sysVarbar">
    	<shiro:hasPermission name="<%= Auths.SYS_NOTICE_ADD %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add1" plain="true" tag="add">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_NOTICE_EDIT %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit1" plain="true" tag="edit">修改</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_NOTICE_DELETE %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-del1" plain="true" tag="del">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="<%= Auths.SYS_NOTICE_VIEW %>">
    		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="m-icon-list" plain="true" tag="view">查看详情</a>
    	</shiro:hasPermission>
    </div>
    <div id="sysNotice_contextMenu" class="easyui-menu" style="width: 120px;">
  		<div  iconCls="icon-add1" tag="add">添加</div>
  		<div  iconCls="icon-edit1" tag="edit">修改</div>
		<div  iconCls="icon-del1" tag="del">删除</div>		
	</div>
	<script type="text/javascript">
	 	function formatNoticeOperate(val,row){
	 		var id = row.notifyId;
	 		var css = "m-icon-publish";
	 		var text = "发布";
	 		if (row.status == 1) {
	 			css = "m-icon-cancel-blue";
	 			text = "取消发布";
	 		}	 		
	 		var htmlStr = new StringBuffer();
	 		htmlStr.append("<a class='l-btn l-btn-plain' href='javascript:noticePublishOrNot("+id+");'  plain='true'>");
	 		htmlStr.append("    <span class='l-btn-left'>");
	 		htmlStr.append("        <span class='l-btn-text "+css+" l-btn-icon-left'>"+text+"</span>");
	 		htmlStr.append("    </span>");
	 		htmlStr.append("</a>");
	 		return htmlStr.toString();        	
     	};
	 	
	 	function noticePublishOrNot(id) {
	 		Hg.getJson("/sys/notice/publishOrNot",{id:id},function(data){
				if (data.success) {
					$.messager.ok("操作成功!",function(){
						$('#sysNotice_grid').datagrid("reload");
					});							
				} else {
					$.messager.alert("操作失败!",data.data);
				}
			});
	 	};
	</script>
	<script type="text/javascript" src="${ctx}/static/js/sys/sysNotice_show.js"></script>
  </body>

</html>
