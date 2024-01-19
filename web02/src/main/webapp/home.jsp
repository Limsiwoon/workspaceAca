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
	&nbsp;	
	<c:if test="${!empty sessionScope.loginName}">
		<a href="/web02/detail">MyInfo</a>&nbsp;
		<a href="/web02/logout">Logout</a> <br>
	</c:if>
	<c:if test="${empty sessionScope.loginName}">
	&nbsp;<a href="/web02/member/loginForm.jsp">Login</a> 
	&nbsp;<a href="/web02/member/joinForm.jsp">Login</a>
	</c:if>
	&nbsp;<a href="/web02/mlist">Mlist</a>
</body>
</html>