<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
		<a href="/">
			<img src="images/logo.jpg">
		</a>
	</div>
	<br><br><br>
	<div class="menu_item">
		<c:if test="${ empty user }">
			<span><a href="loginView">로그인</a></span>
		</c:if>
		<c:if test="${ !empty user }">
			<c:if test="${ empty userBlog }">
				<span><a href="createBlogView">블로그등록</a></span>
			</c:if>
			<c:if test="${ !empty userBlog }">
				<span><a href="blogMain?blogId=${ userBlog.blogId }">내 블로그로 가기</a></span>
			</c:if>
		</c:if>
	</div>
	<br>
	<div class="search_box">
		<form action="searchBlog" method="get">
		  <input id="search_value" name="searchValue" type="search" />
	   	  <input id="search_btn" type="submit" value="검색" />
	   	  <div class="blank_box_1"></div>
		  <input type="radio" id="blog_title" name="searchType" value="title" checked="checked">
		  <label for="blog_title">블로그 제목</label>
		  <input type="radio" id="blog_tag" name="searchType" value="tag">
		  <label for="blog_tag">태그</label>
		  <input type="radio" id="blog_bloger" name="searchType" value="blogger">
		  <label for="blog_bloger">블로거</label>
		</form>
	</div>
	<br>
		<c:if test="${ !empty blogs }">
			<div class="blog_list">
				<table>
					<tr>
						<th>블로그 제목</th>
						<th>블로거</th>
						<th>로고</th>
						<th>상태</th>
						<th>삭제</th>
					</tr>
					<c:forEach var="blogs" items="${ blogs }">
						<tr>
							<td><a href="blogMain?blogId=${ blogs.blogId }">${ blogs.title }</a></td>
							<td>${ blogs.user.userName }</td>
							<td><img id="table_logo_img" src="images/j2eelogo.jpg" alt="로고" /></td>
							<td>${ blogs.status }</td>
							<td>
								<c:if test="${ user.role eq 'ADMIN' }">
									<a href="deleteBlog?blogId=${ blogs.blogId }"><img id="table_delete_img" src="images/delete.jpg" alt="삭제" /></a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:if>
</body>
</html>