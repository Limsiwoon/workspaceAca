package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.service.JoService;
import com.ncs.spring02.service.MemberService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value = "/jo")
@AllArgsConstructor
// => 모든 멤버변수를 초기화하는 생성자 자동 추가 & 사용
//	  그러므로 아래의 @Autowired 는 생략가능하다. 
public class JoController {
	
	//@Autowired(required = false)
	JoService joService;
	//@Autowired(required = false)
	MemberService memberService;
	
	// 1 ) joSelectList
	@RequestMapping(value="/joList", method = RequestMethod.GET)
	public void joList(Model model) {
		model.addAttribute("jinfo", joService.joSelectList() );
	}
	
	
	
	// 2 ) jodetail (joSelectOne메서드 활용)
	@RequestMapping(value="/joDetail", method = RequestMethod.GET)
	public String joDetail(Model model, JoDTO jdto, @RequestParam("joC") int jno) {
		//1. 요청분석
		String uri ="jo/joDetail";
		//2. service 처리
		
		if(jno>100) {
			System.out.println("조업데이트감");
			
			uri = "jo/joUpdate";
			jno -= 100;		
		}

		model.addAttribute("jinfo", joService.joSelectOne(jno) );
		
		System.out.println("조업데이트 안감");
		
		// 조원 출력하기.
		model.addAttribute("banana2",memberService.selectOne2(jno) );
		System.out.println(memberService.selectOne2(jno));
		return uri;
		
	}
	
	
	// 3) joUpdate(joUpdate 메서드 활용) 
	@RequestMapping(value="/joUpdate", method = RequestMethod.GET)
	public String joUpdate(Model model, JoDTO jdto) {
		String uri = "jo/joDetail";
		model.addAttribute("jinfo", jdto);
		
		if( joService.joupdate(jdto) > 0 ) {
			model.addAttribute("message","조를 바꿨어!?<br>");
			// => name을 수정할 수도 있으므로 loginName 을 수정해준다. 
		} else {
			uri = "jo/joUpdate";
			model.addAttribute("message", "수정 안됐는데, 뭘하려던거야?<br> ");	
		}
	
		return uri;
	}
	
	
	// 4. joinsert(joInsert 메서드 활용) => void인 상황에선 value에 준 값과 같은 jsp로 이동.
	@RequestMapping(value = "/joJoinForm", method = RequestMethod.GET )
	public void joJoin() {}
	
	
	@RequestMapping(value="/joinsert", method = RequestMethod.POST )
	public String joinsert(Model model, JoDTO jdto, RedirectAttributes rttr ) {
		String uri = "redirect:/home";
		
		if( joService.joinsert(jdto) > 0 ) {
			rttr.addFlashAttribute("message", "hello! 조 완성돼땅 이누마");
		} else {
			model.addAttribute("message", "hello! 조 절대 안만들어졌다 이누마");
			uri = "jo/joJoinForm";
		}
		
		return uri;
	}
	
	
	// 5. joDelete
	@RequestMapping(value="/joDelete", method = RequestMethod.GET )
	public void joDelete(Model model, @RequestParam("joC") int jno) {
		model.addAttribute("jinfo", joService.joSelectOne(jno) );
	}
//	@RequestMapping(value="/joDelete", method = RequestMethod.GET )
//	public void joDelete() {
//	}
	
	@RequestMapping(value="jdel")
	public String jdel(RedirectAttributes rttr , @RequestParam("joC") int jno) {
		String uri= "redirect:/home";
		
		if (joService.joDelete( jno ) > 0 ) {
			System.out.println("삭제됨");
			rttr.addFlashAttribute("message", "hello! 조 삭제됐다 이누마");
		} else {
			System.out.println("삭제안됨");
			uri = "jo/joList";
		}
		return uri;		
	}
	
	
	
	
	
	
}
