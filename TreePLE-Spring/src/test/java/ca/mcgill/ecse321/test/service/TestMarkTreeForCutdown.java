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

public class TestMarkTreeForCutdown {
	
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

	/* This method creates a new healthy tree and marks it for cutdown. The test checks if the tree has succesfully
	 * been marked for cutdown.
	 */
	@Test
	public void testMarkHealthyTreeForCutdown() {
		
		Calendar c = Calendar.getInstance();
		c.set(2018, 02, 01);
		Date aDate = new Date(c.getTimeInMillis());
		Float longitude = 3f;
		Float latitude = 4f;
		String municipality = "NDG";
		Person p = new Person("jim","student@mail.ca","the321", tm);
		Location l = new Location(longitude,latitude,municipality);
		
		Tree tree= new Tree("oak", 12, 4, aDate, 13, 8765211, p, tm, l);
		
		Assert.assertEquals(Status.Healthy,tm.getTree(0).getStatus());	//Make sure newly added tree is healthy!
		
		ts.markTreeForCutDown(8765211);	//Mark it for cutdown!
		
		Assert.assertEquals(Status.MarkedForCutdown, tm.getTree(0).getStatus());	//Make sure tree is marked for cutdown
	}
	
	/* This method creates a new healthy tree and cuts it down. The method then tries to mark it for cutdown.
	 */
	@Test
	public void testMarkRemovedTreeForCutdown() {
		
		Calendar c = Calendar.getInstance();
		c.set(2018, 02, 01);
		Date aDate = new Date(c.getTimeInMillis());
		Float longitude = 3f;
		Float latitude = 4f;
		String municipality = "NDG";
		Person p = new Person("jim","student@mail.ca","the321", tm);
		Location l = new Location(longitude,latitude,municipality);
		
		Tree tree= new Tree("oak", 12, 4, aDate, 13, 8765212, p, tm, l);
		
		ts.markTreeForCutDown(8765212);	//Must mark tree for cutdown before cutting it down!

		ts.cutDownTree(8765212);

		Assert.assertEquals(Status.Cutdown,tm.getTree(0).getStatus());	//Make sure newly added tree cut down
		
		ts.markTreeForCutDown(8765211);	//Mark it for cutdown!
		
		Assert.assertEquals(Status.Cutdown,tm.getTree(0).getStatus());	//Make sure tree status not changed, because it is already cut down!
	}
	
	/* This method creates a new healthy tree and marks it for cut down. The method then tries to mark it for cutdown again.
	 */
	@Test
	public void testMarkTreeForCutdownTwice() {
		
		Calendar c = Calendar.getInstance();
		c.set(2018, 02, 01);
		Date aDate = new Date(c.getTimeInMillis());
		Float longitude = 3f;
		Float latitude = 4f;
		String municipality = "NDG";
		Person p = new Person("jim", "student@mail.ca","the321",tm);
		Location l = new Location(longitude,latitude,municipality);
		
		Tree tree= new Tree("oak", 12, 4, aDate, 13, 8765211, p, tm, l);

		ts.markTreeForCutDown(8765211);	//Mark tree for cutdown

		Assert.assertEquals(Status.MarkedForCutdown,tm.getTree(0).getStatus());	//Make sure newly added tree is marked for cutdown
		
		Assert.assertEquals(false ,ts.markTreeForCutDown(8765211)); //If you try to mark it for cutdown again, method should return false!
		Assert.assertEquals(Status.MarkedForCutdown,tm.getTree(0).getStatus());	//Make sure nothing changed
	}
}
