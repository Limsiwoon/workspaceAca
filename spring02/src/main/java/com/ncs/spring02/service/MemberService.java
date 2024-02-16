package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

import pageTest.SearchCriteria;

public interface MemberService {

	//pageMaker 함수 필요 
	public List<MemberDTO> mPageList(SearchCriteria cri);
	public int totalRowsCount(SearchCriteria cri);

	
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