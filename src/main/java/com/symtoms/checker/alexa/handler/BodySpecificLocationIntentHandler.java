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

public class BodySpecificLocationIntentHandler extends AbstractIntentHandler {

	private Logger LOG = LoggerFactory.getLogger(BodySpecificLocationIntentHandler.class);
	
	@Resource(name="diagnosisClient")
	DiagnosisClient diagnosisClient; 

	@Resource(name="symtomsCheckerService")
	SymtomsCheckerService symtomsCheckerService; 

	@Override
	protected void handleInternal(HandlerInput input) {
		
		LOG.error("inside BodySpecificLocationIntentHandler:");
		SelectedSymtoms selectedSymtoms  = symtomsCheckerService.getSymtomsFromSession(input);
		HealthItem selectedBodyLocation = selectedSymtoms.getSelectedBodyLocation();
		if(null == selectedBodyLocation) {
			LOG.error("Customer has missed to add step 1 : selectedBodyLocation");
			addModel(input, "error", "");
			return;
		}
		
		List<HealthItem> locationList = selectedSymtoms.getSpecificBodyLocationList();
		
		int index = selectedSymtoms.getSpecificBodyLocationCount();
		
		if(CollectionUtils.isNullOrEmpty(locationList)) {
			locationList = diagnosisClient.loadBodySubLocations(selectedBodyLocation.ID);
			selectedSymtoms.setSpecificBodyLocationList(locationList);
		}
		
		HealthItem specificBodyLocation = locationList.get(index);
		addModel(input, "bodyLocName", specificBodyLocation.Name);
		selectedSymtoms.setSelectedSpecificBodyLocation(specificBodyLocation);
		index = locationList.size() > (index + 1)?(index + 1):0;
		selectedSymtoms.setSpecificBodyLocationCount(index);
		symtomsCheckerService.setSymtomsIntoSession(selectedSymtoms, input);
		setSessionAttributes(input, "type", "BodySpecificLocation");
	}
}