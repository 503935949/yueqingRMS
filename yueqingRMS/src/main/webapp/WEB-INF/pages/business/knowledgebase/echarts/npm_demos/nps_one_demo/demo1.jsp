<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>NPM Dependencies</title>
		
<%-- 		<%@ include file="/common/include.jsp"%> --%>
		<%@ include file="/common/echarts/echarts.jsp"%>
		<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/npm_demos/nps_one_demo/nodes.js"></script>
	
	</head>
	
	<body>
	
	
		<div class="panel panel-headline">
			<div class="panel-heading">
<!-- 				<h3 class="panel-title">YRMS Echarts DEMO </h3> -->
<!-- 					<p class="panel-subtitle">单个系统及关联关系</p> -->
			</div>
			<div class="panel-body">
				<div class="row">
					<div id= "main"  style="width:1200px;height:800px;" ></div>
				</div>
			</div>		
		</div>
		<script>
		var categories = [];
	    for (var i = 0; i < json.nodes.length; i++) {
	        categories[i] = {
	            name: json.nodes[i].label
	        };
	    }
	    json.nodes.forEach(function (node) {
	        node.category = node.label;
	    });
		
		var option = {
				title: {
					text: 'NPM Dependencies one for others'
				},
				//animationDurationUpdate: 1500,
				//animationEasingUpdate: 'quinticInOut',
				legend: [{
					// selectedMode: 'single',
					show:true,
					align:'left',
					top :'0',
					orient : 'vertical',
					data: categories.map(function (a) {
		                return a.name;
		            })
				}],
				series : [
					{
						type: 'graph',
						layout: 'force',
						// progressiveThreshold: 700,
						data: json.nodes.map(function (node) {
							return {
								name: node.label,
								x: node.x,
								y: node.y,
								id: node.id,
								symbolSize: node.size,
								itemStyle: {
									normal: {
										color: node.color
									}
								}
							};
						}),
						categories: categories,
						edges: json.edges.map(function (edge) {
							return {
								source: edge.sourceID,
								target: edge.targetID
							};
						}),
						label: {
							emphasis: {
								position: 'right',
								show: true
							}
						},
						roam: true,
						focusNodeAdjacency: true,
						lineStyle: {
							normal: {
								width: 0.5,
								curveness: 0.3,
								opacity: 0.7
							}
						}
					}
				]
		}
		// 第二个参数可以指定前面引入的主题
		var myChart = echarts.init(document.getElementById('main'), 'macarons');
		myChart.showLoading();
		//function (data) {
		myChart.hideLoading();
		myChart.setOption(option, true);
		</script>
		
		
	
	
	</body>

</html>