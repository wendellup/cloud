<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="header.jsp" %>
<%-- <jsp:include page="header.jsp?id=123"/> --%>
	<%-- <div>
		<ul>
			<c:forEach items="${articleList}" var="info">
				<li>
					<a
						href="${basePath}/blog/article/${info.id}">${info.title}</a>
				</li>
			</c:forEach>
		</ul>
	</div> --%>
			<div class="col-md-6">
				<br /><br />
				<a href="${basePath}/manage/blog/article/add?param_id=${paramId}">新增文章</a>
				<br />
				<div style="height: 360px;">
					<p>
						<div>
							<c:forEach items="${articleList}" var="info">
								<li>
									<a
										href="${basePath}/manage/blog/article/${info.id}">${info.title}</a>
								</li>
							</c:forEach>
						</div>
					</p>
				</div>
				
				<br />
			</div>
			<div class="col-md-4">
				<br /><br />
				标签:
				<div>
					<ul>
						<c:forEach items="${tagList}" var="info">
							<li>
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
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</div>
	
	
<%@ include file="footer.jsp" %>
<%-- <jsp:include page="footer.jsp"/> --%>