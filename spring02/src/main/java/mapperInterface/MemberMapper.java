package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

import pageTest.SearchCriteria;


public interface MemberMapper {
	//bPageMaker에서 search할 함수. 
	public List<MemberDTO> mSearchList(SearchCriteria cri);
	public int mSearchRowsCount(SearchCriteria cri) ;
	
	
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
