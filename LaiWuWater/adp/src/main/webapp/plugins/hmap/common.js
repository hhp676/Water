function addResurePoint(centerX,centerY){
    	var style_point = {
    			strokeColor : "#0000FF",
    			strokeOpacity : 1,
    			strokeWidth : 3,
    			fillColor : "#00AAFF",
    			fillOpacity : 1,
    			pointRadius : 5,
    			pointerEvents : "visiblePainted",
    			externalGraphic : url+"/static/hmap/img/A.png",
    			graphicXOffset : -10,
    			graphicYOffset : -10,
    			graphicWidth : 26,
    			graphicHeight : 26,
    			labelXOffset : 25,
    			labelYOffset : 15,
    			font:"微软雅黑",
    			fontColor : "#955018",
    			fontSize : "13px",
    			fonStyle:"normal",
    			fontWeight : "bold"
    		};
        var aStyleMap = new OpenLayers.StyleMap({"default":style_point});
        if (map.getLayersByName("POINT").length != 0) {
    		var markers = map.getLayersByName("POINT")[0];
    		markers.removeAllFeatures();
    		map.removeLayer(map.getLayersByName("POINT")[0],true);
    	}
   		vectorLayer = new OpenLayers.Layer.Vector("POINT", {
   			styleMap : aStyleMap
   		});
        var point = new OpenLayers.Geometry.Point(centerX,centerY);
		var feature = new OpenLayers.Feature.Vector(point);
        feature.attributes = {
  				url : url+"/static/hmap/img/A.png",
  				resourceX: centerX,
  				resourceY: centerY
  			};
        vectorLayer.addFeatures(feature);
        map.addLayer(vectorLayer);
    }