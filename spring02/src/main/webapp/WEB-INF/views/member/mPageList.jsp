<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web02_MVC02 MemberList **</title>

<script>
"use strict"
//1. 검색조건 입력후 버튼클릭
//	=> 입력값들을 서버로 전송 요청처리:  location

function searchDB(){
	self.location='mPageList'
					+ '?currPage=1&rowsPerPage=5'
					+'&searchType='+document.getElementById('searchType').value
					+'&keyword='+document.getElementById('keyword').value; 
      console.log(document.getElementById('keyword').value); 
}

		
//2. searchType 을 '전체' 로 변경하면 keyword는 clear 
function keywordClear(){
	if(document.getElementById('searchType').value=='all'){
		document.getElementById('keyword').value==''; //전체 선택했을 경우, 검색어를 삭제. 
	}
}

// Board Check_List
// 체크한 값들을 다시 풀기 위해 사용하는 메서드 

function checkClear(){
	
	let ck = document.querySelectorAll('.clear');  /* //리스트의 모양을 ck안에 담음 */
	for(let i=0; i <ck.length; i++){
		ck[i].checked=false;
	}
	return false; //reset 의 기본 이벤트를 제거하기 위함. 
}

</script>

</head>
<body>
<h2> ** Web02_MVC02 MemberList ** </h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>

<!-- 검색기능 만들기 -->
<br>
<div id="searchBar">
   <select name="searchType" id="searchType" onchange="keywordClear()">
      <option value="all" ${ pageMaker.cri.searchType =='all' ? 'selected' : '' } > 전체 </option>
      <option value="id" ${ pageMaker.cri.searchType =='id' ? 'selected' : '' } > ID </option>
      <option value="name" ${ pageMaker.cri.searchType =='name' ? 'selected' : '' } > NAME </option>
      <option value="age" ${ pageMaker.cri.searchType =='age' ? 'selected' : '' } > NAME </option>
      <option value="birthday" ${ pageMaker.cri.searchType =='birthday' ? 'selected' : '' } > 생일 </option>
      <option value="info" ${ pageMaker.cri.searchType =='info' ? 'selected' : '' } > 소개 </option>
      <option value="rid" ${ pageMaker.cri.searchType =='rid' ? 'selected' : '' } > 추천인 </option>
   </select>
   <input type="text" name="keyword" id="keyword" value="${ pageMaker.cri.keyword }">   
   <button id="searchBtn" onclick="searchDB()">검색</button>
   <br>
   <br>
   
   <!-- checkBox Test -->
   <form action="bCheckList" method="get">
      <b>ID : </b>
      <!-- check의 선택한 값 유지를 위한 코드  -->
      <c:set var="ch1" value="false"/>
      <c:set var="ch2" value="false"/>
      <c:set var="ch3" value="false"/>
      <c:set var="ch4" value="false"/>
      <c:set var="ch5" value="false"/>      
      <c:forEach  var="id" items="${pageMaker.cri.check}" >
         <c:if test="${jno=='1'}"> <c:set var="ck1" value="true" /> </c:if>
         <c:if test="${jno=='2'}"> <c:set var="ck2" value="true" /> </c:if>
         <c:if test="${jno=='3'}"> <c:set var="ck3" value="true" /> </c:if>
         <c:if test="${jno=='4'}"> <c:set var="ck4" value="true" /> </c:if>
         <c:if test="${jno=='7'}"> <c:set var="ck5" value="true" /> </c:if>
      </c:forEach>
      
      <input type="checkbox" name="check" class="clear" value="1"${ck1 ?'checked':''}>최문석&nbsp;
      <input type="checkbox" name="check" class="clear" value="2"${ck2 ?'checked':''}>김수빈&nbsp;
      <input type="checkbox" name="check" class="clear" value="3"${ck3 ?'checked':''}>최승삼&nbsp;
      <input type="checkbox" name="check" class="clear" value="4"${ck4 ?'checked':''}>김수옥&nbsp;
      <input type="checkbox" name="check" class="clear" value="7"${ck5 ?'checked':''}>관리자&nbsp;
      <input type="submit" value="Search">&nbsp;
      <input type="reset" value="Clear" onclick="return checkClear()"><br><br>
      
   </form>
</div>
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
</tr>
<c:if test="${!empty requestScope.banana}">
	<c:forEach var="banana" items="${requestScope.banana}">
	<tr>
		<td>${banana.id}</td>
		<td>${banana.name}</td>
		<td>${banana.age}</td>
		<td>${banana.jno}</td>
		<td>${banana.info}</td>
		<td>${banana.point}</td>
		<td>${banana.birthday}</td>
		<td>${banana.rid}</td>
		<td><img alt="yourImg" width="50" height="70" 
			src="/spring02/resources/uploadImages/${banana.uploadfile}"></td>
	</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana}">
	<tr><td colspan="9">!!출력할 데이터 없습미다!!</td></tr>
</c:if>
</table>

<div align="center">

      <c:choose>
     	<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
     	<!-- 버전1 : makeQuery사용
     		<a href="bPageList${pageMaker.makeQuery(1)}">FP</a> &nbsp;
     		<a href="bPageList${pageMaker.makeQuery(pageMaker.spageNo-1)}">&LT;</a>&nbsp; -->
     	<!-- 버전2 : searchQuery 사용 -->	
     		<a href="${pageMaker.searchQuery(1)}">FP</a> &nbsp;
     		<a href="${pageMaker.searchQuery(pageMaker.spageNo-1)}">&LT;</a>&nbsp;
     	</c:when>
     	<c:otherwise>
     		<font color="LightGray">FP&nbsp;&nbsp;&nbsp;&LT;</font>
     	</c:otherwise>
     </c:choose>
     
     
<!-- 2) Display PageNo 
	=> currPage 제외한 PageNo 만 a Tag 적용 -->
	<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
		<c:if test="${i==pageMaker.cri.currPage}">
			<font color="Orange" size="5"><b>${i}</b></font>
		</c:if>
		<c:if test="${i!=pageMaker.cri.currPage}">
			<a href="${pageMaker.searchQuery(i)}">${i}</a>
		</c:if>
	</c:forEach>

<!-- 3) Next, LastPage 
      => ver01: makeQuery() 메서드 사용
      => ver02: searchQuery() 메서드 사용 -->
	<c:choose>
		<c:when test="${pageMaker.next && pageMaker.epageNo > 0}">
			&nbsp;<a href="${pageMaker.makeQuery(pageMaker.epageNo+1)}">&GT;</a>
			&nbsp;<a href="${pageMaker.makeQuery(pageMaker.lastPageNo)}">LP</a>
		</c:when>
		<c:otherwise>
     		<font color="LightGray">&GT;&nbsp;&nbsp;&nbsp;LP</font>
     	</c:otherwise>
	</c:choose>
</div>



<hr>
<a href="/spring02/home">Home</a> 
<!-- 서블릿으로 가는 것이 아니기 때문에,  -->
<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>