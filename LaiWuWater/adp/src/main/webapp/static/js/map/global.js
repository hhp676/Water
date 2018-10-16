/**
 * Created with JetBrains PhpStorm.
 * User: Administrator
 * Date: 13-8-1
 * Time: ����1:53
 * To change this template use File | Settings | File Templates.
 */
var map,xcontrol,QueryControl,bounds,Rmap;
var w,h;//截取图像的宽、高
var layer,userVectorLayer;//矢量图层
var sheetArray=new Array();
var flag=1;//判断输入的是图幅还是坐标串
var ResultLayers_2010,Coverage2010_432,Coverage2010_743,Coverage2000_432,Coverage2000_743,GlobeMapSheet_R,ResultLayers_2000;//叠加图层
var mapControls;
var measure_button,measurePolygonBtn;//测距离、测面积
var marker,markers;//marker图层
var size,offset ,marker_url,icon;
var arrayObj = new Array();
var point_layer;
var select_bar;
var featureTemp = new Array();