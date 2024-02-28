package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Member;

//localhost:8080
public interface MemberRepository extends JpaRepository<Member, String> {
	// 제네릭 타입=> T : 엔티티 전달 Member, id : primary type String
}
