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
						<ul>
							<c:forEach items="${pageData.content}" var="info">
								<li>
									<a href="${basePath}/manage/blog/article/${info.id}">
										<big>${info.title}</big>
									</a>
								</li>
							</c:forEach>
						</ul>
					</p>
				</div>
				<c:if test='${pageData.total>0}'>
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
				</c:if>
				<br />
			</div>
			<div class="col-md-4">
				<br /><br />
				标签:
				<div id="div_tag">
					<ul>
						<c:forEach items="${tagList}" var="info">
							<li>
								${info.tagName} &nbsp;&nbsp;<a href="javascript:deleteTag(${info.id})">删除</a>
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