<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>首页home</title>
</head>
<body>
<div class="easyui-panel" data-options="fit:true" title="个人首页">
      <div class="easyui-layout" data-options="fit:true" id="index_home">
          <div data-options="region:'east'" style="width:200px;padding:10px">
               <div class="easyui-calendar" style="width:180px;height:180px;"></div>
          </div>
          <div data-options="region:'center'" style="padding:10px">
              <div class="easyui-panel" style="height:100px; padding:5px;" id="demos">
                   <span>
                        <img src="${ctx}/static/images/index/message.png" id="message-icon" title="abcde"/>
                   </span>
              </div>        
              <br>  
	          <div class="easyui-panel" style="padding:5px;">
		          <section class='example' >
			        <div class='gridly'>
			          <div class='brick large'>
			                <div id="title1" style="padding: 5px; background: #ccc; color: #fff">访问来源</div>
			                <div style="width: 100%; height: 90%;" id="chart1"></div>
			          </div>
			          <div class='brick large'>
			                <div id="title2" style="padding: 5px; background: #ccc; color: #fff">销售情况</div>
			                <div style="width: 100%; height: 90%;" id="chart2"></div>
			          </div>
			          <div class='brick large'>
			                <div id="title4" style="padding: 5px; background: #ccc; color: #fff">最新动态</div>
			                <div style="width: 100%; height: 90%;" id="chart4"></div>
			          </div>
			        </div>
			      </section>
		      </div>
          </div>        
          <div data-options="region:'south'" style="height:120px" title="用户日志">
      		<table striped=true id="index_userLogGrid" singleSelect=true fitColumns=true fit=true autoRowHeight=false>
	      		<thead>
		       		<tr>
                		<th data-options="field:'logId',width:30">日志Id</th>
                		<th data-options="field:'logType',width:30">日志类型</th>
                		<th data-options="field:'userId',width:30">用户Id</th>
                		<th data-options="field:'operCode',width:120">操作编码</th>
                		<th data-options="field:'operName',width:120">操作名称</th>
                		<th data-options="field:'operIP',width:100">操作IP</th>
                		<th data-options="field:'remark',width:100">备注</th>
                		<th data-options="field:'crtTimeStr',width:80">创建时间</th>
          		 	 </tr>
		  		</thead>
			</table>
          </div>          
      </div>
</div>
	<script type="text/javascript">
	$(document).ready(function(){
		//==========chart1==========
		jQuery.getScript("${ctx}/plugins/jqPlot/plugins/jqplot.pieRenderer.js", function(){
	          var data = [
	            ['Heavy Industry', 12],['Retail', 9], ['Light Industry', 14], 
	            ['Out of home', 16],['Commuting', 7], ['Orientation', 9]
	          ];
	          var plot1 = jQuery.jqplot ('chart1', [data], 
	            { 
	              seriesDefaults: {
	                // Make this a pie chart.
	                renderer: jQuery.jqplot.PieRenderer, 
	                rendererOptions: {
	                  // Put data labels on the pie slices.
	                  // By default, labels show the percentage of the slice.
	                  showDataLabels: true
	                }
	              }, 
	              legend: { show:true, location: 'e' }
	            }
	          );
	          
			});
		
		//==========chart2==========
		jQuery.getScript("${ctx}/plugins/jqPlot/plugins/jqplot.barRenderer.js", function(){
			// For horizontal bar charts, x an y values must will be "flipped"
		    // from their vertical bar counterpart.
		    var plot2 = $.jqplot('chart2', [
		        [[2,1], [4,2], [6,3], [3,4]], 
		        [[5,1], [1,2], [3,3], [4,4]], 
		        [[4,1], [7,2], [1,3], [2,4]]], {
		        seriesDefaults: {
		            renderer:$.jqplot.BarRenderer,
		            // Show point labels to the right ('e'ast) of each bar.
		            // edgeTolerance of -15 allows labels flow outside the grid
		            // up to 15 pixels.  If they flow out more than that, they 
		            // will be hidden.
		            pointLabels: { show: true, location: 'e', edgeTolerance: -15 },
		            // Rotate the bar shadow as if bar is lit from top right.
		            shadowAngle: 135,
		            // Here's where we tell the chart it is oriented horizontally.
		            rendererOptions: {
		                barDirection: 'horizontal'
		            }
		        },
		        axes: {
		            yaxis: {
		                renderer: $.jqplot.CategoryAxisRenderer
		            }
		        }
		    });
          });
		
		//==========chart4==========
		// We use an inline data source in the example, usually data would
        // be fetched from a server
        var data = [],
            totalPoints = 300;
        function getRandomData() {
            if (data.length > 0)
                data = data.slice(1);
            // Do a random walk
            while (data.length < totalPoints) {
                var prev = data.length > 0 ? data[data.length - 1] : 50,
                    y = prev + Math.random() * 10 - 5;
                if (y < 0) {
                    y = 0;
                } else if (y > 100) {
                    y = 100;
                }
                data.push(y);
            }
            // Zip the generated y values with the x values
            var res = [];
            for (var i = 0; i < data.length; ++i) {
                res.push([i, data[i]])
            }
            return res;
        }
        // Set up the control widget
        var updateInterval = 30;
        var plot = $.plot("#chart4", [ getRandomData() ], {
            series: {
                shadowSize: 0   // Drawing is faster without shadows
            },
            yaxis: {
                min: 0,
                max: 100
            },
            xaxis: {
                show: false
            }
        });
        function update() {
            plot.setData([getRandomData()]);
            // Since the axes don't change, we don't need to call plot.setupGrid()
            plot.draw();
            setTimeout(update, updateInterval);
        }
        update();
        // Add the Flot version string to the footer
        $("#footer").prepend("Flot " + $.plot.version + " &ndash; ");
        
        
        
        //-----------------------------
        $('.gridly').gridly('layout');
        Hg.initFreshPanel("index_home");
        $('#message-icon').tooltipster({
            iconDesktop: true,
            iconTouch: true,
            icon:"2"
        });
        
        $("#index_userLogGrid").datagrid({url:"${ctx}/sys/userLog/list",view:bufferview,queryParams: {
    		rows: 10
    	}});
        
	});
	</script>
</body>
</html>
