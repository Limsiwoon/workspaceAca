package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Jo;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Controller
@RequestMapping(value = "/jo")
public class JoController {
	MemberService memberService;
	JoService joService;

	// 1 ) joSelectList
	@GetMapping("/joList")
	public void joList(Model model) {
		model.addAttribute("jinfo", joService.selectList());
		log.info("**list " + joService.selectList());
	}

	// 2 ) jodetail (joSelectOne메서드 활용)
	@GetMapping("/joDetail")
	public String joDetail(Model model, Jo entity, @RequestParam("jCode") String jCode) {
		// 1. 요청분석
		String uri = "jo/joDetail";
		model.addAttribute("jinfo", joService.selectOne(entity.getJno()));

		// 2. service 처리
		if ("U".equals(jCode)) {
			uri = "jo/joUpdate";
			System.out.println("조업데이트감");
		}

		// 조원 출력하기.
		// MemberService 실행
		// => findByJno(int Jno) 메서드 추가
		// => 실행결과는 banana로 작성
		if ("D".equals(jCode)) {
			model.addAttribute("banana2", memberService.findByJno(entity.getJno()));

		}

		return uri;

	}

	// 3) joUpdate(joUpdate 메서드 활용)
	@GetMapping("/joUpdate")
	public String joUpdate(Model model, Jo entity) {
		String uri = "jo/joDetail";
		model.addAttribute("jinfo", joService.selectOne(entity.getJno()));

		try {
			log.info("** Jo Update 성공 => " + joService.save(entity));
			model.addAttribute("message", "조를 바꿨어!?<br>");
		} catch (Exception e) {
			uri = "jo/joUpdate";
			model.addAttribute("message", "수정 안됐는데, 뭘하려던거야?<br> ");
		}

		return uri;
	}

	// 4. joinsert(joInsert 메서드 활용) => void인 상황에선 value에 준 값과 같은 jsp로 이동.
	@GetMapping("/joJoinForm")
	public void joJoin() {
	}

	@PostMapping("/joinsert")
	public String joinsert(Model model, Jo entity, RedirectAttributes rttr) {
		String uri = "redirect:/home";
		try {
			joService.save(entity);
			log.info("** Jo Insert update => " + joService.save(entity));
			rttr.addFlashAttribute("message", "hello! 조 완성돼땅 이누마");
		} catch (Exception e) {
			model.addAttribute("message", "hello! 조 절대 안만들어졌다 이누마");
			uri = "jo/joJoinForm";
		}
		return uri;
	}

	// 5. joDelete
	@GetMapping("/joDelete")
	public void joDelete(Model model, @RequestParam("joC") int jno) {
		model.addAttribute("jinfo", joService.selectOne(jno));
	}

	@GetMapping(value = "jdel")
	public String jdel(RedirectAttributes rttr, @RequestParam("joC") int jno) {
		String uri = "redirect:/home";
		try {
			joService.delete(jno);
			rttr.addFlashAttribute("message", "hello! 조 삭제됐다 이누마");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("삭제안됨");
			uri = "jo/joList";
		}

		return uri;
	}

}
