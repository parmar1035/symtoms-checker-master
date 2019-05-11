package com.symtoms.checker.alexa.handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazonaws.util.CollectionUtils;
import com.symtoms.checker.alexa.data.SelectedSymtoms;
import com.symtoms.checker.alexa.integration.client.DiagnosisClient;
import com.symtoms.checker.alexa.priaid.diagnosis.model.Gender;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthItem;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class ProposedSymtomsIntentHandler extends AbstractIntentHandler {

	private Logger LOG = LoggerFactory.getLogger(ProposedSymtomsIntentHandler.class);
	
	@Resource(name="diagnosisClient")
	DiagnosisClient diagnosisClient; 

	@Resource(name="symtomsCheckerService")
	SymtomsCheckerService symtomsCheckerService; 

	@Override
	protected void handleInternal(HandlerInput input) {
		
		LOG.error("inside BodyLocationIntentHandler:");
		SelectedSymtoms selectedSymtoms  = symtomsCheckerService.getSymtomsFromSession(input);
		
		List<HealthItem> proposedSystomList = selectedSymtoms.getProposedSystomList();
		
		int index = selectedSymtoms.getSpecificProposedSystomCount();
		
		boolean falg = true;
		if(CollectionUtils.isNullOrEmpty(proposedSystomList)) {
			falg = false;
			List<Integer> selectedSymptoms = new ArrayList<Integer>();
			selectedSymptoms.add(33);
			try {
				proposedSystomList = diagnosisClient.loadProposedSymptoms(selectedSymptoms, Gender.Male, 1977);
			 } catch(Exception ex) {
				 
			 }
			selectedSymtoms.setProposedSystomList(proposedSystomList);
		} 
		HealthItem systom = proposedSystomList.get(index);
		addModel(input, "systomName", systom.Name);
		if(falg) {
			systom = proposedSystomList.get(index-1);
			setSelectedBodyLocation(input, selectedSymtoms, systom);
		}
		index++;
		selectedSymtoms.setSpecificProposedSystomCount(index);
		symtomsCheckerService.setSymtomsIntoSession(selectedSymtoms, input);
		setSessionAttributes(input, "type", "ProposedSymtom");
	}
	private void setSelectedBodyLocation(HandlerInput input, SelectedSymtoms selectedSymtoms, HealthItem systomName) {
		if(symtomsCheckerService.isYesNoIntent(input)) {
			Set<Integer> selectedProposedSystomList = selectedSymtoms.getSelectedProposedSystomList();
			if(null == selectedProposedSystomList) {
				selectedProposedSystomList = new HashSet<Integer>();
			}
			selectedProposedSystomList.add(systomName.ID);
			selectedSymtoms.setSelectedProposedSystomList(selectedProposedSystomList);
		}
	}
}