<%--
  Created by IntelliJ IDEA.
  User: michal
  Date: 12.07.2021
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

    <link rel="stylesheet" href="<c:url value="${pageContext.request.contextPath}/resources/css/style.css"/>"/>
</head>
<body>

<%@ include file="header.jsp"%>

<section class="stats">
        <h2><p>${url}</p></h2><br>
        <h2><p>${errorMessage}</p></h2><br>
        <h2><p>${exception}</p></h2><br>
</section>

<%@ include file="footer.jsp"%>

<script src="<c:url value="${pageContext.request.contextPath}/resources/js/app.js"/>"></script>
</body>
</html>
