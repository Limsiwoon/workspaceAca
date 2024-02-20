package aop04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//** JoinPoint  
//=> 핵심적 관심사항으로 들어가는 모든 데이터 (before를 통해) 사항을
//  가지고 있으며 처리할 수 있도록 해줌

//1) 인자
//=> JoinPoint의 getArgs() 메서드
// 핵심관심사항의 인자(매개변수) 의 목록을 배열의 형태로 제공함.
// Before  메서드 에서 사용가능함.
//2) return 값 처리
//=> myAfter_returning 메서드에 매개변수로 전달되어 사용 가능.


public class Test {
	// ** IOC/DI 적용
    // => 스프링컨테이너 생성
    // => 필요한 Bean 을 주입받는다
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext sc = new GenericXmlApplicationContext("aop04.xml");
		
		Programmer programmerB=(Programmer)sc.getBean("boy");
		Programmer programmerG=(Programmer)sc.getBean("girl");
				
	      try {
	          System.out.println("** Boy Test **\n");
	          System.out.println("** Boy Test 결과 => "+programmerB.doStudying(20));
	          System.out.println();
	         
	          System.out.println("\n** Girl Test **\n");
	          System.out.println("\n** Girl Test 결과 => "+ programmerG.doStudying(30));
	          System.out.println();
	          
	       } catch (Exception e) {
	          System.out.println("\n** main Exception => "+e.toString());
	       }
	       sc.close();
		
	
	
	
	}//main

}
