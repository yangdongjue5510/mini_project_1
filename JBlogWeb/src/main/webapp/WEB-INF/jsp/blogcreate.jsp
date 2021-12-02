<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>login</title>
</head>
<body>
<center>
    <img src="/static/images/logo.jpg">
    <br>
    <hr>
    <form action="blogCreate" method="post">
        <a type="text">블로그 제목 : </a>
        <input type="text" name="blogName"/>
        <input type="submit" value="블로그 생성"/>
    </form>
    <br>
    <a href="/">인덱스 페이지로 이동</a>
    <hr>
</center>
</body>
</html>
