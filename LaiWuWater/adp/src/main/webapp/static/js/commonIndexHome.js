/**
 * Created by HHP on 2018/10/14.
 */
//		$("#leftMenu")
$(function(){
    var leftMenuUri = "/leftMenu";
    $('#leftAccordion').accordion({
        animate:false
    });
    Hg.getJson(leftMenuUri,{},function(data){
        if (data.success) {
            $.each(data.data, function(i, auth) {
                var childHtmlStr =  new StringBuffer();
                childHtmlStr.append("<ul class='easyui-tree' style='padding: 0px; '>");

                $.each(auth.childs,function(i,authChild) {
                    var authName = authChild.authName;
                    var authUri = authChild.authUri;
                    var iconCls = "acc_icon_"+authChild.authIcon;
                    var state = "";
                    var tag = "tag='link'";
                    if (authChild.childs.length > 0) {
                        state = "state='closed'";
                        tag = "";
                    }
                    childHtmlStr.append("    <li style='font-size: 16px' iconCls='"+iconCls+"' "+state+"  "+tag+">");
                    childHtmlStr.append("        <span><span style='font-size: 15px' "+tag+"  url='"+authUri+"' icon='"+iconCls+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+authName+"</span></span>");
                    childHtmlStr.append("        <ul>");
                    $.each(authChild.childs,function(i,authLeft){
                        var leftAuthName = authLeft.authName;
                        var leftAuthUri = authLeft.authUri;
                        var leftAuthIconCls = "acc_icon_"+authLeft.authIcon;
                        childHtmlStr.append("    <li iconCls='"+leftAuthIconCls+"'>");
                        childHtmlStr.append("        <span><span tag='link' url='"+leftAuthUri+"' icon='"+leftAuthIconCls+"'>&nbsp;&nbsp;&nbsp;&nbsp;"+leftAuthName+"</span></span>");
                        childHtmlStr.append("    </li>");
                    });
                    childHtmlStr.append("        </ul>")
                    childHtmlStr.append("    </li>");
                });

                childHtmlStr.append("</ul>");


                $('#leftAccordion').accordion('add', {
                    title:"&nbsp;&nbsp;&nbsp;&nbsp;"+auth.authName,
                    content: childHtmlStr.toString(),
                    iconCls: 'icon ' + auth.authIcon
                });
            });

            $.parser.parse('#leftAccordion');

            $("#leftAccordion span[tag=link]").click(function(){
                var tab = $("#tab");
                /* 若要放开tab存在是刷新功能，此处也可放开，处理右键菜单绑定多次click问题
                 tab.tabs({
                 'onAdd':function(title,index){
                 (tab.tabs('getTab',index)).panel({
                 'onBeforeLoad':function(){
                 // $(".easyui-menu>div").unbind('click').bind('click');
                 }
                 })
                 }
                 }); */

                var title = $(this).text();
                if (tab.tabs("exists",title)) {
                    tab.tabs("select", title);
                    //  tab.tabs('getSelected').panel("refresh");
                } else {
                    tab.tabs("add",{title:title,closable:true,icon:$(this).attr("icon")});
                    tab.tabs('getSelected').panel("refresh", G_CTX_PATH+$(this).attr("url"));
                }
            });
        } else {
            $.messager.alert("加载失败!",data.data);
        }
    });
});
function refreshHome(){
    var tab = $("#tab");
    tab.tabs('getTab', "首页").panel("refresh");

}