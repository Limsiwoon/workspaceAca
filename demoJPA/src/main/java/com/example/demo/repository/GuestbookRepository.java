package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Guestbook;

public interface GuestbookRepository 
							extends JpaRepository<Guestbook, Long>{
	//JpaRepository 필요한 것만 쓸 수 있도록 계층적으로 되어 있음
	
}
