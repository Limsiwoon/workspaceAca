<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css" >
</head>


<body>
<h2> ** Hello Spring_MVC02! **</h2>
<P>Home_time : ${serverTime}.</P>
<hr>
<c:if test="${!empty sessionScope.loginName}">
	<h3>${sessionScope.loginName}님 안녕! Hello? <br> 로그인 해줘서 고마워. 약속대로 마스크 벗었어</h3><hr>
	<img alt="마스크벗음" src="/spring02/resources/images/basicman4.png" width=300px height=300px>
</c:if>
<c:if test="${empty sessionScope.loginID && empty requestScope.message}">
	<h3>안녕? Hello? Spring_MVC02에 잘 왔어! <br> 마스크를 벗기고싶다면 로그인 부탁해! <br></h3><hr>
	<img alt="마스크씀" src="/spring02/resources/images/basicman1.jpg" width=300px height=300px>
</c:if>
<hr>
<c:if test="${!empty requestScope.message}">
	<h3>${requestScope.message}<h3>
</c:if>

<!-- 로그인 전 -->
<c:if test="${empty sessionScope.loginID}">
	<!-- &nbsp;<a href="loginForm">LoginF</a> // ver01버전 -->
	&nbsp;<a href="member/loginForm">LoginF</a>
	&nbsp;<a href="member/joinForm">JoinF</a>
</c:if>
<!-- 로그인 후 -->
<c:if test="${!empty sessionScope.loginID}">
	&nbsp;<a href="member/logout">Log_out</a>
	&nbsp;<a href="member/detail?jCode=D">내정보 확인</a>
	&nbsp;<a href="member/detail?jCode=U">내정보 수정</a>
	&nbsp;<a href="member/delete">탈퇴</a>
</c:if>
<hr>
	&nbsp;<a href="member/memberList">MList</a>
	&nbsp;<a href="jo/joList">JoList</a>
</body>


</html>
