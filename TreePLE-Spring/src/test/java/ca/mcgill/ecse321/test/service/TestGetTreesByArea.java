package ca.mcgill.ecse321.test.service;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;
import ca.mcgill.ecse321.treePLE.service.InvalidInputException;
import ca.mcgill.ecse321.treePLE.service.TreePLEService;


public class TestGetTreesByArea {
	private TreePLEManager tm;
	private TreePLEService ts;

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
		ts = new TreePLEService(tm);
	}

	@After
	public void tearDown() throws Exception {
		tm.delete();
	}

	@Test
	public void testGetTreesByAreaNegativeInputRadius() {
		TreePLEManager tm = new TreePLEManager();
		assertEquals(0, tm.getTrees().size());
		try {		
			List<Tree> trees = ts.getTreesByArea(1f, 2f, -5f);
		} catch (InvalidInputException e) {
			assertEquals(e.getMessage(), "Radius cannot be negative!");
		}
	}

	@Test
	public void testGetTreesByAreaLocationParameterOutOfLowerBound() {
		TreePLEManager tm = new TreePLEManager();
		assertEquals(0, tm.getTrees().size());
		try {		
			List<Tree> trees = ts.getTreesByArea(-1f, -2f, 1f);
		} catch (InvalidInputException e) {
			assertEquals(e.getMessage(), "Invalid geo coordinate! Latitude and longitude only can only be set to range from -180 to 180!");
		}
	}

	@Test
	public void testGetTreesByAreaLocationParameterOutOfUpperBound() {
		TreePLEManager tm = new TreePLEManager();
		assertEquals(0, tm.getTrees().size());
		try {		
			List<Tree> trees = ts.getTreesByArea(181f, 181f, 1f);
		} catch (InvalidInputException e) {
			assertEquals(e.getMessage(), "Invalid geo coordinate! Latitude and longitude only can only be set to range from -180 to 180!");
		}
	}

	@Test
	public void TestGetAllTreesByAreaMultiple() {
		TreePLEManager tm = new TreePLEManager();
		TreePLEService ts = new TreePLEService(tm);
		assertEquals(0, tm.getTrees().size());

		String aSpecies = "willow";
		Calendar c = Calendar.getInstance();
		c.set(2018, 03, 10);
		Date aDate = new Date(c.getTimeInMillis());
		Integer randomNum = 1;
		String name = "Joe";
		Float longitude = 44.01f;
		Float latitude = 44.01f;
		String municipality = "NDG";
		Person p = new Person(name,"student@mail.ca","the321", tm);
		Location l = new Location(longitude,latitude,municipality);

		String aSpecies1 = "oak";
		c.set(2018, 03, 11);
		Date aDate1 = new Date(c.getTimeInMillis());
		Integer randomNum1 = 2;
		String name1 = "Jack";
		Float longitude1 = 44.015f;
		Float latitude1 = 44.015f;
		String municipality1 = "Outremont";
		Person p1 = new Person(name1, "student@mail.ca","the321",tm);
		Location l1 = new Location(longitude1,latitude1,municipality1);

		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
			ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		List<Tree> TreesByArea = new ArrayList<Tree>();

		try {
			TreesByArea = ts.getTreesByArea(42.01f, 42.01f, 5f);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(2,TreesByArea.size());

		Assert.assertEquals("willow",TreesByArea.get(0).getSpecies());
		Assert.assertEquals("oak", TreesByArea.get(1).getSpecies());

		Assert.assertEquals(1,TreesByArea.get(0).getId());
		Assert.assertEquals(2,TreesByArea.get(1).getId());

		Assert.assertEquals(p,TreesByArea.get(0).getPerson());
		Assert.assertEquals(p1,TreesByArea.get(1).getPerson());

		Assert.assertEquals(tm,TreesByArea.get(0).getTreePLEManager());
		Assert.assertEquals(tm,TreesByArea.get(1).getTreePLEManager());

		Assert.assertEquals(l,TreesByArea.get(0).getLocation());
		Assert.assertEquals(l1,TreesByArea.get(1).getLocation());
	}

	@Test
	public void TestGetAllTreesByAreaEmpty() {
		TreePLEManager tm = new TreePLEManager();
		TreePLEService ts = new TreePLEService(tm);
		assertEquals(0, tm.getTrees().size());

		String aSpecies = "willow";
		Calendar c = Calendar.getInstance();
		c.set(2018, 03, 10);
		Date aDate = new Date(c.getTimeInMillis());
		Integer randomNum = 1;
		String name = "Joe";
		Float longitude = 44.01f;
		Float latitude = 44.01f;
		String municipality = "NDG";
		Person p = new Person(name, "student@mail.ca","the321",tm);
		Location l = new Location(longitude,latitude,municipality);

		String aSpecies1 = "oak";
		c.set(2018, 03, 11);
		Date aDate1 = new Date(c.getTimeInMillis());
		Integer randomNum1 = 2;
		String name1 = "Jack";
		Float longitude1 = 44.015f;
		Float latitude1 = 44.015f;
		String municipality1 = "Outremont";
		Person p1 = new Person(name1, "student@mail.ca","the321",tm);
		Location l1 = new Location(longitude1,latitude1,municipality1);

		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
			ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		List<Tree> TreesByArea = new ArrayList<Tree>();

		try {
			TreesByArea = ts.getTreesByArea(45.01f, 45.01f, 1f);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(0,TreesByArea.size());
	}
}

