package aop03;

import java.util.Random;

public class Girl implements Programmer{

	
	// ** Test 를 위해 늘 실패 처리 
	//	=> 항상 true 가 되도록
	@Override
	public void doStudying() throws Exception{
		System.out.println("**********************Girl**************************");
		System.out.println();
		
		System.out.println("열심히 게시판을 만듭니다 => 핵심적 관심사항 ");
		if( true ) {
			// 실패
			System.out.println();
			throw new Exception("~~~Error 500 *100개 => 예외발생");
		} 
	}//class
	
}
