/**
 목표!
 Member List를 그리기 

 ** Ajax_REST API, Axios Test **
  => Axios 메서드형식 적용
 => 1. List 출력
   - axiMList : MemberController, Page response
   - idbList(id별 boardList) : RTestController, List_Data response 
 => 2. 반복문에 이벤트 적용하기
   - Delete, JoDetail
 */


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// 1. List 출력 
// 1.1) Page response
//      => response 를 resultArea1 에 출력하기 
//      => 요청 : /member/aximlist
//      => response: axmemberList.jsp
function axiMList(){
    let url ="/member/axMemberList";
    //console.log(`멤버리스트`);
    axios.get(url
        ).then(response => {
            console.log( ` **response : member List 출력 성공! ** `);
            //resultarea에 출력하면됨. 
            document.getElementById('resultArea1').innerHTML=response.data;

        }).catch(err=>{
            alert(` **response :  member List 출력 실패! => ${err.message}`);
        });
}

// 페이징 정보
// search 
function searchDB(){
  let url="axmcri"
					+ '?currPage=1&rowsPerPage=5'
					+'&searchType='+document.getElementById('searchType').value
					+'&keyword='+document.getElementById('keyword').value; 

  //console.log(document.getElementById('keyword').value); 

  axiMListCri(url);
}  

// 
function axiMListcheck(){
  console.log("zxc");
  // => 첫요청
  //  url=/axmcheck?currPage=1&rowsPerPage=5&check=1&check=2&check=3
  let checkAll = document.querySelectorAll(".check");
  let checkData ="";

  for (let i=0; i<checkAll.length; i++) {
     if (checkAll[i].checked)
     checkData +="&check="+checkAll[i].value;
  }
  //console.log(checkData);
  let url='axmcheck'
           +'?currPage=1&rowsPerPage=5'
           +checkData;
  axiMListCri(url); // axios 호출   
} //axiMListCheck



function axiMListCri(url){
	//console.log("찍혀라cri");
  
	url ="/member/"+url;
	alert(`axiMListCri url =${url}`);

	axios.get(url
	).then(response=>{
		console.log(" ** response 성공 ** ");
		document.getElementById('resultArea1').innerHTML=response.data;
	}).catch(err=>{
    console.log(` ** catch 에러 `);
		document.getElementById('resultArea1').innerHTML="**axiMListCri 실패 => "+err.message;
	});
	 document.getElementById('resultArea2').innerHTML="";
}


//1.2) idbList(id 별 board List)
//  => RESTController , PathVariable 처리 ,  List_Data response
//  => Server : service, Sql 구문 작성
//  => request : id를 path 로 전송 => "/rest/idblist/banana" 로 전송한다는 이야기
//  => response 
//      1. 성공이면 = 반복문을 이용해서, table로 List 출력 완성, resultArea2 에 출략. 
//          - 출력자료의 유무 = server에서 출력 자료가 없으면,  502로 처리 
//      2. 실패이면 = resultArea2 clear, alert 으로 에러 메세지 출력하기 
//      
function idbList(id){
  //console.log("1");
  let url = "/rest/idblist/"+id;
  //controller 가 REST 로 보내져야함

  axios.get(url
    ).then( response=>{
        alert("** 성공 => resultArea2 에 List 작성 **");
        console.log(" ** result list_data 전달 된 데이터 => "+ response.data);
        // response.data로 활용하기 불편하기 때문에, 다른 변수에 담아서 따로 활용
        let listData = response.data;
        let resultHtml=
        `<table style="width:100%">
          <tr bgcolor="lavender">
            <th>Seq</th>
            <th>Title</th>
            <th>I D</th>
            <th>RegDate</th>
            <th>조회수</th>
          </tr>
        `;
        //=> 반복문 적용 : why? 데이터가 하나가 아닌 여러개인 경우, 배열 형태로 받아 읽어들여야함 
        for(let b of listData){
          resultHtml += `
          <tr>
		        <td>${b.seq}</td>
		        <td>${b.title}</td>
		        <td>${b.id}</td>
		        <td>${b.regdate}</td>
		        <td>${b.cnt}</td>
	        </tr>
          ` ;
        } //for
        resultHtml += `</table>`;
        document.getElementById('resultArea2').innerHTML=resultHtml;

    }).catch( err =>{
        // => 오류를 잡는 경우에는 502와 같이 출력자료가 아예 없거나, 있더라도 자료에 오류가 있는 경우가 있음
        // => response 의 status 값이 502 면, 출력자료 없음. 
        // => 
        if( err.response.status=='502'){
          document.getElementById('resultArea2').innerHTML
            =err.response.data;
        } else {
          document.getElementById('resultArea2').innerHTML="";
          alert(" ** 시스템 오류, 잠시 후 입력하시오 => "+err.message);
        }

    });

  // 리스트가 담겨진 형태로 옴 -> 객체 형태임. 

}


// DELETE 기능 
//  => axiDelete('${banana.id}') 
//  => 요청 : '/rest/axiDelete/' PathVariable 적용
//  => response: 결과는 성공 / 실패 여부만 전달 RESTController
//  => 성공 : Deleted로 변경, onclick 이벤트 해제
function axiDelete(id){
  let url ="/rest/axidelete/"+id;

  axios.delete(url
    ).then(response=>{
      alert(response.data);
      //  => 삭제 성공 
      //  => * delete -> deleted, gray_color, bold
      // 딜리트를 누르면, 내부 글자 변경 스팬태그에 "d"를 추가 작성하기? 
      //  => * Style 제거  
      document.getElementById(id).innerHTML="Deleted";
      document.getElementById(id).style.color="Gray";
      document.getElementById(id).style.fontWeight="bold";
      document.getElementById(id).classList.remove('textlink');
      document.getElementById(id).removeAttribute('onclick');
      
      console.log("삭제 성공");
    }).catch(err => {
      //  => 실패 
      if( err.response.status=='502' ){
        alert(err.response.data);
      } else {
        alert(" ** 시스템 오류, 잠시후 다시 시도해 주세요");
      }
    });

}