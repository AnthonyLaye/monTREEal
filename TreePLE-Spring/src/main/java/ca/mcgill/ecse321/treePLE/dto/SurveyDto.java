package ca.mcgill.ecse321.treePLE.dto;

import java.sql.Date;

import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.Tree;

public class SurveyDto {
	
	private Date date;
	private int id;
	private Person observer;
	private Tree tree;
	
	public SurveyDto() {
	}

	public SurveyDto(Date aDate, int surveyId, Person aObserver, Tree aTree) {
		this.date = aDate;
		this.id = surveyId;
		this.observer = aObserver;
		this.tree = aTree; 
	}
	

	public Date getDate() {
		return date;
	}

	public int getID() {
		return id;
	}

	public Person getPerson(){
		return observer;
	}

	public Tree getTree() { return tree;}

}
