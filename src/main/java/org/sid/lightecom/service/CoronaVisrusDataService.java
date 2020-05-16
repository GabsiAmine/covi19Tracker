package org.sid.lightecom.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.sid.lightecom.model.LocationState;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CoronaVisrusDataService {

	private static String VIRUS_DATA_URL ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<LocationState> allstate = new ArrayList<>();
		
	public List<LocationState> getAllstate() {
		return allstate;
	}


	public void setAllstate(List<LocationState> allstate) {
		this.allstate = allstate;
	}


	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
	List<LocationState> newstats = new ArrayList<>();
	
	HttpClient httpClient = HttpClient.newHttpClient();
	
	HttpRequest httpRequest = HttpRequest.newBuilder()
			.uri(URI.create(VIRUS_DATA_URL))
			.build();
	
	HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
	
	StringReader csvbodyReader = new StringReader(httpResponse.body());
	 
	Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvbodyReader);
	for (CSVRecord record : records) {
		LocationState locationState = new LocationState();
		locationState.setState(record.get("Province/State"));
		locationState.setCountry(record.get("Country/Region"));
		int latestcases = Integer.parseInt(record.get(record.size()-1));
		int prevdaycases = Integer.parseInt(record.get(record.size()-2));
	    locationState.setLatestTotal(latestcases);
	    locationState.setDiffFromPrevDay(latestcases - prevdaycases);
		newstats.add(locationState);
	}
	this.allstate=newstats;
	
}
	
}
