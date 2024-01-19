package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberDTO;
import service.MemberService;


@WebServlet("/mdelete")
public class C06_mDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public C06_mDelete() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request 의 Parameter 처리 
		// => 성공 : mdetail ( memberDetial.jsp )
		// => 실패 : 재수정 유도 ( updateForm.jsp )
		// => 출력 객체 (info) 필요함
				//=> redirect 또는 전달된 값들을 info에 저장. 
		MemberService service = new MemberService();
		// 받아야 할 친구 id 를 가져옴. 
		String id = (String)request.getSession().getAttribute("loginID");
		String uri = "home.jsp";
		
		if( service.delete(id) > 0 ) {
			// => 성공
			System.out.println("딜리트가 되었지렁");
			request.getSession().invalidate();
			response.sendRedirect(uri);
		} 
		
		// 세션안에 있는 친구들을 삭제함. 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
