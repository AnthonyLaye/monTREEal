package ca.mcgill.ecse321.treePLE.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.model.Tree.Status;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;

@Service
public class TreePLEService {

    public TreePLEManager tm;
    
    public TreePLEService(TreePLEManager tm) {
    	this.tm = tm;
    }

    public Tree createTree(String aSpecies, float aHeight, int aAge, Date aDate, float aDiameter, int aId, Person aPerson, Location aLocation)
        throws InvalidInputException{

        if(aSpecies == null || aHeight == 0 || aAge == 0 || aDate == null || aDiameter == 0){
            throw new InvalidInputException("Something is empty!");
        }

        Tree tree= new Tree(aSpecies, aHeight, aAge, aDate, aDiameter, aId, aPerson, tm, aLocation);
        tm.addTree(tree);
        PersistenceXStream.saveToXMLwithXStream(tm);
        return tree;
    }
    
    public Tree createTree(String aSpecies, Date aDate, int aId, Person aPerson, Location aLocation)
    	throws InvalidInputException{
    	
    	String personName = aPerson.getName().toString();
    	
    	if(aSpecies == null || aDate == null || personName == null || personName == " "){
        	throw new InvalidInputException("Something is empty!");
        }
    	
    	Tree tree= new Tree(aSpecies, aDate, aId, aPerson, tm, aLocation);
    	tm.addTree(tree);
    	PersistenceXStream.saveToXMLwithXStream(tm);
    	return tree;
    }

    public List<Tree> findAllTrees() {
        return tm.getTrees();
    }
    
    public boolean cutDownTree(int aId) {
    	boolean wasCutDown = false;
    	for (Tree tree : tm.getTrees()) {
    		int treeID = tree.getId();
    		if (aId == treeID) {
    			tree.setStatus(Status.Cutdown);
    			wasCutDown = true;
    			break;
    		}
    	}
    	PersistenceXStream.saveToXMLwithXStream(tm);
    	return wasCutDown;
    }
    
    public boolean markTreeForCutDown(int aId) {
    	boolean markedForCutDown = false;
    	for (Tree tree : tm.getTrees()) {
    		int treeID = tree.getId();
    		if (aId == treeID) {
    			if(tree.getStatus() == Status.Cutdown || tree.getStatus() == Status.MarkedForCutdown) {
    				markedForCutDown = false;
    				break;
    			}
    			else {
    				tree.setStatus(Status.MarkedForCutdown);
    				markedForCutDown = true;
    				break;
    			}
    		}
    	}
    	PersistenceXStream.saveToXMLwithXStream(tm);
    	return markedForCutDown;
    }
    
    public boolean markTreeDiseased(int aId) {
    	boolean markedDiseased = false;
    	for (Tree tree : tm.getTrees()) {
    		int treeID = tree.getId();
    		if (aId == treeID) {
    			if(tree.getStatus() == Status.Cutdown || tree.getStatus() == Status.Diseased || tree.getStatus() == Status.MarkedForCutdown) {
    				markedDiseased = false;
    				break;
    			}
    			tree.setStatus(Status.Diseased);
    			markedDiseased = true;
    			break;
    		}
    	}
    	PersistenceXStream.saveToXMLwithXStream(tm);
    	return markedDiseased;
    }
    
    	/**
	 * This method is to calculate the biodiversity index of a list of given trees. 
	 * the index is a sustainability attribute to see how many different species there is
	 * relative to the number of individuals (trees)
	 * @param a list of trees of type Tree
	 * @return a double that is a ratio between the number of different species over the number
	 * of trees
	 */
	public double calculateBiodiversityIndex(List<Tree> trees) {
		double index=0;
		double counterSpecies=0;
		double nbTrees=0;
		List<String> diffSpecies = new ArrayList<String>();		//initially empty and add elements
		int i=0;
		for(Tree t: trees) {
			String speciesName=t.getSpecies();
			nbTrees=nbTrees+1;

			if(!(containsString(diffSpecies, speciesName))) {
				diffSpecies.add(i, speciesName);
				counterSpecies=counterSpecies+1;
				i=i+1;		//increment the index to add elements
			}
		}
		if(nbTrees==0) {
			index=0;
		}
		else {
			index=counterSpecies/nbTrees;
		}
		return index;
	}
	/**
	 * The method containsString is to check if a string is present in a list of strings
	 * @param a List of Strings: here the list is a list of different species of trees diffSpecies
	 * @param a string name that needs to be compared to the ones inside the list
	 * @return a boolean value
	 * @return true, if the string passed is already in the list
	 * @return false, if the string passed is NOT in the list
	 */
	public boolean containsString(List<String> diffSpecies, String name) {
		for(String diffT: diffSpecies) {
			if(diffT.equals(name)) {
				return true;
			}
		}
		return false;		//going through the whole list and no match
	}
    
}
