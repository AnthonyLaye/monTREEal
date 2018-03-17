package ca.mcgill.ecse321.treePLE.service;

import java.sql.Date;
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
    			tree.setStatus(Status.CutDown);
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
    			if(tree.getStatus() == Status.CutDown || tree.getStatus() == Status.Diseased || tree.getStatus() == Status.MarkedForCutdown) {
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
    			tree.setStatus(Status.Diseased);
    			markedDiseased = true;
    			break;
    		}
    	}
    	PersistenceXStream.saveToXMLwithXStream(tm);
    	return markedDiseased;
    }
    
}
