package com.symtoms.checker.alexa.handler;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.symtoms.checker.alexa.handler.AbstractIntentHandler;
import com.symtoms.checker.alexa.handler.HelpIntentHandler;

public class NoIntentHandler  extends AbstractIntentHandler {

	@Resource(name="bodyLocationHandler")
	private BodyLocationIntentHandler bodyLocationHandler;

	@Override
	public Optional<Response> handle(HandlerInput input) {
		String speechText = "";
		
		Object typeObject = getSessionAttributes(input,"type");
		if(null != typeObject && typeObject instanceof String) {
			String type = (String) typeObject;
			switch (type) {
			case "BodyLocation":
				return bodyLocationHandler.handle(input);
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
