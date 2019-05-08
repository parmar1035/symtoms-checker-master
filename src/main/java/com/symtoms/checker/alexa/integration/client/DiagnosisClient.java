package com.symtoms.checker.alexa.integration.client;

import java.util.List;

import com.symtoms.checker.alexa.priaid.diagnosis.model.DiagnosedSpecialisation;
import com.symtoms.checker.alexa.priaid.diagnosis.model.Gender;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthDiagnosis;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthIssueInfo;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthItem;
import com.symtoms.checker.alexa.priaid.diagnosis.model.HealthSymptomSelector;
import com.symtoms.checker.alexa.priaid.diagnosis.model.SelectorStatus;

public interface DiagnosisClient {
	
	/// <summary>
    /// Load all symptoms
    /// </summary>
    /// <returns>Returns list of all symptoms</returns>
	public List<HealthItem> loadSymptoms();
	
	/// <summary>
    /// Load all issues
    /// </summary>
    /// <returns>Returns list of all issues</returns>
	public List<HealthItem> loadIssues();
	 
	 /// <summary>
     /// Load detail informations about selected issue
     /// </summary>
     /// <param name="issueId"></param>
     /// <returns>Returns detail informations about selected issue</returns>
	 public HealthIssueInfo loadIssueInfo(int issueId);

	 /// <summary>
     /// Load calculated list of potential issues for selected parameters
     /// </summary>
     /// <param name="selectedSymptoms">List of selected symptom ids</param>
     /// <param name="gender">Selected person gender (Male, Female)</param>
     /// <param name="yearOfBirth">Selected person year of born</param>
     /// <returns>Returns calculated list of potential issues for selected parameters</returns>
     public List<HealthDiagnosis> loadDiagnosis(List<Integer> selectedSymptoms, Gender gender, int yearOfBirth) throws Exception;
     
     /// <summary>
     /// Load calculated list of specialisations for selected parameters
     /// </summary>
     /// <param name="selectedSymptoms">List of selected symptom ids</param>
     /// <param name="gender">Selected person gender (Male, Female)</param>
     /// <param name="yearOfBirth">Selected person year of born</param>
     /// <returns>Returns calculated list of specialisations for selected parameters</returns>
     public List<DiagnosedSpecialisation> loadSpecialisations(List<Integer> selectedSymptoms, Gender gender, int yearOfBirth) throws Exception;     
     
     /// <summary>
     /// Load human body locations
     /// </summary>
     /// <returns>Returns list of human body locations</returns>
     public List<HealthItem> loadBodyLocations();
     
     /// <summary>
     /// Load human body sublocations for selected human body location
     /// </summary>
     /// <param name="bodyLocationId">Human body location id</param>
     /// <returns>Returns list of human body sublocations for selected human body location</returns>
     public List<HealthItem> loadBodySubLocations(int bodyLocationId);

     /// <summary>
     /// Load all symptoms for selected human body location
     /// </summary>
     /// <param name="locationId">Human body sublocation id</param>
     /// <param name="selectedSelectorStatus">Selector status (Man, Woman, Boy, Girl)</param>
     /// <returns>Returns list of all symptoms for selected human body location</returns>
     public List<HealthSymptomSelector> loadSublocationSymptoms(int locationId, SelectorStatus selectedSelectorStatus);

     ///<summary>
     /// Load list of proposed symptoms for selected symptoms combination
     /// </summary>
     /// <param name="selectedSymptoms">List of selected symptom ids</param>
     /// <param name="gender">Selected person gender (Male, Female)</param>
     /// <param name="yearOfBirth">Selected person year of born</param>
     /// <returns>Returns list of proposed symptoms for selected symptoms combination</returns>
     public List<HealthItem> loadProposedSymptoms(List<Integer> selectedSymptoms, Gender gender, Integer yearOfBirth) throws Exception;
     /// <summary>
     /// Load red flag text for selected symptom
     /// </summary>
     /// <param name="symptomId">Selected symptom id</param>
     /// <returns>Returns red flag text for selected symptom</returns>
     public String loadRedFlag(int symptomId);

}
