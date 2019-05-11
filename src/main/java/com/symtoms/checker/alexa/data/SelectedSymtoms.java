package com.symtoms.checker.alexa.data;

import java.util.List;

import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthItem;

public class SelectedSymtoms {
	
	private List<HealthItem> bodyLocationList;
	private HealthItem bodyLocation;
	private int bodyLocationCount;
	
	public HealthItem getBodyLocation() {
		return bodyLocation;
	}
	public void setBodyLocation(HealthItem bodyLocation) {
		this.bodyLocation = bodyLocation;
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
	
	
}
