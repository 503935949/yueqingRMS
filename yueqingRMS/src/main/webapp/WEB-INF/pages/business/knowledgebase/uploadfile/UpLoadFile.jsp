<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
  	<%@ include file="/common/base/include/include.jsp"%>
    <title>upLoadFile</title>
    
    <script type="text/javascript">
	    layui.use('element', function(){
	    	  var $ = layui.jquery
	    	  ,element = layui.element();
	    }); 
		$(document).ready(function(){
			//课程logo上传
			layui.use('upload', function(){
				  layui.upload({
					    url: contextPath + '/business/knowledgebase/uploadfile/upLoadFileBySpring.action'
					    ,elem: '#upLoadFileBySpring' //指定原始元素，默认直接查找class="layui-upload-file"
					    ,method: 'post' //上传接口的http类型
					    ,ext: 'doc|png' //文件格式
					    ,success: function(res){
// 					    	alert(res.fileName+"______"+
// 					    			res.filePath+"______"+
// 					    			res.newName);
							ajaxSuccess();  
							$("#upLoadFileBySpringName").text(res.fileName);
					    	//设置新上传课\wen件压缩包名称 
					    	$("#upLoadFileBySpringNameH").val(res.newName);
		 			     	$("#upLoadFileBySpringPath").val("/" + res.filePath);
		// 			      var imgUrl = res.filePath.replace(/\\/g,"/") + res.newName;			
					     
		// 			     $("#showLogo").attr("src",path + "/" + imgUrl); //设置当前上传图片
		// 			     $("#lessonLogo").val("/" + imgUrl); //设置新路径  
					    }
				  });
				  
				  
				//压缩包上传
				  layui.upload({
					    url: contextPath + '/business/knowledgebase/uploadfile/upLoadFileByCommonsFileupload.action'
					    ,elem: '#upLoadFileByCommonsFileupload' //指定原始元素，默认直接查找class="layui-upload-file"
					    ,method: 'post' //上传接口的http类型
					    ,ext: 'zip|rar' //文件格式
					    ,success: function(res){
// 					    	alert(res.fileName+"______"+
// 					    			res.filePath+"______"+
// 					    			res.newName);
							ajaxSuccess(); 
					    	$("#upLoadFileByCommonsFileuploadName").text(res.fileName);
					    	//设置新上传课\wen件压缩包名称 
					    	$("#upLoadFileByCommonsFileuploadNameH").val(res.newName);
		 			     	$("#upLoadFileByCommonsFileuploadPath").val("/" + res.filePath);
		// 			      var imgUrl = res.filePath.replace(/\\/g,"/") + res.newName;			
					     
		// 			     $("#showLogo").attr("src",path + "/" + imgUrl); //设置当前上传图片
		// 			     $("#lessonLogo").val("/" + imgUrl); //设置新路径  
					    }
					});
				  
			 });
			//表单同步上传文件
			$("#infoForm3").attr("action",contextPath + "/business/knowledgebase/uploadfile/upLoadFileBySpring.action");
			$("#infoForm3").attr("enctype","multipart/form-data");
			//上传并解析ET/xls/xlsx文件内容
// 			$("#infoForm4").attr("action",contextPath + "/business/knowledgebase/uploadfile/upLoadFileBySpring.action");
// 			$("#infoForm4").attr("enctype","multipart/form-data");
		
		
			/*
    			Js 多文件上传
    		*/
		    $('#uploads').click(function() {  
			   	  var files = document.getElementById('files');  
				  var uploadBtn = document.getElementById('uploads');  
			      var xhr = new XMLHttpRequest();  
			      xhr.open('POST', contextPath + '/business/knowledgebase/uploadfile/upLoadFileBySpring.action', true);  
			      xhr.onload = function () {  
				        if (xhr.status === 200) {  
				          	// File(s) uploaded.  
				          	ajaxSuccess();  
				        } else {  
				        	return ajaxError(xhr);
				        }
				      //避免内存泄漏
						xhr = null;
			      };  
			      
			      var formData = new FormData();  
			      //单文件上传
// 			      var file = files.files[0];  
// 			      formData.append('file', file);  
				  //多文件上传
				  var fileBodys = files.files;
				  for(var i=0 ;i<fileBodys.length;i++ ) {
					  formData.append('file'+i, fileBodys[i]);
				  }
			      xhr.send(formData);  
			}); 
			
			
			//多文件多选择器上传
	        $("	#uploadDiv").on("click",function(){  
	        	
	        	var fileInput = '<div><input name="files" id="uploaderInput" class="fileInput" style="display:inline" type="file"  multiple/>'  
		     	  	 + '<button class="fileBtn"  >'+'<i class="lnr lnr-trash"></i>'+'</button></div>';
		        $('#fileDiv').append($(fileInput));  
		        $("#uploaderInput").bind("change",function(e){  
                      //可以做一些其他的事，比如图片预览  
                     $(this).removeAttr("id");  
			    });  
		        $('.fileBtn').each(function (){
					$(this).click(function() {  
		 				$(this).parent().remove();
					});
				});
		        $("#uploaderInput").click();  
		        
		    }); 
			
	        $('#uploadss').click(function() {  
// 			   	  var files = document.getElementById('files');  
				  var uploadBtn = document.getElementById('uploads');  
			      var xhr = new XMLHttpRequest();  
			      xhr.open('POST', contextPath + '/business/knowledgebase/uploadfile/upLoadFileBySpring.action', true);  
			      xhr.onload = function () {  
				        if (xhr.status === 200) {
				          // File(s) uploaded.  
				          ajaxSuccess();
				        } else {  
				        	return ajaxError(xhr);
				        }  
				      //避免内存泄漏
						xhr = null;
			      };  
			      
			      var formData = new FormData();  
			      //单文件上传
//			      var file = files.files[0];  
//			      formData.append('file', file);  
				  //多文件上传
				  var fileinputs = $('.fileInput');
				  for(var j=0;j< fileinputs.length;j++) {
						var fileBodys = fileinputs[j].files;
						if(fileBodys != "undefined") {
							for(var i=0 ;i<fileBodys.length;i++ ) {
								formData.append('file'+i+""+j, fileBodys[i]);
							} 
						}
	        	  }
			      xhr.send(formData);  
			});
			
		    
		    /*
	    		Jq 上传
	    	*/
		    $('#jQ_loaddown').click(function () {
		    	jQupload(contextPath + '/business/knowledgebase/uploadfile/upLoadFileByCommonsFileupload.action');
	    	});
		
		});
		
	/*
    	Jq 上传
    */
	function jQupload(url) {
// 		var str = $('#jQ_href').val();
// 		var strName = str.split("\\");
// 		if(strName.length == 1){
// 			strName = str.split("/");
// 		}
		
// 		var filename = strName[strName.length-1];
    	var formData = new FormData($('#infoForm4'));
//     	formData.append('filePath', $('#jQ_href').val());
// 	    formData.append('file', filename);
    	$.ajax({
				url:url,
				data: formData,
				type: 'POST',
				async:'fasle',
				cache:'fasle',
				contentType: false,  
		        processData: false
			})
			.done(function(resp){
				return ajaxSuccess();
			}).fail(function(err){
				return ajaxError(err);
			});
	}
			  
	</script>
  </head>
  
  <body>
	<div class="layui-tab">
		<ul class="layui-tab-title">
			<li class="layui-this">异步上传</li>
			<li>表单同步步上传</li>
			<li>XMLHttpRequest</li>
			<li >j多上传</li>
			<li >jQ方式触发(未完成实现)</li>
		</ul>
  		<div class="layui-tab-content">
  			<!-- item>start -->
    		<div class="layui-tab-item layui-show">
			    <fieldset>
			  		<legend>异步上传文件（layUI 上传插件）</legend>
				  	<input type="hidden" id="path" name="path" value="${contextPath}"/>
					<div class="centercontent">
				<%-- 		<baf:nav /> --%>
				    	<div id="contentwrapper" class="contentwrapper">
				    		<div class="pageheader_tab">
								<ul class="hornav">
									<li id="tab1" class="current"><a href="javascript:void(0);">upLoadFileBySpring</a></li>
								</ul>
							</div><!--Tab页区域-->	
					    	<div id="infoDiv" class="infoDiv">
								<form class="stdform stdform2" id="infoForm" name="infoForm" method="post" action="">	
							        <p>
							        	<input id="upLoadFileBySpring" name="file" lay-type="file" class="layui-upload-file" type="file"/>
							        	<i id="upLoadFileBySpringName" style="padding-top:10px;font-weight:bold;display:block;"></i>
						            	<input  name="" id="upLoadFileBySpringNameH" class="longinput" value=""/>
						            	<input  name="" id="upLoadFileBySpringPath" class="longinput" value=""/>
							        </p>
						     	</form>   	
							</div>
							<div class="pageheader_tab">
								<ul class="hornav">
									<li id="tab1" class="current"><a href="javascript:void(0);">upLoadFileByCommonsFileupload</a></li>
								</ul>
								<ul class="hornav">
									<li id="tab1" class="current">压缩上传，并解压在目标文件夹（二者并存）</li>
								</ul>
							</div><!--Tab页区域-->
							<div id="infoDiv2" class="infoDiv">
								<form class="stdform stdform2" id="infoForm2" name="infoForm2" method="post" action="">	
							        <p>
							        	<input id="upLoadFileByCommonsFileupload" name="file" lay-type="file" class="layui-upload-file" type="file"/>
							        	<i id="upLoadFileByCommonsFileuploadName" style="padding-top:10px;font-weight:bold;display:block;"></i>
						            	<input  name="" id="upLoadFileByCommonsFileuploadNameH" class="longinput" value=""/>
						            	<input  name="" id="upLoadFileByCommonsFileuploadPath" class="longinput" value=""/>
							        </p>
						     	</form>   	
							</div><!--内容编辑区 -->
						</div><!-- 右侧内容区 -->	
					</div>
				</fieldset>
    		</div>
    		<!-- item>start -->
    		<!-- item>end -->
		    <div class="layui-tab-item">
		    	<fieldset>
			  		<legend>表单同步上传文件</legend>
			  		<div class="pageheader_tab">
						<ul class="hornav">
							<li id="tab1" class="current"><a href="javascript:void(0);">upLoadFileByCommonsFileupload</a></li>
						</ul>
					</div><!--Tab页区域-->
					<div id="infoDiv2" class="infoDiv">
						<form class="stdform stdform2" id="infoForm3" name="infoForm3" method="post" action="">	
					        <p>
					        	选择文件：<input type="file" name="file">
								<input type="submit" value="上传" >
					        </p>
				     	</form>   	
					</div><!--内容编辑区 -->
			  	</fieldset>	
		    </div>
		    <!-- item>end -->
		    <!-- item>start -->
		    <div class="layui-tab-item">
		    	<fieldset>
			  		<legend>JS/html/form/XMLHttpRequest Ajax </legend> 
			  			<div>  
							<h1>3、JS Ajax post file   多文件上传</h1>  
<!-- 						    <input type="file" id="files" name="photos" multiple accept="image/*"/>   -->
								<input type="file" id="files" name="photos" multiple />  		
						    <button type="submit" id="uploads">上传文件</button>  
						</div>  
				</fieldset>	
		    </div>
		    <!-- item>end -->
		    <!-- item>start -->
		    <div class="layui-tab-item ">
		    	<fieldset>
			  		<legend>js多控件上传</legend>
				        <p>
				        	<div id="fileDiv" style=""></div> 
				        	<br/>
				        	<div id="uploadDiv" class="weui-uploader__input-box"><i class="lnr lnr-pencil"></i></div>  
							
<!-- 							<div id="fileDiv" style="display: none"></div>   -->
							<input type='button' id="uploadss" value="上传" >
				        </p>
			  	</fieldset>	
		    </div>
		    <!-- item>end -->
		    <!-- item>start -->
		    <div class="layui-tab-item ">
		    	<fieldset>
			  		<legend>jQ下载(可简单执行全局error处理)</legend>
			  		<form class="stdform stdform2" id="infoForm4" name="infoForm4" method="post" action="">	
				        <p>
				        	选择文件：<input type="file" name="file">
							<input type="submit" value="上传" >
				        </p>
			     	</form> 
			  	</fieldset>	
		    </div>
		    <!-- item>end -->
  		</div>
	</div>
  	

	
  </body>
</html>