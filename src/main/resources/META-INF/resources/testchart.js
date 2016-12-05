var xAxis = null;
var yAxis = [];

function xChanged( radio){

	xAxis = radio.toElement.value;

	fromForm();
}

function yChanged( checkBox){
	var newYAxis = checkBox.toElement.value;

	var formerIndex = yAxis.indexOf(newYAxis);
	if (formerIndex > -1) {
		yAxis.splice(formerIndex, 1);
	}

	if(checkBox.toElement.checked){
		yAxis.push(newYAxis);
	}

	fromForm();
}

function typeChanged(){
	fromForm();
}

function fromForm(){
	

	
	var sql = sqlInput.value;
	var type = document.getElementById("type").value;

	testChart(sql, xAxis, yAxis, type);
}

function selectAxis(){
	
	if(xAxis != null){
		document.getElementById("x"+xAxis).checked = true;
		
	}
}

function executeSql(){
	
	
	var sql  = sqlInput.value;
	xAxis = null;
	yAxis = [];

	var url = "/vizr/sql/request?sql="+sql;


	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var sqlResult = JSON.parse(this.responseText);

			readSQLResult(sqlResult);

		}
	};


	xmlhttp.open("GET", url, true);
	xmlhttp.setRequestHeader("Accept","application/json");
	xmlhttp.setRequestHeader("Content-Type","application/json")
	xmlhttp.send();
}


function readSQLResult(sqlResult){

	sqlData.innerHTML = "";

	if(sqlResult.error){
		sqlMessage.innerHTML = "";
		sqlError.innerHTML = sqlResult.message;

		return;
	}

	sqlMessage.innerHTML = sqlResult.message;
	sqlError.innerHTML = "";

	var tbl = document.createElement('table');
	var tbdy = document.createElement('tbody');
	//headers
	var tr = document.createElement('tr');
	var td = document.createElement('td');
	td.className='legend';
	td.innerHTML = 'Column';
	tr.appendChild(td)

	var arrayLength = sqlResult.headers.length;
	for (var i = 0; i < arrayLength; i++) {
		var th = document.createElement('th');
		th.innerHTML = sqlResult.headers[i];
		tr.appendChild(th);
	}
	tbdy.appendChild(tr);

	//xAxis
	var trX = document.createElement('tr');
	var tdX1 = document.createElement('td');
	tdX1.className='legend';
	tdX1.innerHTML = 'X axis';
	trX.appendChild(tdX1)

	for (var i = 0; i < arrayLength; i++) {
		var tdd = document.createElement('td');
		var radio = document.createElement('input');
		
		radio.type='radio';
		radio.addEventListener("click", xChanged);
		radio.value=sqlResult.headers[i];	
		radio.name="XAX";
		radio.id = "x"+sqlResult.headers[i];
		
		if(xAxis === sqlResult.headers[i])
			radio.checked=true;
		
		tdd.appendChild(radio);
		
		trX.appendChild(tdd);
	}
	tbdy.appendChild(trX);
	
	//yAxis
	var trY = document.createElement('tr');
	var tdY1 = document.createElement('td');
	tdY1.className='legend';
	tdY1.innerHTML = 'Y axis';
	trY.appendChild(tdY1)

	for (var i = 0; i < arrayLength; i++) {
		var tdy = document.createElement('td');
		var checkbox = document.createElement('input');
		
		checkbox.type='checkbox';
		checkbox.addEventListener("click", yChanged);
		checkbox.value=sqlResult.headers[i];
		checkbox.id="y"+sqlResult.headers[i];
		
		if(yAxis.indexOf(sqlResult.headers[i]) != -1){
			checkbox.checked = true;
		}

		tdy.appendChild(checkbox);
		
		trY.appendChild(tdy);
	}
	tbdy.appendChild(trY);
	
	
	tbl.appendChild(tbdy);
	sqlData.appendChild(tbl);



}
function saveChart(){
	
	var sql = sqlInput.value;
	var type = document.getElementById("type").value;
	var name = nameInput.value ;
	
	
	var json = JSON.stringify({name:name, axisX:xAxis, axisY:yAxis, sql:sql, type:type});

	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			window.location.replace("/charts?chartName="+name);
		}
	};


	xmlhttp.open("POST", "/vizr/sql/save", true);
	xmlhttp.setRequestHeader("Accept","application/json");
	xmlhttp.setRequestHeader("Content-Type","application/json")
	xmlhttp.send(json);
	
}

function testChart(sql, xAxis, yAxis, type){

	var json = JSON.stringify({name:'test', axisX:xAxis, axisY:yAxis, sql:sql, type:type});

	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var myArr = JSON.parse(this.responseText);

			var fieldset = document.getElementById('fs');
			var canvas = document.getElementById('test');
			fieldset.removeChild(canvas);

			var newCanvas = document.createElement("canvas");
			newCanvas.id = 'test';
			fieldset.appendChild(newCanvas);

			updateChart(myArr, 'test');
		}
	};


	xmlhttp.open("POST", "/vizr/sql/", true);
	xmlhttp.setRequestHeader("Accept","application/json");
	xmlhttp.setRequestHeader("Content-Type","application/json")
	xmlhttp.send(json);
}

function SQLChanged(){
	var sqlString = sqlInput.value.trim();
	sqlString =sqlString.replace(/\s+/g,' ').replace(/^\s+|\s+$/,'');
	
	SQLDIV.innerHTML = sqlString;
	
	hljs.highlightBlock(SQLDIV);
	
	executeSql();
}