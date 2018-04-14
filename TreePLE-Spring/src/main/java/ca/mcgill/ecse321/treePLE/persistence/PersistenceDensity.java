package ca.mcgill.ecse321.treePLE.persistence;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

import ca.mcgill.ecse321.treePLE.model.CarbonSequestrationManager;
import ca.mcgill.ecse321.treePLE.model.SpeciesDensities;

public class PersistenceDensity {
	private static XStream xstream = new XStream();
	private static String filename = "/tmp/Density3.xml";
	
	public static CarbonSequestrationManager initializeModelManager(String fileName) {
		// Initialization for persistence
		CarbonSequestrationManager csm;
		setFilename(fileName);
		setAlias("manager", CarbonSequestrationManager.class);
		setAlias("speciesDensities", SpeciesDensities.class);

		// load model if exists, create otherwise
		File file = new File(fileName);
		if (file.exists()) {
			csm = (CarbonSequestrationManager) loadFromXMLwithXStream();
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			//the values here are the values added 
			csm = new CarbonSequestrationManager();
			SpeciesDensities sd1 = new SpeciesDensities("maple", 200, "Maple", csm);
			SpeciesDensities sd2 = new SpeciesDensities("blackwalnut", 626, "Black Walnut", csm);
			SpeciesDensities sd3 = new SpeciesDensities("butternut", 368, "Butternut", csm);
			csm.addSpeciesDensity(sd1);
			csm.addSpeciesDensity(sd2);
			csm.addSpeciesDensity(sd3);
			saveToXMLwithXStream(csm);
		}
		return csm;
	}

	public static boolean saveToXMLwithXStream(Object obj) {
		xstream.setMode(XStream.ID_REFERENCES);
		String xml = xstream.toXML(obj); // save our xml file

        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(xml);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public static Object loadFromXMLwithXStream() {
		xstream.setMode(XStream.ID_REFERENCES);
        try {
            FileReader fileReader = new FileReader(filename); // load our xml file
            return xstream.fromXML(fileReader);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}

	public static void setAlias(String xmlTagName, Class<?> className) {
		xstream.alias(xmlTagName, className);
	}

	public static void setFilename(String fn) {
		filename = fn;
	}

	public static String getFilename() {
		return filename;
	}

	public static void clearData() {
		File myFoo = new File(filename);
		FileWriter fooWriter;
		try {
			fooWriter = new FileWriter(myFoo, false);
			fooWriter.write("");
			fooWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
