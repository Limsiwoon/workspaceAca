<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring BOOT AXIOS MEMBER LIST **</title>
</head>
<body>
<h2> **Spring BOOT AXIOS MEMBER LIST** </h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>


<table border="1" style="width:100%">
<tr bgcolor="lavender">
	<th>ID</th>
	<th>Name</th>
	<th>Age</th>
	<th>Jno</th>
	<th>Info</th>
	<th>Point</th>
	<th>Birthday</th>
	<th>추천인</th>
	<th>프로필 사진</th>
	<th>DELETE</th>
</tr>
<c:if test="${!empty requestScope.banana}">
	<c:forEach var="banana" items="${requestScope.banana}">
	<tr><!-- **idbList : id 별 boardList
				=> 선택된 id를 function 에 전달(매개변수를 활용)
				  	idbList('banana')
				  	EL 태그로 인자를 넣을 수 있으나, 표현방식에 주의해주어야 함. 		
				  	"idbList('${banana.id}')" 작성하면, 
				  	외부에 전달 가능함 
		 -->
		<td>
		<!-- 
		< # 책깔피 기능 >
		- a 태그를 활용하게 되면, 스크롤을 자동으로 이동할 수 있는 기능을 넣을 수 있음
   		- a태그에 이벤트 적용 시 책갈피 기능 활용 가능(scroll 이동 가능 )
    		- href : 적용하지 않음 ⇒ 이동하지 않음
    		- href =”#id” id 위치로 이동, “#” 만 작성하면, 최상단으로 이동
    		- href = “javascript: ;” ⇒ 이동하지 않음 // 속성을 사용하지 않음과 같음
    		- **코드 아래 참고 ) -->
			<%-- <a href="#resultArea2" onclick ="idbList('${banana.id}')">${banana.id}</a> --%>
			<span class="textlink" onclick ="idbList('${banana.id}')">${banana.id}</span> 
			
			   <!--  ** Delete 기능 추가 
            => 선택된 id를 function 에 전달 (매개변수를 활용)
            => 결과는 성공/실패 여부만 전달: RESTController 로 
            => 성공 : Deleted 로 변경, onclick 이벤트 해제 
                     이를 위해 Delete Tag 를 function 에서 인식할수있어야함. 
                     
            ** function 에 이벤트객체 전달
            => 이벤트핸들러의 첫번째 매개변수에 event 라는 이름으로 전달함.
             => a Tag 와 span 사용시 e.target 값 비교
                -> a Tag : "javascript:;" 
                -> span  : [object HTMLSpanElement]          
         -->
		</td>
		
		<td>${banana.name}</td>
		<td>${banana.age}</td>
		<td>
			<span class="textlink" onmouseover="showJoDetail(event,${banana.jno})" 
									onmouseout="hideJoDetail()">${banana.jno}</span>
		</td>
		<td>${banana.info}</td>
		<td>${banana.point}</td>
		<td>${banana.birthday}</td>
		<td>${banana.rid}</td>
		<td><img alt="yourImg" width="50" height="70" 
			src="/resources/uploadImages/${banana.uploadfile}"></td>
		<!-- **DELTE 기능
			=> 선택된 id를 function 에 전달(매개변수를 활용)
			=> 결과 성공 / 실패 여부만 확인 할 수 있으면 됨
			=> 성공 : DELETED 로 변경 . onClick = 이벤트 해제
		 -->
		<td><span class="textlink" onclick="axiDelete(event,'${banana.id}')" 
								id="${banana.id}" >delete</span></td>
	</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana}">
	<tr><td colspan="10">!!출력할 데이터 없습미다!!</td></tr>
</c:if>
</table>

<div id="content"></div>


<hr>
<a href="/home">Home</a> 
<!-- 서블릿으로 가는 것이 아니기 때문에,  -->
<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>