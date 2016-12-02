
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="vz" uri="/WEB-INF/vizr.tld"%>
<html>

<head>
<title>Create chart</title>
<link rel="stylesheet" type="text/css" href="/style.css">
<script src="/Chart.bundle.js"></script>
<script src="/vizr.js"></script>
<script src="/testchart.js"></script>
</head>




<body>
	<div id="main">
		<div id="select"></div>


		<div id="chartCreation">
			<form:form method="post" modelAttribute="chart" action="/saveChart">
				<fieldset id="sqlFS">
					<legend>Request</legend>

					<textarea id="sqlInput" name="sql" rows="8" cols="90">
						<c:out value='${SQL}' />
					</textarea>
					<div id="sqlError"></div>
					<button type="button" onclick="executeSql();">Execute</button>
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
				<div id="save">
					<label for="nameInput">Name</label><input type="text" id="nameInput" />
					<button type="button" onclick="saveChart();">Sauver</button>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>