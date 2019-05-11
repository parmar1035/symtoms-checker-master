package com.symtoms.checker.alexa.data;

import java.util.List;
import java.util.Set;

import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthItem;

public class SelectedSymtoms {
	
	private List<HealthItem> bodyLocationList;
	private HealthItem selectedBodyLocation;
	private int bodyLocationCount;
	
	private HealthItem selectedSpecificBodyLocation;
	private List<HealthItem> specificBodyLocationList;
	private int specificBodyLocationCount;
	
	private List<HealthItem> proposedSystomList;
	private Set<Integer> selectedProposedSystomList;
	private int specificProposedSystomCount;
	
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
	public List<HealthItem> getProposedSystomList() {
		return proposedSystomList;
	}
	public void setProposedSystomList(List<HealthItem> proposedSystomList) {
		this.proposedSystomList = proposedSystomList;
	}
	public Set<Integer> getSelectedProposedSystomList() {
		return selectedProposedSystomList;
	}
	public void setSelectedProposedSystomList(Set<Integer> selectedProposedSystomList) {
		this.selectedProposedSystomList = selectedProposedSystomList;
	}
	public int getSpecificProposedSystomCount() {
		return specificProposedSystomCount;
	}
	public void setSpecificProposedSystomCount(int specificProposedSystomCount) {
		this.specificProposedSystomCount = specificProposedSystomCount;
	}
}
