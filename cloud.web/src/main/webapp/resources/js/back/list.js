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
	alert("call list.js");
//	$("#currentPage").val(currentPage);
//	$("#mainForm").submit();
	location.href="http://www.baidu.com";
}