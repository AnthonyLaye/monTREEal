package ca.mcgill.ecse321.test.persistence;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.treePLE.model.CarbonSequestrationManager;
import ca.mcgill.ecse321.treePLE.model.SpeciesDensities;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceDensity;

public class TestPersistenceDensity {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		PersistenceDensity.initializeModelManager("output"+File.separator+"Density3.xml");
		CarbonSequestrationManager csm = new CarbonSequestrationManager();
		csm=(CarbonSequestrationManager)PersistenceDensity.loadFromXMLwithXStream();

		assertEquals(4, csm.numberOfSpeciesDensities());
		SpeciesDensities sd =csm.getSpeciesDensity(1);
		String name = sd.getSpecies();
		System.out.println(name);
		String species = "butternut";


		boolean saved = PersistenceDensity.saveToXMLwithXStream(csm);
		assertEquals(true, saved);
	}

}
