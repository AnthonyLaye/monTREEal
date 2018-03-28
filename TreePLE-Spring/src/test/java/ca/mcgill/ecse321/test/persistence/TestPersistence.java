package ca.mcgill.ecse321.test.persistence;

import static org.junit.Assert.*;

import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import org.junit.*;
import org.junit.Test;

import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;

import java.sql.Date;
import java.util.Calendar;

import java.io.File;
import static org.junit.Assert.fail;

public class TestPersistence {

	private TreePLEManager tm;

	@Before
	public void setUp() throws Exception {
		tm = new TreePLEManager();
		
		// plant trees
				Tree t1 = new Tree("oak", new Date(2018,02,04) ,21 , new Person("Jeff",tm), tm, new Location(2.00f,2.00f,"NDG"));
				Tree t2 = new Tree("maple", new Date(2018,02,03) ,22 , new Person("George",tm), tm, new Location(3.05f,9.00f,"Outremont"));

			    tm.addTree(t1);
			    tm.addTree(t2);
	}

	@After
	public void tearDown() throws Exception {
		tm.delete();
	}

//	@Test
//	public void test() {
//		 PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
//		    // save model that is loaded during test setup
//		    if (!PersistenceXStream.saveToXMLwithXStream(tm))
//		    	fail("Could not save file.");s
//		
//		    tm.delete();
//		    assertEquals(0, tm.getTrees().size());
//		    
//		    tm = (TreePLEManager) PersistenceXStream.loadFromXMLwithXStream();
//		    if(tm == null)
//		    	fail("Could not load file.");
//		    	
//		    //check trees
//		    assertEquals(2, tm.getTrees().size());
//
//			Assert.assertEquals("oak",tm.getTree(0).getSpecies());
//			Assert.assertEquals("maple",tm.getTree(1).getSpecies());
//		    
//		    Calendar c = Calendar.getInstance();
//		    c.set(2018, 02, 04);
//
//			Assert.assertEquals(c,tm.getTree(0).getDate());
//		    c.set(2018, 02, 03);
//			Assert.assertEquals(c,tm.getTree(1).getDate());
//
//			Assert.assertEquals(21,tm.getTree(0).getId());
//			Assert.assertEquals(22,tm.getTree(1).getId());
//		    
//		    Assert.assertEquals(new Person("Jeff",tm),tm.getTree(0).getPerson());
//			Assert.assertEquals(new Person("George",tm),tm.getTree(1).getPerson());
//
//			Assert.assertEquals(tm,tm.getTree(0).getTreePLEManager());
//			Assert.assertEquals(tm,tm.getTree(1).getTreePLEManager());
//
//			Assert.assertEquals(new Location(2.00f,2.00f, "NDG"),tm.getTree(0).getLocation());
//			Assert.assertEquals(new Location(3.05f,9.00f, "Outremont"),tm.getTree(1).getLocation());
//	}

}
