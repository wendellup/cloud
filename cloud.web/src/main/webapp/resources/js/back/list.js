/**
 * 调用后台批量删除方法
 */
function deleteBatch(basePath) {
	$("#mainForm").attr("action",basePath + "DeleteBatchServlet.action");
	$("#mainForm").submit();
}

/**
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(currentPage) {
	var url = document.getElementById('hidden_path').value ;
//	alert("${basePath}");
//	var href = ${basePath}${requestURI}+"?current_page="+currentPage;
//	alert(href);
	url += "?current_page="+currentPage;
	var rowsOfPage = document.getElementById('hidden_rowsOfPage').value ;
	if(rowsOfPage!=0){
		url += "&rows_of_page="+rowsOfPage;
	}
//	alert(url);
	location.href=url;
}

//管理后台删除标签
function deleteTag(tagId) {
	
	var basePath = document.getElementById('hidden_basePath').value ;
	var paramId = document.getElementById('hidden_paramId').value ;
	$.ajax({
    	url: basePath + "/manage/blog/tag/delete?tag_id=" + tagId+"&param_id="+paramId,
    	//data: "tag_name="+tagName+"&param_id="+paramId,
    	type: "POST",

    	beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
    	success: function(data) {
    		var respContent = "";
    		jQuery.each(data, function(i,item){     
                respContent += "<li>" + item.tagName +"&nbsp;&nbsp;<a href='javascript:deleteTag("+item.id+")'>删除</a></li>";
            });  
    		respContent = "<ul>" + respContent + "</ul>";
    		$("#div_tag").html(respContent);   		
    	}
    });
}