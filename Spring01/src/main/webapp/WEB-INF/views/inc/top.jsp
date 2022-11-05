<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<header> 
	<div id="menu">
			<a href="<%=request.getContextPath() %>">홈</a>
			<a href="<%=request.getContextPath() %>/main">메인페이지</a>
			<a href="<%=request.getContextPath() %>/push">데이터전달</a>
			<a href="<%=request.getContextPath() %>/redirect">리다이렉트</a>
			<a href="<%=request.getContextPath() %>/mav">ModelAndView</a>
			<a href="<%=request.getContextPath() %>/login.me">로그인</a>
			<a href="<%=request.getContextPath() %>/write.bo">글쓰기</a>
	</div>
	<hr>
</header>