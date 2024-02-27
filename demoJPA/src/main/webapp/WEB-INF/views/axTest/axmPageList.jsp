<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** SPRING BOOT AXIOS MemberPAGE LIST **</title>

<script>
"use strict"
//** 검색 & 페이징 포함한 요청의 Ajax 처리
// => Ajax 요청 function 작성, url 을 매개변수로 전달 : axiMListCri(url) 
// => Page 요청 : aTag -> span 으로 변경하고 function 으로 처리 
// => Check 검색은 submit 을 사용하기 때문에 적용하지 않음(주석처리)

// => Ajax 처리시에는 문서내부의 function이 인식 안되므로
//    searchDB(), keywordClear() 모두 axTest03.js 에 작성  


//** Ajax Member_PageList *********************
//=> axiMList 에 Paging + 검색기능 추가
//=> 검색조건 & Paging , Ajax 구현
// -> 입력된 값들을 서버로 전송요청: axios
//-> url 완성후 axios 호출

//1. 검색조건 입력 후 버튼클릭
//-> jsp  문서내무의 script 구문을 외부문서로 작성 : EL Tag 적용안됨 -> 외부 문서까지는 체크 하지 않음
// ${pageMaker.makeQuery(1)} -> " ~~~~ "?currPage=1&rowsPerPage=5 
/*
function searchDB(){
	self.location='axmcri'
					+ '?currPage=1&rowsPerPage=5'
					+'&searchType='+document.getElementById('searchType').value
					+'&keyword='+document.getElementById('keyword').value; 
      console.log(document.getElementById('keyword').value); 
      axiMListCri(url);
} 
*/
//리스트가 한번 뜨고, 버튼을 눌었을 때 url을 검색하는 방식이 되어야 함 => axiMListCri(url);
		
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
/* 
//3. axios code => 못읽어 옴. axTest03에 삽입
function axiMListCri(url){
	console.log("찍혀라cri");
	
	url="/member/"+url;
	alert(`axiMListCri url =${url}`);
	axios.get(url
	).then(response=>{
		console.log(" ** response 성공 ** ");
		document.getElementById('resultArea1').innerHTML=response.data;
	}).catch(err=>{
		document.getElementById('resultArea1').innerHTML="**axiMListCri 실패 => "+err.message;
	});
	document.getElementById('resultArea2').innerHTML="";
}
*/
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
    <form action="" method="get">
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
      
      <input type="checkbox" class="check clear" value="1"${ck1 ?'checked':''}>최문석&nbsp;
      <input type="checkbox" class="check clear" value="2"${ck2 ?'checked':''}>김수빈&nbsp;
      <input type="checkbox" class="check clear" value="3"${ck3 ?'checked':''}>최승삼&nbsp;
      <input type="checkbox" class="check clear" value="4"${ck4 ?'checked':''}>김수옥&nbsp;
      <input type="checkbox" class="check clear" value="7"${ck5 ?'checked':''}>관리자&nbsp;
       
      <button type="button" onclick="axiMListcheck()">check선택</button>&nbsp;
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
			src="/resources/uploadImages/${banana.uploadfile}"></td>
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
     	<!-- 버전2 : searchQuery 사용 -->	
     		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(1)}')">FP</span>&nbsp;
     		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.spageNo-1)}')">&LT;</span>&nbsp;
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
     		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(i)}')">${i}</span>&nbsp;
		</c:if>
	</c:forEach>

<!-- 3) Next, LastPage 
      => ver01: makeQuery() 메서드 사용
      => ver02: searchQuery() 메서드 사용 -->
	<c:choose>
		<c:when test="${pageMaker.next && pageMaker.epageNo > 0}">
     		<span class="textlink" onclick="axiMListCri('${pageMaker.makeQuery(pageMaker.epageNo+1)}')">&GT;</span>&nbsp;
     		<span class="textlink" onclick="axiMListCri('${pageMaker.makeQuery(pageMaker.lastPageNo)}')">LP</span>&nbsp;
		</c:when>
		<c:otherwise>
     		<font color="LightGray">&GT;&nbsp;&nbsp;&nbsp;LP</font>
     	</c:otherwise>
	</c:choose>
</div>



<hr>
<a href="/home">Home</a> 
<!-- 서블릿으로 가는 것이 아니기 때문에,  -->
<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>