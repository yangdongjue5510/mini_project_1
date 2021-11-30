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
	<div class="search_box">
		<form action="createBlog">
		  블로그 제목 : <input id="search_value" name="title" type="search" />
	   	  <input id="search_btn" type="submit" value="블로그 생성" /><br>
	   	  <div class="blank_box_1"></div>
	   	  <span><a href="/">메인 페이지로 이동</a></span>
		</form>
	</div>
</body>
</html>