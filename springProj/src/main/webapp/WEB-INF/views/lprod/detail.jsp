<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
      var sel_file;
      $(function(){
         //------------- 이미지 미리보기 시작 ------------------
         $("#input_img").on("change", handleImgFileSelect);
         
         function handleImgFileSelect(e){
            //e.target : 파일객체
            //e.target.files : 파일객체 안의 파일들
            var files = e.target.files;
            var filesArr = Array.prototype.slice.call(files);
            
            //f : 파일 객체
            filesArr.forEach(function(f){
               //미리보기는 이미지만 가능함
               if(!f.type.match("image.*")){
                  alert("이미지만 가능합니다");
                  return;
               }
               
               //파일객체 복사
//                sel_file.push(f);
               //파일을 읽어주는 객체 생성
               var reader = new FileReader();
               reader.onload = function(e){
                  //forEach 반복 하면서 img 객체 생성
   //                var img_html = "<img src=\"" + e.target.result + "\" />";
                  $(".b-image").css({"background":"url(" + e.target.result + ")"
                           ,"background-size":"contain"
                           ,"background-repeat":"no-repeat"
                           ,"background-position":"center"});
               }
               reader.readAsDataURL(f);
            });
         }
         //------------- 이미지 미리보기 끝 ------------------
         
         $(".b-image").on("click", function(){
        	 $("#ifrm").prop("src","/download?fileName=${buyerVO.buyerImg}");
          });
         
      });
   
      function fn_submit(){
         var frm = document.searchForm;
         console.log("frm.size : " + frm.size.value);
         console.log("frm.keyWord : " + frm.keyWord.value);
         frm.submit();
      }
      

   </script>

<div class="card o-hidden border-0 shadow-lg my-5">
          <div class="card-body p-0">
              <!-- Nested Row within Card Body -->
    <form:form modelAttribute="buyerVO" class="user" method="post" action="/lprod/detail"
       enctype="multipart/form-data">
    <div class="row">
       <!-- 이미지 -->
        <div class="col-lg-5 d-none d-lg-block b-image"
         style="background-image: url('/resources${buyerVO.buyerImg}');
                                      background-size: contain;
                                      background-repeat: no-repeat;
                                      background-position:center;
                cursor:pointer;">
           <input type="file" name="uploadFile" id="input_img" />
        </div>
        <iframe id="ifrm" name="ifrm" style="display:none;"></iframe>
        <!-- 정보 출력 영역 -->
        <div class="col-lg-7">
            <div class="p-5">
                <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">${buyerVO.buyerName}</h1>
                    </div>
                        <div class="form-group row">
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <form:input path="buyerId" class="form-control form-control-user" placeholder="" />
                            </div>
                            <div class="col-sm-6">
                                <form:input path="lprodNm" class="form-control form-control-user" placeholder="" />
                            </div>
                        </div>
                        <div class="form-group">
                            <form:input path="buyerMail" class="form-control form-control-user" placeholder="" />
                            <font color="red"><form:errors path="buyerMail" /></font>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <form:input path="buyerComtel" class="form-control form-control-user" placeholder="" />
                                <font color="red"><form:errors path="buyerComtel" /></font>
                            </div>
                            <div class="col-sm-6">
                                <form:input path="buyerCharger" class="form-control form-control-user" placeholder="" />
                                <font color="red"><form:errors path="buyerCharger" /></font>
                            </div>
                        </div>
                        <a href="#" id="btnEdit" class="btn btn-primary btn-user btn-block">
                               수정
                        </a>
                        <a href="#" id="btnConfirm" style="display:none;" class="btn btn-success btn-icon-split btn-block">
                            <span class="icon text-white-50">
                                <i class="fas fa-check"></i>
                            </span>
                            <span class="text">확인</span>
                        </a>
                        <a href="#" id="btnCancel" style="display:none;" class="btn btn-danger btn-icon-split btn-block">
                            <span class="icon text-white-50">
                                <i class="fas fa-trash"></i>
                            </span>
                            <span class="text">취소</span>
                        </a>
                        <hr>
                        <a href="/lprod/list" class="btn btn-google btn-user btn-block">
                            <i class="fab fa-google fa-fw"></i> 목록보기
                        </a>
                   
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
         </form:form>
    </div>
</div>
   
    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">수정 요청</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
                    <a class="btn btn-primary" id="modalConfirm" href="#">확인</a>
                </div>
            </div>
        </div>
    </div>

    

   <script type="text/javascript">
      $(function(){
         $(".form-control-user").prop("readonly",true);
         //수정버튼 클릭 시 수정버튼 사라지고, 확인/취소버튼 보임
         $("#btnEdit").on("click",function(){
            $(".form-control-user").prop("readonly",false);
            //수정 대상이 아닌 요소 처리
            //disabled -> true(비활성) (x) => form으로 안넘어감..
            $("#buyerId").prop("readonly",true);
            $("#lprodNm").prop("readonly",true);
            //수정버튼 사라지고
            $("#btnEdit").css("display","none");
            //확인/취소버튼 보임
            $("#btnConfirm").css("display","block");
            $("#btnCancel").css("display","block");
         });
         //취소버튼 클릭 시 새로고침 처리
         $("#btnCancel").on("click",function(){
            //참여수업 짱!!
            location.reload();
         });
         //확인버튼 클릭 시 수정 요청
         $("#btnConfirm").on("click",function(){
            $(".modal-body").html("해당 글을 수정 하시겠습니까?");
            $("#logoutModal").modal("show");
         });
         
         //모달 확인 버튼 클릭 시 수정 프로세스 진행
         $("#modalConfirm").on("click",function(){
            $("#buyerVO").submit();
         });
      });
   </script>





