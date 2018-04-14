package ca.mcgill.ecse321.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;
import ca.mcgill.ecse321.treePLE.service.InvalidInputException;
import ca.mcgill.ecse321.treePLE.service.TreePLEService;
import junit.framework.Assert;

	public class TestBiodiversityIndex {

		@BeforeClass
		public static void setUpBeforeClass() throws Exception {
			PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
		}

		@Test
		// this test is the ideal test, where there is a collection of trees, that contain trees that 
		// that both have repetitive species' name and different ones
		public void testBiodiversityIndex() {
			TreePLEManager tm = new TreePLEManager();
			TreePLEService ts = new TreePLEService(tm);
			//create Tree1
			String aSpecies = "willow";
			Calendar c = Calendar.getInstance();
			c.set(2018, 02, 01);
			Date aDate = new Date(c.getTimeInMillis());
			Integer randomNum = 1;
			String name = "Joe";
			Float longitude = 3f;
			Float latitude = 4f;
			String municipality = "NDG";
			Person p = new Person(name,"joe.bob@mail.ca","des",tm);
			Location l = new Location(longitude,latitude,municipality);

			//create Tree2
			String aSpecies1 = "oak";
			c.set(2018, 02, 03);
			Date aDate1 = new Date(c.getTimeInMillis());
			Integer randomNum1 = 2;
			String name1 = "Jack";
			Float longitude1 = 4f;
			Float latitude1 = 5f;
			String municipality1 = "Outremont";
			Person p1 = new Person(name1,"jack.pot@des.com","allo", tm);
			Location l1 = new Location(longitude1,latitude1,municipality1);

			//create Tree3
			String aSpecies2 = "oak";
			c.set(2018, 02, 03);
			Date aDate2 = new Date(c.getTimeInMillis());
			Integer randomNum2 = 3;
			String name2 = "Jack";
			Float longitude2 = 4f;
			Float latitude2 = 5f;
			String municipality2 = "MontRoyal";
			Person p2 = new Person(name1,"","", tm);
			Location l2 = new Location(longitude1,latitude1,municipality1);

			//create Tree4
			String aSpecies3 = "maple";
			c.set(2018, 02, 01);
			Date aDate3 = new Date(c.getTimeInMillis());
			Integer randomNum3 = 4;
			String name3 = "Margo";
			Float longitude3 = 2f;
			Float latitude3 = 5f;
			String municipality3 = "DT";
			Person p3 = new Person(name, "margo1@allo.ca", "desd",tm);
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
			Person p4 = new Person(name, "margo@allo.ca", "desd",tm);
			Location l4 = new Location(longitude,latitude,municipality);

			try {
				ts.createTree(aSpecies, aDate, randomNum, p, l);
				ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
				ts.createTree(aSpecies2, aDate2, randomNum2, p2, l2);
				ts.createTree(aSpecies3, aDate3, randomNum3, p3, l3);
				ts.createTree(aSpecies4, aDate4, randomNum4, p4, l4);

			} catch (InvalidInputException e) {
				e.printStackTrace();
			}

			List<Tree> selectedTrees = null;
			try {
				selectedTrees = ts.findAllTrees();
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
			double index=ts.calculateBiodiversityIndex(selectedTrees);
			assertEquals(0.6, index, 0.0001);

			tm.delete();
		}

		@Test
		// This test is for when the species of a list of trees are all different
		// therefore the expected index should be at its max, thus equal to 1
		public void testBiodiversityIndexAllDiff() {

			TreePLEManager tm = new TreePLEManager();
			TreePLEService ts = new TreePLEService(tm);

			//create Tree1
			String aSpecies = "pine";
			Calendar c = Calendar.getInstance();
			c.set(2018, 02, 01);
			Date aDate = new Date(c.getTimeInMillis());
			Integer randomNum = 1;
			String name = "Joe";
			Float longitude = 3f;
			Float latitude = 4f;
			String municipality = "NDG";
			Person p = new Person(name,"joe.bob@mail.ca","des",tm);
			Location l = new Location(longitude,latitude,municipality);

			//create Tree2
			String aSpecies1 = "oak";
			c.set(2018, 02, 03);
			Date aDate1 = new Date(c.getTimeInMillis());
			Integer randomNum1 = 2;
			String name1 = "Jack";
			Float longitude1 = 4f;
			Float latitude1 = 5f;
			String municipality1 = "Outremont";
			Person p1 = new Person(name1,"jack.pot@des.com","allo", tm);
			Location l1 = new Location(longitude1,latitude1,municipality1);

			//create Tree3
			String aSpecies2 = "maple";
			c.set(2018, 02, 03);
			Date aDate2 = new Date(c.getTimeInMillis());
			Integer randomNum2 = 3;
			String name2 = "Jack";
			Float longitude2 = 4f;
			Float latitude2 = 5f;
			String municipality2 = "MontRoyal";
			Person p2 = new Person(name1,"","", tm);
			Location l2 = new Location(longitude2,latitude2,municipality2);

			try {
				ts.createTree(aSpecies, aDate, randomNum, p, l);
				ts.createTree(aSpecies1, aDate1, randomNum1, p1, l1);
				ts.createTree(aSpecies2, aDate2, randomNum2, p2, l2);
			} catch (InvalidInputException e){
				e.printStackTrace();
			}

			List<Tree> selectedTrees = null;
			try {
				selectedTrees = ts.findAllTrees();
			} catch (InvalidInputException e) {
				e.printStackTrace();
			}
			double index=ts.calculateBiodiversityIndex(selectedTrees);
			assertEquals(1, index, 0.0001);
			tm.delete();
		}


		@Test
		// this test is for when there are trees given to calculate the biodiversity index
		// this test is expected to fail
		public void testBiodiversityIndexZero() {

			TreePLEManager tm = new TreePLEManager();
			TreePLEService ts = new TreePLEService(tm);
			String error = null;

			List<Tree> selectedTrees = null;
			try {
				selectedTrees = ts.findAllTrees();
			} catch (InvalidInputException e) {
				error = e.getMessage();
			}
			
			assertEquals("There are not trees to get from the manager", error);
			tm.delete();
		}

	}
