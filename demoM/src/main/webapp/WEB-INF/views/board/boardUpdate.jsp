<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/resources/myLib/writeBoard.css" >
<title> ** Update Form ** </title>
</head>
<body>

<form action="boardupdate" method="get">
<div style="width:100%; text-align:center; display:flex; justify-content:center; ">
<table border="1" style="width:100%">

	<tr>
		<th bgcolor="lavender">Seq</th>
		<td><input type="text" name ="seq" id="seq" value="${binfo.seq}" readonly></td></tr>
	<tr>	
		<th bgcolor="lavender">I D</th>
		<td><input type="text" name="id" id="id" value="${binfo.id}" readonly></td>
	</tr>
	<tr>	
		<th bgcolor="lavender">Title</th>
		<td><input type="text" name="title" id="title" value="${binfo.title}"></td>
	</tr>
	<tr>	
		<th bgcolor="lavender">Content</th>
		<td><input type="text" name="content" id="content" value="${binfo.content}"></td>
	</tr>
	<tr>	
		<th bgcolor="lavender">RegDate</th>
		<td><input type="text" name="regdate" id="regdate" value="${binfo.regdate}" readonly></td>
	</tr>
	<tr>	
		<th bgcolor="lavender">조회수</th>
		<td><input type="text" name="cnt" id="cnt" value="${binfo.cnt}" readonly></td>
	</tr>
	<tr>
		<th></th>
		<td><input type="submit" name="change" value="수정"></td>
	</tr>
</table>
</div>
</form>
<table>
</table>
<br>
&nbsp;<a href="/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>