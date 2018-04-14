package ca.mcgill.ecse321.test.service;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

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

public class TestFindAllTrees {

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

	//List all trees
	@Test
	public void testFindAllTrees() {

		TreePLEManager tm = new TreePLEManager();
		assertEquals(0,tm.getTrees().size());
		TreePLEService ts = new TreePLEService(tm);

		List<Tree> registeredTrees = null;

		String aSpecies = "willow";
		Calendar c = Calendar.getInstance();
		c.set(2018, 02, 01);
		Date aDate = new Date(c.getTimeInMillis());
		Integer randomNum = 1;
		String name = "Joe";
		Float longitude = 3f;
		Float latitude = 4f;
		String municipality = "NDG";
		Person p = new Person(name,"", "", tm);
		Location l = new Location(longitude,latitude,municipality);

		String aSpecies1 = "oak";
		c.set(2018, 02, 03);
		Date aDate1 = new Date(c.getTimeInMillis());
		Integer randomNum1 = 2;
		String name1 = "Jack";
		Float longitude1 = 4f;
		Float latitude1 = 5f;
		String municipality1 = "Outremont";
		Person p1 = new Person(name1, "", "", tm);
		Location l1 = new Location(longitude1,latitude1,municipality1);

		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
			ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		try {
			registeredTrees = ts.findAllTrees();
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}

		assertEquals(2,tm.getTrees().size());


		assertEquals("willow",registeredTrees.get(0).getSpecies());
		assertEquals("oak",registeredTrees.get(1).getSpecies());

		Calendar ca = Calendar.getInstance();
		ca.set(2018, 02, 01);

		assertEquals(aDate,registeredTrees.get(0).getDate());
		ca.set(2018, 02, 03);
		assertEquals(aDate1,registeredTrees.get(1).getDate());

		assertEquals(1,registeredTrees.get(0).getId());
		assertEquals(2,registeredTrees.get(1).getId());

		assertEquals(p,registeredTrees.get(0).getPerson());
		assertEquals(p1,registeredTrees.get(1).getPerson());

		assertEquals(tm,registeredTrees.get(0).getTreePLEManager());
		assertEquals(tm,registeredTrees.get(1).getTreePLEManager());

		assertEquals(l,registeredTrees.get(0).getLocation());
		assertEquals(l1,registeredTrees.get(1).getLocation());

	}


	@Test
	public void testEmptyTreeListFindAllTrees() {
		
		String error = null;
		
		TreePLEManager tm = new TreePLEManager();
		assertEquals(0,tm.getTrees().size());
		TreePLEService ts = new TreePLEService(tm);

		try {
			ts.findAllTrees();
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("There are no trees to get from the manager", error);
	}
}
