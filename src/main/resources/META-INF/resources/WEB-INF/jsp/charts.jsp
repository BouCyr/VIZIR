<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="vz" uri="/WEB-INF/vizr.tld"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Existing charts</title>
<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<script src="/Chart.bundle.js"></script>
<script src="/vizr.js"></script>

<script>
	var selected = null;

	function displayChart(chartElement ) {
		

		
		if(selected != null){
			selected.className = 'chart';
		}
		
		selected = chartElement;
		selected.className += ' selected';
		
		var fieldset = document.getElementById('fs');
		var canvas = document.getElementById('test');
		fieldset.removeChild(canvas);

		var newCanvas = document.createElement("canvas");
		newCanvas.id = 'test';
		fieldset.appendChild(newCanvas);

		loadChart('/vizr/chart/' + chartElement.id, 'test');
		
	}
</script>
<body>

	<div id="main">

		<div id="left">
			
			<div class="logo">
				<img src="/VIZIRLOGO.png" />
			</div>
			<c:forEach var="provider" items="${providers}">
				<p class="provider" id="${provider.name}">
					<c:out value="${provider.name}" />
				</p>

				<c:forEach var="chart" items="${provider.charts}">
					<p class="chart" id="${chart}" provider="${provider.name}"
						onclick="displayChart(this);">
						<c:out value="${chart}" />
					</p>
				</c:forEach>
			</c:forEach>



		</div>
		<div id="charts">
			<div class="header">
				<a href="/charts" class="topLink selected">All charts</a>
				<a href="/sql" class="topLink">SQL charts</a>
				<a href="/dashboard" class="topLink">Dashboards</a>
			</div>
			<div style="flex-grow: 10;">
				<fieldSet style=" padding-bottom:10px;height: 400px;" id="fs">
					<legend id="chartName"></legend>
					<vz:line name="test" load="false" />
				</fieldSet>
			</div>
		</div>
	</div>

</body>
<c:if test="${loadChart}">

	<script>
	displayChart(${chartName});
</script>

</c:if>

</html>