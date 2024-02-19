package aop01;

import java.util.Random;

public class Girl implements Programmer{

	@Override
	public void doStudying() {
		System.out.println("**********************Girl**************************");
		System.out.println("프로젝트 과제를 합니다 => 준비 작업에 해당함 = Before");
		
		try {
			System.out.println();
			System.out.println("열심히 게시판을 만듭니다 => 핵심적 관심사항 ");
			if( new Random().nextBoolean() ) {
				// 실패
				System.out.println();
				throw new Exception("~~~Error 500 *100개 => 예외발생");
			} else {
				// 성공
				System.out.println();
				System.out.println("~~게시판이 잘됩니다~~~ => 핵심적 관심사항 정상종료");
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println();
			System.out.println(" ** Girl Class Exception => "+e.toString() );
			System.out.println("밤을 새워 수정을 합니다. 잠은 못자요~ ");
		}finally {
			System.out.println();
			System.out.println(" **finally : 무조건 제출합니다. 안하면 맴매 ");
		}//try
		
	}//class
	
}
