<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 	<%@ include file="../language/changeLanguage.jsp"%> --%>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>YUEQING_RESOUCE_MANAGER_SYSTEM</title>
	<%@ include file="/common/base/include/include.jsp"%>
<!-- 	<meta charset="utf-8"> -->
<!-- 	<meta name="renderer" content="webkit"> -->
<!-- 	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->
<!-- 	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"> -->
	
	
  
</head>

<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="${contextPath}/business/common/main.action" class="btn-toggle-fullwidth" ><img src="${contextPath}/static/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
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
<!-- 				<div class="navbar-btn navbar-btn-right"> -->
<!-- 					<a class="btn btn-success update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a> -->
<!-- 				</div> -->
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
<%-- 							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="${contextPath}/static/img/user.png" class="img-circle" alt="Avatar"> <span>Samuel</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a> --%>
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="${contextPath}/static/img/user.png" class="img-circle" alt="Avatar">  <i class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								<li><a href="#"><i class="lnr lnr-user"></i> <span>My Profile</span></a></li>
								<li><a href="#"><i class="lnr lnr-envelope"></i> <span>Message</span></a></li>
								<li><a href="#"><i class="lnr lnr-cog"></i> <span>Settings</span></a></li>
								<li><a href="#"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
							</ul>
						</li>
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
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								<i class="lnr lnr-question-circle"></i>
								<span>
									<spring:message code="object.home.language" arguments="" />
								</span>
								<i class="icon-submenu lnr lnr-chevron-down"></i>
							</a>
							<ul class="dropdown-menu">
								<li onclick="interna('Chinese');">
									<a href="#"  >
										<!-- 中文 -->
										<spring:message code="object.home.chinese" arguments="" />
									</a>
								</li>
								<li onclick="interna('English');">
									<a href="#" >
										<!-- 英文 -->
										<spring:message code="object.home.english" arguments="" />
									</a>
								</li>
<!-- 								<li><a href="#">Security</a></li> -->
<!-- 								<li><a href="#">Troubleshooting</a></li> -->
							</ul>
						</li>
						
<!--  						<li>  -->
<!-- 							<a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a> -->
<!-- 						</li>  -->
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
<!-- 			<div class="main-content"> -->
				<div class="container-fluid" id="main_tabs" >
					<div class="layui-tab layui-tab-brief" id="tabsBar" lay-filter="tabsBar" lay-allowclose="true">
					  <ul class="layui-tab-title">
					   
					  </ul>
					  <div class="layui-tab-content minwidth_572" id="tab_iframes" >
					   
					  </div>
					</div>
<%-- 					<iframe src ="${contextPath}/business/common/findHomePage.action"  --%>
<!-- 					id="yrms_iframe" name="yrms_iframe" frameBorder="0" scrolling="no" width="100%" onLoad="IframeLoadEND();" ></iframe> -->

<!-- 				</div> -->
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		
	</div>
	<div class="clearfix"></div>
		<footer>
			<div class="container-fluid">
				<p class="copyright">&copy; 2017 <a href="#" target="_blank">Theme I Need</a>. All Rights Reserved. More Templates <a href="" target="_blank" title=""></a> - Collect from <a href="" title="" target="_blank"></a></p>
			</div>
		</footer>
	<!-- END WRAPPER -->
	
	<script type="text/javascript">
		var menuDate = [

		{	
			resourceId:"000000",resourceName:"首页",ico:"lnr lnr-home",url:"${contextPath}/business/common/findHomePage.action",hasChild:"true",
			chilNodes:[

			]
		},
		{	
			resourceId:"000010",resourceName:"ECharts3.0",ico:"lnr lnr-chart-bars",url:"",hasChild:"true",
			chilNodes:[
				{resourceId:"000011",resourceName:"NPM关系图DEMO",ico:"lnr lnr-file-empty",url:"${contextPath}/business/echarts/findNps_main_demoPage.action",hasChild:"false"},
				{resourceId:"000012",resourceName:"部分关系图",ico:"lnr lnr-file-empty",url:"${contextPath}/business/echarts/findNps_main_demo_4one.action",hasChild:"false"},
				{resourceId:"000013",resourceName:"地图下钻",ico:"lnr lnr-file-empty",url:"${contextPath}/business/echarts/find_chinamaps.action",hasChild:"false"},
				{resourceId:"000013",resourceName:"条形图_双Y轴",ico:"lnr lnr-file-empty",url:"${contextPath}/business/echarts/find_bar_demo1.action",hasChild:"false"},
				{resourceId:"000013",resourceName:"条形图_3D",ico:"lnr lnr-file-empty",url:"${contextPath}/business/echarts/find_bar_3D_BAR_DEMO.action",hasChild:"false"}
				
			]
		},
		
		{	
			resourceId:"000020",resourceName:"知识库",ico:"fa fa-database",url:"",hasChild:"true",
			chilNodes:[
				{resourceId:"000021",resourceName:"LIST_DEMO",ico:"lnr lnr-dice",url:"${contextPath}/business/knowledgebase/demo/findDemo_List_IndexPage.action",hasChild:"false"},
				{resourceId:"000022",resourceName:"Icons",ico:"lnr lnr-linearicons",url:"",hasChild:"false"},
				{resourceId:"000023",resourceName:"文件上传",ico:"lnr lnr-linearicons",url:"${contextPath}/business/knowledgebase/uploadfile/findUpFile_IndexPage.action",hasChild:"false"},
				{resourceId:"000024",resourceName:"文件下载",ico:"lnr lnr-linearicons",url:"${contextPath}/business/knowledgebase/downloadfile/findDownFile_IndexPage.action",hasChild:"false"},
				{resourceId:"000025",resourceName:"表格文件读写",ico:"lnr lnr-linearicons",url:"${contextPath}/business/knowledgebase/tablefilereadandwrite/findTableFileReadAndWrite_IndexPage.action",hasChild:"false"},
				{resourceId:"000025",resourceName:"邮件",ico:"lnr lnr-linearicons",url:"${contextPath}/business/knowledgebase/mail/findMail_IndexPage.action",hasChild:"false"}
			]
		},

	];
	
	/* 页面资源整合 */
	var page = {
			home_url : "${contextPath}/business/common/findHomePage.action",	
			iframe_Id : "yrms_iframe"
	}
// 	layui.use('element', function(){
// 		  var $ = layui.jquery
// 		  ,element = layui.element();
// 	});
	
	//启动加载
    $(function() {
//     	var timer = window.setInterval("reinitIframe()", 500);
        //建立左侧菜单
        yrms.buildMenu_left("menuBar",menuDate);
		//打开第一个TAB页面（打开首页）
		if(menuDate.length > 0 && !$.isEmptyObject(menuDate[0].url)) {
			yrms.buildorChangeTabs (yrms.tabBar,menuDate[0].resourceId,menuDate[0].resourceName,menuDate[0].url)
		}
		//layUI _ layer 模态框组件全局初始化
		commonDialogConfig({
			resize:false //不可拉伸
			//skin: 'layui-layer-molv' //默认皮肤
		});
		
	});
    /*-----------------------------------/
    /*	 后台国际化切换
    /*----------------------------------*/
    function interna(language){
    	var _url = '${contextPath}/business/common/interna.action?language='+language;
    	$.ajax({
    		type : 'post',
    		url : _url,
    		dataType : 'json',
    		success : function(data){
    			if(data.isReload == "true"){
    				yrms.sourceName = "message";
    				yrms.path = contextPath+"/language/business/";
    				yrms.language = data.locale;
    				
    				
    				//前台获取资源文件（唯一）
//     				yrms.getI18nResouse(
//     					"message",
//     					"${contextPath}/language/business/",
//     					yrms.locale,
//     					function(){
//     						alert(message("object.title"));
//     					}
//     				);
    				top.location.reload();
    			}
    		}
    	});
    }


    </script>

</body>

</html>
