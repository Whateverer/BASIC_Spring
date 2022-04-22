<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- 구글 차트 -->
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<title>구글 차트 01</title>
<script type="text/javascript">
//구글 차트 라이브러리 로딩
google.load("visualization", "1",{
	"packages":["corechart"]
});

//Callback : 구글 차트 로딩이 완료가 된 후에 drawChar라는 함수를 실행(파라미터는 없음)
//responseText : json 데이터를 텍스트로 읽어들임
//JSON.stringify(j/s객체) : javascript 객체를 json 데이터를 읽어들임
google.setOnLoadCallback(drawChart);

function drawChart(){
	var jsonData = $.ajax({
		url: "/resources/json/sampleData.json",
		dataType:"json",
		async:false
	}).responseText;
	
	console.log("jsonData : " + jsonData);
	
	//1) 데이터 테이블 생성
	var data = new google.visualization.DataTable(jsonData);
	
	//2) 차트 출력할 영역 지정
	var chart = new google.visualization.ColumnChart(document.getElementById("chart_div"));
	//1) + 2) => 차트 출력! 차트객체.draw(데이터테이블, 옵션)
	//curveType : "function" => 곡선
	chart.draw(data,{
		title:"차트 예제",
		curveType:"function",
		width:600,
		height:440
	})
}
</script>

</head>
<body>
<div id="chart_div"></div>
</body>
</html>