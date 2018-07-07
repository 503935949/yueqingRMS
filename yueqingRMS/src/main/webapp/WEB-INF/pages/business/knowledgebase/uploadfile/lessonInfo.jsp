<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
  <head>
  	<%@ include file="/common/base/include/include.jsp"%>
    <title>课程编辑页</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/js/layui/css/layui.css"/>
  	<script type="text/javascript" src="${contextPath}/js/layui/layui.js"></script>
    
    <script type="text/javascript">
		$(document).ready(function(){
			var path = $("#path").val();
			//课程logo上传
			layui.use('upload', function(){
			  layui.upload({
			    url: path + '/upload/uploadImageFile.do'
			    ,elem: '#uploadImg' //指定原始元素，默认直接查找class="layui-upload-file"
			    ,method: 'post' //上传接口的http类型
			    ,ext: 'jpg|png' //文件格式
			    ,success: function(res){
			      var imgUrl = res.filePath.replace(/\\/g,"/") + res.newName;			
			     
			     $("#showLogo").attr("src",path + "/" + imgUrl); //设置当前上传图片
			     $("#lessonLogo").val("/" + imgUrl); //设置新路径  
			    }
			  });
			  
			  
			  //课件压缩包上传
			  layui.upload({
			    url: path + '/upload/uploadFile.do'
			    ,title: '上传课件'
			    ,elem: '#uploadFile' //指定原始元素，默认直接查找class="layui-upload-file"
			    ,method: 'post' //上传接口的http类型
			    ,ext: 'zip|rar' //文件格式
			    ,before: function(input){
				    //返回的参数item，即为当前的input DOM对象
				    $("#showZipName").text("课件上传中……");
				}
			    ,success: function(res){
			      	var zipUrl = res.filePath.replace(/\\/g,"/") + res.newName;			
			     
			       	$("#showZipName").text(res.fileName);
			     	$("#lessonZipName").val(res.fileName); //设置新上传课件压缩包名称 
			     	$("#lessonZipPath").val("/" + zipUrl); //设置新Zip路径  
			    }
			  });
			  
			});			  
		});
		
		/***返回课程列表**/
		function returnLessonList(){
			var path = $("#path").val();
			var url = path + "/course/lessonMain.jsp";
			
			$("#infoForm").attr("action",url);
			$("#infoForm").submit();		
		}
		
		/***保存新增课程信息*****/
		function saveLesson(){
			var path = $("#path").val();
			var flag = valid();
			if(flag){
				var url = path + "/lessonManage/lessonSave.do";
				var params = $("#infoForm").serialize();
				$.post(url,params,function(data){
					if(data.msg == 1){
						layer.alert('保存成功!',{skin: 'layui-layer-lan',shade: 0.2,icon: 10},function(index){
							returnLessonList();
							
							layer.close(index);			
						}); //layer提示框
					}else{
						layer.alert('保存失败!',{skin: 'layui-layer-lan',shade: 0.2,icon: 0}); //layer提示框			
					}
				});
			}
		}
		
		/***保存修改课类信息***/
		function updateLesson(){
			var path = $("#path").val();
			var flag = valid();
			if(flag){
				var url = path + "/lessonManage/lessonUpdate.do";
				var params = $("#infoForm").serialize();
				$.post(url,params,function(data){
					if(data.msg == 1){
						layer.alert('保存成功!',{skin: 'layui-layer-lan',shade: 0.2,icon: 10},function(index){
							returnLessonList();
							
							layer.close(index);									
						}); //layer提示框
					}else{
						layer.alert('保存失败!',{skin: 'layui-layer-lan',shade: 0.2,icon: 0}); //layer提示框			
					}
				});
			}
		}
		
		/***新增前验证ID是否可用***/
		var exitValid = true;
		function checkId(){
			var path = $("#path").val();
			var id = $("#lessonCode").val().trim();
			//var reg = /^\w*$/;  //字母、数字、下划线
			var reg = /^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$/;  //字母、数字、下划线,但不能以下划线开头或结尾
			if(id != ""){
				var url = path +"/lessonManage/checkLessonCode.do?lessonCode="+id;
				$.post(url,null,function(data){
					if(data.msg == true){
						if(!reg.test(id)){
							exitValid = false;
							layer.tips('该代码包含字母、数字、下划线,但不能以下划线开头或结尾', '#lessonCode',{tips: [1, '#F63'],time:0});
						}else{
							exitValid = true;
							layer.tips('该代码可以使用', '#lessonCode',{tips: [1, '#78BA32']});
						}
					}else{
						exitValid = false;
						layer.tips('该代码已存在,请更换', '#lessonCode',{tips: [1, '#F63'],time:0});
					}
				});
			}else{
				layer.closeAll('tips'); //关闭所有的tips层  
			}
		}
		
		/*****验证数据完整性******/
		function valid(){
			var id = $("#lessonCode").val().trim();
			var reg = /^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$/;  //字母、数字、下划线,但不能以下划线开头或结尾
			if(id == ""){
				$("#lessonCode").focus();
				layer.tips('课程分类代码不能为空', '#lessonCode',{tips: [1, '#aaa']});
				return false;
			}
			if(!reg.test(id)){
				$("#lessonCode").focus();
				layer.tips('该代码包含字母、数字、下划线,但不能以下划线开头或结尾', '#lessonCode',{tips: [1, '#F63'],time:0});
				return false;
			}	
			
			if(!exitValid){
				$("#lessonCode").focus();
				layer.tips('该代码已存在,请更换', '#lessonCode',{tips: [1, '#F63'],time:0});
				return false;
			}
				
			var name = $("#lessonName").val().trim();
			if(name == ""){
				$("#lessonName").focus();
				layer.tips('课程名称不能为空', '#lessonName',{tips: [1, '#aaa']});
				return false;
			}
			
			return true;
		}
		
	</script>
  </head>
  
  <body>
  	<input type="hidden" id="path" name="path" value="${contextPath}"/>
	<div class="centercontent">
		<baf:nav />
	    	<div id="contentwrapper" class="contentwrapper">
	    		<div class="pageheader_tab">
					<ul class="hornav">
						<li id="tab1" class="current"><a href="javascript:void(0);">课程编辑</a></li>
					</ul>
					<ul class="buttonlist_tab">
						<li><a href="javascript:void(0);" onclick="returnLessonList();" class="btn btn_return btn_green"><span>返回</span></a></li>
					</ul>
				</div><!--Tab页区域-->	
		    	<div id="infoDiv" class="infoDiv">
					<form class="stdform stdform2" id="infoForm" name="infoForm" method="post" action="">	
			        	<p>	
				            <label>课程代码：</label><input type="hidden" name="lessonId" id="lessonId"  value="${lesson.lessonId}"/>
				            <span class="field"><input type="text" name="lessonCode" id="lessonCode" class="longinput" <c:if test="${lessonStatus=='view'}" ><c:out value="readonly=readonly onfocus=lessonCode.blur()"/></c:if> <c:if test="${lessonStatus!='view'}" ><c:out value="oninput=checkId(); onkeyup=checkId();"/></c:if> value="${lesson.lessonCode}"/></span>
				        </p>        		        
				        <p>	
				            <label>课程名称：</label>
				            <span class="field"><input type="text" name="lessonName" id="lessonName" class="longinput" value="${lesson.lessonName}"/></span>
				        </p>
				        <p>
				            <label>课程Logo：</label>		            
				            <span class="field">
				            	<span class="img_area">
				            	<input id="uploadImg" name="file" class="layui-upload-file" type="file">
					            <img id="showLogo" width="280px" height="180px" src="${contextPath}/<c:choose><c:when test="${!empty lesson.lessonLogo}"><c:out value="${lesson.lessonLogo}"/></c:when><c:otherwise><c:out value="images/lassonDefaultLogo.png"/></c:otherwise></c:choose>"/>
					            <input type="hidden" name="lessonLogo" id="lessonLogo" class="longinput" value="${lesson.lessonLogo}"/>
					            <input type="hidden" name="oldLessonLogo" id="oldLessonLogo" class="longinput" value="${lesson.lessonLogo}"/>
					            </span>
				            </span>
				        </p>		        		
					   	<p>
				            <label>课程类型：</label>
				            <span class="field">
				            	<baf:select name="lessonType" noAll="" defValue="0" sql="SELECT A.LESS_TYPE_ID id,A.LESS_TYPE_NAME name FROM BIZ_LESS_TYPE A WHERE A.LESS_TYPE_STATUS = 1  ORDER BY A.SEQUENCE" value="${lesson.lessonType}"/>		            
				            </span>
				        </p>
				        <p>
				            <label>课程讲师：</label>
				            <span class="field">
				            	<baf:select name="teaId" noAll="1" defValue="" sql="SELECT A.TEA_ID id,A.TEA_NAME name FROM BIZ_TEACHER A WHERE A.TEA_STATUS = 1  ORDER BY A.TEA_ID" value="${lesson.teaId}"/>		            
				            </span>
				        </p>
				        <p>
				            <label>课程课件上传：</label>		            
				            <span class="field">
				            	<input id="uploadFile" name="file" lay-type="file" class="layui-upload-file" type="file"/>
				            	<span id="showZipName" style="padding-top:10px;font-weight:bold;display:block;">${lesson.lessonZipName}</span>
				            	<input type="hidden" name="lessonZipName" id="lessonZipName" class="longinput" value="${lesson.lessonZipName}"/>
				            	<input type="hidden" name="lessonZipPath" id="lessonZipPath" class="longinput" value="${lesson.lessonZipPath}"/>					            
				            </span>
				        </p>
				        <p>
				            <label>课程课件主页：</label>		            
				            <span class="field">
				            	<input type="text" name="lessonAccessName" id="lessonAccessName" class="longinput" style="width:38%;" value="${lesson.lessonAccessName}"/> ( 例如：index.html )
				            	<input type="hidden" name="lessonAccessPath" id="lessonAccessPath" class="longinput" value="${lesson.lessonAccessPath}"/>					            
				            </span>
				        </p>		
				        <p>
				             <label>课程副标题： <small>描述课程副标题详细信息.</small></label>
				             <span class="field"><textarea cols="80" rows="3" name="lessonTitles" id="lessonTitles" class="longinput">${lesson.lessonTitles}</textarea></span>
				        </p>
				        <p>
				             <label>课程描述： <small>描述课程详细信息.</small></label>
				             <span class="field"><textarea cols="80" rows="5" name="lessonDesc" id="lessonDesc" class="longinput">${lesson.lessonDesc}</textarea></span>
				        </p>
				        <ul class="buttonlist center">
				        	<li>
				               <c:choose>
									<c:when test="${lessonStatus=='view'}">
										<input type="button" class="stdbtn btn_blue large" onclick="updateLesson();"value="保存"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="stdbtn btn_blue large" onclick="saveLesson();" value="保存"/>
								    </c:otherwise>						
								</c:choose>
							</li>
				        </ul>
			     	</form>   	
				</div><!--内容编辑区 -->
			</div><!-- 右侧内容区 -->	
	</div>
  </body>
</html>