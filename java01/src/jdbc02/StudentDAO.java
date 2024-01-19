package jdbc02;
// 실제로 실행되는 것이 아니기 때문에 main 메서드는 작성하지 않음 

// 데이터 베이스에 접속해서 데이터 추가, 삭제, 수정 등의 작업을 하는 클래스 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc01.DBConnection;

import java.util.PriorityQueue;


//** DAO(Data Access Object)
//=> SQL 구문 처리
//=> CRUD 구현 
// Create(Insert), Read(selectList, selectOne), Update, Delete

// **  첫번째 예제 DBStart 와 ~~~DAO와 차이점
// 결과를 직접 처리 하지 않고ㅡ 제공해야 함. 
// 즉, 메서드 역할 별로 처리 결과를 리턴해주어야 함. 
// 그러므로 특히 Select 결과를 잘 전달하기위해 결과를 객체화해야함. 

public class StudentDAO {

	// 1) 전역변수 정의
	private static Connection cn = DBConnection.getConnection(); // 초기화
	
	private static Statement st; 
	private static PreparedStatement pst; // PreparedStatement 처리 => 구문을 작성하는데 불편함을 줄여주기 위한 것. ? 으로 작성함.
											// 하지만, ?( 바인딩 변수 ) 가 없다고 하더라도, 작성 가능
	private static ResultSet rs; // 결과값 처리를 위한 변수
	private static String sql;// sql내 쿼리문을 담당하는 string 타입으로 전달함

	// ** join Test
	// => sno, name, age, jno, jname, project, captain, 조장이름
	// JoDTO 작성, joinList, 메서드 작성( Controller,Service, DAO )
	
	public List<StudentDTO> joinList ( ) {
		sql = "Select * from Student left join jo on student.jno = jo.jno ";
		
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		
		try {
			pst=cn.prepareStatement(sql);
			rs=pst.executeQuery();
			
			if( rs.next() ) {
				do {
					
					StudentDTO dto = new StudentDTO();
					
					dto.setSno( rs.getInt(1) );
					dto.setName( rs.getString(2) );
					dto.setSno( rs.getInt(3) );		
					dto.setJno( rs.getInt(4) );		
					dto.setJname( rs.getString(5) );
					dto.setProject( rs.getString(6) );
					dto.setCaptain( rs.getInt(7) );
					
					list.add(dto);
				} while( rs.next() );
				System.out.println("** listJoin 성공 = > ** ");
				return list;
			} else {
				System.out.println("** listJoin 자료 없음! ** ");
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("** listJoin 실패 = > ** " + e.toString() );
			return null;
		}
	
	}
	
	
	
	// 2) selectList==============================================================
	public List<StudentDTO> selectList() {
		// 컬렉션 : List ( 중복이 가능함/ 순서 존재 ), map(키와 밸류가 존재 / 키가 다를 때, 중복이 가능 / 순서 개념 X) ,
		// set (중복이 불가능 / 순서가 없음 )

		sql = "select * from student";
		// 제네릭 = List<StudentDAO> 타입명 = 객체 생성 ;
		// list는 인터페스, 따라서 직접 객체 생성 X , 부모는 우측에서 작성이 안됨

		// Q.왜 arry로 작성하지 않고, list 로 작성하나요?
		// 유지보수 관리하는데 편리 하기 때문임.
		List<StudentDTO> list = new ArrayList<StudentDTO>();

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();

			// 결과 존재 여부
			// 존재 : List에 담기 // 존재 X : 종료
			if (rs.next()) {
				// 존재
				do { // 예약어 혹은 명령어

					// 1. 풀어서 쓰는 방법

//					StudentDTO dto = new StudentDTO();  // StudentDTO타입 => 김수미,30,1, 계란한판 => 한 행 전체를 말함.
//					dto.setSno(rs.getInt(1));
//					dto.setName(rs.getString(2));
//					dto.setAge(rs.getInt(3));
//					dto.setJno(rs.getInt(4));
//					dto.setInfo(rs.getString(5));
//					dto.setPoint(rs.getInt(6));

					// 2. 한 번에 작성하는 방법

					StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
							rs.getString(5), rs.getInt(6));
					list.add(dto); // list에 dto객체 주소를 추가함.

				} while (rs.next());
				return list;

			} else {
				// 존재X
				return null;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" ** SelectList Exception => " + e.toString());
			return null;
		}
	}

	// 3) selectOne===================================================================
	// 기본자료형 매개변수 _ Call By Value 형
	public StudentDTO selectOne(int sno) {
		sql = "select * from student where sno = " + sno;

		try {
//			st = cn.createStatement(); 
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {

				StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
						rs.getString(5), rs.getInt(6));

				return dto;
				
			} else {
				System.out.println(" 출력할 데이타가 전혀 없음 ");
				return null;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" ** joList Exception => " + e.toString());
			return null;
		}
	} // selectOne

	
	// 4) insert=============================================================
	public int insert(StudentDTO dto) {
		sql = "insert into student(name,age,jno,info) values (?,?,?,?)";

		try {

			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());

			if (pst.executeUpdate() > 0) {
				System.out.println("** insert 성공 => ");
				return 1;
				// return pst.executeUpdate(); //처리 갯수
			} else {
				System.out.println("** insert 실패 => ");
				return 0;
			}

		} catch (Exception e) {
			System.out.println(" ** Insert Exception => " + e.toString());
			return 0;
		}

	}

	// 5) update ===================================================================
	public int update(StudentDTO dto) {
		sql = "update student SET name =? , age = ?, jno = ?, info = ? where sno = ? ";

		try {

			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());
			pst.setInt(5, dto.getSno());

			if ( pst.executeUpdate() > 0 ) {
				System.out.println("** update 성공 => ");
				return pst.executeUpdate();
				
			} else {
				System.out.println("** update 실패 => ");
				return 0;
			}

		} catch (Exception e) {
			System.out.println(" ** update Exception => " + e.toString());
			return 0;
		}

	}

	// 6) delete
	// StudentDTO로 줄경우, 결국 super타입으 변수를 받는 것과 동일 하기 때문에 , 어느 값이 들어와도 가능
	public int delete( int sno ) {
		sql = "delete from student where sno = " + sno ;

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();

			if ( rs.next() ) {
				System.out.println("** delete 성공 => ");
				return 1;
				
			} else {

				System.out.println("** delete 실패 => ");
				return 0;
			}
		} catch (Exception e) {
			System.out.println(" ** delete Exception => ** " + e.toString());
			return 0;
			// TODO: handle exception
		}
//		return 0;
	}
	
	   // ** Transaction Test
	   // => Connection 객체가 관리
	   // => 기본값은 AutoCommit  true 임.
	   // => setAutoCommit(false) -> commit 또는 rollback 
	   // => Test 사항
	   //   - 동일자료를 2번 입력 -> 2번째 입력에서 p.key 중복 오류발생 

	   // 1) Transaction 적용전
	   // => 동일자료를 2번 입력
	   //   - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생 
	   //   - Rollback 불가능
	   //   - MySql Command 로 1번째 입력 확인 가능 
	      
	   // 2) Transaction 적용후 
	   // => 동일자료를 2번 입력 
	   //   - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생
	   //   - Rollback 가능 -> 둘다 취소됨
	
	public void transactionTest() {
		sql = "insert into student values(19,'트렌후',20,7,'Transaction 적용후',100)";
		
		//1) Transaction 적용전
		try {
			pst = cn.prepareStatement(sql);
			pst.executeUpdate(); // 실행 완료
			pst.executeUpdate(); // p.key 중복 오류 발생 -> catch로 이동
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(" ** Transaction 적용전 오류 ** => " + e.toString() );
		}
		
		
		//2) Transaction 적용후 
//		try {
//			cn.setAutoCommit(false);  //start Transaction
//			
//			pst = cn.prepareStatement(sql);
//			pst.executeUpdate(); // 1번째는 입력완료 되었지만, buffer 에 입력. -> 관리 : db
//			pst.executeUpdate(); // p.key 중복 오류 발생 -> catch로 이동 -> rollback 해야함
//			
//			cn.commit();
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(" ** Transaction 적용후 오류 ** => " + e.toString() );
////			cn.rollback();  //cn자체가 checked이기 떄문에 이곳에서 사용하려면 무조건 try catch 사용해야함
//		
//			try {
//				cn.rollback();
//				System.out.println(" ** rollback 완료 ** " );
//				
//			} catch (Exception e2) {
//				// TODO: handle exception
//				System.out.println(" **  rollback 오류 ** => " + e.toString() );
//				
//			}
//		}
	}
	
}
