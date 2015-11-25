<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- <jsp:include page="header.jsp?id=123"/> --%>
				<div class="col-md-6">
					<form method="post" action="${basePath}/manage/blog/article/add?param_id=${paramId}">
						<div style="height: 360px;">
							title:<input type="text" name="article.title" > <br />
							content:<input type="text" name="article.content" > <br />
							paramId:<input type="text" name="article.appParameter.id" value="${paramId}"> <br />
							<br /><br />
							<br /><br />
							<br /><br />
							<br /><br />
							标签:
							<div id="div_tag">
								<c:forEach items="${tagList}" var="info">
									<label><input type="checkbox" name="tagIds" value=${info.id}>${info.tagName}</label><br />
								</c:forEach>
							</div>
							<input type="submit">
						</div>
					</form>
				</div>
				<div class="col-md-4">
						
				</div>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp" %>
<%-- <jsp:include page="footer.jsp"/> --%>
