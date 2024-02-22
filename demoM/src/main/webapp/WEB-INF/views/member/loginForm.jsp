<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ** Login Form** </title>
<link rel="stylesheet" type="text/css"
	href="/resources/myLib/myStyle.css">
<script src="/resources/myLib/inCheck.js"></script>
<script type="text/javascript">
"use strict"

let iCheck=false;
let pCheck=false;

onload=function(){
	
	document.getElementById('id').focus();

	 document.getElementById('id').addEventListener('keydown',
	       (e)=>{
	          if (e.which==13) {
	             e.preventDefault();
	             // => form 에서는
	             // => enter 누르면 자동 submit 발생되므로 이를 제거함
	             document.getElementById('password').focus();
	          }//if
	       });
	 // -> 무결성 점검 
	 
	  document.getElementById('id').addEventListener('focusout', ()=>{ iCheck=idCheck(); });
	 // => Password
	  document.getElementById('password').addEventListener('keydown',
	       (e)=>{
	          if (e.which==13) {
	             e.preventDefault();
	             document.getElementById('subTag').focus(); 
	             
	               // => password 에서 입력후 Enter_Key 누르면 바로 submit 진행 되도록~~
	               //     type="submit" 을 사용하는경우 정확하게 적용하기 어려워 적용하지 않음    
	               //if (!iCheck) iCheck=idCheck();
	               //else if (!pCheck) pCheck=pwCheck();
	               //else document.getElementById('myForm').submit();
	          }//if
	       });
	 // -> 무결성 점검 
	  document.getElementById('password').addEventListener('focusout', ()=>{ pCheck=pwCheck(); });
	
	
}//onload

function inCheck(){
	if(!iCheck){ document.getElementById('iMessage').innerHTML='필수 입력 : id를 확인하세요!' ;}
	if(!pCheck){ document.getElementById('pMessage').innerHTML='필수 입력 : password를 확인하세요!' ;}
	if(iCheck && pCheck ){
		//모두 true 값인 경우 -> submit 진행
		return true;
	}else {
		return false;
	}//check_조건
	
}


</script>
</head>
<body>
<h2>** Web02 MVC02 LoginForm **</h2>
<form action="login" method="post" id="myForm">
	<table>
		<tr height="40">
			<td bgcolor="AntiqueWhite"><label for="id">I D</label></td>
			<td><input type="text" name="id"  id="id" size="10">
				<br><span id="iMessage" class="eMessage"></span>
			</td>
		</tr>
		<tr height="40">
			<td bgcolor="MistyRose"><label for="password">Password</label></td>
			<td><input type="password" name="password"  id="password" size="20">
			<br><span id="pMessage" class="eMessage"></span>
			</td>
		</tr>
		<tr><td></td>
			<td><input type="submit" id="subTag" value="로그인" onclick="return inCheck()">&nbsp;&nbsp;
			<input type="reset" value="취소"></td>
		</tr>
	</table>
</form>
<hr>
<c:if test="${!empty requestScope.message}">
${requestScope.message}
</c:if>
&nbsp;<a href="/home">HOME</a>&nbsp;

</body>
</html>