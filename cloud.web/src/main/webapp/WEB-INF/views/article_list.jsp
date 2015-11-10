<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	<div>
		<li>
			<c:forEach items="${pageData.content}" var="info">
				<ul>
					<a
						href="${basePath}/blog/article/${info.id}">${info.title}</a>
				</ul>
			</c:forEach>
		</li>
	</div>
	<div class='page fix'>
		共 <b>${pageData.total}</b> 条
		<c:if test="${pageData.currentPage != 0}">
			<a href="javascript:changeCurrentPage('0')" class='first'>首页</a>
			<a href="javascript:changeCurrentPage('${pageData.currentPage-1}')"
				class='pre'>上一页</a>
		</c:if>
		当前第<span>${pageData.currentPage+1}/${pageData.pageCount}</span>页
		<c:if test="${pageData.currentPage+1 != pageData.pageCount}">
			<a href="javascript:changeCurrentPage('${pageData.currentPage+1}')"
				class='next'>下一页</a>
			<a href="javascript:changeCurrentPage('${pageData.pageCount-1}')"
				class='last'>末页</a>
		</c:if>
		跳至&nbsp;<input id="currentPageText" type='text'
			value='${pageData.currentPage+1}' class='allInput w28' />&nbsp;页&nbsp; <a
			href="javascript:changeCurrentPage($('#currentPageText').val()-1)"
			class='go'>GO</a>
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
						<%-- <c:if test="${empty fn:startsWith(requestURI, info.remark)}"> --%>
						<c:choose>
							<c:when test="${fn:substringBefore(requestURI, '/tag') == ''}">
								href="${basePath}${requestURI}/tag/${info.id}"
							</c:when>
							<c:otherwise>
								href="${fn:substringBefore(requestURI, '/tag')}/tag/${info.id}"
							</c:otherwise>
						</c:choose> 
						>
						${info.tagName}</a>
				</ul>
			</c:forEach>
		</li>
	</div>
	<hr />
	
<%@ include file="footer.jsp" %>
<%-- <jsp:include page="footer.jsp"/> --%>