package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.BoardDTO;

import pageTest.SearchCriteria;

public interface BoardMapper {
	
	// ** ajax : id별 board List(idbList)
	@Select("select * from board where id=#{id}")
	public List<BoardDTO> idbList(String id);
	
	
	//board Check List
	public List<BoardDTO> bCheckList(SearchCriteria cri);
	public int bCheckRowsCount(SearchCriteria cri);
	
	// 버전 1 : Criteria 사용
	// 버전 2 : SearchCriteria 사용
	public List<BoardDTO> bSearchList(SearchCriteria cri);
	public int bSearchRowsCount(SearchCriteria cri);
	
	
	
	public List<BoardDTO> selectList();
	public BoardDTO selectOne(int seq) ;
	
	public int boardInsert(BoardDTO dto) ;
	
	public int rinsert(BoardDTO dto);
	
	public int stepUpdate(BoardDTO dto) ;
	
	public int update(BoardDTO dto) ;
	
//	public int delete(int seq) ;
	public int delete(BoardDTO dto) ;
	
}
