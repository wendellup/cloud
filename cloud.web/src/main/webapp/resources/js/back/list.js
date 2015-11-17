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