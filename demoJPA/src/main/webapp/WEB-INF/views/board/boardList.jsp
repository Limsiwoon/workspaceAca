<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
<link rel="stylesheet" type="text/css" href="/resources/myLib/myStyle.css" >
</head>
<body>
	<h2>** Spring MVC02 Board List **</h2>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>

<div style="width:100%;">
<table>
<tr bgcolor="lavender">
	<th>Seq</th>
	<th>Title</th>
	<th>I D</th>
	<th>RegDate</th>
	<th>조회수</th>
</tr>
<c:if test="${!empty requestScope.banana}">
	<c:forEach var="b" items="${requestScope.banana}">
	<tr>
		<td>${b.seq}</td>
		<td>
		<!-- 답글 등록 후 Title 출력 전에 들여쓰기 추가  -->
		<c:if test="${b.indent>0}">
			<c:forEach begin="1" end="${b.indent}">
				<span>&nbsp;&nbsp;</span>
			</c:forEach>
			<span style="color:MediumSlateBlue;">re.. </span>
		</c:if>
		<!--  로그인 한 경우에만, 볼 수 있도록 함.  -->
			<c:if test="${!empty loginID}">
				<a href="boarddetail?jCode=D&seq=${b.seq}">${b.title}</a>
			</c:if>
			<c:if test="${empty loginID}">
				${b.title}
			</c:if>
		</td>
		<td>${b.id}</td>
		<td>${b.regdate}</td>
		<td>${b.cnt}</td>
	</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana}">
	<tr><td colspan="5">!!board 출력 데이터 없음!!</td></tr>
</c:if>
</table>
</div>
<c:if test="${!empty loginID}">
	<a href="boardInsert">등록하기</a>
</c:if> 
<hr>
<a href="/home">Home</a> 


</body>
</html>