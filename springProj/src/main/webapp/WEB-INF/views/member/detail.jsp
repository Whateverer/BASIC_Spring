<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
<!-- 회원 증명사진 이미지 시작 -->
<div class="col-lg-5 d-none d-lg-block bg-register-image"
	style="background-image:url('/resources${memberVO.memberCertPic}');background-repeat:no-repeat;cursor:pointer" 
	alt="클릭하면 다운" title="클릭하면 다운"></div>
<iframe id="ifrm" name="ifrm" style="display:none;"></iframe>

<!-- 회원 증명사진 이미지 끝 -->
    <div class="col-lg-7">
        <div class="p-5">
            <div class="text-center">
                <h1 class="h4 text-gray-900 mb-4">${memberVO.name}</h1>
            </div>
            <form class="user">
                <div class="form-group row">
                    <div class="col-sm-6 mb-3 mb-sm-0">
                        <input type="text" value="${memberVO.memberid}" class="form-control form-control-user" id="memberid" placeholder="memberid" disabled />
                    </div>
                    <div class="col-sm-6">
                        <input type="text" value="${memberVO.regdate}" class="form-control form-control-user" id="regdate" placeholder="regdate" disabled />
                    </div>
                </div>
                <div class="form-group">
                    <input type="email" value="${memberVO.email}" class="form-control form-control-user" id="email" placeholder="email" disabled />
                </div>
                <div class="form-group row" style="width:1000px;">
                    <div class="col-sm-6 mb-3 mb-sm-0" style="width:1000px;">
                        <textarea cols="100" rows="3" class="form-control form-control-user" id="introduction" placeholder="introduction" disabled />${memberVO.introduction}</textarea>
                    </div>
                </div>
                <a href="#" class="btn btn-primary btn-user btn-block">
                    	회원 정보 수정
                </a>
                <hr>
                <a href="#" class="btn btn-google btn-user btn-block">
                    <i class="fab fa-google fa-fw"></i> 회원 정보 삭제
                </a>
                <a href="/member/list" class="btn btn-facebook btn-user btn-block">
                    <i class="fab fa-facebook-f fa-fw"></i> 목록으로 이동
                </a>
            </form>
            <hr>
            <div class="text-center">
                <a class="small" href="forgot-password.html">Forgot Password?</a>
            </div>
            <div class="text-center">
                <a class="small" href="login.html">Already have an account? Login!</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
$(function(){
	$(".bg-register-image").on("click", function(){
// 		alert("${memberVO.memberCertPic}");
		
		$("#ifrm").prop("src","/download?fileName=${memberVO.memberCertPic}");
	});
});
</script>





