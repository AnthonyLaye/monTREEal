package ca.mcgill.ecse321.treePLE.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.treePLE.model.CarbonSequestrationManager;
import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.SpeciesDensities;
import ca.mcgill.ecse321.treePLE.model.Survey;
import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.model.Tree.Status;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceDensity;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;

@Service
public class TreePLEService {

	public TreePLEManager tm;
	public CarbonSequestrationManager csm;

	public TreePLEService(TreePLEManager tm) {
		this.tm = tm;
	}

	/**
	 * This method that checks if a user is already registered in the system
	 * and can access by logging in the system. This will eventually be used to let
	 * different access depending on the user. If it is either a Resident or a Scientist
	 * @param email, Is a string value that needs to contain the specific character "@" and
	 * one of the following extension .ca, .com, .org, .fr
	 * @param password, is an input string chosen by the user
	 * @return a user of type Person with its attribute for name, email address, password, and role
	 * @throws InvalidInputException if there is no @ in the email, if the email does not end with one of the following extension
	 * .com, .ca, .org, or .fr., if the password does not match the email address, and lastly if the email address
	 * does not exist in the system.
	 */
	public Person login(String email, String password) throws InvalidInputException{

		List<Person> AllUsers=tm.getPerson();

		if(email.isEmpty() || password.isEmpty()) {
			throw new InvalidInputException("Nothing is entered. You may want to register below");
		}

		else if(email.contains("@")) {
			if(email.contains(".com")||email.contains(".ca")||email.contains(".org")||email.contains(".fr")) {
				for (Person user: AllUsers) {
					String userEmail = user.getEmail();
					if(userEmail.contentEquals(email)) {
						String userPassword = user.getPassword();
						if(userPassword.contentEquals(password)) {
							return user;
						}
						else {
							throw new InvalidInputException ("incorrect password");
						}
					}
					else {
						throw new InvalidInputException("This email address is not registered in the system. You may want to register below.");
					}
				}
			}
			else {
				throw new InvalidInputException("The email address passed does not have the correct extension. Ex: .com, .ca, .org or .fr");
			}
		}
		else {
			throw new InvalidInputException("The email passed is not an email, does not contain @. You may want to register below.");
		}
		return null;
	}


	public Tree createTree(String aSpecies, float aHeight, int aAge, Date aDate, float aDiameter, int aId, Person aPerson, Location aLocation)
			throws InvalidInputException{
		String name="";
		if(aSpecies == null || aHeight == 0 || aAge == 0 || aDate == null || aDiameter == 0){
			throw new InvalidInputException("Something is empty!");
		}

		if(aHeight<20001 && aHeight>0) {
			if(aHeight<35001 && aHeight>0) {

				if (aSpecies.chars().allMatch(Character::isLetter)) {
					String nameWithout = aSpecies.replace("\\s", "");
					String speciesReadable = nameWithout.toLowerCase();
					csm=(CarbonSequestrationManager)PersistenceDensity.loadFromXMLwithXStream();
					List<SpeciesDensities> sd =new ArrayList<SpeciesDensities>();
					sd = csm.getSpeciesDensities();
					for (SpeciesDensities s: sd) {
						name = s.getSpecies();
						if(name.equals(speciesReadable)) {
							String nameOut = s.getUISpecies();
							Tree tree= new Tree(nameOut, aDate, aId, aPerson, tm, aLocation);
							tm.addTree(tree);
							PersistenceXStream.saveToXMLwithXStream(tm);
							return tree;
						}
					}	
				} else {
					throw new InvalidInputException("The species passed as argument is not a valid tree that can grow on the land of Canada");
				}
			}else {
				throw new InvalidInputException("The diameter is not between 1 cm and 35 000 cm");
			}
		} else {
			throw new InvalidInputException("Enter a height between 1 and 200 meters");
		}
		return null;
	}

	/**
	 * This method is the short version for adding a tree in the system when it has been planted
	 * If the person who is adding the tree does not know the measurements for the age, the height or the diameter
	 * he/she can still plant the tree, and these data will eventually be filled by a scientist
	 * One feature of this method, is that a client can write a species name with Capitals or spaces
	 * and will look into the file if this trees can grow on the lands of Canada
	 * @param aSpecies, the species of tree planted
	 * @param aDate, the current date it has been planted
	 * @param aId, the random id number to track the tree
	 * @param aPerson, the person it belongs too
	 * @param aLocation, its position on the map
	 * @return A tree, as it has been added to the system
	 * @throws InvalidInputException when the species entered contains characters that are not letters
	 */
	public Tree createTree(String aSpecies, Date aDate, int aId, Person aPerson, Location aLocation)
			throws InvalidInputException{
		String name = "";
		String personName = aPerson.getName().toString();

		if(aSpecies == null || aDate == null || personName == null || personName == " "){
			throw new InvalidInputException("Something is empty!");
		}
		if (aSpecies.chars().allMatch(Character::isLetter)) {
			String nameWithout = aSpecies.replace("\\s", "");
			String speciesReadable = nameWithout.toLowerCase();
			csm=(CarbonSequestrationManager)PersistenceDensity.loadFromXMLwithXStream();
			List<SpeciesDensities> sd =new ArrayList<SpeciesDensities>();
			sd = csm.getSpeciesDensities();
			for (SpeciesDensities s: sd) {
				name = s.getSpecies();
				if(name.equals(speciesReadable)) {
					String nameOut = s.getUISpecies();
					Tree tree= new Tree(nameOut, aDate, aId, aPerson, tm, aLocation);
					tm.addTree(tree);
					PersistenceXStream.saveToXMLwithXStream(tm);
					return tree;
				}
			}	
		} else {
			throw new InvalidInputException("The species passed as argument is not a valid tree that can grow on the land of Canada");
		}
		return null;
	}

	/**
	 * This method lists ALL the trees registered in the TreePLE System
	 * @return a list of trees (all the trees registered)
	 * @throws InvalidInputException, when no trees are registered
	 */
	public List<Tree> findAllTrees() throws InvalidInputException {

		List<Tree> treelist = new ArrayList<Tree>();

		for (Tree t: tm.getTrees()) {
			if(!t.getStatus().equals(Status.Cutdown)) {
				treelist.add(t);
			}
		}
		if (treelist.isEmpty()) {
			throw new InvalidInputException("There are no trees to get from the manager");
		}
		return treelist;
	}

	/**
	 * This method returns the list of trees that belong to a specific resident
	 * @param The name of the resident is passed as argument, the name is of type String
	 * @return a list of all the trees that belong to this resident
	 */
	public List<Tree> findTreesForResident(String name) {

		List<Tree> residentTrees = new ArrayList<Tree>();

		for (Tree t: tm.getTrees()) {
			if((t.getPerson().getName()).contentEquals(name)) {
				residentTrees.add(t);
			}
		}
		return residentTrees;
	}

	/**
	 * This method searches for trees that is within a specified municipality
	 * @param a string a municipality
	 * @return a list of corresponding trees
	 * @throws InvalidInputException
	 */
	public List<Tree> getTreesByMunicipality(String municipality) 
			throws InvalidInputException {

		if(municipality == null) {
			throw new InvalidInputException("municipality is empty!");
		}

		List<Tree> treesByMunicipality = new ArrayList<Tree>();
		for(Tree tree: tm.getTrees()) {
			if(tree.getLocation().getMunicipality().equalsIgnoreCase(municipality)) {
				treesByMunicipality.add(tree);
			}
		}
		return treesByMunicipality;
	}

	/**
	 * This is a revised method for getTreesByArea that will list all the trees 
	 * in a given area, specified by the parameters in entry
	 * @param lat, this is the position of the latitude we want to look around
	 * @param lon, this is the position of the longitude we want to look around
	 * @param distance, this is how far on each side of the point (latitude, longitude) 
	 * we want to cover up. It is important to note that the area will be a square
	 * with that specified point at its center
	 * @return a list of trees that are contained inside this area
	 * @throws InvalidInputException, when 
	 */
	public List<Tree> getTreesByAreaRevised(float lat, float lon, float distance) 
			throws InvalidInputException  {
		List<Tree> treesByArea=new ArrayList<Tree>();
		float latitude, longitude;
		float lowerlat, lowerlon, higherlat, higherlon;
		if(distance <= 0 ) {
			throw new InvalidInputException("Distance cannot be negative!");
		}

		if(-90>lat || lat>90 || -180>lon || lon>180) {
			throw new InvalidInputException("Invalid geo coordinate! Latitude and longitude only can only be set to range from -180 to 180!");
		}

		higherlat = lat+distance;
		higherlon = lon+distance;
		lowerlat = lat-distance;
		lowerlon = lat-distance;

		for(Tree t: tm.getTrees()) {
			Location location = t.getLocation();
			latitude = location.getLatitude();
			longitude = location.getLongitude();

			if((lowerlat<latitude) && (higherlat>latitude)) {
				if((lowerlon<longitude) && (higherlon>longitude)) {
					treesByArea.add(t);
				}
			}
		}
		return treesByArea;
	}

	/**
	 * This method searches for trees that is within a specific area (circular) with
	 * center at the location specified by longitude and latitude
	 * @param a float longitude
	 * @param a float latitude
	 * @param a float diagonal
	 * @return a list of corresponding trees
	 * @throws InvalidInputException
	 */
	public List<Tree> getTreesByArea(float lat, float lon, float radius) 
			throws InvalidInputException {

		if(radius <= 0 ) {
			throw new InvalidInputException("Radius cannot be negative!");
		}

		if(-90>lat || lat>90 || -180>lon || lon>180) {
			throw new InvalidInputException("Invalid geo coordinate! Latitude and longitude only can only be set to range from -180 to 180!");
		}

		List<Tree> treesByArea = new ArrayList<Tree>();
		for(Tree tree: tm.getTrees()) {
			if(haversineKm(lat, lon, tree.getLocation().getLatitude(),tree.getLocation().getLongitude())<=radius) {
				treesByArea.add(tree);
			}
		}
		return treesByArea;
	}

	/**
	 * This method is a helper method that returns distance between two points on GeoCoordinates
	 * This method is a simple implementation of Haversine's formula to calculate distance based on geocoordinates
	 * This method returns the distance in kilometer
	 * @param lat1
	 * @param long1
	 * @param lat2
	 * @param long2
	 * @return float distance of two coordinates
	 */
	public float haversineKm(float lat1, float long1, float lat2, float long2) {
		float dr2 = (float)Math.PI / 180;
		float dlong = (long1 - long2) * dr2;
		float dlat = (lat1 - lat2) * dr2;
		float a = (float)Math.pow(Math.sin(dlat/2), 2) + (float)Math.cos(lat1*dr2)* (float)Math.cos(lat2*dr2) * (float)Math.pow(Math.sin(dlong/2.0), 2);
		float c = 2 * (float)Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		float d = 6371 * c;
		return d;
	}

	/**
	 * This method searches for trees that belongs to the same species
	 * @param a string species
	 * @return a list of trees belong to the species
	 */
	public List<Tree> getAllTreesBySpecies(String species) 
			throws InvalidInputException {

		if(species == null) {
			throw new InvalidInputException("Species is empty!");
		}

		List<Tree> treesBySpecies = new ArrayList<Tree>();
		for(Tree tree: tm.getTrees()) {
			if(tree.getSpecies().equalsIgnoreCase(species)) {
				treesBySpecies.add(tree);
			}
		}
		return treesBySpecies;
	}

	/**
	 * Method that tells if the tree specified by ID is cutdown
	 * @param aId the id of the tree we want information on his status
	 * @return  wasCutDown 
	 * true, if the status of the tree was cut down, does not exist in reality
	 * false, if the tree is not cut down, exist in reality
	 * @throws InvalidInputException
	 */
	public boolean cutDownTree(int aId) {
		boolean wasCutDown = false;
		for (Tree tree : tm.getTrees()) {
			int treeID = tree.getId();
			if (aId == treeID) {
				if(tree.getStatus().equals(Status.MarkedForCutdown)) {
					tree.setStatus(Status.Cutdown);
					wasCutDown = true;
				} else {
					wasCutDown = false;
				}
				break;
			}
		}
		PersistenceXStream.saveToXMLwithXStream(tm);
		return wasCutDown;
	}

	/**
	 * Method that tells if the tree specified by ID is marked as cutdown
	 * @param aId the id of the tree we want information on his status
	 * @return markedForCutDown
	 * true, if the tree is marked for cut down
	 * false, if the tree is not marked for cut down
	 */
	public boolean markTreeForCutDown(int aId) {
		boolean markedForCutDown = false;
		for (Tree tree : tm.getTrees()) {
			int treeID = tree.getId();
			if (aId == treeID) {
				if(tree.getStatus().equals(Status.Cutdown) || tree.getStatus().equals(Status.MarkedForCutdown)) {
					markedForCutDown = false;
					break;
				}
				else {
					tree.setStatus(Status.MarkedForCutdown);
					markedForCutDown = true;
					break;
				}
			}
		}
		PersistenceXStream.saveToXMLwithXStream(tm);
		return markedForCutDown;
	}

	/**
	 * Method that tells if the tree specified by ID is marked as diseased
	 * @param aId the id of the tree we want information on his status
	 * @return markedDiseased 
	 * true, if the tree is marked as diseased
	 * false, if the tree is not marked as diseased
	 */
	public boolean markTreeDiseased(int aId) {
		boolean markedDiseased = false;
		for (Tree tree : tm.getTrees()) {
			int treeID = tree.getId();
			if (aId == treeID) {
				if(tree.getStatus() == Status.Cutdown || tree.getStatus() == Status.Diseased || tree.getStatus() == Status.MarkedForCutdown) {
					markedDiseased = false;
					break;
				}
				tree.setStatus(Status.Diseased);
				markedDiseased = true;
				break;
			}
		}
		PersistenceXStream.saveToXMLwithXStream(tm);
		return markedDiseased;
	}

	public boolean markTreeHealthy(int aId) {
		boolean markedHealthy = false;
		for (Tree tree : tm.getTrees()) {
			int treeID = tree.getId();
			if (aId == treeID) {
				if (tree.getStatus() == Status.Cutdown) {
					markedHealthy = false;
					break;
				}
				tree.setStatus(Status.Healthy);
				markedHealthy = true;
				break;
			}
		}
		PersistenceXStream.saveToXMLwithXStream(tm);
		return markedHealthy;
	}

	public double calculateCarbonSequestration(List<Tree> trees) {
		int tonneOfCO2=3670;	//this value is set for 1000kg of carbon
		int density=0;
		double index=0;
		double volume=0;
		double biomass=0;
		double result=0;
		List<SpeciesDensities> listSpeciesDensities=null;
		csm=(CarbonSequestrationManager)PersistenceDensity.loadFromXMLwithXStream();
		for (Tree t: trees) {
			String speciesName=t.getSpecies();
			//compare the speciesName with the file to get the density
			listSpeciesDensities = csm.getSpeciesDensities();
			for(SpeciesDensities sd: listSpeciesDensities) {
				String species= sd.getSpecies();
				if(speciesName.equalsIgnoreCase(species)) {
					density=sd.getDensity();
					break;
				}
			}
			//calculate the volume occupied by the tree
			volume= (double)Math.PI*((t.getDiameter()/2)/100)*((t.getDiameter()/2)/100)*(t.getHeight()/3)/100;
			//biomass in kilograms
			biomass = density*volume;
			index=biomass*tonneOfCO2/1000;
			result=result+index;
		}
		return result;
	}

	/**
	 * This method is to calculate the biodiversity index of a list of given trees. 
	 * the index is a sustainability attribute to see how many different species there is
	 * relative to the number of individuals (trees)
	 * @param a list of trees of type Tree
	 * @return a double that is a ratio between the number of different species over the number
	 * of trees
	 */
	public double calculateBiodiversityIndex(List<Tree> trees) {
		double index=0;
		double counterSpecies=0;
		double nbTrees=0;
		List<String> diffSpecies = new ArrayList<String>();		//initially empty and add elements
		int i=0;
		for(Tree t: trees) {
			String speciesName=t.getSpecies();
			nbTrees=nbTrees+1;

			if(!(containsString(diffSpecies, speciesName))) {
				diffSpecies.add(i, speciesName);
				counterSpecies=counterSpecies+1;
				i=i+1;		//increment the index to add elements
			}
		}
		if(trees==null) {
			index=0;
		}
		else {
			index=counterSpecies/nbTrees;
		}
		return index;
	}

	public double calculateBiodiversityIndexFromTrees(List<String> treesInArea) {
		double index=0;
		double counterSpecies=0;
		double nbTrees=0;
		List<String> diffSpecies = new ArrayList<String>();		//initially empty and add elements
		int i=0;
		for(String t: treesInArea) {
			String speciesName= t;
			nbTrees=nbTrees+1;

			if(!(containsString(diffSpecies, speciesName))) {
				diffSpecies.add(i, speciesName);
				counterSpecies=counterSpecies+1;
				i=i+1;		//increment the index to add elements
			}
		}
		if(treesInArea==null) {
			index=0;
		}
		else {
			index=counterSpecies/nbTrees;
		}
		return index;
	}

	/**
	 * This method is a sustainability attribute to know what is the required amount of water we should
	 * water the trees in a specific area each month. 
	 * @param Takes in a list of trees, to be able to get the diameters in cm of each tree to make 
	 * the calculations on it
	 * @return water, which is a double that is the amount of water required in Liters
	 */
	public double calculateWaterNeeded(List<Tree> trees) {
		double water=0;
		double diameter;
		double totalDiameter=0;
		for(Tree t:trees) {
			diameter = t.getDiameter();
			totalDiameter = totalDiameter+diameter;
		}
		water = totalDiameter/2.54*10*3.78541;
		return water;
	}

	/**
	 * The method containsString is to check if a string is present in a list of strings
	 * @param a List of Strings: here the list is a list of different species of trees diffSpecies
	 * @param a string name that needs to be compared to the ones inside the list
	 * @return a boolean value
	 * @return true, if the string passed is already in the list
	 * @return false, if the string passed is NOT in the list
	 */
	public boolean containsString(List<String> diffSpecies, String name) {
		for(String diffT: diffSpecies) {
			if(diffT.equals(name)) {
				return true;
			}
		}
		return false;		//going through the whole list and no match
	}

	/**
	 * Method to create a survey on a tree, this will permit to change data of a current tree 
	 * in the system
	 * @param aDate, the date when the survey has been conducted
	 * @param surveyId, the id to trace the survey
	 * @param aObserver, the name of the scientist conducting the survey
	 * @param treeId, the tree the survey is done on
	 * @return return a survey of type Survey with all the information 
	 * @throws InvalidInputException
	 */
	public Tree createSurvey(Date aDate, int surveyId, Person aObserver, int treeId, float height, float diameter, String status) 
	{	//throws InvalidInputException {
		Tree t1= null;

		//boolean surveyWasAdded = false;
		Status s = Status.Healthy;

		if (status.contentEquals("Healthy")){
			s = Status.Healthy;
		} else if (status.contentEquals("CutDown")){
			s = Status.Cutdown;
		} else if (status.contentEquals("MarkedForCutDown")){
			s = Status.MarkedForCutdown;
		} else if (status.contentEquals("Diseased")) {
			s = Status.Diseased;
		}
		//} else {
		//	throw new InvalidInputException("The status you have inputted does not exist!");
		//}

		for(Tree t: tm.getTrees()) {
			if(t.getId() == treeId) {

				t.setHeight(height);
				t.setDiameter(diameter);
				t.setStatus(s);
				t.addSurvey(aDate, surveyId, aObserver);
				t1 = t;
				//surveyWasAdded = true;
				break;
			}
		}

		PersistenceXStream.saveToXMLwithXStream(tm);
		return t1;
	}


	//needs testing
	public Status getStatus(int id) {//throws InvalidInputException {
		Status treeStatus = Status.Healthy;
		for (Tree tree : tm.getTrees()) {
			int treeID = tree.getId();
			if (id == treeID) {
				treeStatus = tree.getStatus();
				break;
			}
		}
		return treeStatus;
	}


}
