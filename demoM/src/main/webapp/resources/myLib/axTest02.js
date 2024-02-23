/**
목표! 
 => 데이타를 보내야 하는데, 어떻게 보낼 것인가?
 => 특히, 파일 업로드 하는 경우 어떻게 해야할것인가?

// ** Ajax_REST API Join Test **
// => axios
// => file_UpLoad 가 포함된 formData 처리
// => joinForm 요청: MemberController -> 웹 Page return
// => join 요청: RTestController -> 결과 Text return  

 */
 "use strict"
 
 // 1) joinForm
 // => response: 웹 페이지
function rsJoinf(){
    let url ="/member/joinForm";
    console.log(`로그인`);
    axios.get(url
        ).then(response => {
            console.log( ` **response : joinForm 성공! ** `);
            //resultarea에 출력하면됨. 
            document.getElementById('resultArea1').innerHTML=response.data;
        }).catch(err=>{
            alert(` **response : joinForm 실패! => ${err.message}`);
        });
} //rsJoinf
// ** form Tag의 input Data 처리방법
// => 직접 입력 : multipart 타입은 처리할 수 없음
//      data: { id:document.getElementById('id').value,
//              password:document.getElementById('password').value
//           } 
// => Form 의 serialize() : jQuery 만 적용됨
//       -> input Tag 의 name 과 id 가 같아야 함.   
//       -> multipart 타입은 전송안됨. 
//         처리하지 못하는 값(예-> file Type) 은 스스로 제외시킴 
// => 객체화 : multipart 타입은 처리할 수 없음
//       let myData = {
//            id:document.getElementById('id').value,
//            password:document.getElementById('password').value
//         }

// => FormData 객체 활용 : JS의 내장객체
//      -> append 메서드 : multipart 타입 처리 불편
//      -> 생성자 매개변수 이용 : multipart 타입 포함 간편한 처리가능 




// 2) join 처리 
//  => fileUpload 포함된 경우 -> 되어 있건 안되어 있건 서버에 많은 데이터를 보내줘야함. 
//      이전에는 하나씩(id,password...) 해주었다면,
//      JS에서는 form data라는 객체를 통해 한번에 전송 할 수 있음 

//  => fileUpload 포함된 경우 : JS 의 내장객체 FormData 에 담아서 전송
//  => Data전송: JS 의 FormData 사용, 요청_headers "Content-Type" 변경
//  => multipart 타입 포함
//  => JS의 내장객체 FormData 사용
//      -> 요청_headers

function axiJoin(){
    // 1) data 전송 준비
    //  JoinForm 의 form태그 id를 등록해주어, getElementById에 주입. 
    //  form-data라는 얘는 그 안에 value값들을 모두 담아줌. 
    //  이미지가 있는경우, (multipartType) 이 있는 경우 모두 가져와줌 -> 자동으로 처리
    let formData = new FormData(document.getElementById('myform'));
    //console.log("조인들어옴");
    // 2) Axios 표정 처리 
    let url= "/rest/rsjoin";
    axios.post(url,formData,{
            headers:{'Content-Type':''} //option 값이기 때문에 headersdp {} 넣어줘야함.
        }).then(response =>{
            alert(` ** join 성공! => ${response.data}`);
            location.reload();
        }).catch(err=>{
            if( err.response.status=='502') alert(" ==! 입력 오류 !== ");
            else alert(` ==! 시스템 오류 !! 잠시후, 다시 시도 하세요! => ${err.message}`);
        })
}



