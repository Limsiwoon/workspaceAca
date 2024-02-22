package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//컴포넌트 생성하는 것을 여기서 하는 것이라고 함. 이렇게 하면 에너테이션을 달지 않아도 알아서 작동. 
//bean을 만들기 위한 xml 대신 democonfig에서 함. 

@Configuration
// 설정파일로 인식하도록 설정
// Spring 애플리케이션에서 사용될 빈(Bean)을 정의하는 구성 클래스
//@Configuration 어노테이션은 이 클래스가 Spring의 구성 요소임
public class DemoConfig {
// => 일반적인 Bean 설정용
	
	@Bean
	//Spring 컨테이너에 의해 관리되는 빈을 생성한다는 것 
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
		//즉,  Spring 애플리케이션에서 비밀번호 암호화를 위한 PasswordEncoder 빈을 생성
	}
	
}
