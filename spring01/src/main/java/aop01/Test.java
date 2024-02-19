package aop01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {
	// ** IOC/DI 적용
    // => 스프링컨테이너 생성
    // => 필요한 Bean 을 주입받는다
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext sc = new GenericXmlApplicationContext("aop01.xml");
		
		Programmer programmerB=(Programmer)sc.getBean("boy");
		Programmer programmerG=(Programmer)sc.getBean("girl");
				
		
		programmerB.doStudying();
		programmerG.doStudying();
		sc.close();
	
	
	
	
	}//main

}
