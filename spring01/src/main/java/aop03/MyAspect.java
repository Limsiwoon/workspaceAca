package aop03;


import org.aspectj.lang.ProceedingJoinPoint;

//** 횡적(공통)관심사항 ( cross concerns => Aspect ) 구현
//=> Boy, Girl : 핵심 관심사항 (core concerns) 만 구현하면 됨.
//=> 횡적(공통)관심사항 과 핵심관심사항 의 연결 방법 xml, @ 방식

//** xml 방식의 공통적 관심 사항 구현 2.
//=> pointcut : 매개변수, return 값 없음  
//=> Around 메서드 1개로 구현 
// Before, After_returning, After_throwing, After 
// 을 1개의 메서드에서 try~catch~finally 를 이용해서 처리

//=> Around 메서드 특징
// -> Aspect 와 Pointcut의 모든 Joinpoint를 아우르는 연결고리 
// -> 반드시 ProceedingJoinPoint 타입을 인자로 사용하여 
//    pointcut을 처리 (그렇지 않으면 오류)
//    joinPoint.proceed(); 로 핵심적 관심사항을 처리함

//** ProceedingJoinPoint
//=> JoinPoint 를 상속 했으며 proceed() 메서드를 가짐
//=> JoinPoint (I) -> ProceedingJoinPoint (I) -> JoinPointImpl
//=> 예외상황 처리시에  Throwable 사용해야됨.
// 계층도 : Object -> Throwable -> Error, Exception 
//                -> RuntimeException(unChecked) / IOException(Checked)

//** JoinPoint 
//=> PointCut 을 지원해주는 클래스 (다양한 지원 메서드를 가지고 있음-> 매개변수 전달 등.. )
//=> 핵심적 관심사항으로 들어가는 모든 데이터 (before를 통해) 사항을
// 가지고 있으며 처리할 수 있도록 해줌

//** pom.xml 설정 
//1) AspectJ  
//2) AspectJ Weaver : AOP 실습시에 반드시 추가 해줄것 
//=> weaver가 AOP에서 advice를 핵심기능에 적용하는 설정 파일  


//** xml 방식의 공통적 관심 사항 구현 2.
//=> pointcut : 매개변수, return 값 없음  
//=> 개별 advice 메서드를 구현 
//  Before, After_returning, After_throwing, After 

//** 용어정리
//Target: 핵심사항(Core concerns) 가 구현된 객체 : Boy, Girl
//JoinPoint: 클라이언트가 호출하는 모든 비즈니스 메서드    
//       ( 공통관심사항이 적용될수있는 지점, ex:메소드 호출시, 객체생성시 등 )
//Pointcut: JoinPoint 중 실제 공통적 관심사항이 적용될 대상 : doStudying()  
//Advice : 공통관심사항(Cross-Cutting) 구현 코드 + 적용시점
//     : 시점별 메서드들 ( myBefore() .... )
//     : 적용시점 (핵심로직 실행 전, 후, 정상 종료 후, 비정상 종료 후 등 )
//Aspect : Advice + Pointcut

public class MyAspect {
	
	
	// ** Before
	public void myBefore() {
		System.out.println("프로젝트 관제를 합니다~ => Before");
	}
	
	// ** After returning : 핵심기능의 정상 종료 
	public void myAfter_returning() {
		System.out.println("After returning => 핵심기능의 정상 종료 ");
		
	}
	
	// ** After_throwing : 핵심적 관심사항(기능)의 비정상종료
	public void myAfter_throwing() {
		System.out.println("After_throwing => 핵심적 관심사항(기능)의 비정상종료");
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
