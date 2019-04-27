package com.symtoms.checker.alexa.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.LaunchRequest;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.amazon.ask.request.Predicates.requestType;


public class LaunchRequestHandler extends AbstractIntentHandler {

	private Logger LOG = LoggerFactory.getLogger(LaunchRequestHandler.class);

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(requestType(LaunchRequest.class));
	}


	@Override
	protected void handleInternal(HandlerInput input) {
		
		setSessionAttributes(input, "type", "help");
		LOG.error("Inside Launch Handler");

	}

}