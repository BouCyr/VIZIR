var xAxis = null;
var yAxis = [];

function xChanged( radio){
	
	xAxis = radio.value;
	
	fromForm();
}

function yChanged( checkBox){
	var newYAxis = checkBox.value;
	
	var formerIndex = yAxis.indexOf(newYAxis);
	if (formerIndex > -1) {
		yAxis.splice(formerIndex, 1);
	}
	
	if(checkBox.checked){
		yAxis.push(newYAxis);
	}
	
	fromForm();
}

function typeChanged(){
	fromForm();
}

function fromForm(){
	var sql = document.getElementById("sql").value;
	var type = document.getElementById("type").value;
	
	testChart(sql, xAxis, yAxis, type);
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