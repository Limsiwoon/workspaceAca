package jdbc01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// ** 순서
// 1) 전역변수 정의 
// 2) CRUD 기능 메서드 
// 3) main 에서 사용


public class DBStart {
	
	//DBStart라는 클래스 내부에서만 사용 할 것이기 때문에 private 으로 작성
	private static Connection cn = DBConnection.getConnection(); //초기화
	//
	private static Statement st;
	//PreparedStatement 처리 => 구문을 작성하는데 불편함을 줄여주기 위한 것. ? 으로 작성함. 
	private static PreparedStatement pst;
	//결과값 처리를 위한 변수
	private static ResultSet rs;
	// sql구문을 담당하는 string 타입을
	private static String sql;
	
	// ** StudentList
	// => mysql Command
	// => login -> DB선택 -> sql 구문 실행 -> 결과 
	// => JDBC
	// => Connection 객체 생성 -> sql 구문 : Statement 또는 PreparedStatement
	// => 결과 : ResultSet 에 전달 됨 
	
	
	
	public static void selectList() {
		//모든 DB과정은 TRY CATCH에 작성 되어야함
		// sql 내부 쿼리문은 모두 string으로 작성되어야함
		sql="select * from student";
		try {
			//추상메서드임 확인 가능
			st=cn.createStatement();
			rs=st.executeQuery(sql); // 이렇게 까지하면 테이블이 등록 됨. 
			
			System.out.println("=====================StudentList=======================");
			System.out.println("sno   |  name  | age  | jno |          info          |   point");
			System.out.println("=======================================================");
			
			
			// ** 결과 출력
			//ResultSet 객체는 “결과 존재 확인”을 위한 메서드 제공
			//⇒ next() : 다음에 Data가 존재하면 true, 현재Data를 제공
			if(rs.next()) {
				// 데이타가 존재하는 경우
				// 처음 if 에서 이미 데이타 있음을 확인 했기 때문에, 실행 한 후 작성. 
				do {
					
					//getInt 혹은 String도 가능(columnLabel) => 열명
					//getInt(columnIndex) => 열의 n번째 (sno 같은경우 1) 0인 경우, rownum
					
					System.out.print(rs.getInt(1)+ " ");
					System.out.print(rs.getString("name")+ " ");
					System.out.print(rs.getInt(3)+ " ");
					System.out.print(rs.getInt(4)+ " ");
					System.out.print(rs.getString(5)+ " ");
					System.out.print(rs.getDouble(6)+ "\n");
					
				} while( rs.next() );
				
			} else {
				// 출력할 데이타가 전혀 없음(1건도 없음)을 의미 
				System.out.println("** 출력할 데이타가 전혀 없음 **");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("** selectList Exception => **" + e.toString() );
		}//try
	
	} //selectList
	
	// ** 조별 List1
	// => preparedStatement 활용
	
	public static void joList(int jno) {
		sql="select * from student where jno=" + jno;
		
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql);
			
			if(rs.next()) {
				do {
					System.out.print(rs.getInt(1)+ " ");
					System.out.print(rs.getString("name")+ " ");
					System.out.print(rs.getInt(3)+ " ");
					System.out.print(rs.getInt(4)+ " ");
					System.out.print(rs.getString(5)+ " ");
					System.out.print(rs.getDouble(6)+ "\n");
	
				} while(rs.next());
			} else {
				//데이터 존재 X 
				System.out.println("** 출력할 데이타가 전혀 없음 **");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" ** joList Exception => " + e.toString() );
		}//try
	}// joList
	
	// ** insert (문제점)
	// => 입력에 필요한 컴ㄹ럼을 모두 매개변수로 전달 받아야함
	//	  많으면 처리 불편 => 객체화 
	//	  테이블 자체를 객체화 함( 엔티티) => JAVA Class 로 객체화 = ~DTO, VO, Entity(JPA에서 함)
	//	  엔티티를 처리하기 위해서 어트리뷰트 설정(하나의 행) => 
	// => sql 구문을 완성하기 위해서 문자열 연산을 작성해야함 
	// ( 예 , insert into student(name,age,jno,info) values ('홍길동', 30, 10, '이걸 하기엔 힘드눼') 
	//		 " insert into student(name,age,jno,info) values ( '" + name + "," + age +"," ....)
	// =>  java.sql.SQLSyntaxErrorException 오류 작성됨
	// => 이러한 점을 보완하기 위해 제공된 객체가 PreparedStatemet 임. 
	// => 변수의 위치에  ?(바인딩 변수)를 활용
	// insert into student(name,age,jno,info) values (?,?,?,?) 로 대체 가능함. 
	// 단, ? 에 대한 값을 각각 대응하는 java코드로 처리 = PreparedStatemet의 메서드로 활용
	
	
	// ** 조별 List2
	// => preparedStatement 활용
	
	public static void joListPS(int jno) {
		sql="select * from student where jno= ? " ;
		
		try {
//			st=cn.createStatement();
//			rs=st.executeQuery(sql);
			
			pst = cn.prepareStatement(sql); //sql을 실행 전에 , ? 에 대한 값을 준비하라고 작성하는 것. 
			pst.setInt(1, jno); //parameterIndex
			rs=pst.executeQuery(); // sql을 이미 위에서 작성하기 때문에 따로 인자를 sql로 작성할 이유가 없음
			
			if(rs.next()) {
				do {
					System.out.print(rs.getInt(1)+ " ");
					System.out.print(rs.getString("name")+ " ");
					System.out.print(rs.getInt(3)+ " ");
					System.out.print(rs.getInt(4)+ " ");
					System.out.print(rs.getString(5)+ " ");
					System.out.print(rs.getDouble(6)+ "\n");
	
				} while(rs.next()); 
				
			} else {
				//데이터 존재 X 
				System.out.println("** 출력할 데이타가 전혀 없음 **");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" ** joList Exception => " + e.toString() );
		}//try => DAO 
	}// joList
	
	
	public static void insert(String name, int age, int jno, String info) {
		sql="insert into student(name,age,jno,info) values (?,?,?,?)";
		
		try {
			
			pst=cn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, age);
			pst.setInt(3, jno);
			pst.setString(4, info);
			
			//insert,update,delete 등 작성하는 것은 DML 영역 => executeQuery 사용X => executeUpdate
//			int cnt = pst.executeUpdate();
//			if(cnt==1) System.out.println("** insert 성공 => " + cnt );
//			else System.out.println("** insert 실패 => " + cnt);
			
			//위 혹은 아래처럼 사용 가능함
			if( pst.executeUpdate()>0 ) System.out.println("** insert 성공 => " );
			else System.out.println("** insert 실패 => " );
			
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println(" ** joList Exception => " + e.toString() );
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1) connection 확인
		//System.out.println("** connection 확인 -> " + cn); 작성했을 때, cn 뒤에는 .toString()을 요약하고 작성한 것임 
		// 즉, 출력문에서 인스턴스명을 사용하면, toString( )를 호출하는 것임. 
		System.out.println("** connection 확인 -> " + cn);
		// 2) Student List
//		selectList();
		// 3) 조별 List 출력
		joList(3);
		
//		joListPS(3);
	}//main

} //class


//MVC
//디자인 패턴 중 하나. 
//Model , View , Controller 역할로 나눠지게 됨. 
