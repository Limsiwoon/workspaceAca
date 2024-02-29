<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="/resources/myLib/myStyle.css" >
</head>

<body>
<h2> ** Hello Spring_BOOT JPA! **</h2>
<P>Home_time : ${serverTime}.</P>
<hr>
<c:if test="${!empty sessionScope.loginName}">
	<h3>${sessionScope.loginName}님 안녕! Hello? <br></h3><hr>
	<img alt="마스크벗음" src="/resources/images/gunbaeting.jpg" width=500px height=300px>
</c:if>
<c:if test="${empty sessionScope.loginID && empty requestScope.message}">
	<h3>안녕? Hello? Spring_BOOT JPA에 잘 왔어! <br><br></h3><hr>
	<img alt="마스크씀" src="/resources/images/gunbae.jpeg" width=600px height=300px>
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
	&nbsp;<a href="member/mPageList">Mpage</a>
	<br>
	&nbsp;<a href="jo/joList">JoList</a>
	&nbsp;<a href="board/boardList">Board List</a>
	&nbsp;<a href="bcrypt">BCrypt</a>
	&nbsp;<a href="board/bPageList">Bpage</a>
	&nbsp;<a href="/axtestform">AjaxTest</a>
	<br>
	&nbsp;<a href="/ginsert">G_insert</a>
	&nbsp;<a href="/glist">G_List</a>
	&nbsp;<a href="/gupdate">G_update</a>
	&nbsp;<a href="/gpage">G_Page</a>
	<br>
	&nbsp;<a href="member/mjoinList">mJoinList</a>&nbsp;
	
	<!-- &nbsp;<a href="etest">Exception</a>
	&nbsp;<a href="member/log4jTest">@log4jTest</a> -->
	<br>
	
	<!-- &nbsp;<a href="greensn">GreenSN</a>&nbsp;
	&nbsp;<a href="greenall">GreenALL</a>&nbsp;
	&nbsp;<a href="jeju">JeJu</a>&nbsp;
	&nbsp;<a href="gps">GPS</a>&nbsp; -->
</body>


</html>
