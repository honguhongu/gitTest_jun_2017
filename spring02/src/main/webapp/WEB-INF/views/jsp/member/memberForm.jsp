<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"    isELIgnored="false"
    %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원등록폼</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>


<script>
function fn_overlapped(){
	//alert("중복체크")
	var uId=document.getElementById("uId");
	var _id=uId.value;
	var hId=document.getElementById("hId");
	
	var _name=document.getElementById("name");
	var _weight=document.getElementById("weight");
	var _height=document.getElementById("height");
	var _age=document.getElementById("age");
	var _submit=document.getElementById("submit");
	var _reset=document.getElementById("reset");
	
	alert(_id);
	$.ajax({
	      type:"get",
	      async:false,
	      url:"${pageContext.request.contextPath }/member/overlapped.do",
	      data:{id:_id},
	      dataType:"text",
	      success:function (data,textStatus){
	         //alert(data);
	         if(data=="true"){
	        	 alert("이미 사용중인 아이디입니다.");
	         }else{
	        	 alert("사용 가능한 아이디입니다.");
	        	 hId.value=_id;
	        	 
	        	 uId.disabled=true;
	        	 _name.disabled=false;	        	 
	        	 _weight.disabled=false;	        	 
	        	 _height.disabled=false;	        	
	        	 _age.disabled=false;	        	
	        	 _submit.disabled=false;
	        	 _reset.disabled=false;
	         }
	         //$('#message').append(data);
	         
	      },
	      error:function(data,textStatus){
	         alert("에러가 발생했습니다.");
	      }
	   });
	   
	
}


</script>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath }/member/addMember.do">
	<table width="800px">
		<tr>
			<td>아이디</td>
			<td>
				<input type="hidden" name="id" id="hId" />
				<input type="text" name="uId" id="uId" />
				<input type="button" value="중복체크" onClick="fn_overlapped()"/>
			</td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" disabled id="name" /></td>
			
		</tr>
		<tr>
			<td>몸무게</td>
			<td><input type="text" name="weight" disabled id="weight"/></td>
		</tr>
		<tr>
			<td>키</td>
			<td><input type="text" name="height" disabled id="height"/></td>
		</tr>
		<tr>
			<td>나이</td>
			<td><input type="text" name="age" disabled id="age"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="전송" disabled id="submit"/>
				<input type="reset" value="초기화" disabled id="reset" onClick="location.href='memberForm.do '"/>
		</tr>
		
	</table>

</form>

</body>
</html>