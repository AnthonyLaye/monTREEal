package ca.mcgill.ecse321.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
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

public class TestListTreesForResident {

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
	public void testFindTreesForResidentExists() {

		assertEquals(0, tm.getTrees().size());

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

		String aSpecies1 = "oak";
		c.set(2018, 02, 03);
		Date aDate1 = new Date(c.getTimeInMillis());
		Integer randomNum1 = 2;
		String name1 = "Jack";
		Float longitude1 = 4f;
		Float latitude1 = 5f;
		String municipality1 = "Outremont";
		Person p1 = new Person(name1,"student@mail.ca","the321", tm);
		Location l1 = new Location(longitude1,latitude1,municipality1);

		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
			ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}


		List<Tree> JoeTrees = ts.findTreesForResident("Joe");
		assertEquals(1, JoeTrees.size());

		int id = randomNum;
		assertEquals(id, JoeTrees.get(0).getId());

		assertEquals("willow", JoeTrees.get(0).getSpecies());
		assertEquals(p, JoeTrees.get(0).getPerson());
		assertEquals(tm,JoeTrees.get(0).getTreePLEManager());
	}

	@Test
	public void testFindTreesForResidentMultiple() {

		assertEquals(0, tm.getTrees().size());

		String aSpecies1 = "willow";
		Calendar c1 = Calendar.getInstance();
		c1.set(2018, 02, 01);
		Date aDate1 = new Date(c1.getTimeInMillis());
		Integer randomNum1 = 1;
		String name1 = "Jim";
		Float longitude1 = 3f;
		Float latitude1 = 4f;
		String municipality1 = "NDG";
		Person p1 = new Person(name1, "student@mail.ca","the321",tm);
		Location l1 = new Location(longitude1,latitude1,municipality1);

		String aSpecies2 = "oak";
		Calendar c2 = Calendar.getInstance();
		c2.set(2018, 02, 03);
		Date aDate2 = new Date(c2.getTimeInMillis());
		Integer randomNum2 = 2;
		String name2 = "Jim";
		Float longitude2 = 4f;
		Float latitude2 = 5f;
		String municipality2 = "Outremont";
		Person p2 = new Person(name2, "student@mail.ca","the321",tm);
		Location l2 = new Location(longitude2,latitude2,municipality2);

		String aSpecies3 = "maple";
		Calendar c3 = Calendar.getInstance();
		c3.set(2018, 01, 05);
		Date aDate3 = new Date(c3.getTimeInMillis());
		Integer randomNum3 = 3;
		String name3 = "Jim";
		Float longitude3 = 5f;
		Float latitude3 = 6f;
		String municipality3 = "NDG";
		Person p3 = new Person(name3, "student@mail.ca","the321",tm);
		Location l3 = new Location(longitude3,latitude3,municipality3);

		try {
			ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
			ts.createTree(aSpecies2, aDate2, randomNum2, p2, l2);
			ts.createTree(aSpecies3, aDate3, randomNum3, p3, l3);
		} catch (InvalidInputException e) {
			fail("Error");
		}


		List<Tree> JimTrees = ts.findTreesForResident("Jim");
		assertEquals(3, JimTrees.size());

		int id1 = randomNum1;
		int id2 = randomNum2;
		int id3 = randomNum3;
		assertEquals(id1, JimTrees.get(0).getId());
		assertEquals(id2, JimTrees.get(1).getId());
		assertEquals(id3, JimTrees.get(2).getId());

		assertEquals("willow", JimTrees.get(0).getSpecies());
		assertEquals("oak", JimTrees.get(1).getSpecies());
		assertEquals("maple", JimTrees.get(2).getSpecies());

		assertEquals(p1, JimTrees.get(0).getPerson());
		assertEquals(p2, JimTrees.get(1).getPerson());
		assertEquals(p3, JimTrees.get(2).getPerson());

		assertEquals(tm,JimTrees.get(0).getTreePLEManager());
		assertEquals(tm,JimTrees.get(1).getTreePLEManager());
		assertEquals(tm,JimTrees.get(2).getTreePLEManager());
	}

	@Test
	public void testFindTreesForResidentDoesNotExists() {

		assertEquals(0, tm.getTrees().size());

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

		String aSpecies1 = "oak";
		c.set(2018, 02, 03);
		Date aDate1 = new Date(c.getTimeInMillis());
		Integer randomNum1 = 2;
		String name1 = "Jack";
		Float longitude1 = 4f;
		Float latitude1 = 5f;
		String municipality1 = "Outremont";
		Person p1 = new Person(name1,"student@mail.ca","the321", tm);
		Location l1 = new Location(longitude1,latitude1,municipality1);

		try {
			ts.createTree(aSpecies, aDate, randomNum, p, l);
			ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}


		List<Tree> JillTrees = ts.findTreesForResident("Jill");
		assertEquals(2, tm.getTrees().size());
		assertEquals(0, JillTrees.size());
	}

	@Test
	public void testFindTreesForResidentEmpty() {

		assertEquals(0, tm.getTrees().size());

		List<Tree> trees = ts.findTreesForResident("Joe");
		assertEquals(0, trees.size());
	}

}
