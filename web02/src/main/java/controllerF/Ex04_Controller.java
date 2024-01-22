package controllerF;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Ex04_Controller {
	//유저의 메서드를 처리하는 인터페이스 
	//doUser 메서드는 반환 값이 uri 가 될것임 => String 타입
	public String doUser(HttpServletRequest request, HttpServletResponse response);
	
}
