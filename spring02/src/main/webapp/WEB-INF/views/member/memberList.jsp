<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web02_MVC02 MemberList **</title>
</head>
<body>
<h2> ** Web02_MVC02 MemberList ** </h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>


<table border="1" style="width:100%">
<tr bgcolor="lavender">
	<th>ID</th>
	<th>Password</th>
	<th>Name</th>
	<th>Age</th>
	<th>Jno</th>
	<th>Info</th>
	<th>Point</th>
	<th>Birthday</th>
	<th>추천인</th>
</tr>
<c:if test="${!empty requestScope.banana}">
	<c:forEach var="banana" items="${requestScope.banana}">
	<tr>
		<td>${banana.id}</td>
		<td>${banana.password}</td>
		<td>${banana.name}</td>
		<td>${banana.age}</td>
		<td>${banana.jno}</td>
		<td>${banana.info}</td>
		<td>${banana.point}</td>
		<td>${banana.birthday}</td>
		<td>${banana.rid}</td>
	</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana}">
	<tr><td colspan="9">!!출력할 데이터 없습미다!!</td></tr>
</c:if>
</table>
<hr>
<a href="home">Home</a> 
<!-- 서블릿으로 가는 것이 아니기 때문에,  -->
<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>