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
        <b><a>기본 설정</a></b>
        <a href="/blogAdminCategory/${blog.blogId}">카테고리</a>
        <a href="/blogAdminPost/${blog.blogId}">글작성</a>
    </div>
    <br>
    <form action="/blogAdminBasic/${blog.blogId}" method="post">
        <div>
            <span>블로그 제목 :</span>
            <input type="text" name="title" value="${blog.title}"/>
        </div>
        <div>
            <span>블로그 태그 :</span>
            <input type="text" name="tag" value="${blog.tag}"/>
        </div>
        <div>
            <span>메인페이지 포스트 :</span>
            <input type="text" name="cntDisplayPost" value="${blog.cntDisplayPost}"/>
        </div>
        <div>
            <span>로고 이미지 :</span>
            <img src="/static/images/j2eelogo.jpg"/>
        </div>
        <br>
        <input type="submit" value="확인"/>
    </form>

</center>
</body>
</html>