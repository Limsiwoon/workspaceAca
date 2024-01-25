package com.ncs.spring02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JoDTO {

	private int jno;
	private String jname;
	private String captain;
	private String project;
	private String slogan;

}
