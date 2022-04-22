<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>거래처 별 상품 목록</title>
</head>
<body>

<table border="1">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>상품명</th>
		<th>가격</th>
	</tr>
	<c:set var="i" value="0" />
	<!-- buyerVO : 1에 대한 데이터 -->
	<c:forEach var="buyerVO" items="${list}" varStatus="stat">
	<!-- buyerVO.prodVO : N에 대한 데이터 -->
		<c:forEach var="prodVO" items="${buyerVO.prodVO}">
		<c:set var="cnt" value="${i=i+1}"/>
			<tr>
				<td>${cnt}</td>
				<td>${buyerVO.buyerName}</td>
				<td>${prodVO.prodName}</td>
				<td>${prodVO.prodSale}</td>
			</tr>
		</c:forEach>
	</c:forEach>
</table>
</body>
</html>