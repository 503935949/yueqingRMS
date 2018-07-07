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
		<yrms:demo resName="panel-subtitle" attr="true" >
			DEMO查询列表
		</yrms:demo>	
		</p>
	</div>
	<!-- panel-heading end-->
	<!-- panel-body start-->
	<div class="panel-body">
		<form id="demoSearchForm" class="form-horizontal">
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
						<button id="resetBtn" type="button" class="btn btn-info" >
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
<!-- 		<div class="btn-toolbar pull-right"> -->
<!-- 			<button type="button" class="btn btn-success" data-toggle="modal" data-target="#demoCreateModal" data-whatever="新增"> -->
<%-- 				${i18n_object_add} --%>
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#demoEditModal" data-whatever="编辑"> -->
<%-- 				${i18n_object_edit} --%>
<!-- 			</button> -->
<!-- 			<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#demoDeleteModal" data-whatever="删除"> -->
<%-- 				${i18n_object_del} --%>
<!-- 			</button> -->
<!-- 		</div> -->
<%-- <input type="button" value="${i18n_object_add}" class="btn btn-success" data-toggle="modal" data-target="#demoCreateModal" /> --%>
<%-- 			<input type="button" value="${i18n_object_edit}" class="btn btn-primary" data-toggle="modal" data-target="#demoEditModal" /> --%>
<%-- 			<input type="button" value="${i18n_object_del}" class="btn btn-danger" data-toggle="modal" data-target="#demoDeleteModal" /> --%>

	</div>
</div>
<script type="text/x-kendo-template" id="template">
<div class="toolbar">
	<div class="btn-toolbar pull-right">
			<input type="button" value="${i18n_object_add}" class="btn btn-success" data-toggle="modal" data-target="\\#demoCreateModal" />
			<input type="button" value="${i18n_object_edit}" class="btn btn-primary" data-toggle="modal" data-target="\\#demoEditModal" />
			<input type="button" value="${i18n_object_del}" class="btn btn-danger" data-toggle="modal" data-target="\\#demoDeleteModal" />
	</div>
	<div class="btn-toolbar pull-left">
			<input type="button" id="dt" value="导出" class="btn btn-info" data-toggle="modal"  />
	</div>
</div>
</script>
<div class="form-group">
	<div class="col-xs-12 col-sm-12 col-md-12">
		<!-- layui-table start-->
		<div id="example">
			<div id="grid"></div>
	        <div id="grid0"></div>
        </div>
	</div>	
</div>		
		
<script>
function bindGrid() {
	
	var dataSource = new kendo.data.DataSource({
		transport : {
			   read : {
				   url : contextPath + "/business/knowledgebase/demo/findByWhereForPage.action",
				   dataType : "json",
				   method : "POST",
				   cache:false      //处理不刷新的问题，关闭缓存
			   },
			   parameterMap : function (options,operation) {
				   
				   if (operation == "read") {
				        var parameter = {
				            page : options.page,    //当前页
				            pageSize : options.pageSize//每页显示个数
// 				            take: options.take,
// 				            skip: options.skip
				        };
				    return parameter;
				   }
			   }
			},
			serverPaging : true,
			pageSize : 10,
			schema : {
				data : function (response) {
					if(response.success == false){
						ajaxError (resp);
					}
					return response.result;
				},
				total: function (response) {
					return response.pageInfo.totalRecord;
				}
				
			},
			error: function(e) {
				ajaxError(e);
			}
			
	});
	var $grid = $('#grid');
	$grid.kendoGrid({
    	toolbar: [{template: kendo.template($("#template").html())}],
        dataSource: dataSource,
       // height: 550,
       // groupable: true,//合并表头
        sortable: true,
        resizable: true,
        reorderable: true,
        pageable: true,
        columnMenu: true,
        pageable: {
            pageSizes: true,
            buttonCount: 5,
            page:1,
            numeric:true,
            refresh:true,
            messages: {
                display: "显示{0}-{1}条，共{2}条",
                empty: "没有数据",
                page: "页",
                of: "/ {0}",
                itemsPerPage: "条/页",
                first: "第一页",
                previous: "前一页",
                next: "下一页",
                last: "最后一页",
                refresh: "刷新"
            }
        },
        columns: [{
            title: "<input type='checkbox' class='grid_checkbox_all' />",
            width: 35,
            template:"<input type='checkbox' class='grid_checkbox' />"
        },{
            //field: "orgId",
            template:function (type){
            	return kendo.format(
	          				"<a href='#demoShowModal' data-toggle='modal' data-id-type='{0}' >{0}<a>",
	          				type.orgId
           				);
            },
            title: "机构ID",
            width: 420
        },{
            field: "orgName",
            title: "机构名称",
            width: 420
        }
        
        ],
        selectable:"multiple",
        change: function(e){	//点击其他行的动作
        	//获取grid对象
        	var grid = $grid.getKendoGrid();
 			//获取grid中的行
        	var items = grid.items();
        	//遍历行中的class属性含有grid_checkbox（行checkbox）
        	//如果含有k-state-selected则选中该行的checkbox
 			for(var i=0;i<items.length;i++){
        		var $item = $(items[i]);
        		var $checkbox = $item.find(".grid_checkbox");
        		var checked = $item.hasClass("k-state-selected");
        		if(checked) {
        			$checkbox.prop("checked",true);
        		}else {
        			$checkbox.prop("checked",false);
        		}
        	}
        	//统计一下当前checkbox被选中的数量
        	var checkedNum = 0;
        	$(".grid_checkbox").each(function(){
        		if($(this).prop("checked")) {
        			checkedNum++;
        		}
        	});
			//根据数量判断首部checkbox_all的选中状态（indeterminate：模糊，checked:被选中）
        	if(checkedNum == $(".grid_checkbox").length) {
        		$(".grid_checkbox_all").prop("indeterminate",false);
        		$(".grid_checkbox_all").prop("checked",true);
        	}else if(checkedNum == 0){
        		$(".grid_checkbox_all").prop("indeterminate",false);
        		$(".grid_checkbox_all").prop("checked",false);
        	}else {
        		$(".grid_checkbox_all").prop("indeterminate",true);
        	}
        },excel: {
            fileName: "yueqingRMS.et",	//导出excel文件设置默认名称
            allPages:true			//是否导出所有页中的数据
        }
    });
	
	//点击首部checkbox_all的动作
	$grid.on("click",".grid_checkbox_all",function() {
		var checked = this.checked;
		if(checked) {
			//选中所有grid中的行中checkbox
			$grid.getKendoGrid().items().addClass("k-state-selected");
			$(".grid_checkbox").prop("checked",true);
		}else{
			//取消选中所有grid中的行中checkbox
			$grid.getKendoGrid().items().removeClass("k-state-selected");
			$(".grid_checkbox").prop("checked",false);
		}
	});
	//点击行内checkbox的动作
	$grid.on("click",".grid_checkbox",function() {
		var checked = this.checked;
		var row = $(this).closest("tr");
		//向被点击行的此元素中添加/移除k-state-selected类
		if(checked) {
			row.addClass("k-state-selected");
		}else{
			row.removeClass("k-state-selected");
		}
		//统计一下当前checkbox被选中的数量
		var checkedNum = 0;
    	$(".grid_checkbox").each(function(){
    		if($(this).prop("checked")) {
    			checkedNum++;
    		}
    	});
    	//根据数量判断首部checkbox_all的选中状态（indeterminate：模糊，checked:被选中）
    	if(checkedNum == $(".grid_checkbox").length) {
    		$(".grid_checkbox_all").prop("indeterminate",false);
    		$(".grid_checkbox_all").prop("checked",true);
    	}else if(checkedNum == 0){
    		$(".grid_checkbox_all").prop("indeterminate",false);
    		$(".grid_checkbox_all").prop("checked",false);
    	}else {
    		$(".grid_checkbox_all").prop("indeterminate",true);
    	}
		
	});
	
	
}


function destoryGrid(selector) {
	
	var $grid = $(selector);
	if($grid.data('kendoGrid')) {
		//判断是否已经存在grid实例，有则销毁
		$grid.empty().data('kendoGrid').destory();
	}
	
}

function kendoGrid4Xlsx(){
	var grid = $("#grid").data("kendoGrid");
	 
	//grid.saveAsExcel();
	//在调用saveAsExcel()前对grid._data进行处理
	var grid = $("#grid").data("kendoGrid");
	//打印grid._data发现这里面的数据就是打印出来的数据；
	/*
	*因为grid中显示的数据也是grid._data,所以直接修改grid._data会导致显示的数据也发生变化
	*所以需要先将grid复制一份
	*/
	var excel={};
	//js继承》》》
	$.extend(excel,grid);//此时excel就是grid的副本，拥有和grid一样的属性和函数,对grid的操作可以转移到excel上来进行
	$.each(excel._data,function (i,item) {//逐行处理数据
	//将对数据的处理逻辑放在这里！！！！！！！！！！！！！
	});
	excel.saveAsExcel();
}



$(document).ready(function () {
	
	
	
	
	
	bindGrid();
	
	$("#resetBtn").click(function () {
		bindGrid();
	});
	
	$("#dt").click(function () {
		kendoGrid4Xlsx();
	});
	
	/*
		demo编辑窗口打开事件
	*/
	$('#demoEditModal').on('show.bs.modal', function (e) {
		// e.preventDefault() // 阻止模态框的展示
		var grid = $("#grid").data("kendoGrid");
		var selects = grid.select();
		if(!isSelectOnly(selects.length,"edit")) {
			 e.preventDefault() // 阻止模态框的展示
		}else{
// 			 var dataRows = grid.items();
			// 获取行号
// 			var rowIndex = dataRows.index(selects);
			// 获取行对象
			var data = grid.dataItem(selects);
			var demoOrgId = data.get("orgId");
			console.log(demoOrgId+"    "+data.orgName);
			
		}
		  
	});
	
	/*
		demo编辑窗口打开事件
	*/
	$('#demoDeleteModal').on('show.bs.modal', function (e) {

		var grid = $("#grid").data("kendoGrid");
		var selects = grid.select();
		if(!isSelectOnly(selects.length)) {
			 e.preventDefault() // 阻止模态框的展示
		}else{
			//获取要删除的ID
			var deleteIds = [];
	    	$(selects).each(function(index,item){
	    		// 获取行对象
				var data = grid.dataItem(item);
	    		//将选中项的某字段名称的数据(这里用orgId)
	    		deleteIds.push(data.orgId);
	    	});
	    	//业务逻辑写在这里
	    	
	    	
		}
		   
	});
	
	/*
	demo编辑窗口打开事件
	*/
	$('#demoShowModal').on('show.bs.modal', function (e) {
	
		var button = $(e.relatedTarget) // 触发事件的按钮  
        var orgId = button.data('id-type') // 解析出data-id-type内容 
        //console.log(orgId);
		   
	});
	
	

});
</script>		
<style>
    #grid .k-grid-toolbar
    {
        padding: .6em 1.3em;
    }
    .category-label
    {
        vertical-align: middle;
        padding-right: .5em;
    }
    #category
    {
        vertical-align: middle;
    }
    .toolbar {
        float: right;
    }
</style>		
	
	
	
	