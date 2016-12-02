package cbo.vizr.front;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cbo.vizr.ChartProvider;
import cbo.vizr.charts.ChartCreationException;
import cbo.vizr.front.charts.ProviderDisplay;


@Controller
public class ChartsController {

	@Autowired
	List<ChartProvider> allProviders;
	
	
	private String chartName;
	
	@GetMapping("/charts")
	public ModelAndView get(Map<String, Object> model, @RequestParam(name="chartName", required=false) String chartName) throws ServletException, ChartCreationException{

		ModelAndView mav = new ModelAndView("charts") ;
		
		List<ProviderDisplay> charts = new ArrayList<>();
		
		for(ChartProvider provider : allProviders){
			charts.add(new ProviderDisplay(provider));
		}
		
		mav.addObject("providers", charts);
		if(chartName != null){
			mav.addObject("chartName", chartName);
			mav.addObject("loadChart", true);
		}
		
		
		return mav;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
}
