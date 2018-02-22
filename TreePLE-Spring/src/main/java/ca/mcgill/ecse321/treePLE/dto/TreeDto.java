package ca.mcgill.ecse321.treePLE.dto;

import java.sql.Date;

public class TreeDto {

    	private String species;
    	private double height;
    	private int age;
    	private Date date;
    	private double diameter;
    	private int id;

		public TreeDto() {
		}
		
		/*
		public TreeDto(String name) {
			this(name, );
		}*/

		public TreeDto(String species, Double height, int age, Date date, double diameter, int id) {
			this.species = species;
			this.height = height;
			this.age = age;
			this.date = date;
			this.diameter = diameter;
			this.id = id;
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
