package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;

import pageTest.SearchCriteria;

public interface MemberService {

	//board Check List
	public List<MemberDTO> mCheckList(SearchCriteria cri);
	public int mCheckRowsCount(SearchCriteria cri);
	
	
	
	//pageMaker 함수 필요 
	public List<MemberDTO> mPageList(SearchCriteria cri);
	public int mTotalRowsCount(SearchCriteria cri);

	
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