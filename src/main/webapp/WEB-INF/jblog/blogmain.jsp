<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/theme.css" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>JBlog</title>
</head>
<body>
	<div class="head_box">
		<div class="text_box">
			<h1>${ blog.title }</h1>
			<p>${ blog.tag }</p>
		</div>
	</div>
	<div class="content_box">
		<div class="post_box">
			<c:if test="${ empty posts }">
				<div class="post">
					<h2></h2>
					<p></p>
					<p></p>
					<p class="post_by_user"></p>
				</div>
			</c:if>
			<c:forEach var="posts" items="${ posts }">
				<div class="post">
					<h2>${ posts.title }&nbsp;<c:if test="${ user.userId == blog.user.userId }"><a href="updatePostView?postId=${ posts.postId }&blogId=${ blog.blogId }">EDIT</a>&nbsp;<a href="deletePost?postId=${ posts.postId }&blogId=${ blog.blogId }<c:if test="${ !empty categoryId }">&categoryId=${ categoryId }</c:if> ">DEL</a></c:if></h2>
					<p>${ posts.createdDate }</p>
					<c:if test="${ posts.category.displayType == 'all' }"><p>${ posts.content }</p></c:if>
					<p class="post_by_user">${ user.userName }</p>
				</div>
			</c:forEach>
		</div>
		<br>
			<br>
			<div class="menu_item">
				<c:if test="${ user.userId != blog.user.userId }">
					<span><a href="loginView">로그인</a></span>
				</c:if>
				<c:if test="${ user.userId == blog.user.userId }">
					<span><a href="logout">로그아웃</a></span>
					<span><a href="blogAdmin?blogId=${ blog.blogId }">블로그관리</a></span>
				</c:if>
			</div>
			<br>
			<div class="category_box">
				<img src="images/j2eelogo.jpg" alt="로고" />
				<ul>
					<li class="categorytitle">카테고리</li>
					<c:forEach var="categorys" items="${ categorys }">
						<a href="searchPostByCategory?categoryId=${ categorys.categoryId }&blogId=${ blog.blogId }"><li class="categoryitem">${ categorys.categoryName }</li></a>
					</c:forEach>
				</ul>
				<img src="images/logo.jpg" alt="로고" />
			</div>
		</div>
	</div>
	<div class="footer">
		<br><br>
		<p>Spring Stories is powered by JBlog</p>
	</div>
</body>
</html>