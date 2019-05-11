package com.symtoms.checker.alexa.handler;



import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.symtoms.checker.alexa.data.SelectedSymtoms;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class YearCaptureIntentHandler extends AbstractIntentHandler {


	private static final String YEAR_OF_BIRTH = "yearofbirth";
	private Logger LOG = LoggerFactory.getLogger(YearCaptureIntentHandler.class);
	
	@Resource(name="symtomsCheckerService")
	SymtomsCheckerService symtomsCheckerService; 
	
	@Resource(name="bodyLocationHandler")
	private BodyLocationIntentHandler bodyLocationHandler;
	

	@Override
	protected void handleInternal(HandlerInput input) {
		LOG.error("inside YearCaptureIntentHandler:");
		Map<String, String> slots = getSlots(input);
		String yearofbirth = slots.get(YEAR_OF_BIRTH);
		LOG.error("Year of Birth {}", yearofbirth);
		if(StringUtils.isNotEmpty(yearofbirth)) {
			SelectedSymtoms selectedSymtoms = symtomsCheckerService.getSymtomsFromSession(input);
			selectedSymtoms.setYearofbirth(Integer.parseInt(yearofbirth));
			symtomsCheckerService.setSymtomsIntoSession(selectedSymtoms, input);
		}
		
	}
	
	@Override
    public Optional<Response> handle(HandlerInput input) {
		handleInternal(input);
		return bodyLocationHandler.handle(input);
	}
}