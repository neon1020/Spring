<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="inc/top.jsp"></jsp:include>
	<h1>model_and_view.jsp</h1>
	<h3>제목 : ${map.key.subject }</h3>
	<h3>내용 : ${map.key.content }</h3>
</body>
</html>