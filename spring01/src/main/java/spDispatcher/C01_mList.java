package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;


//스프링이 제공해주는 컨트롤러를 사용해야함 . 
public class C01_mList implements Controller{
	
	@Autowired
	MemberService service;
	
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
