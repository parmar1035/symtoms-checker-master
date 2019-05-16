package com.symtoms.checker.alexa.data;

import java.util.List;
import java.util.Set;

import com.symtoms.checker.alexa.priaid.diagnosis.model.DiagnosedIssue;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthDiagnosis;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthIssueInfo;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthItem;

/**
 * @author manoj.girachh
 *
 */
public class SelectedSymtoms {
	private String gender;
	private int yearofbirth;
	private int step;
	
	private List<HealthItem> bodyLocationList;
	private HealthItem selectedBodyLocation;
	private int bodyLocationCount;
	
	private HealthItem selectedSpecificBodyLocation;
	private List<HealthItem> specificBodyLocationList;
	private int specificBodyLocationCount;
	
	private List<HealthItem> proposedSystomList;
	private List<Integer> selectedProposedSystomList;
	private int specificProposedSystomCount;
	
	private List<HealthDiagnosis> healthDiagnosisList; 
	private int selectedIssueId;
	private int healthDiagnosisCount;
	
	private HealthIssueInfo healthIssueInfo;
	
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
	public List<HealthItem> getProposedSystomList() {
		return proposedSystomList;
	}
	public void setProposedSystomList(List<HealthItem> proposedSystomList) {
		this.proposedSystomList = proposedSystomList;
	}
	public List<Integer> getSelectedProposedSystomList() {
		return selectedProposedSystomList;
	}
	public void setSelectedProposedSystomList(List<Integer> selectedProposedSystomList) {
		this.selectedProposedSystomList = selectedProposedSystomList;
	}
	public int getSpecificProposedSystomCount() {
		return specificProposedSystomCount;
	}
	public void setSpecificProposedSystomCount(int specificProposedSystomCount) {
		this.specificProposedSystomCount = specificProposedSystomCount;
	}
	public List<HealthDiagnosis> getHealthDiagnosisList() {
		return healthDiagnosisList;
	}
	public void setHealthDiagnosisList(List<HealthDiagnosis> healthDiagnosisList) {
		this.healthDiagnosisList = healthDiagnosisList;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getSelectedIssueId() {
		return selectedIssueId;
	}
	public void setSelectedIssueId(int selectedIssueId) {
		this.selectedIssueId = selectedIssueId;
	}
	public int getHealthDiagnosisCount() {
		return healthDiagnosisCount;
	}
	public void setHealthDiagnosisCount(int healthDiagnosisCount) {
		this.healthDiagnosisCount = healthDiagnosisCount;
	}
	public HealthIssueInfo getHealthIssueInfo() {
		return healthIssueInfo;
	}
	public void setHealthIssueInfo(HealthIssueInfo healthIssueInfo) {
		this.healthIssueInfo = healthIssueInfo;
	}
}
