<script type="text/javascript">
$.ajax({
	type: "POST",
	url: "${createLink(controller: 'tree', action: 'initTree')}?fromBlogTag=true",
	success: function (data) {
		$('#clientMenuTree').tree({
		    checkbox:false,
		    data:data
	 	}); 
    }
});

</script>
<div id="clientMenuTree"></div>