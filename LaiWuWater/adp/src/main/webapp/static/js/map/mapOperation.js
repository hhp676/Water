/**
 * Created with JetBrains PhpStorm.
 * User: Administrator
 * Date: 13-8-16
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */

//激活、销毁地图控件
function toggleControl(element) {
	for (var k in mapControls) {
        mapControls[k].deactivate();
		$('#'+k).removeClass("cur");
    }
    for (var k in xcontrol) {
        if (element.id == k) {
            xcontrol[k].activate();
            $('#'+element.id).addClass("cur");
            if(element.id == "resize"){
                xcontrol[k].mode = OpenLayers.Control.ModifyFeature.RESHAPE;
                xcontrol[k].mode |= OpenLayers.Control.ModifyFeature.RESIZE;
                xcontrol[k].mode |= OpenLayers.Control.ModifyFeature.DRAG;
            }
        } else {
            xcontrol[k].deactivate();
            $('#'+k).removeClass("cur");
        }
    }
}
function drawControl(element) {
    for (var k in xcontrol) {
        xcontrol[k].deactivate();
		$('#'+k).removeClass("cur");
    }
    for (var k in mapControls) {
        if (element.id == k) {
            mapControls[k].activate();
			$('#'+element.id).addClass("cur");
        } else {
            mapControls[k].deactivate();
			$('#'+k).removeClass("cur");
        }
    }
}