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
	<div id="content">
					<p id="whereami">
					</p>
					<h1>
						Login
					</h1>
					<form action="login" method="post">
						<table cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td valign="middle" align="right">
									用户名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="userName" />
									<span style="color:red;">
									<%String info = (String)request.getAttribute("login_error"); %>
									<%= (info==null ? "" : info) %>
									</span>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" name="password" />
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="登录 &raquo;" />
						</p>
					</form>
				</div>
</body>
</html>