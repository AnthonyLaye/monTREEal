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


public class TestGetAllTreesByMunicipality {
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
	public void testGetTreesByMunicipalityIsNull() {
		TreePLEManager tm = new TreePLEManager();
		assertEquals(0, tm.getTrees().size());
		try {		
			List<Tree> trees = ts.getTreesByMunicipality(null);
		} catch (InvalidInputException e) {
			assertEquals(e.getMessage(), "municipality is empty!");
		}
	}
	
	@Test
	public void TestGetAllTreesByMunicipalityMunitipleExist() {
		TreePLEManager tm = new TreePLEManager();
		TreePLEService ts = new TreePLEService(tm);
		assertEquals(0, tm.getTrees().size());
		
		String aSpecies = "willow";
		Calendar c = Calendar.getInstance();
		c.set(2018, 03, 10);
		Date aDate = new Date(c.getTimeInMillis());
		Integer randomNum = 1;
		String name = "Joe";
		Float longitude = 3f;
		Float latitude = 4f;
		String municipality = "NDG";
		Person p = new Person(name, "student@mail.ca","the321",tm);
		Location l = new Location(longitude,latitude,municipality);
		
		String aSpecies1 = "oak";
		c.set(2018, 03, 11);
		Date aDate1 = new Date(c.getTimeInMillis());
		Integer randomNum1 = 2;
		String name1 = "Jack";
		Float longitude1 = 4f;
		Float latitude1 = 5f;
		String municipality1 = "Outremont";
		Person p1 = new Person(name1, "student@mail.ca","the321",tm);
		Location l1 = new Location(longitude1,latitude1,municipality1);
		
		String aSpecies2 = "oak";
		c.set(2018, 03, 12);
		Date aDate2 = new Date(c.getTimeInMillis());
		Integer randomNum2 = 3;
		String name2 = "John";
		Float longitude2 = 6f;
		Float latitude3 = 7f;
		String municipality2 = "Outremont";
		Person p2 = new Person(name1,"student@mail.ca","the321", tm);
		Location l2 = new Location(longitude1,latitude1,municipality1);

		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
			ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
			ts.createTree(aSpecies2, aDate2, randomNum2, p2, l2);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		List<Tree> TreesByMunicipality = new ArrayList<Tree>();
		
		try {
			TreesByMunicipality = ts.getTreesByMunicipality("Outremont");
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(2,TreesByMunicipality.size());
		
		Assert.assertEquals("oak",TreesByMunicipality.get(0).getSpecies());
		Assert.assertEquals("oak", TreesByMunicipality.get(1).getSpecies());
    
		Assert.assertEquals(2,TreesByMunicipality.get(0).getId());
		Assert.assertEquals(3,TreesByMunicipality.get(1).getId());

		Assert.assertEquals(p1,TreesByMunicipality.get(0).getPerson());
		Assert.assertEquals(p2,TreesByMunicipality.get(1).getPerson());

		Assert.assertEquals(tm,TreesByMunicipality.get(0).getTreePLEManager());
		Assert.assertEquals(tm,TreesByMunicipality.get(1).getTreePLEManager());

		Assert.assertEquals(l1,TreesByMunicipality.get(0).getLocation());
		Assert.assertEquals(l2,TreesByMunicipality.get(1).getLocation());
	}
	
	
	@Test
	public void TestGetAllTreesByMunicipalityNotExist() {
		TreePLEManager tm = new TreePLEManager();
		TreePLEService ts = new TreePLEService(tm);
		assertEquals(0, tm.getTrees().size());
		
		String aSpecies = "willow";
		Calendar c = Calendar.getInstance();
		c.set(2018, 03, 10);
		Date aDate = new Date(c.getTimeInMillis());
		Integer randomNum = 1;
		String name = "Joe";
		Float longitude = 3f;
		Float latitude = 4f;
		String municipality = "NDG";
		Person p = new Person(name,"student@mail.ca","the321", tm);
		Location l = new Location(longitude,latitude,municipality);
		
		String aSpecies1 = "oak";
		c.set(2018, 03, 11);
		Date aDate1 = new Date(c.getTimeInMillis());
		Integer randomNum1 = 2;
		String name1 = "Jack";
		Float longitude1 = 4f;
		Float latitude1 = 5f;
		String municipality1 = "Outremont";
		Person p1 = new Person(name1,"student@mail.ca","the321", tm);
		Location l1 = new Location(longitude1,latitude1,municipality1);
		
		String aSpecies2 = "oak";
		c.set(2018, 03, 12);
		Date aDate2 = new Date(c.getTimeInMillis());
		Integer randomNum2 = 3;
		String name2 = "John";
		Float longitude2 = 6f;
		Float latitude3 = 7f;
		String municipality2 = "Outremont";
		Person p2 = new Person(name1, "student@mail.ca","the321",tm);
		Location l2 = new Location(longitude1,latitude1,municipality1);

		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
			ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
			ts.createTree(aSpecies2, aDate2, randomNum2, p2, l2);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		List<Tree> TreesByMunicipality = new ArrayList<Tree>();
		
		try {
			TreesByMunicipality = ts.getTreesByMunicipality("Test");
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		Assert.assertEquals(0,TreesByMunicipality.size());
	}
}
