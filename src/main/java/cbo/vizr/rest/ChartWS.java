package cbo.vizr.rest;

import java.util.List;
import java.util.Random;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cbo.vizr.ChartProvider;
import cbo.vizr.charts.ChartCreationException;
import cbo.vizr.charts.RestChart;

@RestController
@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
produces=MediaType.APPLICATION_JSON, 
path="/vizr")
public class ChartWS {
	Random r = new Random();

	@Autowired
	List<ChartProvider> allProviders;



	@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
			produces=MediaType.APPLICATION_JSON, 
			method=RequestMethod.GET, 
			path="/chart/{name}")
	public RestChart chart(@PathVariable(value="name", required=true  )String name) throws ChartCreationException{

		for(ChartProvider provider : allProviders){
			for(String chartName : provider.getChartNames()){
				if(chartName.equals(name)){
					return provider.getChart(chartName);
				}
			}
		}

		return null;

	}
}

