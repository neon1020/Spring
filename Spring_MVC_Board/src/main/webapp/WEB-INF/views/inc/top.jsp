<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@  taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function logout() {
		let result = confirm("로그아웃 하시겠습니까?");
		
		if(result) {
			location.href = "MemberLogout.me";
		}
	}
</script>
<div id="member_area">
	<a href="./">Home</a> | 
	
	<!-- 세션 아이디("sId") 없을 경우 Login(MemberLoginForm.me) 과 Join(MemberJoinForm.me) 링크 표시 -->
	<!-- 세션 아이디 있을 경우 세션아이디 와 Logout(MemberLogout.me) 링크 표시 -->
	<c:choose>
		<c:when test="${empty sessionScope.sId}">
			<a href="MemberLoginForm.me">Login</a> | <a href="MemberJoinForm.me">Join</a>
		</c:when>
		<c:otherwise>
			<a href="MemberInfo.me?id=${sessionScope.sId }">${sessionScope.sId }</a> 님 | <a href="javascript:logout()">Logout</a>
			<c:if test="${sessionScope.sId eq 'admin' }">
				| <a href="AdminMain.me">관리자페이지</a>
			</c:if>
		</c:otherwise>
	</c:choose>
</div>