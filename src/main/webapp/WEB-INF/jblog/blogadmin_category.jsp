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
	<div class="category_list_box">
		<div class="setting_menu_box">
			<a href="blogAdmin?blogId=${ blog.blogId }">기본설정</a>&nbsp;
			<a href="blogAdminCategory?blogId=${ blog.blogId }">카테고리</a>&nbsp;
			<a href="blogAdminPost?blogId=${ blog.blogId }">글작성</a>
			<a href="blogDeleteRequest?blogId=${ blog.blogId }">삭제요청</a>
		</div>
		<br>
		<table>
			<tr>
				<th>번호</th>
				<th>카테고리명</th>
				<th>보이기 유형</th>
				<th>포스트 수</th>
				<th>설명</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="categorys" items="${ categorys }">
				<tr>
					<td>${ categorys.categoryId }</td>
					<td><a href="updateCategoryView?categoryId=${ categorys.categoryId }&blogId=${ blog.blogId }">${ categorys.categoryName }</a></td>
					<td><c:if test="${ categorys.displayType == 'all' }">제목+내용</c:if><c:if test="${ categorys.displayType == 'title' }">제목</c:if></td>
					<td>${ categorys.cntDisplayPost }</td>
					<td>${ categorys.description }</td>
					<c:if test="${ categorys.categoryName == '미분류' }">
						<td>삭제불가</td>
					</c:if>
					<c:if test="${ categorys.categoryName != '미분류' }">
						<td><a href="deleteCategory?categoryId=${ categorys.categoryId }&blogId=${ blog.blogId }"><img id="table_delete_img" src="images/delete.jpg" alt="삭제" /></a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br><br><br>
	<div class="content_box">
		<p>카테고리 <c:if test="${ updateCategory == null }">추가</c:if>
				  <c:if test="${ updateCategory != null }">수정</c:if></p>
		<form action="<c:if test="${ updateCategory == null }">insertCategory</c:if><c:if test="${ updateCategory != null }">updateCategory</c:if>" method="post">
			<input id="blogId" name="blogId" value="${ blog.blogId }" type="hidden" />
			<c:if test="${ updateCategory != null }">
				<input id="categoryId" name="categoryId" value="${ updateCategory.categoryId }" type="hidden" />
			</c:if>
			<table>
				<tr>
					<th>카테고리명 : </th>
					<td>
						<input id="category_name_input" type="text" name="categoryName" value="<c:if test="${ updateCategory != null }">${ updateCategory.categoryName }</c:if>">
					</td>
				</tr>
				<tr>
					<th>보이기유형 : </th>
					<td>
						<input type="radio" id="blog_title" name="displayType" value="title" <c:if test="${ updateCategory.displayType == 'title' }">checked</c:if>>
						<label for="blog_title">제목</label>
						<input type="radio" id="blog_all" name="displayType" value="all" <c:if test="${ updateCategory.displayType == 'all' }">checked</c:if>>
						<labe for="blog_all">제목+내용</labe>
					</td>
				</tr>
				<tr>
					<th>포스트 수 : </th>
					<td>
						<input id="cnt_display_post_input" type="number" name="cntDisplayPost" min="1" max="20" value="<c:if test="${ updateCategory == null }"></c:if><c:if test="${ updateCategory != null }">${ updateCategory.cntDisplayPost }</c:if>">개(1~20)
					</td>
				</tr>
				<tr>
					<th>설명 : </th>
					<td>
						<input id="description" type="text" name="description" value="<c:if test="${ updateCategory == null }"></c:if><c:if test="${ updateCategory != null }">${ updateCategory.description }</c:if>">
					</td>
				</tr>
			</table>
			<div class="submit_btn">
				<input id="submit_btn" type="submit" value="<c:if test="${ updateCategory == null }">추가</c:if><c:if test="${ updateCategory != null }">수정</c:if>" <c:if test="${ updateCategory.categoryName == '미분류' }">disabled</c:if> />
			</div>
		</form>
	</div>
	<div class="footer">
		<br><br>
		<p>Spring Stories is powered by JBlog</p>
	</div>
</body>
</html>