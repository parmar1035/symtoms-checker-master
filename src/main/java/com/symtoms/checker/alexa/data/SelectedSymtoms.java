package com.symtoms.checker.alexa.data;

import java.util.List;

import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthItem;

public class SelectedSymtoms {
	private String gender;
	private int yearofbirth;
	
	private List<HealthItem> bodyLocationList;
	private HealthItem selectedBodyLocation;
	private int bodyLocationCount;
	
	private HealthItem selectedSpecificBodyLocation;
	private List<HealthItem> specificBodyLocationList;
	private int specificBodyLocationCount;
	
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public int getYearofbirth() {
		return yearofbirth;
	}
	public void setYearofbirth(int yearofbirth) {
		this.yearofbirth = yearofbirth;
	}
	public HealthItem getSelectedBodyLocation() {
		return selectedBodyLocation;
	}
	public void setSelectedBodyLocation(HealthItem selectedBodyLocation) {
		this.selectedBodyLocation = selectedBodyLocation;
	}
	public int getBodyLocationCount() {
		return bodyLocationCount;
	}
	public void setBodyLocationCount(int bodyLocationCount) {
		this.bodyLocationCount = bodyLocationCount;
	}
	public List<HealthItem> getBodyLocationList() {
		return bodyLocationList;
	}
	public void setBodyLocationList(List<HealthItem> bodyLocationList) {
		this.bodyLocationList = bodyLocationList;
	}
	public HealthItem getSelectedSpecificBodyLocation() {
		return selectedSpecificBodyLocation;
	}
	public void setSelectedSpecificBodyLocation(HealthItem selectedSpecificBodyLocation) {
		this.selectedSpecificBodyLocation = selectedSpecificBodyLocation;
	}
	public List<HealthItem> getSpecificBodyLocationList() {
		return specificBodyLocationList;
	}
	public void setSpecificBodyLocationList(List<HealthItem> specificBodyLocationList) {
		this.specificBodyLocationList = specificBodyLocationList;
	}
	public int getSpecificBodyLocationCount() {
		return specificBodyLocationCount;
	}
	public void setSpecificBodyLocationCount(int specificBodyLocationCount) {
		this.specificBodyLocationCount = specificBodyLocationCount;
	}
	
	
	
	
	
	
}
