package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

import pageTest.SearchCriteria;

public interface BoardService {
	
	
	//board Check List
	public List<BoardDTO> bCheckList(SearchCriteria cri);
	public int bCheckRowsCount(SearchCriteria cri);
	
	// 버전 1 : Criteria 사용
	// 버전 2 : SearchCriteria 사용
	public List<BoardDTO> bPageList(SearchCriteria cri);
	public int totalRowsCount(SearchCriteria cri);
	
	
	public List<BoardDTO> selectList();
	
	public BoardDTO selectOne(int seq) ;
	
	public int boardInsert(BoardDTO dto) ;
	
	public int rinsert(BoardDTO dto);
	
	public int update(BoardDTO dto) ;
	
//	public int delete(int seq) ;
	public int delete(BoardDTO dto) ;
	
}
