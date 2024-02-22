package com.ncs.green.test;

import com.ncs.green.domain.UserDTO;
import com.ncs.green.service.DTOService;

public class DTOServiceTest {
	public static void main(String[] args) {
		
		UserDTO dto = new UserDTO();
		dto.setId("banana");
		dto.setName("홍길동");
		dto.setLoginTime("2023/02/22 AM 10:04:04");
		
		System.out.println(" ** 직접 출력 => "+dto);
		
		DTOService service = new DTOService();
		service.setUserDTO(dto);
		System.out.println(" ** AntBuild Test **" );
		System.out.println(" ** DTOService => "+ service.getUserDTO());
	}
}