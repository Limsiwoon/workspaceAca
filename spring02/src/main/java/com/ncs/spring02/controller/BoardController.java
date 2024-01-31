package com.ncs.spring02.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mysql.cj.Session;
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
	
	
	//**reply Insert
	@GetMapping("replyInsert")
	public void replyInsert(BoardDTO dto) {}
	//- 답글처리를 위해 부모들의 root, step, indent 를 인자로 전달 받으면, 이 인자에 다겨진 값은 requestScope 과 동일
	//- 그러므로 response 가 전송 전까지는 server에서 사용 가능.
	//- 단, 객체명의 첫문자를 소문자로  해서 접근 가능. ( ${boardDTO.~~} )

	
	
	//=> 메서드명과 요청이 위의 메서드와 동일하지만, 
	//post 요청이고, 인자가 다르기 때문에 replyInsert로 똑같더라도 사용 가능
	@PostMapping("/replyInsert") 
	public String replyInsert(Model model, BoardDTO dto, RedirectAttributes rttr) {
		//** 답글 등록하기 **
		String uri ="redirect:boardList";
		//성공시 : boardList // 입력 완료 확인
		//실패시 : replyInsert 재입력 유도
		
		// => dto 값 확인하기 
		// => id, title, content: 사용가능
		// => 부모글의 root : 사용가능 (자식root도 동일함. )
		// => 부모글의 step , indent : 1 씩 증가 해야함. 
		// => sql 문 처리할 떄, 
		//		-> replyInsert,step의 update
		dto.setStep(dto.getStep()+1);
		dto.setIndent(dto.getIndent()+1);
		System.out.println("********************" + dto);
		if( boardService.rinsert(dto) > 0) {
			rttr.addFlashAttribute("message"," 답글 등록해땨 ~ ");
		}else {
			uri = "board/replyInsert";
			model.addAttribute("message"," 답글 등록 몬해따!  ~ ");
		}
		
		return uri;
		
	}		
		
		
		
		
	//boardList
	@GetMapping("/boardList")  //method 맵핑
	public void boardList(Model model) {
		model.addAttribute("banana", boardService.selectList() );
	}
	
	//boardDetail
	// => 글 요청 처리중, 글을 읽기 전
	// => 조회수 증가
	//    session의 loginID 와 board의 id와 다른 경우 , 
	//	  
	@GetMapping("boarddetail")
	public String boarddetail(HttpSession session, Model model, @RequestParam("jCode") String jCode, @RequestParam("seq") int seq) {
		String uri = "board/boardDetail";
		BoardDTO dto = boardService.selectOne(seq);
		if("U".equals(jCode)&seq!=0) uri = "board/boardUpdate";
		
		// => 조회수 증가 : selectOne 의 결과를 보관하여 다르면 조회수 증가
		else if(!dto.getId().equals((String)session.getAttribute("loginID") )) {
			//=>조회수 증가 조건에 만족
			dto.setCnt(dto.getCnt()+1);
			boardService.update(dto); // => dto 안에 cnt가 있음. DAO에서 업데이트를 함께 해주어야함. 
		}
		model.addAttribute("binfo", boardService.selectOne(seq));

		if("X".equals(jCode)&seq!=0) uri = "board/boardDelete";
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
	
	@GetMapping("/boardInsert")  //method 맵핑
	public void boardInsert() {
	}
	
	@RequestMapping(value="boardinsert", method =RequestMethod.GET)
	public String boardinsert(HttpSession session, Model model, BoardDTO dto ) {
		String uri="redirect:/home";

		dto.setId((String)session.getAttribute("loginID"));
		if(boardService.boardInsert(dto)>0) {
			
			model.addAttribute("message","등록이 완료 되었슴미다");
		}else {
			model.addAttribute("message","등록하는 데 실패하였습미다");
			uri="board/boardInsert";
		}
		model.addAttribute("binfo", dto);
		return uri;
	}
	
	@RequestMapping(value="boarddelete", method =RequestMethod.GET)
	public String boarddelete(BoardDTO dto,Model model) {
		String uri ="redirect:/home";
		
		if(boardService.delete(dto)>0) {
			model.addAttribute("message","삭제되어따 이누마");
		}else{
			uri="board/boardDelete";
			model.addAttribute("message","삭제 안됐는데..");
		}
		return uri;
	} 
}
