<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ** JO Detail ** </title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css" >
</head>
<body>

<table border="1" style="width:100%">
	<tr bgcolor="lavender">
		<th>Jno</th>
		<th>Jname</th>
		<th>Captain</th>
		<th>Name</th>
		<th>Project</th>
		<th>Slogan</th>

	</tr>
<c:if test="${!empty requestScope.jinfo}">
	<tr>
		<th>${requestScope.jinfo.jno}</th>
		<th>${requestScope.jinfo.jname}</th>
		<th>${requestScope.jinfo.captain}</th>
		<td>${requestScope.jinfo.name}</td>
		<th>${requestScope.jinfo.project}</th>
		<th>${requestScope.jinfo.slogan}</th>
	</tr>
</c:if> 
<c:if test="${empty requestScope.jinfo}">
	<tr colspan="9"><td>"출력할 데이터가 없습미다."</td></tr>
</c:if>

</table>
<hr>
<table border="1" style="width:100%">
<tr bgcolor="NavajoWhite">
	<th>ID</th>
	<!-- <th>Password</th> -->
	<th>Name</th>
	<th>Age</th>
	<th>Jno</th>
	<th>Info</th>
	<th>Point</th>
	<th>Birthday</th>
	<th>추천인</th>
</tr>
<c:if test="${!empty requestScope.banana2}">
<c:forEach var="banana2" items="${requestScope.banana2}">
	<tr>
		<td>${banana2.id}</td>
		<%-- <td>${banana2.password}</td> --%>
		<td>${banana2.name}</td>
		<td>${banana2.age}</td>
		<td>${banana2.jno}</td>
		<td>${banana2.info}</td>
		<td>${banana2.point}</td>
		<td>${banana2.birthday}</td>
		<td>${banana2.rid}</td>
	</tr>
</c:forEach>
</c:if>

<c:if test="${empty requestScope.banana2}">
	<tr><td colspan="9">!!출력할 데이터 없습미다!!</td></tr>
</c:if>
</table>
<hr>


&nbsp;<a href="joDetail?joC=100+${jinfo.jno}">조 수정하기</a>&nbsp;
&nbsp;<a href="joDelete?joC=${jinfo.jno}">조 삭제하기</a>&nbsp;
<hr>
&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>