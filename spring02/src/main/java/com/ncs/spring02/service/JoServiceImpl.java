package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.model.JoDAO;

import mapperInterface.JoMapper;

//** Service
//=> 요청클래스 와 DAO클래스 사이의 연결(완충지대) 역할
//=> 요청클래스(컨트롤러) 와 DAO클래스 사이에서 변경사항이 생기더라도 서로 영향   받지않도록해주는 역할
// 결합도는 낮추고, 응집도는 높인다

//** interface 자동완성 
//=> Alt + Shift + T  
//=> 또는 마우스우클릭 PopUp Menu 의  Refactor - Extract Interface...


@Service
public class JoServiceImpl implements JoService {
	
	/*
	 * @Autowired 
	 * JoDAO dao;
	 */
	
	@Autowired
	JoMapper mapper;
	
	@Override
	public List<JoDTO> joSelectList(){
		return mapper.joSelectList();
	}
	
	@Override
	public JoDTO joSelectOne(int jno){
		return mapper.joSelectOne(jno);
	}
	
	@Override
	public int joupdate(JoDTO jdto) {
		return mapper.joupdate(jdto);
	}
	
	@Override
	public int joinsert(JoDTO jdto) {
		return mapper.joinsert(jdto);
	}
	@Override
	public int joDelete(int jno) {
		return mapper.joDelete(jno);
	}
	
}
