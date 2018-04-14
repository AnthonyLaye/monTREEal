package ca.mcgill.ecse321.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.test.persistence.TestPersistenceDensity;
import ca.mcgill.ecse321.test.service.TestBiodiversityIndex;
import ca.mcgill.ecse321.test.service.TestCarbonSequestration;
import ca.mcgill.ecse321.test.service.TestCreateSurvey;
import ca.mcgill.ecse321.test.service.TestCreateTree;
import ca.mcgill.ecse321.test.service.TestCutdownTree;
import ca.mcgill.ecse321.test.service.TestFindAllTrees;
import ca.mcgill.ecse321.test.service.TestGetAllTreesByMunicipality;
import ca.mcgill.ecse321.test.service.TestGetStatus;
import ca.mcgill.ecse321.test.service.TestGetTreesByArea;
import ca.mcgill.ecse321.test.service.TestListTreesForResident;
import ca.mcgill.ecse321.test.service.TestLogin;
import ca.mcgill.ecse321.test.service.TestMarkTreeDiseased;
import ca.mcgill.ecse321.test.service.TestMarkTreeForCutdown;

@RunWith(Suite.class)
@SuiteClasses({TestBiodiversityIndex.class, TestCreateSurvey.class, 
	TestCreateTree.class, TestCutdownTree.class, TestFindAllTrees.class, 
	TestGetAllTreesByMunicipality.class, TestGetTreesByArea.class, 
	TestListTreesForResident.class,TestMarkTreeDiseased.class, 
	TestMarkTreeForCutdown.class, TestGetStatus.class, TestPersistenceDensity.class, 
	TestCarbonSequestration.class, TestLogin.class})
public class AllTests {
}
