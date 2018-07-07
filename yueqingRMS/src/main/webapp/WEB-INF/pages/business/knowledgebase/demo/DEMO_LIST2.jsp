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
		<div class="container-fluid">
			<!-- OVERVIEW -->
<!-- 			<div class="panel panel-headline"> -->
<!-- 				panel-heading start -->
<!-- 				<div class="panel-heading"> -->
<!-- 					<h3 class="panel-title">DEMO_LIST</h3> -->
<!-- 					<p class="panel-subtitle">DEMO查询列表</p> -->
<!-- 				</div> -->
<!-- 				panel-heading end -->
<!-- 				panel-body start -->
<!-- 				<div class="panel-body"> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-xs-3 col-sm-3 col-md-offset-3"> -->
<!-- 							<input type="tel"  class="form-control" name="phone"  placeholder="text field" lay-verify="phone" autocomplete="off" class="layui-input"> -->
<!-- <!-- 							<input type="text" class="form-control" placeholder="text field"> --> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="row"> -->
<!-- 							<div class="col-xs-3 col-sm-3 col-md-3"> -->
<!-- 								<input type="tel"  class="form-control" name="phone"  placeholder="text field" lay-verify="phone" autocomplete="off" class="layui-input"> -->
<!-- 	<!-- 							<input type="text" class="form-control" placeholder="text field"> --> -->
<!-- 							</div> -->
<!-- 							<div class="col-xs-1"> -->
<!-- 							</div> -->
<!-- 							<div class="col-xs-3 col-sm-3 col-md-3"> -->
<!-- 								<input type="tel"  class="form-control" name="phone"  placeholder="text field" lay-verify="phone" autocomplete="off" class="layui-input"> -->
<!-- 	<!-- 							<input type="text" class="form-control" placeholder="text field"> --> -->
<!-- 							</div> -->
<!-- 							<div class="col-xs-1"> -->
<!-- 							</div> -->
<!-- 							<div class="col-xs-3 col-sm-3 col-md-3"> -->
<!-- 								<input type="tel"  class="form-control" name="phone"  placeholder="text field" lay-verify="phone" autocomplete="off" class="layui-input"> -->
<!-- 	<!-- 							<input type="text" class="form-control" placeholder="text field"> --> -->
<!-- 							</div> -->
<!-- 					</div> -->
<!-- 					<div class="row"> -->
<!-- 						<div class="col-md-12"> -->
<!-- 							layui-table start -->
<!-- 							<div class="layui-form"> -->
<!-- 								  <table class="layui-table"> -->
<!-- 								    <colgroup> -->
<!-- 								      <col width="50"> -->
<!-- 								      <col width="150"> -->
<!-- 								      <col width="150"> -->
<!-- 								      <col> -->
<!-- 								      <col> -->
<!-- 								    </colgroup> -->
<!-- 								    <thead> -->
<!-- 								      <tr> -->
<!-- 								        <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th> -->
<!-- 								        <th>人物</th> -->
<!-- 								        <th>民族</th> -->
<!-- 								        <th>出场时间</th> -->
<!-- 								        <th>格言</th> -->
<!-- 								      </tr>  -->
<!-- 								    </thead> -->
<!-- 								    <tbody> -->
<!-- 								      <tr> -->
<!-- 								        <td><input type="checkbox" name="" lay-skin="primary"></td> -->
<!-- 								        <td>贤心</td> -->
<!-- 								        <td>汉族</td> -->
<!-- 								        <td>1989-10-14</td> -->
<!-- 								        <td>人生似修行</td> -->
<!-- 								      </tr> -->
<!-- 								      <tr> -->
<!-- 								        <td><input type="checkbox" name="" lay-skin="primary"></td> -->
<!-- 								        <td>张爱玲</td> -->
<!-- 								        <td>汉族</td> -->
<!-- 								        <td>1920-09-30</td> -->
<!-- 								        <td>于千万人之中遇见你所遇见的人，于千万年之中，时间的无涯的荒野里…</td> -->
<!-- 								      </tr> -->
<!-- 								      <tr> -->
<!-- 								        <td><input type="checkbox" name="" lay-skin="primary"></td> -->
<!-- 								        <td>Helen Keller</td> -->
<!-- 								        <td>拉丁美裔</td> -->
<!-- 								        <td>1880-06-27</td> -->
<!-- 								        <td> Life is either a daring adventure or nothing.</td> -->
<!-- 								      </tr> -->
<!-- 								      <tr> -->
<!-- 								        <td><input type="checkbox" name="" lay-skin="primary"></td> -->
<!-- 								        <td>岳飞</td> -->
<!-- 								        <td>汉族</td> -->
<!-- 								        <td>1103-北宋崇宁二年</td> -->
<!-- 								        <td>教科书再滥改，也抹不去“民族英雄”的事实</td> -->
<!-- 								      </tr> -->
<!-- 								      <tr> -->
<!-- 								        <td><input type="checkbox" name="" lay-skin="primary"></td> -->
<!-- 								        <td>孟子</td> -->
<!-- 								        <td>华夏族（汉族）</td> -->
<!-- 								        <td>公元前-372年</td> -->
<!-- 								        <td>猿强，则国强。国强，则猿更强！ </td> -->
<!-- 								      </tr> -->
<!-- 								    </tbody> -->
<!-- 								  </table> -->
<!-- 							</div> -->
<!-- 							layui-table end -->
<!-- 							<div id="pageTool"></div> -->
<!-- 						</div>	 -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				panel-body end -->
<!-- 			</div> -->
			<!-- OVERVIEW end-->
		</div>
		<!-- container-fluid end-->
		
		
		
	
	
	</body>
	<script>
		//table 组件
		layui.use('form', function(){
		  var $ = layui.jquery, form = layui.form();
		  
		  //全选
		  form.on('checkbox(allChoose)', function(data){
		    var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
		    child.each(function(index, item){
		      item.checked = data.elem.checked;
		    });
		    form.render('checkbox');
		  });
		  
		});
		var pageToolOption = {
			    cont: "pageTool"
			    ,pages: 8
			    ,groups: 5
			    ,skip: true
			    ,jump:function(obj) {
				    	for(var p in obj){
				    		if(typeof(obj[p]) != "function" ) {
				    			//alert(p+" ___ "+obj[p]);
				    		}
				    	}
					}
		}
		pageToolBuild(pageToolOption);
		
		//分页组件
		function pageToolBuild (option) {
			layui.use(['laypage', 'layer'], function(){
				  var laypage = layui.laypage
				  ,layer = layui.layer;
				laypage(option);
			 });
		}
		
	</script>
	

</html>