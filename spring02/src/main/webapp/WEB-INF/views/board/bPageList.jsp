<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/myLib/myStyle.css" >
</head>
<body>
	<h2>** Spring MVC02 Board List **</h2>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>

<div style="width:100%;">
<table>
<tr bgcolor="lavender">
	<th>Seq</th>
	<th>Title</th>
	<th>I D</th>
	<th>RegDate</th>
	<th>조회수</th>
</tr>
<c:if test="${!empty requestScope.banana}">
	<c:forEach var="b" items="${requestScope.banana}">
	<tr>
		<td>${b.seq}</td>
		<td>
		<!-- 답글 등록 후 Title 출력 전에 들여쓰기 추가  -->
		<c:if test="${b.indent>0}">
			<c:forEach begin="1" end="${b.indent}">
				<span>&nbsp;&nbsp;</span>
			</c:forEach>
			<span style="color:MediumSlateBlue;">re.. </span>
		</c:if>
		<!--  로그인 한 경우에만, 볼 수 있도록 함.  -->
			<c:if test="${!empty loginID}">
				<a href="boarddetail?jCode=D&seq=${b.seq}">${b.title}</a>
			</c:if>
			<c:if test="${empty loginID}">
				${b.title}
			</c:if>
		</td>
		<td>${b.id}</td>
		<td>${b.regdate}</td>
		<td>${b.cnt}</td>
	</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana}">
	<tr><td colspan="5">!!board 출력 데이터 없음!!</td></tr>
</c:if>
</table>
</div>


<div align="center">
<!-- ** Paging Block ** 
   => ver01: QueryString 수동 입력 -> 자동생성

     1) FirstPage, Prev  -->
     <c:choose>
     	<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
     		<a href="bPageList?currPage=1&rowsPerPage=5">FP</a> &nbsp;
     		<a href="bPageList?currPage=${pageMaker.spageNo-1}&rowsPerPage=5">&LT;</a>&nbsp;
     		<!-- <img alt="&LT;" src="/spring02/resources/images/arrow_pre.gif"> -->
     	</c:when>
     	<c:otherwise>
     		<font color="LightGray">FP&nbsp;&nbsp;&nbsp;</font>
     	</c:otherwise>
     </c:choose>
     
<!-- 2) Display PageNo 
	=> currPage 제외한 PageNo 만 a Tag 적용 -->
	<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
		<c:if test="${i==pageMaker.cri.currPage}">
			<font color="Orange" size="5"><b>${i}</b></font>
		</c:if>
		<c:if test="${i!=pageMaker.cri.currPage}">
			<a href="bPageList?currPage=${i}&rowsPerPage=5" >${i}</a>
		</c:if>
	</c:forEach>

<!-- 3) Next, LastPage 
      => ver01: makeQuery() 메서드 사용
      => ver02: searchQuery() 메서드 사용 -->
	<c:choose>
		<c:when test="${pageMaker.next && pageMaker.epageNo > 0}">
			&nbsp;<a href="bPageList?currPage=${pageMaker.epageNo+1}&rowsPerPage=5">&GT;</a>
			&nbsp;<a href="bPageList?currPage=${pageMaker.lastPageNo}&rowsPerPage=5">LP</a>
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
<a href="/spring02/home">Home</a> 


</body>
</html>