package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.model.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO dao;
	
	@Override
	public List<BoardDTO> selectList() {
		
		return dao.selectList();
	}

	@Override
	public BoardDTO selectOne(int seq) {
		return dao.selectOne(seq);
	}

	@Override
	public int boardInsert(BoardDTO dto) {
		return dao.boardinsert(dto);
	}
	
	@Override
	public int rinsert(BoardDTO dto) {
		// TODO Auto-generated method stub
		return dao.rinsert(dto);
	}

	@Override
	public int update(BoardDTO dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		return dao.delete(seq);
	}
	
}
