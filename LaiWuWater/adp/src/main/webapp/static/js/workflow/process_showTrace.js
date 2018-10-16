// 获取图片资源
//var imageUrl = G_CTX_PATH + "/activiti/resource/process-instance?pid="
//		+ pid + "&type=image";
var imageUrl = G_CTX_PATH + "/activiti/resource/read?processDefinitionId="
		+ processDefinitionId + "&resourceType=image";
$.getJSON(G_CTX_PATH + '/activiti/process/trace?pid=' + pid,
				function(infos) {
					var positionHtml = "";
					// 生成图片
					var varsArray = new Array();
					$.each(infos, function(i, v) {
						var $positionDiv = $('<div/>', {
							'class' : 'activity-attr'
						}).css({
							position : 'absolute',
							left : (v.x - 1),
							top : (v.y - 1),
							width : (v.width - 2),
							height : (v.height - 2),
							backgroundColor : 'black',
							opacity : 0,
							zIndex : $.fn.qtip.zindex - 1
						});

						// 节点边框
						var $border = $('<div/>', {
							'class' : 'activity-attr-border'
						}).css({
							position : 'absolute',
							left : (v.x - 1),
							top : (v.y - 1),
							width : (v.width - 4),
							height : (v.height - 3),
							zIndex : $.fn.qtip.zindex - 2
						});

						if (v.currentActiviti) {
							$border.addClass('ui-corner-all-12').css({
								border : '3px solid red'
							});
						}
						positionHtml += $positionDiv.outerHTML()
								+ $border.outerHTML();
						varsArray[varsArray.length] = v.vars;
					});
					$(".process_image").append(getPicture(imageUrl));
					$(".process_image").append(positionHtml);

					// 设置每个节点的data
					$('.activity-attr').each(
							function(i, v) {
								$(this).data('vars', varsArray[i]);
							});
					//tip
					$('.activity-attr').tooltip({
						content : function() {
							var vars = $(
									this)
									.data('vars');
							var tipContent = "<table class='need-border'>";
							$.each(vars,function(varKey, varValue) {
									if (varValue) {
										tipContent += "<tr><td style='font-size:8px;'>"
												+ varKey
												+ ":</td><td style='font-size:8px;'>"
												+ varValue
												+ "<td/></tr>";
									}
								});
							tipContent += "</table>";
							return tipContent;
						}
					});
				});
/**
** 获取图片
*/
function getPicture(url){
	var image = $("<img alt=\"流程图\" src='"+url+"' style='position:absolute;'>");
	return image;
};