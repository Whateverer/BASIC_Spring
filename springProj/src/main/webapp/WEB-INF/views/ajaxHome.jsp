<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<title>ajaxHome</title>
<script type="text/javascript">
$(function(){
		
	$("#pubBtn").on("click", function(){
		var boardNo = $("#boardNo");
		var title 	= $("title");
		var content = $("content");
		var writer 	= $("writer");
		
		var boardNoVal = boardNo.val();
		var titleVal = title.val(); 	
		var contentVal = content.val();
		var writerVal = writer.val();
			
		
		var boardObject = {
			boardNo:boardNoVal,
			title:titleVal,
			content:contentVal,
			writer:writerVal
		}
		//JSON.stringify() : JavaScript 값이나 객체를 JSON 문자열로 반환
		/*
		post
		 - 리소스의 생성을 담당
		 - 요청 시 마다 새로운 리소스가 생성됨
		put
		 - 리소스의 생성과 수정을 담당
		 - 요청 시 마다 같은 리소스를 반환함
		*/
		$.ajax({
			type:"put",
			url:"/board/"+boardNoVal,
			data:JSON.stringify(boardObject),
			contentType:"application/json;charset=utf-8",
			success:function(result){
				console.log("result : " + result);
				if(result=="SUCCESS"){
					alert("SUCCESS");
				}
			}
		});
	});// end click
	
	$("#putHeaderBtn").on("click", function(){
		var boardNo = $("#boardNo");
		var title 	= $("title");
		var content = $("content");
		var writer 	= $("writer");
		
		var boardNoVal = boardNo.val();
		var titleVal = title.val(); 	
		var contentVal = content.val();
		var writerVal = writer.val();
			
		
		var boardObject = {
			boardNo:boardNoVal,
			title:titleVal,
			content:contentVal,
			writer:writerVal
		}
		
		//X-HTTP
		$.ajax({
			type:"put",
			url:"/board/"+boardNoVal,
			headers:{"X-HTTP-Method-Override":"PUT"},
			data:JSON.stringify(boardObject),
			contentType:"application/json;charset=utf-8",
			success:function(result){
				console.log("result : " + result);
				if(result=="SUCCESS"){
					alert("SUCCESS");
				}
			}
		});
	});// end click
	
});//end $(function)
</script>
</head>
<body>
<h1>Ajax Home</h1>
<form>
	boardNo : <input type="text" name="boardNo" value="" id="boardNo" /><br>
	title : <input type="text" name="title" value="" id="title" /><br>
	content : <input type="text" name="content" value="" id="content" /><br>
	writer : <input type="text" name="writer" value="" id="writer" /><br>
	
	<div>
		<button type="button" id="pubBtn">MODIFY(put)</button>
		<!-- 헤더정보를 포함한 버튼 -->
		<button type="button" id="putHeaderBtn">MODIFY(put with header)</button>
	</div>
</form> 
</body>
</html>