<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
	<head>
		<title>DEMO_LIST</title>
		<%@ include file="/common/base/include/include.jsp"%>
	</head>
	<body>
		<!-- container-fluid start-->
		<div class="container-fluid panel">
			<!-- OVERVIEW -->
			<%@ include file="DemoCreateModal.jsp"%>
			<%@ include file="DemoEditModal.jsp"%>
			<%@ include file="DemoDeleteModal.jsp"%>
			<%@ include file="DemoSearch.jsp"%>
			<%@ include file="DemoShowModal.jsp"%>
			<!-- OVERVIEW end-->
		</div>
		<!-- container-fluid end-->
	</body>
	<script src="${contextPath}/static/js/forPage/business/knowledgebase/demo/demo.js"></script>
	<script>
// 		//table 组件
// 		layui.use('form', function(){
// 		  var $ = layui.jquery, form = layui.form();
		  
// 		  //全选
// 		  form.on('checkbox(allChoose)', function(data){
// 		    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
// 		    child.each(function(index, item){
// 		      item.checked = data.elem.checked;
// 		    });
// 		    form.render('checkbox');
// 		  });
		  
// 		});
// 		var pageToolOption = {
// 			    cont: "pageTool"
// 			    ,pages: 8
// 			    ,groups: 5
// 			    ,skip: true
// 			    ,jump:function(obj) {
// 				    	for(var p in obj){
// 				    		if(typeof(obj[p]) != "function" ) {
// // 				    			alert(p+" ___ "+obj[p]);
// 				    		}
// 				    	}
// 					}
// 		}
// 		pageToolBuild(pageToolOption);
		
// 		//分页组件
// 		function pageToolBuild (option) {
// 			layui.use(['laypage', 'layer'], function(){
// 				  var laypage = layui.laypage
// 				  ,layer = layui.layer;
// 				laypage(option);
// 			 });
// 		}
		
	</script>
	

</html>