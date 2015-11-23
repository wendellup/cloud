<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- <jsp:include page="header.jsp?id=123"/> --%>
				<div class="col-md-6">
					<div style="height: 360px;">
						<form method="post" action="${basePath}/manage/blog/article/add?param_id=${paramId}">
							title:<input type="text" name="title" id="title" value="${article.title}">
							content:<input type="text" name="content" id="content" value="${article.content}">
							paramId:<input type="text" name="appParameter.id" value="${paramId}" id="appParameter.id">
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