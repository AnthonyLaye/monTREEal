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
import org.junit.Ignore;
import org.junit.Test;

import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;
import ca.mcgill.ecse321.treePLE.service.InvalidInputException;
import ca.mcgill.ecse321.treePLE.service.TreePLEService;

public class TestCutdownTree {
	
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

	//Cutting down tree
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
		Person p = new Person(name,"student@mail.ca","the321", tm);
		Location l = new Location(longitude,latitude,municipality);

		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		ts.markTreeForCutDown(randomNum);

		assertTrue("Tree with the given ID could not be found",ts.cutDownTree(randomNum));
	}

	@Test
	public void testCutDownInexistentTree() {
		TreePLEManager tm = new TreePLEManager();
		TreePLEService ts = new TreePLEService(tm);
		Integer randomNum = 1;
		Assert.assertFalse(ts.cutDownTree(randomNum));
	}
		
	@Test
	public void testCutDownACutDownTree() {
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
		Person p = new Person(name,"student@mail.ca","the321", tm);
		Location l = new Location(longitude,latitude,municipality);

		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		ts.markTreeForCutDown(randomNum);
		assertEquals(true,ts.cutDownTree(randomNum));
		assertEquals(false,ts.cutDownTree(randomNum));
	}
		
	@Test
	public void testCutDownAHealthyTree() {
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
		Person p = new Person(name,"student@mail.ca","the321", tm);
		Location l = new Location(longitude,latitude,municipality);

		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(false,ts.cutDownTree(randomNum));
	}
}
