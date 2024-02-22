<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
<link rel="stylesheet" type="text/css" href="/resources/myLib/myStyle.css" >
<script>
"use strict"
//1. 검색조건 입력후 버튼클릭
//	=> 입력값들을 서버로 전송 요청처리:  location


// ** self.location   
// 1) location 객체 직접사용 Test : url로 이동, 히스토리에 기록됨
// 2) location 객체의 메서드
// => href, replace('...'), reload() 
function searchDB(){
	self.location='bPageList'
					//+'${pageMaker.makeQuery(1)}'  
       				// => 하나의 jsp 문서로 다양한 요청을 처리하기위해 쿼리스트링에 url 을 포함했기 때문에
        			//    첫 요청에서는  makeQuery 메서드 사용할수 없음
					+ '?currPage=1&rowsPerPage=5'
					+'&searchType='+document.getElementById('searchType').value
					+'&keyword='+document.getElementById('keyword').value; 
      console.log(document.getElementById('keyword').value);
	// 즉, bPageList 페이지의 첫페이지를 searchType이 무엇이고, 검색string이 무엇인지 전달하는 것. 
	// 외부 문서에서는 할 수 없고, 내부 문서에서만 가능하도록 만들어 진것. 
	
	//** JS 코드 내부에서 el Tag 사용시 주의사항
	//	 => JS 코드의 스트링 내에서 사용한 el Tag 는 JSP 가 처리해주므로   
	// 		사용가능 하지만, 이 스크립트가 외부 문서인 경우에는 처리해주지 않으므로 주의
	//		이 코드를 외부문서로 작성하면 "${pageMaker.makeQuery(1)}" 이 글자 그대로 적용되어 404 발생 
}


//=> searchBtn 을 클릭한 경우 : 검색조건 입력 후 첫 Page 요청
//이때는 서버에 searchType, keyword 가 전달되기 이전이므로 
//searchType, keyword 가 없는 makeQuery 메서드사용
//=> self.location="bcrilist?currPage=?????" : 해당 요청을 서버로 전달    

		
//2. searchType 을 '전체' 로 변경하면 keyword는 clear 
function keywordClear(){
	if(document.getElementById('searchType').value=='all'){
		document.getElementById('keyword').value==''; //전체 선택했을 경우, 검색어를 삭제. 
	}
}

// Board Check_List
// 체크한 값들을 다시 풀기 위해 사용하는 메서드 

function checkClear(){
	// document.querySelectorAll('.clear') 리스트 모양 => checked 바로 사용이 불가능함. 
	// document.querySelectorAll('.clear').checked=false; 따라서 반복문 처리를 하여 사용. 
	
	let ck = document.querySelectorAll('.clear');  /* //리스트의 모양을 ck안에 담음 */
	for(let i=0; i <ck.length; i++){
		ck[i].checked=false;
	}
	return false; //reset 의 기본 이벤트를 제거하기 위함. 
}
// ** querySelector
// => css 선택자를 이용하여 첫번째 만난 요소 1개만 선택

// ** querySelectorAll 
// => css 선택자를 이용하여 해당하는 nodeList 를 반환
// =>  ","를 사용하면 여러 요소를 한번에 가져올 수 있음
//     querySelectorAll("#id,.class");
// => 그러므로 반복문과 이용됨.
</script>

</head>
<body>
	<h2>** Spring MVC02 Board List **</h2>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<hr>

<!-- 검색기능 만들기 -->
<br>
<div id="searchBar">
   <select name="searchType" id="searchType" onchange="keywordClear()">
      <option value="all" ${ pageMaker.cri.searchType =='all' ? 'selected' : '' } >전체</option>
      <option value="title" ${ pageMaker.cri.searchType =='title' ? 'selected' : '' } >제목</option>
      <option value="content" ${ pageMaker.cri.searchType =='content' ? 'selected' : '' } >내용</option>
      <option value="id" ${ pageMaker.cri.searchType =='id' ? 'selected' : '' } >글쓴이</option>
      <option value="regdate" ${ pageMaker.cri.searchType =='regdate' ? 'selected' : '' } >작성일자</option>
      <option value="tc" ${ pageMaker.cri.searchType =='tc' ? 'selected' : '' } >제목+내용</option>
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
         <c:if test="${id=='admin'}"> <c:set var="ck1" value="true" /> </c:if>
         <c:if test="${id=='simsim916'}"> <c:set var="ck2" value="true" /> </c:if>
         <c:if test="${id=='agr4005'}"> <c:set var="ck3" value="true" /> </c:if>
         <c:if test="${id=='bamboo7'}"> <c:set var="ck4" value="true" /> </c:if>
         <c:if test="${id=='kso1'}"> <c:set var="ck5" value="true" /> </c:if>
      </c:forEach>
      
      
      <input type="checkbox" name="check" class="clear" value="admin"${ck1 ?'checked':''}>관리자&nbsp;
      <input type="checkbox" name="check" class="clear" value="simsim916"${ck2 ?'checked':''}>최문석&nbsp;
      <input type="checkbox" name="check" class="clear" value="agr4005"${ck3 ?'checked':''}>김수빈&nbsp;
      <input type="checkbox" name="check" class="clear" value="bamboo7"${ck4 ?'checked':''}>최승삼&nbsp;
      <input type="checkbox" name="check" class="clear" value="kso1"${ck5 ?'checked':''}>김수옥&nbsp;
      <input type="submit" value="Search">&nbsp;
      <input type="reset" value="Clear" onclick="return checkClear()"><br><br>
      
   </form>
</div>
<br>
<br>
<div style="width:100%; display:flex; justify-content:center; ">
<table style="width:85%;">
   <tr bgcolor="Orange">
      <th>글번호</th>
      <th>제 목</th>
      <th>작성자</th>
      <th>작성시간</th>
      <th>조회수</th>
   </tr>
   <c:if test="${ !empty requestScope.banana }">
      <c:forEach var="b" items="${ requestScope.banana }">
         <tr>
            <td>${ b.seq }</td>
            <td>
            <!-- 답글 등록 되면 Title 출력전에 들여쓰기 추가 -->
            <c:if test="${ b.indent > 0 }">
               <c:forEach begin="1" end="${ b.indent }">
                  <span>&nbsp;&nbsp;</span>
               </c:forEach>
               <span style="color:Red;">re...</span>
            </c:if>
            <c:if test="${ !empty sessionScope.loginID }">
            <a href="detail?jCode=D&seq=${ b.seq }">${ b.title }</a>
            </c:if>
            <c:if test="${ empty sessionScope.loginID}">
               ${ b.title }
            </c:if>
            </td>
            <td>${ b.id }</td>
            <td>${ b.regdate }</td>
            <td>${ b.cnt }</td>
         </tr>
      </c:forEach>
   </c:if>
   <c:if test="${ empty requestScope.banana }">
      <tr><td colspan="5">~~~ 출력할 게시글이 없습니다 ~~~</td></tr>
   </c:if>

</table>
</div>




<div align="center">
<!-- ** Paging Block ** 
   => ver01: QueryString 수동 입력 -> 자동생성 makeQuert 메서드 적용
     1) FirstPage, Prev
     *****  old 버전 *******
     <c:choose>
     	<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
     		<a href="bPageList?currPage=1&rowsPerPage=5">FP</a> &nbsp;
     		<a href="bPageList?currPage=${pageMaker.spageNo-1}&rowsPerPage=5">&LT;</a>&nbsp;
     	</c:when>
     	<c:otherwise>
     		<font color="LightGray">FP&nbsp;&nbsp;&nbsp;&LT;</font>
     	</c:otherwise>
     </c:choose>
     ***** old 버전 완 ***** -->
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

<c:if test="${!empty loginID}">
	<a href="boardInsert">등록하기</a>
</c:if> 
<hr>
<a href="/home">Home</a> 


</body>
</html>