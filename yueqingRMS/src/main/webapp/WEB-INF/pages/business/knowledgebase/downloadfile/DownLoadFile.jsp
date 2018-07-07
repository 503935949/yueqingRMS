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
	    
	    
	  	//js、ajax_get下载
    	function StartGETRequest(url, handler){
	  		
    		var str = $('#js_ajax_get_href').val();
			var strName = str.split("\\");
			if(strName.length == 1){
				strName = str.split("/");
			}
			var filename = strName[strName.length-1];
			
		    xmlhttp = null;
// 		    var is_ie = is_Ie();
// 		    if (is_ie) {    
// 		        var control = (is_ie) ? "Microsoft.XMLHTTP" : "Msxml2.XMLHTTP";
// 		        try {      
// 		            xmlhttp = new ActiveXObject(control);
// 		        } catch(e) {
// 		            alert("You need to enable active scripting and activeX controls");
// 		        }
// 		    } else {
		        xmlhttp = new XMLHttpRequest();
// 		    }
		    xmlhttp.responseType = "blob";//这里是关键，它指明返回的数据的类型是二进制
// 		    xmlhttp.onreadystatechange = function() {handler();}
		    xmlhttp.onreadystatechange =  function () {
		    	
				  if (xmlhttp.readyState == 4) {
			            //当XHR的状态为4时判断请求成功与否，然后处理响应的数据，虽然当XHR的状态为2或者3时可以获取到响应状态，但是此时的数据还未下载完全，不能处理响应数据
			            if (xmlhttp.status == 200) {
			                //请求成功，处理响应数据
// 			            	var data = xmlhttp.responseText;      
								
			                    
			                    if (typeof window.chrome !== 'undefined') {
			                        // Chrome version
			                        var link = document.createElement('a');
			                        link.href = window.URL.createObjectURL(xmlhttp.response);
			                        link.download = filename;
			                        link.click();
			                    } else if (typeof window.navigator.msSaveBlob !== 'undefined') {
			                        // IE version
			                        var blob = new Blob([xmlhttp.response], { type: 'application/force-download' });
			                        window.navigator.msSaveBlob(blob, filename);
			                    } else {
			                        // Firefox version
			                        var file = new File([xmlhttp.response], filename, { type: 'application/force-download' });
			                        window.open(URL.createObjectURL(file));
			                    }

							
			            } else {
			                //请求失败
			                
// 			            	var error = xmlhttp.responseText;
							return errorDialog(xmlhttp);
			            	//ajaxError("操作失败","blob");
			            }
			          	//避免内存泄漏
						xmlhttp = null;
			        }
			 }
		    //alert(urltemp);
		    xmlhttp.open('POST', url, true);
		    
		    var formData = new FormData(); 
		    formData.append('filePath', $('#js_ajax_get_href').val());
		    formData.append('file', filename);
		    
		    xmlhttp.send(formData); 
		 	
		}
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
	    
	  	//页面js创建Ifream方式下载文件
    	function downloadFile(url) {   
            try{ 
                var elemIF = document.createElement("iframe");   
                elemIF.src = url;   
                elemIF.style.display = "none";   
                document.body.appendChild(elemIF);   
            }catch(e){ 
            } 
           // document.body.removeChild(elemIF);  
        }
	  	//直接改变页面上现有的iframe下载（太low未做）
	    function switchIframe(){
	    	window.frames["frameName"].location.href="action(或你需要的名字).aspx"
		}
	  	
	  	
	  	//开启流下载
	  	//但是该方法在火狐上没有效果的，在IE浏览器上是可以的。
	  	//(未实现)
	  	function DownURL(strRemoteURL, strLocalURL){
	        try{
	            var xmlHTTP = new ActiveXObject("Microsoft.XMLHTTP");
	            xmlHTTP.open("Get", strRemoteURL, false);
	            xmlHTTP.send();
	            var adodbStream = new ActiveXObject("ADODB.Stream");
	            adodbStream.Type = 1;//1=adTypeBinary 
	            adodbStream.Open();
	            adodbStream.write(xmlHTTP.responseBody);
	            adodbStream.SaveToFile(strLocalURL, 2);
	            adodbStream.Close();
	            adodbStream = null;
	            xmlHTTP = null;
	        }
	        catch (e){
	            window.confirm("下载URL出错!");
	        }
	        //window.confirm("下载完成."); 
	    }
	   
	    $(function () {
	    	 //<a>的方式触发下载
	    	$('#a_loaddown').click(function () {
	    		$(this).attr('href',$('#a_function').val()+"?"+$('#a_href').val());
	    	});
	    	 
	    	//页面js创建Ifream方式下载文件
	    	$('#iframe_loaddown').click(function () {
	    		downloadFile($('#iframe_function').val()+"?"+$('#iframe_href').val());
	    	});
	    	
	    	
	    	//js、ajax_get下载
	    	//调用、
	    	//StartGETRequest("http://abc.com/xxx.zip",function(){alert("下载完成")});
	    	$('#js_ajax_get_loaddown').click(function () {
	    		StartGETRequest($('#js_ajax_get_function').val(),function(){});
// 	    	+""+encodeURI($('#js_ajax_get_href').val()
	    	});
	    	
	    	
	    	
	    });
	    	
	   
	</script>
  </head>
  
  <body>
	<div class="layui-tab">
		<ul class="layui-tab-title">
			<li class="layui-this">js、ajax_get下载</li>
			<li >&lt;a&gt;方式触发下载</li>
			<li>创建iframe方式触发下载</li>
			
		</ul>
  		<div class="layui-tab-content">
  			<!-- item>start -->
		    <div class="layui-tab-item  layui-show">
		    	<fieldset>
			  		<legend>js、ajax_get下载(可简单执行全局error处理)</legend>
			  		<input id="js_ajax_get_function" value="${contextPath}/business/knowledgebase/downloadfile/downLoadFileCanOnLine.action" />
			  		<input id="js_ajax_get_href" value="C:\Users\Administrator\Desktop\conf\applicationContext.txt" />
			  		<input id="js_ajax_get_loaddown" type="button"  value="js、ajax_get下载" onclick=""/>
			  	</fieldset>	
		    </div>
		    <!-- item>end -->
    		<!-- item>start -->
		    <div class="layui-tab-item">
		    	<fieldset>
			  		<legend>创建iframe方式触发下载</legend>
			  		<input id="iframe_function" value="${contextPath}/business/knowledgebase/downloadfile/downLoadFileCanOnLine.action" />
			  		<input id="iframe_href" value="filePath=C:\Users\Administrator\Desktop\conf\applicationContext.txt" />
			  		<input id="iframe_loaddown" type="button"  value="iframe下载" onclick=""/>
			  	</fieldset>	
		    </div>
		    <!-- item>end -->
		    <!-- item>start -->
    		<div class="layui-tab-item">
			    <fieldset>
			  		<legend>&gt;a&lt;方式触发下载</legend>
			  		<input id="a_function" value="${contextPath}/business/knowledgebase/downloadfile/downLoadFileCanOnLine.action" />
			  		<input id="a_href" value="filePath=C:\Users\Administrator\Desktop\conf\applicationContext.txt" />
			  		<a id="a_loaddown" target="_blank" href="" >a下载</a>
				</fieldset>
    		</div>
    		<!-- item>end -->
		    
  		</div>
	</div>
  	

	
  </body>
</html>