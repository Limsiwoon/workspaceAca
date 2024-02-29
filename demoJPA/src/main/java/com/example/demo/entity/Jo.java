package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jo") // 테이블 맵핑
@Data
@AllArgsConstructor // 생성자
@NoArgsConstructor
@Builder
public class Jo {

	@Id
	private int jno;

	private String jname;

	@Transient // 실제로 테이블에는 없으나, join으로 캡틴 이름을 작성했었음
	private String cname;

	private String captain;
	private String project;
	private String slogan;

}
