<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>首页center</title>	
  </head>
  <body>
  	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',split:true,title:'导航菜单'" style="width:200px;">
			<div id="leftAccordion" class="easyui-accordion" data-options="fit:true,border:false" >
               
            </div>
	    </div>
		<div data-options="region:'center'">
	      <div id="tab" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
				<div title="首页"  data-options="href:'${ctx}/indexHome'" style="padding:5px"></div>
			</div>
	    </div>
	</div>
	<script type="text/javascript">
     $(function(){
		 var leftMenuUri = "/leftMenu";
    	 Hg.getJson(leftMenuUri,{},function(data){
             if (data.success) {
            	 var _menus = data;
            	    //生成导航菜单
            	    $('#leftAccordion').accordion({
            	        animate:false
            	    });
            	    $.each(_menus.data, function(i, n) {
            	        var ul = $("<ul></ul>");
            	        $.each(n.childs, function(j, o) {           
            	            var li_html = "<li><div>";
            	            li_html += "    <a icon='"+o.authIcon+"' url='"+o.authUri+"'>";
            	            li_html += "    <span class='icon acc_icon_"+o.authIcon+"'>&nbsp;</span>";
            	            li_html += "    <span class='nav'>"+ o.authName+"</span>";
            	            li_html += "    </a>";
            	            li_html += "</div></li>";
            	            ul.append($(li_html));
            	            
            	        });
            	        $('#leftAccordion').accordion('add', {
            	            title: n.authName,
            	            content: "<ul>"+ul.html()+"</ul>",
            	            iconCls: 'icon ' + n.icon
            	        });

            	    });
            	    
            	    $('.easyui-accordion li a').click(function(){
            	        var tab = $("#tab");
            	        var title = $(this).children('.nav').text();
            	        if (tab.tabs("exists",title)) {
            	            tab.tabs("select", title);
            	        } else {
            	            tab.tabs("add",{title:title,closable:true,icon:"acc_icon_"+$(this).attr("icon")});
            	            tab.tabs('getSelected').panel("refresh", G_CTX_PATH+$(this).attr("url"));
            	        }
            	        $('.easyui-accordion li div').removeClass("selected");
            	        $(this).parent().addClass("selected");
            	    }).hover(function(){
            	        $(this).parent().addClass("hover");
            	    },function(){
            	        $(this).parent().removeClass("hover");
            	    });
            	    
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
             } else {
                 $.messager.alert("加载失败!",data.data);
             }
         });
});
</script>
</body>
</html>
