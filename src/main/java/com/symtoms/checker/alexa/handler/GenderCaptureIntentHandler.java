package com.symtoms.checker.alexa.handler;



import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.symtoms.checker.alexa.data.SelectedSymtoms;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class GenderCaptureIntentHandler extends AbstractIntentHandler {


	private static final String GENDER = "gender";
	private Logger LOG = LoggerFactory.getLogger(GenderCaptureIntentHandler.class);
	
	@Resource(name="symtomsCheckerService")
	SymtomsCheckerService symtomsCheckerService; 
	

	@Override
	protected void handleInternal(HandlerInput input) {
		LOG.error("inside GenderCaptureIntentHandler:");
		Map<String, String> slots = getSlots(input);
		String gender = slots.get(GENDER);
		LOG.error("Gender {}", gender);
		if(StringUtils.isNotEmpty(gender)) {
			SelectedSymtoms selectedSymtoms = symtomsCheckerService.getSymtomsFromSession(input);
			selectedSymtoms.setGender(gender);
			symtomsCheckerService.setSymtomsIntoSession(selectedSymtoms, input);
		}
		
	}
}