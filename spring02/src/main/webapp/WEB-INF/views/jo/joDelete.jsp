<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css" >
<title>** Web02_MVC02 JoDelete **</title>
</head>
<body>
<h2> ** Web02_MVC02 JoDelete ** </h2>
<hr>
<h3>정말로 조를 삭제하시겠습니까? <br> 되돌릴 수 없습니다. <br>이 문구가 세상에서 제일무섭지? 나도,알아.ㅋ</h3>
<h4>취소하고 싶다면, "이전으로 돌아가기" 버튼을 눌러주세요!</h4>
<hr>
<a href="jdel?joC=${jinfo.jno}">진짜로 삭제하기</a> &nbsp;<br>
<a href="/spring02/home">Home</a>&nbsp;
<!-- 서블릿으로 가는 것이 아니기 때문에,  -->
<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>