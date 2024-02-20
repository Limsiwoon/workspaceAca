package aop04;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAspect {
	
	
	// ** Before
	// 1) 핵심적 관심사항의 매개변수 처리가능 
	// => 핵심적 관심사항을 실행할 필요가 없으므로 JoinPoint Type 을 사용함  
	public void myBefore(JoinPoint joinPoint) {
		System.out.println("프로젝트 관제를 합니다~ => Before");
		
		// pointcut 의 인자값 확인 가능
		Object[] args = joinPoint.getArgs();
		for (Object o :args) {
			System.out.printf(" *Before, pointcut 의 인자값 => "+o);
		}//for
	}//myBefore
	
	// ** After returning : 핵심기능의 정상 종료 
	// => 핵심적 관심사항 정상 종료후 결과 return 
	// => 이 결과를 매개변수로 전달 받으며 이에 대한 처리가 가능
	// => 전달받을 매개변수 : xml에서 mapping -> returning="result"   
	   
	// Test 1. 전달된 Return 값  사용가능함 
	// Test 2. class 의 main 실행시에는 전달된 return 값 출력됨 확인
	
	
	public void myAfter_returning(Object result) {
		System.out.println("~200 : OK '회원가입 혹은 게시글 등록 완료' After returning => 핵심기능의 정상 종료 ");
		System.out.println(" *Before, pointcut 의 인자값 => "+result);
		result += "return Value Change";
		
		// => result 는 현 메서드의 지역변수 이므로 pointcut 의 return 값에는 영향 없음. 
	}
	
	// ** After_throwing : 핵심적 관심사항(기능)의 비정상종료
	// => 핵심적 관심사항 실행도중 예외발생시 예외메시지 return 
	// => 매개변수로 예외 메시지 전달받으면 이에 대한 처리 가능
	// => 전달받을 매개변수 : xml에서 mapping -> throwing="e"
	public void myAfter_throwing(Exception e) {
		System.out.println("After_throwing => 핵심적 관심사항(기능)의 비정상종료");
		System.out.println(" *After_throwing 전달받은 Exception Message를 출력 => "+e.toString());
		
	}
	
	// ** After : 정상 / 비정상 관계없이 무조건 시행
	public void myAfter() {
		System.out.println("After => 정상 / 비정상 관계없이 무조건 시행");
	}
	
	
	/*
	 * public void myAround(ProceedingJoinPoint joinpoint) {
	 * System.out.println("********MyAspect**********************************");
	 * 
	 * try { // ** 핵심 기능 수행 (pointCut 전달) // ** Around 메서드의 매개변수(ProceedingJoinPoint
	 * Type)를 통해 전달 받아 수행 joinpoint.proceed(); // => Throwable 로 예외처리를 해야함 //
	 * Throwable - Exception - RuntimeException (UnChecked), IOException (Checked)
	 * // - Error
	 * 
	 * 
	 * // ** 핵심적 관심사항의 정상종료 : After_returnning System.out.println();
	 * System.out.println("~200 OK: 획원가입 혹은 글 등록이 잘 됩니다~ => 핵심적 관심사항 정상 종료!"); }
	 * catch (Throwable e) { // ** 핵심적 관심사항의 비정상종료 : After_throwing
	 * System.out.println("**밤 새워 수정 합니다. **"); // RuntimeException 아래 작성하면 죽은 코드가 됨
	 * // => 발생된 예외를 Throwable로 처리(처리&종료) 했으므로 main까지 전달되지않음 (확인후 Test) // => main으로
	 * 전달하려면 예외발생시켜주면됨. // throw new Exception(e) ; // Exception 은 Checked ->
	 * try~catch 처리 해야함
	 * 
	 * //비정상 종료 throw new RuntimeException(e);
	 * 
	 * } finally { System.out.println();
	 * System.out.println("** finally: 무조건 제출합니다~ 안내면 맴매합니다~ "); }
	 * 
	 * }
	 */
	
	
}
