package ca.mcgill.ecse321.treePLE.dto;

import java.sql.Date;

import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;

public class TreeDto {

    	private String species;
    	private double height;
    	private int age;
    	private Date date;
    	private double diameter;
    	private int id;
    	private Person person;
    	private Location location;

		public TreeDto() {
		}
		
		public TreeDto(String species, Double height, int age, Date date, double diameter, int id, Person person, Location location) {
			this.species = species;
			this.height = height;
			this.age = age;
			this.date = date;
			this.diameter = diameter;
			this.id = id;
			this.person = person;
			this.location = location;
		}
		
		public TreeDto(String species, Date date, int id, Person person, Location location) {
			this.species = species;
			this.date = date;
			this.id = id;
			this.person = person;
			this.location = location;
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
}
