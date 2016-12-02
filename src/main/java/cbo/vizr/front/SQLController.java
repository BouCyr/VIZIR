package cbo.vizr.front;

import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import cbo.vizr.charts.ChartType;
import cbo.vizr.charts.dbstore.SqlChart;
import cbo.vizr.charts.dbstore.SqlChartRepo;

@Controller
public class SQLController {


	
	@Autowired
	SqlChartRepo repo;

	
	@GetMapping("/sql")
	public ModelAndView get(Map<String, Object> model) throws ServletException{

		ModelAndView mav = new ModelAndView("SQL") ;

		mav.addObject("chart", new SqlChart());
		mav.addObject("types", ChartType.values());

		return mav;
	}
}
