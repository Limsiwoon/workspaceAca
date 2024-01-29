package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.service.BoardService;
import com.ncs.spring02.service.MemberService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
// 안할 경우, @autowired해주어야함. 
@RequestMapping("/board")
public class BoardController {
	
	BoardService boardService;
	MemberService memberService;
	
	
	//boardList
	@GetMapping("/boardList")  //method 맵핑
	public void boardList(Model model) {
		model.addAttribute("banana", boardService.selectList() );
	}
	
	//boardDetail
	@GetMapping("boarddetail")
	public String boarddetail(HttpSession session, Model model, @RequestParam("jCode") String jCode, @RequestParam("seq") int seq) {
		String uri = "board/boardDetail";

		
		if("U".equals(jCode)) uri = "board/boardUpdate";

		model.addAttribute("binfo", boardService.selectOne(seq));
		
		System.out.println( boardService.selectOne(seq) );
		return uri;
	}//boardDetail
	
	
	@RequestMapping(value="boardupdate", method = RequestMethod.GET)
	public String boardupdate(Model model,BoardDTO dto) {
		String uri="board/boardDetail"; //성공시
		model.addAttribute("binfo", dto);
		
		if( boardService.update(dto) >0 ) {
			model.addAttribute("message", "update완료되어땨");

		} else {
			uri="board/boardUpdate";
			model.addAttribute("message", "update 안됨! ");
		}
		return uri;
	}
}
