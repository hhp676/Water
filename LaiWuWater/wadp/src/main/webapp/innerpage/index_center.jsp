<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>ajax</title>	
  </head>
  <body>
  	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true,title:'系统面板'" style="width:200px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<div title="查询" data-options="iconCls:'icon-search',collapsed:false,collapsible:false" style="padding:5px;"><input class="easyui-searchbox" prompt="Enter something here" style="width:180px;height:20px;"></div>
                <div title="系统管理" data-options="iconCls:'m-icon-drive',selected:true">
                	<div class="panel-header acc-panel-inner" style="" src="/innerpage/user.jsp"><div class="panel-with-icon">用户管理</div><div class="panel-icon acc-panel-icon" style="background:url('<%=path%>/static/images/person1.png') no-repeat center center;"></div></div>
					<div class="panel-header acc-panel-inner" style="" src="/innerpage/role.jsp"><div class="panel-with-icon">角色管理</div><div class="panel-icon acc-panel-icon" style="background:url('<%=path%>/static/images/star.gif') no-repeat center center;"></div></div>
					<div class="panel-header acc-panel-inner" style="" src="/innerpage/userGroup.jsp"><div class="panel-with-icon">用户组管理</div><div class="panel-icon acc-panel-icon" style="background:url('<%=path%>/static/images/group.png') no-repeat center center;"></div></div>
					<div class="panel-header acc-panel-inner" style="" src="/innerpage/department.jsp"><div class="panel-with-icon">部门管理</div><div class="panel-icon acc-panel-icon" style="background:url('<%=path%>/static/images/pkg.gif') no-repeat center center;"></div></div>
					<div class="panel-header acc-panel-inner" style="" src="/innerpage/auth.jsp"><div class="panel-with-icon">权限管理</div><div class="panel-icon acc-panel-icon" style="background:url('<%=path%>/static/images/tag_green.gif') no-repeat center center;"></div></div>
					<div class="panel-header acc-panel-inner" style="" src="/innerpage/userGroup.jsp"><div class="panel-with-icon">用户日志</div><div class="panel-icon acc-panel-icon" style="background:url('<%=path%>/static/images/page_white_text.gif') no-repeat center center;"></div></div>
					<div class="panel-header acc-panel-inner" style="" src="/innerpage/DataDic.jsp"><div class="panel-with-icon">数据字典</div><div class="panel-icon acc-panel-icon" style="background:url('<%=path%>/static/images/folder_table.gif') no-repeat center center;"></div></div>
					<div class="panel-header acc-panel-inner" style="" src="/sys/info/showSysInfo"><div class="panel-with-icon">系统信息</div><div class="panel-icon acc-panel-icon" style="background:url('<%=path%>/static/images/world.gif') no-repeat center center;"></div></div>
					<div class="panel-header acc-panel-inner" style="" src="/sys/var/showSysVar"><div class="panel-with-icon">系统变量</div><div class="panel-icon acc-panel-icon" style="background:url('<%=path%>/static/images/pilcrow.gif') no-repeat center center;"></div></div>
					<div class="panel-header acc-panel-inner" style="" src="/sys/cfg/showSysCfg"><div class="panel-with-icon">系统设定</div><div class="panel-icon acc-panel-icon" style="background:url('<%=path%>/static/images/setting.png') no-repeat center center;"></div></div>
				
                </div>
               
            </div>
	    </div>
		<div data-options="region:'center'">
	      <div id="tab" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
				<div title="首页"  data-options="href:'<%=path%>/innerpage/home.jsp'" style="padding:5px"></div>
			</div>
	    </div>
	</div>
	<script type="text/javascript">



$(function(){
	$("div .acc-panel-inner").hover(
		function(){
			$(this).addClass("accordion-header-selected");
		},
		function(){
			$(this).removeClass("accordion-header-selected");
		}
	).click(
		function(){
			var tab = $("#tab");
			var title = $(this).text();
			if (tab.tabs("exists",title)) {
				tab.tabs("select", title);
			} else {
				tab.tabs("add",{title:$(this).text(),closable:true});
				tab.tabs('getSelected').panel("refresh", G_CTX_PATH+$(this).attr("src"));
			}
			
		}
	);
});
</script>
  </body>

</html>
