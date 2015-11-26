<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- <jsp:include page="header.jsp?id=123"/> --%>
				<div class="col-md-6">
					<form method="post" action="${basePath}/manage/blog/article/update">
						<div style="height: 360px;">
							title:<input type="text" name="article.title" value="${articleVO.article.title}">
							content:<input type="text" name="article.content" id="content" value="${articleVO.article.content}">
							paramId:<input type="text" name="article.appParameter.id" value="${articleVO.article.appParameter.id}">
							<input type="hidden" name="article.id" value="${articleVO.article.id}">
							<br /><br />
							<br /><br />
							<br /><br />
							<br /><br />
							标签:
							<div id="div_tag">
								<c:forEach items="${articleVO.allTagInfos}" var="info">
									<label>
										<input type="checkbox" name="articleTagIds" value=${info.id} 
											<c:if test="${fn:contains(articleVO.articleTagIds, info.id)}">  
		                                        checked = "true"
		                                    </c:if>  >
										${info.tagName}
									</label><br />
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
	
	<script type="text/javascript">
		
	</script>
<%@ include file="footer.jsp" %>
<%-- <jsp:include page="footer.jsp"/> --%>