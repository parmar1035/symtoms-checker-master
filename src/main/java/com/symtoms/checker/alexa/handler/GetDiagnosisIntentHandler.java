package com.symtoms.checker.alexa.handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazonaws.util.CollectionUtils;
import com.symtoms.checker.alexa.data.SelectedSymtoms;
import com.symtoms.checker.alexa.data.Steps;
import com.symtoms.checker.alexa.integration.client.DiagnosisClient;
import com.symtoms.checker.alexa.priaid.diagnosis.model.Gender;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthDiagnosis;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthItem;
import com.symtoms.checker.alexa.priaid.diagnosis.model.SelectorStatus;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class GetDiagnosisIntentHandler extends AbstractIntentHandler {

	private Logger LOG = LoggerFactory.getLogger(GetDiagnosisIntentHandler.class);
	
	@Resource(name="diagnosisClient")
	DiagnosisClient diagnosisClient; 

	@Resource(name="symtomsCheckerService")
	SymtomsCheckerService symtomsCheckerService; 

	@Override
	protected void handleInternal(HandlerInput input) {
		
		LOG.error("inside BodyLocationIntentHandler:");
		SelectedSymtoms selectedSymtoms  = symtomsCheckerService.getSymtomsFromSession(input);
		
		List<HealthDiagnosis> healthDiagnosisList = selectedSymtoms.getHealthDiagnosisList();
				
		if(CollectionUtils.isNullOrEmpty(healthDiagnosisList)) {
			List<Integer> selectedSymptoms = new ArrayList<Integer>();
			selectedSymptoms.addAll(selectedSymtoms.getSelectedProposedSystomList());
			LOG.error("Get SelectedProposedSystomList : " + selectedSymptoms);
			try {
				healthDiagnosisList = diagnosisClient.loadDiagnosis(
						selectedSymptoms, findGender(selectedSymtoms.getGender()), selectedSymtoms.getYearofbirth());
				LOG.error("Load healthDiagnosisList : " + healthDiagnosisList);
			 } catch(Exception ex) {
				LOG.error("Error in Load healthDiagnosisList " + ex); 
			 }
			selectedSymtoms.setHealthDiagnosisList(healthDiagnosisList);
		} 
		
		addModel(input, "issueNameList", getIssueNameList(healthDiagnosisList));
		LOG.error("healthDiagnosisList : " + healthDiagnosisList);
		symtomsCheckerService.setSymtomsIntoSession(selectedSymtoms, input);
		symtomsCheckerService.setStepIntoSession(Steps.SIX, input);
	}
	private Gender findGender(final String code) {
		if(StringUtils.isEmpty(code)) {
			return null;
		}
		
		switch (code.toLowerCase()) {
		case "man":
			return Gender.Male;
		case "woman":
			return Gender.Female;
		case "boy":
			return Gender.Male;
		case "girl":
			return Gender.Female;
		default:
			return null;
		}
	}

	private List<String> getIssueNameList(List<HealthDiagnosis> healthDiagnosisList) {
		final List<String> issueNameList = new ArrayList<String>();
		if(null != healthDiagnosisList) {
			for(HealthDiagnosis diagnosis : healthDiagnosisList) {
				issueNameList.add(diagnosis.Issue.Name);
			}
		}
		return issueNameList;
	}
}