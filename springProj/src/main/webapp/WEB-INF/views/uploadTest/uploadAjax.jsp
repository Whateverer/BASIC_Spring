<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/resources/js/jquery-3.6.0.js"></script>
<title>Upload with Ajax</title>
<script type="text/javascript">
//파일을 담아두는 변수
var sel_file = [];

$(function(){
	//----------------이미지 미리보기 시작----------------
	$("#input_img").on("change", handleImgFileSelect);
	
	function handleImgFileSelect(e){
		//e.target : 파일 객체		
		//e.target.files : 파일객체 안의 파일들 
		var files = e.target.files;
		//파일을 잘라서 array로 만든다.
		var filesArr = Array.prototype.slice.call(files);
		
		//f: 파일 객체
		filesArr.forEach(function(f){
			//미리보기는 이미지만 가능함			
			if(!f.type.match("image.*")){
				alert("이미지만 가능합니다.");
				return;
			}
		
			//파일객체 복사
			sel_file.push(f);
            // 파일을 읽어주는 객체 생성
            var reader = new FileReader();
            reader.onload = function(e){
               //하나일 때
               //$("#img").attr("src", e.target.result);
               
               //forEach 반복 하면서 img 객체 생성
               //반복될 때마다 img가 새로 생성된다.
               var img_html = "<img src=\"" + e.target.result + "\" />";
               $(".img_wrap").append(img_html);
            }
            reader.readAsDataURL(f);
         });
	}
	//----------------이미지 미리보기 끝----------------
	//모든 파일명.확장자(exe|sh|zip|alx)는 업로드를 못하도록 막기 
	//첨부파일의 확장자가 exe, sh, zip, alz 경우 업로드를 제한
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alx)$");
	//최대 5MB까지만 업로드 가능
	var maxSize = 5242880; //5MB
	//확장자, 크기 체크
	function checkExtension(fileName, fileSize){
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드할 수 없습니다.");
			return false;
		}
		//체크 통과
		return true;
	}
	
	//Upload 버튼 클릭 시 수행
	$("#uploadBtn").on("click", function(e){
		//FormData : 가상의 <form> 태그
		//Ajax를 이용하는 파일 업로드는 FormData를 이용
		var formData = new FormData();
		//<input type="file" 요소
		var inputFile = $("input[name='uploadFile']");
		//<input type="file" 요소 내의 이미지들
		var files = inputFile[0].files;
		
// 		console.log(files);
		
		for(var i=0; i<files.length;i++){
			console.log(files[i]);
			//확장자, 크기 체크
			//function checkExtension(fileName, fileSize){
			if(!checkExtension(files[i].name, files[i].size)){//!true라면 실패
				return false;
			}
			formData.append("uploadFile", files[i]);
		}
	
		
		//없어?카드가?또?
		// 아리가또 아포가또
		//processData, contentType은 반드시 false여야 전송됨
		$.ajax({
			url:'/uploadTest/uploadAjaxAction',
			processData: false,
			contentType: false,
			data: formData,
			type:'POST',
			success: function(result){
				//JSON을 string으로 변환해서 console에 찍어보기
				console.log("result : " + JSON.stringify(result));
			}
		});
	});
	
});
</script>
</head>
<body>
<h1>Upload with Ajax</h1>

<div class="uploadDiv">
	<input type="file" id="input_img" name="uploadFile" multiple>
</div>

<button id="uploadBtn">Upload</button>
<div class="img_wrap">
	
</div>
</body>
</html>