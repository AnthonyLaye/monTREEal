package ca.mcgill.ecse321.test.service;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;
import ca.mcgill.ecse321.treePLE.service.InvalidInputException;
import ca.mcgill.ecse321.treePLE.service.TreePLEService;

public class TestCreateTree {

	private TreePLEManager tm;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		tm = new TreePLEManager();
	}

	@After
	public void tearDown() throws Exception {
		tm.delete();
	}
    
	//Test create tree
	@Test
	public void testCreateTreeCorrectParameters() {
		
		String error = null;
		
		TreePLEManager tm = new TreePLEManager();
		Assert.assertEquals(0,tm.getTrees().size());
		
		String aSpecies = "willow";
		Calendar c = Calendar.getInstance();
		c.set(2018, 02, 01);
		Date aDate = new Date(c.getTimeInMillis());
		Integer randomNum = 1;
		String name = "Joe";
		Float longitude = 3f;
		Float latitude = 4f;
		String municipality = "NDG";
		Person p = new Person(name,"student@mail.ca","the321", tm);
		Location l = new Location(longitude,latitude,municipality);
		
		TreePLEService ts = new TreePLEService(tm);
		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		
		
		checkResultTree(aSpecies, aDate, randomNum,p,l, tm);
		TreePLEManager tm2 = (TreePLEManager) PersistenceXStream.loadFromXMLwithXStream();
		checkResultTree(aSpecies, aDate, randomNum,p,l, tm2);
		tm2.delete();
		
		try {
			ts.createTree(aSpecies, 199f, 3, aDate, 3f, randomNum, p, l);
		} catch (InvalidInputException e) {
			error = e.getMessage(); 
		}
		
		//assertEquals("The species passed as argument is not a valid tree that can grow on the land of Canada", error);
	}
	
	@Test
	public void testHeightOutOfBounds() {
		
		TreePLEManager tm = new TreePLEManager();
		Assert.assertEquals(0,tm.getTrees().size());
		
		String error = null;
		
		String aSpecies = "willow";
		Calendar c = Calendar.getInstance();
		c.set(2018, 02, 01);
		Date aDate = new Date(c.getTimeInMillis());
		Integer randomNum = 1;
		String name = "Joe";
		Float longitude = 3f;
		Float latitude = 4f;
		String municipality = "NDG";
		Person p = new Person(name,"student@mail.ca","the321", tm);
		Location l = new Location(longitude,latitude,municipality);
		
		TreePLEService ts = new TreePLEService(tm);
		
		try {
			ts.createTree(aSpecies, 201f, 3, aDate, 3f, randomNum, p, l);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Enter a height between 1 and 200 meters", error);
	}
	
	@Test
	public void testSpeciesWithSpecialChars () {
		
		String error = null;
		
		TreePLEManager tm = new TreePLEManager();
		Assert.assertEquals(0,tm.getTrees().size());
		
		String aSpecies = "W1llow$";
		Calendar c = Calendar.getInstance();
		c.set(2018, 02, 01);
		Date aDate = new Date(c.getTimeInMillis());
		Integer randomNum = 1;
		String name = "Joe";
		Float longitude = 3f;
		Float latitude = 4f;
		String municipality = "NDG";
		Person p = new Person(name, "student@mail.ca","the321",tm);
		Location l = new Location(longitude,latitude,municipality);
		
		TreePLEService ts = new TreePLEService(tm);
		
		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		} 
		
		assertEquals("The species passed as argument is not a valid tree that can grow on the land of Canada", error);
	}
	
	public void checkResultTree(String aSpecies, Date aDate, int aId, Person aPerson, Location aLocation, TreePLEManager tm) {
		Assert.assertEquals(1, tm.getTrees().size());
	}

}
