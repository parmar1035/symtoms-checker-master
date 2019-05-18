package com.symtoms.checker.alexa.service;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.symtoms.checker.alexa.data.SelectedSymtoms;
import com.symtoms.checker.alexa.data.Steps;

public interface SymtomsCheckerService {

	final String SYMTOMS_SESSION_KEY = "SYMTOMS_SESSION_KEY";
	final String YES_NO_SESSION_KEY = "YES_NO_SESSION_KEY";
	final String STEP_SESSION_KEY = "STEP_SESSION_KEY";
	
	void clearSymtomSession(final HandlerInput input);
	
	void setSymtomsIntoSession(final SelectedSymtoms symtoms, final HandlerInput input);
	
	SelectedSymtoms getSymtomsFromSession(final HandlerInput input);

	void setYesNoIntent(final Boolean flag, final HandlerInput input);
	
	Boolean isYesNoIntent(final HandlerInput input);
	
	void setStepIntoSession(final Steps steps, final HandlerInput input);
	
	String getStepFromSession(final HandlerInput input);
}
