package ca.mcgill.ecse321.test.service;

import static org.junit.Assert.*;


import ca.mcgill.ecse321.treePLE.model.Tree;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;
import ca.mcgill.ecse321.treePLE.service.InvalidInputException;
import ca.mcgill.ecse321.treePLE.service.TreePLEService;

public class TestTreePLEService {

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
		Person p = new Person(name, tm);
		Location l = new Location(longitude,latitude,municipality);
		
		TreePLEService ts = new TreePLEService(tm);
		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
		} catch (InvalidInputException e) {
			fail("Error");
		} 
		
		
		checkResultTree(aSpecies, aDate, randomNum,p,l, tm);
		TreePLEManager tm2 = (TreePLEManager) PersistenceXStream.loadFromXMLwithXStream();
		checkResultTree(aSpecies, aDate, randomNum,p,l, tm2);
		tm2.delete();
		
		try {
			ts.createTree(aSpecies, 199f, 3, aDate, 3f, randomNum, p, l);
		} catch (InvalidInputException e) {
			fail("Errors in the parameters of created tree");
		}
	}
	
	@Test
	public void testHeightOutOfBounds() {
		
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
		Person p = new Person(name, tm);
		Location l = new Location(longitude,latitude,municipality);
		
		TreePLEService ts = new TreePLEService(tm);
				try {
					ts.createTree(aSpecies, 201f, 3, aDate, 3f, randomNum, p, l);
				} catch (InvalidInputException e) {
					fail("Incorrect height, tree height should be between (0,201) ");
				}
	}
	
	@Test
	public void testSpeciesWithSpecialChars () {
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
		Person p = new Person(name, tm);
		Location l = new Location(longitude,latitude,municipality);
		
		TreePLEService ts = new TreePLEService(tm);
		
		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
		} catch (InvalidInputException e) {
			fail("Error, species should be a string of alphabetical characters");
		} 
	}
	
	public void checkResultTree(String aSpecies, Date aDate, int aId, 
			Person aPerson, Location aLocation, TreePLEManager tm) {
		Assert.assertEquals(1, tm.getTrees().size());
	}
	
	
	


}
