package com.ncs.spring02.model;
// 실제로 실행되는 것이 아니기 때문에 main 메서드는 작성하지 않음 

// 데이터 베이스에 접속해서 데이터 추가, 삭제, 수정 등의 작업을 하는 클래스 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.tomcat.util.log.UserDataHelper.Mode;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ncs.spring02.domain.MemberDTO;




//@Component
@Repository
public class MemberDAO {

	// 1) 전역변수 정의
	private static Connection cn = DBConnection.getConnection(); // 초기화
	private static Statement st; 
	private static PreparedStatement pst; // PreparedStatement 처리 => 구문을 작성하는데 불편함을 줄여주기 위한 것. ? 으로 작성함.											// 하지만, ?( 바인딩 변수 ) 가 없다고 하더라도, 작성 가능
	private static ResultSet rs; // 결과값 처리를 위한 변수
	private static String sql;// sql내 쿼리문을 담당하는 string 타입으로 전달함

	// 2) selectList==============================================================
	public List<MemberDTO> selectList() {
		// 컬렉션 : List ( 중복이 가능함/ 순서 존재 ), map(키와 밸류가 존재 / 키가 다를 때, 중복이 가능 / 순서 개념 X) ,
		// set (중복이 불가능 / 순서가 없음 )

		sql = "select * from member";
		// 제네릭 = List<StudentDAO> 타입명 = 객체 생성 ;
		// list는 인터페스, 따라서 직접 객체 생성 X , 부모는 우측에서 작성이 안됨

		// Q.왜 arry로 작성하지 않고, list 로 작성하나요?
		// 유지보수 관리하는데 편리 하기 때문임.
		List<MemberDTO> list = new ArrayList<MemberDTO>();

		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			// 결과 존재 여부
			if (rs.next()) {
				do { // 예약어 혹은 명령어
					MemberDTO dto = new MemberDTO();  
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setAge(rs.getInt(4));
					dto.setJno(rs.getInt(5));
					dto.setInfo(rs.getString(6));
					dto.setPoint(rs.getDouble(7));
					dto.setBirthday(rs.getString(8));
					dto.setRid(rs.getString(9));
					dto.setUploadfile(rs.getString(10));

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

	public MemberDTO selectOne(String id) {
		sql = "select * from member where id = ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAge(rs.getInt(4));
				dto.setJno(rs.getInt(5));
				dto.setInfo(rs.getString(6));
				dto.setPoint(rs.getDouble(7));
				dto.setBirthday(rs.getString(8));
				dto.setRid(rs.getString(9));
				dto.setUploadfile(rs.getString(10));
				
				System.out.println(dto);
				return dto;
				
			} else {
				System.out.println(" 출력할 데이타가 전혀 없음 ");
				return null;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(" ** selectOne Exception => " + e.toString());
			return null;
		}
	} // selectOne

	//~~~~~~~~~~~~~~selectOne2

	public List<MemberDTO> selectOne2(int jno) {
		sql = "select * from member where jno = ?";
		List<MemberDTO> list = new ArrayList<MemberDTO>();

		try {
			
			pst = cn.prepareStatement(sql);
			pst.setInt(1,jno);
			rs = pst.executeQuery();
			
			// 결과 존재 여부
			if (rs.next()) {
				do { // 예약어 혹은 명령어
					MemberDTO dto = new MemberDTO();  
					dto.setId(rs.getString(1));
					dto.setPassword(rs.getString(2));
					dto.setName(rs.getString(3));
					dto.setAge(rs.getInt(4));
					dto.setJno(rs.getInt(5));
					dto.setInfo(rs.getString(6));
					dto.setPoint(rs.getDouble(7));
					dto.setBirthday(rs.getString(8));
					dto.setRid(rs.getString(9));

					list.add(dto); // list에 dto객체 주소를 추가함.
				} while (rs.next());
				System.out.println(list);
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
	} // selectOne2
	
	
	
	// 4) insert=============================================================
	// => 모든 컬럼 입력 
	public int insert(MemberDTO dto) {
		sql = "insert into member values (?,?,?,?,?,?,?,?,?,?)";

		try {
			pst = cn.prepareStatement(sql); //? 를 편리하게 변환.
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getPassword());
			pst.setString(3, dto.getName());
			pst.setInt(4, dto.getAge());
			pst.setInt(5, dto.getJno());
			pst.setString(6, dto.getInfo());
			pst.setDouble(7, dto.getPoint());
			pst.setString(8, dto.getBirthday());
			pst.setString(9, dto.getRid());
			pst.setString(10, dto.getUploadfile());
			return pst.executeUpdate(); //처리 갯수
		} catch (Exception e) {
			System.out.println(" ** Insert Exception => " + e.toString());
			return 0;
		}

	}

	// 5) update ===================================================================
	// id(P.key) 제외한 모든 컬럼 수정. 
	public int update(MemberDTO dto) {
		sql = "update member SET  name =? , age = ?, jno = ?, info = ?"
				+ ", point =?, birthday =?, rid =? where id = ? ";

		try {
			pst = cn.prepareStatement(sql);
			
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());
			pst.setDouble(5, dto.getPoint());
			pst.setString(6, dto.getBirthday());
			pst.setString(7, dto.getRid());
			pst.setString(8, dto.getId());
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" ** update Exception => " + e.toString());
			return 0;
		}

	}
	

	// passwordUpdate
	public int pwUpdate(MemberDTO dto) {
		sql="update member set password =? where id= ?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getPassword());
			pst.setString(2, dto.getId());
			
			return pst.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(" ** password update Exception => " + e.toString());
			return 0;
		}
	}

	// 6) delete
	// MemberDTO로 줄경우, 결국 super타입으 변수를 받는 것과 동일 하기 때문에 , 어느 값이 들어와도 가능
	public int delete( String id ) {
		sql = "delete from member where id = ?" ;

		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, id);			
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println(" ** delete Exception => ** " + e.toString());
			return 0;

		}
//		return 0;
	}
	
	
}
