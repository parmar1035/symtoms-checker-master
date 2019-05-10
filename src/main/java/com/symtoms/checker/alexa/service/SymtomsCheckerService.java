package com.symtoms.checker.alexa.service;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.symtoms.checker.alexa.data.SelectedSymtoms;

public interface SymtomsCheckerService {

	final String SYMTOMS_SESSION_KEY = "SYMTOMS_SESSION_KEY"; 
	
	void setSymtomsIntoSession(final SelectedSymtoms symtoms, HandlerInput input);
	
	SelectedSymtoms getSymtomsFromSession(HandlerInput input);

}
