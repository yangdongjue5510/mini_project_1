<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${blog.title}</title>
    <Link rel="stylesheet" href="css/theme.css">
</head>
<body>
<center>
    <header>
        <h1>${blog.title}</h1>
        <h3>${blog.tag}</h3>
        <div>
            <c:if test="${user == null }">
                <a href="/loginView">로그인</a>
            </c:if>
            <c:if test="${user != null }">
                <a href="/logout">로그아웃</a>
                <a href="/blogMain/${blog.blogId}">내 블로그 메인</a>
            </c:if>
        </div>
        <br>
    </header>
    <div>
        <a href="/blogAdmin/${blog.blogId}">기본 설정</a>
        <a href="/blogAdminCategory/${blog.blogId}">카테고리</a>
        <b><a>글작성</a></b>

    </div>
    <br>
    <c:if test="${empty post}">
        <form action="/insertPost/${blog.blogId}" method="post">
            </c:if>
        <c:if test="${not empty post}">
        <form action="/updatePost/${blog.blogId}/${post.postId}" method="post">
            </c:if>

            <select name="categoryId">
                <c:forEach var="category" items="${categoryList}">
                    <option value="${category.categoryId}"
                            <c:if test="${post.categoryId == category.categoryId}">
                                selected
                            </c:if>
                    >${category.categoryName}</option>
                </c:forEach>
            </select>
            <div>
                <span>포스트 제목 :</span>
                <input type="text" name="title" value="${post.title}"/>
            </div>
            <div>
                <span>내용 :</span>
                <textarea name="content">${post.content}</textarea>
            </div>
            <br>
            <input type="submit"
                    <c:if test="${empty post}">
                        value="확인"
                    </c:if>
                    <c:if test="${not empty post}">
                        value="수정하기"
                    </c:if>/>
        </form>
</center>
</body>
</html>