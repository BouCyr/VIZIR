package cbo.vizr.rest;

import java.sql.SQLException;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cbo.vizr.charts.ChartCreationException;
import cbo.vizr.charts.RestChart;
import cbo.vizr.charts.dbstore.DBChartProvider;
import cbo.vizr.charts.dbstore.SqlChart;

@RestController
@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
produces=MediaType.APPLICATION_JSON, 
path="/vizr/sql/")
public class SQLChartWS {

	@Autowired
	DBChartProvider repo;
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
			produces=MediaType.APPLICATION_JSON, 
			method=RequestMethod.POST, 
			path="/")
	public RestChart toChart(@RequestBody SqlChart chart) throws ChartCreationException{
		return repo.fromSQLChart(chart);
	}
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
			produces=MediaType.APPLICATION_JSON, 
			method=RequestMethod.GET, 
			path="/{name}")
	public SqlChart getSqlChart(@PathVariable(value="name", required=true  )String name) throws SQLException{
		
		return repo.findByName(name);
	}
	
}
