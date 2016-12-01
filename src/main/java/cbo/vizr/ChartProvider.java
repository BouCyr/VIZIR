package cbo.vizr;

import java.util.Collection;
import java.util.List;

import cbo.vizr.charts.ChartCreationException;
import cbo.vizr.charts.RestChart;

public interface ChartProvider {

	public String getName();

	public List<String> getChartNames();
	
	public RestChart getChart(String name) throws ChartCreationException;
	
	public Collection<RestChart> getCharts() throws ChartCreationException;
}
