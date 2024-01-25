package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;

//** IOC/DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=>  @Controller :사용자 요청을 제어하는 Controller 클래스
//       		  DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌.
//=> 			  interface Controller 의 구현 의무가 없어짐.
//=>			  ** 이로인해 메서드 hndleRequest() 의 오버라이딩 의무 없어짐
//=>			  ** 이로인해 메서드명, 매개변수, 리턴타입에 자유로워짐.
//=>			  ** 그리고 클래스 와 메서드 단위로 맵핑해주는 @RequestMapping 가능하게 해줌
//=> 			  ** 그러므로 하나의 컨트롤러 클래스에 여러 개의 매핑메서드의 구현이 가능해짐
//=>			  ** 그래서  주로 테이블(엔티티) 단위로 작성함. 			
//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//         DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가


//@Component // MemberDAO 에 @Repository로 인해 작성 X 
public class C01_mList implements Controller{
//스프링이 제공해주는 컨트롤러를 사용해야함 . 
	
	@Autowired
	MemberService service;
	// IOC/DI 적용, 자동주입, 이미 생성되어 있어야함. 
	
	@Override
	// 리턴 타입을 ModelAndView 받아줘야함. 
	// 리턴 값이 다르기 때문에, 변경해야함. 
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
			// Member List
			ModelAndView mv = new ModelAndView();
			mv.addObject("banana",service.selectList());
			mv.setViewName("member/memberList");
			return mv;
	}
	
}
