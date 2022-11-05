<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">
	#listForm {
		width: 1024px;
		max-height: 610px;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 1024px;
		table-layout: fixed;
	}
	
	#tr_top {
		background: orange;
		text-align: center;
	}
	
	table td {
		text-align: center;
		
		/* 글이 칸을 벗어나면 ... 으로 줄여줌 (아래 세 코드 세트) */
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
	}
	
	.td_left {
		text-align: left;
		padding-left: 10px;
	}
	
	#pageList {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#emptyArea {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#buttonArea {
		margin: auto;
		width: 1024px;
		text-align: right;
	}
	
	a {
		text-decoration: none;
	}
</style>
</head>
<body>
	<header>
		<jsp:include page="inc/top.jsp"></jsp:include>
	</header>
	<!-- 게시판 리스트 -->
	<section id="listForm">
		<input type="button" value="목록" id="btn2">
	
		<h2>게시판 글 목록</h2>
		
		<table>
			<tr id="tr_top">
				<td width="100px">번호</td>
				<td>제목</td>
				<td width="150px">작성자</td>
				<td width="150px">날짜</td>
				<td width="100px">조회수</td>
			</tr>
			<c:forEach var="board" items="${boardList }">
			<tr>
				<td>${board.board_num }</td>
				<td class="td_left">
					<c:if test="${board.board_re_lev > 0 }">
						<c:forEach var="i" begin="1" end="${board.board_re_lev }" step="1">
							&nbsp;
						</c:forEach>
						<img src="images/re.gif">
					</c:if>
					<a href="BoardDetail.bo?board_num=${board.board_num}&pageNum=${pageInfo.pageNum }">${board.board_subject }</a>
				</td>
				<td>${board.board_name }</td>
				<td>${board.board_date }</td>
				<td>${board.board_readcount }</td>
			</tr>
			</c:forEach>
		</table>
	</section>
	<section id="buttonArea">
		<input type="button" value="글쓰기" onclick="location.href='BoardWriteForm.bo'" />
	</section>
</body>
</html>
