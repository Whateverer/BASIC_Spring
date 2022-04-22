<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<title>좋아하는 과일 판매가 차트</title>
<script type="text/javascript">
//구글 차트 라이브러리 로딩
google.load("visualization", "1",{
	"packages":["corechart"]
});

google.setOnLoadCallback(drawChart);

function drawChart(){
	var jsonData = $.ajax({
		url:"/resources/json/fruitData.json",
		dataType:"json",
		async:false
	}).responseText;
	
	console.log("jsonData : " + jsonData);
	
	//차트객체
	var data = new google.visualization.DataTable(jsonData);
// 	var chart = new google.visualization.LineChart(document.getElementById("chart_div"))
	var chart = new google.visualization.PieChart(document.getElementById("chart_div"))
	
	chart.draw(data,{
		title:"좋아하는 과일 판매가 차트",
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