<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>  
<c:url  var="serverUrl" value="http://localhost:8090/web_ch23" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu</title>
</head>
<body>
<html> 
<body> 
<p>Menu</p>

<a href="${pageContext.request.contextPath}/member/listMember.do" >회원관리</a><br> 
<a href="${pageContext.request.contextPath}/board/listBoard.do">게시판</a> 
</body> 
</html>
</body>
</html>
    


