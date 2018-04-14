package ca.mcgill.ecse321.test.service;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
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
		
		String error = null;
		
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
		Person p = new Person(name,"student@mail.ca","the321", tm);
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
		Person p1 = new Person(name1, "student@mail.ca","the321",tm);
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
		Person p2 = new Person(name1,"student@mail.ca","the321", tm);
		Location l2 = new Location(longitude1,latitude1,municipality1);
		
		try {
			Tree t1 = ts.createTree(aSpecies, aDate, randomNum, p, l);
			Tree t2 = ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
			ts.createTree(aSpecies2, aDate2, randomNum2, p2, l2);

		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		try {
			selectedTrees = ts.findAllTrees();
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}

		double result = ts.calculateCarbonSequestration(selectedTrees);
		Assert.assertNotEquals(13.95, result, 1);

		boolean saved = PersistenceDensity.saveToXMLwithXStream(csm);
		assertEquals(true, saved);

		tm.delete();
	}
}
