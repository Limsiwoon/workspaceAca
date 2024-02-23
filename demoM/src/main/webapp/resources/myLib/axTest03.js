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
    console.log(`멤버리스트`);
    axios.get(url
        ).then(response => {
            console.log( ` **response : member List 출력 성공! ** `);
            //resultarea에 출력하면됨. 
            document.getElementById('resultArea1').innerHTML=response.data;

        }).catch(err=>{
            alert(` **response :  member List 출력 실패! => ${err.message}`);
        });
}