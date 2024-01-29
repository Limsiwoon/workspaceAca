<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> board Detail </title>

</head>
<body>

<div style="width:100%; text-align:center; display:flex; justify-content:center; ">
<table border="1" style="width:100%">
<%-- <c:if test="${requestScope.binfo}"> --%>
	<tr>
		<th bgcolor="lavender">Seq</th><td>${binfo.seq}</td></tr>
	<tr>	
		<th bgcolor="lavender">I D</th><td>${binfo.id}</td>
	</tr>
	<tr>	
		<th bgcolor="lavender">Title</th><td>${binfo.title}</td>
	</tr>
	<tr>	
		<th bgcolor="lavender">Content</th><td>${binfo.content}</td>
	</tr>
	<tr>	
		<th bgcolor="lavender">RegDate</th><td>${binfo.regdate}</td>
	</tr>
	<tr>	
		<th bgcolor="lavender">조회수</th><td>${binfo.cnt}</td>
	</tr>
<%-- </c:if> --%>
</table>
</div>
<c:if test="${binfo.id==sessionScope.loginID}">
<a href="boarddetail?jCode=U&seq=${b.seq}">수정하기</a>&nbsp;
</c:if>
<hr>
<a href="/spring02/home">Home</a> 

</body>
</html>