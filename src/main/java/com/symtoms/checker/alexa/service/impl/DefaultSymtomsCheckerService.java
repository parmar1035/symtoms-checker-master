package com.symtoms.checker.alexa.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
				if (symtomsMap.containsKey("bodyLocation")) {
					symtoms.setBodyLocation(getHealthItem((Map) symtomsMap.get("bodyLocation")));
				}

			}
		}
		return symtoms;
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
}
