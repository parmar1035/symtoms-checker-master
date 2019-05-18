package com.symtoms.checker.alexa.handler;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.symtoms.checker.alexa.data.SelectedSymtoms;
import com.symtoms.checker.alexa.data.Steps;
import com.symtoms.checker.alexa.integration.client.DiagnosisClient;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthDiagnosis;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthIssueInfo;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class GetDiagnosisDetailsIntentHandler extends AbstractIntentHandler {

	private Logger LOG = LoggerFactory.getLogger(GetDiagnosisDetailsIntentHandler.class);
	
	@Resource(name="diagnosisClient")
	DiagnosisClient diagnosisClient; 

	@Resource(name="symtomsCheckerService")
	SymtomsCheckerService symtomsCheckerService; 

	@Override
	protected void handleInternal(HandlerInput input) {
		
		LOG.error("inside GetDiagnosisDetailsIntentHandler");
		SelectedSymtoms selectedSymtoms  = symtomsCheckerService.getSymtomsFromSession(input);
		
		HealthIssueInfo healthIssueInfo = selectedSymtoms.getHealthIssueInfo();
				
		if(null == healthIssueInfo) {
			List<HealthDiagnosis> healthDiagnosisList = selectedSymtoms.getHealthDiagnosisList();
			String position = getSlotValue(input, "position");
			int index = getNumberFromString(position);
			index = index >= healthDiagnosisList.size() ? (healthDiagnosisList.size() - 1) : index;
			try {
				healthIssueInfo = diagnosisClient.loadIssueInfo(healthDiagnosisList.get(index).Issue.ID);
			 } catch(Exception ex) {
				LOG.error("Error while Loading healthIssueInfo " + ex); 
			 }
			selectedSymtoms.setHealthIssueInfo(healthIssueInfo);
		} 
		
		String healthDetails = healthIssueInfo.Description;
		healthDetails = healthDetails.replaceAll("[^ .,a-zA-Z0-9]", StringUtils.EMPTY);
		addModel(input, "issueDetails", healthDetails);
		LOG.error("issueDetails : " + healthDetails);
		symtomsCheckerService.setSymtomsIntoSession(selectedSymtoms, input);
		symtomsCheckerService.setStepIntoSession(Steps.SIX, input);
	}
	int getNumberFromString(String position) {
		if(StringUtils.isEmpty(position))
		{
			return 0;
		}
		
		if(position.contains("first") || position.contains("1") || position.contains("1st")) {
			return 1;
		}
		if(position.contains("second") || position.contains("2") || position.contains("2nd")) {
			return 2;
		}
		if(position.contains("third") || position.contains("3") || position.contains("3rd")) {
			return 3;
		}
		if(position.contains("fourth") || position.contains("4") || position.contains("4th")) {
			return 4;
		}
		
		return 0;
		
	}

}