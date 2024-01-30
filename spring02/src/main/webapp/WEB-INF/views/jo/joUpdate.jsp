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
	<form action="joUpdate" method="get">
	<table>
	<tr height="40" >
		<td><label for="jno"> JNO</label></td>
		<td><input type="text" name="jno" id="jno" value="${requestScope.jinfo.jno}" readonly size="20"></td>
			<!-- readonly 속성은 <input> 요소의 입력 필드가 읽기 전용임 -->
	</tr>
	<tr height="40" >
		<td><label for="jname"> JName</label></td>
		<td><input type="text" name="jname" id="jname" value="${requestScope.jinfo.jname}"></td>
	</tr>
	<tr height="40">
		<td><label for="captain"> Captain</label></td>
		<td><input type="text" name="captain" id="captain" value="${requestScope.jinfo.captain}"></td>
	</tr>
	<tr height="40">
		<td><label for="project"> Project</label></td>
				<!-- scope라고 지정해 주는 것은, direct로 찾을 수 있게 해주기 위해서임 -->
		<td><input type="text" name="project" id="project" value="${requestScope.jinfo.project}"></td>
	</tr>
	<tr height="40">
		<td><label for="slogan"> Slogan</label></td>
		<td><input type="text" name="slogan" id="slogan" value="${requestScope.jinfo.slogan}"></td>
	</tr>
		
	<tr height="40">
		<td><input type="submit" name="change" value="수정"></td>
		<td><input type="reset" name="cancle" value="취소"></td>
	</tr>
	
	</table>
	</form>
	
&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>