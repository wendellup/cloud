
$(document).ready(function() {
	//管理后台article_list页面添加tag
	  $('#article_list_form_tag').submit(function(event) {
		  var tagName = $('#tag_name').val();
		  var paramId = $('#hidden_paramId').val() ;

	    $.ajax({
	    	url: $("#article_list_form_tag").attr("action")+"?"+"tag_name="+tagName+"&param_id="+paramId,
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
	    		$("#article_list_div_tag").html(respContent);	
	    		$("#tag_name").val("");
	    	}
	    });

	    event.preventDefault();
	  });
});

//管理后台article_list页面删除标签
function articleListDeleteTag(tagId) {
	
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
                respContent += "<li>" + item.tagName +"&nbsp;&nbsp;<a href='javascript:articleListDeleteTag("+item.id+")'>删除</a></li>";
            });  
    		respContent = "<ul>" + respContent + "</ul>";
    		$("#article_list_div_tag").html(respContent);   		
    	}
    });
}