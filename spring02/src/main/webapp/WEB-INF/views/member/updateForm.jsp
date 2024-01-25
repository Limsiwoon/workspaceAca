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
	<form action="update" method="get">
	<table>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="id">I D</label></td>
			<td><input type="text" name="id" id="id" value="${requestScope.info.id}" readonly size="20"></td>
			<!-- readonly 속성은 <input> 요소의 입력 필드가 읽기 전용임 -->
		</tr>
		<br><tr height="40" >
			<td bgcolor="lavender"><label for="password">Password</label></td>
			<td><input type="text" name="password" id="password" value="${requestScope.info.password}"></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="name">Name</label></td>
			<td><input type="text" name="name" id="name" value="${requestScope.info.name}"></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="age">Age</label></td>
				<!-- scope라고 지정해 주는 것은, direct로 찾을 수 있게 해주기 위해서임 -->
			<td><input type="text" name="age" id="age" value="${requestScope.info.age}"></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="jno">Jno</label></td>
			<td><select name="jno" id="jno">
				<option value="1" ${requestScope.info.jno==1 ? "selected":""}>1조 : business</option>
				<option value="2" ${requestScope.info.jno==2 ? "selected":""}>2조 : static</option>
				<option value="3" ${requestScope.info.jno==3 ? "selected":""}>3조 : 칭찬해조</option>
				<option value="4" ${requestScope.info.jno==4 ? "selected":""}>4조 : 카톡으로얘기하조</option>
				<option value="7" ${requestScope.info.jno==7 ? "selected":""}>7조 : 칠면조(관리팀)</option>
			</select></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="info">Info</label></td>
			<td><input type="text" name="info" id="info" value="${requestScope.info.info}"></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="point">Point</label></td>
			<td><input type="text" name="point" id="point" value="${requestScope.info.point}"></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="birthday">Birthday</label></td>
			<td><input type="date" name="birthday" id="birthday" value="${requestScope.info.birthday}"></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="rid">추천인</label></td>
			<td><input type="text" name="rid" id="rid" size="20" value="${requestScope.info.rid}"></td>
		</tr>
		<br><tr height="40">
			<td><input type="submit" name="change" value="수정"></td>
			<td><input type="reset" name="cancle" value="취소"></td>
		</tr>
	</table>
	</form>
	
&nbsp;<a href="/web02/home.jsp">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>