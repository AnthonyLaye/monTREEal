package ca.mcgill.ecse321.treePLE.service;

import ca.mcgill.ecse321.treePLE.model.Location;
import ca.mcgill.ecse321.treePLE.model.Person;
import ca.mcgill.ecse321.treePLE.model.Tree;
import ca.mcgill.ecse321.treePLE.model.TreePLEManager;
import ca.mcgill.ecse321.treePLE.persistence.PersistenceXStream;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TreePLEService {

    private TreePLEManager tm;
    
    public TreePLEService(TreePLEManager tm) {
    	this.tm = tm;
    }

    public Tree createTree(String aSpecies, float aHeight, int aAge, Date aDate, float aDiameter, int aId, Person aPerson, TreePLEManager aTreePLEManager, Location aLocation)
        throws InvalidInputException{

        if(aSpecies == null || aHeight == 0 || aAge == 0 || aDate == null || aDiameter == 0){
            throw new InvalidInputException("Something is empty!");
        }

        Tree tree= new Tree(aSpecies, aHeight, aAge, aDate, aDiameter, aId, aPerson, aTreePLEManager, aLocation);
        //tm.addTree(tree);
        //PersistenceXStream.saveToXMLwithXStream(tm);
        return tree;
    }

    public List<Tree> findAllTrees() {
        return tm.getTrees();
    }
}
