<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.cloud.valueobject.entity.Test"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	This is my JSP page.
	<br> Test.........
	<c:out value="${test.name}"></c:out>
	<br>
	<c:out value="${a}"></c:out>
	<br>
	<c:out value="${b}"></c:out>
	<br>

	<hr />
	<div>
		<table>
			<c:forEach items="${appParameters}" var="info">
				<tr>
					<td>${info.id}</td>
					<td>${info.name}</td>
					<td>${info.remark}</td>
				</tr>
			</c:forEach>

		</table>
	</div>

</body>
</html>
