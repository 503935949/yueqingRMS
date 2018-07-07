<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
  	<%@ include file="/common/base/include/include.jsp"%>
    <title>SendMail</title>
    
    <script type="text/javascript">
	    layui.use('element', function(){
	    	  var $ = layui.jquery
	    	  ,element = layui.element();
	    }); 
	    $(function(){
	    $('#bs_submit').click(function () { 
		    $.ajax({
					url:'${contextPath}/business/knowledgebase/mail/sendMail_bySpring.action',
					data: {
						"from":$('#bs_from').val(),
						"toEmails":$('#bs_toEmails').val(),
						"subject":$('#bs_subject').val(),
						"content":$('#bs_content').val()
					},
					type: 'POST',
					async:'fasle',
					cache:'fasle',
			})
			.done(function(resp){
				ajaxSuccess();
				return resp;
			}).fail(function(err){
				return ajaxError(err);
			});
	    });
	    
	    
	   $('#bj_submit').click(function () { 
		    $.ajax({
					url:'${contextPath}/business/knowledgebase/mail/sendMail_byJavaMail.action',
					data: {
						from:$('#bj_from').val(),
						toEmails:$('#bj_toEmails').val(),
						subject:$('#bj_subject').val(),
						content:$('#bj_content').val()
					},
					type: 'POST',
					async:'fasle',
					cache:'fasle',
			})
			.done(function(resp){
				ajaxSuccess();
				return resp;
			}).fail(function(err){
				return ajaxError(err);
			});
		});	
	   
	   
	   
	   
		 //多文件多选择器上传
       $("#uploadDiv").on("click",function(){  
       	
       	var fileInput = '<div ><input name="files" id="uploaderInput" class="fileInput" style="display:inline" type="file"  multiple/>'  
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
	   $('#bjf_submit').click(function () { 
		    var xhr = null;
		    //添加加载层
		    addload();
		    var is_ie = is_Ie();
		    if (is_ie) {    
		        var control = (is_ie) ? "Microsoft.XMLHTTP" : "Msxml2.XMLHTTP";
		        try {      
		        	xhr = new ActiveXObject(control);
		        } catch(e) {
		            alert("You need to enable active scripting and activeX controls");
		        }
		    } else {
		    	xhr = new XMLHttpRequest();
		    }
// 		      var xhr = new XMLHttpRequest();  
		      xhr.open('POST', contextPath+'/business/knowledgebase/mail/sendMail_byJavaMailWithFile.action', true);  
// 		      xhr.onload = function () {  
// 		        if (xhr.status === 200) {  
// 		        	alert(xhr.); 
// 		        } else {  
// 		          alert('An error occurred!');  
// 		        }  
// 		      };  
			  xhr.onreadystatechange =  function () {
				  if (xhr.readyState == 4) {
					  //关闭加载框
					  layercloseAllType('loading');
			            //当XHR的状态为4时判断请求成功与否，然后处理响应的数据，虽然当XHR的状态为2或者3时可以获取到响应状态，但是此时的数据还未下载完全，不能处理响应数据
			            if (xhr.status == 200) {
			                //请求成功，处理响应数据
// 			            	var data = xhr.responseText;      
			            	ajaxSuccess();
			            } else {
			                //请求失败
// 			            	var error = xhr.responseText;
						  return ajaxError(xhr);
			            }
			          //避免内存泄漏
						xhr = null;
			        }
			  }
		      
// 		      var files0 = document.getElementById('bjf_file0');
// 		      var file0 = files0.files[0];  
// 		      var files1 = document.getElementById('bjf_file1');
// 		      var file1 = files1.files[0];
		      //这行代码很关键，用来把字符串类型的参数序列化成Form Data 
// 		      xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		      var formData = new FormData();  
// 		      formData.append('file0', file0);
// 		      formData.append('file1', file1);
		      formData.append('toEmails', $('#bjf_toEmails').val());
		      formData.append('subject', $('#bjf_subject').val());
		      formData.append('content', $('#bjf_content').val());
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
	    });
	</script>
  </head>
  
	<body>
		<div class="layui-tab">
			<ul class="layui-tab-title">
				<li class="layui-this">sendMail_bySpring</li>
				<li>sendMail_byJavaMail</li>
				<li>sendMail_byJavaMailWithFile</li>
			</ul>
			<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
		   			<fieldset>
				  		<legend>sendMail_bySpring</legend>
				  		from:<input id="bs_from" name="from" value="" /><br/>
				  		toEmails:<input id="bs_toEmails" name="toEmails" value="" /><br/>
				  		subject:<input id="bs_subject" name="subject" value="" /><br/>
				  		content:<input id="bs_content" name="content" value="" /><br/>
				  		<input id="bs_submit" type="button"  value="发送" onclick=""/><br/>
					</fieldset>	
				</div><!-- item>end -->
				<div class="layui-tab-item">
					<fieldset>
				  		<legend>（sendMail_byJavaMai）l</legend>
				  		from:<input id="bj_from" name="from" value="" /><br/>
				  		toEmails:<input id="bj_toEmails" name="toEmails" value="" /><br/>
				  		subject:<input id="bj_subject" name="subject" value="" /><br/>
				  		content:<input id="bj_content" name="content" value="" /><br/>
				  		<input id="bj_submit" type="button"  value="发送" onclick=""/><br/>
					</fieldset>
				</div><!-- item>end -->
				<div class="layui-tab-item">
					<fieldset>
				  		<legend>支持群发、多附件（sendMail_byJavaMailWithFile）</legend>
				  		<!-- modal-body start -->
					    <div class="modal-body">
					        <!-- form start -->
							<form  class="form-horizontal" >
								<div class="form-group">
								    <label for="toEmails"  class="col-xs-2 col-sm-2 col-md-2 control-label" >toEmails</label>
								    <div class="col-xs-10 col-sm-10 col-md-10">
									    <input id="bjf_toEmails" name="toEmails"  class="form-control" placeholder="toEmails" value="" />
								    </div>
								</div>
								<div class="form-group">
								    <label for="subject"  class="col-xs-2 col-sm-2 col-md-2 control-label" >subject</label>
								    <div class="col-xs-10 col-sm-10 col-md-10">
									    <input id="bjf_subject" name="subject"  class="form-control" placeholder="subject" value="" />
								    </div>
								</div>
									<div class="form-group">
								    <label for="file"  class="col-xs-2 col-sm-2 col-md-2 control-label" >file</label>
								    <div class="col-xs-10 col-sm-10 col-md-10">
									    <div id="fileDiv" style=""></div> 
							        	<br/>
							        	<div id="uploadDiv" class="weui-uploader__input-box"><i class="lnr lnr-pencil"></i></div>  
<!-- 									    <input id="bjf_file0" name="file0" type="file"  class="form-control"  value="" /> -->
<!-- 									    <input id="bjf_file1" name="file1" type="file"  class="form-control"  value="" /> -->
								    </div>
								</div>
								<div class="form-group">
								    <label for="content"  class="col-xs-2 col-sm-2 col-md-2 control-label" >content</label>
								    <div class="col-xs-10 col-sm-10 col-md-10">
									    <textarea id="bjf_content"  name="content" class="form-control" ></textarea>
								    </div>
								</div>
					        </form>
					        <!-- form end -->
						</div>
		      			<!-- modal-body end -->
						<div class="modal-footer">
						    <button id="bjf_submit" type="button" class="btn btn-primary">
						    	发送
							</button>
						</div>
					</fieldset>
				</div><!-- item>end -->
			</div>
		</div>

	
	</body>
</html>