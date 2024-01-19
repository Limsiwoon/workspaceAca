package jdbc02;

import java.util.List;

// **service 
// controller 요청에 해당하는 DAO의 메서드를 실행 
// controller 와 DAO 사이에 위치하면서 , 간접적으로 연결 될 수 있도록 함 즉, 이 둘의 의존성을 낮춰줌
// => 의존성 : 필요성. controller에서 수행하려면 StudentDTO가 필요함 이를 의존성이라고 함. 
// DAO는 DB를 구성하기 때문에 요청자와 직접적으로 연결되어 있어서, 영향을 적게 받는 것이 좋음. 
public class StudentService {
	
	// **전역변수 정의
	StudentDAO dao = new StudentDAO();
	
	// **selectList
	public List<StudentDTO> selectList() {
		return dao.selectList();

	}// selectList

	// selectOne 
	public StudentDTO selectOne(int sno) {
		return dao.selectOne(sno);
	}
	
	// insert
	public int insert(StudentDTO dto) {
		return dao.insert(dto);
	}
	// update
	public int update(StudentDTO dto) {
		return dao.update(dto);
	}
	// delete
	//StudentDTO로 줄경우, 결국 super타입으 변수를 받는 것과 동일 하기 때문에 , 어느 값이 들어와도 가능
	public int delete(int sno) {
		return dao.delete(sno);
	}
	
	
	public List<StudentDTO> joinList() {
		return dao.joinList();
	}
	//
	public void transactionTest() {
		dao.transactionTest();
	}
	
} // StudentService
