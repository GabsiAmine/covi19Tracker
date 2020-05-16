package org.sid.lightecom.controller;

import java.util.List;

import org.sid.lightecom.model.LocationState;
import org.sid.lightecom.service.CoronaVisrusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Autowired
	private CoronaVisrusDataService coronaVisrusDataService;
	
	@GetMapping("/home")
	public String home(Model model) {
		
		List<LocationState> allstats = coronaVisrusDataService.getAllstate();
		
		int totalReportedCases = allstats.stream().mapToInt(stat -> stat.getLatestTotal()).sum();
		int totalNewCases = allstats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

		model.addAttribute("locationstate",allstats);
		model.addAttribute("totalReportedCases",totalReportedCases);
		model.addAttribute("totalNewCases",totalNewCases);
		
		return "home";
	}
}
