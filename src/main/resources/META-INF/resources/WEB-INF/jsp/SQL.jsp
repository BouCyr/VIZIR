
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="vz" uri="/WEB-INF/vizr.tld"%>
<html>

<head>
<title>Create chart</title>
<link rel="stylesheet" type="text/css" href="/style.css">
<link rel="stylesheet" href="/vizirHL.css">
<script src="/highlight.pack.js"></script>
<script src="/Chart.bundle.js"></script>
<script src="/vizr.js"></script>
<script src="/testchart.js"></script>
<script>hljs.initHighlightingOnLoad();</script>

</head>

<script>
	function displayChart(chartElement ) {
		

		window.location="/sql?chartName="+chartElement.id;
		
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

				<c:forEach var="chartFromDb" items="${provider.charts}">
					<p class="${chart.name eq chartFromDb ? 'chart selected' : 'chart' }" id="${chartFromDb}" provider="${provider.name}"
						onclick="displayChart(this);">
						<c:out value="${chartFromDb}" />
					</p>
				</c:forEach>
			</c:forEach>



		</div>


		<div id="charts">
	
			<div class="header">
				<a href="/charts" class="topLink ">All charts</a>
				<a href="/sql" class="topLink selected">SQL charts</a>
				<a href="/dashboard" class="topLink">Dashboards</a>
			</div>
			
			<form:form method="GET" modelAttribute="chart" action="/sql">
			
				<fieldset id="sqlFS">
					<legend>Chart metadata</legend>
					<div class="inputHolder">
						<label for="nameInput">Name</label><form:input path="name" id="nameInput" />
					</div>
				</fieldset>
			
				<fieldset id="sqlFS">
					<legend>Request</legend>

					<textarea id="sqlInput" oninput="SQLChanged()" name="sql" rows="8" cols="90">
						<c:out value='${SQL}' />
					</textarea>
					
					<div>
						<code id="SQLDIV" class="sql"></code>
						<div id="sqlError"></div>
					</div>	
				</fieldset>


				<fieldset>
					<legend>Rows</legend>
					<div id="sqlMessage"></div>
					<div id="sqlData"></div>
					<form:input type="hidden" name="sql" path="sql" />

					<form:select id="type" path="type" onchange="typeChanged();">
						<form:options items="${types}" itemValue="type"></form:options>
					</form:select>
					<br />


				</fieldset>


				<div id="charts" class="testChart">
					<fieldSet id="fs" class="testChart">
						<canvas id="test" class="testChart" />
					</fieldSet>
				</div>
				<div class="actions">
					<button type="button" class="cancelButton" onclick="window.location.replace('/sql');return true;" >Cancel</button>
					<button type="button" class="okButton" onclick="saveChart();">Sauver</button>
				</div>
			</form:form>
		</div>
	</div>
</body>

<script>
<c:if test="${not empty SQL}">




	executeSql();
	SQLChanged();
	
	xAxis = '${chart.axisX}';
	yAxis=[<c:forEach var="y" items="${chart.axisY}">'<c:out value="${y}" />',</c:forEach>];
	
	fromForm();
	
</c:if>
</script>

</html>