package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.JoDTO;

public interface JoService {

	List<JoDTO> joSelectList();

	JoDTO joSelectOne(int jno);

	int joupdate(JoDTO jdto);

	int joinsert(JoDTO jdto);

	int joDelete(int jno);

}