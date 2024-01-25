package com.ncs.spring02.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.MemberService;


//** IOC/DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=>  @Controller :사용자 요청을 제어하는 Controller 클래스
//     		  DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌.
//=> 			  interface Controller 의 구현 의무가 없어짐.
//=>			  ** 이로인해 메서드 hndleRequest() 의 오버라이딩 의무 없어짐
//=>			  ** 이로인해 메서드명, 매개변수, 리턴타입에 자유로워짐.
//=>			  ** 그리고 클래스 와 메서드 단위로 맵핑해주는 @RequestMapping 가능하게 해줌
//=> 			  ** 그러므로 하나의 컨트롤러 클래스에 여러 개의 매핑메서드의 구현이 가능해짐
//=>			  ** 그래서  주로 테이블(엔티티) 단위로 작성함. 			


//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//       DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가


@Controller
@RequestMapping(value = "/member") //member로 시작하는 경로는 이쪽으로 연결 => MemberController에서는 member 사용 X
public class MemberController {
	
	@Autowired(required = false)
	MemberService service;
	
	
//~~~~~~~~~~~~~~~~~~~~~~~loginForm~~~~~~~~~~~~~~~	
	/*=> ver01 :return String
	 * @RequestMapping(value={"/loginForm"}, method = RequestMethod.GET) // HTTP GET요청이 "/mlistsp" 경로로 들어왔을 때 특정 메서드를 실행하도록 지정하는 것 
	 * public String loginForm(Model model) { return "member/loginForm"; }
	*/
	
	/*=> ver02 :return void
	  => virename 이 생략 : 요청명과 동일한 viewName 을 찾음. 
	  => /WEB-INF/views/member/loginForm.jsp 완성 */
	@RequestMapping(value="/loginForm", method = RequestMethod.GET) // HTTP GET
	public void loginForm() { 
	}
//~~~~~~~~~~~~~~~~~~~~~~login~~~~~~~~~~~~
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, Model model, MemberDTO dto) {
		
		// => mapping메서드의 인자와 동일한 컴럼명의 값은 자동으로 할당되어, 아래와 같이 request.getParameter를 작성하지 않아도됨. 
		//String id = request.getParameter("id");
		//String password = request.getParameter("password");
		//매개변수로 MemberDTO주게 되면, MemberDTO 에 id,password 가 존재하면 자동으로 담아줌.  
		
	      // 1. 요청분석
	      // => requst 로 전달되는 id, password 처리: 
	      //    매서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
	      //   ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌 )
	      // => 전달된 password 보관 (입력된 것)
	      String password = dto.getPassword();
	      String uri = "redirect:/home";
	      
	      // 2. 서비스 & 결과 처리
	      // => id 확인 
	      // => 존재하면 Password 확인
	      // => 성공: id, name은 session에 보관, home 으로
	      // => 실패: 재로그인 유도
		dto = service.selectOne( dto.getId() );
		if(dto != null && dto.getPassword().equals(password)) {
			// 성공
			session.setAttribute("loginID",dto.getId());
			session.setAttribute("loginName",dto.getName());
		} else {
			// 실패
			uri = "member/loginForm";
			model.addAttribute("message","오류났따! <br>로그인 다시해줘~ 마스크 벗겨야지<br>");
		}
		return uri;
	}
//~~~~~~~~~~~~~~~~~~logout~~~~~~~~~~~~~~~
	@RequestMapping(value = "/logout" , method=RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/home" ;
	}
	
//	~~~~~~~~~~~~~~~~~~~~~~~~~Member detail~~~~~~~~~~~
	//=> 단일 Parameter 의 경우 @RequestParam("   ") 활용
	   //    String jCode = request.getParameter("jCode") 과 동일
	   //     단, 해당하는 Parameter 가 없으면 400 오류 발생
	   //    그러므로 detail 요청에도 ?jCode=D 를 추가함. 
	@RequestMapping(value={"/detail"}, method = RequestMethod.GET) //  HTTP GET 요청이 "/mlistsp" 경로로 들어왔을 때 특정 메서드를 실행하도록 지정하는 것
	// => String jCode = @RequestParam("jCode") 과 동일 
	public String detail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
		// 수정할 때 사용해야 하기 때문에 void 보다는 String 타입으로 반환. 
		//1. 요청 분석
		//	=> id : session 에서 get
		//  => detail 또는 수정 Page 출력을 위한 요청인지 jCode 로 구별 
		String id =(String)session.getAttribute("loginID");
		String uri ="member/memberDetail";
		//=> update 요청 확인 후 uri 수정
		if( "U".equals(jCode) ){
			uri="member/updateForm";
		}	
		//2. service &결과 처리 
		model.addAttribute("info",service.selectOne(id));
		return uri;
	}
//~~~~~~~~~~~~~~~~~~~~~~joinForm~~~~~~~~~~
	@RequestMapping(value="/joinForm", method = RequestMethod.GET) // HTTP GET요청이 "/mlistsp" 경로로 들어왔을 때 특정 메서드를 실행하도록 지정하는 것 
	public void joinForm() { 
	}
	
	// **Join
	@RequestMapping(value="/join", method = RequestMethod.GET)
	public String join(Model model,MemberDTO dto) { 
		//1. 요청분석
		// => 이전 : 한글처리 , request 값 -> dto dp set
		// => 스프링 : 한글은 filter, request 처리는 매개변수로 자동화
		String uri = "member/loginForm"; // 성공시 
		
		// 2. Service & 결과 
		if( service.insert(dto) > 0 ) {
			model.addAttribute("message","이제 마스크를 벗길 기회가 생겼습니다.<br> 벗기시러가시져<br>");
		} else {
			uri = "member/joinForm";
			model.addAttribute("message", "잘못 입력하셨습니다. <br>마스크를 벗길 기회를 획득하시겠습니까?<br>");
			
		}
		return uri;
	} //join
	
//~~~~~~~~~~~~~~~~~update~~~~~~~~~~~~~~~~~~~~~~~~~~
	@RequestMapping(value="/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model,MemberDTO dto) { 
		//1. 요청분석
		// => 성공시 memberDetail, 실패 : updateForm
		// => 두 경우 모두 출력하려면, dto 객체의 값("info") 가 필요하므로 보관
		
		String uri = "member/memberDetail"; // 성공시 
		model.addAttribute("info", dto);
		
		// 2. Service & 결과 
		if( service.update(dto) > 0 ) {
			model.addAttribute("message","내 정보 수정하면서, 사진도 바꿨어?<br>");
			// => name을 수정할 수도 있으므로 loginName 을 수정해준다. 
			session.setAttribute("loginName", dto.getName() );
		} else {
			uri = "member/updateForm";
			model.addAttribute("message", "수정 안됐는데, 뭘하려던거야?<br> ");	
		}
		return uri;
	}
//~~~~~~~~~~~~~~~~~~delete~~~~~~~~~~~~~~~~~~~~~~~~~
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public String delete(HttpSession session, Model model,MemberDTO dto) { 
		//1. 요청분석
		// => id ; session 에서 get
		// => delete & session 처리 
		String id = (String)session.getAttribute("loginID");
		String uri = "/home";
		
		// 2. Service & 결과처리 
		if( service.delete(id) > 0 ) {
			model.addAttribute("message","모야 마스크 벗기니까 지겨워진고야? <br>우리 1달은 보지말쟈.<br>");
			session.invalidate();
		} else {
			model.addAttribute("message", "탈퇴 실패 햇어. <br>마스크 놀이 재밋잖아 가지마.<br>") ;
		}
		return uri;
	}
//~~~~~~~~~~~~~~~~~~~mList~~~~~~~~~~~~~~~~~~~~~~~~~
	@RequestMapping(value="/memberList", method = RequestMethod.GET) //  HTTP GET 요청이 "/mlistsp" 경로로 들어왔을 때 특정 메서드를 실행하도록 지정하는 것
	public void mList(Model model) {
		model.addAttribute("banana",service.selectList());
		//return "member/memberList";
	}
	
}
