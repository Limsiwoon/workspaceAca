package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout")
public class C02_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public C02_Logout() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청분석
		// => 세션 종료 
		HttpSession session = request.getSession();
		session.invalidate();
		
		//3. View (Response) : Forward
		String uri = "home.jsp";
		response.sendRedirect(uri);
		
	}

	
}
