package j01_basic;

import jdbc02.JoDTO;

class Store {
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

class StoreG<T> { // 확정되지 않은 타입을 T 지정하여 사용하는 클래스 .
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

} // StoreG

class GenArray<T> { // 확정되지 않은 타입을 T 지정하여 사용하는 클래스 .
	private T[] arr;

	public T[] getData() {
		return arr;
	}

	public void setData(T[] arr) {
		this.arr = arr;
	}

	public void arrayPrint() {
		for (T a : arr) {
			System.out.println(a);
		} // for
	} // arrayPrint

} // StoreG

//============================================내장 객체의 상속을 받는 타입

class Box<T extends Number>{
    private T data;
    public void setData(T data) { this.data = data; }
    public T getData() { return this.data; }
}

//=======================================================================================
//=======================================================================================
//=======================================================================================

public class Gn01_StoreTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jjaja = "짜장면";
		// 1.
		Store s1 = new Store();
		s1.setData("짜장면");
		s1.setData(123); // int <=> Integer : 자동형변환
		s1.setData(123.456); // double <=> Double
		s1.setData(123.456f);
		s1.setData(123456789L);
		s1.setData(new JoDTO(7, "Banana", 77, "화이팅", "Generic Test"));

		System.out.println(" ** Test 1 =>  ** " + s1.getData());

		// ** 단점 TEST
//		String s = (String)s1.getData();
//		System.out.println(" ** 단점 TEST  ** " + s ); //여러가지 코드를 제약 없이 사용하도록 함.  하지만 실행되었을 땐 오류 발생

		// 이러한 점을 보완하여 제네릭 형태로 만드는 것. (원하는 타입으로 제한 하여 사용)
		// 2. Generic StoreG
		
		
		StoreG g1 = new StoreG(); // < > 생략시 오브젝트와 동일하게 됨
		StoreG<String> g2 = new StoreG<String>();
		g2.setData("스트링만 가능");
//		g2.setData(12345);   string 타입 아니기 때문에 오류가 남. 

		StoreG<Integer> g3 = new StoreG<Integer>();
		g3.setData(12345);
//		g3.setData(123.456); 타입 불일치 

		// GenArray Test ========================================
		// 1) String
		String[] ss = { "가", "나", "Do", "Re", "Mi" };
		GenArray<String> ga1 = new GenArray<String>();
		ga1.setData(ss);
		ga1.arrayPrint();

		// 2) Integer
		Integer[] ii = { 1, 2, 3, 4, 5 };
		GenArray<Integer> ga2 = new GenArray<Integer>();
		ga2.setData(ii);
		ga2.arrayPrint();

		// 3) Character
		Character[] cc = { 'A', 'a', 'B', '다', '여' };
		GenArray<Character> ga3 = new GenArray<Character>();
		ga3.setData(cc);
		ga3.arrayPrint();

		// 4) 객체
		JoDTO[] jj = { new JoDTO(), new JoDTO(), new JoDTO() };
		GenArray<JoDTO> ga4 = new GenArray<JoDTO>();
		ga4.setData(jj);
		ga4.arrayPrint();

		// 제네릭 ? 타입
		StoreG<?> ggt = new StoreG<>();
		Object obj = ggt.getClass(); // 읽기는 가능
		// ggt.set(new SomeType()); // 에러! 쓰기는 불가능

		StoreG<? extends JoDTO> d1 = new StoreG();
		
		
		//=================== 제네릭 클래스의 타입 인자 제한
		
		Box<Integer> b1 = new Box();
//		Box<Long> b2 = new Box();
//		Box<Double> b3 = new Box();
		b1.setData(123456);
//		b1.setData(123.456); 넘버로 상속 받아서 인티저 타입으로 제한함. 
		
		
		
	}// main

	
}// Gn01_StoreTest, Class

//------------------------------------------------------------
//** Generic
//=> 컬렉션(Collection:자료구조) 을 보강해준 기능
//=> 컴파일 단계에서 객체의 자료형을 선언(정의) 해주면
// 다른 타입의 자료가 저장될수 없도록 해주는 기능

//** Generic 클래스 정의
//=> 클래스 이름 옆에 <> 사이에 알파벳 1글자를 사용하여 
// Generic Type 명을 선언해줌 
// ex : <T> 처럼 "<" 와 ">" 사이에 선언 
//=> 대문자로 T, E 등을 많이 사용 
// Type 의미로 "T" 를 주로 사용
//=> Generic 타입으로 클래스를 사용한다는 의미 
//=> 제네릭으로 기본 자료형(int, float, double....)은 사용할 수 없지만
// 기본자료형의 wrapper 클래스를 이용할 수있다. 

//** Generic 타입제한 (사용시, Wildcards_와일드카드타입 이용으로)
//=> <?>
// Unbounded Wildcards (제한없음_모든 클래스나 인터페이스 타입 가능)
//=> <? extends 클래스명>
// Upper Bounded Wildcards (상위클래스 제한_같거나 하위 타입 가능)
//=> <? super 클래스명>
// Lower Bounded Wildcards (하위클래스 제한_ 같거나 상위타입 가능)

//=> 정의할때: <T> , <T extends 클래스명> , <T super 클래스명>
//------------------------------------------------------------
