package com.symtoms.checker.alexa.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.symtoms.checker.alexa.data.SelectedSymtoms;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthItem;
import com.symtoms.checker.alexa.service.SymtomsCheckerService;

public class DefaultSymtomsCheckerService implements SymtomsCheckerService{

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
			
			Map symtomsMap = (Map) input.getAttributesManager()
										.getSessionAttributes()
										.get(SYMTOMS_SESSION_KEY);
			if (null != symtomsMap) {
				populateBodyLocation(symtomsMap, symtoms);
				populateSpecificBodyLocation(symtomsMap, symtoms);
				populateProposedSymtoms(symtomsMap, symtoms);
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

	private HealthItem getHealthItem(final Map itemMap) {
		if(null != itemMap) {
			HealthItem item = new HealthItem();
			item.ID = (int) itemMap.get("ID");
			item.Name = (String) itemMap.get("Name");
			return item;
		}
		return null;
	}
	private void populateBodyLocation(Map symtomsMap, SelectedSymtoms symtoms) {
		if (symtomsMap.containsKey("bodyLocationList")) {
			List<Map> bodyLocationListMap = (List) symtomsMap.get("bodyLocationList");
			List<HealthItem> bodyLocationList = new ArrayList<HealthItem>();
			for(Map bodyLocation: bodyLocationListMap) {
				bodyLocationList.add(getHealthItem(bodyLocation));
			}
			symtoms.setBodyLocationList(bodyLocationList);
		}
		if (symtomsMap.containsKey("bodyLocationCount")) {
			symtoms.setBodyLocationCount((int) symtomsMap.get("bodyLocationCount"));
		}
		if (symtomsMap.containsKey("selectedBodyLocation")) {
			symtoms.setSelectedBodyLocation(getHealthItem((Map) symtomsMap.get("selectedBodyLocation")));
		}
		
	}
	private void populateSpecificBodyLocation(Map symtomsMap, SelectedSymtoms symtoms) {
		if (symtomsMap.containsKey("specificBodyLocationList")) {
			List<Map> bodyLocationListMap = (List) symtomsMap.get("specificBodyLocationList");
			List<HealthItem> bodyLocationList = new ArrayList<HealthItem>();
			for(Map bodyLocation: bodyLocationListMap) {
				bodyLocationList.add(getHealthItem(bodyLocation));
			}
			symtoms.setSpecificBodyLocationList(bodyLocationList);
		}
		if (symtomsMap.containsKey("specificBodyLocationCount")) {
			symtoms.setSpecificBodyLocationCount((int) symtomsMap.get("specificBodyLocationCount"));
		}
		if (symtomsMap.containsKey("selectedSpecificBodyLocation")) {
			symtoms.setSelectedSpecificBodyLocation(getHealthItem((Map) symtomsMap.get("selectedSpecificBodyLocation")));
		}

	}
	private void populateProposedSymtoms(Map symtomsMap, SelectedSymtoms symtoms) {
		if (symtomsMap.containsKey("proposedSystomList")) {
			List<Map> proposedSystomListMap = (List) symtomsMap.get("proposedSystomList");
			List<HealthItem> proposedSystomList = new ArrayList<HealthItem>();
			for(Map proposedSystom: proposedSystomListMap) {
				proposedSystomList.add(getHealthItem(proposedSystom));
			}
			symtoms.setProposedSystomList(proposedSystomList);
		}
		if (symtomsMap.containsKey("specificProposedSystomCount")) {
			symtoms.setSpecificProposedSystomCount((int) symtomsMap.get("specificProposedSystomCount"));
		}
		if (symtomsMap.containsKey("selectedProposedSystomList")) {
			List<Integer> proposedSystomListMap = (List) symtomsMap.get("selectedProposedSystomList");
			Set<Integer> proposedSystomList = new HashSet<Integer>();
			proposedSystomList.addAll(proposedSystomListMap);
			symtoms.setSelectedProposedSystomList(proposedSystomList);
		}
		
	}
}
