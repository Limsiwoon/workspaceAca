<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css" >
<title>** Web02_MVC02 JoList **</title>
</head>
<body>
<h2> ** Web02_MVC02 JoList ** </h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>


<table border="1" style="width:100%">
<tr bgcolor="NavajoWhite">
	<th>Jno</th>
	<th>Jname</th>
	<th>Captain</th>
	<th>Project</th>
	<th>Slogan</th>
</tr>
<c:if test="${!empty requestScope.jinfo}">
	<c:forEach var="jinfo" items="${requestScope.jinfo}">
	<tr>
		<td><a href="joDetail?joC=${jinfo.jno}&">${jinfo.jno}</a></td>
		<td>${jinfo.jname}</td>
		<td>${jinfo.captain}</td>
		<td>${jinfo.project}</td>
		<td>${jinfo.slogan}</td>
	</tr>
	</c:forEach>
</c:if>

<c:if test="${empty requestScope.jinfo}">
	<tr><td colspan="9">!!j info에서 출력할 데이터 없습미다!!</td></tr>
</c:if>
</table>
<hr>
<a href="joJoinForm">조 등록하기</a> 
<hr>
<a href="/spring02/home">Home</a> 
<!-- 서블릿으로 가는 것이 아니기 때문에,  -->
<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>