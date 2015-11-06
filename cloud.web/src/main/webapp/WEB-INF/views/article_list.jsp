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
<%@ include file="footer.jsp" %>
<%-- <jsp:include page="footer.jsp"/> --%>