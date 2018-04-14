package ca.mcgill.ecse321.treePLE.dto;

import java.sql.Date;

import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.Survey;
import ca.mcgill.ecse321.treePLE.model.Tree.Status;

public class TreeDto {

	private String species;
	private double height;
	private int age;
	private Date date;
	private double diameter;
	private int id;
	private Person person;
	private String personName;
	private Location location;
	private double latitude;
	private double longitude;
	private Status status;
	private Survey survey;
	private int surveyId;
	private Date surveyDate;
	public TreeDto() {
	}

	public TreeDto(String species, double height, int age, Date date, double diameter, int id, Person person, Location location, Status status, Survey survey) {
		this.id = id;
		this.species = species;
		this.status = status;
		this.date = date;
		this.age = age;
		this.height = height;
		this.diameter = diameter;
		this.personName = person.getName();
		this.location = location;
		latitude = this.location.getLatitude();
		longitude = this.location.getLongitude();
		this.survey = survey;
		this.surveyId = survey.getId();
		this.surveyDate = survey.getDate();
	}

	public TreeDto(String species, Date date, int id, Person person, Location location, Status status, Survey survey) {
		this.id = id;
		this.species = species;
		this.status = status;
		this.date = date;
		this.person = person;
		this.personName = person.getName();
		this.location = location;
		this.latitude = this.location.getLatitude();
		this.longitude = this.location.getLongitude();
		this.survey = survey;
		this.surveyId = survey.getId();
		this.surveyDate = survey.getDate();
	}

	public String getSpecies() {
		return species;
	}

	public Date getDate() {
		return date;
	}

	public double getHeight() {
		return height;
	}

	public double getDiameter() {
		return diameter;
	}

	public int getID() {
		return id;
	}

	public int getAge(){
		return age;
	}

	public double getLatitude(){
		return this.location.getLatitude();
	}

	public double getLongitude(){
		return this.location.getLongitude();
	}

	public String getName(){
		return personName;
	}
	
	public Status getStatus() {
		return status;
	}
}
