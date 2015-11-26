<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<html>
	<head>
	</head>
	<body>
		<h1>
			注销成功,2秒钟后跳转到登陆界面
		</h1>
		<script type="text/javascript">
			var timer;
			function startRedirect(){
				timer = window.setTimeout(redirect,2000);
			}
			function redirect(){
				window.location = "manage/blog/login";
			}
			startRedirect();
		</script>
	</body>
</html>