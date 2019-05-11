package com.symtoms.checker.alexa.handler;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazonaws.util.CollectionUtils;
import com.symtoms.checker.alexa.data.SelectedSymtoms;
import com.symtoms.checker.alexa.integration.client.DiagnosisClient;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthItem;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class BodyLocationIntentHandler extends AbstractIntentHandler {

	private Logger LOG = LoggerFactory.getLogger(BodyLocationIntentHandler.class);
	
	@Resource(name="diagnosisClient")
	DiagnosisClient diagnosisClient; 

	@Resource(name="symtomsCheckerService")
	SymtomsCheckerService symtomsCheckerService; 

	@Override
	protected void handleInternal(HandlerInput input) {
		
		LOG.error("inside BodyLocationIntentHandler:");
		SelectedSymtoms selectedSymtoms  = symtomsCheckerService.getSymtomsFromSession(input);
		
		List<HealthItem> locationList = selectedSymtoms.getBodyLocationList();
		
		int index = selectedSymtoms.getBodyLocationCount();
		
		if(CollectionUtils.isNullOrEmpty(locationList)) {
			locationList = diagnosisClient.loadBodyLocations();
			selectedSymtoms.setBodyLocationList(locationList);
		} 
		HealthItem bodyLocation = locationList.get(index);
		addModel(input, "bodyLocName", bodyLocation.Name);
		selectedSymtoms.setSelectedBodyLocation(bodyLocation);
		index = locationList.size() > (index + 1)?(index + 1):0;
		selectedSymtoms.setBodyLocationCount(index);
		symtomsCheckerService.setSymtomsIntoSession(selectedSymtoms, input);
		setSessionAttributes(input, "type", "BodyLocation");
	}
}