package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;


@WebServlet("/mupdate")
public class C05_mUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public C05_mUpdate() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => request 의 Parameter 처리 
		// => 성공 : mdetail ( memberDetial.jsp )
		// => 실패 : 재수정 유도 ( updateForm.jsp )
		// => 출력 객체 (info) 필요함
				//=> redirect 또는 전달된 값들을 info에 저장. 
		String uri = "member/memberDetail.jsp";
		request.setCharacterEncoding("UTF-8");
		
		MemberDTO dto = new MemberDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setPassword(request.getParameter("password"));
		dto.setName(request.getParameter("name"));
		dto.setAge(Integer.parseInt(request.getParameter("age")));
		dto.setJno(Integer.parseInt(request.getParameter("jno")));
		dto.setInfo(request.getParameter("info"));
		dto.setPoint(Double.parseDouble(request.getParameter("point")));
		dto.setBirthday(request.getParameter("birthday"));
		dto.setRid(request.getParameter("rid"));
		
		// => 결과 출력을 위해 전달된 값들을 apple에 저장
		request.setAttribute("info", dto);
		
		
		//2. 서비스 처리 
		MemberService service = new MemberService();
		if( service.update(dto) > 0 ) {
			// => 성공
			request.getSession().setAttribute("loginName", dto.getName() );
			request.setAttribute("message", "수정 완료 했습니다! ");
		} else {
			// => 실패
			
			request.setAttribute("message", "수정 실패하셨습니다. 다시 수정 해주세요!");
			uri ="member/updateForm.jsp";
		} 
		//3. View (Response) :Forward
		request.getRequestDispatcher(uri).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
