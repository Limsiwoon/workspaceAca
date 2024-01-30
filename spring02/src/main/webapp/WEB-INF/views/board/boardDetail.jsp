<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/writeBoard.css" >
<title> board Detail </title>
</head>
<body>
<h2> ** board Detail ** </h2>
<div style="width:100%;">
<table>
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
<c:if test="${!empty sessionScope.loginID}">
<a href="replyInsert?root=${binfo.root}&step=${binfo.step}&indent=${binfo.indent}">댓글달기</a>&nbsp;
<!-- 댓글등록을 위해 부모글의 root, step, indent 값이 필요하기 때문에
    서버로 보내주어야함 (퀴리스트링으로 작성)    -->
</c:if>
<c:if test="${binfo.id==sessionScope.loginID}">
<a href="boarddetail?jCode=U&seq=${binfo.seq}">수정하기</a>&nbsp;
<a href="boarddetail?jCode=X&seq=${binfo.seq}">삭제하기</a>&nbsp;
</c:if>
<hr>
<a href="/spring02/home">Home</a> 

</body>
</html>