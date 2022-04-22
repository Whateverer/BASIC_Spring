<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>책 생성하기</title>
</head>
<body>
	<h1>책 생성하기</h1>
	<form:form modelAttribute="bookVO" method="post" action="/create">
		<p>제목 : <form:input path="title" /></p>
		<p>카테고리 : <form:input path="category" /></p>
		<p>가격 : <form:input path="price" /></p>
		<p><form:button name="register">등록</form:button></p>
	</form:form>
</body>
</html>