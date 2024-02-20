package javaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.annotations.Select;
import org.junit.Test;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

//** DAO Test 시나리오
//=> Detail 정확성 
// -> Test Data
// -> 정확한 id 를 사용하면 not null : Green_Line
// -> 없는 id 를 사용하면 null : Red_Line

//=> Insert 정확성
// -> 입력 가능한 Data 적용 : 1 return : Green_Line
// -> 입력 불가능한 Data 적용 : 0 return : Red_Line
public class Ex03_DAOTest {
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = new MemberDTO();

	// 1) Detail  정확성
	//@Test
	public void detailTest() {
		
		String id="banana"; 
		assertNotNull(dao.selectOne(id));
		System.out.println("** dto => " +dto );
		
	}

	// 2) Insert 정확성
	//@Test
	public void insertTest() {
		dto.setId("junit");
		dto.setPassword("12345!");
		dto.setName("junit");
		dto.setAge(20);
		dto.setJno(7);
		dto.setInfo("JUnit Test");
		dto.setPoint(500.0);
		dto.setBirthday("2000-01-01");
		dto.setRid("lsw1");
		
		assertEquals(dao.insert(dto), 1);
		//dao.insert(dto) 에서 입력 되는 경우, 처음에만 입력되고 2번째부터는 같은 값의 데이터가 있음으로 같지 않아짐.
	}
}
