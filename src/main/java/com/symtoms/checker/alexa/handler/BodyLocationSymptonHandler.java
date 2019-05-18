package com.symtoms.checker.alexa.handler;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazonaws.util.CollectionUtils;
import com.symtoms.checker.alexa.data.SelectedSymtoms;
import com.symtoms.checker.alexa.data.Steps;
import com.symtoms.checker.alexa.integration.client.DiagnosisClient;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthItem;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthSymptomSelector;
import com.symtoms.checker.alexa.priaid.diagnosis.model.SelectorStatus;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class BodyLocationSymptonHandler extends AbstractIntentHandler {

	private Logger LOG = LoggerFactory.getLogger(BodyLocationSymptonHandler.class);
	
	@Resource(name="diagnosisClient")
	DiagnosisClient diagnosisClient; 

	@Resource(name="symtomsCheckerService")
	SymtomsCheckerService symtomsCheckerService; 

	@Override
	protected void handleInternal(HandlerInput input) {
		
		LOG.error("inside BodyLocationSymptonHandler:");
		SelectedSymtoms selectedSymtoms  = symtomsCheckerService.getSymtomsFromSession(input);
		HealthItem selectedSpecificBodyLocation = selectedSymtoms.getSelectedBodyPartLocation();
		if(null == selectedSpecificBodyLocation) {
			LOG.error("Customer has missed to add step 1 & 2 : selectedSpecificBodyLocation");
			addModel(input, "error", "");
			return;
		}
		
		List<HealthSymptomSelector> healthSymptomSelectorList = selectedSymtoms.getBodyLocationSymptomList();
		
		int index = selectedSymtoms.getSelectedBodyLocationSymptomCount();
		
		if(CollectionUtils.isNullOrEmpty(healthSymptomSelectorList)) {
			healthSymptomSelectorList = diagnosisClient.loadSublocationSymptoms(selectedSpecificBodyLocation.ID, findSelectorStatus(selectedSymtoms.getGender()));
			selectedSymtoms.setBodyLocationSymptomList(healthSymptomSelectorList);
		}
		
		HealthSymptomSelector symptomLocation = healthSymptomSelectorList.get(index);
		addModel(input, "bodySymptomName", symptomLocation.Name);
		selectedSymtoms.setSelectedBodyLocationSymptom(symptomLocation);
		index = healthSymptomSelectorList.size() > (index + 1)?(index + 1):0;
		selectedSymtoms.setSelectedBodyLocationSymptomCount(index);
		symtomsCheckerService.setSymtomsIntoSession(selectedSymtoms, input);
		symtomsCheckerService.setStepIntoSession(Steps.FROUR, input);
	}
	
	
	
}