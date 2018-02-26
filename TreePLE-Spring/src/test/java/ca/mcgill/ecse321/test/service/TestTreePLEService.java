package ca.mcgill.ecse321.test.service;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

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

	@Test
	public void testPlantTree() {
		TreePLEManager tm = new TreePLEManager();
		assertEquals(0,tm.getTrees().size());
		
		String aSpecies = "willow"
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
		
	}
	
	public void checkResultTree(String aSpecies, Date aDate, int aId, 
			Person aPerson, Location aLocation, TreePLEManager tm) {
		assertEquals(1, tm.getTrees().size());
	}
	
	@Test
	public void testCutDownTree() {
		TreePLEManager tm = new TreePLEManager();
		TreePLEService ts = new TreePLEService(tm);
		String aSpecies = "willow"
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
		
		ts.createTree(aSpecies, aDate, randomNum, p, l);
		
		assertTrue("Tree with the given ID could not be found",ts.cutDownTree(randomNum));
		}
	
	@Test
	public void testFindAllTrees() {
		TreePLEManager tm = new TreePLEManager();
		assertEquals(0,tm.getTrees().size());
		TreePLEService ts = new TreePLEService(tm);
		
			String aSpecies = "willow"
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
				
				String aSpecies1 = "oak"
						c.set(2018, 02, 03);
						Date aDate1 = new Date(c.getTimeInMillis());
						Integer randomNum1 = 2;
						String name1 = "Jack";
						Float longitude1 = 4f;
						Float latitude1 = 5f;
						String municipality1 = "Outremont";
						Person p1 = new Person(name1, tm);
						Location l1 = new Location(longitude1,latitude1,municipality1);
						
			ts.createTree(aSpecies, aDate, randomNum, p, l);
			ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
			
			List<Tree> registeredTrees = ts.findAllTrees();
			assertEquals(2,tm.getTrees().size());

			
			AssertEquals("willow",registeredTrees.get(0).getSpecies());
		    AssertEquals("oak",registeredTrees.get(1).getSpecies());
		    
		    Calendar c = Calendar.getInstance();
		    c.set(2018, 02, 01);
		    
		    AssertEquals(c,registeredTrees.get(0).getDate());
		    c.set(2018, 02, 03);
		    AssertEquals(c,registeredTrees.get(1).getDate());
		    
		    AssertEquals(1,registeredTrees.get(0).getId());
		    AssertEquals(2,registeredTrees.get(1).getId());
		    
		    AssertEquals(Person("Joe",tm),registeredTrees.get(0).getPerson());
		    AssertEquals(Person("Jack",tm),registeredTrees.get(1).getPerson());
		    
		    AssertEquals(tm,registeredTrees.get(0).getTreePLEManager());
		    AssertEquals(tm,registeredTrees.get(1).getTreePLEManager());
		    
		    AssertEquals(Location(3f,4f, "NDG"),registeredTrees.get(0).getLocation());
		    AssertEquals(Location(4f,5f, "Outremont"),registeredTrees.get(1).getLocation());
	}

}
