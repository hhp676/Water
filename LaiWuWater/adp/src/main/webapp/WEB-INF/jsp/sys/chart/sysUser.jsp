<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户图表</title>
</head>
<body>
	<div  style="height:600px">
		<div id="sysUserMainChart" style="margin-left:20px;margin-top: 50px;height:400px;width: 1000px;">员工生日月份图表</div>
	</div>
	<script type="text/javascript">
		var maleDataStr = "${maleData}";
   		var maleDataAry = maleDataStr.split(",");
   		var femaleDataStr = "${femaleData}";
   		var femaleDataAry = femaleDataStr.split(",");
		var myChart = echarts.init(document.getElementById('sysUserMainChart'));
		var option = {
			title:{text:"员工生日月份图表",left:"-200px;",top:"20"   },
			tooltip: {trigger: "axis"},
            legend: {data:['男','女']},
            xAxis : [{type : 'category',
                      data : ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"]}],
            yAxis : [{type : 'value'}],
            center: ['60%', '70%'],
            series : [{"name":"男","type":"bar","data":maleDataAry},
            		  {"name":"女","type":"bar","data":femaleDataAry}],
           	toolbox: {
            	show : true,
           		feature : {
            		dataView : {show: true, readOnly: false},
            		magicType : {show: true, type: ['line', 'bar']},
            		restore : {show: true},
					saveAsImage: {
				        show:true,
				        excludeComponents :['toolbox'],
				        pixelRatio: 2
					}
            	}
            }

		};
	 
                    
		myChart.setOption(option); 
    </script>
</body>

</html>
