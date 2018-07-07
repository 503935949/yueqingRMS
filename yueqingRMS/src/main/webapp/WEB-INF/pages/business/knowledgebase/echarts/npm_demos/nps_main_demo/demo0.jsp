<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>NPM Dependencies</title>
		
<%-- 		<%@ include file="/common/include.jsp"%> --%>
		<%@ include file="/common/echarts/echarts.jsp"%>
		<script src="${contextPath}/static/js/forPage/business/knowledgebase/echarts/npm_demos/nps_main_demo/nodes.js"></script>
	
	</head>
	
	<body>
	
		<div class="panel panel-headline">
			<div class="panel-heading">
				<h3 class="panel-title">Weekly Overview</h3>
					<p class="panel-subtitle">Period: Oct 14, 2016 - Oct 21, 2016</p>
			</div>
			<div class="panel-body">
				<div class="row">
					<div id= "main"  style="width:1200px;height:800px;" ></div>
				</div>
			</div>		
		</div>
		
		
		
		<script>
		
		var option = {
				fullWidth: true,
				title: {
					text: 'NPM Dependencies'
				},
				animationDurationUpdate: 1500,
				animationEasingUpdate: 'quinticInOut',
				series : [
					{
						type: 'graph',
						layout: 'none',
						// progressiveThreshold: 700,
						data: json.nodes.map(function (node) {
							return {
								x: node.x,
								y: node.y,
								id: node.id,
								name: node.label,
								symbolSize: node.size,
								itemStyle: {
									normal: {
										color: node.color
									}
								}
							};
						}),
						 legend: [{
							// selectedMode: 'single',
							data: json.nodes.map(function (node) {
							return  node.label;
									
							})
						}],
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