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
        <b><a>카테고리</a></b>
        <a href="/blogAdminPost/${blog.blogId}">글작성</a>
    </div>
    <br>
    <table width="720" height="100" border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td width="320" class="tablelabel">번호</td>
            <td width="100" class="tablelabel">카테고리명</td>
            <td width="100" class="tablelabel">보이기 유형</td>
            <td width="100" class="tablelabel">포스트 수</td>
            <td width="100" class="tablelabel">설명</td>
            <td width="100" class="tablelabel">삭제</td>
        </tr>
        <c:forEach var="category" items="${categoryList}">
        <tr>
            <td class="tablecontent">&nbsp;&nbsp;&nbsp;<c:if test="${category.categoryName ne '분류없음'}"> <a href="/getCategory/${category.categoryId}"></c:if>${category.categoryId}</a></td>
            <td class="tablecontent" align="center">${category.categoryName}</td>
            <td class="tablecontent" align="center">${category.displayType}</td>
            <td class="tablecontent" align="center">${category.cntDisplayPost}</td>
            <td class="tablecontent" align="center">${category.description}</td>
            <td class="tablecontent" align="center">
                <c:if test="${category.categoryName eq '분류없음'}">삭제불가</c:if>
                <c:if test="${category.categoryName ne '분류없음'}">
                    <a href="/deleteCategory?blogId=${blogId}&categoryId=${category.categoryId}"><img height="9" src="/static/images/delete.jpg" border="0"></a>
                </c:if>
            </td>
        </tr>
        </c:forEach>
    </table>
    <br>
    <form action="/blogAdminCategory/${blog.blogId}" method="post">
        <div>
            <span>카테고리 명 : </span>
            <input type="text" name="categoryName"/>
        </div>
        <div>
            <span>보이기 유형 : </span>
            <input type="radio" name="displayType" value="제목" checked>제목
            <input type="radio" name="displayType" value="제목+내용">제목+내용
        </div>
        <div>
            <span>포스트 수 : </span>
            <input type="text" name="cntDisplayPost"/>
        </div>
        <div>
            <span>설명 : </span>
            <input type="text" name="description"/>
        </div>
        <br>
        <input type="submit" value="카테고리 추가"/>
    </form>

</center>
</body>
</html>