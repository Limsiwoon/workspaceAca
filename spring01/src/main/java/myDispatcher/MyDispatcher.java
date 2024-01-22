package myDispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ViewResolver;

//*** Spring MVC2_ver01
//=> MyDispatcherServlet (FrontController 역할)
//  HandlerMapping, ViewResolver 를 활용해서
//  요청분석, Service, View 를 처리

// => Url Mapping 온 web.xml 에서 처리 하기 때문에 @webServlet을 하지 않아도 됨. 
public class MyDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	// ** 전역변수 정의 
	private MyHandlerMapping hmappings;
	private MyViewResolver vresolver;
	
	// ** 멤버변수 초기화 : 생성자에서  
    public MyDispatcher() {
        super();
        hmappings = MyHandlerMapping.getinstance();
        vresolver = new MyViewResolver();
        vresolver.setPrefix("/WEB-INF/views/");
        vresolver.setSuffix(".jsp"); //확장자
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1 ) 요청 분석
		// => url 분석후 요청명 확인
		// => 한글처리
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		uri = uri.substring(uri.lastIndexOf("/")); 
		// 2 ) Service 실행
		// => MyHandlerMapping 에 요청, 해당 서비스컨트롤러의 인스턴스를 제공받음.
		// => 요청에 해당하는 서비스를 실행
		MyController controller = hmappings.getController(uri);
		/* System.out.println("2"+uri); => uri가 잘 찍힌다면, getController 에서 문제가 생김을 확인*/
		if(controller!=null) {
			uri = controller.handleRequest(request, response);
		} else {
			uri = "home";
			request.setAttribute("message", "없는 요청 입니다! ");
		}
		// 3 ) View 처리
		uri = vresolver.getViewName(uri);
		/* System.out.println(uri); => uri를 확인. 하여 잘못된 경로를 찾기 */
		request.getRequestDispatcher(uri).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
