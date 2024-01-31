package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

public interface BoardService {
	
	public List<BoardDTO> selectList();
	
	public BoardDTO selectOne(int seq) ;
	
	public int boardInsert(BoardDTO dto) ;
	
	public int rinsert(BoardDTO dto);
	
	public int update(BoardDTO dto) ;
	
//	public int delete(int seq) ;
	public int delete(BoardDTO dto) ;
	
}
