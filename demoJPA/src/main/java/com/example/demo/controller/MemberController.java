package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Member;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping(value = "/member")
public class MemberController {

	MemberService service;
	PasswordEncoder passwordEncoder;

	// 2) Member_Jo Join List
	// => ver01) @Query("...") 에 JPQL, LEFT_JOIN 구문, MemberDTO return
	// => MemberDTO 는 JoDTO 상속
	@GetMapping("/mjoinList")
	public void mjoinList(Model model) {
		model.addAttribute("banana", service.findMemberJoin());
	}

	// ** 아이디 중복 확인 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
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

	// ~~~~~~~~~~~~~~~~~~~~~~~loginForm~~~~~~~~~~~~~~~

	@GetMapping("/loginForm")
	public void loginForm() {
	}// loginForm

	// ~~~~~~~~~~~~~~~~~~~~~~login~~~~~~~~~~~~
	@PostMapping(value = "/login")
	public String login(HttpSession session, Model model, Member entity) {

		// 1. 요청분석
		String password = entity.getPassword();
		String uri = "redirect:/home";

		// 2. 서비스 & 결과 처리
		entity = service.selectOne(entity.getId());

		if (entity != null && passwordEncoder.matches(password, entity.getPassword())) {
			// 성공
			session.setAttribute("loginID", entity.getId());
			session.setAttribute("loginName", entity.getName());
		} else {
			// 실패
			uri = "member/loginForm";
			model.addAttribute("message", "오류났따! <br>로그인 다시해줘~ 마스크 벗겨야지<br>");
		}
		return uri;
	}

	// ~~~~~~~~~~~~~~~~~~logout~~~~~~~~~~~~~~~
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~Member detail~~~~~~~~~~~

	@GetMapping("/detail")
	public String detail(HttpSession session, Model model, @RequestParam("jCode") String jCode) {

		// 1. 요청 분석
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

	// ~~~~~~~~~~~~~~~~~~~memberList~~~~~~~~~~~~~~~~~~~~~~~~~
	@GetMapping("/memberList")
	public void mList(Model model) {
		model.addAttribute("banana", service.selectList());
		// return "member/memberList";
	}

	// ~~~~~~~~~~~~~~~~~~~~~~joinForm~~~~~~~~~~
	@GetMapping("/joinForm")
	public void joinForm() {
	}

	// **Join
	@PostMapping("/join")
	public String join(HttpServletRequest request, Model model, Member entity) throws IOException {
		// 1. 요청분석
		String uri = "member/loginForm"; // 성공시

		// *** Upload File처리
		// 1) 물리적 실제저장위치 확인
		// 1.1) 현재 웹어플리케이션의 실행위치 확인
		String realPath = request.getRealPath("/");
		log.info("** realPath => " + realPath);

		// 1.2) realPath 를 이용해서 물리적저장위치 (file1) 확인
		realPath += "resources\\uploadImages\\";

		// 1.3) 폴더 만들기 (없을 수도 있음을 가정함)
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdir(); // 디렉토리를 만들기
		}

		// --------------------이미지를 업로드------------------------

		file = new File(realPath + "siba.png");
		// file = new File(realPath+"siba.png"); // 경로에 이미지를 저장함. uploadImages 폴더에 화일존재
		// 확인을 위함
		if (!file.isFile()) { // 존재하지않는 경우
			String basicImagePath = "E:\\MTest\\workspaceAca\\workspaceAca\\demoJPA\\src\\main\\webapp\\resources\\images\\siba.png";
			FileInputStream fi = new FileInputStream(new File(basicImagePath));
			// => basicImage 읽어 파일 입력바이트스트림 생성
			FileOutputStream fo = new FileOutputStream(file);
			// => 목적지 파일(realPath+"basicman1.jpg") 출력바이트스트림 생성
			FileCopyUtils.copy(fi, fo);
		}
		// --------------------------------------------

		// 1.4) 저장경로 완성
		// => 기본 이미지 저장
		String file1 = "", file2 = "siba.png";

		MultipartFile uploadfilef = entity.getUploadfilef();
		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			// => image_File 을 선택함
			// 1.4.1) 물리적위치 저장 (file1)
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로(relaPath+화일명) 완성
			uploadfilef.transferTo(new File(file1)); // 해당경로에 저장(붙여넣기)

			// 1.4.2) Table 저장경로 완성 (file2)
			file2 = uploadfilef.getOriginalFilename();
		}
		// --------------------------------------------

		entity.setUploadfile(file2);
		// 2. Service & 결과
		// => PasswordEncode
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));

		// ** Service 처리
		try {
			log.info("** member insert 성공 => \n" + service.save(entity));
			model.addAttribute("message", "~~ 회원가입 성공 !! 로그인 후 이용하세요 ~~");
		} catch (Exception e) {
			log.info("** member insert Exception => " + e.toString());
			uri = "member/joinForm";
			model.addAttribute("message", "~~ 회원가입 실패 !! 다시 하세요 ~~");
		}

		return uri;
	} // join

	// ~~~~~~~~~~~~~~~~~~~ PasswordEncoder된 password 수정하기 ~~~~~~~~~~~~~~~~~~~~~~

	// **Password 수정 (PasswordEncoder 추가 )
	@GetMapping("pwUpdate")
	public void pwUpdate() {
		// View_name 생략
	}

	// passwordUpdate
	@PostMapping("pwUpdate") // 위와 이름이 같아도 보내는 방법이 다르기 때문에 상관 없음
	public String pwUpdate(HttpSession session, Member entity, Model model) {
		// 1) 요청분석
		// => id : session 에서
		// => password : 암호화
		entity.setId((String) session.getAttribute("loginID"));
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		String uri = "member/loginForm";

		try {
			service.updatePassword(entity.getId(), entity.getPassword());
			log.info(" **Password 수정 성공, 재로그인 하쟈!  " + entity.getId());
			session.invalidate();
			model.addAttribute("message", "Password 수정 성공, 재로그인 하쟈! ");
		} catch (Exception e) {
			log.info(" **Password 수정 실패!!  " + e.toString());
			uri = "member/pwUpdate";
			model.addAttribute("message", "password 수정 실패!");
		}

		return uri;
	}

	// ~~~~~~~~~~~~~~~~~update~~~~~~~~~~~~~~~~~~~~~~~~~~
	@PostMapping("/update")
	public String update(HttpServletRequest request, HttpSession session, Model model, Member entity) throws Exception {
		// 1. 요청분석
		String uri = "member/memberDetail"; // 성공시
		model.addAttribute("info", entity);

		MultipartFile uploadfilef = entity.getUploadfilef();

		if (uploadfilef != null && !uploadfilef.isEmpty()) {
			// => newImage 선택
			// 1) 물리적 위치 저장 ( file1 )
			String realPath = request.getRealPath("/");
			String file1 = entity.getUploadfile(); // 받아온 파일을 oldImage 기본값을 넣기

			// 2) realPath를 이용해서 물리적 저장위치 file1 확인

			realPath += "resources\\uploadImages\\";

			// 3) oldFile 삭제
			// => oldFile Name : dto.getUploadfile()
			// => 삭제 경로 : realPath+dto.getUploadfile()
			File delFile = new File(realPath + entity.getUploadfile()); // 지울 파일 경로를 delFile에 담기 .
			if (delFile.isFile())
				delFile.delete();// 존재한다면 삭제

			// 4) newFile 저장
			file1 = realPath + uploadfilef.getOriginalFilename(); // 저장경로(relaPath+화일명) 완성
			uploadfilef.transferTo(new File(file1)); // try catch를 해주지 않으면 이용 X => Throws 사용

			// 5) Table 저장경로 완성 (uploadfilef.getOriginalFilename())
			entity.setUploadfile(uploadfilef.getOriginalFilename());
		}
		// --------------------------------------------

		// 2. Service & 결과
		try {
			log.info(" ** member update 성공 => " + service.save(entity));
			model.addAttribute("message", " 정보 수정 완료 했눼 ?<br>");
			session.setAttribute("loginName", entity.getName());
		} catch (Exception e) {
			log.info(" ** member update 실패  => " + e.toString());
			uri = "member/updateForm";
			model.addAttribute("message", "수정 안됐는데, 뭘하려던거야?<br> ");
		}

		return uri;
	}// update

	// ~~~~~~~~~~~~~~~~~~delete~~~~~~~~~~~~~~~~~~~~~~~~~
	@GetMapping("/delete")
	public String delete(HttpSession session, Model model, Member entity, RedirectAttributes rttr) {
		// 1. 요청분석

		String id = (String) session.getAttribute("loginID");
		String uri = "redirect:/home";

		try {
			service.deleteById(id);
			log.info(" ** member delete 성공 => " + id);
			rttr.addAttribute("message", "뭐야 눈동자 아팠어? 우리 1달은 보지말쟈.<br>");
			session.invalidate();
		} catch (Exception e) {
			model.addAttribute("message", "탈퇴 실패 햇어. 또 봐 ~ <br>");
		}

		return uri;
	}// delete

}
