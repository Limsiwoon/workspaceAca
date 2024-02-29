package com.example.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

public interface MemberService {
	// 1) JPARepository Method 규약
	// => Jno 별 Member 출력하기
	List<Member> findByJno(int jno);

	// 2)
	// 2.1) JPQL(Java Persistence Query Language)
	void updatePassword(@Param("id") String id, @Param("password") String password);

	// 2.2) Native_query 사용
	// void updatePassword2(@Param("id") String id, @Param("password") String
	// password);

	// ** Join
	List<MemberDTO> findMemberJoin();

	// selectList
	List<Member> selectList();

	// selectOne
	Member selectOne(String id);

	// insert + update
	Member save(Member entity);

	// password Update
	Member pwUpdate(Member entity);

	// delete
	void deleteById(String id);

}