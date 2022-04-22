<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 
BookVO
[bookId=3, title=해리포터, category=소설, price=15000, insertDate=2022-01-28 11:21:43.0]

BookController에서 mav 객체에 data라는 이름으로 select검색 결과를 넣었으므로..
	mav.addObject("data", bookVO)
딸러{data.title} 형식으로 사용하면 됨
-->
<!DOCTYPE html>
<html>
<head>
<title>책 수정하기</title>
</head>
<body>
<h1>책 수정</h1>
<!-- action이 없으면../update를 요청 단, method는 post -->
<form method="post">
	<input type="hidden" name="bookId" value="${data.bookId}">
	<p>제목 : <input type="text" name="title" value="${data.title}" required /></p>
	<p>카테고리 : <input type="text" name="category" value="${data.category}" required ></p>
	<p>가격 : <input type="text" name="price" value="${data.price}" required ></p>
	<input type="submit" value="저장" />&nbsp;
	<input type="button" value="취소" onclick="javascript:location.href='/detail?bookId=${data.bookId}'" />
</form>
</body>
</html>