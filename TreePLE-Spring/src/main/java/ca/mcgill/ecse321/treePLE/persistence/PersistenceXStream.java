package ca.mcgill.ecse321.treePLE.persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

public class PersistenceXStream {
	private static XStream xstream = new XStream();
	private static String filename = "data.xml";

	// TODO create the RegistrationManager instance here (replace the void return value as well)
	public static void initializeModelManager(String fileName) {
	}

	public static boolean saveToXMLwithXStream(Object obj) {
		return false;
	}

	public static Object loadFromXMLwithXStream() {
		return null;
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
