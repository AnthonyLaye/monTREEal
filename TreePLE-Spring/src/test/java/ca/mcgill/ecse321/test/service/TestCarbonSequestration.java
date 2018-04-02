package ca.mcgill.ecse321.test.service;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.treePLE.model.CarbonSequestrationManager;
import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.SpeciesDensities;
import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceDensity;
import ca.mcgill.ecse321.treePLE.service.InvalidInputException;
import ca.mcgill.ecse321.treePLE.service.TreePLEService;

public class TestCarbonSequestration {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceDensity.initializeModelManager("output"+File.separator+"Density.xml");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		TreePLEManager tm = new TreePLEManager();
		TreePLEService ts = new TreePLEService(tm);
		
		CarbonSequestrationManager csm = new CarbonSequestrationManager();
		csm=(CarbonSequestrationManager)PersistenceDensity.loadFromXMLwithXStream();
		List<Tree> selectedTrees = null;
		
		//create Tree1
		String aSpecies = "maple";
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

		//create Tree2
		String aSpecies1 = "blackwalnut";
		c.set(2018, 02, 03);
		Date aDate1 = new Date(c.getTimeInMillis());
		Integer randomNum1 = 2;
		String name1 = "Jack";
		Float longitude1 = 4f;
		Float latitude1 = 5f;
		String municipality1 = "Outremont";
		Person p1 = new Person(name1, tm);
		Location l1 = new Location(longitude1,latitude1,municipality1);

		//create Tree3
		String aSpecies2 = "blackwalnut";
		c.set(2018, 02, 03);
		Date aDate2 = new Date(c.getTimeInMillis());
		Integer randomNum2 = 3;
		String name2 = "Jack";
		Float longitude2 = 4f;
		Float latitude2 = 5f;
		String municipality2 = "MontRoyal";
		Person p2 = new Person(name1, tm);
		Location l2 = new Location(longitude1,latitude1,municipality1);

/*		//create Tree4
		String aSpecies3 = "maple";
		c.set(2018, 02, 01);
		Date aDate3 = new Date(c.getTimeInMillis());
		Integer randomNum3 = 4;
		String name3 = "Margo";
		Float longitude3 = 2f;
		Float latitude3 = 5f;
		String municipality3 = "DT";
		Person p3 = new Person(name, tm);
		Location l3 = new Location(longitude,latitude,municipality);
		
		//create Tree5
		String aSpecies4 = "oak";
		c.set(2018, 02, 01);
		Date aDate4 = new Date(c.getTimeInMillis());
		Integer randomNum4 = 5;
		String name4 = "Margo";
		Float longitude4 = 2f;
		Float latitude4 = 5f;
		String municipality4 = "DT";
		Person p4 = new Person(name, tm);
		Location l4 = new Location(longitude,latitude,municipality);
*/
		try {
			Tree t1 = ts.createTree(aSpecies, aDate, randomNum, p, l);
			Tree t2 = ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
			ts.createTree(aSpecies2, aDate2, randomNum2, p2, l2);
			//ts.createTree(aSpecies3, aDate3, randomNum3, p3, l3);
			//ts.createTree(aSpecies4, aDate4, randomNum4, p4, l4);

		} catch (InvalidInputException e) {
			fail("Error");
		}
		
		//assertEquals(3, csm.numberOfSpeciesDensities());
		try {
			selectedTrees = ts.findAllTrees();
		} catch (InvalidInputException e) {
			fail("Error");
		}

		double result = ts.calculateCarbonSequestration(selectedTrees);
		//assertEquals(1.9216, result, 1);
		assertEquals(13.95, result, 1);

		boolean saved = PersistenceDensity.saveToXMLwithXStream(csm);
		assertEquals(true, saved);

		tm.delete();
	}
}
