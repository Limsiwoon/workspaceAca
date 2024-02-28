package com.example.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;
import com.example.demo.service.GuestbookService;

import lombok.AllArgsConstructor;


//-----------------------------------------------------------------
//** Locale : (사건등의 현장), 다국어 지원 설정을 지원하는 클래스
//=> locale 값을 받아서 현재 설정된 언어를 알려줌 -> 한글 메시지 출력 가능
//=> jsp 의 언어설정을 받아 해당 언어에 맞게 자동으로 message가 출력 되도록 할때 사용.
//  logger.info("Welcome home! 로그 메시지 Test -> locale is {}.", locale);
//    -> locale: ko_KR
//=> {} : 일종의 출력 포맷 으로 우측 ',' 뒷편 변수의 값이 표시됨.

//-----------------------------------------------------------------

//** Logger : 현재 위치상태를 보여줘서 에러 위치를 잡을수 있게 해 줄 수 있는 코드
//=> Log4J의 핵심 구성 요소로 6개의 로그 레벨을 가지고 있으며,
//  출력하고자 하는 로그 메시지를 Appender (log4j.xml 참고) 에게 전달한다.

//=> 활용 하려면 pom.xml 에 dependency (log4j, slf4j) 추가 (되어있음),
//=> Controller에는 아래의 코드를 넣어주고,
//=> 확인이 필요한 위치에서 원하는 메세지 출력, Sysout 은 (I/O 발생으로) 성능 저하 유발
//  현재 클래스 내에서만 사용가능하도록 logger 인스턴스 선언 & 생성

//** Log4J : Log for Java(Apache Open Source Log Library)의 준말
//=> 자바기반 로깅 유틸리티로 디버깅용 도구로 주로 사용됨.
// 로그를 남기는 가장 쉬운 방법은 System.out.println("로그 메세지")이지만
// 프로그램 개발 완료 후 불필요한 구문은 삭제해야 하며 성능 저하 요인이 됨.
// Log4J 라이브러리는 멀티스레드 환경에서도 성능에 전혀 영향을 미치지 않으면서
// 안전하게 로그를 기록할 수 있는 환경을 제공함.

//=> 로깅레벨 단계
// TRACE > DEBUG > INFO > WARN > ERROR > FATAL(치명적인)
// TRACE: Debug보다 좀더 상세한 정보를 나타냄
// DEBUG: 애플리케이션의 내부 실행 상황을 추적하기 위한 상세 정보 출력
//    ( Mybatis 의 SQL Log 확인 가능 )
// INFO : 상태변경과 같은 주요 실행 정보 메시지를 나타냄
// WARN : 잠재적인 위험(에러)를 안고 있는 상태일 때 출력 (경고성 메시지)
// ERROR: 오류가 발생했지만, 애플리케이션은 계속 실행할 수 있을 때 출력
// FATAL: 애플리케이션을 중지해야 할 심각한 에러가 발생 했을 때 출력

//=> 실제는 DEBUG, WARN 이 주로 이용됨.
//-------------------------------------------------

//** 로깅레벨 조정 Test (log4j.xml 의)
//=> root Tag 에서 출력 level 조정 (system 오류 level조정) 
//     <root> <priority value 값 >
//=> <logger name="com.ncs.green"> 에서 출력 level 조정
//   <level value="DEBUG" />
//=> 이 두곳의 값을 warn (default) , error, debug, trace

//=> DEBUG Level 에서 Mybatis SQL구문 오류 메시지
// 아래와 같이 SQL 구문에 전달된 값을 정확하게 확인 할 수 있다. 
// DEBUG: mapperInterface.MemberMapper.insert - 
//          ==>  Preparing: insert into member values(?,?,?, ?,?,?,?,?,?) 
// DEBUG: mapperInterface.MemberMapper.insert - 
//          ==> Parameters: apple(String), 12345!(String), 가나다(String), 
//          열심히 하겠습니다!!!(String), 2022-11-17(String), 9(Integer), 22(Integer),
//          1000.55(Double), resources/uploadImage/adv.gif(String)
//----------------------------------------------------------------

//** Lombok 의 @Log4j Test


@Controller
@AllArgsConstructor
public class HomeController {
	
	GuestbookService service;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//@GetMapping(value={"/", "/home"})
	// => void : 요청명.jsp 를 viewName 으로 처리함 (home.jsp)
	//           그러므로 "/" 요청은 .jsp 를 viewName 으로 찾게됨(주의) 
	// => Boot 의 매핑메서드 에서 "/" 요청은 적용안됨(무시됨) 
	//     WebMvcConfig 의 addViewControllers 메서드로 해결
	@GetMapping(value = "/home")
	public void home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
	}
	
	@GetMapping("/axtestform")
	public String axTestForm() {
		System.out.println("1");
		return "axTest/axTestForm";
	}
	
	
	@GetMapping("/ginsert")
	public String ginsert() {
		GuestbookDTO dto = GuestbookDTO.builder()
								.title("JPA Insert Test")
								.content("입력이 솔솔 된다~")
								.writer("admin1")
								.build();
		System.out.println("** guest Insert => "+ service.register(dto) );
	
		return "redirect:home";
	}
	
	@GetMapping("/glist")
	public String glist() {
		List<Guestbook> list = service.selectList(); 
		for(Guestbook g : list) {
	         System.out.println(g+", regDate ="+g.getRegDate()+", modDate="+g.getModDate() );
	         System.out.println(" ");
		}
		System.out.println("** guest List");
		
		return "redirect:home";
	}
	
	@GetMapping("/gupdate")
	public String gupdate() {
		GuestbookDTO dto = GuestbookDTO.builder()
				.gno(3L)
				.title("JPA Update Test")
				.content("수정이 솔솔 된다~")
				.writer("banana")
				.build();
		
		System.out.println("** guest update => "+ service.register(dto) );

		
		return "redirect:home";
	}
	
	@GetMapping("/gdetail")
//	=> query string으로 작성. / gdetail?gno=2
	public String gdetail(Long gno) {
		System.out.println(" ** g detail => " +service.selectOne(gno) );
		
		return "redirect:home";
	}
	
	@GetMapping("/gdelete")
	//	=> query string으로 작성. /gdelete?gno=4
	public String gdelete(Long gno) {
		try {
			service.delete(gno);
			System.out.println(" ** G Delete 성공 -> " +gno);
			
		} catch (Exception e) {
			System.out.println(" ** guest delete Exception => "+e.toString() );
			// => 자료가 없으면 org.springframework.dao.EmptyResultDataAccessException 발생확인
		}
		
		return "redirect:home";
	}
	
	//** JPA Paging & Sort ===================
	// 출력할 PageNo , size 한 페이지에 담을 row 갯수를 입력
	
	@GetMapping("/gpage")
	public String gpage() {
		PageRequestDTO requestDTO = PageRequestDTO.builder()
									.page(3).size(5).build();
		
		PageResultDTO<GuestbookDTO,Guestbook> resultDTO =
					service.pageList(requestDTO); 
		
		System.out.println(" ** Page List => " + requestDTO.getPage());
		for( GuestbookDTO g:resultDTO.getDtolist() ) {
			System.out.println(g);
		}
		
		
		return "redirect:home";
	}
	

	
}
