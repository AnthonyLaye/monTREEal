package ca.mcgill.ecse321.test.persistence;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;

import java.sql.Date;
import java.util.Calendar;

import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestPersistence {

	private TreePLEManager tm;

	@Before
	public void setUp() throws Exception {
		tm = new TreePLEManager();
		
		// plant trees
				Tree t1 = new Tree("oak", Date(2018,02,04) ,21 , Person("Jeff",tm), Location(2.00,2.00,"NDG"));
				Tree t2 = new Tree("maple", Date(2018,02,03) ,22 , Person("George",tm), Location(3.05,9.00,"Outremont"));

			    tm.addTree(t1);
			    tm.addTree(t2);
	}

	@After
	public void tearDown() throws Exception {
		tm.delete();
	}

	@Test
	public void test() {
		 PersistenceXStream.initializeModelManager("output"+File.separator+"data.xml");
		    // save model that is loaded during test setup
		    if (!PersistenceXStream.saveToXMLwithXStream(rm))
		    	fail("Could not save file.");
		
		    tm.delete();
		    assertEquals(0, tm.getTrees().size());
		    
		    tm = (TreeManager) PersistenceXStream.loadFromXMLwithXStream();
		    if(tm == null)
		    	fail("Could not load file.")
		    	
		    //check trees
		    assertEquals(2, tm.getTrees().size());
		    
		    AssertEquals("oak",tm.getTree(0).getSpecies());
		    AssertEquals("maple",tm.getTree(1).getSpecies());
		    
		    Calendar c = Calendar.getInstance();
		    c.set(2018, 02, 04);
		    
		    AssertEquals(c,tm.getTree(0).getDate());
		    c.set(2018, 02, 03);
		    AssertEquals(c,tm.getTree(1).getDate());
		    
		    AssertEquals(21,tm.getTree(0).getId());
		    AssertEquals(22,tm.getTree(1).getId());
		    
		    AssertEquals(Person("Jeff",tm),tm.getTree(0).getPerson());
		    AssertEquals(Person("George",tm),tm.getTree(1).getPerson());
		    
		    AssertEquals(tm,tm.getTree(0).getTreePLEManager());
		    AssertEquals(tm,tm.getTree(1).getTreePLEManager());
		    
		    AssertEquals(Location(2.00,2.00, "NDG"),tm.getTree(0).getLocation());
		    AssertEquals(Location(3.05,9.00, "Outremont"),tm.getTree(1).getLocation());
	}

}
