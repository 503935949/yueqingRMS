<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!-- OVERVIEW -->
<div class="panel panel-headline">
	<!-- panel-heading start -->
	<div class="panel-heading">
		<h3 class="panel-title">
			DEMO_LIST
		</h3>
		<p class="panel-subtitle">
			DEMO查询列表
		</p>
	</div>
	<!-- panel-heading end-->
	<!-- panel-body start-->
	<div class="panel-body">
		<form class="form-horizontal">
			<div class="form-group">
			    <label for="exampleInputEmail1" class="col-xs-1 col-sm-1 col-md-1 control-label">Email</label>
			    <div class="col-xs-3 col-sm-3 col-md-3">
			       <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
			    </div>
			     <label for="exampleInputEmail1" class="col-xs-1 col-sm-1 col-md-1 control-label">Email</label>
			    <div class="col-xs-3 col-sm-3 col-md-3">
			       <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
			    </div>
			     <label for="exampleInputEmail1" class="col-xs-1 col-sm-1 col-md-1 control-label">Email</label>
			    <div class="col-xs-3 col-sm-3 col-md-3">
			       <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
			    </div>
			</div>
			<div class="form-group">
			    <label for="exampleInputEmail1" class="col-xs-1 col-sm-1 col-md-1 control-label">Email</label>
			    <div class="col-xs-3 col-sm-3 col-md-3">
			       <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
			    </div>
			    <label for="exampleInputEmail1" class="col-xs-1 col-sm-1 col-md-1 control-label">Email</label>
			    <div class="col-xs-3 col-sm-3 col-md-3">
			       <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
			    </div>
			    <label for="exampleInputEmail1" class="col-xs-1 col-sm-1 col-md-1 control-label">Email</label>
			    <div class="col-xs-3 col-sm-3 col-md-3">
			       <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email">
			    </div>
			</div>
			<div class="form-group">
				<div class="col-xs-12 col-sm-12 col-md-12">
					<div class="btn-toolbar pull-right">
						<button type="button" class="btn btn-default" >
							${i18n_object_search}
						</button>
						<button type="button" class="btn btn-info" >
							${i18n_object_reset}
						</button>
					</div>
				</div>
			</div>
		</form>
		
	</div>
	<!-- panel-body end-->
</div>
<!-- OVERVIEW end-->
<div class="form-group">
	<div class="col-xs-12 col-sm-12 col-md-12">
		<div class="btn-toolbar pull-right">
			<button type="button" class="btn btn-success" data-toggle="modal" data-target="#demoCreateModal" data-whatever="新增">
				${i18n_object_add}
			</button>
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#demoEditModal" data-whatever="编辑">
				${i18n_object_edit}
			</button>
			<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#demoDeleteModal" data-whatever="删除">
				${i18n_object_del}
			</button>
		</div>
	</div>
</div>
<div class="form-group">
	<div class="col-xs-12 col-sm-12 col-md-12">
		<!-- layui-table start-->
		<div class="layui-form">
			  <table class="layui-table">
			    <colgroup>
			      <col width="50">
			      <col >
			      <col >
			      <col>
			      <col>
			    </colgroup>
			    <thead>
			      <tr>
			        <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
			        <th>人物</th>
			        <th>民族</th>
			        <th>出场时间</th>
			        <th>格言</th>
			      </tr> 
			    </thead>
			    <tbody>
			      <tr>
			        <td><input type="checkbox" name="" lay-skin="primary"></td>
			        <td>贤心</td>
			        <td>汉族</td>
			        <td>1989-10-14</td>
			        <td>人生似修行</td>
			      </tr>
			      <tr>
			        <td><input type="checkbox" name="" lay-skin="primary"></td>
			        <td>张爱玲</td>
			        <td>汉族</td>
			        <td>1920-09-30</td>
			        <td>于千万人之中遇见你所遇见的人，于千万年之中，时间的无涯的荒野里…</td>
			      </tr>
			      <tr>
			        <td><input type="checkbox" name="" lay-skin="primary"></td>
			        <td>Helen Keller</td>
			        <td>拉丁美裔</td>
			        <td>1880-06-27</td>
			        <td> Life is either a daring adventure or nothing.</td>
			      </tr>
			      <tr>
			        <td><input type="checkbox" name="" lay-skin="primary"></td>
			        <td>岳飞</td>
			        <td>汉族</td>
			        <td>1103-北宋崇宁二年</td>
			        <td>教科书再滥改，也抹不去“民族英雄”的事实</td>
			      </tr>
			      <tr>
			        <td><input type="checkbox" name="" lay-skin="primary"></td>
			        <td>孟子</td>
			        <td>华夏族（汉族）</td>
			        <td>公元前-372年</td>
			        <td>猿强，则国强。国强，则猿更强！ </td>
			      </tr>
			       <tr>
			        <td><input type="checkbox" name="" lay-skin="primary"></td>
			        <td>贤心</td>
			        <td>汉族</td>
			        <td>1989-10-14</td>
			        <td>人生似修行</td>
			      </tr>
			      <tr>
			        <td><input type="checkbox" name="" lay-skin="primary"></td>
			        <td>张爱玲</td>
			        <td>汉族</td>
			        <td>1920-09-30</td>
			        <td>于千万人之中遇见你所遇见的人，于千万年之中，时间的无涯的荒野里…</td>
			      </tr>
			      <tr>
			        <td><input type="checkbox" name="" lay-skin="primary"></td>
			        <td>Helen Keller</td>
			        <td>拉丁美裔</td>
			        <td>1880-06-27</td>
			        <td> Life is either a daring adventure or nothing.</td>
			      </tr>
			      <tr>
			        <td><input type="checkbox" name="" lay-skin="primary"></td>
			        <td>岳飞</td>
			        <td>汉族</td>
			        <td>1103-北宋崇宁二年</td>
			        <td>教科书再滥改，也抹不去“民族英雄”的事实</td>
			      </tr>
			      <tr>
			        <td><input type="checkbox" name="" lay-skin="primary"></td>
			        <td>孟子</td>
			        <td>华夏族（汉族）</td>
			        <td>公元前-372年</td>
			        <td>猿强，则国强。国强，则猿更强！ </td>
			      </tr>
			    </tbody>
			  </table>
		 </div>
		 <!-- layui-table end-->
		 <div id="pageTool"></div>
	</div>	
</div>		
		
		
		
	
	
	
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
		
		
		
		var pageToolOption;
		var pageSize = 3;
		var rows = 10;
		var yrmsDataSouse = {
				table : function () {
					return $.ajax(arguments[0]).done(function(resp){
// 						pageSize = resp.PageInfo.pageSize;
// 						rows = resp.PageInfo.rows;
						return resp.data;
					}).fail(function(err){
					// 当result为false的回调
						return $.Deferred().reject(err);
					});
				}
		}
// 		pageToolOption = {
// 				cont: "pageTool",
// 				skip: true,
// 			    pages: resp.PageInfo.pageSize,
// 			    groups: resp.PageInfo.rows,
// 			    jump:function(obj) {
// //				    	for(var p in obj){
// //				    		if(typeof(obj[p]) != "function" ) {
// //				    			//alert(p+" ___ "+obj[p]);
// //				    		}
// //				    	}
					
// 				}
// 			}
				 
// 		pageToolBuild (pageToolOption);
		var dataSouse = yrmsDataSouse.table({
				url:'${contextPath}/business/knowledgebase/text/text_List.action',
				data: {
					
				},
				type: 'POST',
				async:'false',
				cache:'fasle',
		})
		.done(function(resp){
			alert(resp);
			return resp;
		}).fail(function(err){
			alert(000);
		});
		
		pageToolBuild ({
				cont: "pageTool",
				skip: true,
			    pages: pageSize,
			    groups: rows,
			    jump: function(obj){
				      document.getElementById('biuuu_city_list').innerHTML = render(data, obj.curr);
				}
			   
		});
		
		//分页组件
		function pageToolBuild (option,data) {
			layui.use(['laypage', 'layer'], function(){
				  var laypage = layui.laypage
				  ,layer = layui.layer;
// 				 var option =[];
// 				 if($.isArray(arguments[0])) {
// 					for(var p in arguments[0]){
// 						 option.push(p);
// 						 alert(p);
// 				    }
// 				 }
// 				 option.push({jump: function(obj){
// 				      document.getElementById('biuuu_city_list').innerHTML = render(data, obj.curr);
// 				    }});
				laypage(option);
			 });
		}
		
		
		$(function(){
// 			$.ajax({
// 					url:'${contextPath}/business/knowledgebase/text/text_List.action',
// 					data: {
// 						orgName : "asdasd"
// 					},
// 					type: 'POST',
// 					async:'false',
// 					cache:'fasle',
			
// 			}).done(function(resp){
// 			// 当result为true的回调
// 				alert(resp);
// 			}).fail(function(err){
// 			// 当result为false的回调
// 			});
		});
		
	</script>
	