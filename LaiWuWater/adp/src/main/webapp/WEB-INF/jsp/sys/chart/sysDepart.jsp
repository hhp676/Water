<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE HTML >
<html>
<head>
<title>部门图表</title>
</head>
<body>
	<div id="sysDepartMainChart" style="height:500px;width: 900px;">部门图表</div>
	<script type="text/javascript">
		var itemStyle = {normal: {label: {textStyle: {color: '#000'}},nodeStyle : {brushType : 'both',strokeColor : 'rgba(255,215,0,0.4)',lineWidth : 2}}}
		var itemStyle1 = {normal:{label:{show:true},color:"#0099ff"}};
		var itemStyle2 = {normal:{label:{show:true},color:"#999fff"}};
		var myChart = echarts.init(document.getElementById('sysDepartMainChart'));
		 var option = {
			title:{text:"组织架构图",subtext:"force图",x:"center",y:"top"},
			toolbox: {
				    show: true,
					feature: {
						saveAsImage: {
					        show:true,
					        excludeComponents :['toolbox'],
					        pixelRatio: 2
						}
				    }
			},
			series:[{
				type:"force",
				symbol:"rectangle",
				itemStyle:itemStyle,
				categories:[{name:"公司",symbolSize:30,itemStyle:itemStyle1},{name:"部门",symbolSize:15,itemStyle:itemStyle2}],
				nodes:[{name:"鸿冠",category:0,initial:[450,100],fixX:true,fixY:true,id:0},
				       {name:"aaaaa",category:1,id:1,initial:[10,200],fixY:true,fixX:false},
				       {name:"bbbbb",category:1,id:2,initial:[20,200],fixY:true,fixX:false},
				       {name:"ccccc",category:1,id:3,initial:[30,200],fixY:true,fixX:false},
				       {name:"dddddddd",category:1,id:4,initial:[10,300],fixY:true,fixX:false},
				       {name:"dddddddd",category:1,id:5,initial:[20,300],fixY:true,fixX:false},
				       {name:"dddddddd",category:1,id:6,initial:[30,300],fixY:true,fixX:false},
				       {name:"dddddddd",category:1,id:7,initial:[40,300],fixY:true,fixX:false}],
				links:[{source:0,target:1},{source:0,target:2},{source:0,target:3},
				       {source:1,target:4},{source:2,target:5},{source:3,target:6},{source:3,target:7}]
			}
			]
		}; 	


		myChart.setOption(option); 
    </script>
</body>

</html>
