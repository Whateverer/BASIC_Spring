<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<title>회원목록</title>
</head>
<body>
<!-- 
varStatus
*1) count : 1부터 순서적으로 1씩 증가
*2) index : 0부터 순서적으로 1씩 증가
3) current : 현재의 아이템
4) first : 처음 반복되는 것인지 반환
5) last : 마지막 반복인지 반환
6) begin : 시작값
7) end : 끝값
8) step : 증가값
-->
<div class="card shadow mb-4">
 <div class="card-header py-3">
     <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
 </div>
 <div class="card-body">
     <div class="table-responsive">
         <div id="dataTable_wrapper" class="dataTables_wrapper dt-bootstrap4"><div class="row"><div class="col-sm-12 col-md-6"><div class="dataTables_length" id="dataTable_length"><label>Show <select name="dataTable_length" aria-controls="dataTable" class="custom-select custom-select-sm form-control form-control-sm"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> entries</label></div></div><div class="col-sm-12 col-md-6"><div id="dataTable_filter" class="dataTables_filter"><label>Search:<input type="search" class="form-control form-control-sm" placeholder="" aria-controls="dataTable"></label></div></div></div><div class="row"><div class="col-sm-12"><table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
             <thead>
                 <tr role="row">
	                 <th class="sorting sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" style="width: 10%;">번호</th>
	                 <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 15%;">이름</th>
	                 <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 20%;">이메일</th>
	                 <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending" style="width: 20%;">가입일</th>
	                 <th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" style="width: 40%;">보유 카드번호</th>
	             </tr>
                </thead>
                <tbody>
                <c:forEach var="memberVO" items="${list}" varStatus="stat">
                	<tr class="odd">
                        <td class="sorting_1">${stat.count}</td>
                        <td><a href="/member/detail/${memberVO.memberid}">${memberVO.name}</a></td>
                        <td>${memberVO.email}</td>
                        <td><fmt:formatDate value="${memberVO.regdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                        <td>
							<c:forEach var="cardVO" items="${memberVO.cardList}">
								${cardVO.no}&nbsp;
							</c:forEach>
						</td>
                    </tr>
                </c:forEach>    
                </tbody>
            </table></div></div><div class="row"><div class="col-sm-12 col-md-5"><div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">Showing 1 to 10 of 57 entries</div></div><div class="col-sm-12 col-md-7"><div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate"><ul class="pagination"><li class="paginate_button page-item previous disabled" id="dataTable_previous"><a href="#" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Previous</a></li><li class="paginate_button page-item active"><a href="#" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">1</a></li><li class="paginate_button page-item "><a href="#" aria-controls="dataTable" data-dt-idx="2" tabindex="0" class="page-link">2</a></li><li class="paginate_button page-item "><a href="#" aria-controls="dataTable" data-dt-idx="3" tabindex="0" class="page-link">3</a></li><li class="paginate_button page-item "><a href="#" aria-controls="dataTable" data-dt-idx="4" tabindex="0" class="page-link">4</a></li><li class="paginate_button page-item "><a href="#" aria-controls="dataTable" data-dt-idx="5" tabindex="0" class="page-link">5</a></li><li class="paginate_button page-item "><a href="#" aria-controls="dataTable" data-dt-idx="6" tabindex="0" class="page-link">6</a></li><li class="paginate_button page-item next" id="dataTable_next"><a href="#" aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link">Next</a></li></ul></div></div></div></div>
        </div>
    </div>
</div>

<table border="1">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>이메일</th>
		<th>가입일</th>
		<th>보유카드번호</th>
	</tr>
	<!-- items에는 모델의 속성명 -->
	
	<tr>
		<td>${stat.count}</td>
		<td>${memberVO.name}</td>
		<td>${memberVO.email}</td>
		<td><fmt:formatDate value="${memberVO.regdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>
			<c:forEach var="cardVO" items="${memberVO.cardList}">
				${cardVO.no}&nbsp;
			</c:forEach>
		</td>
		
	</tr>
	
</table>

</body>
</html>