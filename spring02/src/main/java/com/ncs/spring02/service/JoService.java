package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.model.JoDAO;

@Service
public class JoService {
	
	@Autowired
	JoDAO dao;
	
	public List<JoDTO> joSelectList(){
		return dao.joSelectList();
	}
	
	public JoDTO joSelectOne(int jno){
		return dao.joSelectOne(jno);
	}
	
	public int joupdate(JoDTO jdto) {
		return dao.joupdate(jdto);
	}
	
	public int joinsert(JoDTO jdto) {
		return dao.joinsert(jdto);
	}
	public int joDelete(int jno) {
		return dao.joDelete(jno);
	}
	
}
