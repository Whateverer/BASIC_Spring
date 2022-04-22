<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
	function fn_submit(){
		var frm = document.searchForm;
		console.log("frm.size : " + frm.size.value);
		console.log("frm.keyWord : " + frm.keyWord.value);
		frm.submit();
	}
</script>

<!-- Page Heading -->
<h1 class="h3 mb-2 text-gray-800">상품분류 별 거래처 목록</h1>
<p class="mb-4">상품분류 별로 거래처의 목록을 화면에 보여줍니다.</p>

<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4">
            <!-- 검색 폼 영역 시작 -->
            <form name="searchForm" id="searchForm" action="/lprod/list" method="get">
            <div class="row"><div class="col-sm-12 col-md-6"><div class="dataTables_length" id="dataTable_length"><label>Show <select id="size" name="size" aria-controls="dataTable" class="custom-select custom-select-sm form-control form-control-sm"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> entries</label></div></div><div class="col-sm-12 col-md-6"><div id="dataTable_filter" class="dataTables_filter">
            <label>Search:
            <input type="search" name="keyWord" id="keyWord" class="form-control form-control-sm" placeholder="검색어를 입력해주세요" aria-controls="dataTable" value="${param.keyWord}"></label>
            &nbsp;
            <a href="#" class="btn btn-primary btn-icon-split" onclick="fn_submit()">
                <span class="icon text-white-50">
                    <i class="fas fa-check"></i>
                </span>
                <span class="text">검색</span>
            </a>
            </div></div>
            </div>
            </form>
            <!-- 검색 폼 영역 끝 -->
            <div class="row"><div class="col-sm-12"><table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
                <thead>
                <tr role="row">
                    <th class="sorting sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" style="width: 50px;">번호</th>
                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 200px;">상품분류코드</th>
                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending">상품분류명</th>
                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending" style="width: 200px;">거래처코드</th>
                    <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending">거래처명</th>
                </tr>
                </thead>
                <tfoot>
                 <tr>
                 	<th rowspan="1" colspan="1">번호</th>
                    <th rowspan="1" colspan="1">상품분류코드</th>
                    <th rowspan="1" colspan="1">상품분류명</th>
                    <th rowspan="1" colspan="1">거래처코드</th>
                    <th rowspan="1" colspan="1">거래처명</th>
                 </tr>
                </tfoot>
                <tbody>
                <!-- 시작 번호 초기화(시작번호 -1로 초기화해줘야 함) -->
                <c:set var="i" value="${list.currentPage*7-6-1}" />
                <!-- param.currentPage가 null일때 보정.. -->
<%--                                     <c:if test="${param.currentPage==null}"> --%>
<%--                                     	<c:set var="i" value="0" /> --%>
<%--                                     </c:if> --%>
                <c:forEach var="lprodVO" items="${list.content}">
                <c:forEach var="buyerVO" items="${lprodVO.buyerVO}">
                <c:set var="cnt" value="${i=i+1}" />
                <c:if test="${cnt%2==1}">
               	<tr class="odd">
               	</c:if>
               	<c:if test="${cnt%2==0}">
               	<tr class="even">
               	</c:if>
                      <td class="sorting_1">${cnt}</td>
                      <td>${lprodVO.lprodGu}</td>
                      <td><a href="/lprod/detail?buyerId=${buyerVO.buyerId}">${lprodVO.lprodNm}</a></td>
                      <td>${buyerVO.buyerId}</td>
                      <td>${buyerVO.buyerName}</td>
                 </tr>
                 </c:forEach>
                 </c:forEach>
                </tbody>
            </table>
            </div>
            </div>
            <div class="row">
            <div class="col-sm-12 col-md-5">
            <div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">
            <!-- A*7-6	A*7 -->
            Showing ${list.currentPage*7-6} to ${list.currentPage*7} of ${total} entries
            </div>
            </div>
            <div class="col-sm-12 col-md-7">
            <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
            <!-- 페이징 처리 시작 -->
             <ul class="pagination">
             	<!-- Previous 시작 -->
              <li class="paginate_button page-item previous <c:if test='${list.startPage<6}'>disabled</c:if>" id="dataTable_previous"><a href="/lprod/list?currentPage=${list.startPage-5}" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Previous</a></li>
              <!-- Previous 끝 -->
              <!-- Page번호 시작 -->
              <c:forEach var="pNo" begin="${list.startPage}" end="${list.endPage}" step="1">
              <li class="paginate_button page-item <c:if test='${list.currentPage eq pNo}'>active</c:if>"><a href="/lprod/list?currentPage=${pNo}" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">${pNo}</a></li>
              </c:forEach>
              <!-- Page번호 끝 -->
              <!-- Next 시작 -->
              <li class="paginate_button page-item next <c:if test='${list.endPage>=list.totalPages}'>disabled</c:if>" id="dataTable_next"><a href="/lprod/list?currentPage=${list.startPage+5}" aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link">Next</a></li>
              <!-- Next 끝 -->
             </ul>
         <!-- 페이징 처리 끝 -->
            </div></div></div></div>
        </div>
    </div>
</div>



    