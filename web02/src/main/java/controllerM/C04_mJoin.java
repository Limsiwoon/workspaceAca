package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;


@WebServlet("/mjoin")
//요청을 받아 doget메서드를 실행하기 위해 서블릿이 필요함. 
public class C04_mJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public C04_mJoin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request 의 Parameter 처리 
		// => 성공 : 로그인 유도 ( loginForm.jsp )
		// => 실패 : 재가입 유도 ( joinForm.jsp )
		String uri = "member/loginForm.jsp";
		request.setCharacterEncoding("UTF-8");
		
		MemberDTO dto = new MemberDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setPassword(request.getParameter("password"));
		dto.setName(request.getParameter("name"));
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setJno(Integer.parseInt(request.getParameter("jno")));
		dto.setInfo(request.getParameter("info"));
		dto.setPoint(Integer.parseInt(request.getParameter("point")));
		dto.setBirthday(request.getParameter("birthday"));
		dto.setRid(request.getParameter("rid"));
		//2. 서비스 처리 
		MemberService service = new MemberService();
		if( service.insert(dto) > 0 ) {
			// => 성공
			request.setAttribute("message", "회원가입 성공하셨습니다. 로그인해주세요!");
		} else {
			// => 실패
			
			request.setAttribute("message", "회원가입 실패하셨습니다. 다시 가입 해주세요!");
			uri ="member/joinForm.jsp";
		} 
		//3. View (Response) :Forward
		request.getRequestDispatcher(uri).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
