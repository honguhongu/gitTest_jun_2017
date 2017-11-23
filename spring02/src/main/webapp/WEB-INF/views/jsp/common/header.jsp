<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>footer</title>
</head>
<body>
<h1 style="text-align:center">헤더</h1>
<c:choose>
    <c:when test="${isLogOn==true }">
<h4 style="text-align:right"><a href="${pageContext.request.contextPath }/member/logout.do">로그아웃</a></h4>
    </c:when>
    <c:otherwise>
<h4 style="text-align:right"><a href="${pageContext.request.contextPath }/member/loginForm.do">로그인</a></h4>
    </c:otherwise>
</c:choose>
</body>
</html>
