package cbo.vizr.charts;

import cbo.vizr.options.Scale;
import cbo.vizr.options.Scales;

public class Options {
	private boolean responsive = true;
	private boolean maintainAspectRatio=false;
	
	private Scales scales = new Scales();
	
	private Scale scale = new Scale();
	
	public boolean isResponsive() {
		return responsive;
	}
	public void setResponsive(boolean responsive) {
		this.responsive = responsive;
	}
	public boolean isMaintainAspectRatio() {
		return maintainAspectRatio;
	}
	public void setMaintainAspectRatio(boolean maintainAspectRatio) {
		this.maintainAspectRatio = maintainAspectRatio;
	}
	public Scales getScales() {
		return scales;
	}
	public void setScales(Scales scales) {
		this.scales = scales;
	}
	public Scale getScale() {
		return scale;
	}
	public void setScale(Scale scale) {
		this.scale = scale;
	}
	
	
}
