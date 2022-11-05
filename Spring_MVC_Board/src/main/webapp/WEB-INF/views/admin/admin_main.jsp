<%@page import="com.itwillbs.mvc_board.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath() %>/resources/css/top.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	function confirmDelete(id) {
		let result = confirm("삭제하시겠습니까?");
		
		if(result) {
			location.href="MemberDelete.me?id=" + id;
		}
	}
</script>
</head>
<body>
	<h1>관리자 메인페이지</h1>
	<table border="1">
		<tr>
			<th width="50">번호</th>
			<th width="100">이름</th>
			<th width="100">아이디</th>
			<th width="200">이메일</th>
			<th width="150">가입일</th>
			<th width="150"></th>
		</tr>
		<c:forEach var="member" items="${memberList}">
			<tr>
				<td>${member.idx }</td>
				<td>${member.name }</td>
				<td>${member.id }</td>
				<td>${member.email }</td>
				<td>${member.date }</td>
				<td>
					<input type="button" value="상세정보" onclick="location.href='MemberInfo.me?id=${member.id }'">
					<input type="button" value="삭제" onclick="confirmDelete('${member.id}')">
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>