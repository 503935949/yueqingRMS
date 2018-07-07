<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body>
	<h>添加</h>
	<form name="userForm" action="${contextPath}/modelfunction/pdservice/upLoadFile" method="post" enctype="multipart/form-data" >
		选择文件：<input type="file" name="file">
		
		<input type="submit" value="上传" >
		
		
	
	</form>
	
	
	
</body>
</html>