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
					Person p = new Person(name, tm);
					Location l = new Location(longitude,latitude,municipality);

			try {
				ts.createTree(aSpecies, aDate, randomNum, p, l);
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
			
			ts.markTreeForCutDown(randomNum);
			//try {
				assertTrue("Tree with the given ID could not be found",ts.cutDownTree(randomNum));
			//} catch (InvalidInputException e) {
			//	fail("Error in cutting down tree");
			//}
			
			}
		
		@Test
		public void testCutDownInexistentTree() {
			TreePLEManager tm = new TreePLEManager();
			TreePLEService ts = new TreePLEService(tm);
			Integer randomNum = 1;
			//try {
				Assert.assertFalse(ts.cutDownTree(randomNum));
			//} catch (InvalidInputException e) {
			//	fail("Error in cutting down tree");
			//}
		}
		
//		@Test
//		public void testCutDownACutDownTree() {
//			TreePLEManager tm = new TreePLEManager();
//			TreePLEService ts = new TreePLEService(tm);
//			String aSpecies = "willow";
//					Calendar c = Calendar.getInstance();
//					c.set(2018, 02, 01);
//					Date aDate = new Date(c.getTimeInMillis());
//					Integer randomNum = 1;
//					String name = "Joe";
//					Float longitude = 3f;
//					Float latitude = 4f;
//					String municipality = "NDG";
//					Person p = new Person(name, tm);
//					Location l = new Location(longitude,latitude,municipality);
//
//			try {
//				ts.createTree(aSpecies, aDate, randomNum, p, l);
//			} catch (InvalidInputException e) {
//				e.printStackTrace();
//			}
//			
//			ts.markTreeForCutDown(randomNum);
//			try {
//				assertTrue("Tree with the given ID could not be found",ts.cutDownTree(randomNum));
//			} catch (InvalidInputException e) {
//				fail("Error in cutting down tree");
//			}
//			
//			try {
//				assertTrue("Tree with the given ID could not be found",ts.cutDownTree(randomNum));
//			} catch (InvalidInputException e) {
//				fail("The tree has already been cut down");
//			}
//		}
		
//		@Test
//		public void testCutDownAHealthyTree() {
//			TreePLEManager tm = new TreePLEManager();
//			TreePLEService ts = new TreePLEService(tm);
//			String aSpecies = "willow";
//					Calendar c = Calendar.getInstance();
//					c.set(2018, 02, 01);
//					Date aDate = new Date(c.getTimeInMillis());
//					Integer randomNum = 1;
//					String name = "Joe";
//					Float longitude = 3f;
//					Float latitude = 4f;
//					String municipality = "NDG";
//					Person p = new Person(name, tm);
//					Location l = new Location(longitude,latitude,municipality);
//
//			try {
//				ts.createTree(aSpecies, aDate, randomNum, p, l);
//			} catch (InvalidInputException e) {
//				e.printStackTrace();
//			}
//			
//			try {
//				assertTrue("Tree with the given ID could not be found",ts.cutDownTree(randomNum));
//			} catch (InvalidInputException e) {
//				fail("The tree you are trying to cut down has not been marked for cutdown");
//			}
//			
//			
//		}

}
