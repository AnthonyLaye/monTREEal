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

	@Test
	public void testPlantTree() {
		TreePLEManager tm = new TreePLEManager();
		assertEquals(0,tm.getTrees().size());
		
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
		
	}
	
	public void checkResultTree(String aSpecies, Date aDate, int aId, 
			Person aPerson, Location aLocation, TreePLEManager tm) {
		assertEquals(1, tm.getTrees().size());
	}
	
	@Test
	public void testCutDownTree() {
		TreePLEManager tm = new TreePLEManager();
		TreePLEService ts = new TreePLEService(tm);
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

		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertTrue("Tree with the given ID could not be found",ts.cutDownTree(randomNum));
		}
	
	@Test
	public void testFindAllTrees() {
		TreePLEManager tm = new TreePLEManager();
		assertEquals(0,tm.getTrees().size());
		TreePLEService ts = new TreePLEService(tm);
		
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
				
				String aSpecies1 = "oak";
						c.set(2018, 02, 03);
						Date aDate1 = new Date(c.getTimeInMillis());
						Integer randomNum1 = 2;
						String name1 = "Jack";
						Float longitude1 = 4f;
						Float latitude1 = 5f;
						String municipality1 = "Outremont";
						Person p1 = new Person(name1, tm);
						Location l1 = new Location(longitude1,latitude1,municipality1);

		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
			ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

			
			List<Tree> registeredTrees = ts.findAllTrees();
			assertEquals(2,tm.getTrees().size());

			
			Assert.assertEquals("willow",registeredTrees.get(0).getSpecies());
			Assert.assertEquals("oak",registeredTrees.get(1).getSpecies());
		    
		    Calendar ca = Calendar.getInstance();
		    ca.set(2018, 02, 01);

			Assert.assertEquals(c,registeredTrees.get(0).getDate());
		    ca.set(2018, 02, 03);
			Assert.assertEquals(c,registeredTrees.get(1).getDate());

			Assert.assertEquals(1,registeredTrees.get(0).getId());
			Assert.assertEquals(2,registeredTrees.get(1).getId());

			Assert.assertEquals(new Person("Joe",tm),registeredTrees.get(0).getPerson());
			Assert.assertEquals(new Person("Jack",tm),registeredTrees.get(1).getPerson());

			Assert.assertEquals(tm,registeredTrees.get(0).getTreePLEManager());
			Assert.assertEquals(tm,registeredTrees.get(1).getTreePLEManager());

			Assert.assertEquals(new Location(3f,4f, "NDG"),registeredTrees.get(0).getLocation());
			Assert.assertEquals(new Location(4f,5f, "Outremont"),registeredTrees.get(1).getLocation());
	}

}
