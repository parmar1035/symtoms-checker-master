package com.symtoms.checker.alexa.handler;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class CompletedIntentHandler extends AbstractIntentHandler{
	private Logger LOG = LoggerFactory.getLogger(CompletedIntentHandler.class);

	@Resource(name="symtomsCheckerService")
	SymtomsCheckerService symtomsCheckerService; 
	
	@Override
	protected void handleInternal(HandlerInput input) {
		symtomsCheckerService.clearSymtomSession(input);
		
	}
	
}
