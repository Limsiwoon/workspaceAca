package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

//@Component
@Service
public class MemberServiceImpl implements MemberService {
	
	
	 @Override 
	 public int pwUpdate(MemberDTO dto) { 
		 return dao.pwUpdate(dto); 
	}
	 

	@Autowired 
	MemberDAO dao;

	@Override
	public List<MemberDTO> selectList() {
		return dao.selectList();
	}

	// selectOne
	@Override
	public MemberDTO selectOne(String id) {
		return dao.selectOne(id);
	}
	@Override
	public List<MemberDTO> selectOne2(int jno) {
		return dao.selectOne2(jno);
	}
	

	// insert
	@Override
	public int insert(MemberDTO dto) {
		return dao.insert(dto);
	}

	// update
	@Override
	public int update(MemberDTO dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(String id) {
		return dao.delete(id);
	}
}
