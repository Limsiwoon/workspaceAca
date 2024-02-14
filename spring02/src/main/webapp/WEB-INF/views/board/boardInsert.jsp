<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ** Update Form ** </title>
</head>
<body>

<form action="boardinsert" method="get">
<div style="width:100%; text-align:center; display:flex; justify-content:center; ">
<table border="1" style="width:100%">

	<tr>	
		<th bgcolor="lavender">작성자</th>
		<td>${sessionScope.loginID}</td>
	</tr>
	<tr>	
		<th bgcolor="lavender"><label for="title">제목</label></th>
		<td><input type="text" name="title" id="title" size="20"></td>
	</tr>
	<tr>	
		<th bgcolor="lavender"><label for="title">Content</label></th>
		<td><input type="text" name="content" id="content" size="20"></td>
	</tr>
	<tr>
		<th></th>
		<td><input type="submit" name="insert" value="게시글 올리기"></td>
	</tr>
</table>
</div>
</form>
<table>
</table>
<br>
&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>