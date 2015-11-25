<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.cloud.*, java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	if(basePath.endsWith("/")){
		basePath = basePath.substring(0, basePath.length()-1);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>${article.title}</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="${article.title}">
<meta http-equiv="description" content="${article.title}">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet"
		href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
	
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
	<link href="<%= basePath %>/resources/css/common.css" rel="stylesheet" type="text/css" />
	<%-- <script src="<%= basePath %>/resources/js/common/jquery-1.8.0.min.js"></script> --%>
	<script src="<%= basePath %>/resources/js/back/list.js"></script>
	<script src="<%= basePath %>/resources/js/manage/article_list.js"></script>
	<!-- 
	<script type="text/javascript">
		alert("<%= basePath %>/resources/css/all.css");
	</script>-->
</head>

<body>
	<input type="hidden" value="<%= basePath %>" id="hidden_basePath"/>
	<input type="hidden" value="<%= basePath %>${requestURI}" id="hidden_path"/>
	<input type="hidden" value="${param.rows_of_page}" id="hidden_rowsOfPage"/>
	<input type="hidden" value="${paramId}" id="hidden_paramId"/>

	header...

	<%-- <div class="navbar navbar-default navbar-fixed-top " role="navigation">
		<div class="container">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8">
					<ul class="nav navbar-nav">
						<c:forEach items="${appParameterList}" var="info">
							<li class="
								<c:if test='${fn:startsWith(requestURI, info.param)}'>
									active
								</c:if>
							">
								<a href="${basePath}${info.param}">${info.name}</a>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
	</div> --%>

	<%-- <div>
		<li>
			<c:forEach items="${appParameterList}" var="info">
				<ul>
					<a
						href="${basePath}${info.remark}">${info.name}</a>
						<c:if test="${fn:startsWith(requestURI, info.remark)}">
						 	active
						</c:if>
						<c:if test="${requestURI==info.remark}">
							active
						</c:if>
				</ul>
			</c:forEach>
		</li>
	</div> --%>
	<hr />
	
	<div style="height: 500px;">
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<div>
					<li>
						<c:forEach items="${appParameterList}" var="info">
							<ul>
								<a
									href="${basePath}/manage/blog/article/list?param_id=${info.id}">${info.name}</a>
									<c:if test="${paramId == info.id}">
									 	active
									</c:if>
							</ul>
						</c:forEach>
					</li>
				</div>
			</div>