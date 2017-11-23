<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인창</title>
</head>
<body>
<h1>로그인창</h1>
<form method="post" action="${pageContext.request.contextPath }/member/login.do">
<table>
<tr>
<td>아이디</td>
<td><input type="text" size=20 name="id"/></td>
</tr>

<tr>
<td>비밀번호</td>
<td><input type="password" size=20 name="passwd"/></td>
</tr>

<tr>
<td colspan="2">
<input type="submit" value="로그인"/>
<input type="reset" value="초기화"/>
</td>
</tr>

</table>
</form>
</body>
</html>
