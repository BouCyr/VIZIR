package cbo.vizr.charts;

import java.util.ArrayList;
import java.util.List;

public class Data {
	private List<String> labels = new ArrayList<>();
	private List<DataSet> datasets = new ArrayList<>();

	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public List<DataSet> getDatasets() {
		return datasets;
	}
	public void setDatasets(List<DataSet> datasets) {
		this.datasets = datasets;
	}
}
