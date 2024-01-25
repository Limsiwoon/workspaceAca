<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ** member Detail ** </title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css" >
</head>
<body>

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
<c:if test="${!empty requestScope.info}">
	<tr>
		<th>${requestScope.info.id}</th>
		<th>${requestScope.info.password}</th>
		<th>${requestScope.info.name}</th>
		<th>${requestScope.info.age}</th>
		<th>${requestScope.info.jno}</th>
		<th>${requestScope.info.info}</th>
		<th>${requestScope.info.point}</th>
		<th>${requestScope.info.birthday}</th>
		<th>${requestScope.info.rid}</th>
	</tr>
</c:if> 
<c:if test="${empty requestScope.info}">
	<tr colspan="1"><td>"출력할 데이터가 없습미다."</td></tr>
</c:if>

</table>
&nbsp;<a href="home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>