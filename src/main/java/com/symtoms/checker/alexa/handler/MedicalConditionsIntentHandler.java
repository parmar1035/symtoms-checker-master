package com.symtoms.checker.alexa.handler;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.symtoms.checker.alexa.data.SelectedSymtoms;
import com.symtoms.checker.alexa.data.Steps;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthIssueInfo;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class MedicalConditionsIntentHandler extends AbstractIntentHandler {

	private Logger LOG = LoggerFactory.getLogger(MedicalConditionsIntentHandler.class);

	@Resource(name="symtomsCheckerService")
	SymtomsCheckerService symtomsCheckerService; 
	@Override
	protected void handleInternal(HandlerInput input) {
		LOG.error("inside MedicalConditionsIntentHandler:");
		SelectedSymtoms selectedSymtoms  = symtomsCheckerService.getSymtomsFromSession(input);
		HealthIssueInfo healthIssueInfo = selectedSymtoms.getHealthIssueInfo();
		String healthDetails = healthIssueInfo.MedicalCondition;
		healthDetails = healthDetails.replaceAll("[^ .,a-zA-Z0-9]", StringUtils.EMPTY);
		addModel(input, "medicalConditions", healthDetails);
		symtomsCheckerService.setStepIntoSession(Steps.EIGHT, input);
		
	}

}
