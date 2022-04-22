<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>거래처 별 상품 분류 목록</title>
</head>
<body>

<table border="1">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>상품분류명</th>
		<th>상품분류코드</th>
	</tr>
	<c:set var="i" value="0" />
	<!-- buyerVO : 1에 대한 데이터 -->
	<c:forEach var="buyerVO" items="${list}" varStatus="stat">
	<!-- buyerVO.lprodVO : N에 대한 데이터 -->
		<c:forEach var="lprodVO" items="${buyerVO.lprodVO}">
		<c:set var="cnt" value="${i=i+1}"/>
			<tr>
				<td>${cnt}</td>
				<td>${buyerVO.buyerName}</td>
				<td>${lprodVO.lprodNm}</td>
				<td>${lprodVO.lprodGu}</td>
			</tr>
		</c:forEach>
	</c:forEach>
</table>
</body>
</html>