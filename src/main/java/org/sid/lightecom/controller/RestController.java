package org.sid.lightecom.controller;

import java.util.List;

import org.sid.lightecom.model.LocationState;
import org.sid.lightecom.service.CoronaVisrusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin("*")
@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private CoronaVisrusDataService coronaVisrusDataService;
	
	@GetMapping("/listofcases")
	public List<LocationState> listofcases(LocationState locationState) {
		
		List<LocationState> allstats = coronaVisrusDataService.getAllstate();
		
		int totalReportedCases = allstats.stream().mapToInt(stat -> stat.getLatestTotal()).sum();
		int totalNewCases = allstats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();

		
		return allstats;
	}
	

}
