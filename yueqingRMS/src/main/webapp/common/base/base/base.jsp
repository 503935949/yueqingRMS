<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- <%@ taglib uri="/tld/baf" prefix="baf"%> --%>
<%-- <%@ taglib uri="/tld/yueqingRMS" prefix="yrms"%> --%>
<!-- spring空间，本项目用于jsp国际化 -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!-- 封装项目路径，绝对路径 -->
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set var="base" scope="request">${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}/${contextPath}/</c:set>
<script>
var contextPath = "${contextPath}";
</script>

<!-- IE浏览器兼容 -->
<meta name="renderer" content="webkit|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- 页面字符集 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>




