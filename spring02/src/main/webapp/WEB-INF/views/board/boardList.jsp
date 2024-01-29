<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
</head>
<body>
	<h2>** Spring MVC02 Board List **</h2>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>

<div style="width:100%; text-align:center; display:flex; justify-content:center; ">
<table border="1" style="width:100%">
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

<hr>
<a href="home">Home</a> 


</body>
</html>