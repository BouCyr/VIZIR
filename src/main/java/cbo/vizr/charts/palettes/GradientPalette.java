package cbo.vizr.charts.palettes;

import java.util.ArrayList;

import cbo.vizr.charts.DataSet;

public class GradientPalette extends Palette {

	private Color from;
	private Color to;
	
	public GradientPalette(Color from, Color to) {
		super();
		this.from = from;
		this.to = to;
	}
	
	@Override
	public void applyPalette(DataSet ds) {
		
		ds.setBackgroundColor(new ArrayList<>());
		ds.setBorderColor(new ArrayList<>());
		float nbx = (float) ds.getData().size();
		
		float step = 1.0f/nbx ;
		
		for(float f = 0.0f; f < ds.getData().size() ; f++){
			
			float ratio = f*step; 
			
			Color c = Color.interpolate(from, to, ratio);
			
			ds.getBackgroundColor().add(c.toRgba());
			ds.getBorderColor().add(c.toRgb());
			
		}
		
	}

}
