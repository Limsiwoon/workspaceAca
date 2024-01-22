<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Home **</title>
</head>
<body>
	<h2>**Web0_MVC02 Project **</h2>

	<c:if test="${!empty sessionScope.loginName}">
		<h3> ${sessionScope.loginName} 님 안녕하세요! </h3>
	</c:if>
	<c:if test="${empty sessionScope.loginName}">
		<h3> 로그인 후 이용해주세요! 감사합미다 </h3>
	</c:if>
	
	<img alt="life is flower" src="images/pink01.png" width=200px height=200px>
	<hr>

	<c:if test="${!empty sessionScope.loginName}">
		<a href="/web02/mdetail">MyInfo</a>&nbsp;
		<a href="/web02/mdetail?jCode=U">내정보 수정</a>&nbsp;
		<a href="/web02/logout">Logout</a>&nbsp;
		<a href="/web02/mdelete">탈퇴</a>&nbsp;
	</c:if>
	<c:if test="${empty sessionScope.loginName}">
		<a href="/web02/member/loginForm.jsp">Login</a>&nbsp; 
		<a href="/web02/member/joinForm.jsp">Join</a>&nbsp; 
	</c:if>
	<br><a href="/web02/mlist">Mlist</a>
	<br><a href="/web02/mlist.fo">MlistFo</a>
</body>
</html>