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
	function displayChart(selectName ) {
		
		var fieldset = document.getElementById('fs');
		var canvas = document.getElementById('test');
		fieldset.removeChild(canvas);

		var newCanvas = document.createElement("canvas");
		newCanvas.id = 'test';
		fieldset.appendChild(newCanvas);

		loadChart('/vizr/chart/' + selectName, 'test');

		document.getElementById('chartName').innerHTML=selectName;
		
	}
</script>
<body>

	<div id="main">
		<div id="select">


			<c:forEach var="provider" items="${providers}">
				<p class="provider">
					<c:out value="${provider.name}" />
				</p>

				<c:forEach var="chart" items="${provider.charts}">
					<p class="chart" onclick="displayChart('${chart}');">
						<c:out value="${chart}" />
					</p>
				</c:forEach>
			</c:forEach>



		</div>
		<div id="charts">
			
			<div style="flex-grow: 10;">
				
				<fieldSet style="height: 400px;" id="fs">
					<legend id="chartName"></legend>
					<vz:line name="test" load="false" />
				</fieldSet>
			</div>
		</div>
	</div>

</body>


</html>