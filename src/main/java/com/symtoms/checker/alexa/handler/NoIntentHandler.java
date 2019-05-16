package com.symtoms.checker.alexa.handler;

import java.util.Optional;

import javax.annotation.Resource;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.symtoms.checker.alexa.handler.AbstractIntentHandler;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class NoIntentHandler  extends AbstractIntentHandler {

	@Resource(name="bodyLocationHandler")
	private BodyLocationIntentHandler bodyLocationHandler;

	@Resource(name="bodySpecificLocationIntentHandler")
	private BodySpecificLocationIntentHandler bodySpecificLocationIntentHandler;
	
	@Resource(name="bodyLocationSymptonHandler")
	private BodyLocationSymptonHandler bodyLocationSymptonHandler;

	@Resource(name="proposedSymtomsIntentHandler")
	private ProposedSymtomsIntentHandler proposedSymtomsIntentHandler;

	@Resource(name="symtomsCheckerService")
	SymtomsCheckerService symtomsCheckerService; 
	
	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText = "";
		symtomsCheckerService.setYesNoIntent(Boolean.FALSE, input);
		Object typeObject = getSessionAttributes(input,"type");
		if(null != typeObject && typeObject instanceof String) {
			String type = (String) typeObject;
			switch (type) {
				case "BodyLocation":
					return bodyLocationHandler.handle(input);
				case "BodySpecificLocation":
					return bodySpecificLocationIntentHandler.handle(input);
				case "BodyLocationSymptom":
					return bodyLocationSymptonHandler.handle(input);
				case "ProposedSymtom":
					return proposedSymtomsIntentHandler.handle(input);				
				default:
					speechText = "Sorry, I do not understand how to process that.";
			}
		}
		else {
			speechText = "Sorry, I am not sure what you are saying Yes for.";
		}
		return input.getResponseBuilder().withSpeech(speechText).withSimpleCard("Yes/No", speechText).build();
	}

	@Override
	protected void handleInternal(HandlerInput input) {
	}

}
