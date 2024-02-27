<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Update Form **</title>
</head>
<body>
	<form action="update" method="post" enctype="multipart/form-data">
		<table>
			<tr height="40">
				<td bgcolor="lavender"><label for="id">I D</label></td>
				<td><input type="text" name="id" id="id"
					value="${requestScope.info.id}" readonly size="20"></td>
				<!-- readonly 속성은 <input> 요소의 입력 필드가 읽기 전용임 -->
			</tr>
			<%-- <tr height="40">
				<td bgcolor="lavender"><label for="password">Password</label></td>
				<td><input type="password" name="password" id="password"
					value="${requestScope.info.password}" readonly></td>
			</tr> --%>
			<tr height="40">
				<td bgcolor="lavender"><label for="name">Name</label></td>
				<td><input type="text" name="name" id="name"
					value="${requestScope.info.name}"></td>
			</tr>
			<tr height="40">
				<td bgcolor="lavender"><label for="age">Age</label></td>
				<!-- scope라고 지정해 주는 것은, direct로 찾을 수 있게 해주기 위해서임 -->
				<td><input type="text" name="age" id="age"
					value="${requestScope.info.age}"></td>
			</tr>
			<tr height="40">
				<td bgcolor="lavender"><label for="jno">Jno</label></td>
				<td><select name="jno" id="jno">
						<option value="1" ${requestScope.info.jno==1 ? "selected":""}>1조
							: business</option>
						<option value="2" ${requestScope.info.jno==2 ? "selected":""}>2조
							: static</option>
						<option value="3" ${requestScope.info.jno==3 ? "selected":""}>3조
							: 칭찬해조</option>
						<option value="4" ${requestScope.info.jno==4 ? "selected":""}>4조
							: 카톡으로얘기하조</option>
						<option value="7" ${requestScope.info.jno==7 ? "selected":""}>7조
							: 칠면조(관리팀)</option>
				</select></td>
			</tr>
			<tr height="40">
				<td bgcolor="lavender"><label for="info">Info</label></td>
				<td><input type="text" name="info" id="info"
					value="${requestScope.info.info}"></td>
			</tr>
			<tr height="40">
				<td bgcolor="lavender"><label for="point">Point</label></td>
				<td><input type="text" name="point" id="point"
					value="${requestScope.info.point}"></td>
			</tr>
			<tr height="40">
				<td bgcolor="lavender"><label for="birthday">Birthday</label></td>
				<td><input type="date" name="birthday" id="birthday"
					value="${requestScope.info.birthday}"></td>
			</tr>
			<tr height="40">
				<td bgcolor="lavender"><label for="rid">추천인</label></td>
				<td><input type="text" name="rid" id="rid" size="20"
					value="${requestScope.info.rid}"></td>
			</tr>
			<!-- Image Update 추가 
         => form Tag : method, enctype 확인
         => new Image 를 선택하는 경우 -> uploadfilef 사용
         => new Image 를 선택하지않는 경우 
            -> 본래 Image 를 사용 -> uploadfile 값이 필요함
   -->
			<br>
			<tr height="40">
				<td bgcolor="lavender"><label for="uploadfilef">Image</label></td>
				<td><img alt="MyImage" width="80" height="100" class="select_img"
						src="/demoM/resources/uploadImages/${requestScope.info.uploadfile}">
					<input type="hidden" name="uploadfile" id="uploadfile"
					value="${requestScope.info.uploadfile}"><br>
					
					<input type="file" name="uploadfilef" id="uploadfilef" size="20">
				</td>
				<script>
					/* 멀티 이미지 파일 => 실제로 배열 형태로 저장 되어 있으나,
					이 경우, 하나의 이미지만 사용하기 때문에 인덱스 [0]을 사용함.
					또한 클래스의 이름이 동일 할 수 있기때문에 인덱스 사용함. */
					document.getElementById('uploadfilef').onchange = function(e) {
						if (this.files && this.files[0]) {
							let reader = new FileReader;
							reader.readAsDataURL(this.files[0]);
							reader.onload = function(e) {
								// => jQuery를 사용하지 않는경우 
								document.getElementsByClassName('select_img')[0].src = e.target.result;
								
								/*  $는 jquery를 활용하는 것. => 모든 것이 다 함수 형태로 계속 활용가능*/
								//$(".select_img").attr("src", e.target.result).width(70).height(90); 
							} // onload_function
						} // if   
					}; //change
				</script>
			</tr>

			<br>
			<tr height="40">
				<td><input type="submit" name="change" value="수정"></td>
				<td><input type="reset" name="cancle" value="취소"></td>
			</tr>
		</table>
	</form>
	<hr>
	&nbsp;
	<a href="pwUpdate">비밀번호 변경</a>&nbsp; &nbsp;
	<a href="/home">HOME</a>&nbsp; &nbsp;
	<a href="javascript:history.go(-1)">이전으로 돌아가기</a>&nbsp;
</body>
</html>