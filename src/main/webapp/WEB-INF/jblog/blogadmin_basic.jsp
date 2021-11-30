<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<form action="updateBlog" method="post">
		<table>
			<input id="blogId" name="blogId" value="${ blog.blogId }" type="hidden" />
			<tr>
				<th>블로그 제목 : </th>
				<td>
					<input id="title_input" type="text" name="title" value="${ blog.title }">
				</td>
			</tr>
			<tr>
				<th>블로그 태그 : </th>
				<td>
					<input id="tag_input" type="text" name="tag" value="${ blog.tag }">
				</td>
			</tr>
			<tr>
				<th>메인페이지 포스트 : </th>
				<td>
					<input id="cnt_input" type="text" readonly name="cntDisplayPost" value="${ blog.cntDisplayPost }">
				</td>
			</tr>
			<tr>
				<th>로고이미지 : </th>
				<td>
					<img src="images/j2eelogo.jpg" />
				</td>
			</tr>
		</table>
		<div class="submit_btn">
			<input id="submit_btn" type="submit" value="확인" />
		</div>
		</form>
	</div>
	<div class="footer">
		<br><br>
		<p>Spring Stories is powered by JBlog</p>
	</div>
</body>
</html>