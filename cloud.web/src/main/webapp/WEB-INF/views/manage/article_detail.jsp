<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- <jsp:include page="header.jsp?id=123"/> --%>
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
									<c:if test="${param.param_id == info.id}">
									 	active
									</c:if>
							</ul>
						</c:forEach>
						</li>
					</div>
				</div>
				<div class="col-md-6">
					<div style="height: 360px;">
						<form method="post" action="${basePath}/manage/blog/article/add?param_id=${param.param_id}">
							title:<input type="text" name="title" id="title">
							content:<input type="text" name="content" id="content">
							paramId:<input type="text" name="paramId" value="${param.param_id}" id="paramId">
							<input type="submit">
						</form>
					</div>
				</div>
				<div class="col-md-4">
				</div>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp" %>
<%-- <jsp:include page="footer.jsp"/> --%>