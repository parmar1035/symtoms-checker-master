package com.symtoms.checker.alexa.handler;

import java.util.List;

import javax.annotation.Resource;

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
			try {
				healthIssueInfo = diagnosisClient.loadIssueInfo(healthDiagnosisList.get(0).Issue.ID);
			 } catch(Exception ex) {
				LOG.error("Error while Loading healthIssueInfo " + ex); 
			 }
			selectedSymtoms.setHealthIssueInfo(healthIssueInfo);
		} 
		
		addModel(input, "issueDescription", healthIssueInfo.Description);
		LOG.error("issueDescription : " + healthIssueInfo.Description);
		symtomsCheckerService.setSymtomsIntoSession(selectedSymtoms, input);
		symtomsCheckerService.setStepIntoSession(Steps.SIX, input);
	}
}