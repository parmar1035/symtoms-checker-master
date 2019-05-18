package com.symtoms.checker.alexa.handler;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.symtoms.checker.alexa.handler.AbstractIntentHandler;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class YesIntentHandler  extends AbstractIntentHandler {

	@Resource(name="handlerMapperYesIntent")
	private Map<String, AbstractIntentHandler> handlerMap;
	
	@Resource(name="symtomsCheckerService")
	SymtomsCheckerService symtomsCheckerService; 
	
	@Override
	public Optional<Response> handle(HandlerInput input) {
		symtomsCheckerService.setYesNoIntent(Boolean.TRUE, input);
		AbstractIntentHandler handler = handlerMap.get(symtomsCheckerService.getStepFromSession(input));
		if(null != handler) {
			return handler.handle(input);
		} else {
			return super.handle(input);
		}
	}

	@Override
	protected void handleInternal(HandlerInput input) {

	}

}
