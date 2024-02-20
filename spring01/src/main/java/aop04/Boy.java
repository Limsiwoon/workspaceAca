package aop04;

import java.util.Random;


/*
 * Aop 구현
1 단계 : 핵심적 관심사항 과  공통적 관심사항 분리
⇒ 핵심적 관심사항만 구현
⇒ 공통적 관심사항(Aspect) 분리 : 별도의 클래스로 분리 -> MyAspect.java*/

public class Boy implements Programmer{

	// ** Test 를 위해 늘 성공 처리 
	//	=> 항상 false 가 되도록
	@Override
	public String doStudying(int n) throws Exception {
		System.out.println("**********************Boy**************************");
		System.out.println();
		
		System.out.printf("열심히 회원관리를  %d개 만듭니다 => 핵심적 관심사항 ",n);
		if(1==2) {
			throw new Exception("~~~Error 500*100개 => 예외발생"); //코드 테스트를 위해 만든것일뿐임.
		}
		return "취업성공 연복 1억"; 
	}//class
}
