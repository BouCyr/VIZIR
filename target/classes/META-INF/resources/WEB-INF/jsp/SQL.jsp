
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
			<fieldset id="sqlFS">
				<legend>Request</legend>
				<form id="sqlForm" action="/sql" method="POST">
					<textarea name="sql" rows="8" cols="90">
			<c:out value='${SQL}' />
		</textarea>
					<input type="submit" value="GO" /><br />


					<c:out value='${nbRows}' />
					rows.<br />
				</form>
			</fieldset>

			<form:form method="post" modelAttribute="chart" action="/saveChart">
				<fieldset id="charts">
					<legend>Rows</legend>
					
						<form:input type="hidden" name="sql" path="sql" />
						<table>
							<tr>
								<td class="legend">Column</td>
								<c:forEach items='${colNames}' var="name">
									<th><c:out value='${name}' /></th>
								</c:forEach>
							</tr>

							<tr>
								<td class="legend">X Axis</td>
								<c:forEach items='${colNames}' var="name">
									<td><form:radiobutton onclick="xChanged(this);"
											path="axisX" value="${name}" /><br></td>
								</c:forEach>
							</tr>

							<tr>
								<td class="legend">Y Axis</td>
								<c:forEach items='${colNames}' var="name">
									<td><form:checkbox onclick="yChanged(this);" path="axisY"
											value="${name}" /><br></td>
								</c:forEach>
							</tr>

							<c:forEach items='${lines}' var="line">
								<tr>
									<td class="legend"></td>
									<c:forEach items='${line}' var="cell">
										<td><c:out value='${cell}' /></td>
									</c:forEach>
								</tr>

							</c:forEach>
						</table>

						<form:select id="type" path="type" onchange="typeChanged();">
							<form:options items="${types}" itemValue="type"></form:options>
						</form:select>
						<br />

					
				</fieldset>


				<div id="charts"">
					<fieldSet id="fs">
						<canvas id="test" />
					</fieldSet>
				</div>
				<div id="save">
					<form:input path="name" />
					<input type="submit" value="SAUVER" />
				</div>
			</form:form>
		</div>

	</div>
</body>
</html>