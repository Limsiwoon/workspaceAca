package aop02;


import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {
	
	public void myAround(ProceedingJoinPoint joinpoint) {
		System.out.println("********MyAspect**********************************");
	
		try {
			// ** 핵심 기능 수행 (pointCut 전달)
			// ** Around 메서드의 매개변수(ProceedingJoinPoint Type)를 통해 전달 받아 수행
			joinpoint.proceed();
	         // => Throwable 로 예외처리를 해야함
	         //    Throwable - Exception - RuntimeException (UnChecked), IOException (Checked)
	         //              - Error 
			
			
			// ** 핵심적 관심사항의 정상종료 : After_returnning
			System.out.println();
			System.out.println("~200 OK: 획원가입 혹은 글 등록이 잘 됩니다~ => 핵심적 관심사항 정상 종료!");
		} catch (Throwable e) {
	         // ** 핵심적 관심사항의 비정상종료 : After_throwing
			System.out.println("**밤 새워 수정 합니다. **"); // RuntimeException 아래 작성하면 죽은 코드가 됨
	         // => 발생된 예외를 Throwable로 처리(처리&종료) 했으므로 main까지 전달되지않음 (확인후 Test)
	         // => main으로 전달하려면 예외발생시켜주면됨.
	         // throw new Exception(e) ;  // Exception 은 Checked -> try~catch 처리 해야함 
			
			//비정상 종료 
			throw new RuntimeException(e);
			
		} finally {
			System.out.println();
			System.out.println("** finally: 무조건 제출합니다~ 안내면 맴매합니다~ ");
		}
	
	}
	
	
}
