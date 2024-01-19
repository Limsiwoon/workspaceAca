package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;


@WebServlet("/login")
public class C02_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public C02_Login() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청분석
		// => request 의 Parameter 처리 
		// => id, password 
		String id = request.getParameter("id");
//		String pw = request.getParameter("password");
		String uri = "home.jsp";
		//2. 서비스 처리 
		// => Service , DTO처리 
		MemberService service = new MemberService();
		// => id를 확인 ; service.selectOne(  )
		MemberDTO dto = service.selectOne(id);
		// => id가 확인 되면 password일치 확인. 
		if(dto!=null && request.getParameter("password").equals( dto.getPassword() )) {
			response.sendRedirect(uri);
			request.getSession().setAttribute("loginName", dto.getName() );
			request.getSession().setAttribute("loginID", dto.getId() );
			
		} else {
			request.setAttribute("message", "<h3>로그인 실패했어요ㅠㅠ 다시 진행주세요!</h3><br>");
			uri = "/member/loginForm.jsp";
			request.getRequestDispatcher(uri).forward(request, response);
			
		}
		// => 성공 : id 와 name 를 세션에 보관. => home으로 이동
		// => 실패 : loginForm 으로 재로그인 유도. 
		
		//3. View (Response) : Forward
	}

	
}
