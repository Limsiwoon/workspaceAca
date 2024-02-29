<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ** Spring MVC02 Password Update **</title>
<link rel="stylesheet" type="text/css"
	href="/resources/myLib/myStyle.css">
<script src="/resources/myLib/inCheck.js"></script>
<script type="text/javascript">
"use Strict"

let pCheck=false;
let p2Check=false;


onload=function() {
	document.getElementById('password').focus();
	
	 document.getElementById('password').addEventListener('keydown',
		       (e)=>{
		          if (e.which==13) {
		             e.preventDefault();
		             document.getElementById('password2').focus();
		          }//if
		       });
		 // -> 무결성 점검 
		  document.getElementById('password').addEventListener('focusout', ()=>{ pCheck=pwCheck(); });
		 
		 // => Password2
		  document.getElementById('password2').addEventListener('keydown',
		       (e)=>{
		          if (e.which==13) {
		             e.preventDefault();
		             document.getElementById('submitTag').focus();
		          }//if
		       });
		 // -> 무결성 점검 
		  document.getElementById('password2').addEventListener('focusout', ()=>{ p2Check=pw2Check(); });
}


function inCheck(){
	
	if(!pCheck){ document.getElementById('pMessage').innerHTML='필수 입력 : password를 확인하세요!' ;}
	if(!p2Check){ document.getElementById('p2Message').innerHTML='필수 입력 : password2 와 password가 같은지 확인하세요!' ;}

	if(pCheck && p2Check){
		//모두 true 값인 경우 -> submit 진행
		if( confirm("정말 수정할거야? (Yes: 웅 할거야 /No : 아니 안해 )") ){
			// submit 진행
			return true;
		} else {
			alert("수정취소 됨!");
			return false;
		}//confirm
		
	} else {
		//submit 진행 X -> return false
		return false;
	}//check_조건
	
}	
</script>
</head>
<body>
<h2 style="color:Blue;"> ** Spring MVC02 Password Update ** </h2>
<div align="center" bgcolor="PapayaWhip" >
	<br><b>새로운 Password를 입력하세요</b><br><br>
	<form action="pwUpdate" method="post">
		<tr height='80'>
			<td bgcolor=""><label>Password</label></td>
			<td><input bgcolor="white" type="password" id="password" name="password"><br>
				<span id="pMessage" class="eMessage"></span>
			</td>
		</tr>
		<tr height='40'>
			<td bgcolor=""><label>재 확 인</label></td>
			<td><input bgcolor="white" type="password" id="password2" placeholder="반드시 입력하세요"><br>
				<span id="p2Message" class="eMessage"></span>
			</td>
		</tr>
		<tr height='40'>
			<td></td>
			<td><input type="submit" value="수정" id="submitTag" onClick="return inCheck()">&nbsp;&nbsp;
				<input type = "reset" value="취소">
			</td>
		</tr>
	</form>

</div>
<hr> 
<div align="center">
    <c:if test="${!empty requestScope.message}">
    ${requestScope.message}<br>
    </c:if>
</div>
&nbsp;<a href="/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>