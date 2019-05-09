package com.symtoms.checker.alexa.handler;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.LaunchRequest;
import com.symtoms.checker.alexa.integration.client.DiagnosisClient;
import com.symtoms.checker.alexa.integration.client.impl.DefaultDiagnosisClient;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.List;

import javax.annotation.Resource;


public class LaunchRequestHandler extends AbstractIntentHandler {

	private Logger LOG = LoggerFactory.getLogger(LaunchRequestHandler.class);

	@Resource(name="diagnosisClient")
	private DiagnosisClient client;

	@Override
	public boolean canHandle(HandlerInput input) {
		return input.matches(requestType(LaunchRequest.class));
	}


	@Override
	protected void handleInternal(HandlerInput input) {
		
		setSessionAttributes(input, "type", "launch");
		LOG.error("Inside Launch Handler");
	}

}