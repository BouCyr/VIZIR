package cbo.vizr.charts;

public class RestChart  {

	private ChartType chartType;
	
	private String name;
	private Data data = new Data();
	private Options options = new Options();
	
	protected RestChart(){
		super();
	}
	public RestChart(String name){
		super();
		
		this.setChartType(ChartType.BAR);
		this.setName(name);
	}
	



	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Options getOptions() {
		return options;
	}
	
	public void setOptions(Options options) {
		this.options = options;
	}
	
	public ChartType getChartType() {
		return chartType ;
	}
	
	public void setChartType(ChartType type){
		this.chartType = type;
	}
	
	public RestChart(ChartType type){
		super();
		this.chartType = type;
	}
	
	public String getType(){
		return chartType.getType();
	}
	
	public void setType(String type){
		this.chartType = ChartType.parse(type);
	}

	
}
