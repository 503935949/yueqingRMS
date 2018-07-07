<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>YUEQING_RESOUCE_MANAGER_SYSTEM</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<%@ include file="/common/base/include/include.jsp"%>
	
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="index.html"><img src="${contextPath}/static/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
					<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
				</div>
				<form class="navbar-form navbar-left">
					<div class="input-group">
						<input type="text" value="" class="form-control" placeholder="Search dashboard...">
						<span class="input-group-btn"><button type="button" class="btn btn-primary">Go</button></span>
					</div>
				</form>
				<div class="navbar-btn navbar-btn-right">
					<a class="btn btn-success update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
				</div>
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle icon-menu" data-toggle="dropdown">
								<i class="lnr lnr-alarm"></i>
								<span class="badge bg-danger">5</span>
							</a>
							<ul class="dropdown-menu notifications">
								<li><a href="#" class="notification-item"><span class="dot bg-warning"></span>System space is almost full</a></li>
								<li><a href="#" class="notification-item"><span class="dot bg-danger"></span>You have 9 unfinished tasks</a></li>
								<li><a href="#" class="notification-item"><span class="dot bg-success"></span>Monthly report is available</a></li>
								<li><a href="#" class="notification-item"><span class="dot bg-warning"></span>Weekly meeting in 1 hour</a></li>
								<li><a href="#" class="notification-item"><span class="dot bg-success"></span>Your request has been approved</a></li>
								<li><a href="#" class="more">See all notifications</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="lnr lnr-question-circle"></i> <span>Help</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								<li><a href="#">Basic Use</a></li>
								<li><a href="#">Working With Data</a></li>
								<li><a href="#">Security</a></li>
								<li><a href="#">Troubleshooting</a></li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="${contextPath}/static/img/user.png" class="img-circle" alt="Avatar"> <span>Samuel</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								<li><a href="#"><i class="lnr lnr-user"></i> <span>My Profile</span></a></li>
								<li><a href="#"><i class="lnr lnr-envelope"></i> <span>Message</span></a></li>
								<li><a href="#"><i class="lnr lnr-cog"></i> <span>Settings</span></a></li>
								<li><a href="#"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
							</ul>
						</li>
						<!-- <li>
							<a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
						</li> -->
					</ul>
				</div>
			</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul id="menuBar" class="nav">
						
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main" style="height:100%" >
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">

					<iframe src ="${contextPath}/business/common/findHomePage.action" 
					id="yrms_iframe" name="yrms_iframe" frameBorder="0" scrolling="no" width="100%" onLoad="IframeLoadEND();" ></iframe>

				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>
		<footer>
			<div class="container-fluid">
				<p class="copyright">&copy; 2017 <a href="#" target="_blank">Theme I Need</a>. All Rights Reserved. More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
			</div>
		</footer>
	</div>
	<!-- END WRAPPER -->
	
	<script>
	
	</script>
	<script type="text/javascript">
		var menuDate = [

		{	
			dectId:"000000",dectName:"首页",ico:"lnr lnr-home",url:"${contextPath}/business/common/findHomePage.action",hasChild:"true",
			chilNodes:[

			]
		},
		{	
			dectId:"000010",dectName:"ECharts3.0",ico:"lnr lnr-chart-bars",url:"",hasChild:"true",
			chilNodes:[
				{dectId:"000011",dectName:"NPM关系图DEMO",ico:"lnr lnr-file-empty",url:"${contextPath}/business/echarts/findNps_main_demoPage.action",hasChild:"false"},
				{dectId:"000012",dectName:"部分关系图",ico:"lnr lnr-file-empty",url:"${contextPath}/business/echarts/findNps_main_demo_4one.action",hasChild:"false"}
				
			]
		},
		
		{	
			dectId:"000020",dectName:"知识库",ico:"fa fa-database",url:"",hasChild:"true",
			chilNodes:[
				{dectId:"000021",dectName:"LIST_DEMO",ico:"lnr lnr-dice",url:"",hasChild:"false"},
				{dectId:"000022",dectName:"Icons",ico:"lnr lnr-linearicons",url:"",hasChild:"false"}
			]
		},

	];
	
	/* 页面资源整合 */
	var page = {
			home_url : "${contextPath}/business/common/findHomePage.action",	
			iframe_Id : "yrms_iframe"
	}
	/* 修改iframe_src */
	function changeIframeSrc(iframeId,data_url) {
		if(!$.isEmptyObject(data_url)) {
			$("#"+iframeId).attr("src",data_url);
		}
	}
	/* 建立左侧菜单方法 */	
	function buildMenu_left(barId,data) {
		var menuBar = $("#"+barId);
		for(var i=0;i<data.length;i++) {
			var ulParent = $('<li></li>').appendTo(menuBar);
			var node = data[i];
			var menu_head = $('<a href="#'+node.dectId+'" data-toggle="collapse" class="collapsed"><i class="'+node.ico+'"></i> <span>'+node.dectName+'</span> </a>').appendTo(ulParent);
			menu_head.attr("data_url",node.url);
			//添加点击事件，改变iframe的url
			menu_head.click(function(){
				var data_url = $(this).attr("data_url");
				changeIframeSrc(page.iframe_Id,data_url);
			});
			//判断是否有子节点（可强行加标）
			if ( (typeof(node.chilNodes) != "undefined" && node.chilNodes.length>0)) {
				var menu_parent_label = $('<i class="icon-submenu lnr lnr-chevron-left"></i>').appendTo(menu_head)
			}else{
				//判断是否加入徽章
				var exp = node.label;
				if (!$.isEmptyObject(exp))
				{
					//接入徽章
					var a_lv1_label = $('<span class="label label-info">'+ exp +'</span>').appendTo(menu_head);
				}
			}
			//再次判断子节点，递归填入父节点
			if ( (typeof(node.chilNodes) != "undefined" && node.chilNodes.length>0)) {
				var menu_body = $('<ul id="'+node.dectId+'" class="nav nav-list collapse"></ul>').appendTo(menuBar);
				buildMenu_left(node.dectId,node.chilNodes);
			}
		}
	}
	
	/*iframe自适应调整start*/
	//定时调用开始
	var timer
	//完毕后干掉定时器
	function IframeLoadEND(){
		var iframe = document.getElementById(page.iframe_Id);	
		try{
			window.clearInterval(timer);
			var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height = Math.max(bHeight, dHeight);
			iframe.height = height;
		}catch (ex){}
			// 停止定时
		window.clearInterval(timer);
	}
	
	// 定义一个函数，定时调用并刷新iframe高度
	function reinitIframe(){
		var iframe = document.getElementById(page.iframe_Id);
		try{
			var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height = Math.max(bHeight, dHeight);
			iframe.height = height;
		}catch (ex){
			
		}
	};
	/*iframe自适应调整end*/
	
	//启动加载
    $(function() {
        //建立左侧菜单
		buildMenu_left("menuBar",menuDate);
        //打开首页
        changeIframeSrc(page.iframe_Id,page.home_url);
		//定时调用开始
		var timer = window.setInterval("reinitIframe()", 500);
		//

	});


    </script>

</body>

</html>
