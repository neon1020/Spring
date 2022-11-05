<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#id').blur(function() {
			// alert("포커스 해제");
			$.ajax({
				url:'MemberIdCheck.me',
				data:{'id':$('#id').val()},
				success:function(result) {
					$('#checkIdResult').html(result).css('color', 'blue');
				}
			});
		});
	});
</script>
<link href="<%=request.getContextPath() %>/resources/css/top.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<%-- not empty = ne null --%>
	<c:if test="${not empty sessionScope.sId }">
		<script type="text/javascript">
			alert("잘못된 접근입니다!");
			location.href = "./";
		</script>
	</c:if>
	<h1>회원 가입</h1>
	<form action="MemberJoinPro.me" method="post" name="joinForm" onsubmit="return checkForm()">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" id="name" required="required" size="20" onchange="checkName(this.value)"></td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<input type="radio" name="gender" value="남">남&nbsp;&nbsp;
					<input type="radio" name="gender" value="여" checked="checked">여
				</td>
			</tr>
			<tr>
				<td>E-Mail</td>
				<td>
					<input type="text" name="email" required="required" size="10">
				</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>
<!-- 					<input type="text" name="id" id="id" required="required" size="20" placeholder="4-16자리 영문자,숫자 조합" onchange="checkId(this.value)"> -->
					<input type="text" name="id" id="id" required="required" size="20" placeholder="4-16자리 영문자,숫자 조합">
					<span id="checkIdResult"><!-- 자바스크립트에 의해 메세지가 표시될 공간 --></span>
				</td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td>
					<input type="password" name="passwd" id="passwd" required="required" size="20" placeholder="8-20자리 영문자,숫자,특수문자 조합" onchange="checkPasswd(this.value)">
					<span id="checkPasswdResult"><!-- 자바스크립트에 의해 메세지가 표시될 공간 --></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="회원가입">
					<input type="button" value="취소" onclick="history.back()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>