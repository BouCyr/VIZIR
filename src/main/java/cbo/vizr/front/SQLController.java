package cbo.vizr.front;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cbo.vizr.charts.ChartType;
import cbo.vizr.charts.dbstore.DBChartProvider;
import cbo.vizr.charts.dbstore.SqlChart;
import cbo.vizr.charts.dbstore.SqlChartRepo;
import cbo.vizr.front.charts.ProviderDisplay;

@Controller
public class SQLController {

	@Autowired
	DBChartProvider provider;
	
	@Autowired
	SqlChartRepo repo;

	
	@GetMapping("/sql")
	@PostMapping("/sql")
	public ModelAndView get(Map<String, Object> model, @RequestParam(name="chartName", required=false) String chartName) throws ServletException{

		ModelAndView mav = new ModelAndView("SQL") ;

		SqlChart chart = new SqlChart();
		
		if(chartName != null){
			SqlChart fromDB = provider.findByName(chartName);
			chart = fromDB;
			
			if(fromDB!=null){
				mav.addObject("SQL", fromDB.getSql());
			}
		}
		
		
		mav.addObject("chart", chart);
		
		
		mav.addObject("types", ChartType.values());
		
		
		List<ProviderDisplay> charts = new ArrayList<>();
		
		charts.add(new ProviderDisplay(provider));
		mav.addObject("providers", charts);
		

		return mav;
	}
}
