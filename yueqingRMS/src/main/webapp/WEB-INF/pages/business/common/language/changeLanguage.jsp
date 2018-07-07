

<!-- 本页面在本项目暂时没有用，下面的方法是基于session进行国际化处理，7/22，林曌 -->
<%@ page language="java" pageEncoding="UTF-8" %>
<%@page import="java.util.Locale"%>
<%@page
	import="org.springframework.web.servlet.i18n.SessionLocaleResolver"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
</head>
<%
String language = request.getParameter("language");
Locale locale = null;
if ("zh_CN".equals(language)) {
	locale = Locale.CHINA;
} else {
	locale = Locale.US;
}
request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
%>
<script type="text/javascript">
window.location = document.referrer;
</script>
<body>
</body>
</html>