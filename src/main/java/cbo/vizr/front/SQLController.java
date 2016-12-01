package cbo.vizr.front;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cbo.vizr.charts.ChartType;
import cbo.vizr.charts.dbstore.SqlChart;
import cbo.vizr.charts.dbstore.SqlChartRepo;

@Controller
public class SQLController {

	@Autowired
	DataSource source;
	
	@Autowired
	SqlChartRepo repo;

	
	@GetMapping("/sql")
	public ModelAndView get(Map<String, Object> model) throws ServletException{

		ModelAndView mav = new ModelAndView("SQL") ;
		String sql = "";

		mav.addObject("chart", new SqlChart());
		mav.addObject("types", ChartType.values());

		return treatSQL(mav, sql);
	}
	
	
	@PostMapping("/sql")
	public ModelAndView post(Map<String, Object> model, 
			@RequestParam(value="sql", required=true) String sql) throws ServletException{

		ModelAndView mav = new ModelAndView("SQL") ;

		SqlChart chart = new SqlChart();
		
		
		chart.setSql(sql.trim());
		
		mav.addObject("chart", chart);
		mav.addObject("types", ChartType.values());


		return treatSQL(mav, sql);
	}

	@PostMapping("/saveChart")
	public ModelAndView save(Map<String, Object> model,
			@ModelAttribute("chart") SqlChart chart) throws ServletException{

		ModelAndView mav = new ModelAndView("charts") ;

		chart.setSql(chart.getSql().trim());
		
		
		if(repo.findByName(chart.getName()) !=null)
		{
			chart.setId(repo.findByName(chart.getName()).getId());
		}
		repo.save(chart);

		treatSQL(mav, chart.getSql());
		
		mav.addObject("chart", chart);
		mav.addObject("types", ChartType.values());
		
		mav.addObject("chartName", chart.getName());
		
		return mav;
	}


	private ModelAndView treatSQL(ModelAndView mav, String sql) throws ServletException {

		sql = sql.trim();

		if(!StringUtils.isEmpty(sql)){

			Connection con= null;
			PreparedStatement st = null;

			try{
				con = source.getConnection();
				st = con.prepareStatement(sql);

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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}


		}

		mav.addObject("SQL", sql);

		return mav;
	}
}
