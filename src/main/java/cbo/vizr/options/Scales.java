package cbo.vizr.options;

import java.util.ArrayList;
import java.util.List;

public class Scales {

	private List<Scale> yAxes = new ArrayList<>();
	private List<Scale> xAxes = new ArrayList<>();
	
	public Scales(){
		yAxes.add(new Scale());
	}
	
	public List<Scale> getyAxes() {
		return yAxes;
	}
	public void setyAxes(List<Scale> yAxes) {
		this.yAxes = yAxes;
	}
	public List<Scale> getxAxes() {
		return xAxes;
	}
	public void setxAxes(List<Scale> xAxes) {
		this.xAxes = xAxes;
	}
	
	
}
