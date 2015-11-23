<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%-- <jsp:include page="header.jsp?id=123"/> --%>
				<div class="col-md-6">
					<div style="height: 360px;">
						<form method="post" action="${basePath}/manage/blog/article/add?param_id=${paramId}">
							title:<input type="text" name="title" id="title" >
							content:<input type="text" name="content" id="content" >
							paramId:<input type="text" name="appParameter.id" value="${paramId}" id="appParameter.id">
							<input type="submit">
						</form>
					</div>
				</div>
				<div class="col-md-4">
					<br /><br />
					标签:
					<div>
						<ul>
							<c:forEach items="${tagList}" var="info">
								<li>
									<a>${info.tagName}</a>
								</li>
							</c:forEach>
							<form id="tagForm" method="post" action="${basePath}/manage/blog/tag/add.json">
								tagName:<input type="text" name="tag_name" id="tag_name" >
								<input type="submit">
							</form>
							<div id="retDiv">
							</div>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp" %>
<%-- <jsp:include page="footer.jsp"/> --%>

<script type="text/javascript">
$(document).ready(function() {
	  $('#tagForm').submit(function(event) {

		  var tagName = $('#tag_name').val();
		  var paramId = $('#hidden_paramId').val() ;

	    $.ajax({
	    	url: $("#tagForm").attr("action"),
	    	data: "tag_name="+tagName+"&param_id="+paramId,
	    	type: "POST",

	    	beforeSend: function(xhr) {
	    		xhr.setRequestHeader("Accept", "application/json");
	    		xhr.setRequestHeader("Content-Type", "application/json");
	    	},
	    	success: function(data) {
	    		var respContent = "";

	    		jQuery.each(data, function(i,item){     
	                respContent += item.tagName;     
	            });  

	    		$("#retDiv").html(respContent);   		
	    	}
	    });

	    event.preventDefault();
	  });

	});
</script>
