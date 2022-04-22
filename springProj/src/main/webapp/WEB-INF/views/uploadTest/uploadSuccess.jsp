<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>업로드 성공</title>
</head>
<body>
업로드 성공<br>
<c:forEach var="img" items="${list}">
	<img src="/resources/upload/${img}" />
</c:forEach>
</body>
</html>