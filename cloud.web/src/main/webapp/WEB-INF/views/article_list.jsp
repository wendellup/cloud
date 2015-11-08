<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- <jsp:include page="header.jsp?id=123"/> --%>
article_list.jsp <br />
	<div>
		<li>
			<c:forEach items="${articleList}" var="info">
				<ul>
					<a
						href="${basePath}/blog/article/${info.id}">${info.title}</a>
				</ul>
			</c:forEach>
		</li>
	</div>
	<hr />
	<br />
	<br />
	标签:
	<div>
		<li>
			<c:forEach items="${tagList}" var="info">
				<ul>
					<a
						href="${basePath}${requestURI}?tag_id=${info.id}">${info.tagName}</a>
				</ul>
			</c:forEach>
		</li>
	</div>
	<hr />
	
<%@ include file="footer.jsp" %>
<%-- <jsp:include page="footer.jsp"/> --%>