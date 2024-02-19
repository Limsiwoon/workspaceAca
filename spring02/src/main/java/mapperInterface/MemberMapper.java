package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ncs.spring02.domain.MemberDTO;

import pageTest.SearchCriteria;


public interface MemberMapper {
	// ** JUnit Test
	//	=> selectDTO, xml 대신 @으로 sql 구형
	@Select("select * from member where id=#{id}")
	MemberDTO selectDTO(MemberDTO dto);
	//작서하면 dto에 id가 있음으로 사용 가능
	@Select("select * from member where id=#{ii} and jno=#{jno}")
	MemberDTO selectParam(@Param("ii") String id, @Param("jno") int jno);
	
	
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
