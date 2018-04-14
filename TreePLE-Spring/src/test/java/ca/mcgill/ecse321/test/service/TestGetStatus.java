package ca.mcgill.ecse321.test.service;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.model.Tree.Status;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;
import ca.mcgill.ecse321.treePLE.service.InvalidInputException;
import ca.mcgill.ecse321.treePLE.service.TreePLEService;

public class TestGetStatus {
	private TreePLEManager tm;
	private TreePLEService ts;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
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
	public void testGetStatusHealthy() {
		//create Tree
		String aSpecies = "willow";
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

		try {
			Tree t1 = ts.createTree(aSpecies, aDate, randomNum, p, l);}
		catch(InvalidInputException e) {
			e.printStackTrace();
		}

		Status status = ts.getStatus(1);
		assertEquals(status, "Healthy");
	}

	@Test
	public void testGetStatusChanged() {
		//create Tree
		String aSpecies = "willow";
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
		
		Tree t1;

		try {
			t1 = ts.createTree(aSpecies, aDate, randomNum, p, l);}
		catch(InvalidInputException e) {
			e.printStackTrace();
		}

		Status status = ts.getStatus(1);
		assertEquals(status, "Healthy");
		
		boolean statusWasChanged = ts.markTreeForCutDown(1);
		assertEquals(true, statusWasChanged);
		
	}
}
