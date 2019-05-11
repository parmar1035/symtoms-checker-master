package com.symtoms.checker.alexa.handler;

import java.util.Optional;

import javax.annotation.Resource;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.symtoms.checker.alexa.handler.AbstractIntentHandler;
import com.symtoms.checker.alexa.handler.HelpIntentHandler;

public class YesIntentHandler  extends AbstractIntentHandler {

	@Resource(name="helpHandler")
	private HelpIntentHandler helpHandler;
	@Resource(name="bodyLocationHandler")
	private BodyLocationIntentHandler bodyLocationHandler;
	@Resource(name="bodySpecificLocationIntentHandler")
	private BodySpecificLocationIntentHandler bodySpecificLocationIntentHandler;
	@Resource(name="genderIdentificationIntentHandler")
	private GenderIdentificationIntentHandler genderIdentificationIntentHandler;
	@Resource(name="bodyLocationSymptonHandler")
	private BodyLocationSymptonHandler bodyLocationSymptonHandler;
	
	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText = "";
		Object typeObject = getSessionAttributes(input,"type");
		setSessionAttributes(input, "user_option", Boolean.TRUE);
		if(null != typeObject && typeObject instanceof String) {
			String type = (String) typeObject;
			switch (type) {
			case "help":
				return helpHandler.handle(input);
			case "launch":
				return genderIdentificationIntentHandler.handle(input);
			case "BodyLocation":
				return bodySpecificLocationIntentHandler.handle(input);
			case "BodySpecificLocation":
				return bodyLocationSymptonHandler.handle(input);
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
