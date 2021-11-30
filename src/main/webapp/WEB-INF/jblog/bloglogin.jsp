<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/theme.css">
<link rel="stylesheet" href="css/style.css">
<title>JBlog</title>
</head>
<body>
	<div class="blog_logo">
		<img src="images/logo.jpg">
	</div>
	<br><br><br>
	<div class="login_box">
		<form action="login" method="post">
		  아이디 : <input type="text" id="id_input" name="id" />&nbsp;
		  패스워드 : <input type="password" id="pw_input" name="password" /><br><br>
		  <input id="login_btn" type="submit" value="로그인" />
		</form>
	</div>
</body>
</html>