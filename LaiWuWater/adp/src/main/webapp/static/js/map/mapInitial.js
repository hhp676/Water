// JavaScript Document
$(document).ready(function(){
    initialMap();
});

//初始化地图
function initialMap(){
  
    var matrixIds = new Array(19);
        for (var i = 1; i < 19; ++i) {
            matrixIds[i - 1] = i.toString();
        }

        map = new OpenLayers.Map("mapWrapper", {
            maxExtent: new OpenLayers.Bounds(-180, -90, 180, 90),
            controls: [
			new OpenLayers.Control.Navigation(),
			//new OpenLayers.Control.PanZoom(),
			new OpenLayers.Control.LayerSwitcher(),
			new OpenLayers.Control.MousePosition({displayClass:"MousePosCtrl", prefix:'经度:', separator:'，纬度:' }) //增加鼠标位置
			],
            //预先设置解析度值的数组,一个图层的解析度为[a,b,c],那么缩放级别的0就是a ,1就是b。。。
            resolutions: [0.703125, 0.3515625, 0.17578125, 0.087890625, 0.0439453125, 0.02197265625, 0.010986328125, 0.0054931640625, 0.00274658203125, 0.001373291015625, 0.0006866455078125, 0.00034332275390625, 0.000171661376953125, 0.0000858306884765625, 0.00004291534423828125, 0.000021457672119140625, 0.000010728836059570312, 0.000005364418029785156],
            numZoomLevels: 18,
			allOverlays: true
        });
        var TianDi_Map = new OpenLayers.Layer.WMTS({
            name: "行政区划图",
            url: "http://t0.tianditu.com/vec_c/wmts/",
            layer: "vec",
            matrixSet: "c",
            matrixIds: matrixIds,
            format: "tiles",
            style: "_null",
            opacity: 0.7
        });
		 var TianDi_img_Map = new OpenLayers.Layer.WMTS({
            name: "遥感影像图",
            url: "http://t0.tianditu.com/img_c/wmts/",
            layer: "img",
            matrixSet: "c",
            matrixIds: matrixIds,
            format: "tiles",
            style: "_null",
            opacity: 0.7,
			visibility:false
        });
        var TianDi_Map_CHLabels = new OpenLayers.Layer.WMTS({
            name: "TianDi_Map_CHLabels",
            url: "http://t0.tianditu.com/cva_c/wmts/",
            layer: "cva",
            matrixSet: "c",
            matrixIds: matrixIds,
            format: "tiles",
            style: "_null",
            isBaseLayer: false,
			displayInLayerSwitcher:false
        });
        TianDi_Map_CHLabels.events.register("tileerror", this, function(evt) {
            evt.tile.setImgSrc("Src/blank.gif");
            return false;
        });
        TianDi_Map.events.register("tileerror", this, function(evt) {
            evt.tile.setImgSrc("Src/vec404.png");
            return false;
        });
        map.addLayers([TianDi_Map,TianDi_img_Map, TianDi_Map_CHLabels]);
        map.setCenter(new OpenLayers.LonLat(117.655, 36.210), 11);
        point_layer = new OpenLayers.Layer.Vector('点图层', { styleMap: new OpenLayers.StyleMap({
            "default": {
                externalGraphic: '${noselect}',
                //graphicWidth: 17,
                graphicHeight: 35,
                graphicYOffset: -19
            },
            "select": {
                externalGraphic: '${select}',
                //graphicWidth: 17,
                graphicHeight: 35,
                graphicYOffset: -19
            }
        })
        });
        map.addLayer(point_layer);
    //放大、缩小、漫游、查询控件
    xcontrol = {
        zomIn:new OpenLayers.Control.ZoomBox(map),
        zomOut:new OpenLayers.Control.ZoomBox({out:true}, map),
        pan: new OpenLayers.Control.Pan()
    };
    for (var key in xcontrol) {
        map.addControl(xcontrol[key]);
    }

    select_bar = new OpenLayers.Control.SelectFeature(point_layer, { displayClass: "olControlSelectFeature", onSelect: onFeatureInsert, onUnselect: onFeatureUnselect, multiple: false,title:"查看信息" });
        var panel = new OpenLayers.Control.Panel({ displayClass: 'customEditingToolbar',defaultControl: select_bar });
		panel.addControls(select_bar);
        map.addControl(panel);
    function onFeatureInsert(feature) {
            selectedFeature = feature;
			var fid = selectedFeature.id;
			var detail = selectedFeature.attributes['detail'];
            var name = selectedFeature.attributes['name'];

				/* var div = "<div id='p_popup'><div id='p_title'>" + name + "</div><div id='p_content'><div class='p_row'>单位地址：" + address + "<br></div><div class='p_row'>法人代表：" + lPerson + "<br></div><div class='p_row'>法人代表电话：" + ltel+ "<br></div><div class='p_row'>消防安全负责人：" + sPerson + "<br></div><div class='p_row'>安全负责人电话：" +stel + "<br></div><div class='p_row_ipart' title="+iPart+">重点部位：" +iPart1 + "<br></div><div class='p_row_water' title="+water+">周边水源：" + water1 +"<br></div><div class='p_row'>所属中队：" + sszd +"<br></div><div class='p_row'>建筑类型：" +jzlx +"</div></div></div>";*/


 var div = "<div id='p_popup'><div id='p_title'>" + name + "</div><div id='content'><div id='content1'class='child_content'><p>单位详情：" + detail + "</p></div>";
				  
				

            popup = new OpenLayers.Popup.FramedCloud("info",
	       		feature.geometry.getBounds().getCenterLonLat(),
               null,
               div,
               null,
               true,
               onPopupClose);
	  
			 popup.setBorder("0px solid #d91f12");
            feature.popup = popup;
            map.addPopup(popup);
            //modify_bar.deactivate();
        }
        function onFeatureUnselect(feature) {
            map.removePopup(feature.popup);
            feature.popup.destroy();
            feature.popup = null;
        }
		function onPopupClose() {
           var startLonlat = new OpenLayers.LonLat(selectedFeature.attributes.newsLon, selectedFeature.attributes.newsLat);
            selectedFeature.move(startLonlat);
            
            select_bar.unselect(selectedFeature);
            select_bar.activate();
        }

    
     //测距离一系列函数
    OpenLayers.Renderer.symbol.lightning = [0, 0, 15, 0, 15, -15, 15, 15,15,0,30,0 ];
    var sketchSymbolizers = {
        "Point":{
            pointRadius:15,
            graphicName:"lightning",
            fillColor:"white",
            // cursor:'crosshair',
            fillOpacity:1,
            strokeWidth:1,
            strokeOpacity:1,
            strokeColor:"#333333"
        },
        "Line":{
            strokeWidth:3,
            strokeOpacity:1,
            strokeColor:"#FD8044"
            // strokeDashstyle:"dash"
        },
        "Polygon": {
            strokeWidth: 3,
            strokeOpacity: 1,
            strokeColor: "#FD8044",
            fillColor: "#FD8044",
            fillOpacity: 0.3
        }
    };
    var Meas_style = new OpenLayers.Style();
    Meas_style.addRules([
        new OpenLayers.Rule({ symbolizer:sketchSymbolizers })
    ]);
    var Meas_styleMap = new OpenLayers.StyleMap({ "default":Meas_style });
    measure_button =new OpenLayers.Control.Measure(
        OpenLayers.Handler.Path, {
            persist: false,
            handlerOptions: {
                layerOptions: {
                    styleMap: Meas_styleMap
                }
            }
        }
    );
    measure_button.activate=function () {
        if (this.active) {
            return false;
        }
        if (this.handler) {
            this.handler.activate();
        }
        this.active = true;
        if(this.map) {
            OpenLayers.Element.addClass(
                this.map.viewPortDiv,
                this.displayClass.replace(/ /g, "") + "Active"
            );
        }
        this.events.triggerEvent("activate");
        $("#measure").addClass("cur");
        return true;
    }
//measure控件Deactivate重写
    measure_button.deactivate=function(){
        this.cancelDelay();
        // map.popups=[];
        $("#measure").removeClass("cur-corntrol");
        /*var geoidLine=map.getLayersByName("geoidLine")[0];
         if(geoidLine){
         geoidLine.setZIndex(5990);
         }*/
        // $(".measure_popup").remove();
        return OpenLayers.Control.prototype.deactivate.apply(this, arguments);

    }
    measure_button.events.on({
        "measure":handlerMeasurement,
        "measurepartial":handlerPartialMeasurement
    });
    var popup_1=OpenLayers.Class(OpenLayers.Popup,{
        autoSize:true,
        maxSize:new OpenLayers.Size(460,23),
        contentDisplayClass:"popupContentStyle",
        displayClass:"measure_popup"
    });
//每次添加一个结点到草图中时触发的方法
    function handlerPartialMeasurement(event){
        var geometry=event.geometry;
        var units=event.units;
        var measure=event.measure;
        var out="";
        var x,y;
        var p=geometry.getVertices();
        out+="<strong>"+measure.toFixed(2)+"</strong>"+" "+units;
        x = this.map.getPixelFromLonLat(new OpenLayers.LonLat(p[p.length - 1].x, p[p.length - 1].y)).x + 5;
        y = this.map.getPixelFromLonLat(new OpenLayers.LonLat(p[p.length - 1].x, p[p.length - 1].y)).y + 5;
        var path_vector_layer=map.getLayersByName('OpenLayers.Handler.Path')[0];
        var clone_path=map.getLayersByName('clone_path')[0];
        if(measure==0){
            if(!measure_button.active){
                measure_button.activate();
            }
            this.popup=new popup_1(
                'popupStart',
                new OpenLayers.LonLat(p[p.length - 1].x, p[p.length - 1].y),
                null,
                "<strong>"+"开始"+"</strong>",
                false

            );
        }
        else{
            this.popup=new popup_1(
                'midPopup',
                new OpenLayers.LonLat(p[p.length - 1].x, p[p.length - 1].y),
                new OpenLayers.Size(70, 13),
                out,
                false
            );
        }
        var clone_path;
        if(map.getLayersByName('clone_path').length==0){
            clone_path=path_vector_layer.clone();
            clone_path.removeAllFeatures();
            clone_path.name='clone_path';
            map.addLayer(clone_path);
        }
        //添加popup，与featurec通过fid连接起来，关闭时将线与popup统一删除
        clone_path=map.getLayersByName('clone_path')[0];
        this.popup._fid='measure_features_'+(clone_path.features.length);
        this.popup.setBorder("1px solid #7A7A7A");
        this.map.addPopup(this.popup);

    }
//绘图完毕时触发的方法
    function handlerMeasurement(event) {
        var geometry = event.geometry;
        var units = event.units;
        var measure = event.measure;
        var out = "";
        var x, y;
        var clone_path;
        var p = geometry.getVertices();
        var path_vector_layer=map.getLayersByName('OpenLayers.Handler.Path')[0];
        if(measure==0){
            clrPopup();
        };
        out += "总距离"+ "<strong>"+measure.toFixed(2) +"</strong>"+ " " + units;
        x = this.map.getPixelFromLonLat(new OpenLayers.LonLat(p[p.length - 1].x, p[p.length - 1].y)).x + 5;
        y = this.map.getPixelFromLonLat(new OpenLayers.LonLat(p[p.length - 1].x, p[p.length - 1].y)).y + 5;
        if(map.getLayersByName('clone_path').length==0){
            clone_path=path_vector_layer.clone();
            clone_path.removeAllFeatures();
            clone_path.name='clone_path';
            map.addLayer(clone_path);
        }
        //将每次测量添加的线转移到新的矢量图层，保证测距控件失活时，测距线段还在
        clone_path=map.getLayersByName('clone_path')[0];
        var feature_length=path_vector_layer.features.length
        clone_feature=path_vector_layer.features[feature_length-1].clone();
        clone_feature.fid='measure_features_'+(clone_path.features.length);
        clone_path.addFeatures(clone_feature);
        if(this.popup=null){
            this.popup.hide();
            this.popup.moveTo(new OpenLayers.Pixel(x, y));
            this.popup.setContentHTML(out);
        }
        else{
            this.popup= new popup_1(
                'popupMeasure',
                new OpenLayers.LonLat(p[p.length - 1].x, p[p.length - 1].y),
                new OpenLayers.Size(130, 15),
                out,
                true,
                function(event){
                    // console.log(this._fid);
                    var cur_pop_fid=this._fid;
                    clrPopup(cur_pop_fid);
//                setMarkerLayerIndex();
                    //若全部清除当前popup与features，则deactive当前测距控件
                    if(map.popups.length==0)
                    {
                        measure_button.deactivate();
                    }
                }
            );
            this.popup._fid='measure_features_'+(clone_path.features.length-1);
            this.popup.setBorder("1px solid #FF0103");
            this.map.addPopup(this.popup);
        };
        this.deactivate();
    };
    function clrPopup(cur_pop_fid){
        // $(".measure_popup").hide();
        // var path_vector_layer=map.getLayersByName('clone_path')[0];
        var a=map.getLayersByName('clone_path')[0];
        //过滤当前测距线的popups，进行清除
        var del_pops=$.grep(map.popups,function(n,i){
            return n._fid&&n._fid==cur_pop_fid;
        });
        for (var i = 0; i <del_pops.length; i++) {
            map.removePopup(del_pops[i]);
        };
        //过滤当前测距线的features，进行清除
        var del_features=$.grep(a.features,function(n,i){
            return n.fid&&n.fid==cur_pop_fid;
        });
        a.removeFeatures(del_features);

    }
    //测面积一系列函数
    measurePolygonBtn=new OpenLayers.Control.Measure(
        OpenLayers.Handler.Polygon, {
            persist: true,
            immediate : true,
            handlerOptions: {
                layerOptions: {
                    styleMap: Meas_style
                }
            }
        }
    );
    measurePolygonBtn.activate=function () {

        if (this.active) {
            return false;
        }
        if (this.handler) {
            this.handler.activate();
        }
        this.active = true;

        this.events.triggerEvent("activate");
        $("#measurePolygon").addClass("cur");
        return true;
    }
    measurePolygonBtn.deactivate=function(){
        this.cancelDelay();
        $("#measurePolygon").removeClass("cur-corntrol");
        return OpenLayers.Control.prototype.deactivate.apply(this, arguments);
    }
    measurePolygonBtn.events.on({
        "measure":measurePolygonEnd,
        "measurepartial":handleMeasurements
    });
    measurePolygonBtn.geodesic=true;
    var popup_Polygon=OpenLayers.Class(OpenLayers.Popup,{
        autoSize:true,
        maxSize:new OpenLayers.Size(460,23),
        contentDisplayClass:"popupPolygonContentStyle",
        displayClass:"measurePolygon_popup"
    });
    function handleMeasurements(event) {
        var geometry = event.geometry;
        var units = event.units;
        var order = event.order;
        var measure = event.measure;
        var x,y;
        var out = "";
        out+="<strong>"+measure.toFixed(3)+"</strong>"+" "+units+"<sup>2</" + "sup>";
        $("#measurePol").hide();//xhq
        map.events.register('mousemove',this,function(e){
            if($("#fullScreen").hasClass("full")){
                y=e.clientY-20;
                x=e.clientX;
            }
            else{
                y=e.clientY-100;
                x=e.clientX;
            }
            $("#measurePol").css({
                position:'absolute',
//            top:y,
//            left:x,
                height: '23px',
                zIndex: 1000,
                border: '1px ridge #8B8B8B',
//                background:'url('+host_url+'components/com_results_map/assets/imgs/ppbg.png) repeat',
                paddingTop:'5px',
                paddingLeft:'5px',
                paddingRight:'5px',
                paddingBottom:'0',
                color: '#333'
            })
            if(e.xy.x+$("#measurePol").width()>$(window).width()){//若超出屏幕
                $("#measurePol").css({left:x-$("#measurePol").width(), top:y});
            }
            else{
                $("#measurePol").css({left:x, top:y});
            }
            $("#measurePol")[0].innerHTML="面积"+out;

        });
    }
    function measurePolygonEnd(event){
        var clone_path=map.getLayersByName('clone_measurPly')[0];
        var geometry = event.geometry;
        var units = event.units;
        var measure = event.measure;
        var out = "";
        var x, y;
        var path_vector_layer=map.getLayersByName('OpenLayers.Handler.Polygon')[0];
        if(measure==0){
            alert("已选点不满三个，请重新操作。");
            $("#measurePol").hide();
            measurePolygonBtn.updateHandler();
        }
        else{
            out += "面积" + "<strong>"+measure.toFixed(2) +"</strong>"+ " " + units+"<sup>2</" + "sup>";
            if(!clone_path){
                clone_path=path_vector_layer.clone();
                clone_path.removeAllFeatures();
                clone_path.name='clone_measurPly';
                map.addLayer(clone_path);
            }
            //将每次测量添加的线转移到新的矢量图层，保证测距控件失活时，测距线段还在
            clone_path=map.getLayersByName('clone_measurPly')[0];
            var feature_length=path_vector_layer.features.length;
            clone_polygon_feature=path_vector_layer.features[feature_length-1].clone();
            clone_polygon_feature.fid='measure_Polygon_features_'+(clone_path.features.length);
            clone_path.addFeatures(clone_polygon_feature);
            var pos=$(".MousePosCtrl.olControlNoSelect").text();
            var posList=pos.split('，');
            var x=posList[0].split(':')[1];
            var y=posList[1].split(':')[1];
            if(this.popup=null){
                this.popup.hide();
                this.popup.moveTo(new OpenLayers.Pixel(map.getPixelFromLonLat(x, y)));
                this.popup.setContentHTML(out);
            }
            else{
                this.popup= new popup_Polygon(
                    'popupPolygonMeasure',
                    new OpenLayers.LonLat(x,y).transform(
                        new OpenLayers.Projection("EPSG:4326"),
                        map.getProjectionObject()
                    ),
                    new OpenLayers.Size(130, 15),
                    out,
                    true,
                    function(event){
                        console.log(this._fid);
                        var cur_pop_fid=this._fid;
                        clrPolygonPopup(cur_pop_fid);
                        //若全部清除当前popup与features，则deactive当前测距控件
                        if(map.popups.length==0)
                        {
                            measurePolygonBtn.deactivate();
                        }
                    }
                );
                this.popup._fid='measure_Polygon_features_'+(clone_path.features.length-1);
                this.popup.setBorder("1px ridge #8B8B8B");
                this.map.addPopup(this.popup);
            };
            $("#measurePol").hide();
            this.deactivate();
        }
    }
    function clrPolygonPopup(cur_pop_fid){
        var a=map.getLayersByName('clone_measurPly')[0];
        //过滤当前测距线的popups，进行清除
        var del_pops=$.grep(map.popups,function(n,i){
            return n._fid&&n._fid==cur_pop_fid;
        });
        for (var i = 0; i <del_pops.length; i++) {
            map.removePopup(del_pops[i]);
        };
        //过滤当前测距线的features，进行清除
        var del_features=$.grep(a.features,function(n,i){
            return n.fid&&n.fid==cur_pop_fid;
        });
        a.removeFeatures(del_features);
    }
    //测距离、测面积控件
    if (!mapControls) {
        mapControls = {
            mearueLine:measure_button,
            mearuePolygon:measurePolygonBtn
        };
    }
    for (var key in mapControls) {
        map.addControl(mapControls[key]);
    }
    
}
