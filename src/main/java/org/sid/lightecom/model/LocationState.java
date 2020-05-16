package org.sid.lightecom.model;

public class LocationState {
	
	private String state;
	private String country;
	private int latestTotal;
	private int diffFromPrevDay;
	
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotal() {
		return latestTotal;
	}
	public void setLatestTotal(int latestTotal) {
		this.latestTotal = latestTotal;
	}
	@Override
	public String toString() {
		return "LocationState [state=" + state + ", country=" + country + ", latestTotal=" + latestTotal + "]";
	}

}
