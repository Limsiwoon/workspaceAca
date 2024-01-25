<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ** Login Form** </title>
</head>
<body>
<h2>** Web02 MVC02 LoginForm **</h2>
<form action="login" method="post">
	<table>
		<tr height="40">
			<td bgcolor="AntiqueWhite"><label for="id">I D</label></td>
			<td><input type="text" name="id"  id="id" size="10"></td>
		</tr>
		<tr height="40">
			<td bgcolor="MistyRose"><label for="password">Password</label></td>
			<td><input type="text" name="password"  id="password" size="20"></td>
		</tr>
		<tr><td></td>
			<td><input type="submit" value="로그인">&nbsp;&nbsp;
			<input type="submit" value="취소"></td>
		</tr>
	</table>
</form>
<hr>
<c:if test="${!empty requestScope.message}">
${requestScope.message}
</c:if>
&nbsp;<a href="home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>