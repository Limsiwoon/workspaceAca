package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Jo;
import com.example.demo.repository.JoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoServiceImpl implements JoService {

	private final JoRepository repository;

	@Override
	public List<Jo> selectList() {
		return repository.findAll();
	}

	@Override
	public Jo selectOne(int jno) {
		Optional<Jo> result = repository.findById(jno);
		if (result.isPresent())
			return result.get(); // return의 그 값을 받아오는 것.
		else
			return null;
	}

	@Override
	public Jo save(Jo entity) {
		return repository.save(entity);
	}

	@Override
	public void delete(int jno) {
		repository.deleteById(jno);
	}

}
