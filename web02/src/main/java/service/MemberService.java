package service;

import java.util.List;
import domain.MemberDTO;
import model.MemberDAO;

public class MemberService {
	MemberDAO dao =new MemberDAO();

	public List<MemberDTO> selectList() {
		return dao.selectList();
	}

	// selectOne
	public MemberDTO selectOne(String id) {
		return dao.selectOne(id);
	}

	// insert
	public int insert(MemberDTO dto) {
		return dao.insert(dto);
	}

	// update
	public int update(MemberDTO dto) {
		return dao.update(dto);
	}

	public int delete(String id) {
		return dao.delete(id);
	}
}
