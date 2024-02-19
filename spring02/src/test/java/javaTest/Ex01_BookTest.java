package javaTest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

//**Book Class
//	=> 맴버필드 3개(author,title,price) 정의, 이들을 모두 초기화 하는 생성자를 만듦.
//	=> 접근 범위 = default 

class Book{

	String author;
	String title;
	int price;
	//생성자 
	Book(String author,String title,int price ){
		this.author = author;
		this.title = author;
		this.price = price;
	}
	
	public boolean isBook(boolean b) {return b;}
	
} //class Book

//** 테스트 레벨 4단계
//=> 단위테스트 -> 통합테스트 -> 시스템테스트 -> 인수테스트

//** 테스트 주도 개발 (Test-Driven Development , TDD)
//=> JUnit 활용
// Java 개발시 가장 많이 이용되는 단위테스트 프레임
// 오픈 소스 형태로 개발되며 계속 업그레이드 되고 있음.
// JUnit4 부터 에너테이션 적용 ( Java 가 5 부터 언어적 개선이 이루어짐에 따른 변화임 )

//** @ 종류
//=> @Before - @Test - @After
// -> 하나의 클래스에서 @ 들을 반복사용하면 오류는 안나지만, 앞쪽 @이 실행됨
//=> @ 적용 테스트 메서드 : non static, void 로 정의 해야 함.

//** org.junit.Assert 가 지원하는 다양한 Test용 Method 
//1) assertEquals(a,b) : a와 b의 값(Value) 이 같은지 확인
//2) assertSame(a,b) : 객체 a와b가 같은 객체임(같은 주소) 을 확인
//3) assertTrue(a) : a가 참인지 확인
//4) assertNotNull(a) : a객체가 Null 이 아님을 확인
//5) assertArrayEquals(a,b) : 배열 a와b가 일치함을 확인

//=> 자동 import 가 안되는경우
// -> 프로젝트 우클릭 -> Build Path -> Configure Build Path.. 
//       -> Libraries -> Add Library  -> JUnit5
// -> @Test: import org.junit.Test 확인

//=> pom.xml
// -> junit version : 4.12 로 수정
// -> dependency 추가 ( Spring MVC Mybatis Test )
public class Ex01_BookTest {
	//메서드 별로 실행 가능함. 
	
	//1) assertEquals(a,b) : a와 b의 값(Value) 이 같은지 확인
	public void equalsTest() {
		Book b1 = new Book("임시운","9900원은 밥도사먹기 힘든데",9900);
		
		//assertEquals(b1.author,"겨루");
		assertEquals(b1.author,"임시운");
		// => 값의 일치성 확인 -> true: green 라인 / false: red 라인
		// => 둘을 비교하여 맞는지 아닌지 확인 가능
	}
	
	
	//2) assertSame(a,b) : 객체 a와b가 같은 객체임(같은 주소) 을 확인
	
	public void sameTest() {
		Book b1 = new Book("임시운","9900원은 밥도사먹기 힘든데",9900);
		Book b2 = new Book("임시운","9900원은 밥도사먹기 힘든데",9900);
		Book b3 = new Book("홍길동","서자는 아니야",19900);
	
		//assertSame(b1,b2); //false 같은 주소를 가리키고 있는 것이 아니기 때문에 다른 객체임
		b3=b1;
		assertSame(b1,b3);
	}
	
	//3) assertTrue(a) : a가 참인지 확인
	//@Test
	public void trueTest() {
		Book b1= new Book("임시운","9900원은 밥도사먹기 힘든데",9900);
		
		//assertTrue(b1.isBook(false));
		assertTrue(b1.isBook(true));
	}
	
	
	//4) assertNotNull(a) : a객체가 Null 이 아님을 확인
	//@Test
	public void nullTest() {
		Book b1;
		// => 인스턴스를 정의만 하고 생성은 하지않음
	    //    지역변수는 초기화 하지 않으며 오류, 사용시 오류발생
		Book b2 = new Book("임시운","9900원은 밥도사먹기 힘든데",9900);
		
		//assertNotNull(b1); //Null이기 때문에 ->false
		assertNotNull(b2);
	}
	
	
	//5) assertArrayEquals(a,b) : 배열 a와b가 일치함을 확인
	//@Test
	public void arrEqualsTest() {
		String[] ar1={"임","시","운"};
		String[] ar2={"임","시","운"};
		String[] ar3={"운","시","임"};
		String[] ar4={"김","수","옥"};
		
		// => 두 배열의 순서, 값, 모두 동일 && 주소값은 다름 =true
		assertArrayEquals(ar1,ar2);
	    // => 두배열의 순서는 다르고, 값은 모두 동일=false
		assertArrayEquals(ar1,ar3);
	    // => 모두 다른경우=false
		assertArrayEquals(ar1,ar4);
		// => Book Type 의 배열을 2개 만들고 비교하기 
		// => 각 배열의 Data 는 3개씩
		
		Book b1 = new Book("임시운","9900원은 밥도사먹기 힘든데",9900);
		Book b2 = new Book("임시운","밥도사먹기 힘든데",9900);
		Book b3 = new Book("홍길동","서자는 아니야",19900);
		
		Book[] ba1 = {b1,b2,b3};
		Book[] ba2 = {b1,b2,new Book("홍길동","서자는 아니야",19900)};
		Book[] ba3 = {b1,b2,b3};
		
		assertArrayEquals(ba1,ba2); // false
		assertArrayEquals(ba1,ba3); // true
		
	}
	
	
}
