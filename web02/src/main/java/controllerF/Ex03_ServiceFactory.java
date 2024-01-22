package controllerF;

import java.util.HashMap;
import java.util.Map;

//** ServiceFactory
//=> FrontController 가 요청한 서비스 컨트롤러 클래스를 제공

//** Map 적용
//=> HashMap 객체 정의 : 전역변수 (맴버)
//=> 자료 put : 생성자에 정의

//** 싱글톤 패턴 : 인스턴스를 단 1개만 허용
//=> 일반적인 경우 : 복수의 인스턴스 가능 
// Student s1 = new Student();
// Student s2 = new Student();
//=> 클래스 외부에서 인스턴스를 생성할 수 없도록 제한  

//** 방법
//=> 생성자를 private 으로 내부에서만 사용가능하도록하고
//=> 내부에서 getInstance() 메서드로 생성 제공해줌 
//=> 외부에서는 getInstance() 메서드를 통해서만 사용

public class Ex03_ServiceFactory {
	// 1 )
	// 여기서 키 값은 String 으로 "mList.fo", "mDetail.fo" 을 말하고
	// value값은 두 클래스를 모두 받을 수 있는 인터페이스인 Ex04_Conteroller로 받음
	private Map<String, Ex04_Controller> mappings;
	
	// 2 ) 생성자 정의 (싱글톤 패턴)
	//   -> 생성자를 private 으로 정의
	//   -> 내부에서 인스턴스 생성
	//   -> 인스턴스를 제공하는 getter
	private Ex03_ServiceFactory() {
		mappings = new HashMap <String, Ex04_Controller>();
		mappings.put("/mlist.fo",new Ex05_mList());
		mappings.put("/mdetail.fo",new Ex05_mDetail());
	}//생성자
	
	//내부에서 생성하여 공급 : 인스턴스 라는 애에게 Ex03_ServiceFactory를 생성
	private static Ex03_ServiceFactory instance = new Ex03_ServiceFactory();
	public static Ex03_ServiceFactory getInstance() { return instance; }
	// 둘의 공통 분모인 인터페이스를 반환함. 
	public Ex04_Controller getController(String mappingName) {
		//1 ) 1 단계 
//		if("mlist.fo".equals(mappingName)) {
//			return new Ex05_mList();
//		} else if(("mdetail.fo".equals(mappingName)) {
//			return new Ex05_mDetail();
//		} else return null;
		
		// 2 ) **Map적용
		return mappings.get(mappingName);
		
	}//getController
}
