package com.itwillbs.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itwillbs.test.vo.BoardVO;

@Controller
public class BoardController {
	
	// write.bo 서블릿 요청(GET)에 대한 write() 메소드 정의
	// => qna_board_write.jsp 페이지 이동
	@RequestMapping(value = "write.bo", method = RequestMethod.GET)
	public String write() {
		return "qna_board_write";
	}
	
	// qna_board_write.jsp 페이지로부터 요청받은 폼 파라미터 가져오기
	// => 별도의 파라미터별 변수 지정 대신 VO(DTO, Bean) 객체 활용 가능
	//    메소드 정의 시 해당 파라미터들과 일치하는 멤버변수를 갖는 VO 클래스 지정 시 자동 주입됨
	// => 주의! 기본 생성자가 반드시 존재해야한다!
	// => 처리 후 리다이렉트 방식으로 "list.bo" 서블릿 요청
	@RequestMapping(value = "write.bo", method = RequestMethod.POST)
	public String writePost(@ModelAttribute BoardVO board) {
		System.out.println("글쓴이 : " + board.getBoard_name());
		System.out.println("비밀번호 : " + board.getBoard_pass());
		System.out.println("제목 : " + board.getBoard_subject());
		System.out.println("내용 : " + board.getBoard_content());
		
		return "redirect:/list.bo";
	}
	
	// 데이터 저장에 사용될 Model 타입 변수를 파라미터로 선언
	@RequestMapping(value = "list.bo", method = RequestMethod.GET)
	public String list(Model model) {
		
		// 데이터베이스 글 목록 조회했다고 가정
		// 1. 전체 글 목록 저장할 ArrayList 객체 생성(boardList)
		// => 제네릭타입 BoardVO
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		// 2. 1개 게시물 정보를 저장하는 BoardVO 객체 생성하여 데이터 저장
		// => BoardVO 객체를 다시 ArrayList 객체에 저장
		boardList.add(new BoardVO(1, "김감자", "1234", "감자여요", "나는 왕감자", "", "", 1, 0, 0, 0, null));
		boardList.add(new BoardVO(2, "김감자", "1234", "감자여요", "나는 왕감자", "", "", 2, 0, 0, 0, null));
		boardList.add(new BoardVO(3, "김감자", "1234", "감자여요", "나는 왕감자", "", "", 3, 0, 0, 0, null));
		
		// 3. 뷰페이지로 전달할 데이터(ArrayList 객체)를 Model 객체에 저장
		model.addAttribute("boardList", boardList);
		
		return "list";
	}
}
