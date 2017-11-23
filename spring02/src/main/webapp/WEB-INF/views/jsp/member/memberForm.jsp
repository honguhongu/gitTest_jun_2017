<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"    isELIgnored="false"
    %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ�������</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>


<script>
function fn_overlapped(){
	//alert("�ߺ�üũ")
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
	        	 alert("�̹� ������� ���̵��Դϴ�.");
	         }else{
	        	 alert("��� ������ ���̵��Դϴ�.");
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
	         alert("������ �߻��߽��ϴ�.");
	      }
	   });
	   
	
}


</script>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath }/member/addMember.do">
	<table width="800px">
		<tr>
			<td>���̵�</td>
			<td>
				<input type="hidden" name="id" id="hId" />
				<input type="text" name="uId" id="uId" />
				<input type="button" value="�ߺ�üũ" onClick="fn_overlapped()"/>
			</td>
		</tr>
		<tr>
			<td>�̸�</td>
			<td><input type="text" name="name" disabled id="name" /></td>
			
		</tr>
		<tr>
			<td>������</td>
			<td><input type="text" name="weight" disabled id="weight"/></td>
		</tr>
		<tr>
			<td>Ű</td>
			<td><input type="text" name="height" disabled id="height"/></td>
		</tr>
		<tr>
			<td>����</td>
			<td><input type="text" name="age" disabled id="age"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="����" disabled id="submit"/>
				<input type="reset" value="�ʱ�ȭ" disabled id="reset" onClick="location.href='memberForm.do '"/>
		</tr>
		
	</table>

</form>

</body>
</html>