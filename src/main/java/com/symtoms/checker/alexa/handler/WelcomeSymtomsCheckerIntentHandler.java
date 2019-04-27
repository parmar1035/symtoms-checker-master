package com.symtoms.checker.alexa.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public class WelcomeSymtomsCheckerIntentHandler extends AbstractIntentHandler {

	private Logger LOG = LoggerFactory.getLogger(WelcomeSymtomsCheckerIntentHandler.class);
	
	@Override
	protected void handleInternal(HandlerInput input) {
		LOG.error("inside WelcomeSymtomsCheckerIntentHandler:");
	}

}