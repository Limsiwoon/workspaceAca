
/**
** 입력값의 무결성 확인
** member 무결성 확인사항
// ID : 길이(4~10), 영문자,숫자 로만 구성
// Password : 길이(4~10), 영문,숫자,특수문자로 구성, 특수문자는 반드시 1개 이상 포함할것
// Password2: 재입력후 Password 와 일치성 확인
// Name : 길이(2이상), 영문 또는 한글로 만 입력
// Age: 정수의 범위  ( 숫자이면서, '.'이 없어야함 )  
// BirthDay : 입력 여부 확인  ( length == 10 )
// Point : 실수 ( 구간설정 100 ~ 10000 까지만 가능 )
// Jno : select 를 이용 (X)
// Info : (X)

** 작성 규칙
   => JavaScript function 으로 정의 하고 
      결과를 true or false 로 return
   => 정규식을 활용한다.
   
** match Test
   => 아래 조건에 true -> not (!)  match 적용해보면
   => 정확하지 않으므로 (부적절, replace 를 사용)
        ...       
        } else if (!id.match(/[a-z.0-9]/gi)) {
            alert(' ID는 영문자와 숫자로만 입력하세요. !!!')
            return false;
        }    
 */
 
 "use strict"


 // 1 ) ID
 //   => 확인 : 영문과 숫자만 가능 / 특수 문자 X
 function idCheck(){
   let special = /[a-z.0-9]/gi;
 	let id=document.getElementById('id').value;
   if( id.length<4 || id.length>10 ){
      document.getElementById('iMessage').innerHTML='id는 4이상 10미만의 글자여야 합니다.';
      return false;
   } else if(id.replace(special,'').length >0 ){
   // => test( ) 활용
   //} else if( !special.test(id) ){
      //special에 정의된 문자열이 아닌 경우 false값을 반환함.   
      document.getElementById('iMessage').innerHTML='id는 영문과 숫자만 가능합니다. ';
      return false;
   } else {
      document.getElementById('iMessage').innerHTML='';
      return true;
   }
 }
 //id.replace('영문과 숫자','').length>0 의미 : 영문과 숫자를 빈문자열로 변경하면 그 외의 문자들만이 남음.
 //따라서, 그 길이가 1이상인 경우, 다른 문자가 있음을 확인 할 수 있음. 
// 정규식 -> /[a-z.0-9]gi/ 화용

/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 <!-- 
 ** Good 정리
 => https://inpa.tistory.com/entry/JS-📚-정규식-RegExp-누구나-이해하기-쉽게-정리
 
** 정규 표현식 (정규식:Regular Expression) 객체 : RegExp
=> 자바스크립트의 기본 내장 객체 중의 하나
=> 특정한 규칙을 가진 문자열 집합을 표현하는데 사용하는 형식
* 생성
   var regExp1= new RegExp('text') ;
   var regExp2= /text/ ; 
* 메서드   
   test() : 정규식과 일치하는 문자열이 있으면 true 아니면  false 를 return 
   exec() : 정규식과 일치하는 문자열을 return 
* 예제   
   var regExp= /script/ ; 
   var s='Javascript jQuery Ajax';
   
   var output = regExp.test(s) ;
   alert(output) ; 
* 그러나 주로 문자열의 메서드와 같이 사용됨
    
/.../ : 정규식 RegExp 의 리터럴

** 플래그 문자 
g : global, 전역비교
i : 대문자는 소문자 변환후 비교 (대/소문자 구분 없음)
m : 여러줄의 검사 수행
   ( 각줄을 개별문자로 인식하고 검사해줌
    예 : 'JavaScript\njQuery\nAjax' )

\. : . 문자 (. 는 한 문자를 의미하나 \. 는 . 문자를 의미함)
a-z : abcdefghijklmnopqrstuvwxyz 와 같음
0-9 : 0123456789 와 같음
: : : 문자
_ : _ 문자
- : - 문자
[~.~] : ~ 와 ~ , Or 의 묶음
[..] : Or 의 묶음. 안에 기록된 1가지외 중복 적용됨.
[^...] : 내부내용의 부정. 기록된 이외의 것을 찾음.
+ : 하나 이상의 반복적용. (단어(?) 찾음)

*/

// * 메서드   
//    ("문자열").replace(/정규표현식/, "대체문자열") : "정규표현식"에 매칭되는 항목을 "대체문자열"로 변환
//    (정규표현식).test("문자열") : 정규식과 일치하는 문자열이 있으면 true 아니면  false 를 return 
//    (정규표현식).exec("문자열") : 정규식과 일치하는 문자열을 return 

 // 2 ) password
 // => input Tag의  type="password" 의 경우 키보드는 자동 영문
//    2-1 )정규식 이용
//  special= /^(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{4,}$/gi;
//  function pwCheck(){
//    let password=document.getElementById('password').value;
//    if( password.length<4 || password.length>15 ){
//       document.getElementById('pMessage').innerHTML='password는 4이상 15미만의 글자여야 합니다.';
//       return false;
//    } else if( !special.test(password) ){
//       document.getElementById('pMessage').innerHTML='특수문자와 숫자가 반드시 포함되어야합니다. ';
//       return false;
//    } else {
//       document.getElementById('pMessage').innerHTML='';
//       return true;
//    }
  
// }
//    2-2 ) 
function pwCheck(){
   let special = /[a-z.0-9.!-*.@]/gi  ; 
   let password=document.getElementById('password').value;
   if( password.length<4 || password.length>15 ){
         document.getElementById('pMessage').innerHTML='password는 4이상 15미만의 글자여야 합니다.';
         return false;
   } else if( password.replace(special,'').length > 0 ){
         document.getElementById('pMessage').innerHTML='password는 영문 숫자 특수문자만 가능합니다 ';
         return false;
   } else if( password.replace(/[!-*.@]/,'').length >=password.length ) {
         document.getElementById('pMessage').innerHTML='password는 특수문자를 1개 이상 넣어주셔야 합니다';
         return false;
   }else{
          document.getElementById('pMessage').innerHTML='';
          return true;
   }
}

// 3 ) password2

function pw2Check(){
   let password=document.getElementById('password').value;
   let pw2=document.getElementById('password2').value;
   if(password != pw2){ // 자바스크립트는 타입 확인 하지 않음
      document.getElementById('p2Message').innerHTML='password가 다릅니다.';
      return false;
   } else {
      document.getElementById('p2Message').innerHTML='';
      return true;
   }

}

//  4 ) name : 길이 입력
function nmCheck(){
   let name=document.getElementById('name').value;
   if(name.length < 2){ // 자바스크립트는 타입 확인 하지 않음
      document.getElementById('nMessage').innerHTML='이름은 2글자 이상합니다.';
      return false;
   } else if(name.replace(/[a-z.가-힣]/gi,'').length >0 ){
      document.getElementById('nMessage').innerHTML='이름은 영어 혹은 한글만 가능합니다.';
      return false;
   } else {
      document.getElementById('nMessage').innerHTML='';
      return true;
   }
}

// 5 ) age
// => 정수의 조건 : 숫자이면서 소수점이 없어야함
// => Number.isInteger(n) : n 이 정수일 때만 true
      // - 단, n은 숫자 Type 이어야함
      // - value 속성의 값은 문자 type으로 인식함으로 숫자화_parseInt 가 필요함
      // - 단, parseInt(age)  사용시 주의
      // 1. 실수의 경우에는 정수만 사용 (123.56 -> 123)
      // 2. 숫자 뒤쪽에 문자가 포함되면 앞쪽의 숫자만 가져와 정수 return (123abv -> 123)
      // 3. 문자로 시작하면 문자로 취급. NaN (Not a Number) 를 return
// => 숫자 아닌 값이 있는지 확인, Number.isInteger(...) 확인


function agCheck(){
   let age=document.getElementById('age').value;
   console.log(`** parseInt(age) => ${parseInt(age)}`);
   console.log(`** Number.isInteger(age) => ${Number.parseInt(age)}`);
   console.log(`** Number.isInteger(parseInt(age)) => ${Number.isInteger(parseInt(age)) }`);

   if( age.replace(/[^0-9]/,'').length < age.length || Number.isInteger(parseInt(age))==false){
      document.getElementById('aMessage').innerHTML='숫자만 입력 가능합니다!';
      return false;
   }else {
      document.getElementById('aMessage').innerHTML='';
      return true;
   }
}

// 6) Point
// => 정수 또는 실수 허용
// => 범위: 100 ~ 10000
// => parseFloat(point)
//      -> 오류 또는 입력값이 없는 경우 NaN return
//      -> 확인 : Number.isNaN(parseFloat(point)) 
//      -> 단, 숫자로 시작하면 뒤쪽에 문자가 섞여있어도 숫자값만 사용함 ( NaN 을 return 하지않음 ) 

//

function poCheck(){
   let point=document.getElementById('point').value;
   console.log(`** parseFloat(point) => ${parseFloat(point)}`);
   console.log(`** Number.isNaN(point) => ${Number.isNaN(point)}`);
   console.log(`** Number.isNaN(parseFloat(point)) => ${Number.isNaN(parseFloat(point))}`);

   // => 숫자 아닌 값이 있는지 확인, Number.isNaN(...) 적용
   // => 단, 소수점은 허용
   if( point.replace(/[^0-9.\.]/g,'').length < point.length || Number.isNaN(parseFloat(point)) ){
      document.getElementById('oMessage').innerHTML='포인트는 정수 혹은 실수만 입력하세요! ';
      return false;
   } else if (parseFloat(point) < 100 || parseFloat(point) > 10000){
      document.getElementById('oMessage').innerHTML='100이상 10000이하만 입력 가능합니다!';
      return false;
   }else {
      document.getElementById('oMessage').innerHTML='';
      return true;
   }
}

//7 ) birthday
function bdCheck(){
   let birthday=document.getElementById('birthday').value;
   if ( birthday.length !=10 ){
      document.getElementById('bMessage').innerHTML='날짜를 정확히 입력하세요';
      return false;
   }else {
      document.getElementById('bMessage').innerHTML='';
      return true;
   }

}
