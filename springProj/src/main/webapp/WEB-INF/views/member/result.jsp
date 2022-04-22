<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<body>
<h3>Result</h3>
<p>
	<img src="${memberVO.memberCertPic}" />
</p>
<p>
	아이디 : ${memberVO.memberid}
</p>
<p>
	이름 : ${memberVO.name}
</p>
<p>
	이메일 : ${memberVO.email}
</p>
<p>
	소개 : ${memberVO.introduction}
</p>
<p>
	취미 : 
	<c:forEach var="hobby" items="${memberVO.hobbyArray}">
		${hobby}&nbsp;
	</c:forEach>
</p>
<p>
	취미(List) : 
	<c:forEach var="hobby" items="${memberVO.hobbyList}">
		${hobby}&nbsp;
	</c:forEach>
</p>

</body>
</html>