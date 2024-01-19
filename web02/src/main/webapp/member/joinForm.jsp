<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ** Join Form ** </title>
</head>
<body>
	<form action="/web02/mjoin" method="get">
	<table>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="id">I D</label></td>
			<td><input type="text" name="id" id="id" size="20" placeholder="영문4글자 이상"></td>
		</tr>
		<br><tr height="40" >
			<td bgcolor="lavender"><label for="password">Password</label></td>
			<td><input type="text" name="password" id="password" size="20" placeholder="영문,숫자,특수문자이용"></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="name">Name</label></td>
			<td><input type="text" name="name" id="name" size="20"></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="age">Age</label></td>
			<td><input type="text" name="age" id="age" size="20"></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="jno">Jno</label></td>
			<td><select name="jno" id="jno">
				<option value="1">1조 : business</option>
				<option value="2">2조 : static</option>
				<option value="3">3조 : 칭찬해조</option>
				<option value="4">4조 : 카톡으로얘기하조</option>
				<option value="7">7조 : 칠면조(관리팀)</option>
			</select></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="info">Info</label></td>
			<td><input type="text" name="info" id="info" placeholder="자기소개와 희망사항"></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="point">Point</label></td>
			<td><input type="text" name="point" id="point" size="20"></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="birthday">Birthday</label></td>
			<td><input type="date" name="birthday" id="birthday"></td>
		</tr>
		<br><tr height="40">
			<td bgcolor="lavender"><label for="rid">추천인</label></td>
			<td><input type="text" name="rid" id="rid" size="20"></td>
		</tr>
		<br><tr height="40">
			<td><input type="submit" name="join" value="가입"></td>
			<td><input type="reset" name="cancle" value="취소"></td>
		</tr>
	</table>
	</form>
	

</body>
</html>