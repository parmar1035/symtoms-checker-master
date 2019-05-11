package com.symtoms.checker.alexa.service;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.symtoms.checker.alexa.data.SelectedSymtoms;

public interface SymtomsCheckerService {

	final String SYMTOMS_SESSION_KEY = "SYMTOMS_SESSION_KEY";
	final String YES_NO_SESSION_KEY = "YES_NO_SESSION_KEY";
	
	void setSymtomsIntoSession(final SelectedSymtoms symtoms, final HandlerInput input);
	
	SelectedSymtoms getSymtomsFromSession(final HandlerInput input);

	void setYesNoIntent(final Boolean flag, final HandlerInput input);
	
	Boolean isYesNoIntent(final HandlerInput input);
}
