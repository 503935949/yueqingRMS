<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>YRMS</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    

    
	<%@ include file="/common/base/include/include.jsp"%>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .brand { font-family: georgia, serif; }
        .brand .first {
            color: #ccc;
            font-style: italic;
        }
        .brand .second {
            color: #fff;
            font-weight: bold;
        }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  </head>

  <!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
  <!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
  <!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
  <!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
  <!--[if (gt IE 9)|!(IE)]><!--> 
  <body class=""> 
  <!--<![endif]-->
    
    <div class="navbar">
        <div class="navbar-inner">
<!--                 <ul class="nav pull-right"> -->
                    
<!--                     <li><a href="#" class="hidden-phone visible-tablet visible-desktop" role="button">Settings</a></li> -->
<!--                     <li id="fat-menu" class="dropdown"> -->
<!--                         <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> -->
<!--                             <i class="icon-user"></i> Jack Smith -->
<!--                             <i class="icon-caret-down"></i> -->
<!--                         </a> -->

<!--                         <ul class="dropdown-menu"> -->
<!--                             <li><a tabindex="-1" href="#">My Account</a></li> -->
<!--                             <li class="divider"></li> -->
<!--                             <li><a tabindex="-1" class="visible-phone" href="#">Settings</a></li> -->
<!--                             <li class="divider visible-phone"></li> -->
<!--                             <li><a tabindex="-1" href="sign-in.html">Logout</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
                    
<!--                 </ul> -->
                <a class="brand" href="index.html"><span class="first">YRMS</span> <span class="second">Company</span></a>
        </div>
    </div>
    


    
    <div id="menuBar" class="sidebar-nav">
      
    </div>
    

    
    <div class="content">
        
<!-- 					<div class="header"> -->
<!-- 						<div class="stats"> -->
<!-- 							<p class="stat"><span class="number">53</span>tickets</p> -->
<!-- 							<p class="stat"><span class="number">27</span>tasks</p> -->
<!-- 							<p class="stat"><span class="number">15</span>waiting</p> -->
<!-- 						</div> -->

<!-- 						<h1 class="page-title">Dashboard</h1> -->
<!-- 					</div> -->
					
<!-- 					<ul class="breadcrumb"> -->
<!-- 						<li><a href="index.html">Home</a> <span class="divider">/</span></li> -->
<!-- 						<li class="active">Dashboard</li> -->
<!-- 					</ul> -->

<!-- 					<div class="container-fluid"> -->
<!-- 						<div class="row-fluid">																 -->
<!-- 								<footer> -->
<!-- 									<hr> -->
<!-- 									Purchase a site license to remove this link from the footer: http://www.portnine.com/bootstrap-themes -->
<!-- 									<p class="pull-right">A <a href="http://www.portnine.com/bootstrap-themes" target="_blank">Free Bootstrap Theme</a> by <a href="http://www.portnine.com" target="_blank">Portnine</a></p> -->
<!-- 									<p>&copy; 2012 <a href="http://www.portnine.com" target="_blank">Portnine</a></p> -->
<!-- 								</footer>								 -->
<!-- 						</div> -->
<!-- 					</div> -->
					
					
    </div>
    


    
    <script type="text/javascript">
		var menuDate = [

		{	
			dectId:"000000",dectName:"一级（1）",ico:"icon-dashboard",url:"",hasChild:"true",
			chilNodes:[

			]
		},
		{	
			dectId:"000010",dectName:"一级（1）",ico:"icon-dashboard",url:"",hasChild:"true",
			chilNodes:[
				{dectId:"000011",dectName:"二级（1）",ico:"icon-dashboard",url:"",hasChild:"false"},
				{dectId:"000012",dectName:"二级（2）",ico:"icon-dashboard",url:"",hasChild:"false"},
				{dectId:"000013",dectName:"二级（3）",ico:"icon-dashboard",url:"",hasChild:"false"},
				{dectId:"000014",dectName:"二级（4）",ico:"icon-dashboard",url:"",hasChild:"false"},
				{dectId:"000015",dectName:"二级（5）",ico:"icon-dashboard",url:"",hasChild:"false"},
				{dectId:"000016",dectName:"二级（6）",ico:"icon-dashboard",url:"",hasChild:"false"},
				
			]
		},
		
		{	
			dectId:"000020",dectName:"一级（1）",ico:"icon-dashboard",url:"",hasChild:"true",
			chilNodes:[
				{dectId:"000021",dectName:"二级（1）",ico:"icon-dashboard",url:"",hasChild:"false"},
				{dectId:"000022",dectName:"二级（2）",ico:"icon-dashboard",url:"",hasChild:"false"},
				{dectId:"000023",dectName:"二级（3）",ico:"icon-dashboard",url:"",hasChild:"false"},
				{dectId:"000024",dectName:"二级（4）",ico:"icon-dashboard",url:"",hasChild:"false"},
				{dectId:"000025",dectName:"二级（5）",ico:"icon-dashboard",url:"",hasChild:"false"},
				{dectId:"000026",dectName:"二级（6）",ico:"icon-dashboard",url:"",hasChild:"false"},
				
			]
		},

	];
		
		
	function buildMenu_left_one(barId,data) {
		
		var menuBar = $("#"+barId);
		for(var i=0;i<data.length;i++) {
			
			var node = data[i];
			var menu_head = $('<a href="#'+node.dectId+'" class="nav-header collapsed" data-toggle="collapse"><i class="'+node.ico+'"></i>'+node.dectName+'</a>').appendTo(menuBar);
			//判断是否加入徽章
			var exp = node.label;
			if (!(!exp && typeof(exp)!="undefined" && exp!=0))
			{
				//接入徽章
				var a_lv1_label = $('<span class="label label-info">'+ exp +'</span>').appendTo(menu_head);
			}
			//判断是否有子节点（可强行加标）
			if ( (typeof(node.chilNodes) != "undefined" && node.chilNodes.length>0)) {
				var menu_parent_label = $('<i class="icon-chevron-up"></i>').appendTo(menu_head)
			}
			
			var menu_body = $('<ul id="'+node.dectId+'" class="nav nav-list collapse"></ul>').appendTo(menuBar);
			
			//再次判断子节点，递归填入父节点
			if ( (typeof(node.chilNodes) != "undefined" && node.chilNodes.length>0)) {
				buildMenu_left_two(node.dectId,node.chilNodes);
			}
		}
		
	}
	
	function buildMenu_left_two(barId,data) {
		
		var menuBar = $("#"+barId);
		for(var i=0;i<data.length;i++) {
			
			var node = data[i];
			//var menu_head = $('<a href="#'+node.dectId+'" class="nav-header" data-toggle="collapse"><i class="'+node.ico+'"></i>'+node.dectName+'</a>').appendTo(menuBar);
			var menu_head = $('<li><a href="'+node.url+'">'+node.dectName+'</a></li>').appendTo(menuBar);
			
			//判断是否加入徽章
			var exp = node.label;
			if ((!exp && typeof(exp)!="undefined" && exp!=0)&& exp !="undefined")
			{
				//接入徽章
				var a_lv1_label = $('<span class="label label-info">'+ "sss" +'</span>').appendTo(menu_head);
			}
			
		}
		
	}
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
			buildMenu_left_one("menuBar",menuDate);
        });
    </script>
  </body>
</html>


