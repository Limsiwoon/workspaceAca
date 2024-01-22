package myDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

public class C02_mDetail implements MyController{
@Override
public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
	// Member List
	MemberService service = new MemberService();
	request.setAttribute("info",service.selectOne("lsw"));
	return "member/memberDetail";
}
}
