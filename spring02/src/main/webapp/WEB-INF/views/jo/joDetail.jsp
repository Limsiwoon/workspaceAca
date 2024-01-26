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
		<th>Project</th>
		<th>Slogan</th>

	</tr>
<c:if test="${!empty requestScope.jinfo}">
	<tr>
		<th>${requestScope.jinfo.jno}</th>
		<th>${requestScope.jinfo.jname}</th>
		<th>${requestScope.jinfo.captain}</th>
		<th>${requestScope.jinfo.project}</th>
		<th>${requestScope.jinfo.slogan}</th>
	</tr>
</c:if> 
<c:if test="${empty requestScope.jinfo}">
	<tr colspan="9"><td>"출력할 데이터가 없습미다."</td></tr>
</c:if>

</table>
&nbsp;<a href="joDetail?joC=1${jinfo.jno}">조 수정하기</a>&nbsp;
&nbsp;<a href="joDetail?joC=2${jinfo.jno}">조 삭제하기</a>&nbsp;
<hr>
&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>