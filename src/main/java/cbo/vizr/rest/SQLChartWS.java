package cbo.vizr.rest;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cbo.vizr.charts.ChartCreationException;
import cbo.vizr.charts.RestChart;
import cbo.vizr.charts.dbstore.DBChartProvider;
import cbo.vizr.charts.dbstore.QueryResult;
import cbo.vizr.charts.dbstore.SqlChart;

@RestController
@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
produces=MediaType.APPLICATION_JSON, 
path="/vizr/sql/")
public class SQLChartWS {

	@Autowired
	DBChartProvider provider;
	
	@Autowired
	DataSource source;
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
			produces=MediaType.APPLICATION_JSON, 
			method=RequestMethod.POST, 
			path="/")
	public RestChart toChart(@RequestBody SqlChart chart) throws ChartCreationException{
		return provider.fromSQLChart(chart);
	}
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
			produces=MediaType.APPLICATION_JSON, 
			method=RequestMethod.POST, 
			path="/save")
	public void saveChart(@RequestBody SqlChart chart) throws ChartCreationException{
		
		chart.setSql(chart.getSql().trim());
		
		
		if(provider.findByName(chart.getName()) !=null)
		{
			chart.setId(provider.findByName(chart.getName()).getId());
		}
		provider.save(chart);

		
	}
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
			produces=MediaType.APPLICATION_JSON, 
			method=RequestMethod.GET, 
			path="/{name}")
	public SqlChart getSqlChart(@PathVariable(value="name", required=true  )String name) throws SQLException{
		
		return provider.findByName(name);
	}
	
	
	@RequestMapping(consumes=MediaType.APPLICATION_JSON, 
			produces=MediaType.APPLICATION_JSON, 
			method=RequestMethod.GET, 
			path="/request")
	public QueryResult getQueryResult(@RequestParam("sql") String sql){
		
		try {
			return provider.executeQuery(sql);
		} catch (SQLException e) {
			return new QueryResult(sql, true, e.getMessage(), new ArrayList<>(), new ArrayList<>());
		}
	}
	/*
	private ModelAndView treatSQL(ModelAndView mav, String sql) throws ServletException {

		sql = sql.trim();

		if(!StringUtils.isEmpty(sql)){

			Connection con= null;
			PreparedStatement st = null;

			try{
				con = source.getConnection();
				st = con.prepareStatement(sql);
				st.setQueryTimeout(2);
				
				ResultSet rs = st.executeQuery();


				List<String> columnNames= new ArrayList<>();


				for(int i =0; i  < rs.getMetaData().getColumnCount() ; i++){
					columnNames.add(rs.getMetaData().getColumnName(i+1));
				}

				mav.addObject("colNames", columnNames);

				

				List<List<Object>> lines = new ArrayList<>();
				int linecount = 0;
				while(rs.next()){

					if(linecount <10){
						List<Object> line = new ArrayList<>();
						for(String colName:columnNames){
							line.add(rs.getObject(colName));
						}
						lines.add(line);
					}
					linecount++;
				}
				mav.addObject("nbRows", linecount);
				mav.addObject("lines", lines);


			}catch(SQLException e) {
				throw new ServletException(e);
			}finally {
				try {
					if(st!=null)
						st.close();
					if(con!=null)
						con.close();
				} catch (SQLException e) {
					throw e;
				}
			}


		}

		mav.addObject("SQL", sql);

		return mav;
	}*/
}
