package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.model.BoardDAO;

import mapperInterface.BoardMapper;
import pageTest.Criteria;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
//	BoardDAO dao;
	BoardMapper mapper;
	
	
	public List<BoardDTO> bPageList(Criteria cri) {
		return mapper.bPageList(cri);
	}
	
	public int totalRowsCount(Criteria cri) {
		return mapper.totalRowsCount(cri);
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
