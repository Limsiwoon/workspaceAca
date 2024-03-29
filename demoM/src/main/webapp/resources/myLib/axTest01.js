/**
axTest01.js파일 목표 => ajax를 통해 로그인 테스트

// ** Ajax_REST API Login Test **
// 1. fetch방식!!!!!!!


// => then 1단계  
//         * response 속성값 
//         response.status – HTTP 상태코드(예: 200)
//         response.ok - Boolean, HTTP 상태 코드가 200-299 사이이면 True
//         response.headers – HTTP 헤더를 담고있는 맵과 유사한 객체
//          * Body-reading 메서드
//         response.text() – 응답을 읽고 텍스트로 반환
//         response.json() – 응답을 JSON으로 구문 분석
//         response.formData() – 응답을 FormData객체로 반환
//         response.blob() – 응답을 Blob (유형이 있는 이진 데이터) 으로 반환
//         response.arrayBuffer() – 응답을 ArrayBuffer (바이너리 데이터의 저수준 표현) 로 반환
//         response.body - ReadableStream 객체이므로 본문을 청크별로 읽을 수 있다.

//=> catch: then 1단계에서 발생시킨 Error객체 의 매개변수값 을 인자의 message 속성으로 전달받아 처리함
         
// => Test1) Post요청: rsLogin(), rsLoginJJ: JSON -> Text, JSON
// => Test2) Get요청: rsDetail() -> selectOneJno 
// => Test3) Delete요청: rsDelete

 */
"use strict"
// Test1) rsLogin
// 1.1) form 출력 하기 -> rsLoginF()
// form 태그를 작성하지 않은 이유 -> ajax를 통해 만들기 위해서 

function rsLoginf(){
	let resultHTML = 
	`
	<table align center>
		<caption><h3>** Ajax Login Form **</h3></caption>
		<tr height=40><td bgcolor="MediumSlateBlue"><label for="id">I D</label></td>
			<td><input type="text" name="id" id="id"></td>
		</tr>
		<tr height=40><td bgcolor="SlateBlue"><label for="password">Password</label></td>
			<td><input type="password" name="password" id="password"></td>
		</tr>
		<tr height=40>
			<td colspan="2"><span class="textlink" onclick="rsLogin()">rsLogin</span>&nbsp;&nbsp;
				<span class="textlink" onclick="rsLoginJJ()">rsLoginJJ</span>&nbsp;&nbsp;
				<span class="textlink" onclick="axiLoginjj()">axiLoginJJ</span>&nbsp;&nbsp;
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>
	`;

	document.getElementById('resultArea1').innerHTML=resultHTML;

}//rsLoginF


// 1.2) Login 기능 Service 요청처리
// => Ajax 요청, fetch 적용
// => @RestController, 계층적 uri 적용, Post 요청
// => request: JSON, response: Text 

function rsLogin(){
	let url="/rest/rslogin";
	console.log("1S");
	//stringify : 함수 -> 객체화 하려는 객체를 넣어주면 됨. id값이 id와 password인 것입력. 
	fetch(url,{method:'Post', body:JSON.stringify({
										id:document.getElementById('id').value,
										password:document.getElementById('password').value 
							}), //body
							//headers는 여러가지 속성을 가질 수 있기 때문에 ~~:~~ 형식으로 넣어줌
							headers:{'Content-Type':'application/json'}
							// => POST 요청에서는 반드시 headers 형식 작성 
             				//    (JSON 포맷을 사용함을 알려줘야함)
	}).then(response => {
		// 위에서 받아온 정보를 리스폰스에 받아서확인용 
		// ** then 1 단계
        // => status 확인 -> Error catch 블럭으로 또는 Response Body-reading Data return
        // => Response Body의 Data-reading & return.
		// => response.status 안에 에러가 500. 400. 404인지 담음
		if(!response.ok) throw new Error(response.status) // 에러 발생시킴
		// => Error 임을 인지시켜 catch 블럭으로 
      	//   - fetch는 네트워크 장애등으로 HTTP요청을 할수없거나,
     	//     url에 해당하는 페이지가 없는 경우에만 거부(reject)되어 catch로 분기하므로,
      	//     .then절(1단계) 에서 수동으로 HTTP 에러를 처리함.
      	//     그러나 axios는 상태코드가 2xx의 범위를 넘어가면 거부(reject)함.
		return response.text();
		// return하여 아래 then으로 전달함 
		// 서버에서 Text 형식으로 보냈으므로 text()메서드 사용
		//	(Type 별로 Body-reading Method를 적용함)
	}).then(responseData => {
		// ** then 2 단계실행용
       // => 1단계에서 return 한 Data 처리
		alert(` ** response Data => ${responseData}`);
		location.reload();
	}).catch(err => {
		// 에러나 then 에서 잡히지 않은 것을 실행용(비정상 실행)
		console.error(` ** Error => ${err.message}`);
		if(err.message=='502') alert('너의 오류! id 혹은 password 오류 입니다. 다시 로그인 부탁 드립니다~');
		else alert(`나의 오류! System오류 입니다. 잠시 후 다시 로그인 부탁 드립니다! status => ${err.message}`);
	});
}	
	
// 1.3) rsloginJJ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// 	=> request : JSON, response:JSON
function rsLoginJJ(){
	console.log("2")
	let url="/rest/rsloginjj";
	//stringify : 함수 -> 객체화 하려는 객체를 넣어주면 됨. id값이 id와 password인 것입력. 
	fetch(url,{method:'Post', body:JSON.stringify({
										id:document.getElementById('id').value,
										password:document.getElementById('password').value 
							}), //body
							//headers는 여러가지 속성을 가질 수 있기 때문에 ~~:~~ 형식으로 넣어줌
							headers:{'Content-Type':'application/json'}
							// => POST 요청에서는 반드시 headers 형식 작성 
             				//    (JSON 포맷을 사용함을 알려줘야함)
	}).then(response => {
		// 위에서 받아온 정보를 리스폰스에 받아서확인용 
		// => response.status 안에 에러가 500. 400. 404인지 담음
		if(!response.ok) throw new Error(response.status) // 에러 발생시킴

		return response.json();
		// return하여 아래 then으로 전달함 
		// => 서버에서 JSON 형식으로 보냈으므로 json() 메서드 사용
      	//    서버에서 UserDTO를 사용했으므로 사용시에 맴버변수명 주의(id, username..) 
	}).then(responseData => {
		// ** then 2 단계실행용
       // => 1단계에서 return 한 Data 처리
		alert(` ** response Data => id: ${responseData.id} , name= ${responseData.username} `);
		location.reload();
	}).catch(err => {
		// 에러나 then 에서 잡히지 않은 것을 실행용(비정상 실행)
		console.error(` ** Error => ${err.message}`);
		if(err.message=='502') alert('너의 오류! id 혹은 password 오류 입니다. 다시 로그인 부탁 드립니다~');
		else alert(`나의 오류! System오류 입니다. 잠시 후 다시 로그인 부탁 드립니다! status => ${err.message}`);
	});	 
	
	
} // rsLoginJJ











// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// 2. AXIOS 방식!!!!!!!
// => 라이브러리 추가 (CDN 으로..   axTest01.js 에)
// => 서버요청은 위 "1.3) rsLoginJJ" 과 동일하게 rsloginjj 
// => JSON <-> JSON
// => Request
//   - data  : JSON Type 기본 (fetch 처럼 JSON.stringify 필요없음) 
//   - header: {'Content-Type': 'application/json'}  
// => Response
//    - then : 응답 Data는 매개변수.data 로 접근가능, JSON Type 기본 (1단계로 모두 받음: fetch 와 차이점))   
//   - catch
//      . axios는 상태코드가 2xx의 범위를 넘어가면 거부(reject) 되어 catch절로 분기함 
//         이때 catch 절의 매개변수는 response 속성으로 error 내용 전체를 객체형태로 전달받음   
//       . error.response : error 내용 전체를 객체형태로 전달받음
//       . error.response.status : status 확인가능   
//       . error.message : 브라우져의 Error_Message, "Request failed with status code 415

function axiLoginjj(){
	let url ="/rest/rsloginjj";
	
	axios({ url:url,
			method:'Post',
			headers:{ 'Content-Type':'application/json' },
			data:{
				id:document.getElementById('id').value,
				password:document.getElementById('password').value
			}
		
	}).then(response => {
		alert(`** response.data => ${response.data}`);
		alert(`** response:id => ${response.data.id} , name=>${response.data.username}`);
		location.reload();
		
	}).catch(err=>{
		console.error(`** err.response => ${err.response},
						** err.response.status => ${err.response.status}, 
						** err.message => ${err.message}`);
						
		if( err.response.status =='502') alert(`너의 오류! id 혹은 password 오류 입니다. 다시 로그인 부탁 드립니다~`)
		else alert(`나의 오류! System오류 입니다. 잠시 후 다시 로그인 부탁 드립니다! status => ${err.message}`);
	
	});

	
}//axiLoginjj



// ***************************************************************
// *** JSON 객체
// => JavaScript Object Notation(JSON)을 분석하거나
//    값을 JSON으로 변환하는 메서드를 가지고 있으며,
//     JSON 을 직접 호출하거나 인스턴스를 생성할 수 없음.
// => JSON.stringify() : JavaScript 값이나 객체를 JSON 문자열로 변환
// => JSON.parse() : JSON 문자열을 구문 분석하여 JavaScript 값이나 객체를 생성.

// ***************************************************************
// *** fetch
// => fetch(url, [options])
//    -> url: 액세스할 URL
//   -> options – 선택적 매개변수: 메소드, 헤더 등
//   -> fetch 함수의 응답(Response) 처리는 일반적으로 2단계로 함.
//   -> 실행후 promise객체를 return 하므로, .then() .catch() .finally() 등의 메서드체인 적용됨. 

// => Response 의 Property
//  -> response.status: HTTP 상태 코드(예: 200)
//  -> response.ok: Boolean, HTTP 상태 코드가 200-299 사이 이면 True
//  -> response.headers: HTTP 헤더가 있는 맵과 유사한 객체 

// => fetch response 처리 1단계 : ~.then(response => ....
//   -> 서버가 헤더를 Response 하는 즉시 이 Response 객체를 래핑한 Promise 객체를 반환함.
//      이 시점에서는 HTTP상태, 성공여부, 헤더 등은 확인가능하지만 Body Data는 확인할 수 없음 
//   -> 그래서 Response Body-reading 메서드를 호출함. 
//   -> Response Body-reading 메서드는 하나만 선택가능 (추가호출해도 이미 처리되었으므로 작동하지않음)
//      json(), text(), formData(), blob(), arrayBuffer() 등  
//   -> Error 처리
//      - fetch는 네트워크 장애등으로 HTTP요청을 할수없거나,
//        url에 해당하는 페이지가 없는 경우에만 거부(reject)되어 catch 절로 분기 가능 
//      - 따라서 .then절(1단계) 에서 수동으로 HTTP 에러를 처리해야 함.
//        ( 반면에 axios는 상태코드가 2xx의 범위를 넘어가면 거부(reject)함. )  

// => fetch response 처리 2단계 : ~.then(responseData => ....
//   -> 1단계에서 return 한 Body-reading Data 를 처리

// ** Promise
// => 비동기 처리에서 동기식(순차적) 처리를 지원해줌.
//    즉, 지연함수 와 비동기연산(Ajax) 을 제어할 수 있도록 해주는 객체. 
//   ( 지연함수: 지정한 시간 후에 실행 되도록 정의한 함수 )
// => 기본형식
/*
   promise.then(function(count){
          console.log("** Test2) resolve count => "+count); 
       }).catch(function(message){
          console.log("** Test2) reject message => "+message); 
       }).finally(function(){
          console.log("** Test2) finally Test"); 
       });
*/



