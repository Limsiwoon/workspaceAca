package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//** WebMvcConfigurer
//=> 스프링의 자동설정에 원하는 설정을 추가 설정할수있는 메서드들을 제공하는 인터페이스. 
//=> 스프링부트 컨트롤러 매핑메서드에서는 "/" 무시됨 -> addViewControllers 메서드로 해결  

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/").setViewName("redirect:/home");
		// => setViewName("home") 을 사용하는 경우
		//    "forward:/WEB-INF/views/home.jsp"_serverTime출력안됨 , "redirect:/home"
		// => 단, @RestController 설정한 RTestController 에 
		//	  @GetMapping("/") 정의한 매핑메서드가 있으면 우선적용됨 (충돌은 없음)   
	} //addViewControllers
	
	// React Project CORS *******************************************
	private final long MAX_AGE_SECS = 3600; // 단위:초

	// ** CORS 방침 설정
	// => CORS(Cross-Origin Resource Sharing) : 교차(다른) 출처 리소스 공유 
	// => Origin: Protocol, Host, 포트번호를 합친것으로 서버를 찾아가기위한 가장기본적인 주소
	// => 요청헤더에는 이요청의 Origin이 담겨있고 서버는 이를 확인해 자신의 Origin과 다르면 이요청을 거절함 (403) 
	//    그러므로 서버에서 이를 허용하는 방침을 설정해야함.
	
	// ** [Spring Boot] CORS 해결 방법 3가지  (https://wonit.tistory.com/572 )
	// => Filter, @CrossOrigin, WebMvcConfigurer
	
	// => 방법1 설정 
	//    아래 addCorsMappings(...) 메서드를 이용해서 CORS를 적용할 URL패턴을 정의할 수 있음 
	// => https://dev.exd0tpy.xyz/49 
	
	// => 방법2 Controller 또는 메소드단에서 annotation을 통해 적용 
	//    @CrossOrigin(origins = "*", allowedHeaders = "*")
	
	// => 방법3 Filter
	// 	  커스텀필터(CorsFilter) 를 만들어 직접 response에 header를 넣어주기
	//	- Filter 인터페이스를 구현하여 doFilter 메서드 Override
	//	- @Component 에너테이션 추가 
	//	- Filter 는 꼭 javax.servlet 의 Filter를 사용함.

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// 모든 경로에 대해
		registry.addMapping("/**")
						// Origin이 http:localhost:3000에 대해
						.allowedOrigins("http://localhost:3000")
						// GET, POST, PUT, PATCH, DELETE, OPTIONS 메서드를 허용한다.
						.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
						.allowedHeaders("*")
						.allowCredentials(true)
						.maxAge(MAX_AGE_SECS);
	}
	
	
} //class
