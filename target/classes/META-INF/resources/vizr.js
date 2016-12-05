function loadChart(url, chart){

	var xmlhttp = new XMLHttpRequest();

	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var chartData = JSON.parse(this.responseText);
			updateChart(chartData, chart);
		}
	};


	xmlhttp.open("GET", url, true);
	xmlhttp.setRequestHeader("Accept","application/json");
	xmlhttp.setRequestHeader("Content-Type","application/json")
	xmlhttp.send();
	

}

function updateChart(chartData, chart) {
	var ctx = document.getElementById(chart);
	var myChart2 = new Chart(ctx, chartData);
	
	if(chartName != null){
		chartName.innerHTML = chartData.name;
	}
}
