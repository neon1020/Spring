package com.itwillbs.test.controller;

import java.io.UnsupportedEncodingException;
import java.lang.ProcessBuilder.Redirect;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itwillbs.test.vo.TestVO;

//컨트롤러 역할을 하는 클래스 정의 시 @Controller 어노테이션을 클래스 선언부 윗줄에 지정
@Controller
public class TestController {
	
	// "main" 이라는 서블릿 주소 요청 시 자동으로 호출되는 requestMain() 메소드 정의
	// => 파라미터 없음. 리턴타입 : String
	// => @RequestMapping 어노테이션을 사용하여 GET 방식의 "main" 서블릿 주소 요청 받아 처리
	@RequestMapping(value = "main", method = RequestMethod.GET)
	public String requestMain() {
		
		// Dispatch 방식으로 views 디렉토리 내의 "main.jsp" 페이지 요청
		// => 이 때, views 디렉토리까지와 .jsp 는 문자열 결합으로 자동 처리되므로
		//    이동할 페이지 파일의 파일명만 리턴하면 알아서 자동으로 결합됨
		// "/WEB-INF/views/main.jsp" 페이지로 이동하도록 요청
		return "main";
	}
	
	// "push" 서블릿 주소 요청 시 자동으로 호출되는 push() 메소드 정의
	// => 파라미터 : 없음    리턴타입 : String
	// => push.jsp 페이지로 이동(Dispatch)
	//	@RequestMapping(value = "push", method = RequestMethod.GET)
	//	public String push(HttpServletRequest request) {
	
	// 		HttpServletRequest 객체의 setAttribute() 메서드를 사용하여 객체(데이터) 저장 가능
	// 		=> 단, 내장 객체가 존재하지 않으므로 외부로부터 객체를 전달(주입)받아야 함
	//		 => 서블릿 주소를 처리하는 메서드 파라미터에 HttpServletRequest 타입 변수를 선언하면
	//   		 톰캣에 의해 해당 객체가 자동으로 전달(주입)됨
	// 		request 객체에 "msg" 라는 속성명으로 "Hello, World!" 문자열 저장
	
	//		request.setAttribute("msg", "Hello, World!");
	
	// 		"/WEB-INF/views/push.jsp" 페이지로 이동하도록 요청
	// 		=> Dispatch 방식으로 이동 시 request 객체가 그대로 유지됨(= 저장 데이터가 유지됨)
	//		return "push";
	//	}
	
	// org.springframework.ui.Model 타입을 파라미터로 지정 시
	// 데이터 저장이 가능한 Model 객체를 자동으로 전달받을 수 있음
	// => HttpServletRequest 객체와 성격이 유사하며, java.util.Map 객체를 기반으로 만든
	//    스프링에서 제공하는 데이터 공유 객체
	@RequestMapping(value = "push", method = RequestMethod.GET)
	public String push(Model model) {
		
		// request.setAttribute() 메소드와 마찬가지로 Model 객체의 addAttribute() 로 데이터 저장
		// => request 객체와 범위(scope) 동일
		// => request 객체와 동시 사용 불가(일반적인 데이터 저장 시 request 객체보다 더 많이 사용)
		model.addAttribute("msg", "Hello, World! - Model 객체");
		
		return "push";
	}
	
	// --------------------------------------------------------------------------------------------------
	
	//  리다이렉트 방식 포워딩 처리를 수행하기 위해서는 return "redirect:/포워딩주소" 형식으로 지정
	//	@RequestMapping(value = "redirect", method = RequestMethod.GET)
	//	public String redirect() {
	//
	// 		리다이렉트 방식으로 포워딩 할 새 서블릿 주소 dispatcher 지정
	// 		주소 표시줄의 요청 주소가 dispatcher 로 변경됨
	// 		=> "dispatcher" 새 서블릿 요청에 대한 Dispatch 방식 포워딩 작업을 추가로 설정해야함
	//		return "redirect:/dispatcher";
	//	}
	//	
	//	@RequestMapping(value = "dispatcher", method = RequestMethod.GET)
	//	public String dispatcher() {
	//
	// 		디스패치 방식으로 포워딩 할 대상을 redirect.jsp 로 지정
	// 		주소 표시줄의 요청 주소가 dispatcher 인 채로 유지됨
	//		return "redirect";
	//	}
		
	//  리다이렉트 방식 포워딩 처리를 수행하기 위해서는 return "redirect:/포워딩주소" 형식으로 지정
	//	@RequestMapping(value = "redirect", method = RequestMethod.GET)
	//	public String redirect() {
	
	// 		리다이렉트 방식 포워딩 시 데이터 전달 방법 => URL 뒤에 파라미터형식으로 데이터 전달
	//		String name = "김감자";
	//		int age = 10;
	//		
	// 		주의! URL 을 통해 파라미터 직접 전달 시 한글, 한자 등 유니코드 문자 포함할 경우 깨짐
	//		 http://localhost:8081/test/dispatcher?name=hong&age=20  => 영문, 숫자 등은 정상 표기
	//		 http://localhost:8081/test/dispatcher?name=???&age=20   => 한글, 한자 등은 깨짐
	// 		=> 따라서, java.net.URLEncoder 클래스의 encode() 메소드를 호출하여 데이터 인코딩 필요
	//		try {
	//			name = URLEncoder.encode(name, "UTF-8");
	//		} catch (UnsupportedEncodingException e) {
	//			System.out.println("잘못된 인코딩 방식 지정!");
	//			e.printStackTrace();
	//		}
	//		
	// 		dispatcher 서블릿 주소 요청 시 이름과 나이를 파라미터로 전달하여 redirect 방식 포워딩
	//		return "redirect:/dispatcher?name=" + name + "&age=" + age;
	
	//		참고
	//		http://localhost:8081/test/dispatcher?name=%EA%B9%80%EA%B0%90%EC%9E%90&age=10
	// 		=> 인코딩 시 각 문자가 Unicode(3Byte) 형식으로 인코딩 됨
	//    	(주소표시줄의 텍스트 복사하면 인코딩 된 데이터가 %XX%XX%XX 형식으로 표시됨)
	//		
	//	}
	//	
	//	@RequestMapping(value = "dispatcher", method = RequestMethod.GET)
	//	public String dispatcher(Model model, String name, int age) {
	
	// 		요청에 따른 처리를 수행할 메소드 정의 시
	// 		URL 또는 body 를 통해 전달되는 파라미터와 이름이 동일한 매개변수 선언하면
	// 		이름이 일치하는 파라미터가 존재할 경우 해당 값을 자동으로 변수에 저장(GET or POST 무관)
	//		System.out.println("이름 : " + name + ", 나이 : " + age);
	//		
	//		Model 객체(스프링의 데이터 공유 객체)에 이름과 나이를 저장
	//		model.addAttribute("name", name);
	//		model.addAttribute("age", age);
	//		
	//		------------------------------------------------------------------------------
	//		참고!! 인코딩 된 데이터를 다시 원래 형식으로 되돌려야 할 경우
	// 		인코딩(Encoding)의 반대 작업인 디코딩(Decoding) 작업 필요
	// 		단, 현재 저장된 파라미터를 Model 객체 등에 저장 시 디코딩 불필요
	////		try {
	////			String decodedName = URLDecoder.decode(name, "UTF-8");
	////			System.out.println(name + ", " + decodedName);
	////		} catch (UnsupportedEncodingException e) {
	////			System.out.println("잘못된 디코딩 방식 지정!");
	////			e.printStackTrace();
	////		}
	//		------------------------------------------------------------------------------
	//		
	// 		디스패치 방식으로 포워딩 할 대상을 redirect.jsp 로 지정
	// 		주소 표시줄의 요청 주소가 dispatcher 인 채로 유지됨
	//		return "redirect";
	//	}
	
	// @RequestParam 어노테이션 테스트용 age 파라미터 삭제 후 포워딩하는 redirect() 메소드 정의
	@RequestMapping(value = "redirect", method = RequestMethod.GET)
	public String redirect() {
		String name = "potato";
		int age = 10;
		
		// name 파라미터만 URL 을 통해 전달
		return "redirect:/dispatcher?name=" + name;
		
	}
	
	// 서블릿 요청 처리 메소드 정의 시 @RequestParam 어노테이션을 사용하여
	// 파라미터 데이터라는 표시를 명확하게 명시 가능
	// => 또한, @RequestParam 어노테이션에 defaultValue 속성을 추가하여 기본값 설정 가능
	//    ex) @RequestParam(defaultValue = "기본값") 변수선언문
	@RequestMapping(value = "dispatcher", method = RequestMethod.GET)
	public String dispatcher(Model model, @RequestParam String name, @RequestParam(defaultValue = "0") int age) {
		// => 만약, age 라는 파라미터가 존재하지 않더라도 해당 변수 기본값을 0 으로 설정
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "redirect";
	}
	
	// ModelAndView : 데이터를 저장하는 Model 객체 관리와 
	//                view 페이지의 포워딩 처리를 함께 수행하는 객체
	// => ModelAndView 객체를 사용하는 메소드 정의 시 리턴타입을 ModelAndView 타입으로 지정
	@RequestMapping(value = "mav", method = RequestMethod.GET)
	public ModelAndView mav() {
		
		// ModelAndView 객체를 통해 전달할 데이터를 저장하기 위해 HashMap 객체 생성
		Map<String, TestVO> map = new HashMap<String, TestVO>();
		
		map.put("key", new TestVO("제목", "내용"));
		
		// 기존의 객체를 저장 후 뷰페이지로 포워딩하는 방법
		// model.addAttribute("map", map);
		// return "model_and_view";
		
		// ModelAndView 객체를 사용하여 객체 저장 후 뷰페이지로 포워딩하는 방법
		// => 객체 생성 : new ModelAndView("이동할 페이지", "데이터(객체)의 키", 데이터(객체));
		// => 포워딩 방식은 기존과 동일한 Dispatch 방식으로 포워딩
		return new ModelAndView("model_and_view", "map", map);
	}
}