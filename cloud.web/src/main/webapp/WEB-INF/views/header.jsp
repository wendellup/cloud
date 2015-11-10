<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.cloud.*, java.util.List, org.springframework.context.support.ClassPathXmlApplicationContext
	, org.springframework.context.ApplicationContext, com.cloud.service.AppParameterService
	, com.cloud.valueobject.entity.AppParameter
	, com.cloud.valueobject.constvar.ConstVar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="<%= basePath %>/resources/css/common.css" rel="stylesheet" type="text/css" />
	<script src="<%= basePath %>/resources/js/common/jquery-1.8.0.min.js"></script>
	<script src="<%= basePath %>/resources/js/back/list.js"></script>
	<!-- 
	<script type="text/javascript">
		alert("<%= basePath %>/resources/css/all.css");
	</script>-->
</head>

<body>
	<input type="hidden" value="<%= basePath %>${requestURI}" id="path"/>
	<input type="hidden" value="${param.rows_of_page}" id="rowsOfPage"/>
	

	header...
	<div>
		<li>
			<c:forEach items="${appParameterList}" var="info">
				<ul>
					<a
						href="${basePath}${info.remark}">${info.name}</a>
						<c:if test="${fn:startsWith(requestURI, info.remark)}">
						 	active
						</c:if>
						<%-- <c:if test="${requestURI==info.remark}">
							active
						</c:if> --%>
				</ul>
			</c:forEach>
		</li>
	</div>
	<hr />