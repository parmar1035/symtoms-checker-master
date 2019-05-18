package com.symtoms.checker.alexa.service.impl;



import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.symtoms.checker.alexa.data.SelectedSymtoms;
import com.symtoms.checker.alexa.data.Steps;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class DefaultSymtomsCheckerService implements SymtomsCheckerService{

	@Override
	public void clearSymtomSession(HandlerInput input) {
		input.getAttributesManager()
		 .getSessionAttributes()
		 .remove(SYMTOMS_SESSION_KEY);
		
	}
	
	@Override
	public void setSymtomsIntoSession(SelectedSymtoms symtoms, HandlerInput input) {
		input.getAttributesManager()
			 .getSessionAttributes()
			 .put(SYMTOMS_SESSION_KEY, symtoms);
	}

	@Override
	public SelectedSymtoms getSymtomsFromSession(HandlerInput input) {
		SelectedSymtoms symtoms = new SelectedSymtoms();

		if (input.getAttributesManager().getSessionAttributes().containsKey(SYMTOMS_SESSION_KEY)) {
			
			Object sessionAttribute = input.getAttributesManager().getSessionAttributes().get(SYMTOMS_SESSION_KEY);
			if(sessionAttribute instanceof SelectedSymtoms) {
				return (SelectedSymtoms)sessionAttribute;
			}
			
			if (null != sessionAttribute) {
				final ObjectMapper mapper = new ObjectMapper();
				symtoms = mapper.convertValue(sessionAttribute, SelectedSymtoms.class);
			}
		}
		return symtoms;
	}
	public void setYesNoIntent(final Boolean flag, final HandlerInput input) {
		input.getAttributesManager()
		 .getSessionAttributes()
		 .put(YES_NO_SESSION_KEY, flag);
	}
	
	public Boolean isYesNoIntent(final HandlerInput input) {
		if (input.getAttributesManager().getSessionAttributes().containsKey(YES_NO_SESSION_KEY)) {
			return (Boolean) input.getAttributesManager()
			 			   		  .getSessionAttributes()
			 			   		  .get(YES_NO_SESSION_KEY);
		}
		return Boolean.FALSE;
	}
	
	public void setStepIntoSession(final Steps step, final HandlerInput input) {
		input.getAttributesManager()
		 .getSessionAttributes()
		 .put(STEP_SESSION_KEY, step);
	}
	
	public String getStepFromSession(final HandlerInput input) {
		String step="";
		if (input.getAttributesManager().getSessionAttributes().containsKey(YES_NO_SESSION_KEY)) {
			step = (String) input.getAttributesManager()
			 			   		  .getSessionAttributes()
			 			   		  .get(STEP_SESSION_KEY);
		}
		return step;
	}

	

}
