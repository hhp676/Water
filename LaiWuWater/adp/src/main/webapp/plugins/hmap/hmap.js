var map;
var mapUrl;
var format;
var untiled;
//var	newLayer;
var autoSearchSwitch=("${autoSearchSwitch}"?"${autoSearchSwitch}":true);
function showMap(mapDivId,mapUrlTemp,layerName) {
	mapUrl=mapUrlTemp;
	var bounds = new OpenLayers.Bounds(
            119.948, 30.517,
            123.027, 32.284
        );
    format = 'image/png';
    var options = {
        controls: [],
        resolutions: [/**0.703125, 0.3515625, 0.17578125, 0.087890625, 0.0439453125, 0.02197265625, 0.010986328125, 0.0054931640625, */ 0.00274658203125,0.001373291015625, 6.866455078125E-4, 3.4332275390625E-4, 1.71661376953125E-4, 8.58306884765625E-5, 4.291534423828125E-5, 2.1457672119140625E-5, 1.0728836059570312E-5/**, 5.364418029785156E-6, 2.682209014892578E-6, 1.341104507446289E-6, 6.705522537231445E-7, 3.3527612686157227E-7, 1.6763806343078613E-7, 8.381903171539307E-8, 4.190951585769653E-8, 2.0954757928848267E-8*/],
        maxExtent: bounds,
        maxResolution: 0.01202734375,
        projection: "EPSG:4326",
        units: 'degrees',
        eventListeners: { /**"moveend": mapEvent,**/"zoomend":mapZoomend}	
    };
    map = new OpenLayers.Map(mapDivId, options);
    untiled = new OpenLayers.Layer.WMS("ShangHaiLayerUntiled", mapUrl+"/wms", {
        LAYERS: 'CMShangHaiMap',
        STYLES: '',
        format: format
    },
    {
        buffer: 0,
        //singleTile: true,
        ratio: 1,
        isBaseLayer: true,
        transitionEffect: 'resize'
    }
    );
    map.addLayers([untiled]);
    // build up all controls
    map.addControl(new OpenLayers.Control.PanZoomBar());//左上放大缩小尺
    map.addControl(new OpenLayers.Control.Navigation());//滚轮放大缩小
//    map.addControl(new OpenLayers.Control.Scale());//比例 1：423等
    //map.addControl(new OpenLayers.Control.MousePosition());//鼠标所在坐标
    map.addControl(new OpenLayers.Control.KeyboardDefaults());
    //map.addControl(new OpenLayers.Control.LayerSwitcher());
  //  map.addControl(new OpenLayers.Control.Permalink());//添加链接
   /// map.addControl(new OpenLayers.Control.OverviewMap()); //添加鹰眼
    // 标尺
	var gl = "公里";
	var mi = "米";
	var yl = "英里";
	var yc = "英寸";
	OpenLayers.INCHES_PER_UNIT[gl] = OpenLayers.INCHES_PER_UNIT["km"];
	OpenLayers.INCHES_PER_UNIT[mi] = OpenLayers.INCHES_PER_UNIT["m"];
	OpenLayers.INCHES_PER_UNIT[yl] = OpenLayers.INCHES_PER_UNIT["mi"];
	OpenLayers.INCHES_PER_UNIT[yc] = OpenLayers.INCHES_PER_UNIT["ft"];
	// 比例尺控件
	map.addControl(new OpenLayers.Control.ScaleLine( {
		// 顶部单位
		topOutUnits : "公里",
		// 顶部单位
		topInUnits : "米",
		// 底部单位
		bottomOutUnits : "英里",
		// 底部单位
		bottomInUnits : "英寸"
	}));
    var locat=new OpenLayers.LonLat(121.47800,31.15800);
    map.setCenter(locat,1);
    addNewLayers(mapUrl,layerName);
}

function addNewLayers(mapUrl,layerName){
	
	var newLayer= new OpenLayers.Layer.WMS(layerName, mapUrl+"/wms", {
				LAYERS : layerName,
				STYLES : '',
				format : 'image/png',
				transparent : "true"
				//cql_filter:cql
			}, {
				buffer : 1,
				singleTile : true,
				ratio : 1,
				isBaseLayer : false,
				transitionEffect : 'resize'
			});
			map.addLayer(newLayer);
}

function mapEvent(event){
	mapCurrentZoom=map.getZoom();
	var lonLat=map.getCenter();
	var x=lonLat.lon;
	var y=lonLat.lat;
	var zoomNum=map.getZoom();
	if(zoomNum<2){
		var boundsTemp=boundsObj[zoomNum];
		var xB=boundsTemp.xB;
		var yB=boundsTemp.yB;
		var xT=boundsTemp.xT;
		var yT=boundsTemp.yT;	
		autoPan(map,xB,yB,xT,yT,x,y);
	}
	if(autoSearchSwitch){
		/*if(!mapEventfirst){
			bounds=map.getExtent().toBBOX();
			autoSearch(0,null,bounds);
		}*/
	}
	//mapEventfirst=false;
}

//禁用用div
$(function(){
	if(document.getElementById("mapDivId")){
		document.getElementById("mapDivId").oncontextmenu=function(event){return false;};
	}	
});


/**
 * 
 * @param mapObj:OpenLayers.Map
 * @param xB:左下角x坐标
 * @param yB:左下角y坐标
 * @param xT:右上角x坐标
 * @param yT:右上角y坐标
 * @param xN:当先中心点x坐标
 * @param yN:当先中心点y坐标
 */
function autoPan(mapObj,xB,yB,xT,yT,xN,yN){
	if(yN>yT){//上面
		if(xN>xT){//右边
			panByXY(mapObj,xT,yT);
		}else if(xN<xT&&xN>xB){//中间
			panByXY(mapObj,xN,yT);
		}else if(xN<xB){//左边
			panByXY(mapObj,xB,yT);
		}
	}else if(yN<yT&&yN>yB){//中间
		if(xN>xT){//右边
			panByXY(mapObj,xT,yN);
		}else if(xN<xT&&xN>xB){//中间
			
		}else if(xN<xB){//左边
			panByXY(mapObj,xB,yN);
		}
	}else if(yN<yB){//下面
		if(xN>xT){//右边
			panByXY(mapObj,xT,yB);
		}else if(xN<xT&&xN>xB){//中间
			panByXY(mapObj,xN,yB);
		}else if(xN<xB){//左边
			panByXY(mapObj,xB,yB);
		}
	}
}

/**
 * 根据坐标定位
 * @param x:横坐标
 * @param y:纵坐标
 */
function panByXY(mapObj,x,y){
	var locat=new OpenLayers.LonLat(x,y);
	mapObj.panTo(locat);
}

var boundsObj=[
               
	{
		xB:120.83093504492,
		yB:30.98550776133,
		xT:122.17283906933,
		yT:31.815668725841
	},
	{
		xB:120.83093504492,
		yB:30.98550776133,
		xT:122.17283906933,
		yT:31.815668725841
	}
];


function mapZoomend(){
	var mapCurrentZoom=1;
	var mapDiv;
	if(map.getZoom()>mapCurrentZoom){//地图放大的时候
		mapCurrentZoom=map.getZoom();
		mapDiv=document.getElementById("mapDivId");
	}
	if(map.getZoom()<mapCurrentZoom){//地图缩小的时候
		mapCurrentZoom=map.getZoom();
		mapDiv=document.getElementById("mapDivId");
	}
	
}