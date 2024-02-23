package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.MemberDTO;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import pageTest.PageMaker;
import pageTest.SearchCriteria;

//** IOC/DI 적용 ( @Component 의 세분화 ) 
//=> 스프링 프레임워크에서는 클래스들을 기능별로 분류하기위해 @ 을 추가함.
//=>  @Controller :사용자 요청을 제어하는 Controller 클래스
//     		  DispatcherServlet이 해당 객체를 Controller객체로 인식하게 해줌.
//=> 			  interface Controller 의 구현 의무가 없어짐.
//=>			  ** 이로인해 메서드 hndleRequest() 의 오버라이딩 의무 없어짐
//=>			  ** 이로인해 메서드명, 매개변수, 리턴타입에 자유로워짐.
//=>			  ** 그리고 클래스 와 메서드 단위로 맵핑해주는 @RequestMapping 가능하게 해줌
//=> 			  ** 그러므로 하나의 컨트롤러 클래스에 여러 개의 매핑메서드의 구현이 가능해짐
//=>			  ** 그래서  주로 테이블(엔티티) 단위로 작성함. 			

//=>  @Service : 비즈니스로직을 담당하는 Service 클래스
//=>  @Repository : DB 연동을 담당하는 DAO 클래스
//       DB 연동과정에서 발생하는 예외를 변환 해주는 기능 추가

@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping(value = "/member") // member로 시작하는 경로는 이쪽으로 연결 => MemberController에서는 member 사용 X
public class MemberController {

	@Autowired(required = false)
	MemberService service;

	@GetMapping("/mCheckList")
	public String mCheckList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {
		String uri = "member/mPageList";
		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);

		// 1 Criteria 처리
		cri.setSnoEno();

		// 2 Service 처리
		if (cri.getCheck() != null && cri.getCheck().length < 1) {
			cri.setCheck(null);
		}
		model.addAttribute("banana", service.mCheckList(cri));
		// 3 View 처리
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		pageMaker.setTotalRowsCount(service.mCheckRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);

		System.out.println(" ** M CONTROLLER 내부 MCHECKlIST 진입 **");
		return uri;
	} // mCheckList

	// mPageList paing
	@GetMapping("/mPageList")
	public void mPageList(HttpServletRequest request, Model model, SearchCriteria cri, PageMaker pageMaker) {

		String mappingName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);

		cri.setSnoEno();

		// mPageList 는 search할 것을 명해주고
		model.addAttribute("banana", service.mPageList(cri));
		pageMaker.setCri(cri);
		pageMaker.setMappingName(mappingName);
		// totalRowsCount에서는 페이지가 몇장이 나와야 하는지 계산해줌
		pageMaker.setTotalRowsCount(service.totalRowsCount(cri));
		model.addAttribute("pageMaker", pageMaker);

		// 확인용
		System.out.println("  **  M Controller 내부 mPageList 진입.  **  ");

	}

	// ** passwordencoding
	// -> new활용 하지 않고 @Autowired를 활용 & bean을 만들어줘야 함( 라이브러리 사용할 때 -> xml에서 작성함. ).
	@Autowired
	PasswordEncoder passwordEncoder;

	// ** 아이디 중복 확인
	@GetMapping("/idDupCheck")
	public void idDupCheck(@RequestParam("id") String id, Model model) {
		// 1) newID 존재 여부 확인
		if (service.selectOne(id) != null) {
			// => 사용 불가능
			model.addAttribute("idUse", "F");
		} else {
			model.addAttribute("idUse", "T");
		}
	}

//*************************************************************************************	
//~~~~~~~~~~~~~~~~~~~~~~~loginForm~~~~~~~~~~~~~~~

	@RequestMapping(value = "/loginForm", method = RequestMethod.GET) // HTTP GET
	public void loginForm() {
	}

//~~~~~~~~~~~~~~~~~~~~~~login~~~~~~~~~~~~
	@PostMapping(value = "/login")
	public String login(HttpSession session, Model model, MemberDTO dto) {

		// 1. 요청분석
		// => requst 로 전달되는 id, password 처리:
		// 매서드 매개변수로 MemberDTO 를 정의해주면 자동 처리
		// ( Parameter name 과 일치하는 setter 를 찾아 값을 할당해줌 )
		// => 전달된 password 보관 (입력된 것)
		String password = dto.getPassword();
		String uri = "redirect:/home";

		// 2. 서비스 & 결과 처리
		// => id 확인
		// => 존재하면 Password 확인
		// => 성공: id, name은 session에 보관, home 으로
		// => 실패: 재로그인 유도
		dto = service.selectOne(dto.getId());
		// => PasswordEncoder 적용
//		if(dto != null && dto.getPassword().equals(password)) {
		// dto.getPassword() => digest
		if (dto != null && passwordEncoder.matches(password, dto.getPassword())) {
			// 성공
			session.setAttribute("loginID", dto.getId());
			session.setAttribute("loginName", dto.getName());
		} else {
			// 실패
			uri = "member/loginForm";
			model.addAttribute("message", "오류났따! <br>로그인 다시해줘~ 마스크 벗겨야지<br>");
		}
		return uri;
	}

//~~~~~~~~~~~~~~~~~~logout~~~~~~~~~~~~~~~
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/home";
	}

//	~~~~~~~~~~~~~~~~~~~~~~~~~Member detail~~~~~~~~~~~

	@RequestMapping(value = { "/detail" }, method = RequestMethod.GET) // HTTP GET 요청이 "/mlistsp" 경로로 들어왔을 때 특정 메서드를
																		// 실행하도록 지정하는 것
	// => String jCode = @RequestParam("jCode") 과 동일
	public String detail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {
		// 수정할 때 사용해야 하기 때문에 void 보다는 String 타입으로 반환.
		// 1. 요청 분석
		// => id : session 에서 get
		// => detail 또는 수정 Page 출력을 위한 요청인지 jCode 로 구별
		String id = (String) session.getAttribute("loginID");
		String uri = "member/memberDetail";
		// => update 요청 확인 후 uri 수정
		if ("U".equals(jCode)) {
			uri = "member/updateForm";
		}
		// 2. service &결과 처리
		model.addAttribute("info", service.selectOne(id));
		return uri;
	}
//~~~~~~~~~~~~~~~~~~~PasswordEncoder된 password 수정하기 ~~~~~~~~~~~~~~~~~~~~~~

	// **Password 수정 (PasswordEncoder 추가 )
	@GetMapping("pwUpdate")
	public void pwUpdate() {
		// View_name 생략.S
	}

	// passwordUpdate
	// => 서비스 , DAO 에 ㅔpwUpdate(dto) 메서드 추가
	// => 성공 : session을 종료 후 , 로그인 창으로
	// => 실패 : pwUpdate, 재 수정 필요
	@PostMapping("pwUpdate") // 위와 이름이 같아도 보내는 방법이 다르기 때문에 상관 없음
	public String pwUpdate(HttpSession session, MemberDTO dto, Model model) {
		// 1) 요청분석
		// => id : session 에서

		dto.setId((String) session.getAttribute("loginID"));
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		String uri = "member/loginForm";

		if (service.pwUpdate(dto) > 0) {
			// => 성공
			session.invalidate();
			model.addAttribute("message", "Password 수정 성공, 재로그인 하쟈! ");
		} else {
			model.addAttribute("message", "password 수정 실패!");
			uri = "member/pwUpdate";
			// => 실패
		}

		return uri;
	}

//~~~~~~~~~~~~~~~~~~~~~~joinForm~~~~~~~~~~
	@RequestMapping(value = "/joinForm", method = RequestMethod.GET) // HTTP GET요청이 "/mlistsp" 경로로 들어왔을 때 특정 메서드를 실행하도록
																		// 지정하는 것
	public void joinForm() {
	}

	// **Join
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(HttpServletRequest request, Model model, MemberDTO dto) throws IOException {
		// 1. 요청분석
		// => 이전 : 한글처리 , request 값 -> dto dp set
		// => 스프링 : 한글은 filter, request 처리는 매개변수로 자동화
		String uri = "member/loginForm"; // 성공시

		// *** Upload File처리
		// => 주요과제
		// -> 전달된 파일 저장 : file1
		// -> 전달된 파일명 Table에 저장 : file2
		// => MultipartFile
		//
		// *** Upload File 처리 **************************
		// => 주요과제
		// -> 전달된 화일 저장 : file1 (서버의 물리적 실제저장위치 필요함)
		// -> 전달된 화일명 Table에 저장 : file2
		// -> MultipartFile : 위의 과정을 지원해주는 전용객체

		// 1) 물리적 실제저장위치 확인
		// 1.1) 현재 웹어플리케이션의 실행위치 확인
		// => 이클립스 개발환경 (배포전) : ~~.eclipse.~~ 포함
		// => 톰캣 서버 배포후 : ~~.eclipse.~~ 포함되어있지 않음
		String realPath = request.getRealPath("/");
		System.out.println("** realPath => " + realPath);

		// 1.2) realPath 를 이용해서 물리적저장위치 (file1) 확인
		if (!realPath.contains("-tomcat-")) // 개발중
			realPath += "resources\\uploadImages\\";
		// (배포전) 나 혼자 개발 할 떄 저장하는 경로
		else
			realPath = "E:\\MTest\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";
		// (배포후)서버 관점의 경로 : (모두가 접근 할 수있도록 만든 경로)

		// 1.3) 폴더 만들기 (없을수도 있음을 가정, File 실습)
		// => File type 객체 생성 : new File("경로");
		// => file.exists()
		// -> 파일 또는 폴더가 존재하는지 리턴
		// -> \\ 를 뒤에 작성하지 않은 경우, 파일이 정확히 있는지 확인 할 수 없음.
		// -> 폴더가 아닌, 파일존재 확인하려면 file.isDirectory() 도 함께 체크해야함.
		// ( 참고: https://codechacha.com/ko/java-check-if-file-exists/ )
		// => file.isDirectory() : 폴더이면 true (exists()는 true 이면서 false면 file이 존재 한다는 의미가
		// 됨.)
		// => file.isFile()
		// -> 파일이 존재하는 경우 true 리턴,
		// -> file의 Path 가 폴더인 경우는 false 리턴
		File file = new File(realPath); // 이전까지는 그저 string에 불과함. => 경로 인식.
		if (!file.exists()) {
			// => 저장폴더가 존재하지 않는경우 만들어줌
			file.mkdir(); // 디렉토리를 만들기
		} // 참고 ** file인지 폴더 인지 구분 하지 않고, 존재 여부만판단. 구분이 명확해야 하는 경우, isDirectory에서 true인 경우
			// 폴더임.

		// --------------------이미지를 업로드------------------------
		// ** File Copy 하기 (IO Stream 실습)
		// => 기본이미지(siba.png) 가 uploadImages 폴더에 없는경우 기본폴더(images) 에서 가져오기
		// => IO 발생: Checked Exception 처리
		file = new File(realPath + "siba.png");
		// file = new File(realPath+"siba.png"); // 경로에 이미지를 저장함. uploadImages 폴더에 화일존재
		// 확인을 위함
		if (!file.isFile()) { // 존재하지않는 경우
			String basicImagePath = "E:\\MTest\\workspaceAca\\workspaceAca\\demoM\\src\\main\\webapp\\resources\\images\\siba.png";
			FileInputStream fi = new FileInputStream(new File(basicImagePath));
			// => basicImage 읽어 파일 입력바이트스트림 생성
			FileOutputStream fo = new FileOutputStream(file);
			// => 목적지 파일(realPath+"basicman1.jpg") 출력바이트스트림 생성
			FileCopyUtils.copy(fi, fo);
		}
		// --------------------------------------------
		// ** MultipartFile
		// => 업로드한 파일에 대한 모든 정보를 가지고 있으며 이의 처리를 위한 메서드를 제공한다.
		// -> String getOriginalFilename(),
		// -> void transferTo(File destFile),
		// -> boolean isEmpty()

		// 1.4) 저장경로 완성
		// => 기본 이미지 저장
		String file1 = "", file2 = "siba.png";

		MultipartFile uploadfilef = dto.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			// => image_File 을 선택함
			// 1.4.1) 물리적위치 저장 (file1)
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로(relaPath+화일명) 완성
			uploadfilef.transferTo(new File(file1)); // 해당경로에 저장(붙여넣기)

			// 1.4.2) Table 저장경로 완성 (file2)
			file2 = uploadfilef.getOriginalFilename();
		}
		// --------------------------------------------
		dto.setUploadfile(file2);

//***************************************************************************************************		 
		// 2. Service & 결과
		// => PasswordEncode
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		if (service.insert(dto) > 0) {
			model.addAttribute("message", "이제 마스크를 벗길 기회가 생겼습니다.<br> 벗기시러가시져<br>");
		} else {
			uri = "member/joinForm";
			model.addAttribute("message", "잘못 입력하셨습니다. <br>마스크를 벗길 기회를 획득하시겠습니까?<br>");

		}
		return uri;
	} // join

//~~~~~~~~~~~~~~~~~update~~~~~~~~~~~~~~~~~~~~~~~~~~
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpSession session, Model model, MemberDTO dto) throws Exception {
		// 1. 요청분석
		// => 성공시 memberDetail, 실패 : updateForm
		// => 두 경우 모두 출력하려면, dto 객체의 값("info") 가 필요하므로 보관

		String uri = "member/memberDetail"; // 성공시
		model.addAttribute("info", dto);

		// **uploadFile처리
		// => newImage 선택 여부
		// => 선택 -> oldImage 삭제, newImage 저장 : uploadFilef 사용
		// => 선택하지 않음 -> oldImage가 uploadFile로 전달되었음으로 그냥 사용.

		MultipartFile uploadfilef = dto.getUploadfilef();

		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			// => newImage 선택
			// 1) 물리적 위치 저장 ( file1 )
			String realPath = request.getRealPath("/");
			String file1 = dto.getUploadfile(); // 받아온 파일을 oldImage 기본값을 넣기

			// 2) realPath를 이용해서 물리적 저장위치 file1 확인
			if (!realPath.contains("-tomcat-")) // 개발중
				realPath += "resources\\uploadImages\\";
			else
				realPath = "E:\\MTest\\IDESet\\apache-tomcat-9.0.85\\webapps\\demoM\\resources\\uploadImages\\";

			// 3) oldFile 삭제
			// => oldFile Name : dto.getUploadfile()
			// => 삭제 경로 : realPath+dto.getUploadfile()
			File delFile = new File(realPath + dto.getUploadfile()); // 지울 파일 경로를 delFile에 담기 .
			if (delFile.isFile())
				delFile.delete();// 존재한다면 삭제

			// 4) newFile 저장
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로(relaPath+화일명) 완성
			uploadfilef.transferTo(new File(file1)); // try catch를 해주지 않으면 이용 X => Throws 사용

			// 5) Table 저장경로 완성 (uploadfilef.getOriginalFilename())
			dto.setUploadfile(uploadfilef.getOriginalFilename());
		}
		// --------------------------------------------

		// 2. Service & 결과
		if (service.update(dto) > 0) {
			model.addAttribute("message", "내 정보 수정하면서, 사진도 바꿨어?<br>");
			// => name을 수정할 수도 있으므로 loginName 을 수정해준다.
			session.setAttribute("loginName", dto.getName());
		} else {
			uri = "member/updateForm";
			model.addAttribute("message", "수정 안됐는데, 뭘하려던거야?<br> ");
		}
		return uri;
	}

//~~~~~~~~~~~~~~~~~~delete~~~~~~~~~~~~~~~~~~~~~~~~~
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpSession session, Model model, MemberDTO dto, RedirectAttributes rttr) {
		// 1. 요청분석
		// => id ; session 에서 get
		// => delete & session 처리
		String id = (String) session.getAttribute("loginID");
		String uri = "redirect:/home";

		// 2. Service & 결과처리
		if (service.delete(id) > 0) {
			// model.addAttribute("message","모야 마스크 벗기니까 지겨워진고야? <br>우리 1달은 보지말쟈.<br>");
			// => requestScope 의 message를 redirect 시에도 유지하려면,
			// => session 에 보관했다가 사용후에는 삭제해야함
			// => session에 보관 후 redirect 되어진 요청 처리시에 requestScope 에 옮기고.
			// => session 메세지는 삭제
			// => 이것을 처리해주는 API 가 RedirectAttributes의 메세지 담는 매개변수 사용 -> 그 내부에
			// addFlashAttribute
			rttr.addAttribute("message", "모야 마스크 벗기니까 지겨워진고야? <br>우리 1달은 보지말쟈.<br>");
			session.invalidate();
		} else {
			model.addAttribute("message", "탈퇴 실패 햇어. <br>마스크 놀이 재밋잖아 가지마.<br>");
		}
		return uri;
	}

//~~~~~~~~~~~~~~~~~~~mList~~~~~~~~~~~~~~~~~~~~~~~~~
	@RequestMapping(value = "/memberList", method = RequestMethod.GET) // HTTP GET 요청이 "/mlistsp" 경로로 들어왔을 때 특정 메서드를
																		// 실행하도록 지정하는 것
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
		// return "member/memberList";
	}
	
	
	
	
	//~~~~~~ axios 방식 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//aximlist
	@GetMapping("/axMemberList")
	public String axiMemberList(Model model) {
		model.addAttribute("banana", service.selectList());
		log.info(" === member List 성공했습니다! HttpStatus.OK "+HttpStatus.OK);
		return "axTest/axMemberList";
	}
	
}
