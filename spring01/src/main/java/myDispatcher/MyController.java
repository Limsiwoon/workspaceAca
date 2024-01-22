package myDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
// 인터페이스 컨트롤러 

public interface MyController {
	
	public String handleRequest(HttpServletRequest request, HttpServletResponse response);


} // MyController
