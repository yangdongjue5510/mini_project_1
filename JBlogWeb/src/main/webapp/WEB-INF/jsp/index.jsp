<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBlog 홈</title>
    <Link rel="stylesheet" href="css/theme.css">
</head>
<body>
<center>
    <form action="/searchBlog" method="post">
        <table width="100%" height=320 border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td height=40 colspan="10">&nbsp;</td>
            </tr>
            <tr>
                <td width="100%" height="120" colspan="10" align="center">
                    <img src="static/images/logo.jpg" border="0">
                </td>
            </tr>
            <tr>
                <td width="30%" height="30">&nbsp;</td>
                <td width="70%" colspan="2">
                    <c:if test="${user == null}">
                        <a href="loginView"><b>로그인</b></a>&nbsp;&nbsp;
                    </c:if>

                    <c:if test="${user != null}">
                        <a href="/logout"><b>로그아웃</b></a>
                        <c:if test="${blogId == null}">
                            <a href="blogCreateView"><b>블로그등록</b></a>
                        </c:if>
                        <c:if test="${blogId != null}">
                            <a href="/blogMain/${blogId}"><b>내 블로그로 가기</b></a>
                        </c:if>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td width="40%" height="20">&nbsp;</td>
                <td width="60%"><input type="text" name="searchKeyword" size="65"><input type="submit" value="검색"></td>
            </tr>
            <tr>
                <td height="20" colspan="10" align="center" class="tdcontent">
                    <input type="radio" name="searchCondition" value="TITLE"
                    <c:if test="${searchCondtion == null or searchCondition eq 'TITLE'}">
                           checked="checked"
                    </c:if>>블로그 제목
                    <input type="radio" name="searchCondition" value="TAG"
                    <c:if test="${searchCondition eq 'TAG'}">
                           checked="checked"
                    </c:if>>태그&nbsp;&nbsp;
                    <input type="radio" name="searchCondition" value="USER_NAME"
                    <c:if test="${searchCondition eq 'USER_NAME'}">
                           checked="checked"
                    </c:if>>블로거
                </td>
            </tr>
        </table>
    </form>


    <c:forEach var="blog" items="${blogList}">
        <c:if test="${not empty blogList}">
            <table width="720" height="100" border="1" cellpadding="0" cellspacing="0">
                <tr>
                    <td width="420" class="tablelabel">블로그 제목</td>
                    <td width="100" class="tablelabel">블로거</td>
                    <td width="100" class="tablelabel">로고</td>
                    <td width="100" class="tablelabel">상태</td>
                    <c:if test="${user.role eq 'ADMIN'}">
                        <td width="100" class="tablelabel">삭제</td>
                    </c:if>
                </tr>

                <tr>
                    <td class="tablecontent">&nbsp;&nbsp;&nbsp;<a href="/blogMain/${blog.blogId}">${blog.title}</a></td>
                    <c:forEach var="blogUser" items="${userList}">
                        <c:if test="${blogUser.userId eq blog.blogId}">
                            <td class="tablecontent" align="center">${blogUser.userName}</td>
                        </c:if>
                    </c:forEach>
                    <td class="tablecontent" align="center"><img height="32" src="static/images/j2eelogo.jpg"
                                                                 border="0"></td>
                    <td class="tablecontent" align="center">${blog.status}</td>
                    <td class="tablecontent" align="center">
                        <c:if test="${user.role eq 'ADMIN'}">
                            <a href="/deleteBlog/${blog.blogId}"><img height="9" src="static/images/delete.jpg"
                                                                      border="0"></a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </c:if>
    </c:forEach>
</center>
</body>
</html>