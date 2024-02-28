package com.example.demo.entity;

// 데이터를 입력하는 시간, 수정하는 시간, 업데이트 하는 시간을 작성하는 entity

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.Getter;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



//** BaseEntity
//=> 자료 등록시간, 수정시간 등 자동으로 추가되고 변경되는 값들을 자동으로 처리하기위한 BaseEntity 클래스 
//=> 추상클래스로 작성     
//=> @MappedSuperclass: 테이블로 생성되지않음
//=> @EntityListeners : 엔티티객체의 변화를 감지하는 리스너설정 (AuditingEntityListener.class 가 담당)
// AuditingEntityListener 를 활성화 시키기 위해서는 
//  DemoJpaApplication.java 에 @EnableJpaAuditing 설정추가해야함.

@MappedSuperclass
@EntityListeners(value= {AuditingEntityListener.class})
@Getter 
public class BaseEntity {
	
	// 등록
	@CreatedDate
	@Column(name="regdate", updatable=false)
	private LocalDateTime regDate;
	
	// 수정
	@LastModifiedDate
	@Column(name="moddate")
	private LocalDateTime modDate;

}
