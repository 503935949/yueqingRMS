/*
* 
 * 
 * 
 * 
 * JavaScript - Input
 * 
 */

$().ready( function() {
	
	if ($.tools != null) {
		var $tab = $("#tab");
		var $title = $("#inputForm :input[title], #inputForm label[title]");

		// Tab效果
		$tab.tabs("table.tabContent, div.tabContent", {
			tabs: "input"
		});
	
		// 表单提示
		$title.tooltip({
			position: "center right",
			offset: [0, 4],
			effect: "fade"
		});
	}

	// 验证消息
	if($.validator != null) {
		$.extend($.validator.messages, {
		    required: message("admin.validate.required"),
			email: message("admin.validate.email"),
			url: message("admin.validate.url"),
			date: message("admin.validate.date"),
			dateISO: message("admin.validate.dateISO"),
			pointcard: message("admin.validate.pointcard"),
			number: message("admin.validate.number"),
			digits: message("admin.validate.digits"),
			minlength: $.validator.format(message("admin.validate.minlength")),
			maxlength: $.validator.format(message("admin.validate.maxlength")),
			rangelength: $.validator.format(message("admin.validate.rangelength")),
			min: $.validator.format(message("admin.validate.min")),
			max: $.validator.format(message("admin.validate.max")),
			range: $.validator.format(message("admin.validate.range")),
			accept: message("admin.validate.accept"),
			equalTo: message("admin.validate.equalTo"),
			remote: message("admin.validate.remote"),
			integer: message("admin.validate.integer"),
			positive: message("admin.validate.positive"),
			negative: message("admin.validate.negative"),
			decimal: message("admin.validate.decimal"),
			pattern: message("admin.validate.pattern"),
			extension: message("admin.validate.extension"),
			isIDCard : message('business.validate.isIDCard'),
			mobilephone : message('business.validate.mobilephone'),
			phoneCH:message('business.validate.phoneCH'),
			zipCode:message('business.validate.zipCode'),
			isFax: message('business.validate.isFax'),
			isCharacter:message('business.validate.isCharacter'),
			isPhone:message('business.validate.isPhone'),
			isChinese:message('business.validate.isChinese'),
			isEnglish:message('business.validate.isEnglish'),
			isChineseOrEnglish:message('business.validate.isChineseOrEnglish'),
			EnglishAndSymbol:message('business.validate.EnglishAndSymbol'),
			ChineseAndSymbol:message('business.validate.ChineseAndSymbol'),
			ChineseAndEnglishAndBlank:message('business.validate.ChineseAndEnglishAndBlank'),
			taskno:message('business.validate.taskno')
		});
		
		$.validator.setDefaults({
//			errorClass: "message",
//			errorElement:"em",
//			focusCleanup: true,
//			ignore: ".ignore",
//			ignoreTitle: true,
			focusCleanup:true,
			errorElement : "div",
			wrapper : "div",// a wrapper around the error message
			onfocusout: function(element) {
				$(element).valid(); 
			},
//			onkeyup: function(element) {
//				$(element).valid(); 
//			},
			errorPlacement: function(error, element) {
//				error.css({
//					"left":"10px",
//					"top":"0",
//					"color":"red",
//					"vertical-align":"top",
//					"position":"absolute"
//				});
				error.addClass('message')
//				error.insertAfter(element);
				// 处理模拟select功能添加消息验证
				if(element.attr("class").indexOf("select_value")!=-1){
					error.css('top', "40px");
					error.appendTo(element.parent().parent("td"));
				}else{
					error.appendTo(element.parent("td"));
				}
//				if (element.attr("class").indexOf("select_value") != -1) {
//					element.parents("td").append(error);
//				} else {
//					element.parent().append(error);	
//				}
//				var fieldSet = element.closest("span.fieldSet");
//				if (fieldSet.size() > 0) {
//					error.appendTo(fieldSet);
//				} else {
//					error.insertAfter(element);
//				}
			},
			submitHandler : function (form) {
				//$(form).find("#submitBtn").prop("disabled", true);
				//$(form).find("#submitBtn").unbind();
				//alert("提交事件!");
				
				// 处理富文本的信息
				$editors = $("div[id^='editor']");
				$editors.each(function() {
					var editor_id = $(this).attr("id");
					var editor_val = UE.getEditor(editor_id).getContent();
					$(this).next().val(editor_val);
				});
				$.ajax({
					type : "POST",
					dataType : "html",
					url : $(form).attr("action"),
					data : $(form).serialize(),
					success : function (result) {
						var json = jQuery.parseJSON(result);
						if (json.status == "success") {
							if(page.entity_id != undefined && page.entity_id != ""){// 判断是修改页面
								if(page.goBack){
									page.goBack();
								}else{
									window.location = document.referrer;
								}
							}else{
								alertConfirmCancelNew(message('public.save_success_p'), function() {
									$(form).find("input").each(function(){
										if($(this).attr("readonly") != "readonly"){
											//$(form).find("input").val("");
											$(this).val("");
										}
									});
									$(".select_txt").html(message('public.choose'));
									$(form).find("textarea").val("");
								}, function() {
									if(page.goBack){
										page.goBack();
									}else{
										window.location = document.referrer;
									}
								});
							}
						}
					},
					error : function (data) {
						alert("error:" + data.responseText);
					}
				});
			}
		});
	}
	
	// 绑定Form按钮
	// 提交按钮
	$("#submitBtn").click(function() {
		$("#inputForm").submit();
	});
	// 返回按钮
	$("#returnBtn").click(function() {
		var refer = document.referrer;
		window.location = refer;
	});
	
	// 如果查看页面，所有变成readonly状态
	if (cebcm.view) {
		$("input").attr("readonly", "readonly");
		$("textarea").attr("readonly", "readonly");
		$(".select_box").unbind();
		$("input").unbind();
	}
	
	
	// 回显富文本内容
	var editorshow = setInterval(function(){
		$editors = $("div[id^='editor']");
		$editors.each(function() {
			var editor_id = $(this).attr("id");
			var editor_val = $(this).next().val();
			
			if (editor_val.length > 0) {
				UE.getEditor(editor_id).setContent(editor_val);			
			}
			clearInterval(editorshow);
		});
	}, 500);
});
