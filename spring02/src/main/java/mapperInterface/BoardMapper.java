package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

import pageTest.Criteria;

public interface BoardMapper {
	
	public List<BoardDTO> bPageList(Criteria cri);
	
	public int totalRowsCount(Criteria cri);
	
	public List<BoardDTO> selectList();
	
	public BoardDTO selectOne(int seq) ;
	
	public int boardInsert(BoardDTO dto) ;
	
	public int rinsert(BoardDTO dto);
	
	public int stepUpdate(BoardDTO dto) ;
	
	public int update(BoardDTO dto) ;
	
//	public int delete(int seq) ;
	public int delete(BoardDTO dto) ;
	
}
