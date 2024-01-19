package jdbc02;
//StudentController가 실행시키는 것이기 때문에 main 필요 

import java.util.List;

//** Controller
//=> 요청 : 요청분석 -> 담당 Service -> Service 는 DAO 
//=> 결과 : DAO -> Service -> Controller
//=> View : Controller -> View 담당 (Java:Console // Web:JSP, Html.., React) 


public class StudentController {
	
	//* 전역 변수 
	StudentService service = new StudentService();

	//** view 역할 메서드
	// selectList
	// 출력할 타입을 매개변수로 전달받아야함. 
	public void printList( List<StudentDTO> list) {
	
		System.out.println(" ** Student List ** ");
		// 출력 자료의 존재 확인

		if(list!=null) {
			// ** List 출력
			for(StudentDTO s :list) {
				//toString()을 재정의 해두었기 때문에 s만 작성하더라도, toString() 을 호출함. 
				// 즉. StudentDTO 타입으로 s로 작성함으로써 List를를 각각 읽어 오는 것. 따라서  s 내부에 toString이 작성되어 있어, toString()을 호출하는 결과를 가져옴
				System.out.println( s );
			}
			
		} else {
			System.out.println(" ** SelectList 결과가 1건도 없음 ** ");
		}
		
	}
	
	//printOne
	
	public void printOne( StudentDTO dto ) {
		System.out.println( " ** printOne **");
		
		if( dto != null ) {
			System.out.println( dto );
		} else {
			System.out.println(" ** SelectOne 결과가 1건도 없음 ** ");
		}
		
	}
	
	// insert
	public void printInsert( int sno ) {
		System.out.println( " ** insert **");
		
		if( sno > 0 ) {
			System.out.println( "** insert 성공 ** " );
		} else {
			System.out.println(" ** insert 결과가 실패 ** ");
		}

	}
	// update
	public void printUpdate( int sno ) {
		System.out.println( " ** update ** ");
		
		if( sno > 0 ) {
			System.out.println( " ** update 성공 ** " );
		} else {
			System.out.println(" ** update 결과 실패 ** ");
		}

	}
	
	
	//
	public void printDelete( int sno ) {
		System.out.println( " ** Delete ** ");
		
		if( sno > 0 ) {
			System.out.println( " ** Delete 성공 ** " );
		} else {
			System.out.println(" ** Delete 결과 실패 ** ");
		}

	}
	
	public static void main(String[] args) {
		
		StudentController studentC = new StudentController();
		// selectList 
		// 요청에 해당하는 Service의 메서드 호출 -> 객체의 값을 편리하게 확인하기 위해 사용
		studentC.printList( studentC.service.selectList() );
//		studentC.printOne( studentC.service.selectOne(10) );
//		studentC.printInsert( studentC.service.insert( new StudentDTO("잉숭",30,7,"테스트잉숭") ) );
//		studentC.printUpdate( studentC.service.update( new StudentDTO("또띠아",20,7,"또띠아변경테스트",21) ) );
//		studentC.printDelete( studentC.service.delete(22) );
		
		// **join test
//		studentC.printList( studentC.service.joinList() );
		
		// **Transaction Test 
//		studentC.service.transactionTest() ;
		
	} //main
}
