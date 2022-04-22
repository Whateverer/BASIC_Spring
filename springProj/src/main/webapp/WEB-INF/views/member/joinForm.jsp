<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
<link rel="stylesheet" href="/resources/css/style.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css" />
<script type="text/javascript" src="/resources/js/jquery-ui.js"></script>
<title>회원가입</title>
<style type="text/css">
	.img_wrap{
		width:300px;
		margin-up:50px;
	}
	.img_wrap img{
		max-width:100%;
	}
</style>
<script type="text/javascript">
	//다음 우편번호 검색
	function openHomeSearch(){
		new daum.Postcode({
			oncomplete:function(data){
// 				alert("data" + JSON.stringify(data));
				$('[name=postno]').val(data.zonecode);
				$('[name=addr]').val(data.address);
				$('[name=detAddr]').val(data.buildingName);
			}
		}).open();
	}
	
	$(function(){
		$("#birth").datepicker();
	});
</script>
</head>
<body>
<!-- 
	스프링 폼 태그 라이브러리
 - 스프링 폼은 HTML 폼을 표시하기 위한 태그 라이브러리 
 - 스프링 폼을 이용하면 HTML 폼과 자바 객체를 쉽게 바인딩할 수 있음
 
 path : 폼 항목에 바인딩되는 폼 객체(Controller에 있음)의 프로퍼티를 지정
 
 -->
 <script type="text/javascript">
	$(function(){
		$("#input_img").on("change",handleImgFileSelect);		
	});
	//e : change 된 이벤트 정보
	function handleImgFileSelect(e){
		//e.target : <input type="file".. 요소
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		filesArr.forEach(function(f){
			if(!f.type.match("image.*")){
				alert("확장자는 이미지 확장자만 가능합니다");
				return;
			}
			
			var reader = new FileReader();
			reader.onload = function(e){
				$("#img").attr("src",e.target.result);
			}
			reader.readAsDataURL(f);
		});
	}
 </script>
<form:form modelAttribute="memberVO" method="post" action="/member/insert"
	enctype="multipart/form-data">  
	<p>
		<div>
			<div class="img_wrap">
				<img id="img" />
			</div>
		</div>
		<div>
			<h5>회원이미지</h5>
			<input type="file" id="input_img" name="uploadFile">
		</div>
	</p>
	<p>
		아이디 : <form:input path="memberid" />
		<c:if test="${dupChkResult!=null}">
			<font color="red">
			${dupChkResult}
			</font>
		</c:if>
		<font color="red">
			<form:errors path="memberid"/>
		</font>	
	</p>
	<p>
		이름 : <form:input path="name" />
		<font color="red">
			<form:errors path="name"/>
		</font>	
	</p>
	<p>
		비밀번호 : <form:password path="password" />
		<font color="red">
			<form:errors path="password"/>
		</font>	
	</p>
	<p>
		우편번호 : <form:input path="postno" />
		<button type="button" class="btn btn-primary btn-icon-split btn-sm"
			onclick="openHomeSearch()">
            <span class="icon text-white-50">
                <i class="fas fa-flag"></i>
            </span>
            <span class="text">우편번호검색</span>
        </button>
		<font color="red">
			<form:errors path="postno"/>
		</font>	
	</p>
	<p>
		주소 : <form:input path="addr" style="width:50%;"/>
		<font color="red">
			<form:errors path="addr"/>
		</font>	
	</p>
	<p>
		상세주소 : <form:input path="detAddr" style="width:50%;"/>
		<font color="red">
			<form:errors path="detAddr"/>
		</font>	
	</p>
	<p>
		이메일 : <form:input path="email" />
		<font color="red">
			<form:errors path="email"/>
		</font>	
	</p>
	<p>
		생일 : <form:input path="birth" />
		<font color="red">
			<form:errors path="birth"/>
		</font>	
	</p>
	
	<p>
		자기소개 : 
		<form:textarea path="introduction" rows="6" cols="30" />
	</p>
	<p>
		취미 : 
		<form:checkboxes path="hobbyList" items="${hobbyMap}" />
	</p>
	<p>
		취미(hobbyArray) : 
		<form:checkbox path="hobbyArray" value="안찰수" label="안찰수" /><br>
		<form:checkbox path="hobbyArray" value="이죄명" label="이죄명" /><br>
		<form:checkbox path="hobbyArray" value="응등이탐정" label="응등이탐정" /><br>
		<form:checkbox path="hobbyArray" value="쉼상정" label="쉼상정" /><br>
	</p>
	<p>
		성별 : 
<%-- 		<form:radiobuttons path="gender" items="${genderMap}"/> --%>
		<form:radiobutton path="gender" value="Male" label="Male" />&nbsp;
		<form:radiobutton path="gender" value="Female" label="Female" />&nbsp;
		<form:radiobutton path="gender" value="Other" label="Other" />
	</p>
	<!-- <button id="register" name="register" type="submit" 
	value="Submit">등록</button> -->
	<form:button name="register">등록</form:button>
</form:form>
<script type="text/javascript">
CKEDITOR.replace("introduction");
</script>
</body>
</html>







