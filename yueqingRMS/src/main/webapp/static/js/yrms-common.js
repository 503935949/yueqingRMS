/************************************************************************************
* 
* 项目名称：yueqingRMS   
* 类名称：yrms-common.js   
* 类描述：YRMS、前端框架支持js
* 创建人：林曌   
* 创建时间：2017年7月10日 上午11:16:55   
* 修改人：   
* 修改时间：2017年7月10日 上午11:16:55   
* 修改备注：   
* @version  
*  
************************************************************************************/

var yrms = {
		tabBar : "tabsBar",
		locale : "zh_CN",
		sourceName : "message" ,
		path  : "" ,
		language: "zh_CN" ,
		/* 建立左侧菜单方法 */	
		buildMenu_left :function (barId,data) {
			var menuBar = $("#"+barId);
			for(var i=0;i<data.length;i++) {
				var ulParent = $('<li></li>').appendTo(menuBar);
				var node = data[i];
				var menu_head = $('<a href="#'+node.resourceId+'" data-toggle="collapse" class="collapsed"><i class="'+node.ico+'"></i> <span>'+node.resourceName+'</span> </a>').appendTo(ulParent);
				menu_head.attr("data_url",node.url);
				menu_head.attr("data_tabId",node.resourceId);
				menu_head.attr("data_tabTitle",node.resourceName);
				//添加点击事件，改变iframe的url
				menu_head.click(function(){
					
					var data_tabId = $(this).attr("data_tabId");
					var data_tabTitle = $(this).attr("data_tabTitle");
					var data_url = $(this).attr("data_url");
					if(!$.isEmptyObject(data_url)) {
						yrms.buildorChangeTabs (yrms.tabBar,data_tabId,data_tabTitle,data_url);
						//changeIframeSrc(page.iframe_Id,data_url);
						//timer = window.setInterval("reinitIframe()", 500);
					}else {
						
						if(!(typeof(node.chilNodes) != "undefined" && node.chilNodes.length>0)){
							simpleTypeDialog("功能开发中。。。",'parent','lock','0');
						}
						
					}
					
				});
				//判断是否有子节点（可强行加标）
				if ( (typeof(node.chilNodes) != "undefined" && node.chilNodes.length>0)) {
					var menu_parent_label = $('<i class="icon-submenu lnr lnr-chevron-left"></i>').appendTo(menu_head)
				}else{
					//判断是否加入徽章
					var exp = node.label;
					if (!$.isEmptyObject(exp)){
						//接入徽章
						var a_lv1_label = $('<span class="label label-info">'+ exp +'</span>').appendTo(menu_head);
					}
				}
				//再次判断子节点，递归填入父节点
				if ( (typeof(node.chilNodes) != "undefined" && node.chilNodes.length>0)) {
					var menu_body = $('<ul id="'+node.resourceId+'" class="nav nav-list collapse"></ul>').appendTo(menuBar);
					yrms.buildMenu_left(node.resourceId,node.chilNodes);
				}
			}
		},
		buildorChangeTabs : function (lay_filter,data_tabId,data_tabTitle,data_url){
			
			layui.use('element', function(){
				  var $ = layui.jquery
				  ,element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块
				  ifeame_id = data_tabId+"_iframe";
				  timer = window.setInterval("reinitIframe()", 500);
				  //判断是否已存在此ID的tab
				  var lis = $('ul.layui-tab-title').find('li');
				  var isEmpty = true;
				  for(var i=0;i<lis.length;i++) {
					  if($(lis[i]).attr("lay-id") == data_tabId) {
						   isEmpty = false;
					  }
					 
				  }
				  //触发事件
				  if(isEmpty) {
					  //定时调用开始
					 // timer = window.setInterval("reinitIframe()", 500);
					  //新增一个Tab项
					  element.tabAdd(lay_filter, {
						title: data_tabTitle 
						,content:   '<iframe src="'+ data_url +'" '+ 
									'id="'+data_tabId+'_iframe" name="'+data_tabId+'_iframe" '+
									'frameBorder="0" scrolling="no" width="100%" '+
									'onLoad="IframeLoadEND();" ></iframe>'
						,id: data_tabId //实际使用一般是规定好的id
					  });
					  yrms.tabChange(lay_filter, data_tabId); //切换到data_tabId
					  
				  } else {
					   //切换到指定Tab项
					  yrms.tabChange(lay_filter, data_tabId); //切换到
				  }
					  
				  
				  
				  //Hash地址的定位
				  //var layid = location.hash.replace(/^#tabsBar=/, '');
				  //element.tabChange(lay_filter, layid);
				  
				  //element.on('tab('+lay_filter+')', function(elem){
					//location.hash = lay_filter+'='+ $(this).attr('lay-id');
				 //});
				  
			});
					
		},
		tabChange : function (lay_filter, data_tabId) {
			layui.use('element', function(){
				  var $ = layui.jquery
				  ,element = layui.element();
				element.tabChange(lay_filter, data_tabId);
			});
		},
		getI18nResouse : function (sourceName,path,language,fun) {
			/* 需要引入 i18n 文件*/
	        if ($.i18n == undefined) {
	            console.log("请引入i18n js 文件")
	            return false;
	        };
	        /*
	        	这里需要进行i18n的翻译
	         */
	        jQuery.i18n.properties({
	            name : sourceName, //资源文件名称
	            path : path +'/', //资源文件路径
	            mode : 'map', //用Map的方式使用资源文件中的值
	            language : language,
	            callback : fun
	        });
		}
		
}



/* 修改iframe_src */
//function changeIframeSrc(iframeId,data_url) {
//	if(!$.isEmptyObject(data_url)) {
//		$("#"+iframeId).attr("src",data_url);
//		timer = window.setInterval("reinitIframe()", 500);
//	}
//}

/*-----------------------------------/
/*		iframe自适应调整 start
/*----------------------------------*/
//定时调用开始
var timer = window.setInterval("reinitIframe()", 500);
var ifeame_id ="";
//完毕后干掉定时器
function IframeLoadEND(){
	//修改外层DIV高度，适应内容
	var iframe = document.getElementById(ifeame_id);	
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
	
	//修改外层DIV高度，适应内容
	var iframe = document.getElementById(ifeame_id);
	try{
		var bHeight = iframe.contentWindow.document.body.scrollHeight;
		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
		var height = Math.max(bHeight, dHeight);
		iframe.height = height;
	}catch (ex){
		
	}
};
/*-----------------------------------/
/*		iframe自适应调整end    */
/*----------------------------------*/
		
/*-----------------------------------/
/*	handleAjax   start
/*----------------------------------*/
//ajax(arguments).then(function(resp){
//// 成功回调
//if(resp.result){
//	return resp.data; // 直接返回要处理的数据，作为默认参数传入之后done()方法的回调
//}
//else{
//	return ajaxError(resp);
////	return $.Deferred().reject(resp.msg); // 返回一个失败状态的deferred对象，把错误代码作为默认参数传入之后fail()方法的回调
//}

//(1)
//定义ajax参数对象
//function ajaxElement(){
//	this.url; 
//	this.type; 
//	this.async; 
//	this.cache; 
//	this.data; 
//	this.timeout;
//	this.dataType;
//	this.beforeSend;
//	this.contentType;
//	this.dataFilter;
//	this.global;
//	this.ifModified;
//	this.jsonp;
//	this.username;
//	this.password;
//	this.processData;
//	this.scriptCharset;
//}
//二次封装
//url, type, async, cache, param
//function yrmsAjax() {
//	// 利用了jquery延迟对象回调的方式对ajax封装，使用done()，fail()，always()等方法进行链式回调操作
//	// 如果需要的参数更多，比如有跨域dataType需要设置为'jsonp'等等，可以考虑参数设置为对象
////	var ajaxElement = new Object();
//	if (arguments.length == 1 && $.isArray(arguments[0])) {
//		for(var p in arguments[0]){
//			console.log(p+"---------"+arguments[p]); 
//			ajaxElement.prototype.p = arguments[p];
//		}
//	}
//	return $.ajax({
//		url: ajaxElement.url,
//		data: ajaxElement.data || {},
//		type: ajaxElement.type || 'GET',
//		async:ajaxElement.async || 'true',
//		cache:ajaxElement.cache || 'true',
//		beforeSend:ajaxElement.beforeSend || function(){},
//		dataFilter:ajaxElement.dataFilter || function(){},
//		global:ajaxElement.global || 'true',
//		ifModified:ajaxElement.ifModified || 'false',
//		processData:ajaxElement.processData || 'true'
//	});
//}
//// 链式回调
//ajax('www.baidu.com/getInfo').done(function(resp) {
//// 成功回调
//}).fail(function(err) {
//// 失败回调
//});

//url, type, async, cache, param
//function handleAjax(option) {
//	return ajax(arguments).then(function(resp){
//	// 成功回调
//		if(resp.result){
//			return resp.data; // 直接返回要处理的数据，作为默认参数传入之后done()方法的回调
//		}
//		else{
//			return $.Deferred().reject(resp.msg); // 返回一个失败状态的deferred对象，把错误代码作为默认参数传入之后fail()方法的回调
//		}
//	}, function(err){
//	// 失败回调
//		console.log(err.status); // 打印状态码
//	});
//}

//	handleAjax('www.baidu.com/getInfo').done(function(resp){
//	// 当result为true的回调
//	}).fail(function(err){
//	// 当result为false的回调
//	});
//(2)
//var yrmsDataSouse = {
//		table : function () {
//			return $.ajax(arguments[0]).done(function(resp){
//				return resp.data;
//			}).fail(function(err){
//				return $.Deferred().reject(err);
//			});
//		}
//}
//var dataSouse = yrmsDataSouse.table({
//		url:'${contextPath}/business/knowledgebase/text/text_List.action',
//		data: {
//			
//		},
//		type: 'POST',
//		async:'false',
//		cache:'fasle',
//	})
//	.done(function(resp){
//		simpleTypeDialog(resp);
//		return 1;
//	}).fail(function(err){
//		simpleTypeDialog(000);
//	});
/*-----------------------------------/
/*	handleAjax end
/*----------------------------------*/


/*-----------------------------------/
/*	Ajax error function start
/*----------------------------------*/

//function XMLHttpRequestError (response) {
//	
//	if(errorDialogActon()){
//		if(xhr.responseType == "text") {
//			simpleTypeDialog(xhr.responseText,'parent','lock','2');
//		}else {
//			simpleTypeDialog(data,'parent','lock','2');
//		}
//		simpleTypeDialog(i18n_object_error,'parent','lock','2');
//	}
	
	// 返回一个失败状态的deferred对象，把错误代码作为默认参数传入之后fail()方法的回调
//	return $.Deferred().reject(resp.msg); 
//}
/**
 * 
 * 错误事件 
 *
 **/
function ajaxError(response) {
	
	if(errorDialogActon()){
		errorDialog(response);
	}
	
	// 返回一个失败状态的deferred对象，把错误代码作为默认参数传入之后fail()方法的回调
//	return $.Deferred().reject(resp.msg); 
}

/**
 * 
 * 成功事件 
 *
 **/
function ajaxSuccess() {
	successDialog();
}


function errorDialogActon () {
	
	//关闭所有layer窗口
	layercloseAll();
	return true;
}





/*-----------------------------------/
/*	Ajax error function end
/*----------------------------------*/



/*-----------------------------------/
/*	 isIE function start
/*----------------------------------*/


//ie6-8也是支持的
function isIE(){
if (window.navigator.userAgent.indexOf("MSIE")>=1) 
return true; 
else
return false; 
}
//ie10及以上不支持ie浏览器的判断了，因为ie11已经不支持document.all了，下面是支持ie11的版本的，当然ie6-8也是支持的
function is_Ie() { //ie?
 if (!!window.ActiveXObject || "ActiveXObject" in window)
  return true;
  else
  return false;
 }


/*-----------------------------------/
/*	 isIE function end
/*----------------------------------*/


//layui.use('element', function(){
//	  var $ = layui.jquery
//	  ,element = layui.element(); //Tab的切换功能，切换事件监听等，需要依赖element模块
//	  
//	  //触发事件
//	  var active = {
//	    tabAdd: function(){
//	      //新增一个Tab项
//	      element.tabAdd('tabsBar', {
//	        title: '新选项'+ (Math.random()*1000|0) //用于演示
//	        ,content: '内容'+ (Math.random()*1000|0)
//	        ,id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
//	      })
//	    }
//	    ,tabDelete: function(othis){
//	      //删除指定Tab项
//	      element.tabDelete('demo', '44'); //删除：“商品管理”
//	      
//	      
//	      othis.addClass('layui-btn-disabled');
//	    }
//	    ,tabChange: function(){
//	      //切换到指定Tab项
//	      element.tabChange('demo', '22'); //切换到：用户管理
//		  
//	    }
//	  };
//	  
//	  $('.site-demo-active').on('click', function(){
//	    var othis = $(this), type = othis.data('type');
//	    active[type] ? active[type].call(this, othis) : '';
//	  });
//	  
//	  //Hash地址的定位
//	  var layid = location.hash.replace(/^#tabsBar=/, '');
//	  element.tabChange('tabsBar', layid);
//	  
//	  element.on('tab(tabsBar)', function(elem){
//	    location.hash = 'tabsBar='+ $(this).attr('lay-id');
//	  });
//	  
//});

$(document).ready(function() {

	/*-----------------------------------/
	/*	TOP NAVIGATION AND LAYOUT
	/*----------------------------------*/

	$('.btn-toggle-fullwidth').on('click', function() {
		if(!$('body').hasClass('layout-fullwidth')) {
			$('body').addClass('layout-fullwidth');

		} else {
			$('body').removeClass('layout-fullwidth');
			$('body').removeClass('layout-default'); // also remove default behaviour if set
		}

		$(this).find('.lnr').toggleClass('lnr-arrow-left-circle lnr-arrow-right-circle');

		if($(window).innerWidth() < 1025) {
			if(!$('body').hasClass('offcanvas-active')) {
				$('body').addClass('offcanvas-active');
			} else {
				$('body').removeClass('offcanvas-active');
			}
		}
	});

	$(window).on('load', function() {
		if($(window).innerWidth() < 1025) {
			$('.btn-toggle-fullwidth').find('.icon-arrows')
			.removeClass('icon-arrows-move-left')
			.addClass('icon-arrows-move-right');
		}

		// adjust right sidebar top position
		$('.right-sidebar').css('top', $('.navbar').innerHeight());

		// if page has content-menu, set top padding of main-content
		if($('.has-content-menu').length > 0) {
			$('.navbar + .main-content').css('padding-top', $('.navbar').innerHeight());
		}

		// for shorter main content
		if($('.main').height() < $('#sidebar-nav').height()) {
			$('.main').css('min-height', $('#sidebar-nav').height());
		}
	});


	/*-----------------------------------/
	/*	SIDEBAR NAVIGATION
	/*----------------------------------*/

	$('.sidebar a[data-toggle="collapse"]').on('click', function() {
		if($(this).hasClass('collapsed')) {
			$(this).addClass('active');
		} else {
			$(this).removeClass('active');
		}
	});

	if( $('.sidebar-scroll').length > 0 ) {
		$('.sidebar-scroll').slimScroll({
			height: '95%',
			wheelStep: 2,
		});
	}


	/*-----------------------------------/
	/*	PANEL FUNCTIONS
	/*----------------------------------*/

	// panel remove
	$('.panel .btn-remove').click(function(e){

		e.preventDefault();
		$(this).parents('.panel').fadeOut(300, function(){
			$(this).remove();
		});
	});

	// panel collapse/expand
	var affectedElement = $('.panel-body');

	$('.panel .btn-toggle-collapse').clickToggle(
		function(e) {
			e.preventDefault();

			// if has scroll
			if( $(this).parents('.panel').find('.slimScrollDiv').length > 0 ) {
				affectedElement = $('.slimScrollDiv');
			}

			$(this).parents('.panel').find(affectedElement).slideUp(300);
			$(this).find('i.lnr-chevron-up').toggleClass('lnr-chevron-down');
		},
		function(e) {
			e.preventDefault();

			// if has scroll
			if( $(this).parents('.panel').find('.slimScrollDiv').length > 0 ) {
				affectedElement = $('.slimScrollDiv');
			}

			$(this).parents('.panel').find(affectedElement).slideDown(300);
			$(this).find('i.lnr-chevron-up').toggleClass('lnr-chevron-down');
		}
	);


	/*-----------------------------------/
	/*	PANEL SCROLLING
	/*----------------------------------*/

	if( $('.panel-scrolling').length > 0) {
		$('.panel-scrolling .panel-body').slimScroll({
			height: '430px',
			wheelStep: 2,
		});
	}

	if( $('#panel-scrolling-demo').length > 0) {
		$('#panel-scrolling-demo .panel-body').slimScroll({
			height: '175px',
			wheelStep: 2,
		});
	}

	/*-----------------------------------/
	/*	TODO LIST
	/*----------------------------------*/

	$('.todo-list input').change( function() {
		if( $(this).prop('checked') ) {
			$(this).parents('li').addClass('completed');
		}else {
			$(this).parents('li').removeClass('completed');
		}
	});


	/*-----------------------------------/
	/* TOASTR NOTIFICATION
	/*----------------------------------*/

	if($('#toastr-demo').length > 0) {
		toastr.options.timeOut = "false";
		toastr.options.closeButton = true;
		toastr['info']('Hi there, this is notification demo with HTML support. So, you can add HTML elements like <a href="#">this link</a>');

		$('.btn-toastr').on('click', function() {
			$context = $(this).data('context');
			$message = $(this).data('message');
			$position = $(this).data('position');

			if($context == '') {
				$context = 'info';
			}

			if($position == '') {
				$positionClass = 'toast-left-top';
			} else {
				$positionClass = 'toast-' + $position;
			}

			toastr.remove();
			toastr[$context]($message, '' , { positionClass: $positionClass });
		});

		$('#toastr-callback1').on('click', function() {
			$message = $(this).data('message');

			toastr.options = {
				"timeOut": "300",
				"onShown": function() { simpleTypeDialog('onShown callback','parent','lock','2'); },
				"onHidden": function() { simpleTypeDialog('onHidden callback','parent','lock','2'); }
			}

			toastr['info']($message);
		});

		$('#toastr-callback2').on('click', function() {
			$message = $(this).data('message');

			toastr.options = {
				"timeOut": "10000",
				"onclick": function() { simpleTypeDialog('onclick callback','parent','lock'); },
			}

			toastr['info']($message);

		});

		$('#toastr-callback3').on('click', function() {
			$message = $(this).data('message');

			toastr.options = {
				"timeOut": "10000",
				"closeButton": true,
				"onCloseClick": function() { simpleTypeDialog('onCloseClick callback','parent','lock'); }
			}

			toastr['info']($message);
		});
	}
});

// toggle function
$.fn.clickToggle = function( f1, f2 ) {
	return this.each( function() {
		var clicked = false;
		$(this).bind('click', function() {
			if(clicked) {
				clicked = false;
				return f2.apply(this, arguments);
			}

			clicked = true;
			return f1.apply(this, arguments);
		});
	});

}




