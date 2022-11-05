package com.itwillbs.mvc_board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.mvc_board.service.BoardService;
import com.itwillbs.mvc_board.vo.BoardVO;
import com.itwillbs.mvc_board.vo.PageInfo;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;

	// 글 쓰기 폼 - GET
	@GetMapping(value = "/BoardWriteForm.bo")
	public String write() {
		return "board/qna_board_write";
	}
	
	// -----------------------------------------------------------------------------------------------------------------
	
	// "/BoardWritePro.bo" 서블릿 요청에 대해 글쓰기 작업 수행할 writePro() - POST
	@PostMapping(value = "/BoardWritePro.bo")
	public String writePro(@ModelAttribute BoardVO board, Model model) {
		
		System.out.println(board);
		
		int insertCount = service.registBoard(board);
		
		if(insertCount > 0) {
			return "redirect:/BoardList.bo";
		} else {
			model.addAttribute("msg", "글쓰기 실패!");
			return "member/fail_back";
		}
	}
	
	// -----------------------------------------------------------------------------------------------------------------
	
	// "/BoardList.bo" 서블릿 요청에 대해 글 목록 조회 list() - GET
	// => 파라미터 : 현재 페이지번호(pageNum) => 단, 기본값 1로 설정
	//               데이터 저장할 Model 객체(model)
	// => List<BoardVO> 객체 저장한 후 board/qna_board_list.jsp 페이지로 포워딩(Dispatch)
	
	//	@GetMapping(value = "/BoardList.bo")
	//	public String list(Model model, @RequestParam(defaultValue = "1") int pageNum) {
	//		
	//		int listLimit = 10;
	//		int pageListLimit = 10;
	//		
	//		int startRow = (pageNum - 1) * listLimit;
	//		
	//		List<BoardVO> boardList = service.getBoardList(startRow, listLimit);
	//		
	//		int listCount = service.getBoardListCount();
	//		// 페이지 계산 작업 수행
	//		
	//		// 전체 페이지 수 계산 (방법 1)
	//		// int maxPage = listCount / listLimit;
	//		// if(listCount % listLimit > 0) {	maxPage++; }
	//		
	//		// 전체 페이지 수 계산 (방법 2)
	//		// int maxPage = listCount / listLimit + (listCount % listLimit > 0 ? 1 : 0);
	//		
	//		// 전체 페이지 수 계산 (방법 3)
	//		int maxPage = (int)Math.ceil((double)listCount / listLimit);
	//		
	//		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
	//		
	//		int endPage = startPage + pageListLimit - 1;
	//		
	//		if(endPage > maxPage) { endPage = maxPage; }
	//		
	//		// 페이징 처리 정보 저장하는 PageInfo 클래스 인스턴스 생성 및 데이터 저장
	//		PageInfo pageInfo = new PageInfo(pageNum, listLimit, listCount, pageListLimit, maxPage, startPage, endPage);
	//		System.out.println(pageInfo);
	//
	//		model.addAttribute("pageInfo", pageInfo);
	//		model.addAttribute("boardList", boardList);
	//		
	//		return "board/qna_board_list";
	//	}
	
	// -----------------------------------------------------------------------------------------------------------------
	
	// "/BoardList.bo" 서블릿 요청에 대해 글 목록 조회 list() - GET
	// => 파라미터 : 검색타입(searchType) => 기본값 널스트링
	//				 검색어(keyword) => 기본값 널스트링
	//				 현재 페이지번호(pageNum) => 단, 기본값 1로 설정
	//               데이터 저장할 Model 객체(model)
	// => List<BoardVO> 객체 저장한 후 board/qna_board_list.jsp 페이지로 포워딩(Dispatch)
	@GetMapping(value = "/BoardList.bo")
	public String list(Model model,
			@RequestParam(defaultValue = "") String searchType,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "1") int pageNum) {
		
		// System.out.println("searchType : " + searchType + ", keyword : " + keyword);
		
		// 페이징 처리를 위한 계산 작업
		int listLimit = 10; // 한 페이지 당 표시할 게시물 목록 갯수 
		int pageListLimit = 10; // 한 페이지 당 표시할 페이지 목록 갯수
		
		// 조회 시작 게시물 번호(행 번호) 계산
		int startRow = (pageNum - 1) * listLimit;
		
		// Service 객체의 getBoardList() 메소드를 호출하여 게시물 목록 조회
		// => 파라미터 : 시작행번호, 페이지 당 목록 갯수
		// => 리턴타입 : List<BoardVO>(boardList)
		List<BoardVO> boardList = service.getBoardList(startRow, listLimit, searchType, keyword);
		
		// Service 객체의 getBoardListCount() 메서드를 호출하여 전체 게시물 목록 갯수 조회
		// => 파라미터 : 없음, 리턴타입 : int(listCount)
		int listCount = service.getBoardListCount(searchType, keyword);
		
		// ----------------------------------------------------------------------------------------
		
		// 페이지 계산 작업 수행
		
		// 전체 페이지 수 계산 (방법 1)
		// int maxPage = listCount / listLimit;
		// if(listCount % listLimit > 0) {	maxPage++; }
		
		// 전체 페이지 수 계산 (방법 2)
		// int maxPage = listCount / listLimit + (listCount % listLimit > 0 ? 1 : 0);
		
		// 전체 페이지 수 계산 (방법 3)
		// Math 클래스의 ceil() 메소드를 활용하여 소수점 올림 처리를 통해 전체 페이지 수 계산
		// => listCount / listLimit 를 실수 연산으로 수행하여 소수점까지 계산하고
		//    Math.ceil() 메소드를 통해 올림 처리 후 결과값을 정수로 변환
		int maxPage = (int)Math.ceil((double)listCount / listLimit);
		
		// 시작 페이지 번호 계산
		int startPage = (pageNum - 1) / pageListLimit * pageListLimit + 1;
		
		// 끝 페이지 번호 계산
		int endPage = startPage + pageListLimit - 1;
		
		// 만약, 끝 페이지 번호(endPage)가 최대 페이지 번호(maxPage)보다 클 경우 
		// 끝 페이지 번호를 최대 페이지 번호로 교체
		if(endPage > maxPage) { endPage = maxPage; }
		
		// 페이징 처리 정보 저장하는 PageInfo 클래스 인스턴스 생성 및 데이터 저장
		PageInfo pageInfo = new PageInfo(pageNum, listLimit, listCount, pageListLimit, maxPage, startPage, endPage);
		System.out.println(pageInfo);

		// ----------------------------------------------------------------------------------------
		
		// 게시물 목록(boardList) 과 페이징 처리 정보(pageInfo)를 Model 객체에 저장
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("boardList", boardList);
		
		return "board/qna_board_list";
	}
	
	// -----------------------------------------------------------------------------------------------------------------
	
	// "BoardDetail.bo" 서블릿 요청에 대한 글 상세내용 조회 작업 수행 - GET
	@GetMapping(value = "/BoardDetail.bo")
	public String detail(Model model, int board_num) {
		
		// Service 객체의 increaseReadcount() 메소드 호출하여 게시물 조회수 증가
		// => 파라미터 : 글번호, 리턴타입 : void
		service.increaseReadcount(board_num);
		
		// Service 객체의 getBoard() 메소드를 호출하여 게시물 상세 정보 조회
		// => 파라미터 : 글번호, 리턴타입 : BoardVO(board)
		BoardVO board = service.getBoard(board_num);
		
		// Model 객체에 BoardVO 객체 추가
		model.addAttribute("board", board);
		
		return "board/qna_board_view";
	}
	
	// -----------------------------------------------------------------------------------------------------------------
	
	// "BoardDeleteForm.bo" 서블릿 요청에 대한 글 삭제 폼 - GET
	@GetMapping(value = "BoardDeleteForm.bo")
	public String delete(int board_num) {
		return "board/qna_board_delete";
	}
	
	// -----------------------------------------------------------------------------------------------------------------
	
	// "BoardDeletePro.bo" 서블릿 요청에 대한 글 삭제 - POST
	@PostMapping(value = "BoardDeletePro.bo")
	// BoardVO 객체에 board_pass, board_num 이 담김
	public String deletePro(@ModelAttribute BoardVO board, @RequestParam int pageNum, Model model) {
		
		// Service - removeBoard() 메소드 호출하여 삭제 작업 요청
		// => 파라미터 : BoardVO 객체, 리턴타입 : int(deleteCount)
		int deleteCount = service.removeBoard(board);
		
		// 삭제 실패 시 "패스워드 틀림!" 메세지 저장 후 fail_back.jsp 페이지로 포워딩
		// 아니면, BoardList.bo 서블릿 요청(페이지번호 전달)
		if(deleteCount > 0) {
			return "redirect:/BoardList.bo?pageNum=" + pageNum;
		} else {
			model.addAttribute("msg", "패스워드 틀림!");
			return "member/fail_back";
		}
	}
	
	// -----------------------------------------------------------------------------------------------------------------
	
	// "BoardModifyForm.bo" 서블릿 요청에 대한 글 수정 폼 - GET
	@GetMapping(value = "BoardModifyForm.bo")
	public String modify(int board_num, Model model) {
		
		// 기존 게시글 내용 불러와야하므로 getBoard() 메소드 호출
		BoardVO board = service.getBoard(board_num);
		model.addAttribute("board", board);
		
		return "board/qna_board_modify";
	}
	
	// -----------------------------------------------------------------------------------------------------------------
	
	// "BoardModifyPro.bo" 서블릿 요청에 대한 글 수정 - POST
	@PostMapping(value = "BoardModifyPro.bo")
	public String modifyPro(@ModelAttribute BoardVO board, @RequestParam int pageNum, Model model) {
		
		// Service - modifyBoard() 메소드 호출하여 수정 작업 요청
		// => 파라미터 : BoardVO 객체, 리턴타입 : int(updateCount)
		int updateCount = service.modifyBoard(board);
		
		// 수정 실패 시 "패스워드 틀림!" 메세지 저장 후 fail_back.jsp 페이지로 포워딩
		// 아니면, BoardDetail.bo 서블릿 요청(글번호, 페이지번호 전달)
		if(updateCount > 0) {
			return "redirect:/BoardDetail.bo?board_num=" + board.getBoard_num() + "&pageNum=" + pageNum;
		} else {
			model.addAttribute("msg", "패스워드 틀림!");
			return "member/fail_back";
		}
	}
	
	// -----------------------------------------------------------------------------------------------------------------
	
	// "/BoardReplyForm.bo" 서블릿 요청에 대한 답글 폼 - GET
	// Service - getBoard() 메소드 재사용
	@GetMapping(value = "BoardReplyForm.bo")
	public String reply(int board_num, Model model) {
		BoardVO board = service.getBoard(board_num);
		
		model.addAttribute("board", board);
		return "board/qna_board_reply";
	}
	
	// -----------------------------------------------------------------------------------------------------------------
	
	// "/BoardReplyPro.bo" 서블릿 요청에 대한 답글 작성 요청 - POST
	@PostMapping(value = "BoardReplyPro.bo")
	public String replyPro(@ModelAttribute BoardVO board, int pageNum, Model model) {
		
		// 파라미터 : board_re_ref, board_re_seq 필요 => BoardVO 로 대체
		// Service - increaseBoardReSeq() 메서드 호출하여 순서번호(board_re_seq) 조정 요청
		// => 파라미터 : BoardVO 객체   리턴타입 : void
		service.increaseBoardReSeq(board);
		
		// Service - registReplyBoard() 메서드 호출하여 답글 등록 요청
		// => 파라미터 : BoardVO 객체   리턴타입 : int(insertCount)
		int insertCount = service.registReplyBoard(board);
		
		if(insertCount > 0) {
			return "redirect:/BoardList.bo?pageNum=" + pageNum;
		} else {
			model.addAttribute("msg", "답글 쓰기 실패!");
		}
		
		return "board/qna_board_reply";
	}
}
