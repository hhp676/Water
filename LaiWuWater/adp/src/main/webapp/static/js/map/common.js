/**
 * Created with JetBrains PhpStorm.
 * User: Administrator
 * Date: 13-8-16
 * Time: 下午3:22
 * To change this template use File | Settings | File Templates.
 */

$(document).ready(function(){
	// alert("sdsd");
	//$('.olPopupCloseBox').css('background','#fff');
	//$('#info_close').css({'right':'35px','top':'40px'});
	initialDataPanel();
	
	//ditu高度
	var left_w = $('.left_box').width();
	$('.border').css('left',left_w);
    //当浏览器窗口大小改变时，设置显示内容的高度
	changeDivHeight();   
	window.onresize=function(){  
		 changeDivHeight();  
	} 
	
	point_layer.setVisibility(false);
	$("#map_bt").click(function(){
		point_layer.setVisibility(true);
	})

    $("#home_bt").click(function(){
        window.location = G_CTX_PATH + "/index";
    })

    $("#info_bt").click(function(){
        window.location = G_CTX_PATH + "/indexCompanyCenter";
    })

    $("#ysjh_bt").click(function(){
        window.location = G_CTX_PATH + "/indexPlanWaterCenter";
    })

    $("#ysgl_bt").click(function(){
        window.location = G_CTX_PATH + "/indexDataManagementCenter";
    })

});

function changeDivHeight(){
	var windowheight =$(window).height();
	var windowwidth =$(window).width();
	var contheight = windowheight;
	$("#mapWrapper").css('height',contheight)
	//左侧高度
	//$('.left_box').css('height',contheight);
	$('.search_list_scroll').css('height',contheight-$('.search').height()-25);
	
	$('#mapWrapper').css('width',windowwidth);

	map.updateSize();
}
//获取全部数据
function getInitialData(){
  var url="xxx.ashx?funName=getInstSynopsisInfo";
		$.post(url).done(function (responseText) {
			  arrayObj=responseText;  		    			
		  });

}
function initialData(){
	arrayObj.push(['节水单位1','莱芜市龙兴供水有限有限公司成立于2004年，是高新区招商引资建设的重点基础设施企业，日供水能力4万吨。总投资16000万元，注册资本1000万元，管网总长70余公里，供水人口35000多人，实现了供水区域内管网全覆盖，为高新区生活生产用水提供了充足的优质水源保障！ ','117.646','36.207']);		
	arrayObj.push(['节水单位2','莱芜市龙兴供水有限有限公司成立于2004年，是高新区招商引资建设的重点基础设施企业，日供水能力4万吨。总投资16000万元，注册资本1000万元，管网总长70余公里，供水人口35000多人，实现了供水区域内管网全覆盖，为高新区生活生产用水提供了充足的优质水源保障！ ','117.665','36.206']);		
	arrayObj.push(['节水单位3','莱芜市龙兴供水有限有限公司成立于2004年，是高新区招商引资建设的重点基础设施企业，日供水能力4万吨。总投资16000万元，注册资本1000万元，管网总长70余公里，供水人口35000多人，实现了供水区域内管网全覆盖，为高新区生活生产用水提供了充足的优质水源保障！ ','117.658','36.217']);		
	arrayObj.push(['节水单位4','莱芜市龙兴供水有限有限公司成立于2004年，是高新区招商引资建设的重点基础设施企业，日供水能力4万吨。总投资16000万元，注册资本1000万元，管网总长70余公里，供水人口35000多人，实现了供水区域内管网全覆盖，为高新区生活生产用水提供了充足的优质水源保障！ ','117.645','36.203']);		
	arrayObj.push(['节水单位5','莱芜市龙兴供水有限有限公司成立于2004年，是高新区招商引资建设的重点基础设施企业，日供水能力4万吨。总投资16000万元，注册资本1000万元，管网总长70余公里，供水人口35000多人，实现了供水区域内管网全覆盖，为高新区生活生产用水提供了充足的优质水源保障！ ','117.672','36.208']);		
//	arrayObj.push(['节水单位6','莱芜市龙兴供水有限有限公司成立于2004年，是高新区招商引资建设的重点基础设施企业，日供水能力4万吨。总投资16000万元，注册资本1000万元，管网总长70余公里，供水人口35000多人，实现了供水区域内管网全覆盖，为高新区生活生产用水提供了充足的优质水源保障！ ','116.32251','39.72378']);		
//	arrayObj.push(['节水单位7','莱芜市龙兴供水有限有限公司成立于2004年，是高新区招商引资建设的重点基础设施企业，日供水能力4万吨。总投资16000万元，注册资本1000万元，管网总长70余公里，供水人口35000多人，实现了供水区域内管网全覆盖，为高新区生活生产用水提供了充足的优质水源保障！ ','116.32251','39.72378']);		
//	arrayObj.push(['节水单位8','莱芜市龙兴供水有限有限公司成立于2004年，是高新区招商引资建设的重点基础设施企业，日供水能力4万吨。总投资16000万元，注册资本1000万元，管网总长70余公里，供水人口35000多人，实现了供水区域内管网全覆盖，为高新区生活生产用水提供了充足的优质水源保障！ ','116.32251','39.72378']);		
//	arrayObj.push(['节水单位9','莱芜市龙兴供水有限有限公司成立于2004年，是高新区招商引资建设的重点基础设施企业，日供水能力4万吨。总投资16000万元，注册资本1000万元，管网总长70余公里，供水人口35000多人，实现了供水区域内管网全覆盖，为高新区生活生产用水提供了充足的优质水源保障！ ','116.32251','39.72378']);		
//	arrayObj.push(['节水单位10','莱芜市龙兴供水有限有限公司成立于2004年，是高新区招商引资建设的重点基础设施企业，日供水能力4万吨。总投资16000万元，注册资本1000万元，管网总长70余公里，供水人口35000多人，实现了供水区域内管网全覆盖，为高新区生活生产用水提供了充足的优质水源保障！ ','116.32251','39.72378']);		
//	arrayObj.push(['节水单位11','莱芜市龙兴供水有限有限公司成立于2004年，是高新区招商引资建设的重点基础设施企业，日供水能力4万吨。总投资16000万元，注册资本1000万元，管网总长70余公里，供水人口35000多人，实现了供水区域内管网全覆盖，为高新区生活生产用水提供了充足的优质水源保障！ ','116.32251','39.72378']);			
//	arrayObj.push(['节水单位12','莱芜市龙兴供水有限有限公司成立于2004年，是高新区招商引资建设的重点基础设施企业，日供水能力4万吨。总投资16000万元，注册资本1000万元，管网总长70余公里，供水人口35000多人，实现了供水区域内管网全覆盖，为高新区生活生产用水提供了充足的优质水源保障！ ','116.32251','39.72378']);
}
function addSearchMarker(item,number) {
    var imgUrl = G_CTX_PATH+"/static/images/map/1.png";
    var selectimgUrl = G_CTX_PATH+ "/static/images/map/2.png";
	var lat_p = item[3] * 1;
    var lon_p = item[2] * 1;
    featureTemp[number] = new OpenLayers.Feature.Vector(
                    new OpenLayers.Geometry.Point(
                        lon_p,
                        lat_p
                    ),
                    {
                        'noselect': imgUrl,
                        'select': selectimgUrl,
						'numid': number,
                        'name':item[0],
						'detail':item[1],
                        'newsLon': lon_p,
                        'newsLat':lat_p
                    }
            );
	
    point_layer.addFeatures(featureTemp[number]);
}
function initialDataPanel(){
	initialData();

	var every=arrayObj.length;
	marker = new OpenLayers.Layer.Markers("Marker", { displayInLayerSwitcher: false });
    map.addLayer(marker);
	for (var i = 0; i <every; i++) {
		var markerurl= G_CTX_PATH+"/static/images/map/marker_large.png";
			
		addSearchMarker(arrayObj[i],i);
 }
}
