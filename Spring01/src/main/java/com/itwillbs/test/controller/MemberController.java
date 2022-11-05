package com.itwillbs.test.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.test.vo.MemberVO;

@Controller
public class MemberController {
	
	// login.me 서블릿 주소 요청(로그인 폼)에 대해 member_login_form.jsp 페이지 포워딩(Dispatch)
	@RequestMapping(value = "login.me", method = RequestMethod.GET)
	public String login() {
		return "member_login_form";
	}
	
	// [ 파라미터 처리 방법1 ] 파라미터명과 동일한 매개변수를 선언하여 자동으로 전달받는 방법
	// login.me 서블릿 주소 요청(로그인 비즈니스 로직)에 대해 로그인 작업을 처리할
	// loginPost() 메소드 정의 => POST 방식 요청에 대한 처리
	// => 서블릿 주소가 동일하더라도 요청 방식(GET ot POST)이 다르면 충돌이 발생하지 않음 
	// 로그인 폼에서 입력받은 아이디와 패스워드를 전달받아 출력 후 메인페이지로 이동
	
	//	@RequestMapping(value = "login.me", method = RequestMethod.POST)
	//	public String loginPost(@RequestParam String id, @RequestParam String passwd) {
	//		System.out.println(id + ", " + passwd);
	//		
	//		return "redirect:/main";
	//	}
	
	// [ 파라미터 처리 방법2 ] Map 객체를 통해 파라미터명을 key, 파라미터값을 value 로 전달받는 방법
	//	@RequestMapping(value = "login.me", method = RequestMethod.POST)
	//	public String loginPost(@RequestParam Map<String, String> params) {
	//		System.out.println("Map : " + params.get("id") + ", " + params.get("passwd"));
	
	// 		Redirect 방식으로 메인페이지로 포워딩
	//		return "redirect:/main";
	//	}
	
	// [ 파라미터 처리 방법3 ] VO 객체 생성을 통해 일치하는 멤버변수에 전달받는 방법
	// => @ModelAttirbute 어노테이션을 사용하여 VO 객체타입 변수를 지정(어노테이션 생략 가능)
	@RequestMapping(value = "login.me", method = RequestMethod.POST)
	public String loginPost(@ModelAttribute MemberVO member, HttpSession session) {
		
		// 아이디와 패스워드가 MemberVO 객체 내의 id, passwd 변수에 자동 저장됨
		System.out.println("MemberVO : " + member.getId() + ", " + member.getPasswd());
		
		// 만약, 아이디가 "admin" 이고, 패스워드가 "1234" 이면 로그인 성공이므로
		// session 객체에 로그인 성공한 아이디 저장
		if(member.getId().equals("admin") && member.getPasswd().equals("1234")) {
			session.setAttribute("sId", member.getId());
		}
		
		// Redirect 방식으로 메인페이지로 포워딩
		return "redirect:/main";
	}
}
