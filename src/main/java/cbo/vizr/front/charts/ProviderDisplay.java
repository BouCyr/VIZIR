package cbo.vizr.front.charts;

import java.util.ArrayList;
import java.util.List;

import cbo.vizr.ChartProvider;

public class ProviderDisplay {

	private String name ;
	private List<String> charts = new ArrayList<>();
	
	public ProviderDisplay(ChartProvider provider){
		this.setName((provider.getName()));
		this.setCharts(provider.getChartNames());
	}
	
	public ProviderDisplay(String providerName) {
		super();
		this.setName(providerName);
	}

	

	public List<String> getCharts() {
		return charts;
	}

	public void setCharts(List<String> charts) {
		this.charts = charts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
