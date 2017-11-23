<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" 
    isELIgnored="false"
    %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원정보 상세창</title>
<script>
function fn_show(){
	
	result=confirm("수정하시겠습니까?");
	if(result==true){
	d_detail=document.getElementById("div_detail");
	d_modMember=document.getElementById("div_modMember");
	
	d_detail.style.display="none";
	d_modMember.style.display="block";
	}
}

function fn_delete(id){
	alert(id);
	result=confirm("삭제하시겠습니까?");
	    alert(id);
	    if(result==true){
	    var url="${pageContext.request.contextPath}/member/delMember.do";
	    var form=document.createElement("form");
	    form.setAttribute("method", "get");
	    form.setAttribute("action", url);
	    document.body.appendChild(form);
	    
	    var input = document.createElement("input");
	    input.setAttribute("type","hidden");
	    input.setAttribute("name","id");
	    input.setAttribute("value",id);
	    form.appendChild(input);
	    form.submit();
	    }else{
	        return;
	    }
}
</script>
<!-- <script>
function fn_modMember(){
	
	result=confirm("수정완료되었습니다.");
	if(result==true){
	d_modMember=document.getElementById("div_modMember");
	}else{
		return;
	}

}

</script> -->
</head>
<body>
<div id="div_detail">
<h1 style="text-align:center">회원 정보 상세</h1>
<table border="1" width="800px" align="center">
	<tr>
		<td bgcolor="yellow">아이디</td>
		<td>${detail.id }</td>
	</tr>
	<tr>
		<td bgcolor="yellow">이름</td>
		<td>${detail.name }</td>
	</tr>
	<tr>
		<td bgcolor="yellow">몸무게</td>
		<td>${detail.weight }</td>
	</tr>
	<tr>
		<td bgcolor="yellow">키</td>
		<td>${detail.height }</td>
	</tr>
	<tr>
		<td bgcolor="yellow">나이</td>
		<td>${detail.age }</td>
	</tr>
	<tr align="center">
		<td colspan="2">
		<input type="button" value="목록으로 돌아가기" onClick="location.href='${pageContext.request.contextPath}/member/listMember.do'"/>
		<input type="button" value="수정하기" onClick="fn_show()"/>
		<input type="button" value="삭제하기" onClick="fn_delete('${detail.id}')"/>
		</td>
	</tr>
</table>
</div>
<div id="div_modMember" style="display:none;">
<form method="post" action="${pageContext.request.contextPath}/member/modMember.do">
<h1 style="text-align:center">회원 정보 수정</h1>
<table border="1" width="800px" align="center">
	<tr>
		<td bgcolor="yellow">아이디</td>
		<td>
			<input type="text" value="${detail.id }" name="id" disabled/>
			<input type="hidden" value="${detail.id }" name="id"/>	
		</td>
	</tr>
	<tr>
		<td bgcolor="yellow">이름</td>
		<td><input type="text" value="${detail.name }" name="name"/></td>
	</tr>
	<tr>
		<td bgcolor="yellow">몸무게</td>
		<td><input type="text" value="${detail.weight }" name="weight"/></td>
	</tr>
	<tr>
		<td bgcolor="yellow">키</td>
		<td><input type="text" value="${detail.height }" name="height"/></td>
	</tr>
	<tr>
		<td bgcolor="yellow">나이</td>
		<td><input type="text" value="${detail.age }" name="age"/></td>
	</tr>
	<tr align="center">
		<td colspan="2">
		<input type="button" value="수정취소"/>
		<input type="submit" value="수정완료"/>
		
		</td>
	</tr>
</table>
</form>
</div>


</body>
</html>