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
<title>책 상세</title>
</head>
<body>
<h1>책 상세</h1>
<p>제목 : ${data.title}</p>
<p>카테고리 : ${data.category}</p>
<p>가격 : ${data.price}</p>
<p>입력일 : ${data.insertDate}</p>
<p><a href="/update?bookId=${data.bookId}">수정</a></p>
<p><a href="/list">목록으로</a></p>
<form method="post" action="/delete">
	<input type="hidden" name="bookId" value="${data.bookId}" />
	<input type="submit" value="삭제" />
</form>
</body>
</html>

