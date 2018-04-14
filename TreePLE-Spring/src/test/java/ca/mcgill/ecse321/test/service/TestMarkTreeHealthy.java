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

import ca.mcgill.ecse321.treePLE.controller.TreePLERestController;
import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.model.Tree.Status;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;
import ca.mcgill.ecse321.treePLE.service.InvalidInputException;
import ca.mcgill.ecse321.treePLE.service.TreePLEService;

public class TestMarkTreeHealthy {

	private TreePLEManager tm;
	private TreePLEService ts;
	private TreePLERestController trc;

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
	public void testMarkTreeHealthy() {
		Calendar c = Calendar.getInstance();
		c.set(2018, 02, 01);
		Date aDate = new Date(c.getTimeInMillis());
		Float longitude = 3f;
		Float latitude = 4f;
		String municipality = "NDG";
		Person p = new Person("jim", "student@mail.ca","the321",tm);
		Location l = new Location(longitude,latitude,municipality);

		Tree tree= new Tree("oak", 12, 4, aDate, 13, 8765211, p, tm, l);

		try {
			ts.createTree("oak", aDate, 8765211, p, l);
		} catch (InvalidInputException e) {
			fail("Error");
		}

		assertEquals(Status.Healthy,tm.getTree(0).getStatus());	//Make sure newly added tree is healthy!

		ts.markTreeForCutDown(8765211);	//Mark it for cutdown!

		assertEquals(Status.MarkedForCutdown, tm.getTree(0).getStatus());

		ts.markTreeHealthy(8765211);

		assertEquals(Status.Healthy, tm.getTree(0).getStatus());
	}

	@Test
	public void testMarkCutDownTreeHealthy() {
		Calendar c = Calendar.getInstance();
		c.set(2018, 02, 01);
		Date aDate = new Date(c.getTimeInMillis());
		Float longitude = 3f;
		Float latitude = 4f;
		String municipality = "NDG";
		Person p = new Person("jim", "student@mail.ca","the321",tm);
		Location l = new Location(longitude,latitude,municipality);

		Tree tree= new Tree("oak", 12, 4, aDate, 13, 8765211, p, tm, l);

		try {
			ts.createTree("oak", aDate, 8765211, p, l);
		} catch (InvalidInputException e) {
			fail("Error");
		}

		assertEquals(Status.Healthy,tm.getTree(0).getStatus());	//Make sure newly added tree is healthy!

		ts.markTreeForCutDown(8765211);	//Mark it for cutdown!
		assertEquals(Status.MarkedForCutdown, tm.getTree(0).getStatus());

		ts.cutDownTree(8765211);
		assertEquals(Status.Cutdown, tm.getTree(0).getStatus());

		assertEquals(false, ts.markTreeHealthy(8765211));
	}


}
