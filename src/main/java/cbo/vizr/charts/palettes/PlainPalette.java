package cbo.vizr.charts.palettes;

import java.util.ArrayList;

import cbo.vizr.charts.DataSet;

public class PlainPalette extends Palette {

	private Color c;



	public PlainPalette(Color c) {
		super();
		this.c=c;
	}



	@Override
	public void applyPalette(DataSet ds) {


		ds.setBackgroundColor(new ArrayList<>());
		ds.setBorderColor(new ArrayList<>());


		for(@SuppressWarnings("unused") Double d : ds.getData()){
			ds.getBackgroundColor().add(c.toRgba());
			ds.getBorderColor().add(c.toRgb());
		}
	}


}
