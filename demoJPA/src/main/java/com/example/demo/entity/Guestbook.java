package com.example.demo.entity;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity///엔티티임을 알려주는 애너테이션
//@table(name="guestbook") : 클래스명과 동일한 경우 생략 가능

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Guestbook extends BaseEntity {
// BaseEntity 에 작성한 변수들을 사용 할 수 있음
	
	@Id // 프라이머리키 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long gno;// Auto_increment
	
	@Column(length=100,nullable=false) //notnull
	private String title;
	@Column(length=2000,nullable=false)
	private String content;
	@Column(length=50,nullable=false)
	private String writer;
	
	public void changeTitle(String title) {
		this.title=title;
	} // getter의 역할을 하는 것
	
	public void changeContent(String content) {
		this.content=content;
	}// getter의 역할을 하는 것
}
