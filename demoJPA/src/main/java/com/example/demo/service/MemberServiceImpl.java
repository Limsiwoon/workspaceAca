
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

//@Component 
@Service
@RequiredArgsConstructor // final 값을 주입 받기 위해서 작성
public class MemberServiceImpl implements MemberService { // 전역 변수 처리

	private final MemberRepository repository;

	// 1) JPARepository Method 규약
	// => Jno 별 Member 출력하기
	public List<Member> findByJno(int jno) {
		return repository.findByJno(jno);
	}

	// 2) @Query선언을 이용한 직접쿼리 선언
	// 2.1) JPQL(Java Persistence Query Language)
	public void updatePassword(@Param("id") String id, @Param("password") String password) {
		repository.updatePassword(id, password);
	}

	// ** Join
	@Override
	public List<MemberDTO> findMemberJoin() {
		return repository.findMemberJoin();
	}
	// JPA Repository 로 작성 되어 있기 때문에, 그에 맞는 CRUD를 사용해야함

	// selectList
	@Override
	public List<Member> selectList() {
		return repository.findAll();
		// repository에 갖고 있는 함수.
	}

	// selectOne
	@Override
	public Member selectOne(String id) {

		Optional<Member> result = repository.findById(id);
		if (result.isPresent())
			return result.get(); // return의 그 값을 받아오는 것.
		else
			return null;
	}

	// insert + update = save
	@Override
	public Member save(Member entity) {
		// 반환 타입이 Member인 경우, 변경되거나 추가된 Member의 정보를 가지고 올 수 있음
		return repository.save(entity);
	}

	// password Update
	public Member pwUpdate(Member entity) {
		return null;
	}

	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}

}
