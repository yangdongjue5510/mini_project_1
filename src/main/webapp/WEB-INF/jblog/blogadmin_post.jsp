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
			<div class="link_box">
				<span><a href="logout">로그아웃</a></span>
				<span><a href="blogMain?blogId=${ blog.blogId }">내 블로그 메인</a></span>
			</div>
		</div>
	</div>
	<br>
	<div class="content_box">
		<div class="setting_menu_box">
			<a href="blogAdmin?blogId=${ blog.blogId }">기본설정</a>&nbsp;
			<a href="blogAdminCategory?blogId=${ blog.blogId }">카테고리</a>&nbsp;
			<a href="blogAdminPost?blogId=${ blog.blogId }">글작성</a>
			<a href="blogDeleteRequest?blogId=${ blog.blogId }">삭제요청</a>
		</div>
		<br>
		<form action="<c:if test="${ updatePost == null }">insertPost</c:if><c:if test="${ updatePost != null }">updatePost</c:if>" method="post">
			<input id="blogId" name="blogId" value="${ blog.blogId }" type="hidden" />
			<c:if test="${ updatePost != null }">
				<input id="postId" name="postId" value="${ updatePost.postId }" type="hidden" />
			</c:if>
			<table>
				<tr>
					<th>제목 : </th>
					<td>
						<input id="title_input" type="text" name="title" value="<c:if test="${ updatePost != null }">${ updatePost.title }</c:if>">
						<select name="categoryId">
							<c:forEach var="categorys" items="${ categorys }">
								<option value="${ categorys.categoryId }" <c:if test="${ updatePost.category.categoryId == categorys.categoryId }">selected</c:if>>${ categorys.categoryName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>내용 : </th>
					<td>
					<textarea id="content" name="content" rows="10" cols="100"><c:if test="${ updatePost != null }">${ updatePost.content }</c:if></textarea>
					</td>
				</tr>
			</table>
			<br>
			<div class="submit_btn">
				<input id="submit_btn" type="submit" value="<c:if test="${ updatePost == null }">확인</c:if><c:if test="${ updatePost != null }">수정</c:if>" />
			</div>
		</form>
	</div>
	<div class="footer">
		<br><br>
		<p>Spring Stories is powered by JBlog</p>
	</div>
</body>
</html>