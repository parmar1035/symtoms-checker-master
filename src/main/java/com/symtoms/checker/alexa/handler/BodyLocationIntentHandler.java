package com.symtoms.checker.alexa.handler;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazonaws.util.CollectionUtils;
import com.symtoms.checker.alexa.integration.client.DiagnosisClient;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthItem;

public class BodyLocationIntentHandler extends AbstractIntentHandler {

	private Logger LOG = LoggerFactory.getLogger(BodyLocationIntentHandler.class);
	
	@Resource(name="diagnosisClient")
	DiagnosisClient diagnosisClient; 
	@Override
	protected void handleInternal(HandlerInput input) {
		
		LOG.error("inside BodyLocationIntentHandler:");
		Object locationObject = getSessionAttributes(input, "BodyLocation");
		List<HealthItem> locationList = null;
		Integer count = 0;
		if(null != locationObject && locationObject instanceof List) {
			locationList = (List) locationObject;
		} else {
			locationList = diagnosisClient.loadBodyLocations();
			setSessionAttributes(input, "BodyLocation",locationList);
		}
		Object countObject = getSessionAttributes(input, "BodyLocationCount");
		if(null != countObject && countObject instanceof Integer) {
			count = ((Integer) countObject) + 1;
		} 
		if(locationList.size() <= count) {
			count = 0;
		}
		setSessionAttributes(input, "BodyLocationCount", count);
		setSessionAttributes(input, "type", "BodyLocation");
		addModel(input, "bodyLocation", locationList.get(count));
		LOG.error(locationList.get(count).Name);
	}
}