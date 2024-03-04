package com.example.demo.repository;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;

// ** QueryDSL 적용

public interface MemberDSLRepository {
	// => Entity return
	List<Member> findMemberJnoDSL(int jno);

	// => Join & DTO return
	// MemberDTO 에 상속 받았음
	List<MemberDTO> findMemberJoinDSL();

	List<MemberDTO> findMemberJoinDSL2();
}
