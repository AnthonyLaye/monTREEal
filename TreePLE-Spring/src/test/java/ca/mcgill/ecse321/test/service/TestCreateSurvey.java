package ca.mcgill.ecse321.test.service;

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
import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.model.Tree.Status;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;
import ca.mcgill.ecse321.treePLE.service.InvalidInputException;
import ca.mcgill.ecse321.treePLE.service.TreePLEService;

public class TestCreateSurvey {
	
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
	public void testCreateSurvey() {
		
		assertEquals(0,tm.getTrees().size());

		String aSpecies = "butternut";
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
		
		Calendar cSurvey = Calendar.getInstance();
		cSurvey.set(2018, 05, 01);
		Date dateSurvey = new Date(cSurvey.getTimeInMillis());
		float height = (float) 52.0;
		float diameter = (float) 22.0;
		String status = "Healthy";
		Integer idSurvey = 123;
		String observerName = "Carlito";
		Person observer = new Person(observerName, "student@mail.ca","the321",tm);

		try {
			Tree t = ts.createTree(aSpecies, height, 1, aDate, diameter, randomNum, p, l);
			ts.createSurvey(dateSurvey, idSurvey, observer, t.getId(), height, diameter, status);
		} catch (InvalidInputException e) {
			fail("Error");
		}
		
		assertEquals(1, tm.getTree(0).getSurveys().size());
		assertEquals(123, tm.getTree(0).getSurvey(0).getId());
		assertEquals(Status.Healthy, tm.getTree(0).getStatus());
		assertEquals(52.0, tm.getTree(0).getHeight(), 0.0);
		assertEquals(22.0, tm.getTree(0).getDiameter(), 0.0);
	}

	@Test
	public void testCreateSurveyTreeDoesNotExist() {
		
		assertEquals(0,tm.getTrees().size());
		
		String error = null;

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
		
		Calendar cSurvey = Calendar.getInstance();
		cSurvey.set(2018, 05, 01);
		Date dateSurvey = new Date(cSurvey.getTimeInMillis());
		Integer idSurvey = 123;
		String observerName = "Carlito";
		Person observer = new Person(observerName, "student@mail.ca","the321",tm);

		try {
			Tree t = ts.createTree(aSpecies, aDate, randomNum, p, l);
			ts.createSurvey(dateSurvey, idSurvey, observer, 123, 1.2f, 2.3f, "Diseased");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(0, tm.getTree(0).getSurveys().size());
	}
	
	@Test
	public void testCreateSurveyNoObserver() {
		
		assertEquals(0,tm.getTrees().size());
		String error = null;

		String aSpecies = "willow";
		Calendar c = Calendar.getInstance();
		c.set(2018, 02, 01);
		Date aDate = new Date(c.getTimeInMillis());
		int randomNum = 1;
		String name = "Joe";
		Float longitude = 3f;
		Float latitude = 4f;
		String municipality = "NDG";
		Person p = new Person(name, "student@mail.ca","the321",tm);
		Location l = new Location(longitude,latitude,municipality);
		
		Calendar cSurvey = Calendar.getInstance();
		cSurvey.set(2018, 05, 01);
		Date dateSurvey = new Date(cSurvey.getTimeInMillis());
		Integer idSurvey = 123;
		String observerName = " ";
		Person observer = new Person(observerName,"student@mail.ca","the321", tm);

		try {
			Tree t = ts.createTree(aSpecies, aDate, randomNum, p, l);
			ts.createSurvey(dateSurvey, idSurvey, observer, randomNum, 2f, 3f, "Healthy");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals(0, tm.getTree(0).getSurveys().size());
		
	}
}
