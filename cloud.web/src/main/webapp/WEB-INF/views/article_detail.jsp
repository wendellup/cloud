<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- <jsp:include page="header.jsp?id=123"/> --%>
	<div style="min-height: 500px;">
		<div class="container">
			<div class="row">
				<div class="col-md-2">
				</div>
				<div class="col-md-8">
					<div>
						<h2>${article.title}</h2>
						<br /><br />
						${article.content} <br />
					</div>
				</div>
				<div class="col-md-2">
				</div>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp" %>
<%-- <jsp:include page="footer.jsp"/> --%>