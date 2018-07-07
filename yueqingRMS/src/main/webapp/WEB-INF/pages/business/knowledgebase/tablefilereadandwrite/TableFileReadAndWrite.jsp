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
		//上传并解析ET/xls/xlsx文件内容
		layui.use('upload', function(){
			layui.upload({
			    url: contextPath + '/business/knowledgebase/tablefilereadandwrite/upLoadFile4EtInfo03Or07.action'
			    ,elem: '#upLoadFile4EtInfo03Or07' //指定原始元素，默认直接查找class="layui-upload-file"
			    ,method: 'post' //上传接口的http类型
			    ,ext: 'et|xls|xlsx' //文件格式
			    ,success: function(res){
			    	ajaxSuccess();
			    	$("#upLoadFile4EtInfo03Or07Name").text(res.fileName);
			    	//设置新上传课\wen件压缩包名称 
			    	$("#upLoadFile4EtInfo03Or07NameH").val(res.newName);
 			     	$("#upLoadFile4EtInfo03Or07Path").val("/" + res.filePath);
			    }
			});
		});
		
		//页面js创建Ifream方式下载文件
    	function downloadFile(url) {   
            try{ 
                var elemIF = document.createElement("iframe");   
                elemIF.src = url;   
                elemIF.style.display = "none";   
                document.body.appendChild(elemIF);   
            }catch(e){ 
     
            } 
        }
		
    	 $(function () {
	    	//页面js创建Ifream方式下载文件
	    	$('#iframe_loaddown').click(function () {
	    		downloadFile($('#iframe_function').val());
	    	});
    	 });	
	    	
	</script>
  </head>
  
  <body>
  <div class="layui-tab">
	<ul class="layui-tab-title">
		<li class="layui-this">上传并解析ET/xls/xlsx文件内容</li>
		<li>导出ET/xls/xlsx文件</li>
	</ul>
	<div class="layui-tab-content">
		<div class="layui-tab-item layui-show">
   			<fieldset>
		  		<legend>异步上传文件（layUI 上传插件）</legend>
			  	<input type="hidden" id="path" name="path" value="${contextPath}"/>
				<div class="centercontent">
					<div id="contentwrapper" class="contentwrapper">
						<div class="pageheader_tab">
							<ul class="hornav">
								<li id="tab1" class="current"><a href="javascript:void(0);">上传并解析ET/xls/xlsx文件内容</a></li>
								</ul>
						</div><!--Tab页区域-->
						<div id="infoDiv2" class="infoDiv">
							<form class="stdform stdform2" id="infoForm4" name="infoForm4" method="post" action="">	
						        <p>
						        	<input id="upLoadFile4EtInfo03Or07" name="file" lay-type="file" class="layui-upload-file" type="file"/>
						        	<span id="upLoadFile4EtInfo03Or07Name" style="padding-top:10px;font-weight:bold;display:block;"></span>
					            	<input  name="" id="upLoadFile4EtInfo03Or07NameH" class="longinput" value=""/>
					            	<input  name="" id="upLoadFile4EtInfo03Or07Path" class="longinput" value=""/>
						        </p>
					     	</form>   	
						</div><!--内容编辑区 -->
					</div><!-- 右侧内容区 -->	
				</div>
			</fieldset>	
		</div><!-- item>end -->
		<div class="layui-tab-item">
			<fieldset>
		  		<legend>创建iframe方式触发导出功能</legend>
		  		<input id="iframe_function" value="${contextPath}/business/knowledgebase/tablefilereadandwrite/downLoadFile4EtInfo03Or07.action" />
		  		<input id="iframe_href" value="" />
		  		<input id="iframe_loaddown" type="button"  value="导出" onclick=""/>
		  		<a id="iframe_function" href="${contextPath}/business/knowledgebase/tablefilereadandwrite/downLoadFile4EtInfo03Or07.action" >导出</a>
			</fieldset>
		</div><!-- item>end -->
	</div>
</div>

	
  </body>
</html>