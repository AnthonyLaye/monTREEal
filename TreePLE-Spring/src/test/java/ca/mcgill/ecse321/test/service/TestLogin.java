package ca.mcgill.ecse321.test.service;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;
import ca.mcgill.ecse321.treePLE.service.InvalidInputException;
import ca.mcgill.ecse321.treePLE.service.TreePLEService;

public class TestLogin {
	private TreePLEManager tm;


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
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
	public void testUserExistsInSystem() {
		TreePLEManager tm = new TreePLEManager();
		String role;
		List<Person> loggedIn=null;
		tm.addPerson("Marcus", "marcus@mail.mcgill.ca", "allo123");

		TreePLEService ts = new TreePLEService(tm);
		try {
			role=ts.login("marcus@mail.mcgill.ca", "allo123");
			assertEquals("Unspecified", role);

		} catch (InvalidInputException e) {
			String error = e.getMessage();
		}
	}

	@Test
	public void testEmailNotInSystem() {
		TreePLEManager tm = new TreePLEManager();
		String role;
		String error="";
		tm.addPerson("Marcus", "marcus@mail.mcgill.ca", "allo123");

		TreePLEService ts = new TreePLEService(tm);
		try {
			role=ts.login("m@mail.mcgill.ca", "allo123");
			assertEquals("None", role);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

	}

	@Test
	public void testIncorrectPassword() {
		TreePLEManager tm = new TreePLEManager();
		String role;
		String error="";
		tm.addPerson("Marcus", "marcus@mail.mcgill.ca", "allo123");

		TreePLEService ts = new TreePLEService(tm);
		try {
			role=ts.login("marcus@mail.mcgill.ca", "alloesfd3");
			assertEquals("None", role);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

	}

	@Test
	public void testWithoutAsperand() {
		TreePLEManager tm = new TreePLEManager();
		String role;
		String error="";
		tm.addPerson("Marcus", "marcus@mail.mcgill.ca", "allo123");

		TreePLEService ts = new TreePLEService(tm);
		try {
			role=ts.login("marcus.ca", "allo123");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The email passed is not an email, does not contain @. You may want to register below.", error);
	}

	@Test
	public void testWithoutExtension() {
		TreePLEManager tm = new TreePLEManager();
		String role;
		String error="";
		tm.addPerson("Marcus", "marcus@mail.mcgill.ca", "allo123");

		TreePLEService ts = new TreePLEService(tm);
		try {
			role=ts.login("marcus@mail.mcgill.sedf", "allo123");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("The email address passed does not have the correct extension", error);
	}

	@Test
	public void testEmptyEmail() {
		TreePLEManager tm = new TreePLEManager();
		String role;
		String error="";
		tm.addPerson("Marcus", "marcus@mail.mcgill.ca", "allo123");

		TreePLEService ts = new TreePLEService(tm);
		try {
			role=ts.login("", "allo123");
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		assertEquals("Nothing is entered. You may want to register below", error);
	}

	@Test
	public void testEmptyPassword() {
		TreePLEManager tm = new TreePLEManager();
		String role;
		String error="";
		tm.addPerson("Marcus", "marcus@mail.mcgill.ca", "allo123");

		TreePLEService ts = new TreePLEService(tm);
		try {
			role=ts.login("m@mail.mcgill.ca", "");
		} catch (InvalidInputException e) {
			error = e.getMessage();

		}
		assertEquals("Nothing is entered. You may want to register below", error);
	}
	
}
