package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

public interface MemberMapper {
	List<MemberDTO> selectList();

	// selectOne
	MemberDTO selectOne(String id);

	List<MemberDTO> selectOne2(int jno);

	// insert
	int insert(MemberDTO dto);

	// update
	int update(MemberDTO dto);
	
	int pwUpdate(MemberDTO dto);

	int delete(String id);

}
