package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BoardDTO;

import mapperInterface.BoardMapper;
import pageTest.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
//	BoardDAO dao;
	BoardMapper mapper;
	
	//board Check List
	
	public List<BoardDTO> bCheckList(SearchCriteria cri){
		return mapper.bCheckList(cri);
	}
		
	public int bCheckRowsCount(SearchCriteria cri) {
		return mapper.bCheckRowsCount(cri);
	}
	
	// 버전 1 : Criteria 사용
	// 버전 2 : SearchCriteria 사용
	public List<BoardDTO> bPageList(SearchCriteria cri) {
//		return mapper.bPageList(cri); 버전 1
		return mapper.bSearchList(cri); // 버전 2 
	}
	
	public int totalRowsCount(SearchCriteria cri) {
//		return mapper.totalRowsCount(cri);
		return mapper.bSearchRowsCount(cri);
	}
	
	
	@Override
	public List<BoardDTO> selectList() {
		
		return mapper.selectList();
	}

	@Override
	public BoardDTO selectOne(int seq) {
		return mapper.selectOne(seq);
	}

	@Override
	public int boardInsert(BoardDTO dto) {
		return mapper.boardInsert(dto);
	}
	
	// 답글 등록 
	// => rinsert, stepUpdate
	
	@Override
	public int rinsert(BoardDTO dto) {
		if(mapper.rinsert(dto)>0) {
			System.out.println("**stepUpdate count => " +mapper.stepUpdate(dto) );
			return 1 ;
		} else return 0;
		//return mapper.rinsert(dto);
	}

	@Override
	public int update(BoardDTO dto) {
		return mapper.update(dto);
	}

	@Override
	public int delete(BoardDTO dto) {
		return mapper.delete(dto);
	}
	
}
